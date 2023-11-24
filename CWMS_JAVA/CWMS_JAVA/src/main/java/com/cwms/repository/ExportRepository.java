package com.cwms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.cwms.entities.Export;

import jakarta.transaction.Transactional;

public interface ExportRepository extends JpaRepository<Export, String> {

	@Query(value = "SELECT * FROM Export WHERE status != 'd'", nativeQuery = true)
	List<Export> findAllNotDeleted();
	
	@Query(value="select * from export where company_id=:cid and branch_id=:bid and status != 'D'",nativeQuery=true)
	List<Export> findAllData(@Param("cid") String cid, @Param("bid") String bid);
	
	
	@Query(value="select * from export where company_id=:cid and branch_id=:bid and ser_no=:ser",nativeQuery=true)
	Export finexportdata(@Param("cid") String cid, @Param("bid") String bid,@Param("ser") String ser);
	

	@Query(value="select * from export where company_id=:cid and branch_id=:bid and status != 'D'",nativeQuery=true)
	List<Export> findAllData1(@Param("cid") String cid, @Param("bid") String bid);

	@Query(value = "SELECT * FROM export WHERE status != 'D' AND dgdc_status = 'Handed over to DGDC SEEPZ' AND company_id = ?1 AND branch_id = ?2", nativeQuery = true)
    List<Export> findExportsByCompanyAndBranch(String companyId, String branchId);

	@Query(value = "SELECT * FROM Export WHERE company_id=:cid and branch_id=:bid and ser_no=:ser", nativeQuery = true)
	Export findBySer(@Param("cid") String cid, @Param("bid") String bid, @Param("ser") String ser);
	
	@Query(value = "SELECT * FROM export WHERE DATE_FORMAT(created_date, '%Y-%m-%d') =:date AND company_id =:cid AND branch_id =:bid AND dgdc_status =:dgdcstatus", nativeQuery = true)
	   
	 List<Export> findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(@Param("date") String date, @Param("cid") String cid, @Param("bid") String bid, @Param("dgdcstatus") String dgdcstatus);

	@Query(value = "SELECT DISTINCT carting_agent FROM export WHERE company_id = :companyId AND branch_id = :branchId AND DATE(ser_date) = DATE(:ser_date)", nativeQuery = true)
	List<String> findByCompanyAndBranchAndSerDate(@Param("companyId") String companyId, @Param("branchId") String branchId, @Param("ser_date") String ser_date);

	@Query(value = "SELECT  * FROM export WHERE company_id = :companyId AND branch_id = :branchId AND DATE(ser_date) = DATE(:ser_date) AND carting_agent = :exId", nativeQuery = true)
	List<Export> findByCompanyAndBranchAndserDateAndexternalPId(@Param("companyId") String companyId,
			@Param("branchId") String branchId, @Param("ser_date") String ser_date, @Param("exId") String exId

	);
	
	@Query(value="select * from export where company_id=:cid and branch_id=:bid and sb_request_id=:sbid and sb_number=:sbno",nativeQuery=true)
	public Export findBySBNoandSbreqid(@Param("cid") String cid, @Param("bid") String bid,@Param("sbid") String sbid, @Param("sbno") String sbno);
	
	@Query(value="select * from export where company_id=:cid and branch_id=:bid and sb_request_id=:sbid and sb_number=:sbno",nativeQuery=true)
	public List<Export> findAllBySBNoandSbreqid(@Param("cid") String cid, @Param("bid") String bid,@Param("sbid") String sbid, @Param("sbno") String sbno);
	
	@Transactional
	@Modifying
	
	@Query(value="update export set nsdl_status=:nsdl,reason_for_override=:reason,override_document=:over where company_id=:cid and branch_id=:bid and sb_request_id=:sbid and sb_number =:sbno",nativeQuery=true)
	public void updateOverride(@Param("nsdl") String nsdl,@Param("reason") String reason, @Param("over") String over,@Param("cid") String cid, @Param("bid") String bid,@Param("sbid") String sbid, @Param("sbno") String sbno);

	@Transactional
	@Modifying
	@Query(value="update export set dgdc_status='Handed over to Carting Agent',carting_agent=:carting,representative_id=:representid where company_id=:cid and branch_id=:bid and sb_request_id=:sbid and sb_number =:sbno",nativeQuery=true)
    public void updateCartingRecord(@Param("carting") String carting,@Param("representid") String representid,@Param("cid") String cid, @Param("bid") String bid,@Param("sbid") String sbid, @Param("sbno") String sbno);


