package com.lOnlyGames.backend.utilities.cache;

import com.lOnlyGames.backend.interfaces.GameDataCache;
import com.lOnlyGames.backend.utilities.Util;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CODCache implements GameDataCache {
    StringBuffer content;
    private final String API_KEY = Util.fetchProperties().getProperty("mw_api_secret");

    @Override
    public StringBuffer connect(String userID) throws IOException {

        String[] uniqueIdentifier = userID.split("#");

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://call-of-duty-modern-warfare.p.rapidapi.com/multiplayer/"+  uniqueIdentifier[0] +  "%2523"+uniqueIdentifier[1]+ "/battle")
                .get()
                .addHeader("x-rapidapi-host", "call-of-duty-modern-warfare.p.rapidapi.com")
                .addHeader("x-rapidapi-key", API_KEY)
                .build();

        System.out.println("MY SINGLE API CALL OKAY");



        Response response = client.newCall(request).execute();
        System.out.println("MY REQUEST CODE IS " + response.code());
        BufferedReader read;
        String Line;
        content = new StringBuffer();
        read = new BufferedReader(new InputStreamReader(response.body().byteStream()));
        while((Line = read.readLine()) != null)
        {
            content.append(Line);
        }

        return content;
    }
}
