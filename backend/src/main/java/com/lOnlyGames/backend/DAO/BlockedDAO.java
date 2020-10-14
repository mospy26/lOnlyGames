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

    public List<String> allBlockedByUser(User user){
        try{
            List<Blocked> blockedUsers = blockedRepository.findByBlocker(user);
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
            User blocker = userRepository.findById(block.getBlocker().getUsername()).get();
            //User who has been blocked
            User blockee = userRepository.findById(block.getBlockee().getUsername()).get();
            //saves the blocked item to the db
            blockedRepository.save(block);
            return block.getBlockee().getUsername() + " has been blocked.";
        } catch(NoSuchElementException e){
            return "Blocker or blockee missing from database. Please try again.";
        }
    }
}
