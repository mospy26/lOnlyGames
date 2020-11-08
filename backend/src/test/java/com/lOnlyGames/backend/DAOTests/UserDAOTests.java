package com.lOnlyGames.backend.DAOTests;

import com.lOnlyGames.backend.DAO.UserDAO;
import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.repository.GameRepository;
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
@AutoConfigureMockMvc
public class UserDAOTests {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Test
    @Transactional
    @Rollback
    public void testAddUser(){
        User user = new User("User");
        userDAO.addUser(user);
        assertEquals(user.getUsername(), userRepository.findById("User").get().getUsername());
    }

    @Test
    @Transactional
    @Rollback
    public void testAddGame(){
        Game game = new Game("Game");
        userDAO.addGame(game);
        assertEquals(game.getName(), gameRepository.findById("Game").get().getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testGetUser(){
        User user = new User("User");
        userDAO.addUser(user);
        User getUser = userDAO.getUser("User");
        assertEquals(user.getUsername(), getUser.getUsername());
    }


}


