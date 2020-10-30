package com.lOnlyGames.backend.utilities.wrappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PUBGTest {


    @Test
    public void testResolvePlayerID() throws IOException {
        PUBG pubg = new PUBG("LongDeBaby");
        //String actual = pubg.mini();
        //assertEquals(actual,"\"account.147c73d607314784ace140020bb73861\"");

        PUBG pubg1 = new PUBG("HuYa_BMZFeiFei");
        //String actual2 = pubg1.resolvePlayerID();
      //  assertEquals(actual2,"\"account.e6bb782b0b0d4795a2fe057830f0cba3\"");
    }

    @Test
    public void testResolveWins() throws IOException {
        PUBG pubg1 = new PUBG("HuYa_BMZFeiFei");
        String actual2 = pubg1.resolveWins();
        assertEquals(actual2,"Duo Wins: 35\n" +
                "Solo Wins: 11\n" +
                "Squad Wins: 1133");

        PUBG pubg2 = new PUBG("LongDeBaby");
        String actual3 = pubg2.resolveWins();
        assertEquals(actual3,"Duo Wins: 37\n" +
                "Solo Wins: 0\n" +
                "Squad Wins: 207");
    }

    @Test
    public void testResolveTop10s() throws IOException {
        PUBG pubg1 = new PUBG("HuYa_BMZFeiFei");
        String actual2 = pubg1.resolveTop10s();
        assertEquals(actual2,"Solo Top 10 Finishes : 19\n" +
                "Squad Top 10 Finishes: 2953\n" +
                "Duo Top 10 Finishes: 113");

        PUBG pubg2 = new PUBG("LongDeBaby");
        String actual3 = pubg2.resolveTop10s();
        assertEquals(actual3,"Solo Top 10 Finishes : 6\n" +
                "Squad Top 10 Finishes: 1499\n" +
                "Duo Top 10 Finishes: 264");
    }
}
