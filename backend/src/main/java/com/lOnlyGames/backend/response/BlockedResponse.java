package com.lOnlyGames.backend.response;

public class BlockedResponse extends ParentResponse{
    private final String blockedMsg;

    public BlockedResponse(String msg){
        super();
        this.blockedMsg = msg;
    }
    public String getBlockedMsg(){
        return this.blockedMsg;
    }
}
