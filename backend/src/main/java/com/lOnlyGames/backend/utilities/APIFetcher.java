package com.lOnlyGames.backend.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lOnlyGames.backend.utilities.factories.ConcreteGameDataFactory;
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
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
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



    public static List<Stat> getGameStats(String steamID,int appID) throws SteamApiException {
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(API_KEY).build();
        GetUserStatsForGameRequest request = SteamWebApiRequestFactory.createGetUserStatsForGameRequest(appID,steamID);
        GetUserStatsForGame gameStats =  client.<GetUserStatsForGame> processRequest(request);
        Playerstats playerstats = gameStats.getPlayerstats();
        List<Stat> stats = playerstats.getStats();
        return stats;
    }

    public static List<JsonNode> getGamesPlayed(String steamID) throws IOException {
        StringBuffer responseContent = new ConcreteGameDataFactory().create("Steam").connect(steamID);
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(responseContent.toString());
        List<JsonNode> games = node.findValues("appid");
        return games;
    }

    public static String resolvePlayerID(String playerName) throws IOException {
        StringBuffer responseContent = new ConcreteGameDataFactory().create("PUBG").connect(playerName);
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(responseContent.toString());
        List<JsonNode> id = node.findValues("id");
        return id.get(0).toString();

    }













}
