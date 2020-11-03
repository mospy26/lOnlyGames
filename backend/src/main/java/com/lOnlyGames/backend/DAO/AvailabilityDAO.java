package com.lOnlyGames.backend.DAO;

import com.lOnlyGames.backend.repository.AvailabilityRepository;
import com.lOnlyGames.backend.repository.UserAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityDAO {

    @Autowired
    private AvailabilityRepository availabilityRepository;
    @Autowired
    private UserAvailabilityRepository userAvailabilityRepository;

    public AvailabilityRepository getAvailabilityRepository() {
        return availabilityRepository;
    }

    public UserAvailabilityRepository getUserAvailabilityRepository() {
        return userAvailabilityRepository;
    }
}
