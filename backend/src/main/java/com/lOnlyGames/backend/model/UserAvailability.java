package com.lOnlyGames.backend.model;

import com.lOnlyGames.backend.model.CompositeKeys.UserAvailabilityCK;

import javax.persistence.*;

@Entity
public class UserAvailability {
    @EmbeddedId
    private UserAvailabilityCK id;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "availabilityId")
    private Availability availability;

    public UserAvailabilityCK getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Availability getAvailability() {
        return availability;
    }

}
