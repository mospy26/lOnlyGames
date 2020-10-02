package com.lOnlyGames.backend.model;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String avatarURL;

    @OneToMany(mappedBy = "avatars")
    private Set<User> users;
}
