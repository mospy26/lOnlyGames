package com.lOnlyGames.backend.utilities;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.playerstats.GetUserStatsForGame;
import com.lukaspradel.steamapi.data.json.playerstats.Playerstats;
import com.lukaspradel.steamapi.data.json.playerstats.Stat;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;

import java.util.List;

/*
A singleton class designed for shooting synchronous requests to retrieve data, note this object may need to be run on a
Async task if you want to use it using spring.

For more info on singleton see
https://www.tutorialspoint.com/design_pattern/singleton_pattern.htm

I decided to use the singleton pattern, since this method calls on the network to retrieve user data.
 */
public class CSGenerator implements Generator {

    private String totalHours;
    private String KDR;


    public CSGenerator() throws SteamApiException {
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder("BD01DFF46072331CAF754AE917231860").build();
        GetUserStatsForGameRequest request = SteamWebApiRequestFactory.createGetUserStatsForGameRequest(730,"76561198068222746");
        GetUserStatsForGame gameStats =  client.<GetUserStatsForGame> processRequest(request);
    }




    @Override
    public String getTotalHours() {
        return null;
    }

    @Override
    public String getKDR() {
        return null;
    }
}
