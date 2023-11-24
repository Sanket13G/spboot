package com.cwms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cwms.entities.ShippingDetails;
import com.cwms.repository.ScanRepository_Sumit;

@Component
public class ShippingDetailsImpl implements ShippingDetailsService{

	@Autowired
	ScanRepository_Sumit shippingdetailsRepository;
	
	@Override
	public ShippingDetails create(ShippingDetails shippingdetails) {
	return shippingdetailsRepository.save(shippingdetails);
	
	}
	
	public List<ShippingDetails>  getShippingDetails() {
		return shippingdetailsRepository.findAll();
	}
	
    public ShippingDetails getshiShippingDetailsByEntityId(String entityId) {
		Optional<ShippingDetails> op =  shippingdetailsRepository.findByEntityId(entityId);
		return op.get();
	
}
	
}

