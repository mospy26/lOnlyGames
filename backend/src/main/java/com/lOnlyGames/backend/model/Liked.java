package com.lOnlyGames.backend.model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Liked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User liker;

    @ManyToOne
    private User likes;

    public Liked() {

    }

    public Liked(User a, User b) {
        this.liker = a;
        this.likes = b;
    }

    public Integer getId() {
        return id;
    }

    public User getLiker() {
        return liker;
    }

    public User getLikes() {
        return likes;
    }
}
