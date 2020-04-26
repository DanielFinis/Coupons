package com.danny.coupons.dao;

import org.springframework.data.repository.CrudRepository;

import com.danny.coupons.entities.Company;

public interface ICompaniesDao extends CrudRepository<Company, Long> {

	public Company findByName(String name);
	
	

}
