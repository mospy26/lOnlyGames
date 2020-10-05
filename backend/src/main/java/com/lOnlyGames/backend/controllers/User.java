package com.lOnlyGames.backend.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/user")
public class User {

    @RequestMapping(value = "/getAvatar")
    public @ResponseBody String getUserAvatar()
    {
       return "To-DO";
    }

    @RequestMapping(value = "/userLikedBy")
    public @ResponseBody String getLikedBy()
    {
        return "return the likedBy";
    }

    @RequestMapping(value = "/userLikes")
    public @ResponseBody String getUserLikes()
    { return "return the user likes"; }


    @RequestMapping(value = "/discordID")
    public @ResponseBody String getDiscordID()
    {
        return "User Discord ID(Should be Long)";
    }
    @RequestMapping(value = "/steamID/")
    public @ResponseBody String getSteamID()
    {
        return "User Discord ID(Should be Long)";
    }

    @RequestMapping(value = "/playedGames")
    public @ResponseBody String getGamesPlayed()
    {
        return "Return all games the user plays";
    }

    @RequestMapping(value = "/location")
    public @ResponseBody String getLocation()
    {
        return "Location";
    }

    @RequestMapping(value = "/blockedBy")
    public @ResponseBody String getBlockedBy()
    {
        return "The users that block THIS User";
    }

    @RequestMapping(value = "/blocked")
    public @ResponseBody String getBlocked()
    {
        return "The users that THIS user has BLOCKED";
    }

    @RequestMapping(value = "/getAval")
    public  @ResponseBody String getAval()
    {
        return "The users avalabilities";
    }


    @RequestMapping(value = "/getBio")
    public  @ResponseBody String getBio()
    {
        return "The users Bio";
    }

    @RequestMapping(value = "/getReports")
    public  @ResponseBody String getReports()
    {
        return "The users Reports";
    }

    @PostMapping(value = "/update/FirstName")

    public @ResponseBody String updateFName()
    {
        return "Update the users firstname";
    }

    @PostMapping(value = "/update/LastName")
    public @ResponseBody String updateLastName()
    {
        return "Update the users firstname";
    }

    @PostMapping(value = "/update/DiscordID")
    public @ResponseBody String updateDiscordID()
    {
        return "Update the users discordID";
    }

    @PostMapping(value = "/update/SteamID")
    public @ResponseBody String updateSteamID()
    {
        return "Update the users firstname";
    }

    @PostMapping(value = "/update/Bio")
    public @ResponseBody String updateBio()
    {
        return "Update the users Bio";
    }








}
