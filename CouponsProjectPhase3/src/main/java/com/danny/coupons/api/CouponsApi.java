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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danny.coupons.data.UserLoginData;
import com.danny.coupons.entities.Company;
import com.danny.coupons.entities.Coupon;
import com.danny.coupons.entities.User;
import com.danny.coupons.enums.CouponCategories;
import com.danny.coupons.exceptions.ApplicationException;
import com.danny.coupons.logic.CompaniesController;
import com.danny.coupons.logic.CouponsController;
import com.danny.coupons.logic.UsersController;

@RestController
@RequestMapping("/coupons")
public class CouponsApi {

	@Autowired
	private CouponsController couponsController;
	@Autowired
	private UsersController usersController;
	@Autowired
	private CompaniesController companiesController;
	
	@PostMapping
	public long createCoupon (@RequestBody Coupon coupon, HttpServletRequest request) throws ApplicationException {
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
		return couponsController.createCoupon(coupon, userData.getCompanyId());
	}
	
	@PutMapping
	public void updateCoupon (@RequestBody Coupon coupon, HttpServletRequest request) throws ApplicationException {
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
		couponsController.updateCoupon(coupon,userData.getCompanyId(), userData.getUserType());
	}
	
	@GetMapping ("{couponId}")
	public Coupon getCoupon (@PathVariable ("couponId") long couponId) throws ApplicationException {
		return couponsController.getCoupon(couponId);
	}
	
	@GetMapping()
	public List<Coupon> getAllCoupons () throws ApplicationException{
		return couponsController.getAllCoupons();
	}
	
	@DeleteMapping("{couponId}")
	public void deleteCoupon (@PathVariable("couponId") long couponId, HttpServletRequest request) throws ApplicationException {
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
		couponsController.deleteCoupon(couponId, userData.getCompanyId(), userData.getUserType());
	}

//	@GetMapping ("/byCompany")
//	public List<Coupon> getCouponsByCompanyId (@RequestParam (value ="companyId") long companyId) throws ApplicationException{
//		return couponsController.getCouponsByCompanyId(companyId);
//	}
	
	@GetMapping ("/byCompany")
	public List<Coupon> getCouponsByCompanyId (HttpServletRequest request) throws ApplicationException{
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
//		User user = usersController.getUser(userData.getId());
//		Company company = companiesController.getCompanyById(user.getCompany().getId());
		return couponsController.getCouponsByCompanyId(userData.getCompanyId());
	}
	
	@GetMapping ("/byCategory")
	public List<Coupon> getCouponsByCategory (@RequestParam ("category") CouponCategories category) throws ApplicationException{
		return couponsController.getCouponsByCategory(category);
	}
			

}
