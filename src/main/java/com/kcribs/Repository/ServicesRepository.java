package com.kcribs.Repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.kcribs.Model.OfferedServices;

public interface ServicesRepository extends CrudRepository<OfferedServices, String>{

	OfferedServices findByName(String serviceName);
	
	Collection<OfferedServices> findByNameIgnoreCaseContaining(String serviceName);
}
