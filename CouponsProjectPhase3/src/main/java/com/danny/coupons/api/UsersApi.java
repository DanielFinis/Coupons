package com.danny.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danny.coupons.data.UserLoginData;
import com.danny.coupons.databeans.SuccessfulLoginData;
import com.danny.coupons.databeans.UserLoginDetails;
import com.danny.coupons.entities.User;
import com.danny.coupons.exceptions.ApplicationException;
import com.danny.coupons.logic.UsersController;
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UsersApi {

	@Autowired
	private UsersController usersController;

	@PostMapping
	public long createUser(@RequestBody User user) throws ApplicationException  {
		return usersController.createUser(user);
	}
	
	@PutMapping
	public void updateUser (@RequestBody User user) throws ApplicationException {
		usersController.updateUser(user);
	}

	@GetMapping ("{userId}")
	public User getUser (@PathVariable ("userId") long userId) throws ApplicationException {
		return usersController.getUser(userId);
	}

	@GetMapping()
	public List<User> getAllUsers () throws ApplicationException{
		return usersController.findAllUsers();
	}

	@DeleteMapping("{userId}")
	public void deleteUser (@PathVariable("userId") long userId) throws ApplicationException {
		usersController.deleteUser(userId);
	}

	@PostMapping("/login")
	public SuccessfulLoginData login (@RequestBody UserLoginDetails userLoginDetails) throws ApplicationException {
	
		return usersController.login(userLoginDetails);
	}
}
