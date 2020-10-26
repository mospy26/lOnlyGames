package com.lOnlyGames.backend.utilities;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.playerstats.Stat;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;


public class GeneratorImpl implements Generator {


    private List<Stat> stat;

    public GeneratorImpl(String key, String steamID, int appId) throws SteamApiException {
       stat =  APIFetcher.getGameStats(steamID,appId);
    }

    /**
     *
     * @return Kill Death Ratio of a specific player.
     */
    @Override
    public String KDR() {

        DecimalFormat df = new DecimalFormat("#.##");
        //Calculate Ratio
        double ratio = Double.parseDouble(totalKills())/Double.parseDouble(totalDeaths());
        //Format to 2dp
        String ratio2dp = df.format(ratio);
        //Return
    return ratio2dp;
    }

    /**
     *
     * @return Total Hours this user has played.
     */
    @Override
    public String totalHours() {
        Optional<Stat> totalHours = stat.stream().
                filter(p -> p.getName().equals("total_hours")).
                findFirst();
        Double hours = totalHours.get().getValue().doubleValue() / 60 / 60;
        return hours.toString();
    }

    @Override
    public String totalKills() {
        Optional<Stat> totalKills = stat.stream().
                filter(p -> p.getName().equals("total_kills")).
                findFirst();
        return totalKills.get().getValue().toString();
    }

    @Override
    public String totalDeaths() {
        Optional<Stat> totalKills = stat.stream().
                filter(p -> p.getName().equals("total_deaths")).
                findFirst();
        return totalKills.get().getValue().toString();
    }

    @Override
    public List<Stat> getAllStats() {
        return stat;
    }

    @Override
    public String totalWins() {
        Optional<Stat> totalWins = stat.stream().
                filter(p -> p.getName().equals("total_wins")).
                findFirst();
        return  totalWins.get().getValue().toString();

    }
}
