package com.cwms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.Services;
import com.cwms.repository.SerViceRepositary;
@Service
public class ServiceImpl implements ServicesInterface
{
	
	@Autowired
	private SerViceRepositary SerViceRepositary;
	
	@Override
	public Services addService(Services services) 
	{  	
		
//		Setting Default Values
		services.setServiceGroup("0");
		services.setServiceChangeDate(new Date());
		services.setServiceNewDescription("ServiceNewDescription");
		services.setCreatedDate(new Date());
		services.setApprovedDate(new Date());
		services.setServiceChangeDate(new Date());
		return SerViceRepositary.save(services);
	}

	@Override
	public Services updateService(Services services) 
	{
		services.setServiceNewDescription("ServiceNewDescription");
		services.setServiceGroup("0");
		services.setEditedDate(new Date());
		services.setServiceChangeDate(new Date());
		return SerViceRepositary.save(services);
	}

	@Override
	public List<Services> getServices(String CompId,String branchId) 
	{
		return this.SerViceRepositary.getActiveServices(CompId,branchId);
	}

	@Override
	public Services getServicesById(String compId,String branchId,String sid) 
	{
		return this.SerViceRepositary.findByService_Id(compId,branchId,sid);
	}

	
	@Override
	public void deleteServices(String compId,String branchId,String sid)
	{
		System.out.println(sid);
		Services Service = this.SerViceRepositary.findByService_Id(compId,branchId,sid);
		this.SerViceRepositary.delete(Service);
		
	}

	
	

	@Override
	public List<Services> findByService_IdNotIn(String compId,String branchId,List<String> excludedPartyIds) {
		// TODO Auto-generated method stub
		

		
		return this.SerViceRepositary.findServicesNotInIdsAndCompanyAndBranch(compId,branchId,excludedPartyIds);
	}

	@Override
	public String findTaxPercentage(String CompanyId, String branchId, String serviceId) {
		// TODO Auto-generated method stub
		return SerViceRepositary.findTaxPercentage(CompanyId, branchId, serviceId);
	}

	

	
}