package com.lOnlyGames.backend.utilities;

import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Loader {

    @Autowired
    private UserDAO userDAO;






    public void loadCallOfDuty(User user)
    {}
    public void loadPUBG(User user)
    {}
    public void loadRunescape(User user)
    {}
    public void loadCSGO(User user) throws SteamApiException {


    }

}
