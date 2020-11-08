package com.lOnlyGames.backend.DAO;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    private static String AVATARS[] = {
        "https://i.imgur.com/SRxUakd.png",
        "https://i.imgur.com/NfS3y42.png",
        "https://i.imgur.com/fvl0dqi.png",
        "https://i.imgur.com/kKfFMgv.png",
        "https://i.pinimg.com/originals/76/aa/34/76aa3494374e18931fdac18061884ce2.webp",
        "https://i.pinimg.com/originals/dc/cd/99/dccd99e1be1fa3f4f1543aaa9cbfc810.webp",
        "https://i.pinimg.com/originals/62/71/df/6271df0313418f138a64b8e2634812ad.png",
        "https://i.pinimg.com/474x/ab/b1/16/abb11623b8ff29604b1eceaa1e3894e0.jpg"
    };

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
        if (user.getAvatarURL() == null) {
            user.setAvatarURL(AVATARS[new Random().nextInt(AVATARS.length)]);
        }
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
        user.setAvatarURL(AVATARS[new Random().nextInt(AVATARS.length)]);
        userRepository.save(user);
    }
    
    public List<User> findUsersStartWith(String username) {
        return userRepository.findByUsernameStartsWith(username);
    }

    public void deleteUserGame(UserGame userGame)
    {
        userGameRepository.delete(userGame);
    }
}
