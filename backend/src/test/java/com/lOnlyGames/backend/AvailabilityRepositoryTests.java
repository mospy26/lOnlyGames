package com.lOnlyGames.backend;

import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserAvailability;
import com.lOnlyGames.backend.repository.AvailabilityRepository;
import com.lOnlyGames.backend.repository.UserAvailabilityRepository;
import com.lOnlyGames.backend.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Test
    public void testAddTimeSlot() throws ParseException {
        int day = 5; // Saturday
        int startTime = 600; // 10am
        int endTime = 630; // 10:30am

        Availability a = new Availability(day, startTime, endTime);
        a = availability.save(a);

        assertNotNull(a);
        assertEquals(a.getDay(), 5);
        assertEquals(a.getTimeStart(), 600);
        assertEquals(a.getTimeEnd(), 630);
    }

    @Test
    public void testMultipleUsersAvailability() throws ParseException {
        User a1 = new User("user1");
        User a2 = new User("user2");
        user.save(a1);
        user.save(a2);

        int day = 5; // Saturday
        int startTime = 600; // 10am
        int endTime = 630; // 10:30am

        Availability a = new Availability(day, startTime, endTime);
        a = availability.save(a);

        UserAvailability ua = new UserAvailability(a1, a);
        ua = userAvailability.save(ua);
        UserAvailability ua2 = new UserAvailability(a2, a);
        ua2 = userAvailability.save(ua2);

        assertNotNull(ua);
        assertEquals(ua.getUser().getUsername(), "user1");
        assertEquals(ua.getAvailability().getDay(), 5);
        assertEquals(ua.getAvailability().getTimeStart(), 600);
        assertEquals(ua.getAvailability().getTimeEnd(), 630);


        assertNotNull(ua2);
        assertEquals(ua2.getUser().getUsername(), "user2");
        assertEquals(ua2.getAvailability().getDay(), 5);
        assertEquals(ua2.getAvailability().getTimeStart(), 600);
        assertEquals(ua2.getAvailability().getTimeEnd(), 630);
    }

    @Test
    public void testFailTimes() {
        boolean dayOOB = false;
        boolean startOOB = false;
        boolean endOOB = false;
        try {
            Availability a = new Availability(1, 1441, 1450);
            a = availability.save(a);
            startOOB = true;
        } catch (TransactionSystemException tse) {
        }
        try {
            Availability a = new Availability(11, 1440, 1440);
            a = availability.save(a);
            dayOOB = true;
        } catch (TransactionSystemException tse) {
        }
        try {
            Availability a = new Availability(1, 1440, 1441);
            a = availability.save(a);
            endOOB = true;
        } catch (TransactionSystemException tse) {
        }

        if (startOOB || dayOOB || endOOB) {
            fail();
        }
    }

    @Test
    public void testGetAllSlotsWithinABoundary() throws ParseException {

        HashMap<String, Availability> list = new HashMap<>();
        list.put("twelveOne", new Availability(1, 720, 780));
        list.put("twelveTwo", new Availability(1, 720, 840));
        list.put("threeFour", new Availability(1, 900, 960));
        list.put("oneTwo", new Availability(1, 780, 840));
        list.put("fourFive", new Availability(1, 960, 1020));
        list.put("fourSix", new Availability(1, 960, 1080));
        list.put("sevenNine", new Availability(1, 1140, 1260));

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


        List<UserAvailability> uaList = availability.findByAvailability(1, 780, 1020);
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
