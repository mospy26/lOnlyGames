package com.lOnlyGames.backend.utilities.factories;

import com.lOnlyGames.backend.interfaces.GameDataCache;
import com.lOnlyGames.backend.utilities.cache.CODCache;
import com.lOnlyGames.backend.utilities.cache.PUBGCache;
import com.lOnlyGames.backend.utilities.cache.SteamCache;

public class ConcreteGameDataFactory implements CacheFactory {
    @Override
    public GameDataCache create(String type) {

        switch (type) {
            case "COD":
                return new CODCache();
            case "PUBG":
                return new PUBGCache();
            case "Steam":
                return new SteamCache();


        }
        return null;
    }

}
