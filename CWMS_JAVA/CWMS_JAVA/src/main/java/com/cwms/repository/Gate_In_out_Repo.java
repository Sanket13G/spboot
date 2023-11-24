package com.cwms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.Gate_In_Out;

public interface Gate_In_out_Repo extends JpaRepository<Gate_In_Out, String> {
  
	@Query(value="select * from gate_in_out where company_id=:cid and branch_id=:bid and sr_no=:sr",nativeQuery=true)
	Gate_In_Out findbysr(@Param("cid") String cid,@Param("bid") String bid,@Param("sr") String sr);
}
