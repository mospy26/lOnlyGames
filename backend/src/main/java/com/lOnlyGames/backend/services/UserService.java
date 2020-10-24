package com.lOnlyGames.backend.services;

import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component(value="UserService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    public Iterable<User> getAllUsers(){
        return userDAO.getAllUsers();
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


    public User getUser(String username) throws Exception {
        return userDAO.getUser(username);
    }

    public User authenticate(String username, String password) throws InvalidCredentialsException {
        return userDAO.authenticate(username, password);
    }

    public void register(User user) {
        userDAO.register(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user;
        
        try {
            user = getUser(username);
            return user;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Username \"" + username + "\" is invalid");
        }
    }
}
