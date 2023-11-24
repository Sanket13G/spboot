package com.cwms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cwms.entities.ImportDgdcDeliveryStatus;
import com.cwms.repository.ImportDgdcDeliveryStatusRepositary;
@Service
public class ImportDgdcDeliveryStatusServiceIMPL 
{
	@Autowired
	private ImportDgdcDeliveryStatusRepositary ImportDgdcDeliveryStatusRepositary;
	
	
	public List<ImportDgdcDeliveryStatus> findAll(String companyId,String branchId)
	{
		return ImportDgdcDeliveryStatusRepositary.findByCompanyIdAndBranchId(companyId, branchId);
	}

}
