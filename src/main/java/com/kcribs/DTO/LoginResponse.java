package com.kcribs.DTO;

import com.kcribs.Model.AuthToken;
import com.kcribs.Model.User;

public class LoginResponse {

	private User user;
	private AuthToken token;
	
	public AuthToken getToken() {
		return token;
	}
	public void setToken(AuthToken accessToken) {
		this.token = accessToken;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user2) {
		this.user = user2;
	}
}
