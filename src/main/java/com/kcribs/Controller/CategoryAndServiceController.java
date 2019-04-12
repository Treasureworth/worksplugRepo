package com.kcribs.Controller;

import java.util.List;

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
import com.kcribs.DTO.CategoryDto;
import com.kcribs.DTO.OfferedServiceDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.Model.Category;
import com.kcribs.Service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/categoryandservice")
public class CategoryAndServiceController {

	private HttpHeaders responseHeaders = new HttpHeaders();

	@Autowired
	CategoryService categoryService;
	
	ServerResponse response = new ServerResponse();
	
	@Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
	 @RequestMapping(value="/savecategoryandservice", method = RequestMethod.POST)
	    public ResponseEntity<ServerResponse> saveCategoryAndServices(@RequestHeader("Authorization") String authorization, @RequestBody List<Category> category){
	        try {
	        	return categoryService.saveCategoryAndServices(category);
	        } catch(Exception e) {
	        	response.setData("");
	        	response.setMessage("Something went wrong, Please try again");
	        	response.setStatus(Constants.FAIL);
	        	response.setSuccess(false);
	        }
	        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	    }
	
	@Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
	 @RequestMapping(value="/savecategory", method = RequestMethod.POST)
	    public ResponseEntity<ServerResponse> saveCategory(@RequestHeader("Authorization") String authorization, @RequestBody CategoryDto category){
	        try {
	        	return categoryService.save(category);
	        } catch(Exception e) {
	        	response.setData("");
	        	response.setMessage("Something went wrong, Please try again");
	        	response.setStatus(Constants.FAIL);
	        	response.setSuccess(false);
	        }
	        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	    }
	
	@Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
	 @RequestMapping(value="/saveofferedservice", method = RequestMethod.POST)
	    public ResponseEntity<ServerResponse> saveOfferedService(@RequestHeader("Authorization") String authorization, @RequestBody OfferedServiceDto request){
	        try {
	        	return categoryService.saveOfferedServices(request);
	        } catch(Exception e) {
	        	response.setData("");
	        	response.setMessage("Something went wrong, Please try again");
	        	response.setStatus(Constants.FAIL);
	        	response.setSuccess(false);
	        }
	        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	    }
	
	@Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
	 @RequestMapping(value="/savebulkcategory", method = RequestMethod.POST)
	    public ResponseEntity<ServerResponse> saveCategoryBulk(@RequestHeader("Authorization") String authorization, @RequestBody List<CategoryDto> request){
	        try {
	        	return categoryService.saveMany(request);
	        } catch(Exception e) {
	        	response.setData("");
	        	response.setMessage("Something went wrong, Please try again");
	        	response.setStatus(Constants.FAIL);
	        	response.setSuccess(false);
	        }
	        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	    }
	
//	@Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
	 @RequestMapping(value="/getcategoryandservice", method = RequestMethod.GET)
	 public ResponseEntity<ServerResponse> getCategoryAndServices(){
	
	        try {
	        	
	        	return categoryService.findAll();
	        } catch(Exception e) {
	        	response.setData("");
	        	response.setMessage("Something went wrong, Please try again");
	        	response.setStatus(Constants.FAIL);
	        	response.setSuccess(false);
	        }
	        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	    }
	
	@Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFESSIONAL"})
	 @RequestMapping(value="/getcategorybyname/{category}", method = RequestMethod.GET)
	 public ResponseEntity<ServerResponse> getCategoryByNameOrCode(@RequestHeader("Authorization") String authorization, @PathVariable("category") String request){
	        try {
	        	return categoryService.getCategoryByNameOrCode(request);
	        } catch(Exception e) {
	        	response.setData("");
	        	response.setMessage("Something went wrong, Please try again");
	        	response.setStatus(Constants.FAIL);
	        	response.setSuccess(false);
	        }
	        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	    }
	
	@RequestMapping(value="/getservice/{service}", method = RequestMethod.GET)
	public ResponseEntity<ServerResponse> getService(@PathVariable("service") String request){
		try {
			return categoryService.getService(request);
		} catch(Exception e) {
			response.setData("");
			response.setMessage("Something went wrong, Please try again");
			response.setStatus(Constants.FAIL);
			response.setSuccess(false);
		}
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}
}
