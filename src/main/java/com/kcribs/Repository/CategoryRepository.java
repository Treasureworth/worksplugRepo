package com.kcribs.Repository;

import org.springframework.data.repository.CrudRepository;

import com.kcribs.Model.Category;

public interface CategoryRepository extends CrudRepository<Category, String>{

	Category findByName(String categoryName);
	
	Category findByCategoryCode(String categoryCode);
	
	Category  findByCategoryCodeOrName(String categorycode, String name);
}
