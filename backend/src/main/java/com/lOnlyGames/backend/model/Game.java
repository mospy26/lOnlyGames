package com.lOnlyGames.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Game {
    @Id
    private String name;
    private String iconURL;
    private String apiURL;
}
