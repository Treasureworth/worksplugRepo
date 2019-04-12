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
import com.kcribs.DTO.ProfessionalDto;
import com.kcribs.DTO.ProfessionalUserUpdateDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.Service.ProfessionalUserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/professional")
public class ProfessionalController {

	@Autowired
    private ProfessionalUserService professionalUserService;

private HttpHeaders responseHeaders = new HttpHeaders();
	
	ServerResponse response = new ServerResponse();

 
//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/getallusers", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> listUser(@RequestHeader("Authorization") String authorization ){
        
        try {
        	return professionalUserService.findAll();
        } catch(Exception e) {
        	response.setData("");
        	response.setMessage("Something went wrong, Please try again");
        	response.setStatus(Constants.FAIL);
        	response.setSuccess(false);
        }
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

    }


    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> saveUser(@RequestBody ProfessionalDto user){
        try {
        	return professionalUserService.save(user);
        } catch(Exception e) {
        	response.setData("");
        	response.setMessage("Something went wrong, Please try again");
        	response.setStatus(Constants.FAIL);
        	response.setSuccess(false);
        }
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
    }
    
    @Secured({"ROLE_ADMIN", "ROLE_PROFESSIONAL"})
    @RequestMapping(value="/updateprofessionaluser", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> updateUser(@RequestHeader("Authorization") String authorization, @RequestBody ProfessionalUserUpdateDto user){
    	try {
    		return professionalUserService.update(user);
    	} catch(Exception e) {
    		response.setData("");
    		response.setMessage("Something went wrong, Please try again");
    		response.setStatus(Constants.FAIL);
    		response.setSuccess(false);
    	}
    	return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
    }

   
//    @Secured({"ROLE_ADMIN", "ROLE_PROFESSIONAL", "ROLE_CLIENT"})
    @RequestMapping(value="/pricerange/{price}", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> priceRange( @PathVariable(value = "price") int price){

        try {
        	return professionalUserService.priceRange((int) price);
        } catch(Exception e) {
        	response.setData("");
        	response.setMessage("Something went wrong, Please try again "+ e.getMessage());
        	response.setStatus(Constants.FAIL);
        	response.setSuccess(false);
        }
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

    }
    
//    @Secured({"ROLE_ADMIN", "ROLE_PROFESSIONAL", "ROLE_CLIENT"})
    @RequestMapping(value="/getprofessionaluser/{usercode}", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> getSingleUser(@PathVariable(value = "usercode") String usercode){
    	
    	try {
    		return professionalUserService.findByUserCodeOrEmail(usercode);
    	} catch(Exception e) {
    		response.setData("");
    		response.setMessage("Something went wrong, Please try again "+ e.getMessage());
    		response.setStatus(Constants.FAIL);
    		response.setSuccess(false);
    	}
    	return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
    	
    }
    
//    @Secured({"ROLE_ADMIN", "ROLE_PROFESSIONAL", "ROLE_CLIENT"})
    @RequestMapping(value="/getprofessionaluserbycategory/{category}", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> getProByCategory(@PathVariable(value = "category") String category){
    	
    	try {
    		return professionalUserService.findByCategory(category);
    	} catch(Exception e) {
    		response.setData("");
    		response.setMessage("Something went wrong, Please try again "+ e.getMessage());
    		response.setStatus(Constants.FAIL);
    		response.setSuccess(false);
    	}
    	return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
    	
    }
    
//    @Secured({"ROLE_ADMIN", "ROLE_PROFESSIONAL", "ROLE_CLIENT"})
    @RequestMapping(value="/getprofessionaluserbyservice/{service}", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> getProByService(@PathVariable(value = "service") String service){
    	
    	try {
    		return professionalUserService.findByServices(service);
    	} catch(Exception e) {
    		response.setData("");
    		response.setMessage("Something went wrong, Please try again "+ e.getMessage());
    		response.setStatus(Constants.FAIL);
    		response.setSuccess(false);
    	}
    	return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
    	
    }

}
