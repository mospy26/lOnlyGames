package com.lOnlyGames.backend.model;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String avatarURL;

    @OneToMany(mappedBy = "avatar")
    private Set<User> users;

}
