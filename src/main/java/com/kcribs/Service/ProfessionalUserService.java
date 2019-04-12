package com.kcribs.Service;

import org.springframework.http.ResponseEntity;

import com.kcribs.DTO.ProfessionalDto;
import com.kcribs.DTO.ProfessionalUserUpdateDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.Model.ProfessionalUser;

public interface ProfessionalUserService {
    
    ProfessionalUser findByEmailOrPhone(String user);
    
    ResponseEntity<ServerResponse>  findByUserCodeOrEmail(String usercode);
    
    ResponseEntity<ServerResponse> findAll();
	
	ResponseEntity<ServerResponse> priceRange(int price);

	ResponseEntity<ServerResponse> save(ProfessionalDto user);
	
	ResponseEntity<ServerResponse> update(ProfessionalUserUpdateDto user);	

	ResponseEntity<ServerResponse> findByCategory(String category);	

	ResponseEntity<ServerResponse> findByServices(String services);	
    
}