	@Query(value="SELECT * FROM export where company_id=:cid and branch_id=:bid and dgdc_status='Entry at DGDC Cargo GATE' and cancel_status='N' and carting_agent=:cartagent and representative_id=:representid and hold_status='N'",nativeQuery=true)
    public List<Export> getalldataforreceivecarting(@Param("cid") String cid, @Param("bid") String bid,@Param("cartagent") String cartagent, @Param("representid") String representid);

    @Query(value="select * from export where company_id=:cid and branch_id=:bid and airline_code=:air and dgdc_status='Handed over to DGDC Cargo' and hold_status='N'",nativeQuery=true)
    public List<Export> getdataByairline(@Param("cid") String cid, @Param("bid") String bid,@Param("air") String air);

    @Query(value="select * from export where company_id=:cid and branch_id=:bid and dgdc_status='Handed over to DGDC SEEPZ' and (nsdl_status='Allow Export' OR nsdl_status='Let Export') and cancel_status='N' and hold_status='N' ",nativeQuery=true)
    public List<Export> getalldataforhandover(@Param("cid") String cid, @Param("bid") String bid);




	@Query(value ="SELECT DISTINCT *  FROM Export " +
            "WHERE company_id = :companyId " +
            "AND branch_id = :branchId " +
            "AND ser_date BETWEEN :startDate AND :endDate " +
            "AND  tp_no  IS NULL " +
            "AND pctm_no  IS NULL ", nativeQuery = true)
     List<Export> findAllExportData( @Param("companyId") String companyId,
    	        @Param("branchId") String branchId,
    	        @Param("startDate") Date startDate,
    	        @Param("endDate") Date endDate );
 
                                  
   @Query(value = "UPDATE Export e " +
            "SET e.pctmNo = :newPCTMNo, e.tpNo = :newTPNo " +
            "WHERE e.companyId = :companyId " +
            "AND e.branchId = :branchId " +
            "AND e.tpNo IS NULL"+
            "AND e.pctmNo IS NULL", nativeQuery = true)
    String updatePCTMAndTPNo(
            @Param("newPCTMNo") String pctmNo,
            @Param("newTPNo") String tpNo,
            @Param("companyId") String companyId,
            @Param("branchId") String branchId  
    );
   
   @Query(value = "SELECT DISTINCT carting_agent " +
            "FROM Export " +
            "WHERE company_id = :companyId " +
            "AND branch_id = :branchId " +
            " AND DATE_FORMAT(ser_date, '%Y-%m-%d') =:serDate " +
            "AND pctm_No IS NOT NULL " +
            "AND tp_No IS NOT NULL " +
            "GROUP BY carting_agent", nativeQuery = true)
    List<String> findAllCartingAgentNames(@Param("companyId")String companyId,
    		@Param("branchId") String branchId, 
    		@Param("serDate")String serDate);
   
   
   
    @Query(value = "SELECT DISTINCT tp_No FROM Export WHERE company_id =:companyId AND branch_id =:branchId AND  DATE_FORMAT(ser_date, '%Y-%m-%d') =:serDate AND carting_agent=:cartingAgent", nativeQuery = true)
List<String> findDistinctTpNos(@Param("companyId") String companyId,
                              @Param("branchId") String branchId,
                             
                              @Param("serDate") String endDate,
                              @Param("cartingAgent") String cartingAgent);
    
    
    
    
    @Query(value ="SELECT DISTINCT *  FROM Export  " +
            "WHERE company_id = :companyId " +
            "AND branch_id = :branchId " +
            "AND DATE_FORMAT(ser_date, '%Y-%m-%d') =:serDate " +
            "AND carting_agent = :cartingAgent " +
            "AND tp_No = :tpNo", nativeQuery = true)
     List<Export> findImportTPData(@Param("companyId") String companyId,
    		 @Param("branchId") String branchId,
    		  @Param("serDate") String endDate,
    		  @Param("cartingAgent") String cartingAgent,
    		  @Param ("tpNo")String tpNo);
    
 

    @Query(value = "select * from Export i where i.company_id=:cid and i.branch_id=:bid and i.tp_date=:date and i.tp_no=:tpno", nativeQuery = true)
	public List<Export> findByTpdateTpno(
	    @Param("cid") String cid,
	    @Param("bid") String bid,
	    @Param("date") Date date,
	    @Param("tpno") String tp_no
	   // @Param("status") char status
	    ); 
	

	   @Query(value="select distinct i.tp_no from Export i where i.tp_date=:date and i.company_id=:cid and i.branch_id=:bid" , nativeQuery=true)
	    public List<String> findByTp(@Param("date") Date date,@Param("cid") String cid,@Param("bid") String bid);

