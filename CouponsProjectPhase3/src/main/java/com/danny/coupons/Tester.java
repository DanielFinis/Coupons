package com.danny.coupons;
import java.sql.Date;
import java.sql.Timestamp;

import com.danny.coupons.entities.Company;
import com.danny.coupons.entities.Coupon;
import com.danny.coupons.entities.Customer;
import com.danny.coupons.entities.Purchase;
import com.danny.coupons.entities.User;
import com.danny.coupons.enums.CompanyTypes;
import com.danny.coupons.enums.CouponCategories;
import com.danny.coupons.enums.UserTypes;
import com.danny.coupons.exceptions.ApplicationException;
import com.danny.coupons.logic.CompaniesController;
import com.danny.coupons.logic.CouponsController;
import com.danny.coupons.logic.CustomersController;
import com.danny.coupons.logic.PurchasesController;
import com.danny.coupons.logic.UsersController;

public class Tester {

	public static void test(String[] args) throws Exception {
		
		UsersController usersController = null;

//		usersController.findByUsername("dpines7@gmail.com");
		
		//Companies tests---------------------------------------------------------------------------------
		
//		Company company = new Company("SOGOOD", "03-12345678", "1223@walla.com", CompanyTypes.Electronics);
//		CompaniesController.getInstance().createCompany(company);
		
//		CompaniesController.getInstance().deleteCompany(10);
		
//		Company company = new Company(15, "03-12345678", "matrix@walla.com", CompanyTypes.Computers);
//		CompaniesController.getInstance().updateCompany(company);
		
//		System.out.println(CompaniesController.getInstance().getCompanyById(11));
		
//		System.out.println(CompaniesController.getInstance().getAllCompanies());
		
		//Coupons tests---------------------------------------------------------------------------------
		
//		Coupon coupon = new Coupon("Book", (float) 567, 10, 5,Date.valueOf("2019-10-16") ,Date.valueOf("2019-10-30"), CouponCategories.Electronics);
//		CouponsController.getInstance().createCoupon(coupon);
		
//		CouponsController.getInstance().deleteCoupon(2);
		
//		Coupon coupon = new Coupon(3, "table", (float) 200, 4, Date.valueOf("2019-06-20") ,Date.valueOf("2019-10-16"), CouponCategories.Furnitures);
//		CouponsController.getInstance().updateCoupon(coupon);
		
//		System.out.println(CouponsController.getInstance().getCoupon(5));
		
//		System.out.println(CouponsController.getInstance().getAllCoupons());
		
//		System.out.println(CouponsController.getInstance().getCouponsByCompanyId(17));
		
//		System.out.println(CouponsController.getInstance().getCouponsByCategory(CouponCategories.Furnitures));
		
//		System.out.println(CouponsController.getInstance().getPurchasedCoupons());
		
		//Users tests---------------------------------------------------------------------------------
//		
//		User user = new User("danielf@inbal", "a123456A", UserTypes.Administrator);
//		usersController.createUser(user);
//		
//		Customer customer = new Customer("hauv@inbal.co.il", "a167A", UserTypes.Customer, "fullName", 2, true, null);
//		CustomersController.getInstance().createCustomer(customer);
		
//		UsersController.getInstance().deleteUser(8);
		
//		User user = new User(10, "danielf@inbal.co.il", "123456aA", UserTypes.Customer, null);
//		UsersController.getInstance().updateUser(user);
	
//		System.out.println(UsersController.getInstance().getUser(12));
		
//		System.out.println(UsersController.getInstance().getAllUsers());
		
//		System.out.println(UsersController.getInstance().login("danielf@inbal.co.il", "123456aA"));
	
		//Purchases tests---------------------------------------------------------------------------------
	
//		Purchase purchase = new Purchase(7, 16, 1, new Timestamp(System.currentTimeMillis()));
//		PurchasesController.getInstance().createPurchase(purchase);

//		long couponId, long customerId, int amountOfItems, Timestamp timestamp
		
//		System.out.println(PurchasesController.getInstance().getPurchaseWithCouponInfo(4));
		
//		System.out.println(PurchasesController.getInstance().getAllPurchasesWithCouponInfo());
	}

}
