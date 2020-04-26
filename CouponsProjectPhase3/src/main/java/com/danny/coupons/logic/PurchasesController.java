package com.danny.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.danny.coupons.dao.ICustomerDao;
import com.danny.coupons.dao.IPurchasesDao;
import com.danny.coupons.entities.Purchase;
import com.danny.coupons.enums.ErrorTypes;
import com.danny.coupons.exceptions.ApplicationException;

@Controller
public class PurchasesController {

	@Autowired
	private IPurchasesDao purchasesDao;
	@Autowired
	private CustomersController customersController;
	@Autowired
	private CouponsController couponsController;
	

	public long createPurchase(Purchase purchase, long customerId) throws ApplicationException {

		purchase.setCustomer(customersController.getCustomer(customerId));
		purchaseValitation(purchase);
		try {
			this.purchasesDao.save(purchase);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_CREATE_PURCHASE, "Failed to create purchase");
		}
		updateAvailableQuantityOfCoupon(purchase);
		return purchase.getId();
	}

	private void purchaseValitation(Purchase purchase) throws ApplicationException {

		couponsController.isCouponExistsById(purchase.getCoupon().getId());
		customersController.isCustomerExistsById(purchase.getCustomer().getId());

		if (purchase.getAmountOfItems() < 1) {
			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT_OF_ITEMS, "Quantity of items for purchase must be more than 0.");
		}
		int availableQuantity = couponsController.getAvailableQuantity(purchase.getCoupon().getId());
		if ((availableQuantity - purchase.getAmountOfItems()) < 0 ) {
			throw new ApplicationException(ErrorTypes.INVALID_AMOUNT_OF_ITEMS, "Not enough stock.");
		}

	}

	private void updateAvailableQuantityOfCoupon (Purchase purchase) throws ApplicationException {
		int amountBeforePurchase = couponsController.getAvailableQuantity(purchase.getCoupon().getId());
		int amountOfItemsForPerchase = purchase.getAmountOfItems();
		int amountAAfterThePurchase = amountBeforePurchase - amountOfItemsForPerchase;
		couponsController.updateAvailableQuantity(purchase.getCoupon().getId(), amountAAfterThePurchase);

	}

	public void updatePurchase(Purchase purchase) throws ApplicationException {
		this.purchaseValitation(purchase);
		try {
			this.purchasesDao.save(purchase);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_UPDATE_PURCHASE, "Failed to update purchase");
		}
	}

	public void deletePurchase(long purchaseId) throws ApplicationException {
		isPurchaseExistsById(purchaseId);
		try {
			this.purchasesDao.delete(purchaseId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.FAIL_TO_DELETE_PURCHASE, "Failed to delete purchase");
		}
	}

	private void isPurchaseExistsById(long purchaseId) throws ApplicationException {
		if( this.purchasesDao.findOne(purchaseId) == null) {
			throw new ApplicationException(ErrorTypes.INVALID_PURCHASE, "There is no purchase with this ID");
		}
	}

	public Purchase getPurchase(long purchaseId) throws ApplicationException {
		isPurchaseExistsById(purchaseId);
		try {
			return this.purchasesDao.findOne(purchaseId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_PURCHASE, "Failed to get purchase");
		}
	}

	public List<Purchase> getCustomerPurchases (Long customerId) throws ApplicationException{
		try {
			return (List<Purchase>) this.purchasesDao.findByCustomerId(customerId);
		}catch(Exception e) {
			throw new ApplicationException(ErrorTypes.INVALID_COUPON, "Faild to get customer purchases");
		}
	}
	
	public List<Purchase> getAllPurchases() throws ApplicationException {
		return (List<Purchase>) this.purchasesDao.findAll();
	}

}
