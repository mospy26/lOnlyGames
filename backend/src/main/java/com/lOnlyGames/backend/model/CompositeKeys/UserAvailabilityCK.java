package com.lOnlyGames.backend.model.CompositeKeys;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserAvailabilityCK implements Serializable {

    private String username;
    private Integer availabilityId;

}
