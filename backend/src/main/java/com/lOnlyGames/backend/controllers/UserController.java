package com.lOnlyGames.backend.controllers;

import java.util.List;
import java.util.Map;

import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.response.*;
import com.lOnlyGames.backend.services.BlockedService;
import com.lOnlyGames.backend.services.LikeService;
import com.lOnlyGames.backend.services.MatchesService;
import com.lOnlyGames.backend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1/users")
public class UserController {

    @Autowired
    private MatchesService matchesService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private BlockedService blockedService;

    @Autowired
    private LikeService likeService;

    //MATCHING RELATED FUNCTION IN CONTROLLER

    //finds other users who like the same games as our current user
    //who is searching for matches
    @GetMapping(value = "/matches")
    public ResponseEntity<?> getMatches() {
        List<List<UserGame>> matches = matchesService.getMatches();
        return new ResponseEntity<MatchesResponse>(new MatchesResponse(matches), HttpStatus.OK);
    }


    //BLOCKING RELATED FUNCTIONS IN CONTROLLER

    //blocks a specific user that the current user wants to block
    @PostMapping(value = "/block")
    public ResponseEntity<?> blockUser(@RequestBody User toBlock) throws UsernameNotFoundException {
        String blockedMsg = blockedService.blockUser(toBlock);
        return new ResponseEntity<BlockedResponse>(new BlockedResponse(blockedMsg), HttpStatus.OK);

    }
    //gets all users who have been blocked by the current user
    @GetMapping(value = "/users-blocked")
    public ResponseEntity<?> getAllBlockedUsers() throws InvalidCredentialsException {
        List<User> blockedList = blockedService.allBlockedByUser();
        return new ResponseEntity<AllBlockedResponse>(new AllBlockedResponse(blockedList), HttpStatus.OK);
    }


    //HIGH Priority
    @GetMapping(value = "")
    public ResponseEntity<?> getUserDetails()
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<UserResponse>(new UserResponse(user), HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> dynamicSearch(@RequestParam String username)
    {
        return new ResponseEntity<UsersListResponse>(new UsersListResponse(userService.getUsersWithNameLike(username)), HttpStatus.OK);
    }

    //LIKING RELATED FUNCTIONS IN CONTROLLER

    //the current user likes another user
    @PostMapping(value = "/like")
    public ResponseEntity<?> likeUser(@RequestBody User toLike)
    {
        String likedMsg = likeService.likeUser(toLike);
        return new ResponseEntity<LikeResponse>(new LikeResponse(likedMsg), HttpStatus.OK);
    }

    //the current user dislikes another user
    @PostMapping(value = "/dislike")
    public ResponseEntity<?> dislikeUser(@RequestBody User dislikeUser)
    {
        String dislikeMsg = likeService.dislikeUser(dislikeUser);
        return new ResponseEntity<DislikeResponse>(new DislikeResponse(dislikeMsg), HttpStatus.OK);
    }


    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody Map<String, String> payload)
    {
        UserDetails user = userService.updateUser(payload);
        return new ResponseEntity<UserResponse>(new UserResponse(user), HttpStatus.OK);
    }


    @GetMapping(value = "/liked")
    public String getAllLikes(){return "Everyone this user has liked";}








}
