package com.kcribs.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kcribs.Model.Role;

public interface RoleRepository extends CrudRepository<Role, String> {

    @Query(value = "SELECT * FROM Roles where name IN (:roles)", nativeQuery = true)
    Set<Role> find(@Param("roles") List<String> roles);
    
    Role findByName(String name);
}