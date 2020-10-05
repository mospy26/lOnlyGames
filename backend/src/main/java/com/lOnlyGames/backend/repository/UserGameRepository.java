package com.lOnlyGames.backend.repository;

import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.CompositeKeys.UserGameCK;
import com.lOnlyGames.backend.model.UserGame;
import org.springframework.data.repository.CrudRepository;

public interface UserGameRepository extends CrudRepository<UserGame, UserGameCK> {
}
