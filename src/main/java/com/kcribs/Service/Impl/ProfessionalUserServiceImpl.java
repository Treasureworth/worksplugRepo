package com.kcribs.Service.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kcribs.Configuration.KcribsEmailLib;
import com.kcribs.Constants.Constants;
import com.kcribs.DTO.ProfessionalDto;
import com.kcribs.DTO.ProfessionalUserUpdateDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.Model.ProfessionalUser;
import com.kcribs.Model.Role;
import com.kcribs.Model.User;
import com.kcribs.Repository.ProfessionalUserRepository;
import com.kcribs.Repository.RoleRepository;
import com.kcribs.Repository.UserRepository;
import com.kcribs.Service.ProfessionalUserService;

@Service
public class ProfessionalUserServiceImpl implements ProfessionalUserService{

private HttpHeaders responseHeaders = new HttpHeaders();
	
	ServerResponse response = new ServerResponse();
	
	@Autowired
	private ProfessionalUserRepository professionalUserRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	 @Autowired
	    private RoleRepository roleRepository;

	public ResponseEntity<ServerResponse> findAll() {
		List<ProfessionalUser> list = new ArrayList<>();
		professionalUserRepository.findAll().iterator().forEachRemaining(list::add);
		 	
				response.setData(list);
				response.setStatus(Constants.OK);
				response.setMessage("Successfully fetched user records");
				response.setSuccess(true);
				
		        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}

	@Override
	public ProfessionalUser findByEmailOrPhone(String user) {
		return professionalUserRepository.findByUser_EmailOrUser_PhoneNumber(user, user);
	}
	
	@Override
    public ResponseEntity<ServerResponse> save(ProfessionalDto user) {
		String userCode = "PRO" + System.currentTimeMillis();

		Role professionalRole = roleRepository.findByName("PROFESSIONAL");
		
		User newUser = userRepository.findByEmail(user.getEmail());
				
		if(newUser != null) {
			response.setData(newUser);
			response.setStatus(Constants.OK);
			response.setMessage("The user with this Email already exist");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		} else {
			if(!(Constants.isValidEmail(user.getEmail())) ) {
				response.setData("");
				response.setStatus(Constants.BAD_REQUEST);
				response.setMessage("The Email is not valid");
				response.setSuccess(false);
				return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			} else {
			    long activationCode = System.currentTimeMillis();

			    User userDetail = new User();
			    String email = user.getEmail();
			    userDetail.setEmail(email);
			    userDetail.setName(user.getBusinessName());
			    userDetail.setPassword(bcryptEncoder.encode(user.getPassword()));
			    userDetail.setEnabled(false);
			    userDetail.setRole(professionalRole);
			    userDetail.setActivationCode(activationCode);
			    userDetail.setPhoneNumber(user.getPhoneNumber());
			    userDetail.setUserCode(userCode);
			    userDetail.setProfilePicture("https://res.cloudinary.com/worksplug/image/upload/v1554895831/resources/avatar.png");
			    userDetail.setStateOfResidence(user.getStateOfResidence());
			    
			    String businessName = user.getBusinessName();
			    
			    ProfessionalUser proUser = new ProfessionalUser();
			    proUser.setBusinessName(businessName);
			    proUser.setBusinessType(user.getBusinessType());
			    proUser.setCategory(user.getCategory());
			   	proUser.setAbout(user.getAbout());
			    proUser.setMinCost(user.getMinCost());
			    proUser.setMaxCost(user.getMaxCost());
			    proUser.setServices(user.getServices());
			    proUser.setCoveredStates(user.getCoveredStates());
			    proUser.setAverageRating(0);
			    proUser.setUser(userDetail);
			    
			    ProfessionalUser savedProfessional = professionalUserRepository.save(proUser);
		        
		        response.setData(savedProfessional);
				response.setStatus(Constants.CREATED);
				response.setMessage("Account was successfully created");
				response.setSuccess(true);
			    
			    try {
					KcribsEmailLib kcribsEmailLib = new KcribsEmailLib();
					String htmlbody = kcribsEmailLib.htmlRegisterProfessional(businessName, activationCode);
					new KcribsEmailLib(email, "Business Registration", htmlbody).sendMail();

				} catch (Exception e) {
					response.setData(e.getMessage());
					response.setStatus(Constants.FAIL);
					response.setMessage("Email Failed");
					response.setSuccess(false);
					return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
				}
				
		        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			}
		}
    }


