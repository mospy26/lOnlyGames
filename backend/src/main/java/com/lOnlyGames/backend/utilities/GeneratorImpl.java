package com.lOnlyGames.backend.utilities;

import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.utilities.wrappers.*;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

import java.io.IOException;


public class GeneratorImpl{


    private User user;
    public GeneratorImpl(User user){
        this.user = user;
    }
    public String getCSGOStats() throws SteamApiException {return new CSGO(user.getSteamId()).resolveGameStats();}
    public String getTeamFortressTwoStats() throws SteamApiException { return new TeamFortress2(user.getSteamId()).resolveGameStats();}
    public String getCODStats() throws IOException { return new CODMW(user.getBattlenet()).resolveAllGameStats();}
    public String resolveAllRunescapeStats() throws IOException{return new Runescape(user.getRunescapeDisplayName()).resolveAllStats();}
    public String resolveAllPUBGStats() throws IOException {return new PUBG(user.getPubGPlayerName()).resolveAllStats();}


}
