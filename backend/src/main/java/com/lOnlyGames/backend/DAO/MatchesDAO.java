package com.lOnlyGames.backend.DAO;

import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.repository.UserGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class MatchesDAO {

    @Autowired
    private UserGameRepository userGameRepository;

    public UserGameRepository getUserGameRepository(){
        return userGameRepository;
    }
}
