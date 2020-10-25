package com.lOnlyGames.backend.response;

import java.util.List;

import com.lOnlyGames.backend.model.UserGame;

public class MatchesResponse extends ParentResponse {
    private final List<List<UserGame>> matches;

	public MatchesResponse(List<List<UserGame>> matches) {
		super();
		this.matches = matches;
	}

	public List<List<UserGame>> getMatches() {
		return this.matches;
	}
}
