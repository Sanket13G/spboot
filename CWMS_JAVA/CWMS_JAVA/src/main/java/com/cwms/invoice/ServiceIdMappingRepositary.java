package com.cwms.invoice;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceIdMappingRepositary extends JpaRepository<ServiceIdMapping, Long>
{
//	String findByCompanyIdAndBranchIdAndServiceName(String companyId, String branchId, String serviceName);
	 @Query("SELECT p.serviceId FROM ServiceIdMapping p WHERE p.companyId = :companyId AND p.branchId = :branchId AND p.serviceName = :serviceName")
	    String findServieIdByKeys(
	        @Param("companyId") String companyId,
	        @Param("branchId") String branchId,
	        @Param("serviceName") String serviceName
	    );
}
