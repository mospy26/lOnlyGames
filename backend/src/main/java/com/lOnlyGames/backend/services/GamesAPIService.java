package com.lOnlyGames.backend.services;
import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.utilities.Loader;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class GamesAPIService {
    @Autowired
    private UserDAO userDAO;


    @Autowired
    private Loader loader;



    /**
     * If and only if registration is successful, we want to use the API and preload all the games from the users steam library into
     * our application.
     */
    public void preload(User user) throws IOException, SteamApiException {
        if(!user.getBattlenet().equals("") && user.getBattlenet() != null)
        {
            loader.loadCallOfDuty(user);
        }
        if(!user.getPubGPlayerName().equals("") && user.getPubGPlayerName() != null)
        {
           loader.loadPUBG(user);
        }
        if(!user.getRunescapeDisplayName().equals("") && user.getRunescapeDisplayName() != null) {
            loader.loadRunescape(user);
        }

        if(!user.getSteamId().equals("") && user.getSteamId() != null)
        {
            loader.loadCSGO(user);
            loader.loadPUBG(user);
        }

    }







}
