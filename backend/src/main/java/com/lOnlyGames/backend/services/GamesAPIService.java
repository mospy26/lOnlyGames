package com.lOnlyGames.backend.services;
import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.errorhandlers.exceptions.APICallFailedException;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.utilities.APIFetcher;
import com.lOnlyGames.backend.utilities.GeneratorImpl;
import com.lOnlyGames.backend.utilities.Loader;
import com.lOnlyGames.backend.utilities.Poller;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.apache.tomcat.jni.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public class GamesAPIService {
    @Autowired
    private UserDAO userDAO;


    @Autowired
    private Loader loader;

    @Autowired
    private Poller poll;

    /**
     * When a user registers, check if they have a valid steam, battlenet, pubg, runescape id
     * if they do then search the external API to retrieve stats and create user game objects.
     * If they do not, then do not instantiate user game objects for that user.
     *
     * @param user User to preload infomation for
     * @throws IOException
     * @throws SteamApiException
     */
    public void preload(User user) throws IOException, SteamApiException {


            if (!"".equals(user.getBattlenet()) && user.getBattlenet() != null) {
                loader.loadCallOfDuty(user);
            }
            if (!"".equals(user.getPubGPlayerName()) && user.getPubGPlayerName() != null) {
                loader.loadPUBG(user);
            }
            if (!"".equals(user.getRunescapeDisplayName()) && user.getRunescapeDisplayName() != null) {
                loader.loadRunescape(user);
            }

            if (!"".equals(user.getSteamId()) && user.getSteamId() != null) {
                if (APIFetcher.resolveCSGO(user.getSteamId())) {
                    loader.loadCSGO(user);
                }
                if (APIFetcher.resolveTeamFortressTwo(user.getSteamId())) {
                    loader.loadTeamFortress2(user);
                }
            }


    }

    /**
     * Your probably asking why cant i just call the preload method, well, by using the poll method you are saving memory. Since no new usergame objects are created.
     * Call this method when you want to update the users data from the API every X amount of Seconds/Minutes/Hours
     *
     * @param user to update.
     */
    public void poll(User user) throws IOException, SteamApiException {
        // Update the current user instance.
      poll.update(user);
    }
}