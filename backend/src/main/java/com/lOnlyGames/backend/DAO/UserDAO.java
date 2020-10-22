package com.lOnlyGames.backend.DAO;

import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidUsernameException;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<String> getAllUsers(){

        var it = userRepository.findAll();
        var users = new ArrayList<String>();
        it.forEach(e -> users.add(e.getUsername()));

        return users;
    }
    public String addUser(String name){
        User user = new User(name);
        if(userRepository.findById(name).isPresent()){
            return "Username already exists\nPlease select another.";
        }
        else{
            userRepository.save(user);
            return "Saved\n" + name + " has been added.";
        }
    }

    public User getUser(String username) throws Exception {
        Optional<User> user = userRepository.findById(username);

        if (!user.isPresent()) throw new Exception("User not found");

        return user.get();
    }
	public User authenticate(String username, String password) {
        Optional<User> user = userRepository.findById(username);
        if (!user.isPresent()) throw new InvalidUsernameException();
        if (!passwordEncoder.matches(password, user.get().getPassword())) throw new InvalidCredentialsException();
        
        return user.get();
	}
	public void register(User user) {
        if (userRepository.findById(user.getUsername()).isPresent()) {
            throw new InvalidUsernameException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
	}
}
