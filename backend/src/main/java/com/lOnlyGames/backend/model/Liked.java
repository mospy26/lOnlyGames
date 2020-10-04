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
}
