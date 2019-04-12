package com.kcribs.Service;

import org.springframework.http.ResponseEntity;

import com.kcribs.DTO.ResetPasswordDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.DTO.SetPasswordDto;
import com.kcribs.DTO.UserDto;
import com.kcribs.Model.User;

public interface UserService {

    ResponseEntity<ServerResponse> save(UserDto user);
    
//    ResponseEntity<ServerResponse> login(LoginUser loginUser);
    
    ResponseEntity<ServerResponse> findAll();

    ResponseEntity<ServerResponse>  findByUserCodeOrEmail(String usercode);
    
    User findByEmailOrPhone(String user);
    
    ResponseEntity<ServerResponse>  activateAccount(Long code);

	ResponseEntity<ServerResponse> resetPassword(ResetPasswordDto request);
	
	ResponseEntity<ServerResponse> setPassword(SetPasswordDto request);

}
