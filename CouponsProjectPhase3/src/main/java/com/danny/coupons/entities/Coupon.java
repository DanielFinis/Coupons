package com.danny.coupons.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.danny.coupons.enums.CouponCategories;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "coupons")
public class Coupon {

	// Variables--------------------


	@Id
	@GeneratedValue
	private long id;
	
	@Column (nullable = false)
	private String title;
	
	@Column (nullable = false)
	private float price;
	
	@ManyToOne 
	private Company company;
	
	@Column (nullable = false)
	private int availableQuantity;
	
	@Column (nullable = false)
	private Date endDate;
	
	@Column (nullable = false)
	private Date startDate;
	
	@Enumerated(EnumType.STRING)
	private CouponCategories category;
	
	@JsonIgnore
	@OneToMany (mappedBy = "coupon", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Purchase> purchases;
	
	
	

	// Constructors--------------------

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Coupon() {

	}

	public Coupon(String title, float price, Company company, int availableQuantity, Date startDate, Date endDate,
			CouponCategories category) {
		this.title = title;
		this.price = price;
		this.company = company;
		this.availableQuantity =  availableQuantity;
		this.endDate = endDate;
		this.startDate = startDate;
		this.category = category;
	}

	public Coupon(long id, String title, float price, Company company, int availableQuantity, Date startDate,
			Date endDate, CouponCategories category) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.company = company;
		this.availableQuantity =  availableQuantity;
		this.endDate = endDate;
		this.startDate = startDate;
		this.category = category;
	}
	
	public Coupon(long id, String title, float price, int  availableQuantity, Date startDate, Date endDate,
			CouponCategories category) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.availableQuantity =  availableQuantity;
		this.endDate = endDate;
		this.startDate = startDate;
		this.category = category;
	}

	// Getters and Setters--------------------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	
	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public CouponCategories getCategory() {
		return category;
	}

	public void setCategory(CouponCategories category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", price=" + price + ", company=" + company==null?company.getName():""
				+ ",  availableQuantity=" + availableQuantity + ", endDate=" + endDate + ", startDate=" + startDate
				+ ", category=" + category + "\n";
	}

}
