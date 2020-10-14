package com.lOnlyGames.backend;

import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.Liked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.repository.BlockedRepository;
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
public class BlockedRepositoryTests {

    @Autowired
    private UserRepository user;

    @Autowired
    private BlockedRepository block;

//    @Test
//    public void TestUserABlockedUserB() {
//        User eGirl = new User("EGrill123");
//        User eBoy = new User("EBoy123");
//        user.save(eGirl);
//        user.save(eBoy);
//
//        Blocked blocked = new Blocked(user.findById("EBoy123").get(), user.findById("EGrill123").get());
//        block.save(blocked);
//
//        Blocked foundFromDB = block.findByBlocker(user.findById("EBoy123").get());
//        assertNotNull(foundFromDB);
//
//        // Better to extract the username and compare from there
//        assertEquals("EBoy123", foundFromDB.getBlocker().getUsername());
//        assertEquals("EGrill123", foundFromDB.getBlockee().getUsername());
//    }
}
