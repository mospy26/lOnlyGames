package com.lOnlyGames.backend.response;

import org.springframework.security.core.userdetails.UserDetails;

public class UserResponse extends ParentResponse {


    public UserResponse(UserDetails user) {
        super(user);

    }

}
