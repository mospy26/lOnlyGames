package com.lOnlyGames.backend.utilities.cache;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lOnlyGames.backend.interfaces.GameDataCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class PUBGCache implements GameDataCache {

    private final String BEARER_TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiOGIwY2FiMC1mYmI0LTAxMzgtMmQ4ZC0wMDVkYjk1NTg2OTIiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNjAzOTM1MjMwLCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6ImxvbmVseWdhbWVzIn0.TeGbwaCvbBcSReblPNbaxgI-TZLi85kMMi_eqnxlyeo";


    @Override
    public StringBuffer connect(String userID) throws IOException {
        URL url = new URL("https://api.pubg.com/shards/steam/players?filter[playerNames]="+userID);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
