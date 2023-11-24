package com.cwms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.ExportSub;
import com.cwms.entities.ImportSub;

import jakarta.transaction.Transactional;

import java.util.*;

@EnableJpaRepositories
public interface ImportSubRepository extends JpaRepository<ImportSub, String> {

	@Query(value = "select i.* from importsub i where i.company_id=:cid and i.branch_id=:bid", nativeQuery = true)
	public List<ImportSub> getall(@Param("cid") String cid,@Param("bid") String bid);
	
	@Query(value="select i.* from importsub i where i.company_id=:cid and i.branch_id=:bid and i.request_id=:reqid",nativeQuery=true)
	public List<ImportSub> findRequestAllId(@Param("cid") String cid,@Param("bid") String bid,@Param("reqid") String reqid);
	
	@Query(value = "select i.* from importsub i where (i.import_type='LGD' or i.import_type='Zone to Zone') and i.company_id=:cid and i.branch_id=:bid order by sir_no desc", nativeQuery = true)
	public List<ImportSub> getalltocheckLGD(@Param("cid") String cid,@Param("bid") String bid);
	
	@Query(value = "select i.* from importsub i where !(i.import_type='LGD' or i.import_type='Zone to Zone') and i.company_id=:cid and i.branch_id=:bid order by sir_no desc", nativeQuery = true)
	public List<ImportSub> getalltocheckLGD1(@Param("cid") String cid,@Param("bid") String bid);
	
	 @Query(value = "SELECT SUM(nop) FROM importsub WHERE Company_Id = :cid AND Branch_Id = :bid AND DGDC_Status = :string AND SIR_Date = :date1", nativeQuery = true)
	    Integer findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate2(@Param("cid") String cid, @Param("bid") String bid, @Param("string") String string, @Param("date1") Date date1);
	

	   
	    @Query(value = "SELECT * FROM Importsub WHERE Company_Id = :companyId AND Branch_Id = :branchId AND DGDC_Status = 'Handed over to Party/CHA' AND Out_Date =  :startDate AND handover_represntative_id = :representativeId AND (handover_party_cha = :paryCHAId OR handover_party_name = :paryCHAId) AND (gate_pass_status IS NULL OR gate_pass_status != 'Y')", nativeQuery = true)
	    List<ImportSub> findByCompanyAndBranchAndDate2(String companyId, String branchId, Date startDate, String paryCHAId, String representativeId);
	    
	    
//	    @Query(value = "SELECT * FROM Importsub WHERE Company_Id = :companyId AND Branch_Id = :branchId AND DGDC_Status = 'Handed over to Party/CHA' AND SIR_Date =  :startDate AND handover_represntative_id = :representativeId AND (handover_party_cha = :paryCHAId OR handover_party_name = :paryCHAId) ", nativeQuery = true)
//	    List<ImportSub> findByCompanyAndBranchAndDate5(String companyId, String branchId, Date startDate, String paryCHAId, String representativeId);
	    
	    @Query(value = "SELECT * FROM Importsub WHERE Company_Id = :companyId AND Branch_Id = :branchId AND DGDC_Status = 'Handed over to Party/CHA' AND Out_Date =  :startDate AND handover_represntative_id = :representativeId AND (handover_party_cha = :paryCHAId OR handover_party_name = :paryCHAId) ", nativeQuery = true)
	    List<ImportSub> findByCompanyAndBranchAndDate5(String companyId, String branchId, Date startDate, String paryCHAId, String representativeId);
	    
	    @Transactional
	    @Modifying
	    @Query(value = "UPDATE ImportSub SET gate_pass_status = 'Y' WHERE imp_sub_id IN :importSubIds", nativeQuery = true)
	    void setGatePassStatusToY(@Param("importSubIds") List<String> importSubIds);


	
	@Query(value="select i.* from importsub i where i.company_id=:cid and i.branch_id=:bid and i.request_id=:reqid",nativeQuery=true)
	public ImportSub findRequestId(@Param("cid") String cid,@Param("bid") String bid,@Param("reqid") String reqid);
	
