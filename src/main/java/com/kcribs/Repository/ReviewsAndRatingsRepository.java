package com.kcribs.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kcribs.Model.ReviewsAndRatings;

public interface ReviewsAndRatingsRepository extends CrudRepository<ReviewsAndRatings, String> {
	
	ReviewsAndRatings findByUserCode(String usercode);
	
	ReviewsAndRatings findByProfessionalUserCode(String professionalusercode);
	
	ReviewsAndRatings findByReviewId(String reviewid);

	List<ReviewsAndRatings> findAllByUserCode(String usercode);

	List<ReviewsAndRatings> findAllByProfessionalUserCode(String professionalusercode);
	
	Long countByProfessionalUserCodeAndRating(String professionalusercode, int ratings);

	Long countRatingByProfessionalUserCode(String professionalusercode);
	
	Long countByProfessionalUserCode(int ratings);
	
	ReviewsAndRatings findByProfessionalUserCodeAndUserCode(String professionalusercode, String usercode);
	
	
}
