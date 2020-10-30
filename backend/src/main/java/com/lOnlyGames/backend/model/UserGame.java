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
    public String modernWafareTotalTimePlayed;

    public String getPubgWins() {
        return pubgWins;
    }

    public void setPubgWins(String pubgWins) {
        this.pubgWins = pubgWins;
    }

    public String getPubGTop10s() {
        return pubGTop10s;
    }

    public void setPubGTop10s(String pubGTop10s) {
        this.pubGTop10s = pubGTop10s;
    }

    public String getModernWarfareKDR() {
        return modernWarfareKDR;
    }

    public void setModernWarfareKDR(String modernWarfareKDR) {
        this.modernWarfareKDR = modernWarfareKDR;
    }

    public String getModernWarfareWarZoneWins() {
        return modernWarfareWarZoneWins;
    }

    public void setModernWarfareWarZoneWins(String modernWarfareWarZoneWins) {
        this.modernWarfareWarZoneWins = modernWarfareWarZoneWins;
    }


    public String getModernWafareTotalTimePlayed() {
        return modernWafareTotalTimePlayed;
    }

    public void setModernWafareTotalTimePlayed(String modernWafareTotalTimePlayed) {
        this.modernWafareTotalTimePlayed = modernWafareTotalTimePlayed;
    }

    public String getModernwWarfareTotalWarzoneGames() {
        return modernwWarfareTotalWarzoneGames;
    }

    public void setModernwWarfareTotalWarzoneGames(String modernwWarfareTotalWarzoneGames) {
        this.modernwWarfareTotalWarzoneGames = modernwWarfareTotalWarzoneGames;
    }

    public String getModernWarfareWarzoneKDR() {
        return modernWarfareWarzoneKDR;
    }

    public void setModernWarfareWarzoneKDR(String modernWarfareWarzoneKDR) {
        this.modernWarfareWarzoneKDR = modernWarfareWarzoneKDR;
    }

    public String getRunescapeStats() {
        return runescapeStats;
    }

    public void setRunescapeStats(String runescapeStats) {
        this.runescapeStats = runescapeStats;
    }

    public String getTeamFortress2Kills() {
        return teamFortress2Kills;
    }

    public void setTeamFortress2Kills(String teamFortress2Kills) {
        this.teamFortress2Kills = teamFortress2Kills;
    }

    public double getTeamFortress2TimePlayed() {
        return teamFortress2TimePlayed;
    }

    public void setTeamFortress2TimePlayed(double teamFortress2TimePlayed) {
        this.teamFortress2TimePlayed = teamFortress2TimePlayed;
    }

    public String modernwWarfareTotalWarzoneGames;
    public String modernWarfareWarzoneKDR;
    @Column(length = 3000)
    public String runescapeStats;
    public String teamFortress2Kills;
    public double teamFortress2TimePlayed;



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
