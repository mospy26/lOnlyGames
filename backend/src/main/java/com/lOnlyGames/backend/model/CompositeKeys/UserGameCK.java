package com.lOnlyGames.backend.model.CompositeKeys;

import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;

import javax.persistence.Embeddable;
import java.io.Serializable;

public class UserGameCK implements Serializable {

    private String user; // Must have the same name AND same PK type as the connected entity
    private String game;

    public UserGameCK() {}
    public UserGameCK(String user, String game) {
        this.user = user;
        this.game = game;
    }
}
