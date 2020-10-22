package com.lOnlyGames.backend;

import java.util.List;
import java.util.Optional;

import com.lOnlyGames.backend.response.*;
import com.lOnlyGames.backend.auth.JwtTokenUtil;
import com.lOnlyGames.backend.auth.LonlygamesUserDetailsService;
import com.lOnlyGames.backend.model.Blocked;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.repository.BlockedRepository;
import com.lOnlyGames.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="")
public class SampleController {

    @Autowired
    BlockedRepository blocked;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
	private LonlygamesUserDetailsService userDetailsService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @PostMapping(value="/authenticate")
    public ResponseEntity<?> login(@RequestBody User user) throws Exception {
        authenticate(user.getUsername(), user.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        JwtTokenResponse token = new JwtTokenResponse(jwtTokenUtil.generateToken(userDetails));

        return new ResponseEntity<JwtTokenResponse>(token, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            User u = userRepository.findByUsernameAndPassword(username, password);
		} catch (Exception e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
    }

    @GetMapping("/hello")
    public String hello() {
        Optional<User> u = userRepository.findById("hello");

        if (!u.isPresent()) {
            userRepository.save(new User("hello"));
        }

        User us = userRepository.findById("hello").get();
        List<Blocked> b = blocked.findByBlocker(us);
        return "hehehehehe";
    }
}
