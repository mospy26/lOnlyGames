package com.lOnlyGames.backend.controllers;


import com.lOnlyGames.backend.model.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/filter")
public class FilterController {



    @RequestMapping("/game/{game}")
    public @ResponseBody String filterByGame(@PathVariable String game)
    {
      return "All users that play this game.";
    }

    @RequestMapping("/avalability")
    public @ResponseBody String filterByAval(@RequestParam String avaliability )
    {
        return "All users that are avaliable at these times";
    }

    @RequestMapping("/game/{gameName}/kdr/")
    public @ResponseBody String filterByKDR(@PathVariable("gameName") String gameName,@RequestParam double kdrMin, @RequestParam double kdrMax )
    {
        return "All users that play specific game and fall within a specific kdr range";
    }

    @RequestMapping("/game/{gameName}/hours")
    public @ResponseBody String filterByHours(@PathVariable String gameName, @RequestParam int minHours, @RequestParam int MaxHours)
    {
        return "All users that play a specific game have atleast X amount of hours but not greater than Y amount of hours";
    }

    @RequestMapping("/matches/{gameName}")
    public @ResponseBody String gamesPlayed(@PathVariable("gameName") String game, @RequestBody User user)
    {
        return "All matches that the user has, and show matches that only play a specific game";
    }
    @RequestMapping("/location/")
    public @ResponseBody String filterByLocation(@RequestParam String location)
    {
        return "All users within the same location";
    }
    @RequestMapping("/{username}/}")
    public @ResponseBody String filterByUsername(@PathVariable("username") String username)
    {
        return "get user with specific username";
    }

    @RequestMapping("/discord/}")
    public @ResponseBody String findUserbyDiscord(@RequestParam String discordID)
    {
        return "User with specific discord id";
    }
    @RequestMapping("/steam/}")
    public @ResponseBody String findUserbySteam(@RequestParam String steamID)
    {
        return "User with specific steam id";
    }



}
