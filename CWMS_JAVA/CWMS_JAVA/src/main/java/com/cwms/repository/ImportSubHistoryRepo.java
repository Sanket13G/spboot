package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.ExportSub_History;
import com.cwms.entities.ImportSub_History;

public interface ImportSubHistoryRepo extends JpaRepository<ImportSub_History, String> {
       
	@Query(value="select * from importsub_history where company_id=:cid and branch_id=:bid and request_id=:rid and sir_no=:ser",nativeQuery=true)
	List<ImportSub_History> getalldata(@Param("cid") String cid,@Param("bid") String bid,@Param("rid") String rid,@Param("ser") String ser);
 
	@Query(value="select * from importsub_history where company_id=:cid and branch_id=:bid and request_id=:rid and sir_no=:ser",nativeQuery=true)
	ImportSub_History getSingledata(@Param("cid") String cid,@Param("bid") String bid,@Param("rid") String rid,@Param("ser") String ser);




}
