package com.kcribs.Controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kcribs.Configuration.TokenProvider;
import com.kcribs.Constants.Constants;
import com.kcribs.DTO.LoginResponse;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.Model.AuthToken;
import com.kcribs.Model.LoginUser;
import com.kcribs.Model.User;
import com.kcribs.Service.ProfessionalUserService;
import com.kcribs.Service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class AuthenticationController {


	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;
    
    @Autowired
    UserService userService;
    
    @Autowired 
    ProfessionalUserService professionalUserService;
    
    private HttpHeaders responseHeaders = new HttpHeaders();

    @RequestMapping(value = "/login/user", method = RequestMethod.POST)
    public ResponseEntity<ServerResponse> authenticate(@RequestBody LoginUser loginUser) throws AuthenticationException {
    	
    	ServerResponse response = new ServerResponse();
    	LoginResponse userDtoResponse = new LoginResponse();
        try {
        	final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getEmail(),
                            loginUser.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);
            AuthToken accessToken = new AuthToken(token);
            
            User user = userService.findByEmailOrPhone(loginUser.getEmail());
            if (user.isEnabled()) {
            	userDtoResponse.setToken(accessToken);
                userDtoResponse.setUser(user);
                
                response.setData(userDtoResponse);
                response.setMessage("Login was successfull");
                response.setSuccess(true);
                response.setStatus(Constants.OK);
            } else {

                response.setData("");
                response.setMessage("Account has not been activated, check your email for activation link");
                response.setSuccess(false);
                response.setStatus(Constants.OK);
            }
          
            
        } catch(Exception e) {
        	response.setData("An error occured " + e.getMessage());
            response.setMessage("Email or Password is not correct");
            response.setSuccess(false);
            response.setStatus(Constants.FAIL);
        }
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
 
    }
    
}
