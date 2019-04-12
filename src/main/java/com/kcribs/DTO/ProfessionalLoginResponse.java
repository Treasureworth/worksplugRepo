package com.kcribs.DTO;

import com.kcribs.Model.AuthToken;

public class ProfessionalLoginResponse {

	private ProfessionalDto proUser;
	
	private AuthToken token;

	public ProfessionalDto getProUser() {
		return proUser;
	}

	public void setProUser(ProfessionalDto proUser) {
		this.proUser = proUser;
	}

	public AuthToken getToken() {
		return token;
	}

	public void setToken(AuthToken token) {
		this.token = token;
	}

}
