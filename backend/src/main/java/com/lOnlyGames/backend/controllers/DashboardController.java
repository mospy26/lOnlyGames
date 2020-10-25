package com.lOnlyGames.backend.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/dashboard")
public class DashboardController {

    @RequestMapping(value ="/{userid}/recommendedmatches/")
    public @ResponseBody String getRecommendedMatches(@PathVariable("userid") String username)
    {
        return "recommended matches for user";
    }






}
