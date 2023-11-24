package com.cwms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.ScannedParcels;

public interface ScannedParcelsRepo extends JpaRepository<ScannedParcels, String> {

	@Query(value="select * from scanned_parcels where company_id=:cid and branch_id=:bid and scan_parcel_type='seepz'",nativeQuery = true)
	List<ScannedParcels> getalldata1(@Param("cid") String cid,@Param("bid") String bid);
	
	@Query(value="select * from scanned_parcels where company_id=:cid and branch_id=:bid and scan_parcel_type='seepz' and gateiout between :date1 and :date2 order by scanned_id desc",nativeQuery = true)
	List<ScannedParcels> getalldata3(@Param("cid") String cid,@Param("bid") String bid,@Param("date1") Date date1,@Param("date2") Date date2);
	
	@Query(value="select * from scanned_parcels where company_id=:cid and branch_id=:bid and scan_parcel_type='cargo'",nativeQuery = true)
	List<ScannedParcels> getalldata2(@Param("cid") String cid,@Param("bid") String bid);
	
	@Query(value="select * from scanned_parcels where company_id=:cid and branch_id=:bid and scan_parcel_type='cargo' and gateiout between :date1 and :date2 order by scanned_id desc",nativeQuery = true)
	List<ScannedParcels> getalldata4(@Param("cid") String cid,@Param("bid") String bid,@Param("date1") Date date1,@Param("date2") Date date2);
	
	@Query(value="select * from scanned_parcels where company_id=:cid and branch_id=:bid and sr_no=:sr",nativeQuery = true)
	List<ScannedParcels> getbysr(@Param("cid") String cid,@Param("bid") String bid,@Param("sr") String sr);
	
	@Query(value="select * from scanned_parcels where company_id=:cid and branch_id=:bid and doc_ref_no=:sr",nativeQuery = true)
	List<ScannedParcels> getbydocrefid(@Param("cid") String cid,@Param("bid") String bid,@Param("sr") String sr);
	

	@Query(value="select distinct sr_no from scanned_parcels where company_id=:cid and branch_id=:bid and scan_parcel_type='seepz' and type_of_transaction='Import-out' and gateiout between :date1 and :date2",nativeQuery = true)
	List<String> getImportoutdata(@Param("cid") String cid,@Param("bid") String bid,@Param("date1") Date date1,@Param("date2") Date date2);
	
	@Query(value="select distinct sr_no from scanned_parcels where company_id=:cid and branch_id=:bid and scan_parcel_type='seepz' and type_of_transaction='DTA-Import-out' and gateiout between :date1 and :date2",nativeQuery = true)
	List<String> getImportSuboutdata(@Param("cid") String cid,@Param("bid") String bid,@Param("date1") Date date1,@Param("date2") Date date2);
	
	@Query(value="select distinct sr_no from scanned_parcels where company_id=:cid and branch_id=:bid and scan_parcel_type='seepz' and type_of_transaction='DTA-Export-Out' and gateiout between :date1 and :date2",nativeQuery = true)
	List<String> getExportSuboutdata(@Param("cid") String cid,@Param("bid") String bid,@Param("date1") Date date1,@Param("date2") Date date2);
	
	@Query(value="select distinct sr_no from scanned_parcels where company_id=:cid and branch_id=:bid and scan_parcel_type='seepz' and type_of_transaction='Export-out' and gateiout between :date1 and :date2",nativeQuery = true)
	List<String> getExportoutdata(@Param("cid") String cid,@Param("bid") String bid,@Param("date1") Date date1,@Param("date2") Date date2);

}
