package com.danny.coupons.api;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danny.coupons.data.UserLoginData;
import com.danny.coupons.entities.Customer;
import com.danny.coupons.exceptions.ApplicationException;
import com.danny.coupons.logic.CustomersController;

@RestController
@RequestMapping("/customers")
public class CustomersApi {

	@Autowired
	private CustomersController customersController;
	
	@PostMapping
	public long createCustomer (@RequestBody Customer customer) throws ApplicationException {
		return customersController.createCustomer(customer);
	}
	
//	@PutMapping
//	public void updateCustomer (@RequestBody Customer customer) throws ApplicationException {
//		customersController.updateCustomer(customer);
//	}
	
	@PutMapping
	public void updateCustomer (@RequestBody Customer customer, HttpServletRequest request) throws ApplicationException {
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
		customer.setId(userData.getId());
		customer.getUser().setId(userData.getId());
		customer.getUser().setUserType(userData.getUserType());
		customersController.updateCustomer(customer);
	}
	
	
//	@GetMapping ("{customerId}")
//	public Customer getCustomer (@PathVariable ("customerId") long customerId) throws ApplicationException {
//		return customersController.getCustomer(customerId);
//	}
	
	@GetMapping
	public Customer getCustomer (HttpServletRequest request) throws ApplicationException {
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
		Customer customer = customersController.getCustomer(userData.getId());
		return this.customersController.getCustomer(customer.getId());
	}
	
	@GetMapping("/all")
	public List<Customer> getAllCustomers () throws ApplicationException{
		return customersController.getAllCustomers();
	}
	
//	@DeleteMapping("{customerId}")
//	public void deleteCustomer (@PathVariable("customerId") long customerId) throws ApplicationException {
//		customersController.deleteCustomer(customerId);
//	}
}
