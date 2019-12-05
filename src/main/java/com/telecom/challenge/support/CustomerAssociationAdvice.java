package com.telecom.challenge.support;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomerAssociationAdvice {
	@ResponseBody
	@ExceptionHandler(CustomerAssociationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String customerNotAssociatedWithPhoneNumber(CustomerAssociationException ex) {
		return ex.getMessage();
	}
}
