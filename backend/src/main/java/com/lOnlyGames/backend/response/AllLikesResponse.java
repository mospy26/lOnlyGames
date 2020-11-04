package com.lOnlyGames.backend.response;

import com.lOnlyGames.backend.model.User;

import java.util.List;

public class AllLikesResponse extends ParentResponse{

    public AllLikesResponse(List<User> list){
        super(list);
    }
}
