package com.lOnlyGames.backend.response;

import java.util.List;

import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.UserGame;

public class GamesResponse extends ParentResponse
{
    public GamesResponse(List<UserGame> result) {
        super(result);
    }
}
