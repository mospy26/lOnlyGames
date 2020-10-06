package com.lOnlyGames.backend.model;

import com.lOnlyGames.backend.model.CompositeKeys.UserAvailabilityCK;

import javax.persistence.*;

@Entity
@IdClass(UserAvailabilityCK.class)
public class UserAvailability {

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username")
    @Id
    private User user;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "availabilityId")
    @Id
    private Availability availability;

    public UserAvailability() {

    }

    public UserAvailability(User user, Availability availability) {
        this.user = user;
        this.availability = availability;
    }

    public User getUser() {
        return user;
    }

    public Availability getAvailability() {
        return availability;
    }

}
