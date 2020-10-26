package com.lOnlyGames.backend.services;

import com.lOnlyGames.backend.DAO.LikeDAO;
import com.lOnlyGames.backend.model.Liked;
import com.lOnlyGames.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LikeService {

    @Autowired
    private LikeDAO likeDAO;

    public String likeUser(User toLike) throws UsernameNotFoundException{
        //User who is doing the liking
        try{
            //User doing the liking
            User liking = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //User who is going to be liked
            User liked = likeDAO.getUserRepository().findById(toLike.getUsername()).get();

            //check to see if user has been liked already
            if(likeDAO.getLikedRepository().findByLikerAndLikes(liking, liked) != null){
                return "You have already liked" + liked.getUsername();
            }

            //create a new liked object
            Liked likeObj = new Liked(liking, liked);

            //saves the liked item to the db
            likeDAO.likeUser(likeObj);

            return "User '" + liked.getUsername() + "' has been liked.";

        } catch(Exception e){
            //i think this is the correct error to return
            throw new UsernameNotFoundException("Username '" + toLike.getUsername() + "' is invalid");
        }
    }


    public String dislikeUser(User toDislike) throws UsernameNotFoundException{
        //User who is doing the disliking
        try{
            //User doing the disliking
            User disliking = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //User who is going to be disliked
            User disliked = likeDAO.getUserRepository().findById(toDislike.getUsername()).get();

            Liked likedObj = likeDAO.getLikedRepository().findByLikerAndLikes(disliking,disliked);

            //check to see if user has been liked already
            if(likedObj != null)
            {
                likeDAO.removeLike(likedObj);
                return toDislike.getUsername() + " has been disliked.";
            }
            else {
                return "You cannot dislike a user you have not liked.";
            }

        } catch(Exception e){
            //i think this is the correct error to return
            throw new UsernameNotFoundException("Username '" + toDislike.getUsername() + "' is invalid.");
        }
    }
}
