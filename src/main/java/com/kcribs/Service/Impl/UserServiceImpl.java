package com.kcribs.Service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kcribs.Configuration.KcribsEmailLib;
import com.kcribs.Constants.Constants;
import com.kcribs.DTO.ResetPasswordDto;
import com.kcribs.DTO.ServerResponse;
import com.kcribs.DTO.SetPasswordDto;
import com.kcribs.DTO.UserDto;
import com.kcribs.Model.ClientUser;
import com.kcribs.Model.Role;
import com.kcribs.Model.User;
import com.kcribs.Repository.ClientUserRepository;
import com.kcribs.Repository.RoleRepository;
import com.kcribs.Repository.UserRepository;
import com.kcribs.Service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	private HttpHeaders responseHeaders = new HttpHeaders();
	
	ServerResponse response = new ServerResponse();
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ClientUserRepository clientUserRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
    private RoleRepository roleRepository;
	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);	
		
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
		} else {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public ResponseEntity<ServerResponse> findAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		 	
				response.setData(list);
				response.setStatus(Constants.OK);
				response.setMessage("Successfully fetched user records");
				response.setSuccess(true);
				
		        return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}

	@Override
	public ResponseEntity<ServerResponse>  findByUserCodeOrEmail(String usercode) {
		
//		User userRecord = userRepository.findByUserCodeOrEmail(usercode, usercode);
//		User userRecordByUserCode = userRepository.findByUserCode(usercode);
		ClientUser userRecord = clientUserRepository.findByUser_UserCode(usercode);
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
	public User findByEmailOrPhone(String user) {
		return userRepository.findByEmailOrPhoneNumber(user, user);
	}
	
	@Override
    public ResponseEntity<ServerResponse> save(UserDto user) {

		Role clientRole = roleRepository.findByName("CLIENT");
		
		User newUser = userRepository.findByEmailOrPhoneNumber(user.getEmail(), user.getPhoneNumber());
		
		if(newUser != null) {
			response.setData(newUser);
			response.setStatus(Constants.OK);
			response.setMessage("The user with this Email or Phone Number already exist");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		} else {
			if(!(Constants.isValidEmail(user.getEmail())) || !(Constants.isValidPhone(user.getPhoneNumber())) ) {
				response.setData("");
				response.setStatus(Constants.BAD_REQUEST);
				response.setMessage("The Email or Phone Number is not valid");
				response.setSuccess(false);
				return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			} else {
			
				String userCode = "CLI" + System.currentTimeMillis();
				newUser = new User();
			    newUser.setUserCode(userCode);
			    
			    String email = user.getEmail();
			    
			    newUser.setEmail(email);
			    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			    newUser.setName(user.getFirstName());
			    newUser.setProfilePicture("https://res.cloudinary.com/worksplug/image/upload/v1554895831/resources/avatar.png");
			    
			    String name = user.getFirstName();
			 
			    newUser.setPhoneNumber(user.getPhoneNumber());
			    newUser.setRole(clientRole);
			    newUser.setEnabled(false);
			    newUser.setStateOfResidence(user.getStateOfResidence());
			    
			    long activationCode = System.currentTimeMillis();
			    newUser.setActivationCode(activationCode); 
			    
			    ClientUser clientUser = new ClientUser();
			    clientUser.setFirstName(name);
			    clientUser.setLastName(user.getLastName());
			    clientUser.setUser(newUser);
			    
			    clientUserRepository.save(clientUser);
		        
		        response.setData(clientUser);
				response.setStatus(Constants.CREATED);
				response.setMessage("Account was successfully created. Please check your email to activate your account");
				response.setSuccess(true);
				
				try {
					KcribsEmailLib kcribsEmailLib = new KcribsEmailLib();
					String htmlbody = kcribsEmailLib.htmlRegisterUsers(name, activationCode);
					new KcribsEmailLib(email, "User Registration", htmlbody).sendMail();

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
	public ResponseEntity<ServerResponse> activateAccount(Long request) {
		User user = new User();
		user = userRepository.findByActivationCode(request);
		
		if (request == null) {
			
			response.setData("");
			response.setStatus(Constants.BAD_REQUEST);
			response.setMessage("Please verify that you clicked the correct link");
			response.setSuccess(false);
		} else if (user == null) {
			response.setData("");
			response.setStatus(Constants.NOT_FOUND);
			response.setMessage("The activation code does not exist.");
			response.setSuccess(false);
		} else if(user.isEnabled()) {
			response.setData("");
			response.setStatus(Constants.OK);
			response.setMessage("This account was already activated.");
			response.setSuccess(false);
		} else if(user != null) {
			user.setEnabled(true);
			userRepository.save(user);
			
			response.setData(user);
			response.setStatus(Constants.OK);
			response.setMessage("Your acount has been activated please login.");
			response.setSuccess(true);
		}
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}

	@Override
	public ResponseEntity<ServerResponse> resetPassword(ResetPasswordDto request) {
		User user = new User();
		user = userRepository.findByEmail(request.getEmail());		
		if (request.getEmail() == null) {
			response.setData("");
			response.setStatus(Constants.BAD_REQUEST);
			response.setMessage("Email field can not be submitted empty.");
			response.setSuccess(false);
		} else if(!(Constants.isValidEmail(request.getEmail()))) {
			response.setData("");
			response.setStatus(Constants.BAD_REQUEST);
			response.setMessage("Please supply a valid email.");
			response.setSuccess(false);
		}else if (request.getEmail() != null) {
			
			long activationCode = System.currentTimeMillis();
			
			String email = user.getEmail();
			
			user.setActivationCode(activationCode);
			
			userRepository.save(user);
			
			String firstName = user.getName();
			
			try {
				KcribsEmailLib kcribsEmailLib = new KcribsEmailLib();
				String htmlbody = kcribsEmailLib.htmlResetUsers(activationCode, firstName);
				new KcribsEmailLib(email, "Password Reset", htmlbody).sendMail();

			} catch (Exception e) {
				response.setData(e.getMessage());
				response.setStatus(Constants.FAIL);
				response.setMessage("Email Failed");
				response.setSuccess(false);
				return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
			}
			
			response.setData("");
			response.setStatus(Constants.OK);
			response.setMessage("Password reset was successful, Check your email to set new password.");
			response.setSuccess(true);
		}
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}

	@Override
	public ResponseEntity<ServerResponse> setPassword(SetPasswordDto request) {
		User user = new User();
		user = userRepository.findByActivationCode(request.getActivationCode());
		
		if (user == null) {
			response.setData("");
			response.setStatus(Constants.NOT_FOUND);
			response.setMessage("The activation code can not be found, please check your email or reset the account again.");
			response.setSuccess(false);
		} else {
			user.setPassword(bcryptEncoder.encode(request.getPassword()));
			user.setActivationCode(null);
			userRepository.save(user);
			response.setData("");
			response.setStatus(Constants.OK);
			response.setMessage("Your password has been successfully reset, you can now proceed to login.");
			response.setSuccess(true);
		}
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

	}
}
