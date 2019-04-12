package com.kcribs.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kcribs.Constants.Constants;
import com.kcribs.DTO.ReviewRatingDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.Model.ProfessionalUser;
import com.kcribs.Model.ReviewsAndRatings;
import com.kcribs.Model.User;
import com.kcribs.Repository.ProfessionalUserRepository;
import com.kcribs.Repository.ReviewsAndRatingsRepository;
import com.kcribs.Repository.UserRepository;
import com.kcribs.Service.ReviewsAndRatingsService;

@Service
public class ReviewsAndRatingsServiceImpl implements ReviewsAndRatingsService{

	@Autowired
	private ReviewsAndRatingsRepository reviewsAndRatingsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfessionalUserRepository professionalUserRepository;

	
	private HttpHeaders responseHeaders = new HttpHeaders();
	
	ServerResponse response = new ServerResponse();
	
	@Override
	public ResponseEntity<ServerResponse> findByUserCode(String usercode) {

		List<ReviewsAndRatings> reviewsRecord = new ArrayList<>();
		reviewsAndRatingsRepository.findAllByUserCode(usercode).iterator().forEachRemaining(reviewsRecord::add);

	
			if (reviewsRecord.isEmpty()) {
				response.setData("");
				response.setStatus(Constants.OK);
				response.setMessage("There are no reviews for this user");
				response.setSuccess(false);
				return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			} else {
				response.setData(reviewsRecord);
				response.setStatus(Constants.OK);
				response.setMessage("User reviews and ratings successfully fetched");
				response.setSuccess(true);
				return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
			}
		}


	@Override
	public ResponseEntity<ServerResponse> findByProfessionalUserCode(String professionalusercode) {
		
		List<ReviewsAndRatings> reviewsRecord = new ArrayList<>();
		reviewsAndRatingsRepository.findAllByProfessionalUserCode(professionalusercode).iterator().forEachRemaining(reviewsRecord::add);

		if (reviewsRecord.isEmpty() ){
			response.setData("");
			response.setStatus(Constants.OK);
			response.setMessage("There are no reviews for this user");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		} else {
			response.setData(reviewsRecord);
			response.setStatus(Constants.OK);
			response.setMessage("User reviews and ratings successfully fetched");
			response.setSuccess(true);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			}
	}

	@Override
	public ResponseEntity<ServerResponse> save(ReviewsAndRatings reviews) {
		
		User isUserExist = userRepository.findByUserCode(reviews.getUserCode());
		ProfessionalUser professionalUserData = professionalUserRepository.findByUser_UserCode(reviews.getProfessionalUserCode());
		String reviewCode = "REV" + System.currentTimeMillis();
		
		ReviewsAndRatings reviewAndRatings = new ReviewsAndRatings();
		
		reviewAndRatings = reviewsAndRatingsRepository.findByProfessionalUserCodeAndUserCode(reviews.getProfessionalUserCode(), reviews.getUserCode());
				
		if (reviewAndRatings != null) {
			response.setData("");
			response.setStatus(Constants.OK);
			response.setMessage("Sorry, you can't rate more than once");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		} else if (isUserExist == null) {
			response.setData("");
			response.setStatus(Constants.OK);
			response.setMessage("This user does not exist in our record");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		} else if (professionalUserData == null) {
			response.setData("");
			response.setStatus(Constants.OK);
			response.setMessage("This proffesional user does not exist in our record");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
			} else {
				ReviewsAndRatings newReviews = new ReviewsAndRatings();
				
				newReviews.setReviewId(reviewCode);
				newReviews.setRating(reviews.getRating());
				newReviews.setReview(reviews.getReview());
				newReviews.setUserCode(reviews.getUserCode());
				newReviews.setProfessionalUserCode(reviews.getProfessionalUserCode());
				
		        ReviewsAndRatings savedReview = reviewsAndRatingsRepository.save(newReviews);

				
				Long totalReview = reviewsAndRatingsRepository.countRatingByProfessionalUserCode(reviews.getProfessionalUserCode());
				
				Long reviewOne = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(reviews.getProfessionalUserCode(), 1);
				
				Long reviewTwo = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(reviews.getProfessionalUserCode(), 2);
				
				Long reviewThree = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(reviews.getProfessionalUserCode(), 3);
				
				Long reviewFour = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(reviews.getProfessionalUserCode(), 4);
				
				Long reviewFive = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(reviews.getProfessionalUserCode(), 5);
							
				Long totalProductSum = (reviewOne * 1) + (reviewTwo * 2) + (reviewThree * 3) + (reviewFour * 4) + (reviewFive * 5);
				
				
				double averageRating = totalProductSum / totalReview;
				
				professionalUserData.setAverageRating(averageRating);
				
				professionalUserRepository.save(professionalUserData);
		        
		        response.setData(savedReview);
				response.setStatus(Constants.CREATED);
				response.setMessage("Review and Rating was successfully created");
				response.setSuccess(true);
				
		        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			}
		}

	
	@Override
	public ResponseEntity<ServerResponse> updateReview(ReviewsAndRatings reviews) {
		ReviewsAndRatings isReviewExist = reviewsAndRatingsRepository.findByUserCode(reviews.getReviewId());
		
		if (isReviewExist == null) {
			response.setData("");
			response.setStatus(Constants.OK);
			response.setMessage("This review does not exist in our record");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		} else {
				ReviewsAndRatings updateReviews = new ReviewsAndRatings();
				
				updateReviews.setReviewId(reviews.getReviewId());
				updateReviews.setRating(reviews.getRating());
				updateReviews.setReview(reviews.getReview());
				updateReviews.setUserCode(reviews.getUserCode());
				updateReviews.setProfessionalUserCode(reviews.getProfessionalUserCode());
				
		        ReviewsAndRatings savedReview = reviewsAndRatingsRepository.save(updateReviews);
		        
		        response.setData(savedReview);
				response.setStatus(Constants.CREATED);
				response.setMessage("Review and Rating was successfully created");
				response.setSuccess(true);
				
		        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			}
	}


