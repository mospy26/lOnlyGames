package com.lOnlyGames.backend.utilities;

import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.utilities.wrappers.*;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


public class GeneratorImpl{


private User user;
    public GeneratorImpl(User user){
        this.user = user;
    }

   //CSGO
    public String csgoKDR() throws SteamApiException {return new CSGO(user.getSteamId()).resolveKDR(); }

    public String csgoTotalHours() throws SteamApiException { return new CSGO(user.getSteamId()).resolveTotalHours(); }

    public String csgoTotalKills() throws SteamApiException { return new CSGO(user.getSteamId()).resolvetotalKills(); }

    public String csgoTotalDeaths() throws SteamApiException { return new CSGO(user.getSteamId()).resolveTotalDeaths(); }


    public String csgoTotalWins() throws SteamApiException {return new CSGO(user.getSteamId()).resolveTotalWins();}

   //TF2

    public String TeamFortressTwoKills() throws SteamApiException { return new TeamFortress2(user.getSteamId()).resolveTF2Kills(); }

    public double totalTeamFortressHours() throws SteamApiException { return new TeamFortress2(user.getSteamId()).resolvePlayTime(); }


    //WARZONE
    public String getWarzoneKDR() throws IOException{ return new CODMW(user.getBattlenet()).resolveKDRWarzone(); }

    public String getWarzoneWins() throws IOException{ return new CODMW(user.getBattlenet()).resolveTotalWarzoneWins(); }

    public String getMWTotalTimePlayed() throws IOException{ return new CODMW(user.getBattlenet()).resolveTotalTimePlayer(); }

    public String getMWMultiplayerKDR() throws IOException{ return new CODMW(user.getBattlenet()).resolveMultiplayerKDR(); }

    public String getMWtotalWarzoneGame() throws IOException {return new CODMW(user.getBattlenet()).resolveTotalWarzoneGames();}


    //PUBG
    public String getPUBWins() throws IOException { return new PUBG(user.getPubGPlayerName()).resolveWins();}
    public String getPUBTop10s() throws IOException { return new PUBG(user.getPubGPlayerName()).resolveTop10s();}


    //Runescape
    public String resolveAllRunescapeStats() throws IOException{return new Runescape(user.getRunescapeDisplayName()).resolveAllStats();}



}
