package com.cwms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.ExportSub;
import com.cwms.entities.Import;

import jakarta.transaction.Transactional;

@EnableJpaRepositories
public interface ExportSubRepository extends JpaRepository<ExportSub, String> {
	@Query(value = "select i.* from exportsub i where i.company_id=:cid and i.branch_id=:bid and i.status<>'D' order by i.ser_no desc", nativeQuery = true)
	public List<ExportSub> getall(@Param("cid") String cid,@Param("bid") String bid);
	
	@Query(value = "select i.* from exportsub i where i.company_id=:cid and i.branch_id=:bid", nativeQuery = true)
	public List<ExportSub> getallFORCHECK(@Param("cid") String cid,@Param("bid") String bid);
	

	@Query(value="select i.* from exportsub i where i.company_id=:cid and i.branch_id=:bid and i.ser_no=:subid",nativeQuery=true)
	public ExportSub findExportSubByseronly(@Param("cid") String cid,@Param("bid") String bid,@Param("subid") String subid);
	
	@Query(value="select i.* from exportsub i where i.company_id=:cid and i.branch_id=:bid and i.request_id=:reqid",nativeQuery=true)
	public  List<ExportSub>findRequestId1(@Param("cid") String cid,@Param("bid") String bid,@Param("reqid") String reqid);
	
	
	@Query(value="select i.* from exportsub i where i.company_id=:cid and i.branch_id=:bid and i.ser_no=:subid and i.request_id=:reqid",nativeQuery=true)
	public ExportSub findExportSubByser(@Param("cid") String cid,@Param("bid") String bid,@Param("subid") String subid,@Param("reqid") String reqid);
	
	
	@Query(value="select i.* from exportsub i where i.company_id=:cid and i.branch_id=:bid and i.request_id=:reqid",nativeQuery=true)
	public ExportSub findRequestId(@Param("cid") String cid,@Param("bid") String bid,@Param("reqid") String reqid);
	
	@Query(value="select i.* from exportsub i where i.company_id=:cid and i.branch_id=:bid and i.exp_sub_id=:subid and i.request_id=:reqid",nativeQuery=true)
	public ExportSub findExportSub(@Param("cid") String cid,@Param("bid") String bid,@Param("subid") String subid,@Param("reqid") String reqid);
	
	@Transactional
	@Modifying
	@Query(value="update exportsub set nsdl_status=:nsdl,status_doc=:statusdoc where company_id=:cid and branch_id=:bid and exp_sub_id=:expid and request_id=:reqid",nativeQuery=true)
	public void updateData(@Param("nsdl") String nsdl,@Param("statusdoc") String statusdoc,@Param("cid") String cid,@Param("bid") String bid,@Param("expid") String expid,@Param("reqid") String reqid);
	
	@Transactional
	@Modifying
	@Query(value="update exportsub set dgdc_status='Handed over to Party/CHA',handover_party_cha='P',handover_party_name=:hpid,handover_represntative_id=:representid, out_date = CURDATE()  where company_id=:cid and branch_id=:bid and exp_sub_id=:eid and request_id=:rid",nativeQuery=true)
	public void updateStatus(@Param("cid") String cid,@Param("bid") String bid,@Param("eid") String eid,@Param("rid") String rid,@Param("hpid") String hpid,@Param("representid") String representid);
	
//	@Transactional
//	@Modifying
//	@Query(value="update exportsub set dgdc_status='Handed over to Party/CHA',handover_party_cha='C',handover_party_name=:hpid,handover_represntative_id=:representid  where company_id=:cid and branch_id=:bid and exp_sub_id=:eid and request_id=:rid",nativeQuery=true)
//	public void updateCHAStatus(@Param("cid") String cid,@Param("bid") String bid,@Param("eid") String eid,@Param("rid") String rid,@Param("hpid") String hpid,@Param("representid") String representid);

	
//	Updating outdate
	
