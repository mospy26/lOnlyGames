package com.lOnlyGames.backend.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserGamePK implements Serializable {

    private String username;
    private String gameName;

}
