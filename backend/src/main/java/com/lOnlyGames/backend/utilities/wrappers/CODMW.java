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
    private final String API_KEY = "c8e7025684msh6371be6d590c323p19fe31jsnc680714fd143";

    public CODMW(String userID) throws IOException {
        this.userID = userID;
        String[] uniqueIdentifier = userID.split("#");
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://call-of-duty-modern-warfare.p.rapidapi.com/multiplayer/"+  uniqueIdentifier[0] +  "%2523"+uniqueIdentifier[1]+ "/battle")
                .get()
                .addHeader("x-rapidapi-host", "call-of-duty-modern-warfare.p.rapidapi.com")
                .addHeader("x-rapidapi-key", API_KEY)
                .build();

        Response response = client.newCall(request).execute();
        BufferedReader read;
        String Line;
        content = new StringBuffer();
        read = new BufferedReader(new InputStreamReader(response.body().byteStream()));
        while((Line = read.readLine()) != null)
        {
            content.append(Line);
        }


    }


    public  String resolveKDRWarzone() throws IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(content.toString());
        System.out.println("The content is ");
        List<JsonNode> games = node.findValues("br");
        System.out.println("The size of games is " + games.size());
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
