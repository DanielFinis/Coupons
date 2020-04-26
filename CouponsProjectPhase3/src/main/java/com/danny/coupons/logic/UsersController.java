package com.danny.coupons.logic;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.danny.coupons.cache.CacheController;
import com.danny.coupons.dao.IUsersDao;
import com.danny.coupons.data.UserLoginData;
import com.danny.coupons.databeans.SuccessfulLoginData;
import com.danny.coupons.databeans.UserLoginDetails;
import com.danny.coupons.entities.User;
import com.danny.coupons.exceptions.ApplicationException;
import com.danny.coupons.enums.ErrorTypes;
import com.danny.coupons.enums.UserTypes;

@Controller
public class UsersController {

	@Autowired
	private IUsersDao usersDao;
	@Autowired
	private CompaniesController companiesController;
	@Autowired
	private CacheController cacheController;

	private static final String ENCRYPTION_TOKEN_SALT = "AASDFHSJFHJHKAAAAA-3423@#$@#$";


	public long createUser(User user) throws ApplicationException {
		userValidations(user);
		user.setPassword(String.valueOf(user.getPassword().hashCode()));
		try {
			return this.usersDao.save(user).getId();
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_CREATE_USER, "Failed to create user");
		}
	}

	private void userValidations(User user) throws ApplicationException {

		if (user.getUserType() == UserTypes.Company) {
			if (user.getCompany() == null) {
				throw new ApplicationException(ErrorTypes.INVALID_COMPANY, "Company id must be specified for a company user.");
			}
			companiesController.isCompanyExsitById(user.getCompany().getId()); 

		}	
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if ( !user.getUsername().matches(regex)) {
			throw new ApplicationException(ErrorTypes.INVALID_USERNAME,"Email not valid");
		}

		if (user.getPassword().length() > 15 || user.getPassword().length() < 6) {
			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, "Password should be less than 15 and more than 6 characters in length.");

		}
		if (user.getPassword() == user.getUsername()) {
			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, "Password Should not be same as user name");

		}

		String lowerCaseChars = "(.*[a-z].*)";
		if (!user.getPassword().matches(lowerCaseChars)) {
			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, "Password should contain atleast one lower case alphabet");

		}
		String numbers = "(.*[0-9].*)";
		if (!user.getPassword().matches(numbers)) {
			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, "Password should contain atleast one number.");

		}
	}

	public void updateUser(User user) throws ApplicationException {
		isUserExsitById(user.getId());
		userValidations(user);
		user.setPassword(String.valueOf(user.getPassword().hashCode()));
		try {
			this.usersDao.save(user);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_UPDATE_USER, "Failed to updata user");
		}
	}

	public void deleteUser(long userId) throws ApplicationException {
		isUserExsitById(userId);
		try {
			this.usersDao.delete(userId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_DELETE_USER, "Failed to delete user");
		}
	}

	public User getUser(long userId) throws ApplicationException {
		isUserExsitById(userId);
		try {
			return this.usersDao.findOne(userId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_USER, "Failed to get user");
		}
	}

	public List<User> findAllUsers() throws ApplicationException {
		try {
			return (List<User>) this.usersDao.findAll();
		}catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_USER, "Failed to get users", e);
		}
	}



	public void isUserExsitById (long userId) throws ApplicationException {
		if (this.usersDao.findOne(userId) == null) {
			throw new ApplicationException(ErrorTypes.INVALID_USER_ID, "There is no user with this ID");
		}
	}

	public SuccessfulLoginData login(UserLoginDetails userLoginDetails ) throws ApplicationException {
		System.out.println(userLoginDetails);
		String userName = userLoginDetails.getUsername();
		try {
			User user = usersDao.getUserByUsername(userName);
			if (user == null) {
				throw new ApplicationException(ErrorTypes.INVALID_USER, "No such user");
			}
			int userHashPassword = Integer.parseInt(user.getPassword());


			if (userHashPassword != userLoginDetails.getPassword().hashCode()) {

				throw new ApplicationException(ErrorTypes.INVALID_USER, "Invalid password");
			}

			long id = user.getId();
			UserTypes userType = user.getUserType();
			Long companyId;
			if (userType == UserTypes.Company) {
				companyId = user.getCompany().getId();
			}
			else {
				companyId = null;
			}
			UserLoginData userLoginData = new UserLoginData(id, userType, companyId);
			int token = generateToken(userLoginDetails);

			cacheController.put(String.valueOf(token), userLoginData);

			return new SuccessfulLoginData(token, userLoginData.getUserType());

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.FAIL_TO_GET_PASSWORD, "Failed get the hash password", e);
		}

	}

	private int generateToken(UserLoginDetails userLoginDetails) {
		String text = userLoginDetails.getUsername() + Calendar.getInstance().getTime().toString() + ENCRYPTION_TOKEN_SALT;
		return text.hashCode();
	}

}
