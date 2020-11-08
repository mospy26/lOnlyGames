package com.lOnlyGames.backend.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lOnlyGames.backend.DAO.LikeDAO;
import com.lOnlyGames.backend.DAO.MatchesDAO;
import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.errorhandlers.exceptions.CannotReportSelfException;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidUsernameException;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.Liked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;

import com.lOnlyGames.backend.utilities.Poller;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transaction;
import javax.websocket.Session;


@Component(value="UserService")
public class UserService implements UserDetailsService {


    @Autowired
    private  GamesAPIService gamesAPIService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BlockedService blockedService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LikeDAO likeDAO;

    @Autowired
    private MatchesDAO matchesDAO;

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

    public User authenticate(String username, String password) throws InvalidCredentialsException, IOException, SteamApiException {
        Optional<User> user = userDAO.authenticate(username, password);

        if (!user.isPresent()) throw new InvalidCredentialsException();
        if (!passwordEncoder.matches(password, user.get().getPassword())) throw new InvalidCredentialsException();
        // gamesAPIService.poll(user.get());
        return user.get();
    }

    public void register(User user) throws IOException, SteamApiException {

        if (userDAO.getUser(user.getUsername()) != null) {
            throw new InvalidUsernameException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.register(user);
        gamesAPIService.preload(user);




    }

    public List<UserGame> getGames() {
        User me = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (me == null) {
            throw new AccessDeniedException("Not logged in");
        }
        List<UserGame> userGames = matchesDAO.getUserGameRepository().findByUser(me);
        List<Game> games = new ArrayList<>();
        for(UserGame userGame:userGames){
            Game game = userGame.getGame();
            games.add(game);
        }
        return userGames;
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
    public User updateUser(Map<String, String> payload) throws IOException, SteamApiException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean toPoll = false;
        if (payload.containsKey("firstName")) user.setFirstName(payload.get("firstName"));
        if (payload.containsKey("lastName")) user.setLastName(payload.get("lastName"));
        if (payload.containsKey("email")) user.setEmail(payload.get("email"));
        if (payload.containsKey("discordId")) user.setDiscordId(payload.get("discordId"));
        if (payload.containsKey("bio")) user.setBio(payload.get("bio"));
        if (payload.containsKey(("location"))) user.setLocation((payload.get("location")));
        if (payload.containsKey("avatarURL")) user.setAvatarURL(payload.get("avatarURL"));
        if (payload.containsKey("battlenet")) user.setBattlenet(payload.get("battlenet"));
        if (payload.containsKey(("runescapeDisplayName"))) user.setRunescapeDisplayName((payload.get("runescapeDisplayName")));
        if (payload.containsKey("pubGPlayerName")) user.setPubGPlayerName(payload.get("pubGPlayerName"));
        if (payload.containsKey("steamId")) user.setSteamId(payload.get("steamId"));



        if(new Poller().resolveisPreloadable(user))
        {
            gamesAPIService.preload(user);
        }
        else
        gamesAPIService.poll(user);


        userDAO.addUser(user);


        return user;
    }

    public User getProfile(String username) throws UsernameNotFoundException{
        User current_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /*check if we are looking at the profile for the
        user who is currently logged in.
         */
        if(current_user.getUsername().equals(username)){
            return current_user;
        }
        /*must be the profile of another user so we must
        check if we have they have liked us or not
        before revealing their steamID etc.
         */
        else{
            try{
                User targetUser = getUser(username);
                User userObj = new User(username);
                userObj.setBio(targetUser.getBio());
                userObj.setFirstName(targetUser.getFirstName());
                userObj.setLastName(targetUser.getLastName());
                userObj.setAvatarURL(targetUser.getAvatarURL());
                userObj.setNumberOfReports(targetUser.getNumberOfReports());
                userObj.setLocation(targetUser.getLocation());

                //checking if user has liked us
                /*adding in the extra personal details if that
                user has already liked us when we visit their profile.
                 */
                if(likeDAO.getLikedRepository().findByLikerAndLikes(targetUser, current_user) != null) {
                    userObj.setEmail(targetUser.getEmail());
                    userObj.setDiscordId(targetUser.getDiscordId());
                    userObj.setSteamId(targetUser.getSteamId());
                }
                return userObj;
            } catch(Exception e){
                throw new UsernameNotFoundException("Username \"" + username + "\" is invalid");
            }
        }
    }

    public String reportUser(User user) {
        user = userDAO.getUser(user.getUsername());

        //Throw exception if user reports themselves
        String currentUsername = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        if (user.getUsername().equals(currentUsername)) throw new CannotReportSelfException();

        //block user as well to prevent multiple reporting exploits
        blockedService.blockUser(user);

        user.setNumberOfReports(user.getNumberOfReports()+1);
        userDAO.addUser(user);

        return "User '" + user.getUsername() + "' has been reported.";
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
