package com.telecom.challenge.support;

public class ActivationServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActivationServiceException(String customerId, String phoneNumber) {
		super("Phone Number " + phoneNumber + " already active for Customer " + customerId);
	}

}
