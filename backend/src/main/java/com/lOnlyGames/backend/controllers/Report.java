package com.lOnlyGames.backend.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/*
Other than report a user, I can't think of anything.
 */

@RequestMapping(value = "/api/v1/report")
@RestController
public class Report {

    @PostMapping(value = "/reportUser")
    public @ResponseBody String reportUser()
    { return "TO-DO: reports a user";}


    @RequestMapping (value = "/reports")
    public @ResponseBody String getReports()
    {
        return " TO-DO : takes a user as the parameter and returns everyone who reported them";
    }



}
