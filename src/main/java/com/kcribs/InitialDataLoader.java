package com.kcribs;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kcribs.Model.Role;
import com.kcribs.Model.User;
import com.kcribs.Repository.RoleRepository;
import com.kcribs.Repository.UserRepository;


@Component
@Transactional
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	 
	    @Autowired
	    private UserRepository userRepository;
	  
	    @Autowired
	    private RoleRepository roleRepository;
	  
	  
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	  
	    @Override
	    @Transactional
	    public void onApplicationEvent(ContextRefreshedEvent event) {
	  
	    	User adminExist = userRepository.findByEmail("admin@kcribs.com");
	    	if (adminExist == null) {
	    		createRoleIfNotFound("PROFESSIONAL", "professionalUsers");
		        createRoleIfNotFound("CLIENT", "clientUsers");
		        createRoleIfNotFound("ADMIN", "administrators");
		 
//		        Set<Role> roles = new HashSet<>();
		        Role adminRole = roleRepository.findByName("ADMIN");
//		        roles.add(adminRole);
		        String userCode = "ADM" + System.currentTimeMillis();
		        User user = new User();
		        user.setUserCode(userCode);
		        user.setName("Admin");
		        user.setPhoneNumber("08012345678");
		        user.setPassword(passwordEncoder.encode("kcr1b3123"));
		        user.setEmail("admin@kcribs.com");
		        user.setRole(adminRole);
		        user.setEnabled(true);
		        userRepository.save(user);
	    	} 
	    }

	 
	    @Transactional
	    private Role createRoleIfNotFound(String name, String desc) {
	  
	        Role role = roleRepository.findByName(name);
	        if (role == null) {
	            role = new Role();
	            role.setDescription(desc);
	            role.setName(name);
	            roleRepository.save(role);
	        }
	        return role;
	    }
}
