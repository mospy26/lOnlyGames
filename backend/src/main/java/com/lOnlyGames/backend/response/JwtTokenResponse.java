package com.lOnlyGames.backend.response;

import java.io.Serializable;

public class JwtTokenResponse extends ParentResponse {
    private final String token;

	public JwtTokenResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}
