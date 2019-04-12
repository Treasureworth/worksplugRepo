package com.kcribs.Repository;

import org.springframework.data.repository.CrudRepository;

import com.kcribs.Model.State;


public interface CoveredStateRepository extends CrudRepository<State, String> {

	State findByName(String collection);
}
