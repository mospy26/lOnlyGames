package com.lOnlyGames.backend.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/user")
public class UserController {

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


    @RequestMapping(value = "/discord_id")
    public @ResponseBody String getDiscordID()
    {
        return "User Discord ID(Should be Long)";
    }
    @RequestMapping(value = "/steam_id/")
    public @ResponseBody String getSteamID()
    {
        return "User Discord ID(Should be Long)";
    }

    @RequestMapping(value = "/played_games")
    public @ResponseBody String getGamesPlayed()
    {
        return "Return all games the user plays";
    }

    @RequestMapping(value = "/location")
    public @ResponseBody String getLocation()
    {
        return "Location";
    }

    @RequestMapping(value = "/blocked_by")
    public @ResponseBody String getBlockedBy()
    {
        return "The users that block THIS User";
    }

    @RequestMapping(value = "/blocked")
    public @ResponseBody String getBlocked()
    {
        return "The users that THIS user has BLOCKED";
    }

    @RequestMapping(value = "/get_aval")
    public  @ResponseBody String getAval()
    {
        return "The users avalabilities";
    }


    @RequestMapping(value = "/get_bio")
    public  @ResponseBody String getBio()
    {
        return "The users Bio";
    }

    @RequestMapping(value = "/get_reports")
    public  @ResponseBody String getReports()
    {
        return "The users Reports";
    }

    @PostMapping(value = "/update/firstName")

    public @ResponseBody String updateFName()
    {
        return "Update the users firstname";
    }

    @PostMapping(value = "/update/lastName")
    public @ResponseBody String updateLastName()
    {
        return "Update the users firstname";
    }

    @PostMapping(value = "/update/discordID")
    public @ResponseBody String updateDiscordID()
    {
        return "Update the users discordID";
    }

    @PostMapping(value = "/update/steamid")
    public @ResponseBody String updateSteamID()
    {
        return "Update the users firstname";
    }

    @PostMapping(value = "/update/bio")
    public @ResponseBody String updateBio()
    {
        return "Update the users Bio";
    }


    @RequestMapping(value = "/steam/all_played")
    public @ResponseBody String allPlayedGames()
    {
        return "All the games the user has played";
    }
    @RequestMapping(value = "/steam/csgo/total_kills")
    public @ResponseBody String totalKillsCSGO()
    {
        return "Use STEAM API and fetch total kill in CSGO";
    }
    @RequestMapping(value = "/steam/csgo/kdr")
    public @ResponseBody String calculateCSGOKDR()
    {
        return "Kill Death Ratio of Player";
    }


    @RequestMapping(value = "/steam/csgo/total_hours")
    public @ResponseBody String getCSGOHours()
    {
        return "Get Total Hours Played";
    }
    @RequestMapping(value = "/steam/dota/total_hours")
    public @ResponseBody String getDotaHours()
    {
        return "Get Total Hours Played";
    }
    @RequestMapping(value = "/steam/rb6/total_hours")
    public @ResponseBody String getRB6Hours()
    {
        return "Get Total Hours Played";
    }



    @RequestMapping(value = "/steam/dota/total_kills")
    public @ResponseBody String getTotalKillsDota()
    {
        return "Use STEAM API and fetch total kill in CSGO";
    }


    @RequestMapping(value = "/steam/dota/kdr")
    public @ResponseBody String calculateDotaKDR()
    {
        return "Kill Death Ratio of Player";
    }


    @RequestMapping(value = "/steam/rb6/total_kills")
    public @ResponseBody String getTotalKillsRb6()
    {
        return "Use STEAM API and fetch total kill in CSGO";
    }


    @RequestMapping(value = "/steam/rb6/kdr")
    public @ResponseBody String calculateRb6KDR()
    {
        return "Kill Death Ratio of Player";
    }


    @PostMapping(value = "/matches/unmatch/{userid}")
   public @ResponseBody String unmatchUser(@PathVariable("userid") String username)
{
    return "Umatch user based on userid";
}











}
