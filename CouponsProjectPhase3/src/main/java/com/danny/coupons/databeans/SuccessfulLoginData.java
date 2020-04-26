package com.danny.coupons.databeans;

import com.danny.coupons.enums.UserTypes;

public class SuccessfulLoginData {
	private int token;
	private UserTypes userType;

	public SuccessfulLoginData() {
	}

	public SuccessfulLoginData(int token, UserTypes userType) {
		this.token = token;
		this.userType = userType;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public UserTypes getUserType() {
		return userType;
	}

	public void setUserType(UserTypes userType) {
		this.userType = userType;
	}
}
