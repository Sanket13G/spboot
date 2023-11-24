package com.cwms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.Forward_out;

@EnableJpaRepositories
public interface ForwardOutRepo extends JpaRepository<Forward_out, String> {

	public List<Forward_out> findByCompanyIdAndBranchId(String companyId, String branchId);

	  Forward_out findByCompanyIdAndBranchIdAndSirNoAndPackageNo(String companyId, String branchId, String sirNo, String packageNo);
	
	  @Query(value = "SELECT COALESCE(SUM(nop), 0) FROM Forward_out WHERE Company_Id = :cid AND Branch_Id = :bid AND Forward_out_date between :date1 and :date2", nativeQuery = true)
	  int findSumByCompanyIdAndBranchIdAndDate8(@Param("cid") String cid, @Param("bid") String bid,  @Param("date1") Date date1,@Param("date2") Date date2);

	  @Query(value="select count(doc_ref_no) FROM forward_out where company_id=:cid and branch_id=:bid and doc_ref_no=:doc",nativeQuery=true)
      int findbydoc_ref_no(@Param("cid") String cid, @Param("bid") String bid,  @Param("doc") String doc);
	  
	  
	  @Query(value="select count(doc_ref_no) FROM forward_out where company_id=:cid and branch_id=:bid and sir_no=:doc",nativeQuery=true)
      int findbydoc_ref_no1(@Param("cid") String cid, @Param("bid") String bid,  @Param("doc") String doc);
	  
	  
}
