package com.lOnlyGames.backend.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import retrofit2.http.Url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;


public class APIFetcher {
    private final static String API_KEY = "BD01DFF46072331CAF754AE917231860";
    private final static String PUBG_API_KEY ="";


    public static List<Stat> getGameStats(String steamID,int appID) throws SteamApiException {
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(API_KEY).build();
        GetUserStatsForGameRequest request = SteamWebApiRequestFactory.createGetUserStatsForGameRequest(appID,steamID);
        GetUserStatsForGame gameStats =  client.<GetUserStatsForGame> processRequest(request);
        Playerstats playerstats = gameStats.getPlayerstats();
        List<Stat> stats = playerstats.getStats();
        return stats;
    }

    public static List<JsonNode> getGamesPlayed(String steamID) throws IOException {
        URL url = new URL("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=" +API_KEY+ "&steamid="+steamID+"&format=json&include_appinfo");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        while((line = reader.readLine()) != null)
        {
            responseContent.append(line);
        }
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(responseContent.toString());
        List<JsonNode> games = node.findValues("appid");

        reader.close();
        return games;

    }

    public static String resolvePlayerID(String playerName) throws IOException {
        URL url = new URL("https://api.pubg.com/shards/steam/players?filter[playerNames]="+playerName);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiOGIwY2FiMC1mYmI0LTAxMzgtMmQ4ZC0wMDVkYjk1NTg2OTIiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNjAzOTM1MjMwLCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6ImxvbmVseWdhbWVzIn0.TeGbwaCvbBcSReblPNbaxgI-TZLi85kMMi_eqnxlyeo");
        conn.setRequestProperty("Accept", "application/vnd.api+json");
        conn.connect();
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while((line = reader.readLine()) != null)
        {
            responseContent.append(line);
        }
        reader.close();
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(responseContent.toString());
        List<JsonNode> games = node.findValues("id");
        return games.get(0).toString();

    }


    public static int getTotalWins(String playerName) throws IOException {
        String id = APIFetcher.resolvePlayerID(playerName).replace("\"", "");

        System.out.println(id);
        URL url2 = new URL("https://api.pubg.com/shards/steam/players/"+id+"/seasons/lifetime?filter[gamepad]=false");
        HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiOGIwY2FiMC1mYmI0LTAxMzgtMmQ4ZC0wMDVkYjk1NTg2OTIiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNjAzOTM1MjMwLCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6ImxvbmVseWdhbWVzIn0.TeGbwaCvbBcSReblPNbaxgI-TZLi85kMMi_eqnxlyeo");
        conn.setRequestProperty("Accept", "application/vnd.api+json");
        conn.connect();
        int code = conn.getResponseCode();
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while((line = reader.readLine()) != null)
        {
            responseContent.append(line);
        }

        reader.close();
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(responseContent.toString());
        List<JsonNode> duo = node.findValues("duo");

        int wins = 0;


        return wins;

    }

}
