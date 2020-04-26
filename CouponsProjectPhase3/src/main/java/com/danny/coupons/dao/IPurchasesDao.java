package com.danny.coupons.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.danny.coupons.entities.Purchase;

public interface IPurchasesDao extends CrudRepository<Purchase, Long> {

	List<Purchase> findByCustomerId(Long customerId);

	
}
