package com.lOnlyGames.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Game {
    @Id
    private String name;
    private String iconURL;
    private String apiURL;

    @OneToMany(mappedBy = "game")
    private Set<UserGame> game;
}
