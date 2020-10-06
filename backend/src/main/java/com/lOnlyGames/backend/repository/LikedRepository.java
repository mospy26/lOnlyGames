package com.lOnlyGames.backend.repository;

import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.Liked;
import com.lOnlyGames.backend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface LikedRepository extends CrudRepository<Liked, Integer> {

    public Liked findByLiker(User user);
    public Liked findByLikes(User user);

}
