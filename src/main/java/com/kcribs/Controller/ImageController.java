package com.kcribs.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kcribs.DTO.ImageDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.Model.ProfessionalGallery;
import com.kcribs.Service.ImageServices;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/image")
public class ImageController {

private HttpHeaders responseHeaders = new HttpHeaders();
	
	ServerResponse response = new ServerResponse();
	
	@Autowired
    private ImageServices imageService;
	
	 @Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
	    @RequestMapping(value="/imagemanagement", method = RequestMethod.POST)
	    public ResponseEntity<ServerResponse> imageManagement(@RequestHeader("Authorization") String authorization, @RequestBody ImageDto image){
     		return imageService.uploadImage(image);
	    }
	 
	 @Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
	 @RequestMapping(value="/uploadgallery", method = RequestMethod.POST)
	 public ResponseEntity<ServerResponse> galleryUpload(@RequestHeader("Authorization") String authorization, @RequestBody ProfessionalGallery gallery){
		 return imageService.saveGallery(gallery);
	 }
	 
	 @RequestMapping(value="/viewgallery/{usercode}", method = RequestMethod.GET)
	 public ResponseEntity<ServerResponse> viewGallery(@PathVariable("usercode") String usercode){
		 return imageService.viewGallery(usercode);
	 }
}
