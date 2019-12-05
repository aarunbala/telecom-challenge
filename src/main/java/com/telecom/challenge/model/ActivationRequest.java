package com.telecom.challenge.model;

public class ActivationRequest {

	private String customerId;
	private String phoneNumber;

	public ActivationRequest() {
		super();
	}

	public ActivationRequest(String customerId, String phoneNumber) {
		super();
		this.customerId = customerId;
		this.phoneNumber = phoneNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
