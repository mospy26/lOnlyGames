package com.lOnlyGames.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting {
    @GetMapping
    public String greeting() {
        return "Helloworld 123456";
    }
}