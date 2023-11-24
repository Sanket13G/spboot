package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.Export_History;

public interface Export_HistoryRepository extends JpaRepository<Export_History, String> {


	@Query(value = "SELECT * FROM export_history " + "WHERE branch_id = ?1 " + "AND company_id = ?2 "
			+ "AND sb_number = ?3 " + "AND sb_request_id = ?4 " + "AND ser_no = ?5", nativeQuery = true)
	List<Export_History> findRecordsByCriteria(String branchId, String companyId, String sbNumber, String sbRequestId,
			String serNo);
	
	@Query(value = "SELECT * FROM export_history " + "WHERE branch_id = ?1 " + "AND company_id = ?2 "
			+ "AND sb_number = ?3 " + "AND sb_request_id = ?4 ", nativeQuery = true)
	List<Export_History> findRecordsByCriteria1(String branchId, String companyId, String sbNumber, String sbRequestId);
	
	@Query(value="SELECT * FROM dev_cwms.export_history where company_id=:cid and branch_id=:bid and sb_number=:sbno and sb_request_id=:reqid and ser_no=:ser order by hid desc limit 1",nativeQuery=true)
	Export_History findSingledata(@Param("cid") String cid,@Param("bid") String bid,@Param("sbno") String sbno,@Param("reqid") String reqid,@Param("ser") String ser);
	
}