package com.lOnlyGames.backend.repository;

import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import org.springframework.cglib.core.Block;
import org.springframework.data.repository.CrudRepository;

public interface BlockedRepository extends CrudRepository<Blocked, Integer> {
    public Blocked findByBlocker(User user);
    public Blocked findByBlockee(User user);
}
