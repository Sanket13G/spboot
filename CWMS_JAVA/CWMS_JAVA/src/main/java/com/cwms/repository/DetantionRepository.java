package com.cwms.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.Detention;
import com.cwms.entities.Party;

public interface DetantionRepository extends JpaRepository<Detention, String> {

	
	@Query(value = "select * from detention where company_id = :companyId and branch_id = :branchId", nativeQuery = true)
    List<Detention> getalldata(@Param("companyId") String companyId, @Param("branchId") String branchId);
	
	
	Detention findDetentionByDetentionId(String detentionId);
	
	
	
	@Modifying
	@Query(value = "UPDATE Detention e " +
	        "SET e.issueDate = :newissueDate, e.issueOfficerName = :newissueOfficerName, e.issueOfficerDesignation = :newissueOfficerDesignation, " +
	        "e.issueDgdcOfficerName = :newissueDgdcOfficerName, e.issueDgdcOfficerDesignation = :newissueDgdcOfficerDesignation, " +
	        "e.issueNop = :newissueNop, e.issueType = :newissueType, e.issueReason = :newissueReason, e.issueRemarks = :newissueRemarks " +
	        "WHERE e.companyId = :companyId " +
	        "AND e.branchId = :branchId " +
	        "AND e.siNo = :sirNo " +
	        "AND e.detentionId =:detentionId"+
	        "AND e.fileNo = :fileNo",
	        nativeQuery = true)
	Detention updateDetention(
	        @Param("newissueDate") Date issueDate,
	        @Param("newissueOfficerName") String issueOfficerName,
	        @Param("newissueOfficerDesignation") String issueOfficerDesignation,
	        @Param("newissueDgdcOfficerName") String issueDgdcOfficerName,
	        @Param("newissueDgdcOfficerDesignation") String issueDgdcOfficerDesignation,
	        @Param("newissueNop") int issueNop,
	        @Param("newissueType") String issueType,
	        @Param("newissueReason") String issueReason,
	        @Param("newissueRemarks") String issueRemarks,
	        @Param("companyId") String companyId,
	        @Param("branchId") String branchId,
	        @Param("sirNo") long sirNo,
	        @Param("fileNo") String fileNo,
	        @Param("detentionId") String detentionId
	);

	//shubham
	
	
		 @Query(value = "SELECT SUM(nop) FROM detention WHERE Company_Id = :cid AND Branch_Id = :bid AND status = :string AND Deposit_Date < :date1 And Parcel_Type = :string2", nativeQuery = true)
		    Integer findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate5(@Param("cid") String cid, @Param("bid") String bid, @Param("string") String string,@Param("string2") String string2, @Param("date1") Date date1);
		
		 @Query(value = "SELECT SUM(nop) FROM detention WHERE Company_Id = :cid AND Branch_Id = :bid AND Deposit_Date = :date1 And Parcel_Type = :string2", nativeQuery = true)
		    Integer findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate6(@Param("cid") String cid, @Param("bid") String bid,@Param("string2") String string2, @Param("date1") Date date1);
		
		 @Query(value = "SELECT SUM(nop) FROM detention WHERE Company_Id = :cid AND Branch_Id = :bid AND (status = :string2 And Parcel_Type = :string3 AND Withdraw_Date = :date1) OR (status = :string1 AND Issue_Date = :date1)", nativeQuery = true)
		    Integer findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate7(@Param("cid") String cid, @Param("bid") String bid, @Param("string1") String string1,@Param("string2") String string2,@Param("string3") String string3, @Param("date1") Date date1);
		
		 
}
