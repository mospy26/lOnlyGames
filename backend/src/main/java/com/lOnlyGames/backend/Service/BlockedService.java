package com.lOnlyGames.backend.Service;

import com.lOnlyGames.backend.DAO.BlockedDAO;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class BlockedService {

    @Autowired
    private BlockedDAO blockedDAO;

    public List<String> allBlockedByUser(User user){
        try{
            List<Blocked> blockedUsers = blockedDAO.getBlockedRepository().findByBlocker(user);
            var listBlocks = new ArrayList<String>();
            for(Blocked blocked:blockedUsers)
            {
                listBlocks.add(blocked.getBlockee().getUsername());
            }
            return listBlocks;
        } catch(NoSuchElementException e){
            var error = new ArrayList<String>();
            error.add("User does not exist. Please enter a different username.");
            return error;
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