	@Query(value="select i.* from importsub i where i.company_id=:cid and i.branch_id=:bid and i.imp_sub_id=:subid and i.request_id=:reqid",nativeQuery=true)
	public ImportSub findImportSub(@Param("cid") String cid,@Param("bid") String bid,@Param("subid") String subid,@Param("reqid") String reqid);
	
	@Query(value="select * from importsub where company_id=:cid and branch_id=:bid and sir_no=:sir",nativeQuery=true)
    ImportSub  Singledata(@Param("cid") String companyId,@Param("bid") String branchId,@Param("sir") String sir);
	
	@Query(value="select i.* from importsub i where i.company_id=:cid and i.branch_id=:bid and i.sir_no=:subid and i.request_id=:reqid",nativeQuery=true)
	public ImportSub findImportSubBysir(@Param("cid") String cid,@Param("bid") String bid,@Param("subid") String subid,@Param("reqid") String reqid);
	
	@Query(value="select i.* from importsub i where i.company_id=:cid and i.branch_id=:bid and i.sir_no=:subid",nativeQuery=true)
	public ImportSub findImportSubBysironly(@Param("cid") String cid,@Param("bid") String bid,@Param("subid") String subid);
	

	@Transactional
	@Modifying
	@Query(value="update importsub set nsdl_status=:nsdl,status_doc=:statusdoc where company_id=:cid and branch_id=:bid and imp_sub_id=:expid and request_id=:reqid",nativeQuery=true)
	public void updateData(@Param("nsdl") String nsdl,@Param("statusdoc") String statusdoc,@Param("cid") String cid,@Param("bid") String bid,@Param("expid") String expid,@Param("reqid") String reqid);
	
	@Transactional
	@Modifying
	@Query(value="update importsub set dgdc_status='Handed over to Party/CHA',handover_party_cha='P',handover_party_name=:hpid,handover_represntative_id=:representid , out_date = CURDATE() where company_id=:cid and branch_id=:bid and imp_sub_id=:eid and request_id=:rid",nativeQuery=true)
	public void updateStatus(@Param("cid") String cid,@Param("bid") String bid,@Param("eid") String eid,@Param("rid") String rid,@Param("hpid") String hpid,@Param("representid") String representid);
	
//	@Transactional
//	@Modifying
//	@Query(value="update importsub set dgdc_status='Handed over to Party/CHA',handover_party_cha='C',handover_party_name=:hpid,handover_represntative_id=:representid  where company_id=:cid and branch_id=:bid and imp_sub_id=:eid and request_id=:rid",nativeQuery=true)
//	public void updateCHAStatus(@Param("cid") String cid,@Param("bid") String bid,@Param("eid") String eid,@Param("rid") String rid,@Param("hpid") String hpid,@Param("representid") String representid);

	
//	Selecting out Date
	@Transactional
	@Modifying
	@Query(value = "UPDATE importsub SET dgdc_status='Handed over to Party/CHA', handover_party_cha='C', handover_party_name=:hpid, handover_represntative_id=:representid, out_date = CURDATE() " +
	       "WHERE company_id=:cid AND branch_id=:bid AND imp_sub_id=:eid AND request_id=:rid", nativeQuery = true)
	public void updateCHAStatus(@Param("cid") String cid, @Param("bid") String bid, @Param("eid") String eid, @Param("rid") String rid, @Param("hpid") String hpid, @Param("representid") String representid);
	
	@Query(value = "SELECT * FROM importsub WHERE DATE_FORMAT(sir_date, '%Y-%m-%d') =:sirDate AND company_id =:compnayId AND branch_id =:branchId AND dgdc_status =:dgdcStatus", nativeQuery = true)

	List<ImportSub> findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(@Param("sirDate") String sirDate,
			@Param("compnayId") String compnayId, @Param("branchId") String branchId,
			@Param("dgdcStatus") String dgdcStatus);

