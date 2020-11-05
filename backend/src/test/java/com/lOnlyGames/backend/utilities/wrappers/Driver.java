package com.lOnlyGames.backend.utilities.wrappers;
import com.lOnlyGames.backend.utilities.APIFetcher;
import com.lOnlyGames.backend.utilities.wrappers.PUBG;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import java.io.IOException;



public class Driver {

    public static void main(String args[]) throws SteamApiException, IOException {


        System.out.println(APIFetcher.resolveCSGO("76561198882328170"));
        System.out.println(APIFetcher.resolveTeamFortressTwo("76561198882328170"));
    }
}
