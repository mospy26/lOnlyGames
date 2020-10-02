package com.lOnlyGames.backend.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Timestamp timeStart;
    private Timestamp timeEnd;
    
    @ManyToMany(mappedBy="availabilities")
    private Set<User> usersAvailable;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getTimeStart() {
		return this.timeStart;
	}

	public void setTimeStart(Timestamp timeStart) {
		this.timeStart = timeStart;
	}

	public Timestamp getTimeEnd() {
		return this.timeEnd;
	}

	public void setTimeEnd(Timestamp timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Set<User> getUsersAvailable() {
		return this.usersAvailable;
	}

	public void setUsersAvailable(Set<User> usersAvailable) {
		this.usersAvailable = usersAvailable;
    }
    
    public boolean addUsersAvailable(User user) {
		return this.usersAvailable.add(user);
	}

}
