package com.kcribs.DTO;

public class SetPasswordDto {

	private Long activationCode;
	
	private String password;

	public Long getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(Long activationCode) {
		this.activationCode = activationCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
