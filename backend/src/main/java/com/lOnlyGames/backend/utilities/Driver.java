package com.lOnlyGames.backend.utilities;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.ownedgames.Game;
import com.lukaspradel.steamapi.data.json.playerstats.GetUserStatsForGame;
import com.lukaspradel.steamapi.data.json.playerstats.Playerstats;
import com.lukaspradel.steamapi.data.json.playerstats.Stat;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Driver {

    public static void main(String args[]) throws SteamApiException {
//        steamFacade steamFacade = new steamFacade();
//        steamFacade.test();



        List<Stat> stats = APIFetcher.getGameStats("76561198068222746",730);

        GeneratorImpl generator = new GeneratorImpl("BD01DFF46072331CAF754AE917231860","76561198068222746",730);

        List<Game> games = APIFetcher.getGamesPlayed("76561198882328170");

        for(int i = 0; i<games.size(); ++i)
        {
            System.out.println(games.get(i).getName());
        }







    }
}
