package com.lOnlyGames.backend.DAO;

import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.repository.UserGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class MatchesDAO {

    @Autowired
    private UserGameRepository userGameRepository;

    public List<String> getMatches(User user){
        //find the list of games played by the user
        List<UserGame> userGames = userGameRepository.findByUser(user);
        //create a list of users that play the same game as the user
        List<List<UserGame>> listUserGames = new ArrayList<List<UserGame>>();
        for(UserGame ug: userGames){
            //find all users that play the same
            //games and are not our current user
            //listUserGames.add(
            List<UserGame> gameList = userGameRepository.findByGameAndUserNot(ug.getGame(), user);
            listUserGames.add(gameList);
        }
        List<String> finalList = new ArrayList<String>();
        for(List<UserGame> list: listUserGames){
            for(UserGame ug: list){
                finalList.add(ug.getUser().getUsername() + "," + ug.getGame().getName());
            }
        }
        return finalList;
    }
}
