package com.telecom.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telecom.challenge.model.CustomerProfile;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
	CustomerProfile findByCustomerId(String customerId);
}
