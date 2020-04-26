package com.danny.coupons.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.danny.coupons.entities.Customer;
import com.danny.coupons.entities.Purchase;

public interface ICustomerDao extends CrudRepository<Customer, Long> {


	

}
