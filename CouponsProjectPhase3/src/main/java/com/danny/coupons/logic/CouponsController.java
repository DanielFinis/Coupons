package com.danny.coupons.logic;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.danny.coupons.dao.ICouponsDao;
import com.danny.coupons.entities.Coupon;
import com.danny.coupons.enums.CouponCategories;
import com.danny.coupons.enums.ErrorTypes;
import com.danny.coupons.enums.UserTypes;
import com.danny.coupons.exceptions.ApplicationException;

@Controller
public class CouponsController {

	@Autowired
	private ICouponsDao couponsDao;
	@Autowired
	private CompaniesController companiesController;

	public long createCoupon(Coupon coupon, long companyId) throws ApplicationException {
		coupon.setCompany(companiesController.getCompanyById(companyId));
		createCouponValidations(coupon);
		try {
			return this.couponsDao.save(coupon).getId();
		} catch (Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_UPDATE_COUPON, "Failed to update coupon", e);
		}

	}

	private void createCouponValidations(Coupon coupon) throws ApplicationException {

		if(this.couponsDao.findByTitleAndCompanyId(coupon.getTitle(), coupon.getCompany().getId()) != null) {
			throw new ApplicationException(ErrorTypes.INVALID_COUPON_NAME, "Coupon is already exists for this company");
		}
		companiesController.isCompanyExsitById(coupon.getCompany().getId());

		if(coupon.getPrice() <= 0) {
			throw new ApplicationException(ErrorTypes.INVALID_PRICE, "Invalid price");
		}
		if(coupon.getStartDate().after(coupon.getEndDate())) {
			throw new ApplicationException(ErrorTypes.INVALID_DATE, "Start date can not be after the end date");
		}
		if(coupon.getEndDate().before(Calendar.getInstance().getTime())){
			throw new ApplicationException(ErrorTypes.INVALID_DATE, "End date can not be before current date");
		}
	}

	public void deleteCoupon (long couponId, long companyId, UserTypes userTypes) throws ApplicationException {
		isCouponExistsById(couponId);
		if (companyId == getCoupon(couponId).getCompany().getId() || userTypes == UserTypes.Administrator) {
			try {
				this.couponsDao.delete(couponId);
			}catch (Exception e) {
				throw new ApplicationException(ErrorTypes.FAIL_TO_DELETE_COUPON, "Failed to delete coupon", e);
			}
		}
		else {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "You can't delete not your company coupons");
		}
	}

	public void updateCoupon(Coupon coupon, Long companyId, UserTypes userTypes) throws ApplicationException {
		isCouponExistsById(coupon.getId());
		coupon.setCompany(companiesController.getCompanyById(companyId));
		updateCouponValidation(coupon);
		if (companyId == getCoupon(coupon.getId()).getCompany().getId() || userTypes == UserTypes.Administrator) {
			try {
				this.couponsDao.save(coupon);
			}catch (Exception e) {
				throw new ApplicationException(ErrorTypes.FAIL_TO_UPDATE_COUPON, "Failed to update coupon", e);
			}
		}
		else {
			throw new ApplicationException(ErrorTypes.FAIL_TO_UPDATE_COUPON, "You can't update not your company coupons");
		}
	}

	private void updateCouponValidation(Coupon coupon) throws ApplicationException {
		if(coupon.getPrice() <= 0) {
			throw new ApplicationException(ErrorTypes.INVALID_PRICE, "Invalid price");
		}
		if(coupon.getStartDate().after(coupon.getEndDate())) {
			throw new ApplicationException(ErrorTypes.INVALID_DATE, "Start date can not be after the end date");
		}
		if(coupon.getEndDate().before(Calendar.getInstance().getTime())){
			throw new ApplicationException(ErrorTypes.INVALID_DATE, "End date can not be before current date");
		}
	}

	public Coupon getCoupon(long couponId) throws ApplicationException {
		isCouponExistsById(couponId);
		try {
			return this.couponsDao.findOne(couponId);
		}catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COUPON, "Faild to get coupon");
		}
	}

	public List<Coupon> getAllCoupons() throws ApplicationException {
		try {
			return (List<Coupon>) this.couponsDao.findAll();
		}catch(Exception e){
			throw new ApplicationException(ErrorTypes.INVALID_COUPON, "Faild to get coupons");
		}
	}

	public List<Coupon> getCouponsByCompanyId(long companyId) throws ApplicationException {
		try {
			return this.couponsDao.findByCompanyId(companyId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COUPON, "Faild to get company coupons");
		}
	}

	public List<Coupon> getCouponsByCategory(CouponCategories category) throws ApplicationException {
		try {
			return this.couponsDao.findByCategory(category);
		}catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COUPON, "Faild to get coupons by category");
		}
	}

	public void isCouponExistsById(long couponId) throws ApplicationException {
		if (this.couponsDao.findOne(couponId) == null) {
			throw new ApplicationException(ErrorTypes.INVALID_COUPON_ID, "There is no coupon with this ID");
		}
	}

	public int getAvailableQuantity(long couponId) throws ApplicationException {
		try {
			return couponsDao.findOne(couponId).getAvailableQuantity();
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COUPON, "Faild to get coupon");
		}
	}

	public void updateAvailableQuantity(long couponId, int currentAmount) throws ApplicationException{
		try {
			this.couponsDao.findOne(couponId).setAvailableQuantity(currentAmount);
			this.couponsDao.save(couponsDao.findOne(couponId));
		}catch (Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COUPON, "Faild to update coupon available quantity");
		}
	}
    
	@Transactional
	public void removeNotValidCouponsByEndDate (Date todayDate) throws ApplicationException {
		try {
		couponsDao.deleteByEndDateLessThan(todayDate);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Fail to remove not valid coupons");
		}
	}

}
