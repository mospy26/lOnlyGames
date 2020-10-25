package com.lOnlyGames.backend.controllers;

import com.lOnlyGames.backend.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController (value = "/users")
public class UserController {

    //HIGH PRIORITY
    @GetMapping(value = "/matches")
    public String getAllMatches()
    {
        return "all me matches";
    }

    //HIGH PRIORITY
    @PostMapping(value = "/block")
    public String blockUser(@RequestBody User toBlock)
    {
        return "Block me";
    }

    //LOW PRIORITY
    @GetMapping(value = "/users-blocked")
    public String getAllBlockedUsers()
    {
        return "All blocked users";
    }

    //HIGH Priority
    @GetMapping(value = "")
    public String getUserDetails(@RequestParam String username)
    {
        return "Return the instance of the user that is logged in";
    }

    @GetMapping(value = "/search/")
    public String dynamicSearch(@RequestParam String s)
    {
        return "Search";
    }

    // MEDIUM
    @PostMapping(value = "/like")
    public String likeUser(@RequestBody User toLike) {return "Like this user";}



    @PutMapping(value = "/update/")
    public String update(@RequestBody User user)
    {
        return "Update the details of this user";
    }

    @PostMapping(value = "/dislike/")
    public String dislikeUser(@RequestBody User dislikeUser)
    {
        return "Dislikeuser";
    }

    @GetMapping(value = "/liked")
    public String getAllLikes(){return "Everyone this user has liked";}








}
