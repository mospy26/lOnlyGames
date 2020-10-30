package com.lOnlyGames.backend.utilities.wrappers;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TeamFortress2Test {

    @Test
    public void getTotalKills() throws SteamApiException {
        TeamFortress2 teamFortress2 = new TeamFortress2("76561198102331830");
        String expcted = "Scout Total Kills 34\n" +
                "Soldier Total Kills: 0\n" +
                "Spy Total Kills: 4\n" +
                "Pyro Total Kills: 35\n" +
                "Medic Total Kills: 2\n" +
                "Demolition Total Kills: 0\n" +
                "Heavy Total Kills: 1\n" +
                "Engineer Total Kills: 1\n" +
                "Sniper Total Kills: 18\n";

        assertEquals(teamFortress2.resolveTF2Kills(),expcted);
    }

    @Test
    public void getTotalPlayTime() throws SteamApiException {
        TeamFortress2 teamFortress2 = new TeamFortress2("76561198102331830");
        System.out.println(teamFortress2.resolvePlayTime());
        assertEquals(teamFortress2.resolvePlayTime(),0.6461111111111111,0.1);
    }
}
