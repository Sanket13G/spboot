package com.cwms.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.CFSTariffRange;

public interface CFSTariffRangeRepositary extends JpaRepository<CFSTariffRange, String> {
	
	List<CFSTariffRange> findByCompanyIdAndBranchIdAndCfsTariffNoAndCfsAmndNo(
		        String companyId, String branchId, String cfsTariffNo, String cfsAmndNo
		    );

	 List<CFSTariffRange> findByCompanyIdAndBranchId(String companyId, String branchId);
	
	 List<CFSTariffRange> findByCompanyIdAndBranchIdAndCfsTariffNoAndCfsAmndNoAndServiceIdAndStatusNot(String companyId, String branchId, String cfsTariffNo, String cfsAmndNo, String serviceId, String status
		    );
//	List<CFSTariffRange> findByCfsTariffNoAndServiceId(String cfsTariffNo, String serviceId);

//	CFSTariffRange findByCfsTariffNoAndServiceIdAndSrlNo(String tarrifNo, String serviceId, int srlNo);

	@Query(value = 
	        "SELECT DISTINCT a.cfs_tariff_no, a.service_id, a.tax_applicable, s.service_short_desc, s.rate_calculation, a.rate,a.CFS_Amnd_No " +
	        "FROM cfstdtrfrng a " +
	        "LEFT OUTER JOIN services s ON a.company_id = s.company_id AND a.branch_id = s.branch_id AND a.service_ID = s.service_id " +
	        "WHERE a.company_id = :companyId AND a.branch_id = :branchId AND a.CFS_Tariff_No = :tariffNo AND a.status <> 'D' " +
	        
	        "UNION ALL " +
	        
	        "SELECT a.cfs_tariff_no, a.service_id, a.tax_applicable, s.service_short_desc,s.rate_calculation ,a.rate,a.CFS_Amnd_No " +
	        "FROM cfstdtrfsrv a " +
	        "LEFT OUTER JOIN services s ON a.company_id = s.company_id AND a.branch_id = s.branch_id AND a.service_ID = s.service_id " +
	        "WHERE a.company_id = :companyId AND a.branch_id = :branchId AND a.CFS_Tariff_No = :tariffNo AND a.status <> 'D'", nativeQuery = true)
	    List<Object[]> getCombinedResultsForTariff(String companyId, String branchId, String tariffNo);


	    @Query("SELECT tr.rangeRate FROM CFSTariffRange tr " +
	            "WHERE tr.companyId = :companyId " +
	            "AND tr.branchId = :branchId " +
	            "AND tr.cfsTariffNo = :cfsTariffNo " +
	            "AND tr.cfsAmndNo = :cfsAmndNo " +
	            "AND tr.serviceId = :serviceId " +
	            "AND :totalPackages BETWEEN tr.rangeFrom AND tr.rangeTo " +
	            "AND tr.rangeType = 'Range'")
	     BigDecimal findRateByCriteria(@Param("companyId") String companyId,
	                                   @Param("branchId") String branchId,
	                                   @Param("cfsTariffNo") String cfsTariffNo,
	                                   @Param("cfsAmndNo") String cfsAmndNo,
	                                   @Param("serviceId") String serviceId,
	                                   @Param("totalPackages") int totalPackages);

	    List<CFSTariffRange> findByCompanyIdAndBranchIdAndCfsTariffNoAndCfsAmndNoAndServiceIdAndPartyIdAndStatusNot(String companyId, String branchId, String cfsTariffNo, String cfsAmndNo, String serviceId, String partyId ,String status);
	    
	    
}