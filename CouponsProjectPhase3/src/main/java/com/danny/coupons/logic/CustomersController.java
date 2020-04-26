package com.danny.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.danny.coupons.dao.ICustomerDao;
import com.danny.coupons.entities.Customer;
import com.danny.coupons.entities.User;
import com.danny.coupons.enums.ErrorTypes;
import com.danny.coupons.enums.UserTypes;
import com.danny.coupons.exceptions.ApplicationException;

@Controller
public class CustomersController {

	@Autowired
	private ICustomerDao customersDao;
	@Autowired
	private UsersController usersController;

	public long createCustomer(Customer customer) throws ApplicationException {
		customerValidations(customer);
		long userId = usersController.createUser(customer.getUser());
		customer.setId(userId);

		try {
			return this.customersDao.save(customer).getId();

		} catch (Exception e) {
			usersController.deleteUser(userId);
			throw new ApplicationException(ErrorTypes.FAIL_TO_CREATE_CUSTOMER, "Failed to create cutomer");
		}
	}

	private void customerValidations(Customer customer) throws ApplicationException {

		if (customer.getUser().getUserType() != UserTypes.Customer) {
			throw new ApplicationException(ErrorTypes.INVALID_USERTYPE, "Invalid usertype.");
		}
		if (customer.getAmountOfKids() < 0) {
			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT_OF_KIDS, "Invalid amount of kids.");
		}

	}

	public void updateCustomer(Customer customer) throws ApplicationException {
		customerValidations(customer);
		customerUserValidation(customer);
		try {
			this.usersController.updateUser(customer.getUser());
			this.customersDao.save(customer);
		}catch (Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_UPDATE_CUSTOMER, "Failed to update cutomer");
		}
	}

	private void customerUserValidation(Customer customer) throws ApplicationException {
		User user = customer.getUser();
		usersController.isUserExsitById(user.getId());
		if (user.getId() != customer.getId()) {
			throw new ApplicationException(ErrorTypes.INVALID_USER_ID, "User id and customer id doesn't match");
		}

	}

	public Customer getCustomer(long customerId) throws ApplicationException {
		isCustomerExistsById(customerId);
		try {
			return this.customersDao.findOne(customerId);
		}catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_CUSTOMER, "Failed to get cutomer");
		}
	}

	public List<Customer> getAllCustomers() throws ApplicationException {
		try {
			return (List<Customer>) this.customersDao.findAll();
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_CUSTOMER, "Failed to get cutomers");
		}
	}

	public void isCustomerExistsById(long customerId) throws ApplicationException {
		if (this.customersDao.findOne(customerId) == null) {
			throw new ApplicationException(ErrorTypes.INVALID_CUSTOMER, "There is no customer with this ID");
		}
	}
}
