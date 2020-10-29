package com.lOnlyGames.backend.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.playerstats.Stat;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;


public class GeneratorImpl implements Generator {


    private List<Stat> stat;

    public GeneratorImpl(String steamID, int appId) throws SteamApiException {
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
                filter(p -> p.getName().equals("total_time_played")).
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



    public String TeamFortressTwoKills(String steamID) throws SteamApiException {
        List<Stat> stats =  APIFetcher.getGameStats(steamID,440);
        String totalKills = "";
        for(int i = 0; i<stats.size(); ++i) {
            if(stats.get(i).getName().equals("Scout.accum.iNumberOfKills"))
            {
                totalKills += "Scout Total Kills " + stats.get(i).getValue() + "\n";
            }
            if(stats.get(i).getName().equals("Soldier.accum.iNumberOfKills"))
            {
                totalKills += "Soldier Total Kills: " + stats.get(i).getValue()+"\n";
            }
            if(stats.get(i).getName().equals("Spy.accum.iNumberOfKills"))
            {
                totalKills += "Spy Total Kills: " + stats.get(i).getValue()+"\n";
            }
            if(stats.get(i).getName().equals("Pyro.accum.iNumberOfKills"))
            {
                totalKills += "Pyro Total Kills: " + stats.get(i).getValue()+"\n";
            }
            if(stats.get(i).getName().equals("Medic.accum.iNumberOfKills"))
            {
                totalKills += "Medic Total Kills: " + stats.get(i).getValue()+"\n";
            }
            if(stats.get(i).getName().equals("Demoman.accum.iNumberOfKills"))
            {
                totalKills += "Demolition Total Kills: " + stats.get(i).getValue()+"\n";
            }
            if(stats.get(i).getName().equals("Heavy.accum.iNumberOfKills"))
            {
                totalKills += "Heavy Total Kills: " + stats.get(i).getValue()+"\n";
            }
            if(stats.get(i).getName().equals("Engineer.accum.iNumberOfKills"))
            {
                totalKills += "Engineer Total Kills: " + stats.get(i).getValue()+"\n";
            }
            if(stats.get(i).getName().equals("Sniper.accum.iNumberOfKills"))
            {
                totalKills += "Sniper Total Kills: " + stats.get(i).getValue()+"\n";
            }
        }

        return totalKills;

    }

    public double totalTeamFortressHours(String steamID) throws SteamApiException {
        double totalPlaytime = 0;

        List<Stat> stats = APIFetcher.getGameStats(steamID,440);

        for (int i = 0; i < stats.size(); ++i) {
            if (stats.get(i).getName().equals("Scout.accum.iPlayTime")) {
                totalPlaytime += stats.get(i).getValue().doubleValue();
                if (stats.get(i).getName().equals("Soldier.accum.iPlayTime")) {
                    totalPlaytime += stats.get(i).getValue().doubleValue();
                }
                if (stats.get(i).getName().equals("Spy.accum.iPlayTime")) {
                    totalPlaytime += stats.get(i).getValue().doubleValue();
                }
                if (stats.get(i).getName().equals("Pyro.accum.iPlayTime")) {
                    totalPlaytime += stats.get(i).getValue().doubleValue();
                }
                if (stats.get(i).getName().equals("Medic.accum.iPlayTime")) {
                    totalPlaytime += stats.get(i).getValue().doubleValue();
                }
                if (stats.get(i).getName().equals("Demoman.accum.iPlayTime")) {
                    totalPlaytime += stats.get(i).getValue().doubleValue();
                }
                if (stats.get(i).getName().equals("Heavy.accum.iPlayTime")) {
                    totalPlaytime += stats.get(i).getValue().doubleValue();
                }
                if (stats.get(i).getName().equals("Engineer.accum.iPlayTime")) {
                    totalPlaytime += stats.get(i).getValue().doubleValue();
                }
                if (stats.get(i).getName().equals("Sniper.accum.iPlayTime")) {
                    totalPlaytime += stats.get(i).getValue().doubleValue();
                }
            }

        }

        return totalPlaytime/60/60;
    }




    public static String getKDRWarzone(String battleNetID) throws IOException {

        String[] uniqueIdentifier = battleNetID.split("#");
        System.out.println(uniqueIdentifier.length);

        System.out.println("The val is" +  uniqueIdentifier[1]);


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://call-of-duty-modern-warfare.p.rapidapi.com/multiplayer/"+  uniqueIdentifier[0] +  "%2523"+uniqueIdentifier[1]+ "/battle")
                .get()
                .addHeader("x-rapidapi-host", "call-of-duty-modern-warfare.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "c8e7025684msh6371be6d590c323p19fe31jsnc680714fd143")
                .build();

        Response response = client.newCall(request).execute();
        BufferedReader read;
        String Line;
        StringBuffer content = new StringBuffer();
        read = new BufferedReader(new InputStreamReader(response.body().byteStream()));
        while((Line = read.readLine()) != null)
        {
            content.append(Line);
        }
        read.close();
        System.out.println(content);

        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(content.toString());
        List<JsonNode> games = node.findValues("br");

        // THIS IS THE KDR OF USER IN WARZONE ONLY
       return Double.toString(games.get(0).get("properties").get("kdRatio").asDouble());
    }





    public String getKDRMultiplayer(String battleNetID) throws IOException {
        String[] uniqueIdentifier = battleNetID.split("#");
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://call-of-duty-modern-warfare.p.rapidapi.com/multiplayer/"+  uniqueIdentifier[0] +  "%2523"+uniqueIdentifier[1]+ "/battle")
                .get()
                .addHeader("x-rapidapi-host", "call-of-duty-modern-warfare.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "c8e7025684msh6371be6d590c323p19fe31jsnc680714fd143")
                .build();

        Response response = client.newCall(request).execute();
        BufferedReader read;
        String Line;
        StringBuffer content = new StringBuffer();
        read = new BufferedReader(new InputStreamReader(response.body().byteStream()));
        while((Line = read.readLine()) != null)
        {
            content.append(Line);
        }
        read.close();
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(content.toString());
        List<JsonNode> games = node.findValues("lifetime");

        // THIS IS THE TOTAL TIME PLAYED IN HOURS ONLY.
        return Double.toString(games.get(0).get("all").get("properties").get("kdRatio").asDouble());
    }



    public static String getTimePlayedMultiplayerMW(String battleNetID) throws IOException {
        String[] uniqueIdentifier = battleNetID.split("#");
        System.out.println(uniqueIdentifier.length);

        System.out.println("The val is" +  uniqueIdentifier[1]);


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://call-of-duty-modern-warfare.p.rapidapi.com/multiplayer/"+  uniqueIdentifier[0] +  "%2523"+uniqueIdentifier[1]+ "/battle")
                .get()
                .addHeader("x-rapidapi-host", "call-of-duty-modern-warfare.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "c8e7025684msh6371be6d590c323p19fe31jsnc680714fd143")
                .build();

        Response response = client.newCall(request).execute();
        BufferedReader read;
        String Line;
        StringBuffer content = new StringBuffer();
        read = new BufferedReader(new InputStreamReader(response.body().byteStream()));
        while((Line = read.readLine()) != null)
        {
            content.append(Line);
        }
        read.close();
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(content.toString());
        List<JsonNode> games = node.findValues("lifetime");

        // THIS IS THE TOTAL TIME PLAYED IN HOURS ONLY.
        return Double.toString(games.get(0).get("all").get("properties").get("timePlayedTotal").asDouble() /60/60);
    }

    public static String getWarzoneWins(String BattleNetID) throws IOException {

        String[] uniqueIdentifier = BattleNetID.split("#");
        System.out.println(uniqueIdentifier.length);

        System.out.println("The val is" +  uniqueIdentifier[1]);


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://call-of-duty-modern-warfare.p.rapidapi.com/multiplayer/"+  uniqueIdentifier[0] +  "%2523"+uniqueIdentifier[1]+ "/battle")
                .get()
                .addHeader("x-rapidapi-host", "call-of-duty-modern-warfare.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "c8e7025684msh6371be6d590c323p19fe31jsnc680714fd143")
                .build();

        Response response = client.newCall(request).execute();
        BufferedReader read;
        String Line;
        StringBuffer content = new StringBuffer();
        read = new BufferedReader(new InputStreamReader(response.body().byteStream()));
        while((Line = read.readLine()) != null)
        {
            content.append(Line);
        }
        read.close();
        System.out.println(content);

        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(content.toString());
        List<JsonNode> games = node.findValues("br");

        // TOTAL AMOUNT OF WINS IN WARZONE
        return Double.toString(games.get(0).get("properties").get("wins").asDouble());
    }

    public static String getAllWarzonePlayedGames(String BattleNetID) throws IOException {

        String[] uniqueIdentifier = BattleNetID.split("#");
        System.out.println(uniqueIdentifier.length);

        System.out.println("The val is" +  uniqueIdentifier[1]);


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://call-of-duty-modern-warfare.p.rapidapi.com/multiplayer/"+  uniqueIdentifier[0] +  "%2523"+uniqueIdentifier[1]+ "/battle")
                .get()
                .addHeader("x-rapidapi-host", "call-of-duty-modern-warfare.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "c8e7025684msh6371be6d590c323p19fe31jsnc680714fd143")
                .build();

        Response response = client.newCall(request).execute();
        BufferedReader read;
        String Line;
        StringBuffer content = new StringBuffer();
        read = new BufferedReader(new InputStreamReader(response.body().byteStream()));
        while((Line = read.readLine()) != null)
        {
            content.append(Line);
        }
        read.close();
        System.out.println(content);

        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(content.toString());
        List<JsonNode> games = node.findValues("br");

        // THIS IS THE KDR OF USER IN WARZONE ONLY
        return Double.toString(games.get(0).get("properties").get("gamesPlayed").asDouble());
    }


}
