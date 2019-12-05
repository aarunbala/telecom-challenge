package com.telecom.challenge.support;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PhoneNumberAlreadyActivatedAdvice {
	@ResponseBody
	@ExceptionHandler(ActivationServiceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String customerNotAssociatedWithPhoneNumber(ActivationServiceException ex) {
		return ex.getMessage();
	}
}
