package com.lOnlyGames.backend.utilities;

import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.model.User;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Poller {


    @Autowired
    private UserDAO userDAO;

    public void update(User user) throws IOException, SteamApiException {
       userDAO.getGameRepository().findByName("CODMW");

        //TO-DO
        for(int i = 0; i<userDAO.getUserGameRepository().findByUser(user).size(); ++i)
        {
            //Lets begin its a bit verbose but this works,

            if(userDAO.getUserGameRepository().findByUser(user).get(i).getGame().getName().equals("CODMW"))
            {
                //If the current user has this game, that means we were able to extract stats for it, so lets update the stats by calling the API once again.
                userDAO.getUserGameRepository().findByUser(user).get(i).setStatistics(new GeneratorImpl(user).getCODStats()); // Thats done, so lets just save this info.
                userDAO.getUserGameRepository().save( userDAO.getUserGameRepository().findByUser(user).get(i));

            }
            if(userDAO.getUserGameRepository().findByUser(user).get(i).getGame().getName().equals("PUBG"))
            {
                //Same as above.
                userDAO.getUserGameRepository().findByUser(user).get(i).setStatistics(new GeneratorImpl(user).resolveAllPUBGStats());
                userDAO.getUserGameRepository().save( userDAO.getUserGameRepository().findByUser(user).get(i));
            }
            if(userDAO.getUserGameRepository().findByUser(user).get(i).getGame().getName().equals("Runescape"))
            {
                //If the current user has this game, that means we were able to extract stats for it, so lets update the stats by calling the API once again.
                userDAO.getUserGameRepository().findByUser(user).get(i).setStatistics(new GeneratorImpl(user).resolveAllRunescapeStats()); // Thats done, so lets just save this info.
                userDAO.getUserGameRepository().save( userDAO.getUserGameRepository().findByUser(user).get(i));

            }
            if(userDAO.getUserGameRepository().findByUser(user).get(i).getGame().getName().equals("Counter Strike Global Offensive"))
            {
                //If the current user has this game, that means we were able to extract stats for it, so lets update the stats by calling the API once again.
                userDAO.getUserGameRepository().findByUser(user).get(i).setStatistics(new GeneratorImpl(user).getCSGOStats()); // Thats done, so lets just save this info.
                userDAO.getUserGameRepository().save( userDAO.getUserGameRepository().findByUser(user).get(i));

            }
            if(userDAO.getUserGameRepository().findByUser(user).get(i).getGame().getName().equals("TeamFortress 2"))
            {
                //If the current user has this game, that means we were able to extract stats for it, so lets update the stats by calling the API once again.
                userDAO.getUserGameRepository().findByUser(user).get(i).setStatistics(new GeneratorImpl(user).getTeamFortressTwoStats()); // Thats done, so lets just save this info.
                userDAO.getUserGameRepository().save( userDAO.getUserGameRepository().findByUser(user).get(i));

            }
        }
    }

    public boolean resolveisPreloadable(User user)
    {

        System.out.println(user.getBattlenet());
        System.out.println(user.getPubGPlayerName());
        System.out.println(user.getRunescapeDisplayName());
        System.out.println(user.getSteamId());

        // This function is called in the update endpoint if the user has decided to not input steam,battlenet, or other Game IDs during registration
        if(user.getBattlenet() == null || user.getRunescapeDisplayName() == null || user.getPubGPlayerName() == null || user.getSteamId() == null)
        {
            return true;
        }
        return false;
    }


}
