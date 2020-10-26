package com.lOnlyGames.backend.utilities;

import com.lukaspradel.steamapi.data.json.playerstats.Stat;

import java.util.List;

public interface Generator {
    public String KDR();
    public String totalHours();
    public String totalKills();
    public String totalDeaths();
    public List<Stat> getAllStats();
    public String totalWins();

}
