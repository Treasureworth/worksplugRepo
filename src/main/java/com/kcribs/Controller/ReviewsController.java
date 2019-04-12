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

import com.kcribs.Constants.Constants;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.Model.ReviewsAndRatings;
import com.kcribs.Service.ReviewsAndRatingsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reviewsandratings")
public class ReviewsController {

	private HttpHeaders responseHeaders = new HttpHeaders();

	@Autowired
    private ReviewsAndRatingsService reviewsAndRatingsService;
	
	ServerResponse response = new ServerResponse();

    @Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/createreview", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> createReview(@RequestHeader("Authorization") String authorization, @RequestBody ReviewsAndRatings reviews ){
        
        try {
        	return reviewsAndRatingsService.save(reviews);
        } catch(Exception e) {
        	response.setData("");
        	response.setMessage("Something went wrong, Please try again");
        	response.setStatus(Constants.FAIL);
        	response.setSuccess(false);
        }
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

    }
    
    @Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
//  @PreAuthorize("hasRole('ADMIN')")
  @RequestMapping(value="/clientview/{usercode}", method = RequestMethod.GET)
  public ResponseEntity<ServerResponse> clientViewReview(@RequestHeader("Authorization") String authorization, @PathVariable(value = "usercode") String userCode){
      
      try {
      	return reviewsAndRatingsService.findByUserCode(userCode);
      } catch(Exception e) {
      	response.setData("");
      	response.setMessage("Something went wrong, Please try again");
      	response.setStatus(Constants.FAIL);
      	response.setSuccess(false);
      }
      return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

  }
    
    @Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
//  @PreAuthorize("hasRole('ADMIN')")
  @RequestMapping(value="/professionalview/{professionalusercode}", method = RequestMethod.GET)
  public ResponseEntity<ServerResponse> professionalViewReview(@RequestHeader("Authorization") String authorization, @PathVariable(value = "professionalusercode") String professionalUserCode){
      
      try {
      	return reviewsAndRatingsService.findByProfessionalUserCode(professionalUserCode);
      } catch(Exception e) {
      	response.setData("");
      	response.setMessage("Something went wrong, Please try again");
      	response.setStatus(Constants.FAIL);
      	response.setSuccess(false);
      }
      return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

  }
    
    @Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
//  @PreAuthorize("hasRole('ADMIN')")
  @RequestMapping(value="/updatereview", method = RequestMethod.POST)
  public ResponseEntity<ServerResponse> updateReview(@RequestHeader("Authorization") String authorization, @RequestBody ReviewsAndRatings reviews ){
      
      try {
      	return reviewsAndRatingsService.updateReview(reviews);
      } catch(Exception e) {
      	response.setData("");
      	response.setMessage("Something went wrong, Please try again");
      	response.setStatus(Constants.FAIL);
      	response.setSuccess(false);
      }
      return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

  }
    
//    @Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
//  @PreAuthorize("hasRole('ADMIN')")
  @RequestMapping(value="/countratings/{professionalusercode}", method = RequestMethod.GET)
  public ResponseEntity<ServerResponse> fetchRatingsAverage(@PathVariable(value = "professionalusercode") String professionalUserCode ){
      
      try {
      	return reviewsAndRatingsService.countReview(professionalUserCode);
      } catch(Exception e) {
      	response.setData("");
      	response.setMessage("Something went wrong, Please try again"+ e.getMessage());
      	response.setStatus(Constants.FAIL);
      	response.setSuccess(false);
      }
      return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

  }
    
   
}
