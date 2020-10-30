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

    public String gameRank;
    public String Kdr;
    public String totalKills;
    public String totalHoursPlayed;
    public String totalDeaths;
    public String pubgWins;
    public String pubGTop10s;
    public String modernWarfareKDR;
    public String modernWarfareWarZoneWins;


    public String getKdr() {
        return Kdr;
    }

    public void setKdr(String kdr) {
        Kdr = kdr;
    }

    public String getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(String totalKills) {
        this.totalKills = totalKills;
    }

    public String getTotalHoursPlayed() {
        return totalHoursPlayed;
    }

    public void setTotalHoursPlayed(String totalHoursPlayed) {
        this.totalHoursPlayed = totalHoursPlayed;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }



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
