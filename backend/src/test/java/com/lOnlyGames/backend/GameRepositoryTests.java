package com.lOnlyGames.backend;

import com.lOnlyGames.backend.model.Game;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserGame;
import com.lOnlyGames.backend.repository.GameRepository;
import com.lOnlyGames.backend.repository.UserGameRepository;
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
public class GameRepositoryTests {

    @Autowired
    private UserRepository user;

    @Autowired
    private GameRepository game;

    @Autowired
    private UserGameRepository userGame;

    @Test
    public void testAddGame() {
        String name = "League Of Legends";
        String apiURL = "https://developer.riotgames.com/";
        String iconURL = "https://s3.Region.amazonaws.com/bucket-name/keyname/";

        Game g = new Game(name);
       // g.setApiURL(apiURL);
        g.setIconURL(iconURL);

        g = game.save(g);
       // assertEquals(g.getApiURL(), apiURL);
        assertEquals(g.getIconURL(), iconURL);
        assertEquals(g.getName(), name);
    }

    @Test
    public void testAddUserGameWithRank() {
        User u = new User("Hello");
        User u2 = new User("Hello 2");
        u = user.save(u);
        u2 = user.save(u2);

        Game g = game.findByName("League Of Legends");
        //assertNotNull(g); // Must run with testAddGame as well

        UserGame ug = new UserGame(u, g);
        ug.setGameRank("Gold I");
        ug = userGame.save(ug);

        UserGame ug2 = new UserGame(u2, g);
        ug2.setGameRank("Diamond V");
        ug2 = userGame.save(ug2);

        assertNotNull(ug);
        assertEquals(ug.getGame().getName(), "League Of Legends");
        assertEquals(ug.getUser().getUsername(), "Hello");
        assertEquals(ug.getGameRank(), "Gold I");

        assertNotNull(ug2);
        assertEquals(ug2.getGame().getName(), "League Of Legends");
        assertEquals(ug2.getUser().getUsername(), "Hello 2");
        assertEquals(ug2.getGameRank(), "Diamond V");
    }
}
