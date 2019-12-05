package com.telecom.challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.challenge.model.ActivationRequest;
import com.telecom.challenge.model.CustomerProfile;
import com.telecom.challenge.model.Service;

@RestController
public class TelephoneInquiryController {

	@Autowired
	private TelephoneInquiryService service;

	@GetMapping("/all/services")
	public List<Service> getAllTelephoneNumbers() {
		return service.getAllTelephoneNumbers();
	}

	@GetMapping("/{customerId}/services")
	public CustomerProfile getAllTelephoneNumbers(@PathVariable String customerId) {
		return service.getAllTelephoneNumbers(customerId);
	}

	@PostMapping("/activate/service")
	public void activateTelephoneNumber(@RequestBody ActivationRequest request) {
		service.activateService(request, true);
	}

	@PostMapping("/deactivate/service")
	public void deactivateTelephoneNumber(@RequestBody ActivationRequest request) {
		service.activateService(request, false);
	}

}
