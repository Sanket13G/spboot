package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwms.entities.ImportDgdcDeliveryStatus;

public interface ImportDgdcDeliveryStatusRepositary extends JpaRepository<ImportDgdcDeliveryStatus, Long> 
{
	
	List<ImportDgdcDeliveryStatus> findByCompanyIdAndBranchId(String companyId,String branchId);

}
