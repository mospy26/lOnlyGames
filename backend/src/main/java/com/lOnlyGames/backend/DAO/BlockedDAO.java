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

    public BlockedRepository getBlockedRepository() {
        return blockedRepository;
    }
    public UserRepository getUserRepository(){
        return userRepository;
    }
    public void blockUser(Blocked block){
        blockedRepository.save(block);
    }
}
