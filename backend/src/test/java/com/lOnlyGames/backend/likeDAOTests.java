package com.lOnlyGames.backend;

import com.lOnlyGames.backend.DAO.LikeDAO;
import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.Liked;
import com.lOnlyGames.backend.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class likeDAOTests {

    @Autowired
    private LikeDAO likeDAO;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testLike() {
        User liker = new User("Liker001");
        User likee = new User("Likee001");
        Liked like = new Liked(liker, likee);

        userDAO.getUserRepository().save(liker);
        userDAO.getUserRepository().save(likee);

        likeDAO.likeUser(like);

        assertEquals(likeDAO.getLikedRepository().findByLikerAndLikes(liker, likee).getId(), like.getId());

        likeDAO.removeLike(like);
        userDAO.getUserRepository().delete(liker);
        userDAO.getUserRepository().delete(likee);

    }

    @Test
    public void testDislike() {
        User liker = new User("Liker001");
        User likee = new User("Likee001");
        Liked like = new Liked(liker, likee);

        userDAO.getUserRepository().save(liker);
        userDAO.getUserRepository().save(likee);

        likeDAO.likeUser(like);

        assertEquals(likeDAO.getLikedRepository().findByLikerAndLikes(liker, likee).getId(), like.getId());

        likeDAO.removeLike(like);

        assertNull(likeDAO.getLikedRepository().findByLikerAndLikes(liker, likee));
        userDAO.getUserRepository().delete(liker);
        userDAO.getUserRepository().delete(likee);

    }
}