	@Override
	public ResponseEntity<ServerResponse> priceRange(int price) {
//		professionalUserRepository.findByMinCostAndMaxCostBetween(mincost, maxcost).iterator().forEachRemaining(list::add);
		Collection<ProfessionalUser> professionalUsers = professionalUserRepository.findByMinCostLessThanEqualAndMaxCostGreaterThanEqual(price, price);
		
		System.out.println(professionalUsers);
		if (professionalUsers.size() < 1) {
			response.setData(professionalUsers);
			response.setStatus(Constants.OK); 
			response.setMessage("No professionalUsers found");
			response.setSuccess(false);
		} else {
			response.setData(professionalUsers);
			response.setStatus(Constants.OK);
			response.setMessage("Price Range fetched");
			response.setSuccess(true);
		}
		
		
        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}

	@Override
	public ResponseEntity<ServerResponse> update(ProfessionalUserUpdateDto user) {
		String userCode = user.getUserCode();
		
		User updateUser = userRepository.findByUserCode(userCode);
				
		if(updateUser == null) {
			response.setData(updateUser);
			response.setStatus(Constants.OK);
			response.setMessage("This user does not exist in our record");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		} else {
			    ProfessionalUser proUser = new ProfessionalUser();
			    proUser = professionalUserRepository.findByUser_UserCode(user.getUserCode());
			    
			    proUser.setBusinessName(user.getBusinessName());
			    proUser.setBusinessType(user.getBusinessType());
			    
			    proUser.setCoveredStates(user.getCoveredStates());
			    proUser.setAbout(user.getAbout());
			    proUser.setMinCost(user.getMinCost());
			    proUser.setMaxCost(user.getMaxCost());
			    updateUser.setName(user.getBusinessName());
			    proUser.setUser(updateUser);
			    
		        ProfessionalUser savedProfessional = professionalUserRepository.save(proUser);
		        
		        
		        response.setData(savedProfessional);
				response.setStatus(Constants.CREATED);
				response.setMessage("Account was successfully updated");
				response.setSuccess(true);
				
		        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			}

	}

	@Override
	public ResponseEntity<ServerResponse> findByUserCodeOrEmail(String usercode) {
//		User userRecord = userRepository.findByUserCodeOrEmail(usercode, usercode);
//		User userRecordByUserCode = userRepository.findByUserCode(usercode);
		ProfessionalUser userRecord = professionalUserRepository.findByUser_UserCode(usercode);
//		User userRecordByUserCode = userRepository.findByUserCode(usercode);
		if (usercode == null || usercode.isEmpty()) {
			response.setData("");
			response.setStatus(Constants.OK);
			response.setMessage("The User Code was empty, please supply a valid user code");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
		}
		if (userRecord == null) {
				response.setData("");
				response.setStatus(Constants.OK);
				response.setMessage("This user does not exist");
				response.setSuccess(false);
				return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
			} else {
				response.setData(userRecord);
				response.setStatus(Constants.OK);
				response.setMessage("User record successfully fetched");
				response.setSuccess(true);
				return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			}
	}

	@Override
	public ResponseEntity<ServerResponse> findByCategory(String category) {
		if (category == null) {
			response.setData("");
			response.setStatus(Constants.OK);
			response.setMessage("Please select a valid service");
			response.setSuccess(false);
		}
	List<ProfessionalUser> proUser = new  ArrayList<>();
	professionalUserRepository.findByCategory(category).iterator().forEachRemaining(proUser::add);
	
	if(proUser.isEmpty()) {
		response.setData("");
		response.setStatus(Constants.OK);
		response.setMessage("No professional has registered for this category");
		response.setSuccess(false);
	} else {
		response.setData(proUser);
		response.setStatus(Constants.OK);
		response.setMessage("Professionals found for the category selected");
		response.setSuccess(true);
	}
	
	return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}

	@Override
	public ResponseEntity<ServerResponse> findByServices(String services) {
		if (services == null) {
			response.setData("");
			response.setStatus(Constants.OK);
			response.setMessage("Please select a valid service");
			response.setSuccess(false);
		}
	List<ProfessionalUser> proUser = new  ArrayList<>();
	professionalUserRepository.findByServices(services).iterator().forEachRemaining(proUser::add);
	
	if(proUser.isEmpty()) {
		response.setData("");
		response.setStatus(Constants.OK);
		response.setMessage("No professional has registered for this service");
		response.setSuccess(false);
	} else {
		response.setData(proUser);
		response.setStatus(Constants.OK);
		response.setMessage("Professionals found for the service selected");
		response.setSuccess(true);
	}
	
	return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}

}
