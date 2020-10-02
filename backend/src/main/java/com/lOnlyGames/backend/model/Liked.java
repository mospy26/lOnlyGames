package com.lOnlyGames.backend.model;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Liked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Set<User> users;

    @OneToOne
    private User thisUser;
}
