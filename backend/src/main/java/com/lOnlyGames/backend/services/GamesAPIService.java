package com.lOnlyGames.backend.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.utilities.APIFetcher;
import com.lOnlyGames.backend.utilities.GeneratorImpl;
import com.lOnlyGames.backend.utilities.Loader;
import com.lOnlyGames.backend.utilities.wrappers.CODMW;
import com.lOnlyGames.backend.utilities.wrappers.PUBG;
import com.lOnlyGames.backend.utilities.wrappers.Runescape;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GamesAPIService {




    @Autowired
    private UserDAO userDAO;


    public String addGame(User user)
    {
//      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//      String steam = "";
//      if(principal instanceof User)
//      {
//          User user1 = (User) principal;
//          steam = user1.getSteamId();
//      }
      return "LOL";
    }

    /**
     * If and only if registration is successful, we want to use the API and preload all the games from the users steam library into
     * our application.
     */
    public void preload(User user) throws IOException, SteamApiException {
        if(user.getBattlenet() != null && !user.getBattlenet().equals(""))
        {
            loadCallOfDuty(user);
            System.out.println(user.getBattlenet());
        }
        if(user.getSteamId() != null && !user.getSteamId().equals(""))
        {
          loadCSGO(user);
          loadTF2(user);
        }
        if(user.getRunescapeDisplayName() != null && !user.getRunescapeDisplayName().equals(""))
        {
            loadRunescape(user);
        }
        if(user.getPubGPlayerName() != null && !user.getPubGPlayerName().equals(""))
        {
            loadPubG(user);
        }

    }



    private void loadCSGO(User user) throws SteamApiException {
        GeneratorImpl generator = new GeneratorImpl(user);
        UserGame userGame = new UserGame(user,userDAO.getGameRepository().findByName("Counter Strike Global Offensive"));
        System.out.println(userGame.getGame().getName());
        userGame.setTotalKills(generator.csgoTotalKills());
        userGame.setTotalHoursPlayed(generator.csgoTotalHours());
        userGame.setTotalDeaths(generator.csgoTotalDeaths());
        userGame.setKdr(generator.csgoKDR());
        userDAO.getUserGameRepository().save(userGame);
        userDAO.getUserRepository().save(user);
    }

    private void loadCallOfDuty(User user) throws IOException {
        UserGame userGame = new UserGame(user,userDAO.getGameRepository().findByName("CODMW"));
        System.out.println(userGame.getGame().getName());
        userGame.setModernWafareTotalTimePlayed(new CODMW(user.getBattlenet()).resolveTotalTimePlayer());

      userGame.setModernWarfareKDR(new CODMW(user.getBattlenet()).resolveMultiplayerKDR());
      userGame.setModernWarfareWarZoneWins(new CODMW(user.getBattlenet()).resolveTotalWarzoneWins());
      userGame.setModernwWarfareTotalWarzoneGames(new CODMW(user.getBattlenet()).resolveTotalWarzoneGames());
      userGame.setModernWarfareWarzoneKDR(new CODMW(user.getBattlenet()).resolveKDRWarzone());
        user.getGames().add(userGame);
        userDAO.getUserGameRepository().save(userGame);
        userDAO.getUserRepository().save(user);
    }

    private void loadRunescape(User user) throws IOException {
        UserGame userGame = new UserGame(user,userDAO.getGameRepository().findByName("Runescape"));
        userGame.setRunescapeStats(new Runescape(user.getRunescapeDisplayName()).resolveAllStats());
        user.getGames().add(userGame);
        userDAO.getUserGameRepository().save(userGame);
        userDAO.getUserRepository().save(user);
        System.out.println("Added due to github errors");
    }

    private void loadTF2(User user) throws SteamApiException {
        UserGame userGame = new UserGame(user,userDAO.getGameRepository().findByName("TeamFortress 2"));
        userGame.setTeamFortress2Kills(new GeneratorImpl(user).TeamFortressTwoKills());
        userGame.setTeamFortress2TimePlayed(new GeneratorImpl(user).totalTeamFortressHours());
        user.getGames().add(userGame);
        userDAO.getUserGameRepository().save(userGame);
        userDAO.getUserRepository().save(user);
    }

    private void loadPubG(User user) throws SteamApiException, IOException {
        UserGame userGame = new UserGame(user,userDAO.getGameRepository().findByName("PUBG"));
        userGame.setPubGTop10s(new PUBG(user.getPubGPlayerName()).resolveTop10s());
        userGame.setPubgWins(new PUBG(user.getPubGPlayerName()).resolveWins());
        user.getGames().add(userGame);
        userDAO.getUserGameRepository().save(userGame);
        userDAO.getUserRepository().save(user);
    }







}
