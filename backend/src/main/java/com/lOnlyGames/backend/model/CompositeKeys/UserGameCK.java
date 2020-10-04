package com.lOnlyGames.backend.model.CompositeKeys;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserGameCK implements Serializable {

    private String username;
    private String gameName;

}
