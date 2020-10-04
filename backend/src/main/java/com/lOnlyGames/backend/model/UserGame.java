package com.lOnlyGames.backend.model;

import javax.persistence.*;

@Entity
public class UserGame {
    @EmbeddedId
    private UserGamePK id;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username")
    private User user;
}
