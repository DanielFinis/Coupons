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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danny.coupons.data.UserLoginData;
import com.danny.coupons.entities.Customer;
import com.danny.coupons.entities.Purchase;
import com.danny.coupons.exceptions.ApplicationException;
import com.danny.coupons.logic.CustomersController;
import com.danny.coupons.logic.PurchasesController;

@RestController
@RequestMapping("/purchases")
public class PurchasesApi {

	@Autowired
	private PurchasesController purchasesController;
	
	@Autowired
	private CustomersController customersController;
	
	@PostMapping
	public long createPurchase (@RequestBody Purchase purchase, HttpServletRequest request) throws ApplicationException {
		
		System.out.println(purchase);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
		return purchasesController.createPurchase(purchase, userData.getId());
	}
	
	@PutMapping
	public void updatePurchase (@RequestBody Purchase purchase) throws ApplicationException {
		purchasesController.updatePurchase(purchase);
	}
	
//	@GetMapping ("{purchaseId}")
//	public Purchase getPurchase (@PathVariable ("purchaseId") long purchaseId) throws ApplicationException {
//		return purchasesController.getPurchase(purchaseId);
//	}
	
	@GetMapping("/all")
	public List<Purchase> getAllPurchases () throws ApplicationException{
		return purchasesController.getAllPurchases();
	}
	
	@GetMapping("byCustomer")
	public List<Purchase> getCustomerPurchases (HttpServletRequest request) throws ApplicationException{
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
//		Customer customer = customersController.getCustomer(userData.getId());
//		System.out.println(userData+"**************************************************************");
		return purchasesController.getCustomerPurchases(userData.getId());
	}
	
	@DeleteMapping("{purchaseId}")
	public void deletePurchase (@PathVariable("purchaseId") long purchaseId) throws ApplicationException {
		purchasesController.deletePurchase(purchaseId);
	}

//	@GetMapping("/extended/ByPurchaseId")
//	public PurchaseWithCouponInfo getPurchaseWithCouponInfo (@RequestParam ("purchaseId") long purchaseId) throws ApplicationException {
//		return purchasesController.getPurchaseWithCouponInfo(purchaseId);
//	}
}
