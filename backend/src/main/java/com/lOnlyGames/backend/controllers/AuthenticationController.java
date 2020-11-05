package com.lOnlyGames.backend.controllers;

import java.io.IOException;
import java.util.Map;

import com.lOnlyGames.backend.auth.JwtTokenUtil;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidUsernameException;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.response.JwtTokenResponse;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/auth")
public class AuthenticationController {
    
    @Autowired
    com.lOnlyGames.backend.services.UserService userService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @PostMapping(value="/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) throws InvalidCredentialsException, IOException, SteamApiException {
        if (!payload.containsKey("username") || !payload.containsKey("password")) throw new InvalidCredentialsException("Username or password is missing");

        String username = payload.get("username");
        String password = payload.get("password");
        userService.authenticate(username, password);
        return new ResponseEntity<JwtTokenResponse>(generateTokenResponse(username), HttpStatus.OK);
    }

    @PostMapping(value="/register")
    public ResponseEntity<?> register(@RequestBody User user) throws InvalidUsernameException, IOException, SteamApiException {
        userService.register(user);
        return new ResponseEntity<JwtTokenResponse>(generateTokenResponse(user.getUsername()), HttpStatus.OK);
    }

    private JwtTokenResponse generateTokenResponse(String username) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        JwtTokenResponse token = new JwtTokenResponse(jwtTokenUtil.generateToken(userDetails));
        return token;
    }
}
