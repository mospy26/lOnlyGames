package com.lOnlyGames.backend.model;

import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.lOnlyGames.backend.model.ModelKeys.UserGameKey;

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
