package com.lOnlyGames.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    public UserGame() { }
    public UserGame(User user, Game game) {
        this.user = user;
        this.game = game;
    }

    public String getStatistics() {
        return statistics;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }


   //  @Column(length = 1000)  THis doesnt update our database I  had to change it manually using a SQL script
    public String statistics;

    public User getUser() {
        return user;
    }

    public Game getGame() { return game; }
}