	   @Query(value="select * from Export where company_id=:companyId and branch_id=:branchId",nativeQuery=true)
		public List<Export> findByAll(@Param("companyId") String companyId,@Param("branchId") String branchId);
	   
	   Export findByCompanyIdAndBranchIdAndSerDateAndNameOfExporterAndNoOfPackagesAndPcStatusAndScStatusAndHpStatus(String companyId, String branchId, Date sirDate,String importerId , int ImpoNop, String pcStatus, String scStatus,String hpStatus);

	   @Query(value = "SELECT DISTINCT * FROM Export WHERE company_id = :companyId AND branch_id = :branchId AND cancel_status = 'N' AND ser_date BETWEEN :startDate AND :endDate AND console_Agent = :cartingAgent", nativeQuery = true)
	   List<Export> findExportAllData(@Param("companyId") String companyId, @Param("branchId") String branchId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("cartingAgent") String cartingAgent);

	 
	   
	   
	   @Query(value = "SELECT DISTINCT * " +
	            "FROM Export " +
	            "WHERE company_id = :companyId " +
	            "AND branch_id = :branchId " + 
	            "AND cancel_status='N'" +
	            "AND ser_date BETWEEN :startDate AND :endDate ",
	            nativeQuery = true)
	     List<Export> findExportData(String companyId, String branchId, Date startDate, Date endDate
	                                    );
	 
	   @Query(value="select * from Export b where b.company_id=:cid AND b.branch_id = :bid",nativeQuery=true)
		public Export findByCartingAgnetWithCartingAgent(@Param("cid") String cid,@Param("bid") String bid);
	   
	   
	   @Query(value="select distinct i.ser_date from Export i where i.ser_date=:date and i.company_id=:cid and i.branch_id=:bid" , nativeQuery=true)
	    public List<String> findBySerDate(@Param("date") Date date,@Param("cid") String cid,@Param("bid") String bid);
	   
	   
	   @Query(value = "SELECT * FROM Export e WHERE e.company_id = :cid AND e.branch_id = :bid AND e.ser_date = :date AND e.airline_code = :airlineCode AND e.dgdc_status = 'Handed Over to Airline' and hold_status='N'", nativeQuery = true)
	   public List<Export> findBySerDateAndAirlineCode(
	       @Param("cid") String cid,
	       @Param("bid") String bid,
	       @Param("date") Date date,
	       @Param("airlineCode") String airlineCode
	   );
	   
	   @Query(value = "SELECT * FROM Export  WHERE Company_Id = :cid AND Branch_Id = :bid AND SER_No = :serNo AND PC_Status = :pcStatus AND (DGDC_Status = :status1 OR DGDC_Status = :status2) AND Gate_Pass_Status != :status3", nativeQuery = true)
		public List<Export> findByCompanyIdAndBranchIdAndSerNoAndPcStatusAndDgdcStatusAndGatePassStatusNot(
		        @Param("cid") String cid,
		        @Param("bid") String bid,
		        @Param("serNo") String serNo,
		        @Param("pcStatus") String pcStatus,
		        @Param("status1") String status1,
		        @Param("status2") String status2,
		        @Param("status3") String status3
		);

		Export findByCompanyIdAndBranchIdAndSerNo(String companyId, String branchId, String serNo);
		
		//shubham
		   
		   @Query(value = "SELECT SUM(no_of_packages) FROM Export WHERE Company_Id = :cid AND Branch_Id = :bid AND DGDC_Status = :string AND SER_Date <  :date1", nativeQuery = true)
			int findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDateNot(@Param("cid") String cid, @Param("bid") String bid, @Param("string") String string, @Param("date1") Date date1);

//			@Query(value = "SELECT SUM(no_of_packages) FROM Export WHERE Company_Id = :cid AND Branch_Id = :bid AND DGDC_Status = :string AND SER_Date =  :date1", nativeQuery = true)
//			int findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate1(@Param("cid") String cid, @Param("bid") String bid, @Param("string") String string, @Param("date1") Date date1);

