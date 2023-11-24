package com.cwms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.ForwardIn;

public interface ForwardInRepo extends JpaRepository<ForwardIn, String> {
	public List<ForwardIn> findByCompanyIdAndBranchId(String companyId, String branchId);
	
	ForwardIn findByCompanyIdAndBranchIdAndSirNoAndPackageNo(String companyId, String branchId, String sirNo, String packageNo);

	@Query(value = "SELECT COALESCE(SUM(nop), 0) FROM Forward_In WHERE Company_Id = :cid AND Branch_Id = :bid AND Forward_in_date between :date1 and :date2", nativeQuery = true)
	int findSumByCompanyIdAndBranchIdAndDate9(@Param("cid") String cid, @Param("bid") String bid,  @Param("date1") Date date1, @Param("date2") Date date2);

	  @Query(value="select count(doc_ref_no) FROM Forward_In where company_id=:cid and branch_id=:bid and doc_ref_no=:doc",nativeQuery=true)
      int findbydoc_ref_no(@Param("cid") String cid, @Param("bid") String bid,  @Param("doc") String doc);
	  
	  
	  @Query(value="select count(doc_ref_no) FROM Forward_In where company_id=:cid and branch_id=:bid and sir_no=:doc",nativeQuery=true)
      int findbydoc_ref_no1(@Param("cid") String cid, @Param("bid") String bid,  @Param("doc") String doc);
	  
	  
}
