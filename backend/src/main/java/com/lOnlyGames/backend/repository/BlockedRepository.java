package com.lOnlyGames.backend.repository;

import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.Blocked;
import org.springframework.data.repository.CrudRepository;

public interface BlockedRepository extends CrudRepository<Blocked, Integer> {
}
