package com.cwms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cwms.entities.Party;
import com.cwms.entities.Services;


@Service
public interface ServicesInterface 
{
	public Services addService(Services services);
	public Services updateService(Services services);
	public List<Services> getServices(String compId,String branchId);
	public Services getServicesById(String compId,String branchId,String sid);
	public void deleteServices(String compId,String branchId,String sid);
	
	public List<Services> findByService_IdNotIn(String CompanyId,String branchId, List<String> excludedPartyIds);
	
    public String findTaxPercentage(String CompanyId,String branchId,String serviceId);
    
	
}