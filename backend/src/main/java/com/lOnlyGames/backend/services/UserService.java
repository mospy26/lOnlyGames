package com.lOnlyGames.backend.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidUsernameException;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component(value="UserService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BlockedService blockedService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        Optional<User> user = userDAO.authenticate(username, password);

        if (!user.isPresent()) throw new InvalidCredentialsException();
        if (!passwordEncoder.matches(password, user.get().getPassword())) throw new InvalidCredentialsException();

        return user.get();
    }

    public void register(User user) {
        if (userDAO.getUser(user.getUsername()) != null) {
            throw new InvalidUsernameException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userDAO.register(user);
    }

    public List<User> getUsersWithNameLike(String partialUsername) {
        User me = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> fetchedUsers = userDAO.findUsersStartWith(partialUsername);
        List<User> blocked = blockedService.allBlockedByUser();

        List<String> blockedUsers = blocked.stream().map(b -> b.getUsername())
                .collect(Collectors.toList());

        // Only fetch those users who aren't blocked
        fetchedUsers.removeIf(x -> blockedUsers.contains(x.getUsername()) || x.getUsername().equals(me.getUsername()));

        return fetchedUsers;
    }
    public UserDetails updateUser(Map<String, String> payload) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (payload.containsKey("firstName")) user.setFirstName(payload.get("firstName"));
        if (payload.containsKey("lastName")) user.setLastName(payload.get("lastName"));
        if (payload.containsKey("email")) user.setEmail(payload.get("email"));
        if (payload.containsKey("discordId")) user.setDiscordId(payload.get("discordId"));
        if (payload.containsKey("steamId")) user.setSteamId(payload.get("steamId"));
        if (payload.containsKey("bio")) user.setBio(payload.get("bio"));
        if (payload.containsKey(("location"))) user.setLocation((payload.get("location")));
        if (payload.containsKey("avatarURL")) user.setAvatarURL(payload.get("avatarURL"));

        userDAO.addUser(user);
        return user;
    }

    public String reportUser(User user) {
        user = userDAO.getUser(user.getUsername());
        user.setNumberOfReports(user.getNumberOfReports()+1);
        userDAO.addUser(user);
        //block user as well after report
        blockedService.blockUser(user);
        return user.getUsername() + " has been reported";
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
