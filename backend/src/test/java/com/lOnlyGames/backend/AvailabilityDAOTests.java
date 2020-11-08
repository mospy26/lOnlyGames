package com.lOnlyGames.backend;

import com.lOnlyGames.backend.DAO.AvailabilityDAO;
import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.CompositeKeys.UserAvailabilityCK;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserAvailability;
import com.lOnlyGames.backend.repository.AvailabilityRepository;
import com.lOnlyGames.backend.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AvailabilityDAOTests {

    @Autowired
    private AvailabilityDAO availabilityDAO;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @Rollback
    public void testAddAvailability() {
        Availability availability = new Availability(5, 10, 20);
        availabilityDAO.addAvailability(availability);

        assertEquals(availability.getDay(), availabilityDAO.getAvailabilityRepository().findById(availability.getId()).get().getDay());
    }

    @Test
    @Transactional
    @Rollback
    public void testAddUserAvailability() {
        User user = new User("testAvUser");
        Availability availability = new Availability(0, 10, 20);

        UserAvailability userAvailability = new UserAvailability(user, availability);

        availabilityRepository.save(availability);
        userRepository.save(user);


        availabilityDAO.addUserAvailability(userAvailability);

        assertEquals(userAvailability.getAvailability().getId(), availabilityDAO.getUserAvailabilityRepository().findByAvailability(availability).getAvailability().getId());
        assertEquals(userAvailability.getUser().getUsername(), availabilityDAO.getUserAvailabilityRepository().findByUser(user).get(0).getUser().getUsername());

    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveAvailability() {
        Availability availability = new Availability(5, 10, 20);
        availabilityDAO.addAvailability(availability);

        assertEquals(availability.getDay(), availabilityDAO.getAvailabilityRepository().findById(availability.getId()).get().getDay());

        availabilityDAO.deleteAvailability(availability);

        assertNull(availabilityDAO.getAvailability(availability.getId()));

    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveUserAvailability() {
        User user = new User("testAvUser");
        Availability availability = new Availability(0, 10, 20);

        UserAvailability userAvailability = new UserAvailability(user, availability);

        availabilityRepository.save(availability);
        userRepository.save(user);

        availabilityDAO.addUserAvailability(userAvailability);

        assertEquals(userAvailability.getAvailability().getId(), availabilityDAO.getUserAvailabilityRepository().findByAvailability(availability).getAvailability().getId());
        assertEquals(userAvailability.getUser().getUsername(), availabilityDAO.getUserAvailabilityRepository().findByUser(user).get(0).getUser().getUsername());

        availabilityDAO.deleteUserAvailability(userAvailability);

        UserAvailabilityCK compositeKey = new UserAvailabilityCK(user.getUsername(), availability.getId());
        assertNull(availabilityDAO.getUserAvailability(compositeKey));
    }
}


