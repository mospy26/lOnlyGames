package com.lOnlyGames.backend.controllers;

import com.lOnlyGames.backend.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/register")
public class RegisterController {

    @PostMapping(path = "")
    public @ResponseBody String registerUser(@RequestBody User user)
    { return "TO-DO";}






}

