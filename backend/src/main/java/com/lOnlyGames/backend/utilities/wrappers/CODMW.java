package com.lOnlyGames.backend.utilities.wrappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lOnlyGames.backend.utilities.factories.CacheFactory;
import com.lOnlyGames.backend.utilities.factories.ConcreteGameDataFactory;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.List;

public class CODMW {

    private String userID;
    private StringBuffer content;
    private CacheFactory cacheFactory;

    public CODMW(String userID) throws IOException {
        this.userID = userID;
        cacheFactory = new ConcreteGameDataFactory();
        content = cacheFactory.create("COD").connect(userID);

    }


    public  String resolveKDRWarzone() throws IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(content.toString());
        List<JsonNode> games = node.findValues("br");
        double a = games.get(0).get("properties").get("kdRatio").asDouble();
        double finalVal = Math.round(a * 100.0) / 100.0;
        return Double.toString(finalVal);
    }

    public String resolveMultiplayerKDR() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(content.toString());
        List<JsonNode> games = node.findValues("lifetime");
        return Double.toString(games.get(0).get("all").get("properties").get("kdRatio").asDouble());
    }

    public String resolveTotalTimePlayer() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(content.toString());
        List<JsonNode> games = node.findValues("lifetime");
        // THIS IS THE TOTAL TIME PLAYED IN HOURS ONLY.
        return Double.toString(games.get(0).get("all").get("properties").get("timePlayedTotal").asDouble() /60/60);
    }

    public String resolveTotalWarzoneGames() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(content.toString());
        List<JsonNode> games = node.findValues("br");

        // Total games played in warzone
        return Double.toString(games.get(0).get("properties").get("gamesPlayed").asDouble());
    }

    public String resolveTotalWarzoneWins() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(content.toString());
        List<JsonNode> games = node.findValues("br");
        // Total games played in warzone
        return Double.toString(games.get(0).get("properties").get("wins").asDouble());
    }

}