	@Transactional
	@Modifying
	@Query(value="update exportsub set dgdc_status='Handed over to Party/CHA',handover_party_cha='C',handover_party_name=:hpid,handover_represntative_id=:representid , out_date = CURDATE()  where company_id=:cid and branch_id=:bid and exp_sub_id=:eid and request_id=:rid",nativeQuery=true)
	public void updateCHAStatus(@Param("cid") String cid,@Param("bid") String bid,@Param("eid") String eid,@Param("rid") String rid,@Param("hpid") String hpid,@Param("representid") String representid);
	
	
	@Transactional
	@Modifying
	@Query(value="update exportsub set status='D'  where company_id=:cid and branch_id=:bid and exp_sub_id=:eid and request_id=:rid",nativeQuery=true)
	public void updateDELETEStatus(@Param("cid") String cid,@Param("bid") String bid,@Param("eid") String eid,@Param("rid") String rid);
	 
	 @Query(value = "SELECT * FROM Exportsub WHERE Company_Id = :companyId AND Branch_Id = :branchId AND DGDC_Status = 'Handed over to Party/CHA' AND Out_Date = :startDate AND handover_represntative_id = :representativeId AND (handover_party_cha = :paryCHAId OR handover_party_name = :paryCHAId) AND (gate_pass_status IS NULL OR gate_pass_status != 'Y')", nativeQuery = true)
	 List<ExportSub> findByCompanyAndBranchAndSerDate(String companyId, String branchId, Date startDate, String paryCHAId, String representativeId);
 
	 
//	 @Query(value = "SELECT * FROM Exportsub WHERE Company_Id = :companyId AND Branch_Id = :branchId AND DGDC_Status = 'Handed over to Party/CHA' AND SER_Date = :startDate AND handover_represntative_id = :representativeId AND (handover_party_cha = :paryCHAId OR handover_party_name = :paryCHAId)", nativeQuery = true)
//	 List<ExportSub> findByCompanyAndBranchAndSerDate3(String companyId, String branchId, Date startDate, String paryCHAId, String representativeId);
 
	 
	 @Query(value = "SELECT * FROM Exportsub WHERE Company_Id = :companyId AND Branch_Id = :branchId AND DGDC_Status = 'Handed over to Party/CHA' AND Out_Date = :startDate AND handover_represntative_id = :representativeId AND (handover_party_cha = :paryCHAId OR handover_party_name = :paryCHAId)", nativeQuery = true)
	 List<ExportSub> findByCompanyAndBranchAndSerDate3(String companyId, String branchId, Date startDate, String paryCHAId, String representativeId);
	 
	 
	 @Query(value="select i.* from exportsub where (:dgdcStatus IS NULL OR :dgdcStatus = '' OR i.dgdc_status = :dgdcStatus) and ((:startDate IS NULL AND :endDate IS NULL) OR (:startDate IS NULL AND :endDate >= i.ser_date) OR (:startDate <= i.ser_date AND :endDate IS NULL) OR (:startDate <= i.ser_date AND :endDate >= i.ser_date)) AND i.company_id = :companyId AND i.branch_id = :branchId ORDER BY i.ser_date ASC",nativeQuery=true)
		List<ExportSub> findByAttributes(
		    @Param("companyId") String companyId,
		    @Param("branchId") String branchId,
		    @Param("dgdcStatus") String dgdcStatus,
		    @Param("startDate") Date startDate,
		    @Param("endDate") Date endDate);
	 
	 @Query(value = "SELECT * FROM exportsub WHERE DATE_FORMAT(ser_date, '%Y-%m-%d') =:serDate AND company_id =:compnayId AND branch_id =:branchId AND dgdc_status =:dgdcStatus ORDER BY ser_No ASC", nativeQuery = true)

		List<ExportSub> findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(@Param("serDate") String serDate,
				@Param("compnayId") String compnayId, @Param("branchId") String branchId,
				@Param("dgdcStatus") String dgdcStatus);
	 
	 @Query(value = "SELECT DISTINCT * " +
	            "FROM exportsub " +
	            "WHERE company_id = :companyId " +
	            "AND branch_id = :branchId " +
	            "AND ser_date BETWEEN :startDate AND :endDate " +
	           
	            "AND exporter = :exporter", nativeQuery = true)
	     List<ExportSub> findImportAllData(String companyId, String branchId, Date startDate, Date endDate,
	                                    String exporter);
	 
	 
	 @Query(value = "SELECT DISTINCT * " +
	            "FROM exportsub " +
	            "WHERE company_id = :companyId " +
	            "AND branch_id = :branchId " +    
	            "AND request_id = :requestId", nativeQuery = true)
	     List<ExportSub> findRequestIdData(String companyId, String branchId,
	                                    String requestId);
	 
