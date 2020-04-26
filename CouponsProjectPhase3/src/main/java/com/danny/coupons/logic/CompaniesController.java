package com.danny.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.danny.coupons.dao.ICompaniesDao;
import com.danny.coupons.entities.Company;
import com.danny.coupons.enums.ErrorTypes;
import com.danny.coupons.exceptions.ApplicationException;

@Controller
public class CompaniesController {

	@Autowired
	private ICompaniesDao companiesDao;

	public long createCompany(Company company) throws ApplicationException {

		companyValidations(company);
		try {
		return this.companiesDao.save(company).getId();
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.COMPANY_CREATION_FAILED, "Failed to create new company", e);
		}
	}

	private void companyValidations(Company company) throws ApplicationException {

		if (this.companiesDao.findByName(company.getName()) != null) {
			throw new ApplicationException(ErrorTypes.INVALID_COMPANY_NAME, "Company with this name is already exist");
		}
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (!company.getEmail().matches(regex)) {
			throw new ApplicationException(ErrorTypes.INVALID_EMAIL_ADDRESS, "Invalid Email address");
		}

	}

	public void deleteCompany(long companyId) throws ApplicationException {
		isCompanyExsitById(companyId);
		try {
		this.companiesDao.delete(companyId);
		}catch (Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_DELETE_COMPANY, "Failed to delete company", e);
		}
	}

	public void updateCompany(Company company) throws ApplicationException {
		isCompanyExsitById(company.getId());
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (!company.getEmail().matches(regex)) {
			throw new ApplicationException(ErrorTypes.INVALID_EMAIL_ADDRESS, "Invalid Email address");
		}
		try {
		this.companiesDao.save(company);
		}catch (Exception e) {
			throw new ApplicationException(ErrorTypes.COMPANY_UPDATE_FAILED, "Failed to get company", e);
		}
	}

	public Company getCompanyById(long companyId) throws ApplicationException {
		isCompanyExsitById(companyId);
		try {
		return this.companiesDao.findOne(companyId);
		}catch (Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_GET_COMPANY, "Failed to get company", e);	
		}
	}

	public List<Company> getAllCompanies() throws ApplicationException {
		try {
		return (List<Company>) this.companiesDao.findAll();
		}catch (Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_GET_COMPANY, "Failed to get companies", e);
		}
	}

	public void isCompanyExsitById (long companyId) throws ApplicationException {
		if (this.companiesDao.findOne(companyId) == null) {
			throw new ApplicationException(ErrorTypes.INVALID_COMPANY_ID, "There is no company with this ID");
		}
	}
}

