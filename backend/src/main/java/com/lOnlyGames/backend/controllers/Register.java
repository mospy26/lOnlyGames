package com.lOnlyGames.backend.controllers;

import com.lOnlyGames.backend.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class Register {

    @PostMapping(path = "/Register")
    public @ResponseBody String registerUser(@RequestParam String Fname, @RequestParam String Lname, @RequestParam String email, @RequestParam String password)
    { return "TO-DO";}




}

