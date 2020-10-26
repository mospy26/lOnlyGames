package com.lOnlyGames.backend.response;

import com.lOnlyGames.backend.model.Blocked;

import java.util.List;

public class AllBlockedResponse extends ParentResponse{

    public AllBlockedResponse(List<Blocked> list){
        super(list);
    }
}
