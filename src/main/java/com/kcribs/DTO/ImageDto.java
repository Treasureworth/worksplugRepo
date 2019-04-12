package com.kcribs.DTO;

import com.kcribs.EnumType.ImageEnum;

public class ImageDto {

	private String image;
	
	private String userCode;
	
	private ImageEnum imageType;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public ImageEnum getImageType() {
		return imageType;
	}

	public void setImageType(ImageEnum imageType) {
		this.imageType = imageType;
	}

}
