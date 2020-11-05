package com.lOnlyGames.backend.repository;

import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.UserAvailability;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface AvailabilityRepository extends CrudRepository<Availability, Integer> {
    public Availability findByTimeStart(Timestamp start);
    public Availability findByTimeEnd(Timestamp start);

    // https://stackoverflow.com/a/43828274/9921724
    @Query("SELECT ua FROM UserAvailability ua WHERE :day = ua.availability.day AND " +
            "(:startTime >= ua.availability.timeStart AND :startTime < ua.availability.timeEnd) OR  " +
            "(:endTime > ua.availability.timeStart AND :endTime <= ua.availability.timeEnd) OR " +
            "(:startTime >= ua.availability.timeStart AND :endTime <= ua.availability.timeEnd)")
    public List<UserAvailability> findByAvailability(@Param("day") int day, @Param("startTime") int start, @Param("endTime") int end);
}
