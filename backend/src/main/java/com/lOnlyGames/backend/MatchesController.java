package com.lOnlyGames.backend;

import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.response.MatchesResponse;
import com.lOnlyGames.backend.services.MatchesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/matches") // This means URL's start with /user (after Application path)

public class MatchesController {

    @Autowired
    private MatchesService matchesService;

    @GetMapping("")
    //finds other users who like the same games as our current user
    //who is searching for matches
    public @ResponseBody ResponseEntity<?> getMatches(@RequestParam(value = "username") User user){
        List<List<UserGame>> matches = matchesService.getMatches(user);
        return new ResponseEntity<MatchesResponse>(new MatchesResponse(matches), HttpStatus.OK);
    }
}