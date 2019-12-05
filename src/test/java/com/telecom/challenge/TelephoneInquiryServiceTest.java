package com.telecom.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.telecom.challenge.model.ActivationRequest;
import com.telecom.challenge.model.CustomerProfile;
import com.telecom.challenge.model.Service;
import com.telecom.challenge.repository.CustomerProfileRepository;
import com.telecom.challenge.repository.ServiceRepository;
import com.telecom.challenge.support.ActivationServiceException;
import com.telecom.challenge.support.CustomerAssociationException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TelephoneInquiryService.class })
@AutoConfigureMockMvc
@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
public class TelephoneInquiryServiceTest {

	@MockBean
	private ServiceRepository serviceRepo;
	
	@MockBean
	private CustomerProfileRepository customerRepo;

	@Autowired
	private TelephoneInquiryService service;
	
	private CustomerProfile customer;
	private ActivationRequest request;
	private Service activeSvc;
	private Service inactiveSvc;
	private List<Service> services;
	
	@BeforeEach
	public void setUp() {
		customer = new CustomerProfile();
		customer.setCustomerId("CUST0123");
		request = new ActivationRequest("CUST0123", "0123456789");
		activeSvc = new Service(1, "0123456789", true, customer);
		inactiveSvc = new Service(2, "1234567890", false, customer);
		services = new ArrayList<>();
		services.add(activeSvc);
		services.add(inactiveSvc);
	}
	
	@Test
	@DisplayName("activateService - Activation Request Successful")
	public void testActivateService() {
		when(serviceRepo.findByCustomerProfileAndPhoneNumber(customer, request.getPhoneNumber())).thenReturn(inactiveSvc);
		assertTrue(service.activateService(request, true));
	}
	
	@Test
	@DisplayName("activateService - Activation Request Exception Scenario")
	public void testActivateService_Exception() {
		when(serviceRepo.findByCustomerProfileAndPhoneNumber(customer, request.getPhoneNumber())).thenReturn(activeSvc);
		assertThrows(ActivationServiceException.class, () -> {service.activateService(request, true);});
	}
	
	@Test
	@DisplayName("activateService - Deactivation Request Successful")
	public void testDeactivateService() {
		when(serviceRepo.findByCustomerProfileAndPhoneNumber(customer, request.getPhoneNumber())).thenReturn(activeSvc);
		assertTrue(service.activateService(request, false));
	}
	
	@Test
	@DisplayName("activateService - Deactivation Request Exception Scenario")
	public void testDeactivateService_Exception() {
		when(serviceRepo.findByCustomerProfileAndPhoneNumber(customer, request.getPhoneNumber())).thenReturn(inactiveSvc);
		assertThrows(ActivationServiceException.class, () -> {service.activateService(request, false);});
	}
	
	@Test
	@DisplayName("activateService - Invalid Customer Association Exception Scenario")
	public void testActivateService_Association_Exception() {
		customer.setCustomerId("CUST0124");
		when(serviceRepo.findByCustomerProfileAndPhoneNumber(customer, request.getPhoneNumber())).thenReturn(inactiveSvc);
		assertThrows(CustomerAssociationException.class, () -> {service.activateService(request, false);});
	}
	
	@Test
	@DisplayName("getAllPhoneNumbers - Success scenario")
	public void testGetAllPhoneNumbers() {
		when(serviceRepo.findAll()).thenReturn(services);
		assertTrue(service.getAllTelephoneNumbers().size() == services.size());
	}
	
	@Test
	@DisplayName("getAllPhoneNumbers for Customer - Success Scenario")
	public void testGetAllPhoneNumbersForCustomer() {
		customer.setServices(services);
		when(customerRepo.findByCustomerId(customer.getCustomerId())).thenReturn(customer);
		assertEquals(customer, service.getAllTelephoneNumbers(customer.getCustomerId()));
	}
	
	@AfterAll
	public void tearDown() {
		customer = null;
		request = null;
		activeSvc = null;
		inactiveSvc = null;
	}
}
