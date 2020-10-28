package com.lOnlyGames.backend.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.playerstats.Stat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Driver {

    public static void main(String args[]) throws SteamApiException, IOException {
//        steamFacade steamFacade = new steamFacade();
//        steamFacade.test();



        List<Stat> stats = APIFetcher.getGameStats("76561198068222746",730);

        GeneratorImpl generator = new GeneratorImpl("BD01DFF46072331CAF754AE917231860","76561198882328170",730);

      System.out.println(APIFetcher.getGamesPlayed("76561198882328170").size());






    }
}
