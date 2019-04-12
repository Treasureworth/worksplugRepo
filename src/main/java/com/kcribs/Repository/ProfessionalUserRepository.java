package com.kcribs.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kcribs.Model.ProfessionalUser;


@Repository
public interface ProfessionalUserRepository extends CrudRepository<ProfessionalUser, String> {

	ProfessionalUser findByUser_Email(String email);
    
	ProfessionalUser findByUser_UserCode(String userCode);
    
	ProfessionalUser findByUser_EmailOrUser_PhoneNumber(String email, String phoneNumber);
    
	ProfessionalUser findByUser_UserCodeOrUser_Email( String usercode, String email);

	ProfessionalUser findByUser_ActivationCode(Long request);
	
	Collection<ProfessionalUser> findByCategory(String category);

	Collection<ProfessionalUser> findByServices(String category);

	@Query("select p from ProfessionalUser p where p.minCost <= ?1 AND p.maxCost >= ?2")
	Collection<ProfessionalUser> findByMinCostLessThanEqualAndMaxCostGreaterThanEqual(int minCost, int maxCost);

}
