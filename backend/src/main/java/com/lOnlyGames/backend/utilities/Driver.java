//package com.lOnlyGames.backend.utilities;
//
//import com.lukaspradel.steamapi.core.exception.SteamApiException;
//import com.lukaspradel.steamapi.data.json.playerstats.GetUserStatsForGame;
//import com.lukaspradel.steamapi.data.json.playerstats.Playerstats;
//import com.lukaspradel.steamapi.data.json.playerstats.Stat;
//import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
//import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest;
//import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
//
//import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;
//
//public class Driver {
//
//    public static void main(String args[]) throws SteamApiException {
////        steamFacade steamFacade = new steamFacade();
////        steamFacade.test();
//
//
//        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder("BD01DFF46072331CAF754AE917231860").build();
//        GetUserStatsForGameRequest request = SteamWebApiRequestFactory.createGetUserStatsForGameRequest(730,"76561198068222746");
//        GetUserStatsForGame gameStats =  client.<GetUserStatsForGame> processRequest(request);
//        Playerstats playerstats = gameStats.getPlayerstats();
//        List<Stat> stats = playerstats.getStats();
//
//
//        GetUserStatsForGameRequest request2 = SteamWebApiRequestFactory.createGetUserStatsForGameRequest(730,"76561198882328170");
//        GetUserStatsForGame gamestats2 =  client.<GetUserStatsForGame> processRequest(request);
//        Playerstats playerstats2 = gameStats.getPlayerstats();
//        List<Stat> stats2 = playerstats.getStats();
//
//
//
//        for(int i = 0; i<stats.size(); ++i)
//        {
//
//            if(stats.get(i).getName().equalsIgnoreCase("total_kills"))
//            {
//                System.out.println(stats.get(i).getValue());
//            }
//        }
//
//
//
//
//
//
//    }
//}
