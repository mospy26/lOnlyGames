package com.lOnlyGames.backend.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/dashboard")
public class Dashboard {

    @RequestMapping(value = "/allUsers")
    public @ResponseBody String getAllUsers()
    {
        return "Not sure if we would want to return all users here but here it is none the less.";
    }

    @RequestMapping(value = "/allUserBios")
    public @ResponseBody String getAllUserBios()
    {
        return "All the user bios to be displayed in the view";
    }

    @RequestMapping(value = "/AllAvatars")
    public @ResponseBody String getAllAvatars()
    {
        return "All User Avatars";
    }



}
