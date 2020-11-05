package com.lOnlyGames.backend.utilities.wrappers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lOnlyGames.backend.utilities.Util;
import com.lOnlyGames.backend.utilities.factories.ConcreteGameDataFactory;
import java.io.IOException;
import java.util.List;

public class CODMW {

    private String userID;
    private StringBuffer content;

    public CODMW(String userID) throws IOException {
        this.userID = userID;
        content = new ConcreteGameDataFactory().create("COD").connect(userID);
    }



    public String resolveAllGameStats() throws IOException {

        String stats = "";
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(content.toString());
        List<JsonNode> games = node.findValues("br");
        System.out.println("The size of games is " + games.size());
        double a = games.get(0).get("properties").get("kdRatio").asDouble();

        stats += "Warzone KDR = " + a + "\n";
        // Lets get the Multiplayer KDR

        JsonNode node2 = om.readTree(content.toString());
        List<JsonNode> games2 = node.findValues("lifetime");
        stats += "Multiplayer KDR =" + Double.toString(games2.get(0).get("all").get("properties").get("kdRatio").asDouble()) + "\n" ;

        // Lets get the Total Time played


        JsonNode node3 = om.readTree(content.toString());
        List<JsonNode> games3 = node.findValues("lifetime");
        // THIS IS THE TOTAL TIME PLAYED IN HOURS ONLY.
        stats += "Total Time Played= " + (games3.get(0).get("all").get("properties").get("timePlayedTotal").asDouble() / 60 /60) + "\n";

        // Now lets get the total games played in warzone


        JsonNode node4 = om.readTree(content.toString());
        List<JsonNode> games4 = node.findValues("br");

        // Total games played in warzone
        stats += "Total Warzone Games Played = " + games4.get(0).get("properties").get("gamesPlayed").asDouble();

        return stats;
    }



}
