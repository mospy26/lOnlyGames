package com.lOnlyGames.backend.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/dashboard")
public class DashboardController {

    @RequestMapping(value = "/users")
    public @ResponseBody String getAllUsers()
    {
        return "Not sure if we would want to return all users here but here it is none the less.";
    }

    @RequestMapping(value = "/userbios")
    public @ResponseBody String getAllUserBios()
    {
        return "All the user bios to be displayed in the view";
    }

    @RequestMapping(value = "/useravatars")
    public @ResponseBody String getAllAvatars()
    {
        return "All User Avatars";
    }

    @RequestMapping(value ="/{userid}/recommendedmatches/")
    public @ResponseBody String getRecommendedMatches(@PathVariable("userid") String username)
    {
        return "recommended matches for user";
    }






}
