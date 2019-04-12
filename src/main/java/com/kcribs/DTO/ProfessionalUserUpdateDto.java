package com.kcribs.DTO;

import java.util.List;

import com.kcribs.Model.State;

public class ProfessionalUserUpdateDto {

	private String userCode;
    private String businessName;
    
    private String businessType;
    
    private List<State> coveredStates;
    
    private String about;

    private int minCost;
 
    private int maxCost;

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
