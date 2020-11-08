package com.lOnlyGames.backend.services;

import com.lOnlyGames.backend.DAO.BlockedDAO;
import com.lOnlyGames.backend.DAO.LikeDAO;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.errorhandlers.exceptions.UserAlreadyDislikedException;
import com.lOnlyGames.backend.errorhandlers.exceptions.UserAlreadyLikedException;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.Liked;
import com.lOnlyGames.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LikeService {

    @Autowired
    private LikeDAO likeDAO;
    @Autowired
    private BlockedDAO blockedDAO;
    @Autowired
    private BlockedService blockedService;

    public String likeUser(User toLike) throws UsernameNotFoundException, UserAlreadyLikedException{
        //User doing the liking
        User liking = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //User who is going to be liked
        try{
            User liked = likeDAO.getUserRepository().findById(toLike.getUsername()).get();
        }catch(Exception e){
            throw new UsernameNotFoundException("Username '" + toLike.getUsername() + "' is invalid");
        }
        User liked = likeDAO.getUserRepository().findById(toLike.getUsername()).get();

        //remove the block on a user if we are liking them
        if(blockedDAO.getBlockedRepository().findByBlockerAndBlockee(liking, liked) != null){
            blockedDAO.unblockUser(blockedDAO.getBlockedRepository().findByBlockerAndBlockee(liking, liked));
        }

        //check to make sure a user cannot like themselves
        if(liking.getUsername().matches(liked.getUsername())){
            throw new IllegalArgumentException();
        }
        //check to see if user has been liked already
        if(likeDAO.getLikedRepository().findByLikerAndLikes(liking, liked) != null){
            throw new UserAlreadyLikedException();
        }

        //create a new liked object
        Liked likeObj = new Liked(liking, liked);

        //saves the liked item to the db
        likeDAO.likeUser(likeObj);

        return "User '" + liked.getUsername() + "' has been liked.";
    }


    public String dislikeUser(User toDislike) throws UsernameNotFoundException, UserAlreadyDislikedException{
        //User who is doing the disliking
        //User doing the disliking
        User disliking = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //User who is going to be disliked
        try{
            User disliked = likeDAO.getUserRepository().findById(toDislike.getUsername()).get();
        }catch(Exception e) {
            throw new UsernameNotFoundException("Username '" + toDislike.getUsername() + "' is invalid.");
        }
        User disliked = likeDAO.getUserRepository().findById(toDislike.getUsername()).get();

        if(disliking.getUsername().matches(disliked.getUsername())){
            throw new IllegalArgumentException();
        }

        Liked likedObj = likeDAO.getLikedRepository().findByLikerAndLikes(disliking,disliked);

        //check to see if user has been liked already
        if(likedObj != null)
        {
            likeDAO.removeLike(likedObj);
            return toDislike.getUsername() + " has been disliked.";
        }
        else {
            throw new UserAlreadyDislikedException();
        }

    }

    public List<User> getAllLikes(){
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Liked> liked = likeDAO.getLikedRepository().findByLiker(user);

            List<User> usersBlockedMe = blockedService.allBlockedMe();
            for(Liked like: liked){
                for(User users: usersBlockedMe){
                    if(like.getLikes().equals(users)){
                        liked.remove(like);
                    }
                }
            }
            return liked.stream().map(l -> l.getLikes()).collect(Collectors.toList());
        } catch(Exception e){
            //not sure here what kind of error to return
            throw new InvalidCredentialsException();
        }
    }

    public List<User> getAllLikeed(){
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Liked> likeed = likeDAO.getLikedRepository().findByLikes(user);

            List<User> usersBlockedMe = blockedService.allBlockedMe();
            for(Liked like: likeed) {
                for(User users: usersBlockedMe){
                    if(like.getLikes().equals(users)){
                        likeed.remove(like);
                    }
                }
            }
            return likeed.stream().map(l -> l.getLiker()).collect(Collectors.toList());
        } catch(Exception e){
            //not sure here what kind of error to return
            throw new InvalidCredentialsException();
        }
    }
}