	 @Query(value = "SELECT  * " +
	            "FROM exportsub " +
	            "WHERE company_id = :companyId " +
	            "AND branch_id = :branchId " + 
	      
	            "AND ser_date BETWEEN :startDate AND :endDate ",
	           
	            nativeQuery = true)
	     List<ExportSub> findExportSubAllData(String companyId, String branchId, Date startDate, Date endDate);
	 
	 public ExportSub findByCompanyIdAndBranchIdAndSerDateAndExporterAndNop(String companyId, String branchId, Date startDate,String exporter,int nop);
	 
	//shubham 
		 @Query(value = "SELECT SUM(nop) FROM exportsub WHERE Company_Id = :cid AND Branch_Id = :bid  AND SER_Date = :date1", nativeQuery = true)
		    Integer findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate2(@Param("cid") String cid, @Param("bid") String bid,  @Param("date1") Date date1);
		
		 @Query(value = "SELECT SUM(nop) FROM exportsub WHERE Company_Id = :cid AND Branch_Id = :bid AND DGDC_Status = :string AND SER_Date = :date1", nativeQuery = true)
		    Integer findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate4(@Param("cid") String cid, @Param("bid") String bid, @Param("string") String string, @Param("date1") Date date1);
		
		 @Transactional
		 @Modifying
		 @Query(value = "UPDATE ExportSub SET gate_pass_status = 'Y' WHERE exp_sub_id IN :exportSubIds", nativeQuery = true)
		 void setGatePassStatusToY(@Param("exportSubIds") List<String> exportSubIds);
		 
		 List<ExportSub> findByCompanyIdAndBranchIdAndExporterAndSerDateBetweenAndStatusNot(String companyId, String branchId,String partyId, Date startDate,Date endDate , String Status);
//		 List<ExportSub> findByCompanyIdAndBranchIdAndExporterAndSerDateBetweenAndStatusNot(String companyId, String branchId,String partyId, Date startDate,Date endDate , String Status);
		 List<ExportSub> findByCompanyIdAndBranchIdAndExporterAndSerDate(String companyId, String branchId,String partyId, Date serDate);
		 
		 
		//sanket
		 
		 @Query(value="select i.* from exportsub i where i.company_id=:cid and i.branch_id=:bid and i.exporter=:exp and i.dgdc_status='Handed over to DGDC SEEPZ' and i.nsdl_status='Passed Out' and forwarded_status!='FWD_OUT' and i.status != 'D'",nativeQuery=true)
			public List<ExportSub> findExportSubByparty(@Param("cid") String cid,@Param("bid") String bid,@Param("exp") String exporter);
			
		 @Query(value = "select i.* from exportsub i where i.company_id=:cid and i.branch_id=:bid and i.exporter=:exporter and i.dgdc_status='Handed over to DGDC SEEPZ' and i.nsdl_status='Passed Out' and i.status != 'D' and forwarded_status!='FWD_OUT'", nativeQuery = true)
			public List<ExportSub> getalldatabyparty(@Param("cid") String cid,@Param("bid") String bid,@Param("exporter") String exporter);
			
		 
		 @Query(value = "select i.* from exportsub i where i.company_id=:cid and i.branch_id=:bid and i.exporter =:exporter and i.status<>'D' order by i.ser_no desc", nativeQuery = true)
			public List<ExportSub> getall1(@Param("cid") String cid,@Param("bid") String bid,@Param("exporter") String exporter);
			
			@Query(value = "select i.* from exportsub i where i.company_id=:cid and i.branch_id=:bid and i.handover_party_cha='C' and i.handover_party_name =:exporter and i.status<>'D' order by i.ser_no desc", nativeQuery = true)
			public List<ExportSub> getall2(@Param("cid") String cid,@Param("bid") String bid,@Param("exporter") String exporter);
}
