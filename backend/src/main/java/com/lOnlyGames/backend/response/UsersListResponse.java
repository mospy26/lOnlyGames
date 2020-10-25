package com.lOnlyGames.backend.response;

import java.util.ArrayList;
import java.util.List;

import com.lOnlyGames.backend.model.User;

public class UsersListResponse extends ParentResponse {
    private List<String> usernames;

    public UsersListResponse(List<User> users) {
        usernames = new ArrayList<>();
        users.forEach(u -> {
            usernames.add(u.getUsername());
        });
    }

    public List<String> getUsernames() {
        return this.usernames;
    }
}
