package com.kcribs.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kcribs.DTO.ImageDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.Model.ProfessionalGallery;

@Service
public interface ImageServices {

	ResponseEntity<ServerResponse> uploadImage( ImageDto image );
	
	ResponseEntity<ServerResponse> saveGallery(ProfessionalGallery gallery);

	ResponseEntity<ServerResponse> viewGallery(String usercode);

}
