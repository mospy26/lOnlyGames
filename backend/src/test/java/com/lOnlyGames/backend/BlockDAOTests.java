package com.lOnlyGames.backend;

import com.lOnlyGames.backend.DAO.BlockedDAO;
import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.repository.BlockedRepository;
import com.lOnlyGames.backend.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockDAOTests {

    @Autowired
    private BlockedDAO blockedDAO;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testBlock() {
        User blocker = new User("Blocker001");
        User blockee = new User("Blockee001");
        Blocked block = new Blocked(blocker, blockee);

        userDAO.getUserRepository().save(blocker);
        userDAO.getUserRepository().save(blockee);

        blockedDAO.blockUser(block);

        assertEquals(blockedDAO.getBlockedRepository().findByBlockerAndBlockee(blocker, blockee).getId(), block.getId());

        blockedDAO.unblockUser(block);
        userDAO.getUserRepository().delete(blocker);
        userDAO.getUserRepository().delete(blockee);

    }

    @Test
    public void testUnblock() {
        User blocker = new User("Blocker001");
        User blockee = new User("Blockee001");
        Blocked block = new Blocked(blocker, blockee);

        userDAO.getUserRepository().save(blocker);
        userDAO.getUserRepository().save(blockee);

        blockedDAO.blockUser(block);

        assertEquals(blockedDAO.getBlockedRepository().findByBlockerAndBlockee(blocker, blockee).getId(), block.getId());

        blockedDAO.unblockUser(block);
        assertNull(blockedDAO.getBlockedRepository().findByBlockerAndBlockee(blocker, blockee));
        userDAO.getUserRepository().delete(blocker);
        userDAO.getUserRepository().delete(blockee);

    }
}
