package com.kcribs.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kcribs.EnumType.ImageEnum;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class ProfessionalGallery {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "professional_id", nullable = false, unique = true)
	private long id;
	
	private String userCode;
	
	private String images;
	
	private ImageEnum imageType;
	
	 @ApiModelProperty(access="hidden", hidden=true)
		@CreationTimestamp
		private Date createdDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public ImageEnum getImageType() {
		return imageType;
	}

	public void setImageType(ImageEnum imageType) {
		this.imageType = imageType;
	}
}
