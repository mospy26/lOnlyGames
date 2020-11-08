package com.lOnlyGames.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import com.lOnlyGames.backend.DAO.BlockedDAO;
import com.lOnlyGames.backend.DAO.LikeDAO;
import com.lOnlyGames.backend.errorhandlers.exceptions.*;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class    BlockedService {

    @Autowired
    private BlockedDAO blockedDAO;
    @Autowired
    private LikeDAO likeDAO;

    public List<User> allBlockedByUser() throws InvalidCredentialsException {
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Blocked> blocked = blockedDAO.getBlockedRepository().findByBlocker(user);
            return blocked.stream().map(b -> b.getBlockee()).collect(Collectors.toList());
        } catch(Exception e){
            //not sure here what kind of error to return
            throw new InvalidCredentialsException();
        }
    }

    public List<User> allBlockedMe(){
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Blocked> blockedMe = blockedDAO.getBlockedRepository().findByBlockee(user);
            return blockedMe.stream().map(b -> b.getBlocker()).collect(Collectors.toList());
        } catch(Exception e){
            //not sure here what kind of error to return
            throw new InvalidCredentialsException();
        }
    }

    public String blockUser(User user) throws UsernameNotFoundException, UserAlreadyBlockedException{
        //User doing the blocking
        User blocker = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //User who has been blocked
        try{
            User blockee = blockedDAO.getUserRepository().findById(user.getUsername()).get();
        }catch(Exception e){
            //i think this is the correct error to return
            throw new UsernameNotFoundException("Username '" + user.getUsername() + "' is invalid");
        }
        User blockee = blockedDAO.getUserRepository().findById(user.getUsername()).get();

        //remove the like on a user if we are blocking them
        if(likeDAO.getLikedRepository().findByLikerAndLikes(blocker, blockee) != null){
            likeDAO.removeLike(likeDAO.getLikedRepository().findByLikerAndLikes(blocker, blockee));
        }
        //remove the like on a user if they like us and we want to block them
        if(likeDAO.getLikedRepository().findByLikerAndLikes(blockee, blocker) != null){
            likeDAO.removeLike(likeDAO.getLikedRepository().findByLikerAndLikes(blockee, blocker));
        }

        //for when a user tries to block themselves
        if(blocker.getUsername().equals(blockee.getUsername())){
            throw new CannotBlockSelfException();
        }
        //check to see if user has been blocked already
        if(blockedDAO.getBlockedRepository().findByBlockerAndBlockee(blocker,blockee) != null){
            throw new UserAlreadyBlockedException();
        }

        //create a new Blocked object
        Blocked blockObj = new Blocked(blocker, blockee);

        //saves the blocked item to the db
        blockedDAO.blockUser(blockObj);

        return "User '" + user.getUsername() + "' has been blocked.";

    }

    public String unblockUser(User user) throws UsernameNotFoundException, UserAlreadyUnblockedException {
        //User who is doing the blocking
        //User doing the unblocking
        User blocker = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //User to be unblocked
        try{
            User blockee = blockedDAO.getUserRepository().findById(user.getUsername()).get();
        }catch(Exception e){
            //i think this is the correct error to return
            throw new UsernameNotFoundException("Username '" + user.getUsername() + "' is invalid");
        }
        User blockee = blockedDAO.getUserRepository().findById(user.getUsername()).get();

        if(blocker.getUsername().equals(blockee.getUsername())){
            throw new CannotUnblockSelfException();
        }
        //check to see if user has been blocked already
        if(blockedDAO.getBlockedRepository().findByBlockerAndBlockee(blocker,blockee) != null){
            blockedDAO.unblockUser(blockedDAO.getBlockedRepository().findByBlockerAndBlockee(blocker, blockee));
            return "User '" + user.getUsername() + "' has been unblocked.";
        }
        else{
            throw new UserAlreadyUnblockedException();
        }
    }
}
