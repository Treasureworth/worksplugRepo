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
import com.kcribs.DTO.ResetPasswordDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.DTO.SetPasswordDto;
import com.kcribs.DTO.UserDto;
import com.kcribs.Service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/client")
public class UserController {

	@Autowired
    private UserService userService;
	
	private HttpHeaders responseHeaders = new HttpHeaders();
	
	ServerResponse response = new ServerResponse();

    @Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFFESSIONAL"})
//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/getallusers", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> listUser(@RequestHeader("Authorization") String authorization ){
        
        try {
        	return userService.findAll();
        } catch(Exception e) {
        	response.setData("");
        	response.setMessage("Something went wrong, Please try again");
        	response.setStatus(Constants.FAIL);
        	response.setSuccess(false);
        }
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

    }

    @Secured({"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_PROFFESSIONAL"})
    ////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/getsingleuser/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse>  getSingleUser(@RequestHeader("Authorization") String authorization, @PathVariable(value = "id") String userCode){
        try {
        	return userService.findByUserCodeOrEmail(userCode);
        } catch (Exception e) {
        	response.setData("");
        	response.setMessage("Something went wrong, Please try again");
        	response.setStatus(Constants.FAIL);
        	response.setSuccess(false);
        	return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
        }
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> saveUser(@RequestBody UserDto user){
        try {
        	return userService.save(user);
        } catch(Exception e) {
        	response.setData("");
        	response.setMessage("Something went wrong, Please try again");
        	response.setStatus(Constants.FAIL);
        	response.setSuccess(false);
        }
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

    }

    @RequestMapping(value="/resetpassword", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> resetPassword(@RequestBody ResetPasswordDto request){
        try {
        	return userService.resetPassword(request);
        } catch(Exception e) {
        	response.setData("");
        	response.setMessage("Something went wrong, Please try again");
        	response.setStatus(Constants.FAIL);
        	response.setSuccess(false);
        }
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

    }
    
    @RequestMapping(value="/setpassword", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> setPassword(@RequestBody SetPasswordDto request){
    	try {
    		return userService.setPassword(request);
    	} catch(Exception e) {
    		response.setData("");
    		response.setMessage("Something went wrong, Please try again");
    		response.setStatus(Constants.FAIL);
    		response.setSuccess(false);
    	}
    	return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
    	
    }
    
    @RequestMapping(value="/activateaccount/{code}", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> activateAccount(@PathVariable(value = "code") Long code){

        try {
        	return userService.activateAccount(code);
        } catch(Exception e) {
        	response.setData("");
        	response.setMessage("Something went wrong, Please try again");
        	response.setStatus(Constants.FAIL);
        	response.setSuccess(false);
        }
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

    }

}