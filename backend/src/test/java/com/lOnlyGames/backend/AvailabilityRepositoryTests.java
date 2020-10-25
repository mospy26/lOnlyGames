package com.lOnlyGames.backend;

import com.lOnlyGames.backend.model.*;
import com.lOnlyGames.backend.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AvailabilityRepositoryTests {

    @Autowired
    private UserRepository user;

    @Autowired
    private AvailabilityRepository availability;

    @Autowired
    private UserAvailabilityRepository userAvailability;

    private Timestamp getTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return new Timestamp(sdf.parse(time).getTime());
    }

    @Test
    public void testAddTimeSlot() throws ParseException {

        String startingDate = "2014/10/29 18:00:00";
        String endingDate = "2014/10/29 20:00:00";

        Timestamp starting = getTime(startingDate);
        Timestamp ending = getTime(endingDate);

        Availability a = new Availability(starting, ending);
        a = availability.save(a);

        assertNotNull(a);
        assertEquals(a.getTimeStart().getTime(), getTime(startingDate).getTime());
        assertEquals(a.getTimeEnd().getTime(), getTime(endingDate).getTime());
    }

    @Test
    public void testMultipleUsersAvailability() throws ParseException {
        User a1 = new User("user1");
        User a2 = new User("user2");
        user.save(a1);
        user.save(a2);

        String startingDate = "2014/10/29 18:00:00";
        String endingDate = "2014/10/29 20:00:00";
        Timestamp starting = getTime(startingDate);
        Timestamp ending = getTime(endingDate);

        Availability a = new Availability(starting, ending);
        a = availability.save(a);

        UserAvailability ua = new UserAvailability(a1, a);
        ua = userAvailability.save(ua);
        UserAvailability ua2 = new UserAvailability(a2, a);
        ua2 = userAvailability.save(ua2);

        assertNotNull(ua);
        assertEquals(ua.getUser().getUsername(), "user1");
        assertEquals(ua.getAvailability().getTimeStart().getTime(), starting.getTime());
        assertEquals(ua.getAvailability().getTimeEnd().getTime(), ending.getTime());

        assertNotNull(ua2);
        assertEquals(ua2.getUser().getUsername(), "user2");
        assertEquals(ua2.getAvailability().getTimeStart().getTime(), starting.getTime());
        assertEquals(ua2.getAvailability().getTimeEnd().getTime(), ending.getTime());
    }

    @Test
    public void testGetAllSlotsWithinABoundary() throws ParseException {
        Timestamp twelve = getTime("2014/10/29 12:00:00");
        Timestamp one = getTime("2014/10/29 13:00:00");
        Timestamp two = getTime("2014/10/29 14:00:00");
        Timestamp three = getTime("2014/10/29 15:00:00");
        Timestamp four = getTime("2014/10/29 16:00:00");
        Timestamp five = getTime("2014/10/29 17:00:00");
        Timestamp six = getTime("2014/10/29 18:00:00");
        Timestamp seven = getTime("2014/10/29 19:00:00");
        Timestamp nine = getTime("2014/10/29 21:00:00");

        HashMap<String, Availability> list = new HashMap<>();
        list.put("twelveOne", new Availability(twelve, one));
        list.put("twelveTwo", new Availability(twelve, two));
        list.put("threeFour", new Availability(three, four));
        list.put("oneTwo", new Availability(one, two));
        list.put("fourFive", new Availability(four, five));
        list.put("fourSix", new Availability(four, six));
        list.put("sevenNine", new Availability(seven, nine));

        // When storing the time in the database it'll be stored differently
        // but when values are returned, it'll be returned the same as how it was inputted
        for (String x : list.keySet()) {
            Availability a = availability.save(list.get(x));
            assertNotNull(a);
            list.put(x, a);
        }

        User a = new User("a");
        User b = new User("b");
        a = user.save(a);
        b = user.save(b);

        UserAvailability ua1 = new UserAvailability(a, list.get("twelveOne"));
        UserAvailability ua2 = new UserAvailability(a, list.get("twelveTwo")); // x
        UserAvailability ua3 = new UserAvailability(a, list.get("oneTwo")); // x
        UserAvailability ua4 = new UserAvailability(b, list.get("oneTwo")); // x
        UserAvailability ua5 = new UserAvailability(b, list.get("fourSix")); // x

        ua1 = userAvailability.save(ua1);
        ua2 = userAvailability.save(ua2);
        ua3 = userAvailability.save(ua3);
        ua4 = userAvailability.save(ua4);
        ua5 = userAvailability.save(ua5);

        HashMap<String, List<Integer>> uaListKeys = new HashMap<>();
        uaListKeys.put(a.getUsername(), new ArrayList<>(List.of(list.get("twelveTwo").getId(), list.get("oneTwo").getId())));
        uaListKeys.put(b.getUsername(), new ArrayList<>(List.of(list.get("oneTwo").getId(), list.get("fourSix").getId())));

        List<UserAvailability> uaList = availability.findByAvailability(one, five);

        for (UserAvailability x : uaList) {
            List<Integer> times = uaListKeys.get(x.getUser().getUsername());
            int index = times.indexOf(x.getAvailability().getId());
            if (index >= 0) {
                uaListKeys.get(x.getUser().getUsername()).remove(index);
            }
        }

        for (String x : uaListKeys.keySet()) {
            if (uaListKeys.get(x).size() > 0) {
                fail();
            }
        }
    }


}
