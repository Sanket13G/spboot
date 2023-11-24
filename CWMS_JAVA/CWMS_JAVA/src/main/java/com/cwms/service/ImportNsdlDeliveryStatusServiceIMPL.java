package com.cwms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.ImportNsdlDeliveryStatus;
import com.cwms.repository.ImportNsdlDeliveryStatusRepositary;
@Service
public class ImportNsdlDeliveryStatusServiceIMPL 
{
	@Autowired
	private ImportNsdlDeliveryStatusRepositary ImportNsdlDeliveryStatusRepositary;
	
	
	public List<ImportNsdlDeliveryStatus> findAll(String companyId,String branchId)
	{
		return ImportNsdlDeliveryStatusRepositary.findByCompanyIdAndBranchId(companyId, branchId);
	}

}
