package com.lOnlyGames.backend;

import com.lOnlyGames.backend.Service.MatchesService;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/matches") // This means URL's start with /user (after Application path)

public class MatchesController {

    @Autowired
    private MatchesService matchesService;

    @GetMapping("/findGamers")
    //finds other users who like the same games as our current user
    //who is searching for matches
    public @ResponseBody List<List<UserGame>> getMatches(@RequestBody User user){
        return matchesService.getMatches(user);
    }
}