			 @Query(value = "SELECT SUM(no_of_packages) FROM Export WHERE Company_Id = :cid AND Branch_Id = :bid AND SER_Date = :date1", nativeQuery = true)
			    Integer findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate1(@Param("cid") String cid, @Param("bid") String bid, @Param("date1") Date date1);
			
			 
			 @Query(value = "SELECT SUM(no_of_packages) FROM Export WHERE Company_Id = :cid AND Branch_Id = :bid AND DGDC_Status = :string AND SER_Date = :date1", nativeQuery = true)
			    Integer findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate3(@Param("cid") String cid, @Param("bid") String bid, @Param("string") String string, @Param("date1") Date date1);

			 
			 
//			 @Query(value = "SELECT SUM(no_of_packages) FROM Export WHERE Company_Id = :cid AND Branch_Id = :bid AND YEAR(SER_Date) = YEAR(CURRENT_DATE()) GROUP BY YEAR(SER_Date), MONTH(SER_Date)", nativeQuery = true)
//			 List<Object[]> findSumByCompanyIdAndBranchIdMonthWise(@Param("cid") String cid, @Param("bid") String bid);

//			 @Query(value = "SELECT MONTH(Export_date) AS month, COALESCE(SUM(no_of_packages), 0) AS totalPackages FROM Export WHERE Company_Id = :cid AND Branch_Id = :bid AND YEAR(Export_date) = YEAR(CURRENT_DATE()) GROUP BY MONTH(Export_date)", nativeQuery = true)
//			 List<Object[]> findSumByCompanyIdAndBranchIdMonthWise(@Param("cid") String cid, @Param("bid") String bid);
		 
			 @Query(value = "SELECT " +
			            "SUM(I.nop) AS ImportTotalNop, " +
			            "SUM(E.No_Of_Packages) AS ExportTotalNopPackages, " +
			            "DATE_FORMAT(I.SIR_Date, '%Y-%m') AS MonthYear " +
			            "FROM import I " +
			            "LEFT JOIN export E ON DATE_FORMAT(I.SIR_Date, '%Y-%m') = DATE_FORMAT(E.SER_Date, '%Y-%m') " +
			            "WHERE I.Status <> 'D' AND E.Status <> 'D' " +
			            "AND I.company_Id = :companyId AND I.branch_Id = :branchId " +
			            "AND E.company_Id = :companyId AND E.branch_Id = :branchId " +
			            "GROUP BY DATE_FORMAT(I.SIR_Date, '%Y-%m')", nativeQuery = true)
			    List<Object[]> getImportExportDataByCompanyAndBranch(@Param("companyId") String companyId, @Param("branchId") String branchId);
			 
			    
			    @Query(value = "SELECT " +
			            "SUM(c.total_invoice_amt) AS TotalAmount, " +
			            "DATE_FORMAT(c.invoice_date, '%Y-%m') AS MonthYear " +
			            "FROM cfinvsrv c " +
			            "WHERE c.company_id = :companyId AND c.branch_id = :branchId " +
			            "GROUP BY DATE_FORMAT(c.invoice_date, '%Y-%m')", nativeQuery = true)
			    List<Object[]> getTotalInvoiceAmountByMonth(@Param("companyId") String companyId, @Param("branchId") String branchId);
			  
			   
			    
			    
			    
			    
			    List<Export> findByCompanyIdAndBranchIdAndNameOfExporterAndSerDateAndStatusNot(String companyId, String branchId,String partyId, Date serDate , String Status);
			    
			    
	@Query(value="select distinct tp_no from export where company_id=:cid and branch_id=:bid and and tp_no != 'NULL' and tp_date=:tpdate",nativeQuery=true)
      public List<String> findtpbytpdata(@Param("cid") String cid, @Param("bid") String bid,@Param("tpdate") @DateTimeFormat(pattern="yyyy-MM-dd") Date tpdate);
	   
	
	  @Query(value="select distinct tp_no from export where company_id=:cid and branch_id=:bid and tp_date=:tdate order by tp_no desc",nativeQuery=true)
	    List<String> getalltp(@Param("cid") String cid, @Param("bid") String bid ,@Param("tdate") Date date);
	  List<Export> findByCompanyIdAndBranchIdAndSerNoAndPcStatus(String cid, String bid, String serNo, String string);
	    
	  @Query(value="select * from export where company_id=:cid and branch_id=:bid and exporter_name=:exporter and status != 'D'",nativeQuery=true)
		List<Export> findAllDataforparty(@Param("cid") String cid, @Param("bid") String bid, @Param("exporter") String exporter);
		
		@Query(value="select * from export where company_id=:cid and branch_id=:bid and carting_agent=:carting and status != 'D'",nativeQuery=true)
		List<Export> findAllDataforcartingagent(@Param("cid") String cid, @Param("bid") String bid, @Param("carting") String carting);
		
		
		@Query(value="select * from export where company_id=:cid and branch_id=:bid and chano=:carting and status != 'D'",nativeQuery=true)
		List<Export> findAllDataforcha(@Param("cid") String cid, @Param("bid") String bid, @Param("carting") String carting);
		
		
		@Query(value="select * from export where company_id=:cid and branch_id=:bid and console_agent=:carting and status != 'D'",nativeQuery=true)
		List<Export> findAllDataforconsole(@Param("cid") String cid, @Param("bid") String bid, @Param("carting") String carting);
}

