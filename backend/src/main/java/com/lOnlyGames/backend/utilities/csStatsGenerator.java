//package com.lOnlyGames.backend.utilities;
//import com.lukaspradel.steamapi.core.exception.SteamApiException;
//import com.lukaspradel.steamapi.data.json.playerstats.GetUserStatsForGame;
//import com.lukaspradel.steamapi.data.json.playerstats.Playerstats;
//import com.lukaspradel.steamapi.data.json.playerstats.Stat;
//import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
//import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest;
//import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
//
//import java.util.List;
//
///*
//A singleton class designed for shooting synchronous requests to retrieve data, note this object may need to be run on a
//Async task if you want to use it using spring.
//
//For more info on singleton see
//https://www.tutorialspoint.com/design_pattern/singleton_pattern.htm
//
//I decided to use the singleton pattern, since this method calls on the network to retrieve user data.
// */
//public class csStatsGenerator {
//
//    private static csStatsGenerator instance;
//
//    List<Stat> aListOfStats;
//
//    // The webclient used to fetch data
//    private SteamWebApiClient client;
//
//
//
//    //GameIDs for CSGO
//    private final int csgoID = 730;
//
//
//    //Essentially each time the singleton is generated
//    private csStatsGenerator(String id) throws SteamApiException {
//        client = new SteamWebApiClient.SteamWebApiClientBuilder("BD01DFF46072331CAF754AE917231860").build();
//        GetUserStatsForGameRequest request = SteamWebApiRequestFactory.createGetUserStatsForGameRequest(730,id);
//        GetUserStatsForGame gameStats =  client.<GetUserStatsForGame> processRequest(request);
//        Playerstats playerstats = gameStats.getPlayerstats();
//        aListOfStats = playerstats.getStats();
//    }
//
//    public static csStatsGenerator getInstance(String id) throws SteamApiException {
//       if(instance == null)
//       {
//           instance = new csStatsGenerator(id);
//       }
//       return instance;
//    }
//
//    public long getTotalKillsCSGO()
//    { return aListOfStats.stream().filter(e->e.getName().equalsIgnoreCase("total_kills")).findFirst().get().getValue(); }
//
//
//    //Whenever you want to use it to find details of another user just call the release method and reinitialize.
//    public void release()
//    {
//       this.instance = null;
//    }
//
//}
