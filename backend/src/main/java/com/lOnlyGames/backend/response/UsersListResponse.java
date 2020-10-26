package com.lOnlyGames.backend.response;

import java.util.ArrayList;
import java.util.List;

import com.lOnlyGames.backend.model.User;

public class UsersListResponse extends ParentResponse {

    public UsersListResponse(List<User> users) {
        super();

        List<String> usernames = new ArrayList<>();
        users.forEach(u -> {
            usernames.add(u.getUsername());
        });

        this.result = usernames;
    }
}
