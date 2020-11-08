package com.lOnlyGames.backend.repository;

import com.lOnlyGames.backend.model.Liked;
import com.lOnlyGames.backend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikedRepository extends CrudRepository<Liked, Integer> {

    public List<Liked> findByLiker(User user);
    public List<Liked> findByLikes(User user);
    public Liked findByLikerAndLikes(User liker, User likes);

}
