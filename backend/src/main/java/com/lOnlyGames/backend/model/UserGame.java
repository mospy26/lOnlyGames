package com.lOnlyGames.backend.model;

import com.lOnlyGames.backend.model.CompositeKeys.UserGamePK;

import javax.persistence.*;

@Entity
public class UserGame {
    @EmbeddedId
    private UserGamePK id;

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
