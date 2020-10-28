package com.lOnlyGames.backend.response;

import java.util.List;

import com.lOnlyGames.backend.model.UserGame;

public class MatchesResponse extends ParentResponse {

	public MatchesResponse(List<List<UserGame>> matches) {
		super(matches);
	}
}
