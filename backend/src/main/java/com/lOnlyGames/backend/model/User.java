package com.lOnlyGames.backend.model;

import javassist.bytecode.analysis.ControlFlow;

import java.util.Set;

import javax.persistence.*;

@Entity
public class User {

    // User's properties
    @Id
    private String username;

    private String firstName;
    private String lastName;
    private String email;
    private String discordId;
    private String steamId;
    private String bio;
    private String location;

    // Avatars
    @ManyToOne
    private Avatar avatar;

    // Games
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<UserGame> games;

    // Availabilities
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<UserAvailability> availabilities;

    // Likes
    @OneToMany(mappedBy = "liker", cascade = CascadeType.REMOVE)
    private Set<Liked> likes;

    // Liked By
    @OneToMany(mappedBy="likes", cascade = CascadeType.REMOVE)
    private Set<Liked> likedBy;

    // Blocked
    @OneToMany(mappedBy = "blocker", cascade = CascadeType.REMOVE)
    private Set<Blocked> blocked;

    // Blockers
    @OneToMany(mappedBy = "blockee", cascade = CascadeType.REMOVE)
    private Set<Blocked> blockers;

}
