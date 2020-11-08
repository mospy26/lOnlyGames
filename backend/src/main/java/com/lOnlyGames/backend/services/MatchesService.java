package com.lOnlyGames.backend.services;

import com.lOnlyGames.backend.DAO.MatchesDAO;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchesService {

    @Autowired
    private MatchesDAO matchesDAO;
    @Autowired
    private BlockedService blockedService;
    @Autowired
    private LikeService likedService;

    public List<List<UserGame>> getMatches(){
        //get the user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //find the list of games played by the user
        List<UserGame> userGames = matchesDAO.getUserGameRepository().findByUser(user);
        //create a list of users that play the same game as the user
        List<List<UserGame>> listUserGames = new ArrayList<>();
        for(UserGame ug: userGames){
            /*find all users that play the same
            games and are not our current user*/
            List<UserGame> gameList = matchesDAO.getUserGameRepository().findByGameAndUserNot(ug.getGame(), user);
            listUserGames.add(gameList);
        }
        //don't want any matches to users that have been blocked

        //all user objects blocked by the user
        List<User> blockedUsers = blockedService.allBlockedByUser();
        //remove any user games that contain a blocked user
        for(List<UserGame> listUG: listUserGames){
            listUG.removeIf(ug -> blockedUsers.contains(ug.getUser()));
        }

        //all user objects that have blocked me
        List<User> usersBlockedMe = blockedService.allBlockedMe();
        for(List<UserGame> listUG: listUserGames){
            listUG.removeIf(ug -> usersBlockedMe.contains(ug.getUser()));
        }

        //don't want any matches to users that I have liked

        //all user objects liked by the user
        List<User> likedUsers = likedService.getAllLikes();
        //remove any user games that contain a liked user
        for(List<UserGame> listUG: listUserGames){
            listUG.removeIf(ug -> likedUsers.contains(ug.getUser()));
        }

        if(listUserGames.isEmpty()){
            return new ArrayList<>();
        }
        return listUserGames;
    }
}
