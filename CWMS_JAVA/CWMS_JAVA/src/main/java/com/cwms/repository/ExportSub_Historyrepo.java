package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.ExportSub_History;

public interface ExportSub_Historyrepo extends JpaRepository<ExportSub_History, String> {

	@Query(value="select * from exportsub_history where company_id=:cid and branch_id=:bid and request_id=:rid and ser_no=:ser",nativeQuery=true)
	List<ExportSub_History> getalldata(@Param("cid") String cid,@Param("bid") String bid,@Param("rid") String rid,@Param("ser") String ser);
 
	@Query(value="select * from exportsub_history where company_id=:cid and branch_id=:bid and request_id=:rid and ser_no=:ser",nativeQuery=true)
	ExportSub_History getSingledata(@Param("cid") String cid,@Param("bid") String bid,@Param("rid") String rid,@Param("ser") String ser);

	@Query(value="select * from exportsub_history where company_id=:cid and branch_id=:bid and request_id=:rid",nativeQuery=true)
	List<ExportSub_History> getSingledata1(@Param("cid") String cid,@Param("bid") String bid,@Param("rid") String rid);
}
