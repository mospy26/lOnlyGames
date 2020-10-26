package com.lOnlyGames.backend.response;

import com.lOnlyGames.backend.model.Blocked;

import java.util.List;

public class AllBlockedResponse extends ParentResponse{
    private final List<Blocked> blockedList;

    public AllBlockedResponse(List<Blocked> list){
        super();
        this.blockedList = list;
    }

    public List<Blocked> getBlockedList(){
        return this.blockedList;
    }
}
