package com.lOnlyGames.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class User {



    @RequestMapping(value = "getAllUsers")
    public @ResponseBody List<User> getALl()
    {
        return null;
    }
}
