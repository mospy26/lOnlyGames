package com.lOnlyGames.backend.model;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /* 
    As per stackoverflow: https://stackoverflow.com/questions/47526592/saving-images-using-spring-boot-and-mysql
    Recommends storing as an address on an S3
    */
    private String icon;
    private String apiURL;

    @OneToMany(mappedBy = "game")
    Set<UserGames> user;

}
