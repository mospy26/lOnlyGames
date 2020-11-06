package com.lOnlyGames.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lOnlyGames.backend.model.CompositeKeys.UserAvailabilityCK;

import javax.persistence.*;

@Entity
@IdClass(UserAvailabilityCK.class)
public class UserAvailability {

    //@JsonManagedReference
    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username")
    @Id
    private User user;

    //@JsonManagedReference
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass()!= this.getClass()) return false;

        UserAvailability other = (UserAvailability) obj;

        if (this.getUser().getUsername().equals(other.getUser().getUsername()) &&
                this.getAvailability().getId().equals(other.getAvailability().getId())) {
            return true;
        }

        return false;

    }
}
