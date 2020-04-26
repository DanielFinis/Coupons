package com.danny.coupons.api;

import java.util.List;

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
import com.danny.coupons.entities.Company;
import com.danny.coupons.entities.User;
import com.danny.coupons.exceptions.ApplicationException;
import com.danny.coupons.logic.CompaniesController;
import com.danny.coupons.logic.UsersController;

@RestController
@RequestMapping("/companies")
public class CompaniesApi {

	// comment
	
	@Autowired
	private CompaniesController companiesController;
	@Autowired
	private UsersController usersController;
	
	@PostMapping
	public long createCompany(@RequestBody Company company) throws ApplicationException  {
		return companiesController.createCompany(company);
	}
	
	@PutMapping("{companyId}")
	public void updateCompany (@RequestBody Company company) throws ApplicationException {
		companiesController.updateCompany(company);
	}
	
	@GetMapping("/byId")
	public Company getCompany (HttpServletRequest request) throws ApplicationException {
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
//		User user = usersController.getUser(userData.getId());
//		Company company = companiesController.getCompanyById(user.getCompany().getId());
		return companiesController.getCompanyById(userData.getCompanyId());
	}
	
	@GetMapping
	public List<Company> getAllCompanies () throws ApplicationException{
		return companiesController.getAllCompanies();
	}
	
	@DeleteMapping("{companyId}")
	public void deleteCompany (@PathVariable("companyId") long companyId) throws ApplicationException {
		companiesController.deleteCompany(companyId);
	}
}
