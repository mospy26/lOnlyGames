package com.lOnlyGames.backend.utilities;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.ownedgames.Game;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.data.json.playerstats.GetUserStatsForGame;
import com.lukaspradel.steamapi.data.json.playerstats.Playerstats;
import com.lukaspradel.steamapi.data.json.playerstats.Stat;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
import java.util.List;


public class APIFetcher {
    private final static String API_KEY = "BD01DFF46072331CAF754AE917231860";


    public static List<Stat> getGameStats(String steamID,int appID) throws SteamApiException {
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(API_KEY).build();
        GetUserStatsForGameRequest request = SteamWebApiRequestFactory.createGetUserStatsForGameRequest(appID,steamID);
        GetUserStatsForGame gameStats =  client.<GetUserStatsForGame> processRequest(request);
        Playerstats playerstats = gameStats.getPlayerstats();
        List<Stat> stats = playerstats.getStats();
        return stats;
    }

    public static List<Game> getGamesPlayed(String steamID) throws SteamApiException {
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(API_KEY).build();
        GetOwnedGamesRequest request = SteamWebApiRequestFactory.createGetOwnedGamesRequest(steamID);
        GetOwnedGames gamesOwned = client.<GetOwnedGames> processRequest(request);
        List<Game> games = gamesOwned.getResponse().getGames();
        return games;
    }
}