	@Override
	public ResponseEntity<ServerResponse> countReview(String usercode) {
		ReviewRatingDto ratingRecords = new ReviewRatingDto();
		Long totalReview = reviewsAndRatingsRepository.countRatingByProfessionalUserCode(usercode);
		
		if (totalReview == null || totalReview <1) {
			
			ratingRecords.setAverage(0);
			ratingRecords.setFiveRating((long) 0);
			ratingRecords.setFiveRatingPercent(0);
			ratingRecords.setFourRating((long) 0);
			ratingRecords.setFourRatingPercent(0);
			ratingRecords.setThreeRating((long) 0);
			ratingRecords.setThreeRatingPercent(0);
			ratingRecords.setTwoRating((long) 0);
			ratingRecords.setTwoRatingPercent(0);
			ratingRecords.setOneRating((long) 0);
			ratingRecords.setOneRatingPercent(0);
			ratingRecords.setProfessionalUserCode(usercode);
			ratingRecords.setTotalRating((long) 0);
			
			 response.setData(ratingRecords);
				response.setStatus(Constants.OK);
				response.setMessage("User not rated yet");
				response.setSuccess(true);
		} else {
			
			Long reviewOne = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(usercode, 1);
			
			Long reviewTwo = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(usercode, 2);
			
			Long reviewThree = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(usercode, 3);
			
			Long reviewFour = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(usercode, 4);
			
			Long reviewFive = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(usercode, 5);
						
			Long totalProductSum = (reviewOne * 1) + (reviewTwo * 2) + (reviewThree * 3) + (reviewFour * 4) + (reviewFive * 5);
			
			double averageRating = totalProductSum / totalReview;
			
			double calFive = (reviewFive / totalReview) * 100;
			double calFour = (reviewFour / totalReview) * 100;
			double calThree = (reviewThree / totalReview) * 100;
			double calTwo = (reviewTwo / totalReview) * 100;
			double calOne = (reviewOne / totalReview) * 100;
			ratingRecords.setAverage(averageRating);
			ratingRecords.setFiveRating(reviewFive);
			ratingRecords.setFiveRatingPercent(calFive);
			ratingRecords.setFourRating(reviewFour);
			ratingRecords.setFourRatingPercent(calFour);
			ratingRecords.setThreeRating(reviewThree);
			ratingRecords.setThreeRatingPercent(calThree);
			ratingRecords.setTwoRating(reviewTwo);
			ratingRecords.setTwoRatingPercent(calTwo);
			ratingRecords.setOneRating(reviewOne);
			ratingRecords.setOneRatingPercent(calOne);
			ratingRecords.setProfessionalUserCode(usercode);
			ratingRecords.setTotalRating(totalReview);
			      
	        response.setData(ratingRecords);
			response.setStatus(Constants.OK);
			response.setMessage("Records were fetched successfully");
			response.setSuccess(true);
		}
		
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	} 

//	@Override
//	public ResponseEntity<ServerResponse> averageReview(String reviews) {
//		
//		
//		ReviewRatingDto ratingRecords = new ReviewRatingDto();
//		
//		Long totalReview = reviewsAndRatingsRepository.countRatingByProfessionalUserCode(reviews);
//		
//		Long reviewOne = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(reviews, 1);
//		
//		Long reviewTwo = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(reviews, 2);
//		
//		Long reviewThree = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(reviews, 3);
//		
//		Long reviewFour = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(reviews, 4);
//		
//		Long reviewFive = reviewsAndRatingsRepository.countByProfessionalUserCodeAndRating(reviews, 5);
//					
//		Long totalProductSum = reviewOne + reviewTwo + reviewThree + reviewFour + reviewFive;
//		
//		Long averageRating = totalProductSum / totalReview;
//		
//		ratingRecords.setAverage(averageRating);
//		ratingRecords.setFiveRating(reviewFive);
//		ratingRecords.setFourRating(reviewFour);
//		ratingRecords.setThreeRating(reviewThree);
//		ratingRecords.setTwoRating(reviewTwo);
//		ratingRecords.setOneRating(reviewOne);
//		ratingRecords.setProfessionalUserCode(reviews);
//		ratingRecords.setTotalRating(totalReview);
//		      
//        response.setData(ratingRecords);
//		response.setStatus(Constants.OK);
//		response.setMessage("Records were fetched successfully");
//		response.setSuccess(true);
//		
//        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
//
//	} 
}
