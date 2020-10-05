package com.lOnlyGames.backend.repository;

import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.CompositeKeys.UserAvailabilityCK;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserAvailability;
import org.springframework.data.repository.CrudRepository;

public interface UserAvailabilityRepository extends CrudRepository<UserAvailability, UserAvailabilityCK> {
    public UserAvailability findByUser(User user);
    public UserAvailability findByAvailability(Availability availability);
}
