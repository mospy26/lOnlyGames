package com.lOnlyGames.backend.model.CompositeKeys;

import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;

import javax.persistence.Embeddable;
import java.io.Serializable;

public class UserGameCK implements Serializable {

    private User user;
    private Game game;

}
