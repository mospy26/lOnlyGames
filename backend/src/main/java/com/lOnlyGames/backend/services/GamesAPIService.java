package com.lOnlyGames.backend.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.interfaces.Generator;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.utilities.APIFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class GamesAPIService {
    private Generator generator;



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
    public void preload(User user) throws IOException {
        System.out.print(user.getSteamId());
        List<JsonNode> nodes = APIFetcher.getGamesPlayed(user.getSteamId());



    }





}
