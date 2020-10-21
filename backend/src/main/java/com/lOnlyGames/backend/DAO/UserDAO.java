package com.lOnlyGames.backend.DAO;

import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.repository.GameRepository;
import com.lOnlyGames.backend.repository.UserGameRepository;
import com.lOnlyGames.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*DAO should only be used for remove/update
insert/select, all other logic should occur
in the service layer*/
@Component
public class UserDAO {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserGameRepository userGameRepository;

    //UserRepository should be accessible from DAO so
    //business logic can be performed in the Service Layer
    public UserRepository getUserRepository(){
        return userRepository;
    }
    public UserGameRepository getUserGameRepository(){
        return userGameRepository;
    }
    public GameRepository getGameRepository(){
        return gameRepository;
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
    public void addUser(User user){
            userRepository.save(user);
    }

    public void addGame(Game game){
        gameRepository.save(game);
    }

    public void addUserGame(UserGame userGame){
        userGameRepository.save(userGame);
    }
}
