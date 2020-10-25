package com.lOnlyGames.backend.DAO;

import java.util.Optional;

import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidUsernameException;
import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.repository.GameRepository;
import com.lOnlyGames.backend.repository.UserGameRepository;
import com.lOnlyGames.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

    public User getUser(String username) {
        Optional<User> user = userRepository.findById(username);

        if (!user.isPresent()) return null;

        return user.get();
    }
	public Optional<User> authenticate(String username, String password) {
        Optional<User> user = userRepository.findById(username);
        
        return user;
	}
	public void register(User user) {
        userRepository.save(user);
	}
}
