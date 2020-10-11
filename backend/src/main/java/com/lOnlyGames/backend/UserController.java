package com.lOnlyGames.backend;

import com.lOnlyGames.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/user") // This means URL's start with /user (after Application path)

public class UserController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserDAO userDAO;

    @GetMapping("/all") //Map GET requests
    public @ResponseBody List<String> getAllUsers() {
        // This returns a JSON or XML with the users
        //UserDAO dao = new UserDAO();
        return userDAO.getAllUsers();
    }
    @PostMapping("/add") //Map POST requests
    public @ResponseBody String addUser (@RequestParam String name) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        return userDAO.addUser(name);
    }
}
