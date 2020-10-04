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

    public String getGameRank() {
        return gameRank;
    }

    public void setGameRank(String gameRank) {
        this.gameRank = gameRank;
    }

    public UserGameCK getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Game getGame() {
        return game;
    }
}
