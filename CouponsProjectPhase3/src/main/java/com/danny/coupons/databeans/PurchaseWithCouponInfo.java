package com.danny.coupons.databeans;

import java.sql.Date;

import com.danny.coupons.enums.CouponCategories;

public class PurchaseWithCouponInfo {
	
	private long purchaseId;
	private long couponId;
	private Date dateOfPurchase;
	private String title;
	private float price;
	private CouponCategories category;

	
	
	
	public PurchaseWithCouponInfo() {
	}


	public PurchaseWithCouponInfo(long purchaseId, long couponId, Date dateOfPurchase, String title, float price,
			CouponCategories category) {
		this.purchaseId = purchaseId;
		this.couponId = couponId;
		this.dateOfPurchase = dateOfPurchase;
		this.title = title;
		this.price = price;
		this.category = category;
	}


	public long getPurchaseId() {
		return purchaseId;
	}


	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}


	public long getCouponId() {
		return couponId;
	}


	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}


	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}


	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public CouponCategories getCategory() {
		return category;
	}


	public void setCategory(CouponCategories category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "PurchaseWithCouponInfo [purchaseId=" + purchaseId + ", couponId=" + couponId + ", dateOfPurchase="
				+ dateOfPurchase + ", title=" + title + ", price=" + price + ", category=" + category + "\n";
	}
	
	
	
	
	

}
