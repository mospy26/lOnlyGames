package com.lOnlyGames.backend.model;

import com.lOnlyGames.backend.model.CompositeKeys.UserGameCK;

import javax.persistence.*;

@Entity
@IdClass(UserGameCK.class)
public class UserGame {

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username")
    @Id
    private User user;

    @ManyToOne
    @MapsId("name")
    @JoinColumn(name = "gameName")
    @Id
    private Game game;

    public UserGame() {

    }

    public String gameRank;

    public String getGameRank() {
        return gameRank;
    }

    public void setGameRank(String gameRank) {
        this.gameRank = gameRank;
    }

    public User getUser() {
        return user;
    }

    public Game getGame() { return game; }
}
