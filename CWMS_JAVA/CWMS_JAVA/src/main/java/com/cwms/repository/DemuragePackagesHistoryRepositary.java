package com.cwms.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.DemuragePackagesHistory;

public interface DemuragePackagesHistoryRepositary extends JpaRepository<DemuragePackagesHistory, Long>
{
	List<DemuragePackagesHistory> findByCompanyIdAndBranchIdAndPartyIdAndInviceNo(String companyId,String branchId,String partyId,String invoiceno);	
	
	@Query("SELECT dph FROM DemuragePackagesHistory dph " +
	        "WHERE dph.companyId = :companyId " +
	        "AND dph.branchId = :branchId " +
	        "AND dph.partyId = :partyId " +
	        "AND dph.inviceDate >= :startDate " +
	        "AND dph.inviceDate <= :endDate " +
	        "AND (:inviceNo IS NULL OR :inviceNo = '' OR dph.inviceNo = :inviceNo) " +
	        "ORDER BY dph.inviceDate DESC")
	List<DemuragePackagesHistory> findDemuragePackagesByCriteria(
	        @Param("companyId") String companyId,
	        @Param("branchId") String branchId,
	        @Param("partyId") String partyId,
	        @Param("startDate") Date startDate,
	        @Param("endDate") Date endDate,
	        @Param("inviceNo") String inviceNo
	);
	
	
}
