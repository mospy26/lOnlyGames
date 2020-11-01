package com.lOnlyGames.backend.utilities.cache;

import com.lOnlyGames.backend.interfaces.GameDataCache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CODCache implements GameDataCache {
    StringBuffer content;
    private final String API_KEY = "c8e7025684msh6371be6d590c323p19fe31jsnc680714fd143";

    @Override
    public StringBuffer connect(String userID) throws IOException {
        System.out.println("Added due to github errors");
        return null;
    }
}
