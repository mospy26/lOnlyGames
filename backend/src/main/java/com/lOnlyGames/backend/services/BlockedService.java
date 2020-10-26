package com.lOnlyGames.backend.services;

import com.lOnlyGames.backend.DAO.BlockedDAO;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.utilities.Generator;
import com.lOnlyGames.backend.utilities.GeneratorImpl;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class BlockedService {



    @Autowired
    private BlockedDAO blockedDAO;

    public BlockedService() throws SteamApiException {
    }


    public List<Blocked> allBlockedByUser(User user){
        try{
            return blockedDAO.getBlockedRepository().findByBlocker(user);
        } catch(NoSuchElementException e){
            return null;
        }
    }

    public String blockUser(Blocked block){
        //User who is doing the blocking
        try{
            //User doing the blocking
            User blocker = blockedDAO.getUserRepository().findById(block.getBlocker().getUsername()).get();
            //User who has been blocked
            User blockee = blockedDAO.getUserRepository().findById(block.getBlockee().getUsername()).get();
            //create a new Blocked object
            Blocked blockObj = new Blocked(blocker, blockee);

            //saves the blocked item to the db
            blockedDAO.blockUser(blockObj);

            return block.getBlockee().getUsername() + " has been blocked.";
        } catch(NoSuchElementException e){
            return "Blocker or blockee missing from database. Please try again.";
        }
    }
}
