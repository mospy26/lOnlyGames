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

    private boolean liked;

    public Integer getId() {
        return id;
    }

    public User getLiker() {
        return liker;
    }

    public User getLikes() {
        return likes;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
