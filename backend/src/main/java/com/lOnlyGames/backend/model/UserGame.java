package com.lOnlyGames.backend.model;

import com.lOnlyGames.backend.model.CompositeKeys.UserGameCK;

import javax.persistence.*;

@Entity
public class UserGame {
    @EmbeddedId
    private UserGameCK id;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @MapsId("name")
    @JoinColumn(name = "gameName")
    private Game game;

    public String gameRank;
}
