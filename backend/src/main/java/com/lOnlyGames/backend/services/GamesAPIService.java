package com.lOnlyGames.backend.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.utilities.APIFetcher;
import com.lOnlyGames.backend.utilities.GeneratorImpl;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GamesAPIService {



    private final int CSGO_RESOURCE_CODE = 730;
    private final int RB6_RESOURCE_CODE = 359550;
    private final int DOTA_RESOURCE_CODE = 570;

    @Autowired
    private UserDAO userDAO;


    public String addGame(User user)
    {
//      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//      String steam = "";
//      if(principal instanceof User)
//      {
//          User user1 = (User) principal;
//          steam = user1.getSteamId();
//      }
      return "LOL";
    }

    /**
     * If and only if registration is successful, we want to use the API and preload all the games from the users steam library into
     * our application.
     */
    public void preload(User user) throws IOException, SteamApiException {
        System.out.println(user.getSteamId());
        System.out.println(user.getBattlenet());
        System.out.println(user.getPubGPlayerName());
        System.out.println(user.getRunescapeDisplayName());
    }



    private void loadPUBG(String playerName)
    {}

    private void loadLeague(String playerName)
    {
    }

    private void loadRunescape(String playerName)
    {
    }

    private void loadCallofDuty()
    {
    }




}
