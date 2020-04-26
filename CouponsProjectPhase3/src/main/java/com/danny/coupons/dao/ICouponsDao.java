package com.danny.coupons.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.danny.coupons.entities.Coupon;
import com.danny.coupons.enums.CouponCategories;

public interface ICouponsDao extends CrudRepository<Coupon, Long>{
	
Coupon findByTitleAndCompanyId (String title, long companyId);

List <Coupon> findByCompanyId (Long companyId);

List <Coupon> findByCategory (CouponCategories category);

void deleteByEndDateLessThan (Date todayDate);



}
