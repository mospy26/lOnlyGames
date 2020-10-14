package com.lOnlyGames.backend.DAO;

import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.repository.GameRepository;
import com.lOnlyGames.backend.repository.UserGameRepository;
import com.lOnlyGames.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserGameRepository userGameRepository;

    public List<String> getAllUsers(){

        var it = userRepository.findAll();
        var users = new ArrayList<String>();
        it.forEach(e -> users.add(e.getUsername()));

        return users;
    }
    public String addUser(User user){
        if(userRepository.findById(user.getUsername()).isPresent()){
            return "Username already exists\nPlease select another.";
        }
        else{
            userRepository.save(user);
            return "Saved\n" + user.getUsername() + " has been added.";
        }
    }

    public String addGame(UserGame userGame){
        if(!gameRepository.findById(userGame.getGame().getName()).isPresent()){
            //Game is not already present.
            //Save the game
            gameRepository.save(userGame.getGame());
        }
        userGameRepository.save(userGame);
        return "Game and userGame objects created.";
    }
}
