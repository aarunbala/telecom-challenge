package com.telecom.challenge.support;

public class CustomerAssociationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerAssociationException(String customerId, String phoneNumber) {
		super("Customer " + customerId + "not associated with Phonenumber : " + phoneNumber);
	}

}
