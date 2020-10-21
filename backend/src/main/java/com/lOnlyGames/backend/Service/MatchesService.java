package com.lOnlyGames.backend.Service;

import com.lOnlyGames.backend.DAO.MatchesDAO;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchesService {

    @Autowired
    private MatchesDAO matchesDAO;
    @Autowired
    private BlockedService blockedService;

    public List<String> getMatches(User user){
        //find the list of games played by the user
        List<UserGame> userGames = matchesDAO.getUserGameRepository().findByUser(user);
        //create a list of users that play the same game as the user
        List<List<UserGame>> listUserGames = new ArrayList<List<UserGame>>();
        for(UserGame ug: userGames){
            /*find all users that play the same
            games and are not our current user*/
            List<UserGame> gameList = matchesDAO.getUserGameRepository().findByGameAndUserNot(ug.getGame(), user);
            listUserGames.add(gameList);
        }
        //getting the user names and games for all matches
        List<String> finalList = new ArrayList<String>();
        for(List<UserGame> list: listUserGames){
            for(UserGame ug: list){
                finalList.add(ug.getUser().getUsername());
            }
        }
        //don't want any matches to users that have been blocked
        List<String> blockedUsers = blockedService.allBlockedByUser(user);
        for(String str: blockedUsers){
            if(finalList.contains(str)){
                finalList.remove(str);
            }
        }
        return finalList;
    }
}
