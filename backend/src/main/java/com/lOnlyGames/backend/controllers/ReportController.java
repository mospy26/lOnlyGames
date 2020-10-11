package com.lOnlyGames.backend.controllers;


import com.lOnlyGames.backend.model.User;
import org.springframework.web.bind.annotation.*;


/*
Other than report a user, I can't think of anything.
 */

@RequestMapping(value = "/api/v1/report")
@RestController
public class ReportController {

    @PostMapping(value = "/report_user")
    public @ResponseBody String reportUser(@RequestBody User reporter, @RequestBody User reported)
    { return "TO-DO: reports a user";}


    @RequestMapping (value = "/reports")
    public @ResponseBody String getReports()
    {
        return " TO-DO : takes a user as the parameter and returns everyone who reported them";
    }

    @RequestMapping(value = "/report/{id}")
    public @ResponseBody String getReportByID(@PathVariable int id)
    {
        return "The report @ this id";
    }

    /*
    I feel like this should also go in the user controller.
     */
    @RequestMapping(value = "/remove_report/{userid}/{reportid}")
    public @ResponseBody String removeReportByID(@PathVariable("userid") Integer userid, @PathVariable("reportid") Integer reportid)
    {
        return "Remove specific report from user with specific id. ";

    }

    /*
    I feel like this should also go in the user controller.
     */
    @RequestMapping(value = "/removereports/{userid}")
    public @ResponseBody String removeAllReports(@PathVariable("userid") Integer userId)
    {
        return "Remove all the reports of this particular user";
    }


}
