package com.kcribs.Service;

import org.springframework.http.ResponseEntity;

import com.kcribs.DTO.ServerResponse;
import com.kcribs.Model.ReviewsAndRatings;

public interface ReviewsAndRatingsService {

	ResponseEntity<ServerResponse>  findByUserCode(String usercode);
	
	ResponseEntity<ServerResponse>  findByProfessionalUserCode(String professionalUsercode);
	
	ResponseEntity<ServerResponse> save(ReviewsAndRatings reviews);

	ResponseEntity<ServerResponse> updateReview(ReviewsAndRatings reviews);
	
	ResponseEntity<ServerResponse> countReview(String usercode);

	
	
}
