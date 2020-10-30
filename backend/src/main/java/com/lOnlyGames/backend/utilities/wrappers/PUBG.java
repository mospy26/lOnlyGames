package com.lOnlyGames.backend.utilities.wrappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lOnlyGames.backend.utilities.APIFetcher;
import com.lOnlyGames.backend.utilities.factories.ConcreteGameDataFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

public class PUBG {

    private String playerName;
    private StringBuffer responseContent;
    private final String BEARER_TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiOGIwY2FiMC1mYmI0LTAxMzgtMmQ4ZC0wMDVkYjk1NTg2OTIiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNjAzOTM1MjMwLCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6ImxvbmVseWdhbWVzIn0.TeGbwaCvbBcSReblPNbaxgI-TZLi85kMMi_eqnxlyeo";
    public PUBG(String playerName) throws IOException {
        responseContent = new ConcreteGameDataFactory().create("PUBG").connect(playerName);
        this.playerName = playerName;
    }




    public String resolveWins() throws IOException {
        responseContent = minimumCalls(playerName);
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(responseContent.toString());
        List<JsonNode> duo = node.findValues("duo");
        List<JsonNode> solo = node.findValues("solo");
        List<JsonNode> squad = node.findValues("squad");
        int Duowins = duo.get(0).get("wins").asInt();
        int SoloWins = solo.get(0).get("wins").asInt();
        int squadWins = squad.get(0).get("wins").asInt();
        return "Duo Wins: " + Duowins+ "\n"
                + "Solo Wins: " + SoloWins + "\n"
                +  "Squad Wins: " + squadWins;

    }

    public String resolveTop10s() throws IOException {

        responseContent = minimumCalls(playerName);
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(responseContent.toString());
        List<JsonNode> duo = node.findValues("duo");
        List<JsonNode> solo = node.findValues("solo");
        List<JsonNode> squad = node.findValues("squad");
        int Top10Duos = duo.get(0).get("top10s").asInt();
        int Top10Solo = solo.get(0).get("top10s").asInt();
        int Top10Squads = squad.get(0).get("top10s").asInt();
        return "Solo Top 10 Finishes : " + Top10Solo + "\n"
                + "Squad Top 10 Finishes: " + Top10Squads +"\n"
                + "Duo Top 10 Finishes: " + Top10Duos;
    }

    private StringBuffer minimumCalls(String playerName) throws IOException {
        String id = APIFetcher.resolvePlayerID(playerName).replace("\"", "");
        URL url2 = new URL("https://api.pubg.com/shards/steam/players/"+id+"/seasons/lifetime?filter[gamepad]=false");
        HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization",BEARER_TOKEN);
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
        return responseContent;
    }



}
