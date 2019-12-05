package com.telecom.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telecom.challenge.model.CustomerProfile;
import com.telecom.challenge.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {

	@Query(value="SELECT * FROM SERVICE S JOIN CUSTOMER_PROFILE C ON C.id = S.CUSTOMER_PROFILE_ID  WHERE C.CUSTOMER_ID = :#{#customerProfile.customerId}", nativeQuery=true)
	List<Service> findByCustomerProfile(@Param("customerProfile") CustomerProfile customerProfile);

	@Query(value="SELECT * FROM SERVICE S JOIN CUSTOMER_PROFILE C ON C.id = S.CUSTOMER_PROFILE_ID  WHERE S.ACTIVATED = :#{#activated} AND C.CUSTOMER_ID = :#{#customerProfile.customerId}", nativeQuery=true)
	List<Service> findByCustomerProfileAndActivated(@Param("customerProfile") CustomerProfile customerProfile, @Param("activated") Boolean activated);
	
	@Query(value="SELECT * FROM SERVICE S JOIN CUSTOMER_PROFILE C ON C.id = S.CUSTOMER_PROFILE_ID  WHERE C.CUSTOMER_ID = :#{#customerProfile.customerId} AND S.PHONE_NUMBER = :#{#phoneNumber}", nativeQuery=true)
	Service findByCustomerProfileAndPhoneNumber(@Param("customerProfile") CustomerProfile customerProfile, @Param("phoneNumber") String phoneNumber);
}
