package com.lOnlyGames.backend.model;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Blocked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User blocker;

    @ManyToOne
    private User blockee;
}
