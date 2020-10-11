package com.lOnlyGames.backend.repository;

import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import org.springframework.cglib.core.Block;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlockedRepository extends CrudRepository<Blocked, Integer> {
    //returns all the blocks that a user has made
    public List<Blocked> findByBlocker(User user);
}
