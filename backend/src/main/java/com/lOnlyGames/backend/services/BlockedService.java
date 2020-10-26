package com.lOnlyGames.backend.services;

import java.util.List;

import com.lOnlyGames.backend.DAO.BlockedDAO;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class BlockedService {

    @Autowired
    private BlockedDAO blockedDAO;

    public List<Blocked> allBlockedByUser() throws InvalidCredentialsException {
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return blockedDAO.getBlockedRepository().findByBlocker(user);
        } catch(Exception e){
            //not sure here what kind of error to return
            throw new InvalidCredentialsException();
        }
    }

    public String blockUser(User user) throws UsernameNotFoundException{
        //User who is doing the blocking
        try{
            //User doing the blocking
            User blocker = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //User who has been blocked
            User blockee = blockedDAO.getUserRepository().findById(user.getUsername()).get();

            //check to see if user has been blocked already
            if(blockedDAO.getBlockedRepository().findByBlockerAndBlockee(blocker,blockee) != null){
                return "This user has already been blocked";
            }
            
            //create a new Blocked object
            Blocked blockObj = new Blocked(blocker, blockee);

            //saves the blocked item to the db
            blockedDAO.blockUser(blockObj);

            return "User '" + user.getUsername() + "' has been blocked.";

        } catch(Exception e){
            //i think this is the correct error to return
            throw new UsernameNotFoundException("Username '" + user.getUsername() + "' is invalid");
        }
    }
}
