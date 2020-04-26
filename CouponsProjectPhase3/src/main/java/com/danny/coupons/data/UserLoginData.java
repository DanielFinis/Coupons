package com.danny.coupons.data;

import com.danny.coupons.enums.UserTypes;

public class UserLoginData {
	private long id;
	private UserTypes userType;
	private Long companyId;

	public UserLoginData(long id, UserTypes userType, Long companyId) {
		super();
		this.id = id;
		this.userType = userType;
		this.companyId = companyId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserTypes getUserType() {
		return userType;
	}

	public void setUserType(UserTypes userType) {
		this.userType = userType;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
