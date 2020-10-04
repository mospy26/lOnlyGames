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
    private String discordID;
    private String steamID;
    private String bio;
    private String location;

    // Availabilities 
    @ManyToMany
    @JoinTable(
        name="UserAvailabilities",
        joinColumns = @JoinColumn(name="username"),
        inverseJoinColumns = @JoinColumn(name="avaliability_id")
    )
    private Set<Availability> availabilities;

    // Avatars
    @ManyToOne
    private Avatar avatar;

//    // Games
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<UserGames> games;

    // Likes
    @OneToMany(mappedBy = "liker")
    private Set<Liked> likes;

    // Liked By
    @OneToMany(mappedBy="likes")
    private Set<Liked> likedBy;

    // Blocked
    @OneToMany(mappedBy = "blocker")
    private Set<Blocked> blocked;

    // Blockers
    @OneToMany(mappedBy = "blockee")
    private Set<Blocked> blockers;

}
