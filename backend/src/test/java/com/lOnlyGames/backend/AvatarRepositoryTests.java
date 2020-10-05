package com.lOnlyGames.backend;

import com.lOnlyGames.backend.model.Avatar;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.repository.AvatarRepository;
import com.lOnlyGames.backend.repository.BlockedRepository;
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
public class AvatarRepositoryTests {

    @Autowired
    private UserRepository user;

    @Autowired
    private AvatarRepository avatar;

    @Test
    public void testAddAvatar() {
        String url = "https://s3.Region.amazonaws.com/bucket-name/keyname";
        Avatar a = new Avatar(url);
        a = avatar.save(a);
        assertNotNull(avatar.findByAvatarURL(url));
        assertNull(avatar.findByAvatarURL("invalidUrl"));
    }

    @Test
    public void testAssignAvatarURLToUsers() {
        String url = "https://s3.Region.amazonaws.com/bucket-name/keyname/1";
        Avatar a = new Avatar(url);
        a = avatar.save(a);

        User u = new User("lol");
        u.setAvatar(a);
        u = user.save(u);

        User u2 = new User("lol2");
        u2.setAvatar(a);
        u2 = user.save(u);

        assertEquals(u.getAvatar().getAvatarURL(), url);
        assertEquals(u2.getAvatar().getAvatarURL(), url);
    }
}
