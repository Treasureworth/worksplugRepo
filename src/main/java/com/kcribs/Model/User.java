package com.kcribs.Model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
public class User {

	
//	@JsonIgnore
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	@Column(name = "user_id", nullable = false, unique = true)
//	private long id;
    
	@Id
	@Column(name = "user_code", nullable = false, unique = true)
	private String userCode;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column
    private String phoneNumber;
    
    @Column
    private String name;
    
    @Column
    private String stateOfResidence;
    
    @Column
    private boolean enabled;
    
    @Column
    private String password;
    
    @ApiModelProperty(access="hidden", hidden=true)
	@CreationTimestamp
	private Date createdDate;
    
    @ApiModelProperty(access="hidden", hidden=true)
	@UpdateTimestamp
	private Date modifiedDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_CODE") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private Role role;
    
    @Column
    private String profilePicture;
    
    @JsonIgnore
    @Column
    private Long activationCode;
 

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public String getUserId() {
//		return userCode;
//	}
//
//	public void setUserId(String userId) {
//		this.userCode = userId;
//	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public long getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(Long activationCode) {
		this.activationCode = activationCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStateOfResidence() {
		return stateOfResidence;
	}

	public void setStateOfResidence(String stateOfResidence) {
		this.stateOfResidence = stateOfResidence;
	}
    
}
