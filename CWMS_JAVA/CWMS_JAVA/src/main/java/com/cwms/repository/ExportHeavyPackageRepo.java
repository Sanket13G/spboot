package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.ExportHeavyPackage;

import jakarta.transaction.Transactional;

public interface ExportHeavyPackageRepo extends JpaRepository<ExportHeavyPackage, String> {

	@Query(value="select * from export_heavy_packages where company_id=:cid and branch_id=:bid and sb_request_id=:reqid and sb_number=:sbno and package_number=:packnum",nativeQuery=true)
	public ExportHeavyPackage finddata(@Param("cid")String cid,@Param("bid") String bid,@Param("reqid") String reqid,@Param("sbno") String sbNo,@Param("packnum") String packnum);
	
	@Query(value="select * from export_heavy_packages where company_id=:cid and branch_id=:bid and sb_request_id=:reqid and sb_number=:sbno",nativeQuery=true)
	public List<ExportHeavyPackage> findalldata(@Param("cid")String cid,@Param("bid") String bid,@Param("reqid") String reqid,@Param("sbno") String sbNo);

	@Transactional
	@Modifying
	@Query(value="update export_heavy_packages set package_number=:packno,weight=:wt where company_id=:cid and branch_id=:bid and sb_request_id=:reqid and sb_number=:sbno and package_number=:packnum",nativeQuery=true)
	public void updateData(@Param("packno")String packno,@Param("wt") String wt,@Param("cid")String cid,@Param("bid") String bid,@Param("reqid") String reqid,@Param("sbno") String sbNo,@Param("packnum") String packnum);
    
	
	@Query(value="select * from export_heavy_packages where company_id=:cid and branch_id=:bid and sb_request_id=:reqid and sb_number=:sbno",nativeQuery=true)
	public ExportHeavyPackage findexistingdata(@Param("cid")String cid,@Param("bid") String bid,@Param("reqid") String reqid,@Param("sbno") String sbNo);
}
