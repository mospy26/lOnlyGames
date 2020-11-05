package com.lOnlyGames.backend.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Min(value=0)
    @Max(value=6)
    private int day;

    @Min(value=0)
    @Max(value=1440)
    private int timeStart; // Time representing minutes

    @Min(value=0)
    @Max(value=1440)
    private int timeEnd; // Time representing minutes

	@OneToMany(mappedBy = "availability", cascade = CascadeType.REMOVE)
	private Set<UserAvailability> users;

	public Availability() {}

	public Availability(int day, int timeStart, int timeEnd) {
        this.day = day;
	    this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Integer getId() {
        return id;
    }

    public int getDay() {
        return day;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

    public Set<UserAvailability> getUsers() {
        return users;
    }
}
