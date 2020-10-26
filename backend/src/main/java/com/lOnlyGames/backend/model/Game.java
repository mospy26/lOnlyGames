package com.lOnlyGames.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Game {
    @Id
    private String name;
    private String iconURL;

    @OneToMany(mappedBy = "game", cascade = CascadeType.REMOVE)
    private Set<UserGame> users;

    public Game() { }
    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }
}
