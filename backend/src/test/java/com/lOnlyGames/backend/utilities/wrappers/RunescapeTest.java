package com.lOnlyGames.backend.utilities.wrappers;

import org.hibernate.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnit4.class)
public class RunescapeTest {
    @Test
    public void getAllStats() throws IOException {

        String expected = "Overall: 2898\n" +
                "Attack: 99\n" +
                "Defence: 99\n" +
                "Strength: 99\n" +
                "Constitution: 99\n" +
                "Ranged: 99\n" +
                "Prayer: 99\n" +
                "Magic: 99\n" +
                "Cooking: 99\n" +
                "Woodcutting: 99\n" +
                "Fletching: 99\n" +
                "Fishing: 99\n" +
                "Firemaking: 99\n" +
                "Crafting: 99\n" +
                "Smithing: 99\n" +
                "Mining: 99\n" +
                "Herblore: 120\n" +
                "Agility: 99\n" +
                "Thieving: 99\n" +
                "Slayer: 120\n" +
                "Farming: 120\n" +
                "Runecrafting: 99\n" +
                "Hunter: 99\n" +
                "Construction: 99\n" +
                "Summoning: 99\n" +
                "Dungeoneering: 120\n" +
                "Divination: 99\n" +
                "Invention: 120\n";

        Runescape actual = new Runescape("Linkin1985");
        assertEquals(actual.resolveAllStats(),expected);
    }

    @Test
    public void getAllStatsFailed() throws IOException {
        boolean notFound = false;

        try{
            Runescape mock = new Runescape("I AM NOT A VALID RUNESCAPE PLAYER OKAY!!!!");
            mock.resolveAllStats();
        } catch ( NoSuchElementException elementException) {
            notFound = true;
            elementException.printStackTrace();
        }
        assertTrue(notFound);
    }

}