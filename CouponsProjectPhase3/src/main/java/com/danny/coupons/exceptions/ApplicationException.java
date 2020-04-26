package com.danny.coupons.exceptions;

import com.danny.coupons.enums.ErrorTypes;

public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7565320969788653933L;
	private ErrorTypes errorTypes;

	public ApplicationException(ErrorTypes errorTypes, String message) {
		super(message);
		this.errorTypes = errorTypes;
	}
	
	public ApplicationException(ErrorTypes errorTypes, String message, Exception e) {
		super(message, e);
		this.errorTypes = errorTypes;
	}

	public ErrorTypes getErrorTypes() {
		return this.errorTypes;
	}
		
	
}
