package com.lOnlyGames.backend.controllers;

import com.lOnlyGames.backend.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
   // All requests to the login controller will now start with login
@RequestMapping(value = "/api/v1/login")
public class LoginController {
    //By default mapping in Spring is GET


    /*
    Cant this test this method until User class has been finalized.
     */
    @PostMapping("")
    public String loginRoot(@RequestBody User user) {return "TO-DO";}



}
