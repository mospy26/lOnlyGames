package com.lOnlyGames.backend.DAO;


import com.lOnlyGames.backend.model.Liked;
import com.lOnlyGames.backend.repository.LikedRepository;
import com.lOnlyGames.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class LikeDAO {

    @Autowired
    private LikedRepository likedRepository;

    @Autowired
    private UserRepository userRepository;

    public LikedRepository getLikedRepository(){
        return likedRepository;
    }

    public UserRepository getUserRepository(){ return userRepository; }

    public void likeUser(Liked likeObj){
        likedRepository.save(likeObj);
    }
    public void removeLike(Liked likeObj){
        likedRepository.delete(likeObj);
    }
}
