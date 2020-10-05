package com.lOnlyGames.backend.repository;

import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.Liked;
import org.springframework.data.repository.CrudRepository;

public interface LikedRepository extends CrudRepository<Liked, Integer> {
}
