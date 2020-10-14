package com.lOnlyGames.backend.DAO;

import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.repository.BlockedRepository;
import com.lOnlyGames.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class BlockedDAO {

    @Autowired
    private BlockedRepository blockedRepository;

    @Autowired
    private UserRepository userRepository;

    public List<String> allBlockedByUser(String username){
        try{
            List<Blocked> blockedUsers = blockedRepository.findByBlocker(userRepository.findById(username).get());
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

    public String blockUser(String blkr_username, String blkee_username){
        //User who is doing the blocking
        try{
            //User doing the blocking
            User blocker = userRepository.findById(blkr_username).get();
            //User who has been blocked
            User blockee = userRepository.findById(blkee_username).get();

            Blocked block = new Blocked(blocker, blockee);

            //saves the blocked item to the db
            blockedRepository.save(block);
            return blkee_username + " has been blocked.";
        } catch(NoSuchElementException e){
            return "Blocker or blockee missing from database. Please try again.";
        }
    }
}
