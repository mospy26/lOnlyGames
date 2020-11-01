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

public class SteamCache implements GameDataCache {

    private final String API_KEY = "BD01DFF46072331CAF754AE917231860";


    @Override
    public StringBuffer connect(String userID) throws IOException {
        URL url = new URL("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=" +API_KEY+ "&steamid="+userID+"&format=json&include_appinfo");
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

        System.out.println("Added due to github errors");

        return responseContent;
    }
}
