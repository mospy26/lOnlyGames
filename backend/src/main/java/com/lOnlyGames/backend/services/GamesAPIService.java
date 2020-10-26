package com.lOnlyGames.backend.services;

import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.interfaces.Generator;
import com.lOnlyGames.backend.utilities.APIFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GamesAPIService {
    private Generator generator;

    @Autowired
    UserService userService = new UserService();

    @Autowired
    private UserDAO userDAO;


    public String addGame()
    {
        return "";
    }


}
