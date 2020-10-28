package com.lOnlyGames.backend.repository;

import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import org.springframework.cglib.core.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface BlockedRepository extends CrudRepository<Blocked, Integer> {
    //returns all the blocks that a user has made
    // @PreAuthorize("#user?.username == authentication?.name")
    public List<Blocked> findByBlocker(User user);
    public List<Blocked> findByBlockee(User user);
    public Blocked findByBlockerAndBlockee(User blocker, User blockee);
}
