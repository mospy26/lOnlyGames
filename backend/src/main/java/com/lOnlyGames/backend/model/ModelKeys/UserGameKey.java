package com.lOnlyGames.backend.model.ModelKeys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// Refer to https://www.baeldung.com/jpa-many-to-many section 3.3
@Embeddable
public class UserGameKey implements Serializable {

    @Column(name="id")
    private int gamesId;

    @Column(name="username")
    private String username; 
}