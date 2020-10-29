package com.lOnlyGames.backend.utilities;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import java.io.IOException;




public class Driver {

    public static void main(String args[]) throws SteamApiException, IOException {


        APIFetcher.getTotalWins("LongDeBaby");

    }
}
