package com.lOnlyGames.backend.response;

import com.lOnlyGames.backend.model.User;

import java.util.List;

public class AllBlockedResponse extends ParentResponse{

    public AllBlockedResponse(List<User> list){
        super(list);
    }
}
