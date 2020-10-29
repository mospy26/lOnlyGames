package com.lOnlyGames.backend.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.interfaces.Generator;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.utilities.APIFetcher;
import com.lOnlyGames.backend.utilities.GeneratorImpl;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GamesAPIService {
    private Generator generator;



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
        List<JsonNode> nodes = null;
        if(!user.getSteamId().equals(""))
        {
             nodes = APIFetcher.getGamesPlayed(user.getSteamId());
        }
        // Lets see if they play the games that we want.

        for(int i = 0; i<nodes.size(); ++i)
        {
            int code = nodes.get(i).asInt();
            switch(code)
            {
                case 238090: //RB6
                    continue;
                case 730:  // CSGO
                   loadCSGO(user,"Counter Strike Global Offensive",730);
                case 698780: // Forgot wat this was
                    continue;

            }


        }




    }

    private void loadCSGO(User user, String gameName,int appID) throws SteamApiException {
        UserGame userGame = new UserGame(user,userDAO.getGameRepository().findByName(gameName));
        //Lets get the stats
        userGame.setKdr(new GeneratorImpl(user.getSteamId(),appID).KDR());
        //set the details
        userGame.setTotalKills(new GeneratorImpl(user.getSteamId(),appID).totalKills());
        userGame.setTotalDeaths(new GeneratorImpl(user.getSteamId(),appID).totalDeaths());
        userGame.setTotalHoursPlayed(new GeneratorImpl(user.getSteamId(),appID).totalHours());
        //And away we go.
        userDAO.getUserGameRepository().save(userGame);
        //Dont forget to link a reference that the user can access
        Set<UserGame> games = new HashSet<>();
        user.setGames(games);
        user.getGames().add(userGame);
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
