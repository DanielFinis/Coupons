package com.danny.coupons.dao;

import org.springframework.data.repository.CrudRepository;

import com.danny.coupons.entities.User;

public interface IUsersDao extends CrudRepository<User, Long>{

	
	User getUserByUsername(String userName);
	


	
	
}
