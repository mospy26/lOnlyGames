package com.lOnlyGames.backend.repository;

import com.lOnlyGames.backend.model.CompositeKeys.UserGameCK;
import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserGameRepository extends CrudRepository<UserGame, UserGameCK> {
    public UserGame findByGameRank(String rank);
    public UserGame findByGame(Game game);
    public List<UserGame> findByUser(User user);
    public List<UserGame> findByGameAndUserNot(Game game, User user);
}
