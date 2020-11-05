package com.lOnlyGames.backend.utilities.factories;

import com.lOnlyGames.backend.interfaces.GameDataCache;

public interface CacheFactory {
    public GameDataCache create(String type);
}
