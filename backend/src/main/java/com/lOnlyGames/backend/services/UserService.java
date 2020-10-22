package com.lOnlyGames.backend.services;

import java.util.Optional;

import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component(value="UserService")
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User getUser(String username) throws Exception {
        return userDAO.getUser(username);
    }
}