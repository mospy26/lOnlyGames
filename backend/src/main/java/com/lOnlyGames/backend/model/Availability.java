package com.lOnlyGames.backend.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Timestamp timeStart;
    private Timestamp timeEnd;

	@OneToMany(mappedBy = "availability", cascade = CascadeType.REMOVE)
	private Set<UserAvailability> users;

}
