package com.cwms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cwms.entities.ImportNsdlDeliveryStatus;

public interface ImportNsdlDeliveryStatusRepositary extends JpaRepository<ImportNsdlDeliveryStatus, Long>
{
 List<ImportNsdlDeliveryStatus> findByCompanyIdAndBranchId(String companyId,String branchId);
}
