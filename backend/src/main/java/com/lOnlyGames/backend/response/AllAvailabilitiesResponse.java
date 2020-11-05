package com.lOnlyGames.backend.response;

import com.lOnlyGames.backend.model.Availability;

import java.util.List;

public class AllAvailabilitiesResponse extends ParentResponse {
    public AllAvailabilitiesResponse(List<Availability> list) {
        super(list);
    }
}
