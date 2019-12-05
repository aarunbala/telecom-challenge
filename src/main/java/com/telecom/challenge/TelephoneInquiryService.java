package com.telecom.challenge;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.challenge.model.ActivationRequest;
import com.telecom.challenge.model.CustomerProfile;
import com.telecom.challenge.repository.CustomerProfileRepository;
import com.telecom.challenge.repository.ServiceRepository;
import com.telecom.challenge.support.CustomerAssociationException;
import com.telecom.challenge.support.ActivationServiceException;

@Service
public class TelephoneInquiryService {

	private static final Logger log = LoggerFactory.getLogger(TelephoneInquiryService.class);

	@Autowired
	private CustomerProfileRepository customerRepo;

	@Autowired
	private ServiceRepository serviceRepo;

	public List<com.telecom.challenge.model.Service> getAllTelephoneNumbers() {
		log.info("Get all Telephone numbers");
		return serviceRepo.findAll();
	}

	public CustomerProfile getAllTelephoneNumbers(String customerId) {
		log.info("Get all Telephone numbers for Customer : {}", customerId);
		return customerRepo.findByCustomerId(customerId);
	}

	public Boolean activateService(ActivationRequest request, Boolean activate) {
		log.info("Activate service : {} for Customer : {}", request.getPhoneNumber(), request.getCustomerId());
		com.telecom.challenge.model.Service service = fetchService(request);
		if (service != null) {
			log.debug("{} Service : {} for Customer : {}", activate ? "Activating" : "Deactivating",
					request.getPhoneNumber(), request.getCustomerId());
			if(service.isActivated() == activate) {
				throw new ActivationServiceException(request.getCustomerId(), request.getPhoneNumber());
			}
			service.setActivated(activate);
			service.setActivatedTime(LocalDateTime.now());
			serviceRepo.saveAndFlush(service);
			return true;
		} else {
			throw new CustomerAssociationException(request.getCustomerId(), request.getPhoneNumber());
		}
	}

	private com.telecom.challenge.model.Service fetchService(ActivationRequest request) {
		log.debug("Fetch service for Customer : {} and Phonenumber : {} ", request.getCustomerId(),
				request.getPhoneNumber());
		CustomerProfile customer = new CustomerProfile();
		customer.setCustomerId(request.getCustomerId());
		return serviceRepo.findByCustomerProfileAndPhoneNumber(customer, request.getPhoneNumber());
	}

}
