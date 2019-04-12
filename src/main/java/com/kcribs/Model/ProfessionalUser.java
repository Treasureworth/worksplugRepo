package com.kcribs.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProfessionalUser {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "professional_id", nullable = false, unique = true)
	private long id;
	
    @Column
    private String businessName;
    
    @Column
    private String businessType;
    
    @Column
    private String category;
    
    @Column
    private String services;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_code", unique = true, nullable = false, insertable = true, updatable = true)
    private User user;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PROFESSIONAL_COVERED_STATE", joinColumns = {
            @JoinColumn(name = "USER_CODE") }, inverseJoinColumns = {
            @JoinColumn(name = "STATE_CODE") })
    private List<State> coveredStates;
    
    @Lob 
    @Column(length = 5000)
    private String about;
    
    @Column
    private int minCost;
    
    @Column
    private int maxCost;
    
    @Column
    private double averageRating;

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

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
