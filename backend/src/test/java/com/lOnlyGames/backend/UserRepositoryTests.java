package com.lOnlyGames.backend;

import static org.junit.jupiter.api.Assertions.*;

import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRepositoryTests {

    @Autowired
    private UserRepository repository;

    @Test
    public void testCreateUser() {
        String name = "user101";
        User user = repository.save(new User(name));

        // Find the User
        Optional<User> userFound = repository.findById(name);
        assertNotNull(userFound);
        assertEquals(userFound.get().getUsername(), name);
    }

    @Test
    public void testDeleteUser() {
        User user = repository.save(new User("user101"));
        assertTrue(repository.findById("user101").isPresent());
        repository.delete(repository.findById("user101").get());
        assertFalse(repository.findById("user101").isPresent());
    }

    @Test
    public void testNotFoundUser() {
        User user = new User("XD");
        repository.save(user);
        assertFalse(repository.findById("XD123").isPresent());
    }

    @Test
    public void testAllPropertiesOfUser() {
        HashMap<String, String> user = new HashMap<>();
        user.put("username", "tester01");
        user.put("firstName", "Tester");
        user.put("lastName", "TestingLastName");
        user.put("email", "email@email.com");
        user.put("discordId", "TESTER#0001");
        user.put("steamId", "TesterSteamID");
        user.put("bio", "Hello World");
        user.put("location", "Sydney, NSW");

        User object = new User(user.get("username"));
        object.setFirstName(user.get("firstName"));
        object.setLastName(user.get("lastName"));
        object.setEmail(user.get("email"));
        object.setDiscordId(user.get("discordId"));
        object.setSteamId(user.get("steamId"));
        object.setBio(user.get("bio"));
        object.setLocation(user.get("location"));

        repository.save(object);
        assertTrue(repository.findById(user.get("username")).isPresent());

        object = repository.findById(user.get("username")).get();
        assertEquals(object.getUsername(), user.get("username"));
        assertEquals(object.getFirstName(), user.get("firstName"));
        assertEquals(object.getLastName(), user.get("lastName"));
        assertEquals(object.getEmail(), user.get("email"));
        assertEquals(object.getDiscordId(), user.get("discordId"));
        assertEquals(object.getSteamId(), user.get("steamId"));
        assertEquals(object.getBio(), user.get("bio"));
        assertEquals(object.getLocation(), user.get("location"));
        assertEquals(object.getNumberOfReports(), 0);

        // Test Whether setters work
        object.setNumberOfReports(object.getNumberOfReports() + 1);
        assertEquals(object.getNumberOfReports(), 1);

    }

}
