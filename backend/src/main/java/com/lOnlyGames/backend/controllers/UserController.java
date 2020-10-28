package com.lOnlyGames.backend.controllers;

import java.util.List;

import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.response.AllBlockedResponse;
import com.lOnlyGames.backend.response.BlockedResponse;
import com.lOnlyGames.backend.response.MatchesResponse;
import com.lOnlyGames.backend.response.UsersListResponse;
import com.lOnlyGames.backend.services.BlockedService;
import com.lOnlyGames.backend.services.MatchesService;
import com.lOnlyGames.backend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //HIGH PRIORITY
    //finds other users who like the same games as our current user
    //who is searching for matches
    @GetMapping(value = "/matches")
    public ResponseEntity<?> getMatches(@RequestParam User user) {
        List<List<UserGame>> matches = matchesService.getMatches(user);
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
    public String getUserDetails(@RequestParam String username)
    {
        return "Return the instance of the user that is logged in";
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> dynamicSearch(@RequestParam String username)
    {
        return new ResponseEntity<UsersListResponse>(new UsersListResponse(userService.getUsersWithNameLike(username)), HttpStatus.OK);
    }

    // MEDIUM
    @PostMapping(value = "/like")
    public String likeUser(@RequestBody User toLike) {return "Like this user";}



    @PutMapping(value = "/update")
    public String update(@RequestBody User user)
    {
        return "Update the details of this user";
    }

    @PostMapping(value = "/dislike")
    public String dislikeUser(@RequestBody User dislikeUser)
    {
        return "Dislikeuser";
    }

    @GetMapping(value = "/liked")
    public String getAllLikes(){return "Everyone this user has liked";}








}
