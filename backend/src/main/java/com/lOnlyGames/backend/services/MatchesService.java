package com.lOnlyGames.backend.services;

import com.lOnlyGames.backend.DAO.MatchesDAO;
import com.lOnlyGames.backend.model.Blocked;
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

    public List<List<UserGame>> getMatches(User user){
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

        //all blocked objects from the user
        List<Blocked> blockedUsers = blockedService.allBlockedByUser(user);
        //all user objects blocked by the user
        List<User> blockees = new ArrayList<>();
        for(Blocked blk: blockedUsers){
            blockees.add(blk.getBlockee());
        }
        //remove any user games that contain a blocked user
        for(List<UserGame> listUG: listUserGames){
            listUG.removeIf(ug -> blockees.contains(ug.getUser()));
//            if(listUG.isEmpty()){
//                listUserGames.remove(listUG);
//            }
        }
        if(listUserGames.isEmpty()){
            return null;
        }
        return listUserGames;
    }
}
