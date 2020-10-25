package com.lOnlyGames.backend.model.CompositeKeys;

import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserAvailability;

import java.io.Serializable;

public class UserAvailabilityCK implements Serializable {

    private String user; // Must have the same name AND same PK type as the connected entity
    private Integer availability; // Must have the same name AND same PK type as the connected entity

    public UserAvailabilityCK() {}
    public UserAvailabilityCK(String user, Integer availability) {
        this.user = user;
        this.availability = availability;
    }

}
