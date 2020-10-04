package com.lOnlyGames.backend.model;

import javax.persistence.*;

import com.lOnlyGames.backend.model.ModelKeys.UserGameKey;

@Entity
public class UserGames {
    @EmbeddedId
    private UserGameKey id;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name="username")
    private User user;

    @ManyToOne
    @MapsId("gamesId")
    @JoinColumn(name="id")
    private Games game;

    private String rank;
}
