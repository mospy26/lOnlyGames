package com.lOnlyGames.backend;

import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.repository.BlockedRepository;
import com.lOnlyGames.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlockedDAO {

    @Autowired
    private BlockedRepository blockedRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Blocked> allBlockedByUser(String username){
        List<Blocked> blockedUsers = blockedRepository.findByBlocker(userRepository.findById(username).get());
        return blockedUsers;

    }

    public String blockUser(String blkr_username, String blkee_username){
        //User who is doing the blocking
        User blocker = userRepository.findById(blkr_username).get();
        //User who has been blocked
        User blockee = userRepository.findById(blkee_username).get();

        Blocked block = new Blocked(blocker, blockee);

        //saves the blocked item to the db
        blockedRepository.save(block);
        return blkee_username + "has been blocked.";
    }
}
