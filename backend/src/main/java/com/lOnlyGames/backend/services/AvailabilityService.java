package com.lOnlyGames.backend.services;

import com.lOnlyGames.backend.DAO.AvailabilityDAO;
import com.lOnlyGames.backend.errorhandlers.exceptions.AvailabilityOverlapException;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidAvailabilityException;
import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.User;
import com.lOnlyGames.backend.model.UserAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvailabilityService {

    @Autowired
    private AvailabilityDAO availabilityDAO;

    public List<Availability> allUserAvailabilities(User user) {
        List<UserAvailability> userAvailabilities = availabilityDAO.getUserAvailabilityRepository().findByUser(user);
        List<Availability> availabilities = new ArrayList<>();

        for (UserAvailability availability: userAvailabilities) {
            availabilities.add(availability.getAvailability());
        }
        return availabilities;
    }

    public String addAvailability(Availability availability) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        int newDay = availability.getDay();
        int newStart = availability.getTimeStart();
        int newEnd = availability.getTimeEnd();
        //Days coded Monday to Sunday (0-6)
        if (newDay >6 || newDay <0) throw new InvalidAvailabilityException();
        //time coded from 0 to 1440 minutes per day
        if (newStart < 0 || newEnd < 1 || newStart >1439 || newEnd > 1440) throw new InvalidAvailabilityException();
        //time start has to be before time end
        if (newEnd <= newStart) throw new InvalidAvailabilityException();

        //get this users availabilities and check to see overlaps
        List<UserAvailability> userAvailabilityList = availabilityDAO.getUserAvailabilityRepository().findByUser(user);

        for (UserAvailability userAvailability: userAvailabilityList) {
            //check if overlap exists, and throw a new error for overlap, they have to delete availability then add new one if overlap exists
            int existingDay = userAvailability.getAvailability().getDay();
            int existingStartTime = userAvailability.getAvailability().getTimeStart();
            int existingEndTime = userAvailability.getAvailability().getTimeEnd();

            //need to check start time and end time overlaps e.g. (1-5 overlaps with 2-6 (start) and 6-9 overlaps with 4-7 (end)
            //also check inner overlaps e.g. 2-8 overlaps with 3-6 and outer overlaps e.g. 3-6 overlaps with 2-8

            //check if start time overlaps, e.g. if on tuesday there is a time 6-10 available, and you try add 7-11 or 7-8
            if (newDay == existingDay && newStart >= existingStartTime && newStart <= existingEndTime ) throw new AvailabilityOverlapException();
            //check if end time overlaps, e.g. if on tuesday 6-10 is available, and you try add 4-9 or even 7-9
            if (newDay == existingDay && newEnd >= existingEndTime && newEnd <= existingEndTime) throw new AvailabilityOverlapException();
            //final case, where the entire existingTime is contained within new time, e.g. if you have on tuesday 6-10 and you try add 5-11.
            if (newDay == existingDay && newStart <= existingStartTime && newEnd >= existingEndTime) throw new AvailabilityOverlapException();

            if (newDay == existingDay && newEnd >= existingStartTime && newEnd <= existingEndTime) throw new AvailabilityOverlapException();
        }


        availabilityDAO.addAvailability(availability);
        availabilityDAO.addUserAvailability(new UserAvailability(user, availability));

        return "Availability has been successfully added.";

    }

    public String removeAvailability(Availability availability) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        List<UserAvailability> userAvailabilityList = availabilityDAO.getUserAvailabilityRepository().findByUser(user);
        UserAvailability userAvailability = new UserAvailability(user, availability);
        if (!userAvailabilityList.contains(userAvailability)) {
            throw new InvalidAvailabilityException();
        }
        else {
            availabilityDAO.deleteUserAvailability(new UserAvailability(user, availability));
            availabilityDAO.deleteAvailability(availability);
        }


        return "Availability successfully removed";
    }


}
