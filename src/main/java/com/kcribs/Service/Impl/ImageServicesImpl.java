package com.kcribs.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cloudinary.utils.ObjectUtils;
import com.kcribs.Configuration.CloudinaryConfig;
import com.kcribs.Constants.Constants;
import com.kcribs.DTO.ImageDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.Model.ProfessionalGallery;
import com.kcribs.Model.User;
import com.kcribs.Repository.ProfessionalGalleryRepository;
import com.kcribs.Repository.ProfessionalUserRepository;
import com.kcribs.Repository.UserRepository;
import com.kcribs.Service.ImageServices;

@Transactional
@Service
public class ImageServicesImpl implements ImageServices {

private HttpHeaders responseHeaders = new HttpHeaders();
	
	ServerResponse response = new ServerResponse(); 
	
	@Autowired
	CloudinaryConfig cloudinaryConfig;
	
	@Autowired
	UserRepository  userRepository;
	
	@Autowired
	ProfessionalUserRepository professionalUserRepository;
	
	@Autowired
	ProfessionalGalleryRepository proGalleryRepository;
	
	
	@Override
	public ResponseEntity<ServerResponse> uploadImage(ImageDto image) {
		if (image.getImage() == null || image.getImage().isEmpty()) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Image is empty");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}
		
		if (image.getUserCode() == null || image.getUserCode().isEmpty()) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Usercode is empty");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}
		
		if (image.getImageType() == null || image.getImageType().toString().isEmpty()) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Image type not valid");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}
		
		try {
			
			User user = userRepository.findByUserCode(image.getUserCode());
			
			if (user == null) {
				response.setData("User not found");
				response.setStatus(Constants.FAIL);
				response.setMessage("User not found");
				response.setSuccess(false);
				return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
			}
			String id = "AP" + System.currentTimeMillis();
			Map uploadResult =  cloudinaryConfig.upload(image.getImage(), 

			    	ObjectUtils.asMap("resourcetype", "auto",

			    			"folder", "kcribs/" + image.getImageType(), 

			    		    "public_id", image.getUserCode() + id));
			
			if (user != null) {
				user.setProfilePicture(uploadResult.get("url").toString());
				response.setData(user);

			}
			
			response.setStatus(Constants.OK);
			response.setMessage("Image uploaded successfully");
			response.setSuccess(true);

		} catch( Exception e) {
			e.printStackTrace();
			response.setData("Something went wrong");
			response.setStatus(Constants.FAIL);
			response.setMessage("Something went wrong with image upload");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}


	@Override
	public ResponseEntity<ServerResponse> saveGallery(ProfessionalGallery gallery) {
		if (gallery.getImages() == null || gallery.getImages().isEmpty()) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Image is empty");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}
		
		if (gallery.getUserCode() == null || gallery.getUserCode().isEmpty()) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Usercode is empty");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}
		
		if (gallery.getImageType() == null || gallery.getImageType().toString().isEmpty()) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Image type not valid");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}
		
		try {
			
			User user = userRepository.findByUserCode(gallery.getUserCode());
			ProfessionalGallery proGallery = new ProfessionalGallery();
			
			if (user == null) {
				response.setData("User not found");
				response.setStatus(Constants.FAIL);
				response.setMessage("User not found");
				response.setSuccess(false);
				return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
			}
			String id = "XM" + System.currentTimeMillis();
			Map uploadResult =  cloudinaryConfig.upload(gallery.getImages(), 

			    	ObjectUtils.asMap("resourcetype", "auto",

			    			"folder", "kcribs/" + gallery.getImageType(), 

			    		    "public_id", gallery.getUserCode() + id));
			
			if (user != null) {
				proGallery.setImages(uploadResult.get("url").toString());
				proGallery.setUserCode(gallery.getUserCode());
				proGallery.setImageType(gallery.getImageType());
				
				proGalleryRepository.save(proGallery);
				response.setData(proGallery);

			}
			
			response.setStatus(Constants.OK);
			response.setMessage("Image uploaded successfully");
			response.setSuccess(true);

		} catch( Exception e) {
			e.printStackTrace();
			response.setData("Something went wrong");
			response.setStatus(Constants.FAIL);
			response.setMessage("Something went wrong with image upload");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}
		
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));


	}


	@Override
	public ResponseEntity<ServerResponse> viewGallery(String usercode) {
		
		List<ProfessionalGallery> proGallery = new ArrayList<>();
		proGalleryRepository.findByUserCode(usercode).iterator().forEachRemaining(proGallery::add);
		 	

//		ProfessionalGallery proGallery = new ProfessionalGallery();
//		proGallery = proGalleryRepository.findByUserCode(usercode);
		
		if (usercode == null) {
			response.setData("User code is empty");
			response.setStatus(Constants.FAIL);
			response.setMessage("User code is empty");
			response.setSuccess(false);
			
		} else if (proGallery.isEmpty()) {
			response.setData("No gallery record");
			response.setStatus(Constants.FAIL);
			response.setMessage("No gallery record");
			response.setSuccess(false);
		
		} else if (proGallery != null) {
			response.setData(proGallery);
			response.setStatus(Constants.OK);
			response.setMessage("Gallery Record fetched");
			response.setSuccess(true);	
		}
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		
	}

}
