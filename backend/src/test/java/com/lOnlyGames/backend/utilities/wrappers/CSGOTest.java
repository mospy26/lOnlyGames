package com.lOnlyGames.backend.utilities.wrappers;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(JUnit4.class)
public class CSGOTest {


    @Test
    public void  testTotalKills() throws SteamApiException {
        //Friends Account
        CSGO stub = new CSGO("76561198102331830");
        String actual = stub.resolvetotalKills();
        assertEquals(actual,"10001");

        //My personal Account
        CSGO stub2 = new CSGO("76561198882328170");
        String actual2 = stub2.resolvetotalKills();
        assertEquals(actual2,"208");

        //Team members account.
        CSGO stub3 = new CSGO("76561198068222746");
        String actual3 = stub3.resolvetotalKills();
        assertEquals(actual3,"9475");
    }

    @Test
    public void testKDR() throws SteamApiException {
        CSGO stub = new CSGO("76561198102331830");
        String actual = stub.resolveKDR();
        assertNotEquals(actual, "");
        assertNotEquals(actual,null);
    }

    @Test
    public void testTotalWins() throws SteamApiException {
        //Friends Account
        CSGO stub = new CSGO("76561198102331830");
        String actual = stub.resolveTotalWins();
        assertEquals(actual,"5002");

        //My personal Account
        CSGO stub2 = new CSGO("76561198882328170");
        String actual2 = stub2.resolveTotalWins();
        assertEquals(actual2,"108");

        //Team members account.
        CSGO stub3 = new CSGO("76561198068222746");
        String actual3 = stub3.resolveTotalWins();
        assertEquals(actual3,"4967");
    }

    @Test
    public void testTotalDeaths() throws SteamApiException
    {
        CSGO stub = new CSGO("76561198102331830");
        String actual = stub.resolveTotalDeaths();
        assertEquals(actual,"1738");

        //My personal Account
        CSGO stub2 = new CSGO("76561198882328170");
        String actual2 = stub2.resolveTotalDeaths();
        assertEquals(actual2,"267");

        //Team members account.
        CSGO stub3 = new CSGO("76561198068222746");
        String actual3 = stub3.resolveTotalDeaths();
        assertEquals(actual3,"10411");
    }

}
