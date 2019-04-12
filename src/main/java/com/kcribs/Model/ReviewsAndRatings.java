package com.kcribs.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class ReviewsAndRatings {

	@JsonIgnore
	@Id
	private String reviewId;
	
	@Column
	private String userCode;
	
	@Column
	private String professionalUserCode;
	
	@Column
	private int rating;
	
	@Column
	private String review;
	
	@ApiModelProperty(access="hidden", hidden=true)
	@CreationTimestamp
	private Date createdDate;
    
    @ApiModelProperty(access="hidden", hidden=true)
	@UpdateTimestamp
	private Date modifiedDate;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
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

	public String getProfessionalUserCode() {
		return professionalUserCode;
	}

	public void setProfessionalUserCode(String professionalUserCode) {
		this.professionalUserCode = professionalUserCode;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	
}
