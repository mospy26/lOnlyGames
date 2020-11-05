package com.lOnlyGames.backend.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.lOnlyGames.backend.DAO.AvailabilityDAO;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.response.*;
import com.lOnlyGames.backend.services.*;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/api/v1/users")
public class UserController {

    @Autowired
    private MatchesService matchesService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private BlockedService blockedService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private GamesAPIService gamesAPIService;

    @Autowired
    private AvailabilityService availabilityService;

    //MATCHING RELATED FUNCTION IN CONTROLLER

    //finds other users who like the same games as our current user
    //who is searching for matches
    @GetMapping(value = "/matches")
    public ResponseEntity<?> getMatches() {
        List<List<UserGame>> matches = matchesService.getMatches();
        return new ResponseEntity<MatchesResponse>(new MatchesResponse(matches), HttpStatus.OK);
    }


    //BLOCKING RELATED FUNCTIONS IN CONTROLLER

    //blocks a specific user that the current user wants to block
    @PostMapping(value = "/block")
    public ResponseEntity<?> blockUser(@RequestBody User toBlock) throws UsernameNotFoundException {
        String blockedMsg = blockedService.blockUser(toBlock);
        return new ResponseEntity<BlockUnblockResponse>(new BlockUnblockResponse(blockedMsg), HttpStatus.OK);

    }

    //unblocks a specific user that the current user wants to unblock
    @PostMapping(value = "/unblock")
    public ResponseEntity<?> unblockUser(@RequestBody User toUnblock) throws UsernameNotFoundException {
        String unblockMsg = blockedService.unblockUser(toUnblock);
        return new ResponseEntity<BlockUnblockResponse>(new BlockUnblockResponse(unblockMsg), HttpStatus.OK);
    }

    //gets all users who have been blocked by the current user
    @GetMapping(value = "/users-blocked")
    public ResponseEntity<?> getAllBlockedUsers() throws InvalidCredentialsException {
        List<User> blockedList = blockedService.allBlockedByUser();
        return new ResponseEntity<AllBlockedResponse>(new AllBlockedResponse(blockedList), HttpStatus.OK);
    }


    //HIGH Priority
    @GetMapping(value = "")
    public ResponseEntity<?> getUserDetails()
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<UserResponse>(new UserResponse(user), HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> dynamicSearch(@RequestParam String username)
    {
        return new ResponseEntity<UsersListResponse>(new UsersListResponse(userService.getUsersWithNameLike(username)), HttpStatus.OK);
    }

    //LIKING RELATED FUNCTIONS IN CONTROLLER

    //the current user likes another user
    @PostMapping(value = "/like")
    public ResponseEntity<?> likeUser(@RequestBody User toLike)
    {
        String likedMsg = likeService.likeUser(toLike);
        return new ResponseEntity<LikeDislikeResponse>(new LikeDislikeResponse(likedMsg), HttpStatus.OK);
    }

    //the current user dislikes another user
    @PostMapping(value = "/dislike")
    public ResponseEntity<?> dislikeUser(@RequestBody User dislikeUser)
    {
        String dislikeMsg = likeService.dislikeUser(dislikeUser);
        return new ResponseEntity<LikeDislikeResponse>(new LikeDislikeResponse(dislikeMsg), HttpStatus.OK);
    }

    //get all users this person has liked
    @GetMapping(value = "/users-liked")
    public ResponseEntity<?> getAllLikes(){
        List<User> likedUsers = likeService.getAllLikes();
        return new ResponseEntity<AllLikesResponse>(new AllLikesResponse(likedUsers), HttpStatus.OK);
    }

    //get a user's profile
    @GetMapping(value="/profile")
    public ResponseEntity<?> getProfile(@RequestParam String username){
        User user = userService.getProfile(username);
        return new ResponseEntity<UserResponse>(new UserResponse(user), HttpStatus.OK);
    }
    //update a user's profile
    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody Map<String, String> payload)
    {
        User user = userService.updateUser(payload);
        return new ResponseEntity<UserResponse>(new UserResponse(user), HttpStatus.OK);
    }

    @PostMapping(value = "/report")
    public ResponseEntity<?> report(@RequestBody User toReport) {
        String reportMsg = userService.reportUser(toReport);
        return new ResponseEntity<ReportResponse>(new ReportResponse(reportMsg), HttpStatus.OK);

    }

    @PostMapping(value = "/fetch")
    public ResponseEntity<?>  fetch() throws IOException, SteamApiException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        gamesAPIService.poll(user);
       return new ResponseEntity<FetchGameDataResponse>(new FetchGameDataResponse("Fetched Games Data for " + user.getUsername()),HttpStatus.OK);
    }

    @PostMapping(value = "/availability/add")
    public ResponseEntity<?> availability(@RequestBody Availability availability) {
        String availabilityMsg = availabilityService.addAvailability(availability);
        return new ResponseEntity<AvailabilityResponse>(new AvailabilityResponse(availabilityMsg), HttpStatus.OK);
    }

    @PostMapping(value = "/availability/remove")
    public ResponseEntity<?> removeAvailability(@RequestBody Availability availability) {
        String availabilityMsg = availabilityService.removeAvailability(availability);
        return new ResponseEntity<AvailabilityResponse>(new AvailabilityResponse(availabilityMsg), HttpStatus.OK);
    }
}
