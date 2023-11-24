package com.cwms.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.CFSTariffService;

public interface cfsServicesRepo extends JpaRepository<CFSTariffService, String>
{
	
	 List<CFSTariffService> findByCompanyIdAndBranchId(String companyId, String branchId);
	 
	 List<CFSTariffService> findByCfsTariffNoAndCompanyIdAndBranchId(String cfsTariffNo, String companyId, String branchId);
	 
	 CFSTariffService findByCompanyIdAndBranchIdAndCfsTariffNoAndCfsAmndNoAndServiceId(String companyId, String branchId, String cfsTariffNo,String amndno ,String serviceId);
	 CFSTariffService findByCompanyIdAndBranchIdAndCfsTariffNoAndCfsAmndNoAndServiceIdAndPartyId(String companyId, String branchId, String cfsTariffNo,String amndno ,String serviceId,String partId);
	 
	 @Query("SELECT c.rate FROM CFSTariffService c " +
		       "WHERE c.companyId = :companyId " +
		       "AND c.branchId = :branchId " +
		       "AND c.cfsTariffNo = :cfsTariffNo " +
		       "AND c.cfsAmndNo = :cfsAmndNo " +
		       "AND c.serviceId = :serviceId " +
		       "AND c.partyId = :partyId")
		Double findRateService(
		    @Param("companyId") String companyId,
		    @Param("branchId") String branchId,
		    @Param("cfsTariffNo") String cfsTariffNo,
		    @Param("cfsAmndNo") String cfsAmndNo,
		    @Param("serviceId") String serviceId,
		    @Param("partyId") String partyId
		);
	 
	 @Query("SELECT c.rate FROM CFSTariffService c " +
		       "WHERE c.companyId = :companyId " +
		       "AND c.branchId = :branchId " +
		       "AND c.cfsTariffNo = :cfsTariffNo " +
		       "AND c.cfsAmndNo = :cfsAmndNo " +
		       "AND c.serviceId = :serviceId ")
		Double findRateServiceByTarrifNo(
		    @Param("companyId") String companyId,
		    @Param("branchId") String branchId,
		    @Param("cfsTariffNo") String cfsTariffNo,
		    @Param("cfsAmndNo") String cfsAmndNo,
		    @Param("serviceId") String serviceId   
		);
	 

}