package com.kcribs.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.kcribs.DTO.CategoryDto;
import com.kcribs.DTO.OfferedServiceDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.Model.Category;

public interface CategoryService {

	 ResponseEntity<ServerResponse> save(CategoryDto category);
	 
	 ResponseEntity<ServerResponse> saveMany(List<CategoryDto> categoryList);
	    
	 ResponseEntity<ServerResponse> findAll();
	    
	 ResponseEntity<ServerResponse> saveOfferedServices(OfferedServiceDto request);

	ResponseEntity<ServerResponse> saveCategoryAndServices(List<Category> request);

	ResponseEntity<ServerResponse> getCategoryByNameOrCode(String request);
	
	ResponseEntity<ServerResponse> getService(String request);
	

}
