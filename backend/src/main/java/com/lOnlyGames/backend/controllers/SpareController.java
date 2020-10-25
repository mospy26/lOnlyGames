

/*
Controller is redundant, don't use this, rather if there is any missing endpoints, just copy it over
to the UserController.java class.
 */
















//package com.lOnlyGames.backend.controllers;
//import com.lOnlyGames.backend.model.Blocked;
//import com.lOnlyGames.backend.model.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("api/v1/user")
//public class UserController {
//
//    @RequestMapping(value = "/getAvatar")
//    public @ResponseBody String getUserAvatar(@RequestBody User user)
//    {
//       return "To-DO";
//    }
//
//    @RequestMapping(value = "/userLikedBy")
//    public @ResponseBody String getLikedBy()
//    {
//        return "return the likedBy";
//    }
//
//    @RequestMapping(value = "/likes")
//    public @ResponseBody String getUserLikes(@RequestBody User user)
//    { return "return the user likes"; }
//
//
////    //Priority LOW
////    @RequestMapping(value = "/discord_id")
////    public @ResponseBody String getDiscordID(@RequestBody User user)
////    {
////        return "User Discord ID(Should be Long)";
////    }
////    //Priority LOW
////    @RequestMapping(value = "/steam_id/")
////    public @ResponseBody String getSteamID(@RequestBody User user)
////    {
////        return "User Discord ID(Should be Long)";
////    }
//
//    @RequestMapping(value = "/played-games")
//    public @ResponseBody String getGamesPlayed(@RequestBody User user)
//    {
//        return "Return all games the user plays";
//    }
//
//    @RequestMapping(value = "/location")
//    public @ResponseBody String getLocation(@RequestBody User user)
//    {
//        return "Location";
//    }
//
//    @RequestMapping(value = "/blocked-by")
//    public @ResponseBody String getBlockedBy(@RequestBody User user)
//    {
//        return "The users that block THIS User";
//    }
//
//    @RequestMapping(value = "/blocked")
//    public @ResponseBody String getBlocked(@RequestBody User user)
//    {
//        return "The users that THIS user has BLOCKED";
//    }
//
//    @RequestMapping(value = "/avaliabilities")
//    public  @ResponseBody String getAval(@RequestBody User user)
//    {
//        return "The users avalabilities";
//    }
//
//
//    @RequestMapping(value = "/bio")
//    public  @ResponseBody String getBio(@RequestBody User user)
//    {
//        return "The users Bio";
//    }
//
//    // Priority LOW
//    @RequestMapping(value = "/reports")
//    public  @ResponseBody String getReports(@RequestBody User user)
//    {
//        return "The users Reports";
//    }
//
//    @PostMapping(value = "/update/")
//
//    //Priority HIGH
//    public @ResponseBody String updateProfile(@RequestBody User user)
//    {
//        return "Update the users firstname";
//    }
//
//    @RequestMapping(value = "/steam/allplayed")
//    public @ResponseBody String allPlayedGames(@RequestBody User user)
//    {
//        return "All the games the user has played";
//    }
//    @RequestMapping(value = "/steam/csgo/total_kills")
//    public @ResponseBody String totalKillsCSGO(@RequestBody User user)
//    {
//        return "Use STEAM API and fetch total kill in CSGO";
//    }
//    @RequestMapping(value = "/steam/csgo/kdr")
//    public @ResponseBody String calculateCSGOKDR(@RequestBody User user)
//    {
//        return "Kill Death Ratio of Player";
//    }
//
//
//    @RequestMapping(value = "/steam/csgo/total_hours")
//    public @ResponseBody String getCSGOHours(@RequestBody User user)
//    {
//        return "Get Total Hours Played";
//    }
//    @RequestMapping(value = "/steam/dota/total_hours")
//    public @ResponseBody String getDotaHours(@RequestBody User user)
//    {
//        return "Get Total Hours Played";
//    }
//    @RequestMapping(value = "/steam/rb6/total_hours")
//    public @ResponseBody String getRB6Hours(@RequestBody User user)
//    {
//        return "Get Total Hours Played";
//    }
//
//
//
//    @RequestMapping(value = "/steam/dota/total_kills")
//    public @ResponseBody String getTotalKillsDota(@RequestBody User user)
//    {
//        return "Use STEAM API and fetch total kill in CSGO";
//    }
//
//
//    @RequestMapping(value = "/steam/dota/kdr")
//    public @ResponseBody String calculateDotaKDR(@RequestBody User user)
//    {
//        return "Kill Death Ratio of Player";
//    }
//
//
//    @RequestMapping(value = "/steam/rb6/total_kills")
//    public @ResponseBody String getTotalKillsRb6(@RequestBody User user)
//    {
//        return "Use STEAM API and fetch total kill in CSGO";
//    }
//
//
//    @RequestMapping(value = "/steam/rb6/kdr")
//    public @ResponseBody String calculateRb6KDR(@RequestBody User user)
//    {
//        return "Kill Death Ratio of Player";
//    }
//
//    @RequestMapping(value = "/remove_report/")
//    public @ResponseBody int removeReportByID(@RequestBody User user)
//    {
//        return 0;
//        // Decrement Report count;
//
//    }
//
//    @PostMapping(value = "/report-user")
//    public @ResponseBody int reportUser(@RequestBody User user)
//    {
//        //Increment this users report count.
//        return 0; }
//
//
//
//    @PostMapping(value = "/add")
//    public @ResponseBody String addUserFriend(@RequestBody User personToAdd)
//    {
//        return "To-Do";
//    }
//
//    @PostMapping("/block")
//    public @ResponseBody String blockUser(@RequestBody Blocked block){
//        return "TO-DO";
////        return blockedService.blockUser(block);
//    }
//
////    @RequestMapping("/filter/game/{game}")
////    public @ResponseBody String filterByGame(@PathVariable String game)
////    {
////        return "All users that play this game.";
////    }
////
////    @RequestMapping("/filter/avalability")
////    public @ResponseBody String filterByAval(@RequestParam String avaliability )
////    {
////        return "All users that are avaliable at these times";
////    }
//
////    @RequestMapping("/filter/game/{gameName}/kdr/")
////    public @ResponseBody String filterByKDR(@PathVariable("gameName") String gameName,@RequestParam double kdrMin, @RequestParam double kdrMax )
////    {
////        return "All users that play specific game and fall within a specific kdr range";
////    }
//
////    @GetMapping("/game/{gameName}/hours")
////    public @ResponseBody String filterByHours(@PathVariable String gameName, @RequestParam int minHours, @RequestParam int MaxHours)
////    {
////        return "All users that play a specific game have atleast X amount of hours but not greater than Y amount of hours";
////    }
//
////    @GetMapping("filter/matches/")
////    public @ResponseBody String gamesPlayed(@RequestParam User user)
////    {
////        return "All matches that the user has, and show matches that only play a specific game";
////    }
////    @GetMapping("filter/location/")
////    public @ResponseBody String filterByLocation(@RequestParam String location)
////    {
////        return "All users within the same location";
////    }
////    @RequestMapping("filter/{username}/")
////    public @ResponseBody String filterByUsername(@PathVariable("username") String username)
////    {
////        return "get user with specific username";
////    }
//
//    @RequestMapping("/discord/}")
//    public @ResponseBody String findUserbyDiscord(@RequestParam String discordID)
//    {
//        return "User with specific discord id";
//    }
//    @RequestMapping("/steam/}")
//    public @ResponseBody String findUserbySteam(@RequestParam String steamID)
//    {
//        return "User with specific steam id";
//    }
//
//
//
//
//
//
//}
