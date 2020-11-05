package com.lOnlyGames.backend;

import com.lOnlyGames.backend.model.Liked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.repository.LikedRepository;
import com.lOnlyGames.backend.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LikedRepositoryTests {

    @Autowired
    private UserRepository user;

    @Autowired
    private LikedRepository liked;

//    @Test
//    public void TestUserALikesUserB() {
//        User eGirl = new User("EGrill123");
//        User eBoy = new User("EBoy123");
//        user.save(eGirl);
//        user.save(eBoy);
//
//        Liked likes = new Liked(eBoy, eGirl);
//        liked.save(likes);
//
//        Liked foundFromDB = liked.findByLiker(user.findById("EBoy123").get());
//        assertNotNull(foundFromDB);
//
//        // Better to extract the username and compare from there
//        assertEquals("EBoy123", foundFromDB.getLiker().getUsername());
//        assertEquals("EGrill123", foundFromDB.getLikes().getUsername());
//    }
}
