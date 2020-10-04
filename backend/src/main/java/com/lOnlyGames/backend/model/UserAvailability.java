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

}
