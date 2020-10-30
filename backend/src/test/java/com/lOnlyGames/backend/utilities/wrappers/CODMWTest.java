package com.lOnlyGames.backend.utilities.wrappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CODMWTest {



    @Test
    public void testGetKDRWarzone() throws IOException {
        CODMW codmw = new CODMW("Syncronax#1344");
        String actual = codmw.resolveKDRWarzone();
        String expected = "1.42";
        assertEquals(actual,expected);


        CODMW codmw2 = new CODMW("BigBick47#1647");
        String actual2 = codmw2.resolveKDRWarzone();
        String expected2 = "0.73";
        assertEquals(actual2,expected2);
    }

    @Test
    public void testGetWarzoneWins() throws IOException {
        CODMW codmw = new CODMW("Syncronax#1344");
        String actual = codmw.resolveTotalWarzoneWins();
        String expected = "9.0";
        assertEquals(actual,expected);


        CODMW codmw2 = new CODMW("BigBick47#1647");
        String actual2 = codmw2.resolveTotalWarzoneWins();
        String expected2 = "1.0";
        assertEquals(actual2,expected2);
    }

    @Test
    public void testTotalWarzoneGames() throws IOException {
        CODMW codmw = new CODMW("Syncronax#1344");
        String actual = codmw.resolveTotalWarzoneGames();
        String expected = "437.0";
        assertEquals(actual,expected);

        CODMW codmw2 = new CODMW("BigBick47#1647");
        String actual2 = codmw2.resolveTotalWarzoneGames();
        String expected2 = "360.0";
        assertEquals(actual2,expected2);
    }
}
