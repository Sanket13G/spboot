package com.cwms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwms.entities.ShippingDetails;

public interface ScanRepository_Sumit extends JpaRepository<ShippingDetails, Integer> {
	
	Optional<ShippingDetails> findByEntityId(String Entity_ID);

}