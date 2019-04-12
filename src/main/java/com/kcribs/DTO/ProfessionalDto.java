package com.kcribs.DTO;

import java.util.List;

import com.kcribs.Model.Role;
import com.kcribs.Model.State;

public class ProfessionalDto {

	private String userCode;
	
	private String businessName;
	
	private String businessType;
	
	private String category;
	
	private String email;
	
	private String password;
	
	private String stateOfResidence;
	
	private String profilePicture;
	
	private int minCost;
	
	private int maxCost;
	
	private String services;
	
	private Role role;
	
	private String about;
	
	private String phoneNumber;
	
	private List<State> coveredStates;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMinCost() {
		return minCost;
	}

	public void setMinCost(int minCost) {
		this.minCost = minCost;
	}

	public int getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(int maxCost) {
		this.maxCost = maxCost;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<State> getCoveredStates() {
		return coveredStates;
	}

	public void setCoveredStates(List<State> coveredStates) {
		this.coveredStates = coveredStates;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getStateOfResidence() {
		return stateOfResidence;
	}

	public void setStateOfResidence(String stateOfResidence) {
		this.stateOfResidence = stateOfResidence;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	
	
}
