package com.lOnlyGames.backend.model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Game {
    @Id
    private String name;


    private String iconURL;
    private String apiURL;

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

    public String getApiURL() {
        return apiURL;
    }

    public void setApiURL(String apiURL) {
        this.apiURL = apiURL;
    }
}
