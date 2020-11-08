package com.lOnlyGames.backend.DAOTests;

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
public class UserDAOTests {

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

}


