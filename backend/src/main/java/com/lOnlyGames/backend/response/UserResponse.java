package com.lOnlyGames.backend.response;

import org.springframework.security.core.userdetails.UserDetails;

public class UserResponse extends ParentResponse {

    private UserDetails user;

    public UserResponse(UserDetails user) {
        super();
        this.user = user;

    }

    public UserDetails getUser() {
        return this.user;
    }

}
