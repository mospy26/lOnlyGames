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
                case 238090:
                    continue;
                case 730:
                    UserGame userGame = new UserGame(user,userDAO.getGameRepository().findByName("Counter Strike Global Offensive"));
                    //Lets get the stats
                    userGame.setKdr(new GeneratorImpl(user.getSteamId(),730).KDR());
                    userGame.setTotalKills(new GeneratorImpl(user.getSteamId(),730).totalKills());
                    userGame.setTotalDeaths(new GeneratorImpl(user.getSteamId(),730).totalDeaths());
                    userGame.setTotalHoursPlayed(new GeneratorImpl(user.getSteamId(),730).totalHours());
                    userDAO.getUserGameRepository().save(userGame);
                    Set<UserGame> games = new HashSet<>();
                    user.setGames(games);
                    user.getGames().add(userGame);
                case 698780:
                    continue;

            }


        }




    }





}
