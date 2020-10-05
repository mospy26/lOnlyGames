package com.lOnlyGames.backend.model;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Avatar {
    @Id
    private String avatarURL;

    @OneToMany(mappedBy = "avatar")
    private Set<User> users;

    public Avatar() { }
    public Avatar(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getAvatarURL() {
        return avatarURL;
    }
}
