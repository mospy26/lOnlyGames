package com.lOnlyGames.backend.model;

import java.util.Set;

import javax.persistence.*;

@Entity
public class User {

    // User's properties
    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String discordId;
    private String steamId;
    private String bio;
    private String location;
    private String avatarURL;
    private Integer numberOfReports;

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

    // THIS user's block list
    @OneToMany(mappedBy = "blocker", cascade = CascadeType.REMOVE)
    private Set<Blocked> blocked;

    // The users who blocked THIS person
    @OneToMany(mappedBy = "blockee", cascade = CascadeType.REMOVE)
    private Set<Blocked> blockers;

    public User() {
        this.numberOfReports = 0;
    }

    public User(String username) {
        this.username = username;
        this.numberOfReports = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDiscordId() {
        return discordId;
    }

    public String getSteamId() {
        return steamId;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public Integer getNumberOfReports() {
        return numberOfReports;
    }

    public Set<UserGame> getGames() {
        return games;
    }

    public Set<UserAvailability> getAvailabilities() {
        return availabilities;
    }

    public Set<Liked> getLikes() {
        return likes;
    }

    public Set<Liked> getLikedBy() {
        return likedBy;
    }

    public Set<Blocked> getBlocked() {
        return blocked;
    }

    public Set<Blocked> getBlockers() {
        return blockers;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNumberOfReports(Integer numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

}
