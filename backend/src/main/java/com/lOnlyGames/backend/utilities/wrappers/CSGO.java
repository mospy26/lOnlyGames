package com.lOnlyGames.backend.utilities.wrappers;

import com.lOnlyGames.backend.utilities.APIFetcher;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.playerstats.Stat;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

public class CSGO {
    private List<Stat> stat;
    private final int APP_ID = 730;

    public CSGO(String steamID) throws SteamApiException {
        stat =  APIFetcher.getGameStats(steamID,APP_ID);
    }

    public String resolveKDR() {

        DecimalFormat df = new DecimalFormat("#.##");
        //Calculate Ratio
        double ratio = Double.parseDouble(resolvetotalKills())/Double.parseDouble(resolveTotalDeaths());
        //Format to 2dp
        String ratio2dp = df.format(ratio);
        //Return
        return ratio2dp;
    }

    public String resolveTotalHours() {
        Optional<Stat> totalHours = stat.stream().
                filter(p -> p.getName().equals("total_time_played")).
                findFirst();

        Double hours = totalHours.get().getValue().doubleValue() / 60 / 60;
        return hours.toString();
    }
    public String resolvetotalKills() {
        Optional<Stat> totalKills = stat.stream().
                filter(p -> p.getName().equals("total_kills")).
                findFirst();
        return totalKills.get().getValue().toString();
    }

    public String resolveTotalDeaths() {
        Optional<Stat> totalKills = stat.stream().
                filter(p -> p.getName().equals("total_deaths")).
                findFirst();
        return totalKills.get().getValue().toString();
    }


    public List<Stat> resolveAllStats() {
        return stat;
    }

    public String resolveTotalWins() {
        Optional<Stat> totalWins = stat.stream().
                filter(p -> p.getName().equals("total_wins")).
                findFirst();
        return  totalWins.get().getValue().toString();

    }
}
