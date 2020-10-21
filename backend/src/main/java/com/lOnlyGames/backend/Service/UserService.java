package com.lOnlyGames.backend.Service;

import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.repository.GameRepository;
import com.lOnlyGames.backend.repository.UserGameRepository;
import com.lOnlyGames.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component(value="UserService")
public class UserService {

    @Autowired
    private UserGameRepository userGameRepository;

    @Autowired
    private UserDAO userDAO;

    public List<String> getAllUsers(){

        var it = userDAO.getAllUsers();
        var users = new ArrayList<String>();
        it.forEach(e -> users.add(e.getUsername()));

        return users;
    }
    public String addUser(User user){
        if(userDAO.getUserRepository().findById(user.getUsername()).isPresent()){
            return "Username already exists\nPlease select another.";
        }
        else{
            userDAO.addUser(user);
            return "Saved\n" + user.getUsername() + " has been added.";
        }
    }

    public String addGame(UserGame userGame){
        if(!userDAO.getGameRepository().findById(userGame.getGame().getName()).isPresent()){
            //Game is not already present.
            //Save the game
            userDAO.addGame(userGame.getGame());
        }
        userDAO.addUserGame(userGame);
        return "Game and userGame objects created.";
    }
}
