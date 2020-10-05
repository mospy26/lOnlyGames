package com.lOnlyGames.backend.model.CompositeKeys;

import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.User;

import javax.persistence.Embeddable;
import java.io.Serializable;

public class UserAvailabilityCK implements Serializable {

    private User user;
    private Availability availability;

}
