package com.lOnlyGames.backend.DAO;

import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.UserAvailability;
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

    public void addAvailability(Availability availability) {
        availabilityRepository.save(availability);
    }

    public void deleteAvailability(Availability availability) {
        availabilityRepository.delete(availability);
    }

    public void addUserAvailability(UserAvailability userAvailability) {
        userAvailabilityRepository.save(userAvailability);
    }

    public void deleteUserAvailability(UserAvailability userAvailability) {
        userAvailabilityRepository.delete(userAvailability);
    }
}
