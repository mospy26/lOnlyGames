package com.lOnlyGames.backend;

import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){

        var it = userRepository.findAll();
        var users = new ArrayList<User>();
        it.forEach(e -> users.add(e));

        return users;
    }
    public String addUser(String name){
        User user = new User(name);
        userRepository.save(user);
        return "Saved";
    }
}
