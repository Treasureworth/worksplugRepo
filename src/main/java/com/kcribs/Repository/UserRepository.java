package com.kcribs.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kcribs.Model.User;

	@Repository
	public interface UserRepository extends CrudRepository<User, String> {
	    User findByEmail(String username);
	    
	    User findByUserCode(String usercode);
	    
	    User findByActivationCode(Long request);
	    
	    User findByEmailOrPhoneNumber(String email, String phonenumber);
	    
	    User findByUserCodeOrEmail( String usercode, String email);
	}
