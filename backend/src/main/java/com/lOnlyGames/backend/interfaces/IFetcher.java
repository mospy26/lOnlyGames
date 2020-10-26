package com.lOnlyGames.backend.interfaces;

import java.util.List;

public interface IFetcher {
    public String getSteamName();
    public List<String> getAllGamesPlayed();
    public String getWarzoneID();
}
