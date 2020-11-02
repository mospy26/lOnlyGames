package com.lOnlyGames.backend.utilities;

import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Loader {

    @Autowired
    private UserDAO userDAO;

    public void loadCSGO(User user) throws IOException, SteamApiException {
        UserGame userGame = new UserGame(user,userDAO.getGameRepository().findByName("Counter Strike Global Offensive") );
        user.getGames().add(userGame);
        userGame.setStatistics(new GeneratorImpl(user).getCSGOStats());
        userDAO.getUserGameRepository().save(userGame);
        userDAO.getUserRepository().save(user);

    }



    public void loadTeamFortress2(User user) throws SteamApiException {
        UserGame userGame = new UserGame(user,userDAO.getGameRepository().findByName("TeamFortress 2") );
        user.getGames().add(userGame);
        userGame.setStatistics(new GeneratorImpl(user).getTeamFortressTwoStats());
        userDAO.getUserRepository().save(user);
        userDAO.getUserGameRepository().save(userGame);
    }


    public void loadCallOfDuty(User user) throws IOException {
        UserGame userGame = new UserGame(user,userDAO.getGameRepository().findByName("CODMW") );
        user.getGames().add(userGame);
        userGame.setStatistics(new GeneratorImpl(user).getCODStats());
        userDAO.getUserGameRepository().save(userGame);
        userDAO.getUserRepository().save(user);

    }
    public void loadPUBG(User user) throws IOException {
        UserGame userGame = new UserGame(user,userDAO.getGameRepository().findByName("PUBG") );
        user.getGames().add(userGame);
        userGame.setStatistics(new GeneratorImpl(user).resolveAllPUBGStats());
        userDAO.getUserGameRepository().save(userGame);
        userDAO.getUserRepository().save(user);

    }
    public void loadRunescape(User user) throws IOException {
        UserGame userGame = new UserGame(user,userDAO.getGameRepository().findByName("Runescape") );
        user.getGames().add(userGame);
        userGame.setStatistics(new GeneratorImpl(user).resolveAllRunescapeStats());
        userDAO.getUserGameRepository().save(userGame);
        userDAO.getUserRepository().save(user);

    }


}
