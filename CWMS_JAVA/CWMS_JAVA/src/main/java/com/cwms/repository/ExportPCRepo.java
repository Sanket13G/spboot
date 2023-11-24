package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.ExportPC;

@EnableJpaRepositories
public interface ExportPCRepo extends JpaRepository<ExportPC, String>  {
	
	@Query(value="select * from exportpc where company_id=:cid and branch_id=:bid",nativeQuery = true)
	List<ExportPC> findalldata(@Param("cid") String cid,@Param("bid") String bid);
	
	@Query(value="select * from exportpc where company_id=:cid and branch_id=:bid and sb_request_id=:reqid and sb_number=:sbno and ser_no=:serno",nativeQuery=true)
	ExportPC getdataById(@Param("cid") String cid,@Param("bid") String bid,@Param("reqid") String reqid,@Param("sbno") String sbno,@Param("serno") String serno);

}