	@Query(value = "SELECT DISTINCT * " +
            "FROM importsub " +
            "WHERE company_id = :companyId " +
            "AND branch_id = :branchId " +
         
            "AND exporter = :exporter", nativeQuery = true)
     List<ImportSub> findImportAllData(String companyId, String branchId,
                                    String exporter);
	

 
 
 
 @Query(value = "SELECT  * " +
            "FROM importsub " +
            "WHERE company_id = :companyId " +
            "AND branch_id = :branchId " + 
      
            "AND sir_date BETWEEN :startDate AND :endDate ",
           
            nativeQuery = true)
     List<ImportSub> findImportSubAllData(String companyId, String branchId, Date startDate, Date endDate);
 
 
 public ImportSub findByCompanyIdAndBranchIdAndSirDateAndExporterAndNop(String companyId, String branchId, Date startDate,String exporter,int nop);
	
//shubham
@Query(value = "SELECT SUM(nop) FROM importsub WHERE Company_Id = :cid AND Branch_Id = :bid AND SIR_Date = :date1", nativeQuery = true)
Integer findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate2(@Param("cid") String cid, @Param("bid") String bid, @Param("date1") Date date1);


@Query(value = "SELECT SUM(Nop) FROM importsub WHERE Company_Id = :cid AND Branch_Id = :bid AND DGDC_Status = :string AND SIR_Date = :date1", nativeQuery = true)
Integer findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate3(@Param("cid") String cid, @Param("bid") String bid, @Param("string") String string, @Param("date1") Date date1);

List<ImportSub> findByCompanyIdAndBranchIdAndExporterAndSirDateBetweenAndStatusNot(String companyId, String branchId,String partyId, Date startDate,Date endDate , String Status);
List<ImportSub> findByCompanyIdAndBranchIdAndExporterAndSirDateAndStatusNot(String companyId, String branchId,String partyId,Date serDate , String Status);

//sanket
@Query(value = "select i.* from importsub i where i.company_id=:cid and i.branch_id=:bid and i.exporter=:exporter and i.dgdc_status='Handed over to DGDC SEEPZ' and (i.nsdl_status='Passed In Full' or i.nsdl_status='Passed In Partial') and i.status != 'D' and forwarded_status!='FWD_OUT' and !(import_type='LGD' or import_type='Zone to Zone')", nativeQuery = true)
	public List<ImportSub> getalldatabyparty(@Param("cid") String cid,@Param("bid") String bid,@Param("exporter") String exporter);
	

@Query(value = "select i.* from importsub i where i.company_id=:cid and i.branch_id=:bid and i.exporter=:exporter and i.dgdc_status='Handed over to DGDC SEEPZ' and (i.nsdl_status='Passed In Full' or i.nsdl_status='Passed In Partial') and i.status != 'D' and forwarded_status!='FWD_OUT' and (import_type='LGD' or import_type='Zone to Zone')", nativeQuery = true)
	public List<ImportSub> getalldatabyparty1(@Param("cid") String cid,@Param("bid") String bid,@Param("exporter") String exporter);
	
@Query(value = "select i.* from importsub i where (i.import_type='LGD' or i.import_type='Zone to Zone') and i.company_id=:cid and i.branch_id=:bid and i.exporter =:exporter order by sir_no desc", nativeQuery = true)
public List<ImportSub> getalltocheckLGD4(@Param("cid") String cid,@Param("bid") String bid,@Param("exporter") String exporter);


@Query(value = "select i.* from importsub i where (i.import_type='LGD' or i.import_type='Zone to Zone') and i.company_id=:cid and i.branch_id=:bid and i.handover_party_cha='C' and i.handover_party_name =:exporter order by sir_no desc", nativeQuery = true)
public List<ImportSub> getalltocheckLGD6(@Param("cid") String cid,@Param("bid") String bid,@Param("exporter") String exporter);

@Query(value = "select i.* from importsub i where !(i.import_type='LGD' or i.import_type='Zone to Zone') and i.company_id=:cid and i.branch_id=:bid and i.exporter =:exporter order by sir_no desc", nativeQuery = true)
public List<ImportSub> getalltocheckLGD3(@Param("cid") String cid,@Param("bid") String bid,@Param("exporter") String exporter);

@Query(value = "select i.* from importsub i where !(i.import_type='LGD' or i.import_type='Zone to Zone') and i.company_id=:cid and i.branch_id=:bid and i.handover_party_cha='C' and i.handover_party_name =:exporter order by sir_no desc", nativeQuery = true)
public List<ImportSub> getalltocheckLGD5(@Param("cid") String cid,@Param("bid") String bid,@Param("exporter") String exporter);

}
