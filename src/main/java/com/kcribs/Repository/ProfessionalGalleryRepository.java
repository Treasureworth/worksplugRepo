package com.kcribs.Repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.kcribs.Model.ProfessionalGallery;

public interface ProfessionalGalleryRepository extends CrudRepository<ProfessionalGallery, String> {

	Collection<ProfessionalGallery> findByUserCode(String usercode);

}
