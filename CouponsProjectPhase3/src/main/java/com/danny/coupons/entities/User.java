package com.danny.coupons.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.danny.coupons.enums.UserTypes;

@Entity
@Table (name = "users")
public class User {

	// Variables--------------------
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column (unique = true, nullable = false)
	private String username;
	
	@Column (nullable = false)
	private String password;
	
	@Column (nullable = true)
	@Enumerated(EnumType.STRING)
	private UserTypes userType;
	
	@ManyToOne
	private Company company;
	
	

	// Constructors--------------------

	public User() {

	}

	
	public User(String username, String password, UserTypes userType) {
		this.username = username;
		this.password = password;
		this.userType = userType;
	}


	public User(String username, String password, UserTypes userType, Company company) {
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.company = company;
	}

	public User(long id, String username, String password, UserTypes userType, Company company) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.company = company;
	}

	// Getters and Setters--------------------

	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserTypes getUserType() {
		return userType;
	}

	public void setUserType(UserTypes userType) {
		this.userType = userType;
	}

	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", userType=" + userType
				+ ", company=" + company!=null?company.getName():"" + "\n";
	}

}
