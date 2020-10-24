package com.lOnlyGames.backend;

import java.util.List;

import com.lOnlyGames.backend.response.*;
import com.lOnlyGames.backend.services.UserService;
import com.lOnlyGames.backend.auth.JwtTokenUtil;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidUsernameException;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.repository.BlockedRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="")
public class AuthenticationController {

    @Autowired
    BlockedRepository blocked;
    
    @Autowired
    com.lOnlyGames.backend.services.UserService userService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @PostMapping(value="/authenticate")
    public ResponseEntity<?> login(@RequestBody User user) throws InvalidCredentialsException {
        authenticate(user.getUsername(), user.getPassword());
        return new ResponseEntity<JwtTokenResponse>(generateTokenResponse(user.getUsername()), HttpStatus.OK);
    }

    @PostMapping(value="/register")
    public ResponseEntity<?> register(@RequestBody User user) throws InvalidUsernameException {
        userService.register(user);
        return new ResponseEntity<JwtTokenResponse>(generateTokenResponse(user.getUsername()), HttpStatus.OK);
    }

    // Example function, needs to be deleted later on
    @GetMapping("/hello")
    public String hello() throws Exception {
        User us = userService.getUser("mustafa");
        List<Blocked> b = blocked.findByBlocker(us);
        return b.toString();
    }

    private void authenticate(String username, String password) throws InvalidCredentialsException {
        userService.authenticate(username, password);
    }

    private JwtTokenResponse generateTokenResponse(String username) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        JwtTokenResponse token = new JwtTokenResponse(jwtTokenUtil.generateToken(userDetails));
        return token;
    }
}
