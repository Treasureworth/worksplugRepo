package com.kcribs.Repository;

import org.springframework.data.repository.CrudRepository;

import com.kcribs.Model.ClientUser;

public interface ClientUserRepository extends CrudRepository<ClientUser, String> {

	ClientUser findByUser_UserCode(String userCode);

}
