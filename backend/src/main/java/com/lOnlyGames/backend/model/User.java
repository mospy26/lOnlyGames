package com.lOnlyGames.backend.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javassist.bytecode.analysis.ControlFlow.Block;

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
        inverseJoinColumns = @JoinColumn(name="id")
    )
    private Set<Availability> availabilities;

    // Avatars
    @ManyToOne
    @JoinColumn(name="id", nullable = false)
    private Avatar avatar;

    // Games
    @OneToMany(mappedBy="user")
    private Set<UserGames> games;

    // Likes
    @OneToOne
    private Liked likes;

    // Blocked
    @OneToOne
    private Block blocks;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiscordID() {
        return this.discordID;
    }

    public void setDiscordID(String discordID) {
        this.discordID = discordID;
    }

    public String getSteamID() {
        return this.steamID;
    }

    public void setSteamID(String steamID) {
        this.steamID = steamID;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
