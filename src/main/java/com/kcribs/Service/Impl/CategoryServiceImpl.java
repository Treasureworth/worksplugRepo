package com.kcribs.Service.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kcribs.Constants.Constants;
import com.kcribs.DTO.CategoryDto;
import com.kcribs.DTO.OfferedServiceDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.Model.Category;
import com.kcribs.Model.OfferedServices;
import com.kcribs.Repository.CategoryRepository;
import com.kcribs.Repository.ServicesRepository;
import com.kcribs.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ServicesRepository serviceRepository;

	
	private HttpHeaders responseHeaders = new HttpHeaders();
	
	ServerResponse response = new ServerResponse();


	@Override
	public ResponseEntity<ServerResponse> findAll() {
		
		try {
			List<Category> list = new ArrayList<>();
			
			
					categoryRepository.findAll().iterator().forEachRemaining(list::add);
					response.setData(list);
					response.setStatus(Constants.OK);
					response.setMessage("Successfully fetched services and categories");
					response.setSuccess(true);
			        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		} catch (Exception e) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Records could not be saved " + e.getMessage());
			response.setSuccess(false);
			
	        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}
		
	}

	@Override
	public ResponseEntity<ServerResponse> save(CategoryDto request) {
		
		if (request.getCategoryName() == null || request.getCategoryName().isEmpty()) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Please enter category name");
			response.setSuccess(false);	
	        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}
		try {
			
			Category category = categoryRepository.findByName(request.getCategoryName());
			if (category != null) {
				response.setData("");
				response.setStatus(Constants.FAIL);
				response.setMessage(category.getName() + " already exit");
				response.setSuccess(false);	
		        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			}
			
			category  = new Category();
			category.setName(request.getCategoryName());
			
			categoryRepository.save(category);
			
			response.setData(category);
			response.setStatus(Constants.OK);
			response.setMessage("Successfully saved all categories");
			response.setSuccess(true);
			            

		} catch (Exception e) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Records could not be saved " + e.getMessage());
			response.setSuccess(false);	
		}
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}

	@Override
	public ResponseEntity<ServerResponse> saveMany(List<CategoryDto> request) {
		if (request == null || request.size() < 1) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Please provide category name");
			response.setSuccess(false);	
	        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}
		try {
			
			Set<Category> categories = new HashSet<>();
			for(CategoryDto dto : request) {
				Category category = categoryRepository.findByName(dto.getCategoryName());
				if (category != null) {
					response.setData("");
					response.setStatus(Constants.FAIL);
					response.setMessage(category.getName() + " already exit");
					response.setSuccess(false);	
			        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

				}
				
				category  = new Category();
				category.setName(dto.getCategoryName());
				categories.add(category);
			}
			
			
			categoryRepository.saveAll(categories);
			
			response.setData(categories);
			response.setStatus(Constants.OK);
			response.setMessage("Successfully saved all services and categories");
			response.setSuccess(true);
			            

		} catch (Exception e) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Records could not be saved " + e.getMessage());
			response.setSuccess(false);	
		}
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}

	@Override
	public ResponseEntity<ServerResponse> saveOfferedServices(OfferedServiceDto request) {
		if (request.getServiceName() == null || request.getServiceName().isEmpty()) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Please enter service name");
			response.setSuccess(false);	
	        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}
		try {
			
			Category category = categoryRepository.findByCategoryCode(request.getCategoryId());
			
			if (category == null) {
				response.setData("");
				response.setStatus(Constants.FAIL);
				response.setMessage("Category not found");
				response.setSuccess(false);	
		        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			}
			
			OfferedServices offeredServices = serviceRepository.findByName(request.getServiceName());
			
			if (offeredServices != null) {
				response.setData("");
				response.setStatus(Constants.FAIL);
				response.setMessage(offeredServices.getName() + " already exit");
				response.setSuccess(false);	
		        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			}
			
			offeredServices  = new OfferedServices();
			offeredServices.setName(request.getServiceName());
			offeredServices.setCategory(category);
			
			serviceRepository.save(offeredServices);
			
			response.setData(category);
			response.setStatus(Constants.OK);
			response.setMessage("Successfully saved all services and categories");
			response.setSuccess(true);
			            

		} catch (Exception e) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Records could not be saved " + e.getMessage());
			response.setSuccess(false);	
		}
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}

	@Override
	public ResponseEntity<ServerResponse> saveCategoryAndServices(List<Category> request) {

		try {
		
			Collection<Category> categories = new HashSet<>();
			for(Category data : request) {
				
				Category category = categoryRepository.findByName(data.getName());
				if (category != null) {
					response.setData("");
					response.setStatus(Constants.FAIL);
					response.setMessage(category.getName() + " already exit");
					response.setSuccess(false);	
			        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
				}
				
				
				category  = new Category();
     				category.setName(data.getName());
				
				Set<OfferedServices> offeredServices = new HashSet<>();
				for(OfferedServices offeredService : data.getOfferedServices()) {
					offeredService.setCategory(category);
					offeredServices.add(offeredService);
				}
				category.setOfferedServices(offeredServices);
				categories.add(category);
			}
			
			categoryRepository.saveAll(categories);
			
			response.setData(categories);
			response.setStatus(Constants.OK);
			response.setMessage("Successfully saved all services and categories");
			response.setSuccess(true);            

		} catch (Exception e) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Records could not be saved " + e.getMessage());
			response.setSuccess(false);	
		}
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}

	@Override
	public ResponseEntity<ServerResponse> getCategoryByNameOrCode(String request) {
		
		if (request == null || request.isEmpty()) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Please enter category name");
			response.setSuccess(false);	
	        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		} 
		
		try {
			Category category = categoryRepository.findByCategoryCodeOrName(request, request);
			if (category == null) {
				response.setData("");
				response.setStatus(Constants.FAIL);
				response.setMessage("The Category with this name does not exist");
				response.setSuccess(false);	
		        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			}
			else {
				
				response.setData(category);
				response.setStatus(Constants.OK);
				response.setMessage("Successfully fetched record");
				response.setSuccess(true);

			}			            

		} catch (Exception e) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Records could not be fetched " + e.getMessage());
			response.setSuccess(false);	
		}
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}

	@Override
	public ResponseEntity<ServerResponse> getService(String request) {
		Collection<OfferedServices> services = new HashSet<>();
		services = serviceRepository.findByNameIgnoreCaseContaining(request);
		if (request == null) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Input a service to search");
			response.setSuccess(false);	
		}
		if(services == null || services.size() < 1) {
			response.setData("");
			response.setStatus(Constants.OK);
			response.setMessage("Services were not found");
			response.setSuccess(false);	
		} else {
			response.setData(services);
			response.setStatus(Constants.OK);
			response.setMessage("Services found");
			response.setSuccess(true);	
		}
		 return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}

}
