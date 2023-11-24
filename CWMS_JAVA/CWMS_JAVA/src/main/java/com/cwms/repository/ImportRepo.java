package com.cwms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.Import;

public interface ImportRepo extends JpaRepository<Import, String>
{
 
	 @Query(value = "SELECT airline_code FROM import WHERE company_id =:cid AND branch_id =:bid AND sir_date BETWEEN :startDate AND :endDate AND status = 'A' AND  nipt_status ='N' AND pc_status='N' AND (pctm_no = '' OR pctm_no IS NULL) GROUP BY airline_code", nativeQuery = true)
	   List<String> findAirlineNames(@Param("cid") String companyId,@Param("bid") String branchId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	   Import findByCompanyIdAndBranchIdAndMawbAndHawbAndIgmNo(String CompId, String branchId, String mawb, String Hawb, String IgmNo);
	   
	   @Query(value="select * from import where company_id=:cid and branch_id=:bid and sir_no=:sir",nativeQuery=true)
	     Import  Singledata(@Param("cid") String companyId,@Param("bid") String branchId,@Param("sir") String sir);
		    
	   @Query(value="select * from import where company_id=:cid and branch_id=:bid and mawb=:mawb and hawb=:hawb",nativeQuery=true)
	     Import  SingleImportdata(@Param("cid") String companyId,@Param("bid") String branchId,@Param("mawb") String mawb,@Param("hawb") String hawb);


	   @Query(value = "SELECT DISTINCT airline_name, mawb,  sir_no,sir_date, parcel_type, hawb, nop FROM import  WHERE company_id =:companyId AND branch_id =:branchId  AND sir_date BETWEEN :startDate AND :endDate AND status = 'A' AND (pctm_no = '' OR pctm_no IS NULL) AND airline_name = :airlineName", nativeQuery = true)
	   List<Object[]> findImportData(@Param("companyId") String companyId, @Param("branchId") String branchId,@Param("startDate") Date startDate,@Param("endDate") Date endDate,
	   		@Param("airlineName") String airlineName);
	   
	   @Query(value = "SELECT DISTINCT * " +
	            "FROM import " +
	            "WHERE company_id = :companyId " +
	            "AND branch_id = :branchId " +
	            "AND sir_date BETWEEN :startDate AND :endDate " +
	            "AND status = 'A'" +
	            "AND (pctm_no = '' OR pctm_no IS NULL) " +
	            "AND flight_no = :flightNo", nativeQuery = true)
	     List<Import> findImportAllData(String companyId, String branchId, Date startDate, Date endDate,
	                                    String flightNo);
		
	    
	   
	 

	    @Modifying
	    @Query(value = "UPDATE import e " +
	            "SET e.pctmNo = :newPCTMNo, e.tpNo = :newTPNo " +
	            "WHERE e.companyId = :companyId " +
	            "AND e.branchId = :branchId " +
	            "AND e.sirNo = :sirNo " +
	            "AND e.mawb = :mawb " +
	            "AND (pctm_no = '' OR pctm_no IS NULL)", nativeQuery = true)
	    String updatePCTMAndTPNo(
	            @Param("newPCTMNo") String pctmNo,
	            @Param("newTPNo") String tpNo,
	            @Param("companyId") String companyId,
	            @Param("branchId") String branchId,
	            @Param("sirNo") String sirNo,
	            @Param("mawb") String mawb
	    );

	    
	    @Query(value = "SELECT DISTINCT airline_code " +
	            "FROM Import " +
	            "WHERE company_id = :companyId " +
	            "AND branch_id = :branchId " +
	            "AND sir_date BETWEEN :startDate AND :endDate " +
	            "AND pctm_No IS NOT NULL " +
	            "AND pctm_No != ' ' " +
	            "AND airline_code IS NOT NULL " +
	            "AND airline_code != ' ' " +  // Adding condition for not empty string
	            "GROUP BY airline_code", nativeQuery = true)
	    List<String> findAllAirlineNames(String companyId, String branchId, Date startDate, Date endDate);
	    
	    
	    @Query(value = "SELECT DISTINCT pctm_no FROM import WHERE company_id =:companyId AND branch_id =:branchId AND  sir_date BETWEEN :startDate AND :endDate AND airline_code=:airline_code", nativeQuery = true)
		List<String> findDistinctPctmNos(@Param("companyId") String companyId,
		                              @Param("branchId") String branchId,
		                              @Param("startDate") Date startDate,
		                              @Param("endDate") Date endDate,
		                              @Param("airline_code") String flightNo);
	    
	    @Query(value = "SELECT DISTINCT * FROM Import " +
	               "WHERE company_id = :companyId " +
	               "AND branch_id = :branchId " +
	               "AND sir_date BETWEEN :startDate AND :endDate " +
	               "AND airline_code = :airline_code " +
	               "AND pctm_No = :pctmNo " +
	               "ORDER BY sir_no ASC", nativeQuery = true)
	List<Import> findImportPCTMData(String companyId, String branchId, Date startDate, Date endDate,
	                                String airline_code, String pctmNo);

		Import findByCompanyIdAndBranchIdAndImpTransIdAndMawbAndHawbAndSirNo(
		        String companyId, String branchId, String impTransId, String mawb, String hawb, String sirNo
		    );
		 
		List<Import> findByCompanyIdAndBranchIdAndMawbAndStatusNotOrderBySirNoDesc(String companyId, String branchId, String mawb, String status);

		

		 List<Import> findByCompanyIdAndBranchIdAndStatusNot(String companyId, String branchId,String status);
		 
		 Import findByCompanyIdAndBranchIdAndImpTransIdAndSirNo(String companyId, String branchId, String impTransId,String SirNo);
		 
		  List<Import> findByCompanyIdAndBranchIdAndDgdcStatusAndStatusNotAndCloseStatusAndPcStatusAndHoldStatusNot(String companyId
				  				, String branchId, String dgdcStatus,String Status,String CloseStatus,String PcStatus,String HoldStatus);
		  List<Import> findByCompanyIdAndBranchIdAndCartingAgentAndPartyRepresentativeIdAndDgdcStatusAndStatusNotAndCloseStatusAndPcStatusAndHoldStatusNot(String companyId
					, String branchId,String carting,String representative, String dgdcStatus,String Status,String CloseStatus,String PcStatus,String HoldStatus);

		  
		  @Query("SELECT i FROM Import i " +
			        "LEFT JOIN Party p ON i.importerId = p.partyId " +  // Join import table with party table
			        "WHERE " +
			        "(:pcStatus IS NULL OR :pcStatus = '' OR i.pcStatus = :pcStatus) " +
			        "AND (:scStatus IS NULL OR :scStatus = '' OR i.scStatus = :scStatus) " +
			        "AND (:hpStatus IS NULL OR :hpStatus = '' OR i.hpStatus = :hpStatus) " +
			        "AND (:holdStatus IS NULL OR :holdStatus = '' OR i.holdStatus = :holdStatus) " +
			        "AND (:dgdcStatus IS NULL OR :dgdcStatus = '' OR i.dgdcStatus = :dgdcStatus) " +
			        "AND ((:startDate IS NULL AND :endDate IS NULL) OR " +
			        "(:startDate IS NULL AND :endDate >= i.sirDate) OR " +
			        "(:startDate <= i.sirDate AND :endDate IS NULL) OR " +
			        "(:startDate <= i.sirDate AND :endDate >= i.sirDate)) " +
			        "AND i.companyId = :companyId " +
			        "AND i.branchId = :branchId " +
			        "AND i.status != 'D' " +
			        "AND ((:searchValue IS NULL OR :searchValue = '') OR " +
			        "(i.sirNo = :searchValue OR i.hawb = :searchValue OR i.mawb = :searchValue OR i.importerId IN " +
			        "(SELECT party.partyId FROM Party party WHERE party.partyName = :searchValue))) " + 
			        "ORDER BY i.sirNo DESC")
			List<Import> findByAttributes(
			    @Param("companyId") String companyId,
			    @Param("branchId") String branchId,
			    @Param("pcStatus") String pcStatus,
			    @Param("scStatus") String scStatus,
			    @Param("hpStatus") String hpStatus,
			    @Param("holdStatus") String holdStatus,
			    @Param("dgdcStatus") String dgdcStatus,
			    @Param("startDate") Date startDate,
			    @Param("endDate") Date endDate,
			    @Param("searchValue") String searchValue);



		  public Import findByCompanyIdAndBranchIdAndMawbAndBeRequestIdAndStatusNot(String companyId, String branchId, String mawb,String requestId,String Status); 
		  
		  List<Import> findByCompanyIdAndBranchIdAndImporterId(
			        String companyId, String branchId, String importerId);
		  
		  
		  
		  
		  
		  @Query(nativeQuery = true, value =
				    "SELECT " +
				    "    partyId, " +
				    "    date, " +
				    "    SUM(nop) AS nop, " +
				    "    MAX(importScStatus) AS importScStatus, " +
				    "    MAX(importPcStatus) AS importPcStatus, " +
				    "    MAX(importHpStatus) AS importHpStatus, " +
				    "    MAX(importHpWeight) AS importHpWeight, " +
				    "    SUM(exportNoOfPackages) AS exportNoOfPackages, " +
				    "    MAX(exportScStatus) AS exportScStatus, " +
				    "    MAX(exportPcStatus) AS exportPcStatus, " +
				    "    MAX(exportHpStatus) AS exportHpStatus, " +
				    "    MAX(exportHpWeight) AS exportHpWeight, " +
				    "    SUM(importPenalty) AS importPenalty, " +
				    "    SUM(exportPenalty) AS exportPenalty, " +
				    "    SUM(importSubNop) AS importSubNop, " +
				    "    SUM(importSubPenalty) AS importSubPenalty, " +
				    "    SUM(exportSubNop) AS exportSubNop, " +
				    "    SUM(exportSubPenalty) AS exportSubPenalty, " +
				    "    MAX(importNiptStatus) AS importNiptStatus ," +
				    "    MAX(importOutDate) AS importOutDate, " +   // Add this line to get the export outDate
				    "    MAX(exportOutDate) AS exportOutDate, " +   // Add this line to get the export outDate
				    "    MAX(importSubOutDate) AS importSubOutDate, " +   // Add this line to get the importSub outDate
				    "    MAX(exportSubOutDate) AS exportSubOutDate " +   // Add this line to get the exportSub outDate
				    "FROM (" +
				    "    SELECT " +
				    "        Importer_Id AS partyId, " +
				    "        SIR_Date AS date, " +
				    "        Nop AS nop, " +
				    "        SC_Status AS importScStatus, " +
				    "        Pc_Status AS importPcStatus, " +
				    "        HP_Status AS importHpStatus, " +
				    "        HP_Weight AS importHpWeight, " +
				    "        0 AS exportNoOfPackages, " +
				    "        0 AS exportScStatus, " +
				    "        0 AS exportPcStatus, " +
				    "        0 AS exportHpStatus, " +
				    "        0 AS exportHpWeight, " +
				    "        Impose_Penalty_Amt AS importPenalty, " +
				    "        0 AS exportPenalty, " +
				    "        0 AS importSubNop, " +
				    "        0 AS importSubPenalty, " +
				    "        0 AS exportSubNop, " +
				    "        0 AS exportSubPenalty ," +
				    "        Nipt_Status As importNiptStatus ," +
				    "        Out_Date As importOutDate ," +
				    "        NULL As exportOutDate ," +
				    "        NULL As importSubOutDate ," +
				    "        NULL As exportSubOutDate" +
				    "    FROM Import " +
				    "    WHERE " +
				    "        Company_Id = :companyId " +
				    "        AND Branch_Id = :branchId " +
				    "        AND Importer_Id = :partyId " +
				    "        AND Status != 'D' " +
				    "        AND COALESCE(SIR_Date) BETWEEN :startDate AND :endDate " +
				    "    UNION ALL " +
				    "    SELECT " +
				    "        Exporter AS partyId, " +
				    "        SIR_Date AS date, " +
				    "        0 AS nop, " +
				    "        NULL AS importScStatus, " +
				    "        NULL AS importPcStatus, " +
				    "        NULL AS importHpStatus, " +
				    "        NULL AS importHpWeight, " +
				    "        0 AS exportNoOfPackages, " +
				    "        0 AS exportScStatus, " +
				    "        0 AS exportPcStatus, " +
				    "        0 AS exportHpStatus, " +
				    "        0 AS exportHpWeight, " +
				    "        0 AS importPenalty, " +
				    "        0 AS exportPenalty, " +
				    "        Nop AS importSubNop, " +
				    "        Impose_Penalty_Amt AS importSubPenalty, " +
				    "        0 AS exportSubNop, " +
				    "        0 AS exportSubPenalty , " +
				    "        NULL AS importNiptStatus," +
				    "        NULL As importOutDate ," +
				    "        NULL As exportOutDate ," +
				    "        Out_Date As importSubOutDate ," +
				    "        NULL As exportSubOutDate" +
				    "    FROM importSub " +
				    "    WHERE " +
				    "        Company_Id = :companyId " +
				    "        AND Branch_Id = :branchId " +
				    "        AND Exporter = :partyId " +
				    "        AND Status != 'D' " +
				    "        AND SIR_Date BETWEEN :startDate AND :endDate " +
				    "    UNION ALL " +
				    "    SELECT " +
				    "        Exporter_Name AS partyId, " +
				    "        SER_Date AS date, " +
				    "        0 AS nop, " +
				    "        0 AS importScStatus, " +
				    "        0 AS importPcStatus, " +
				    "        0 AS importHpStatus, " +
				    "        0 AS importHpWeight, " +
				    "        No_Of_Packages AS exportNoOfPackages, " +
				    "        SC_Status AS exportScStatus, " +
				    "        Pc_Status AS exportPcStatus, " +
				    "        HP_Status AS exportHpStatus, " +
				    "        HP_Weight AS exportHpWeight, " +
				    "        0 AS importPenalty, " +
				    "        Impose_Penalty_Amt AS exportPenalty, " +
				    "        0 AS importSubNop, " +
				    "        0 AS importSubPenalty, " +
				    "        0 AS exportSubNop, " +
				    "        0 AS exportSubPenalty ," +
				    "        0 AS importNiptStatus ," +
				    "        NULL As importOutDate ," +
				    "        Out_Date As exportOutDate ," +
				    "        NULL As importSubOutDate ," +
				    "        NULL As exportSubOutDate" +
				    "    FROM Export " +
				    "    WHERE " +
				    "        Company_Id = :companyId " +
				    "        AND Branch_Id = :branchId " +
				    "        AND Exporter_Name = :partyId " +
				    "        AND Status != 'D' " +
				    "        AND COALESCE(SER_Date) BETWEEN :startDate AND :endDate " +
				    "    UNION ALL " +
				    "    SELECT " +
				    "        Exporter AS partyId, " +
				    "        SER_Date AS date, " +
				    "        0 AS nop, " +
				    "        0 AS importScStatus, " +
				    "        0 AS importPcStatus, " +
				    "        0 AS importHpStatus, " +
				    "        0 AS importHpWeight, " +
				    "        0 AS exportNoOfPackages, " +
				    "        0 AS exportScStatus, " +
				    "        0 AS exportPcStatus, " +
				    "        0 AS exportHpStatus, " +
				    "        0 AS exportHpWeight, " +
				    "        0 AS importPenalty, " +
				    "        0 AS exportPenalty, " +
				    "        0 AS importSubNop, " +
				    "        0 AS importSubPenalty, " +
				    "        Nop AS exportSubNop, " +
				    "        Impose_Penalty_Amt AS exportSubPenalty ," +
				    "        0 AS importNiptStatus ," +
				    "        NULL As importOutDate ," +
				    "        NULL As exportOutDate ," +
				    "        NULL As importSubOutDate ," +
				    "        Out_Date As exportSubOutDate" +
				    "    FROM exportSub " +
				    "    WHERE " +
				    "        Company_Id = :companyId " +
				    "        AND Branch_Id = :branchId " +
				    "        AND Exporter = :partyId " +
				    "        AND Status != 'D' " +
				    "        AND SER_Date BETWEEN :startDate AND :endDate " +
				    ") AS combinedData " +
				    "GROUP BY partyId, date " +
				    "ORDER BY date ASC")
				List<Object[]> getCombinedImportExportData(
				    @Param("companyId") String companyId,
				    @Param("branchId") String branchId,
				    @Param("partyId") String partyId,
				    @Param("startDate") Date startDate,
				    @Param("endDate") Date endDate
				);




				Import findByCompanyIdAndBranchIdAndSirDateAndImporterIdAndNopAndPcStatusAndScStatusAndHpStatus(String companyId, String branchId, Date sirDate,String importerId , int ImpoNop, String pcStatus, String scStatus,String hpStatus);
	    
	    



		  
		
		  
		  

		    @Query(value = "SELECT * FROM import WHERE DATE_FORMAT(sir_date, '%Y-%m-%d') =:sirDate AND company_id =:compnayId AND branch_id =:branchId AND dgdc_status =:dgdcStatus", nativeQuery = true)
			   
			 List<Import> findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(@Param("sirDate") String sirDate, @Param("compnayId") String compnayId, @Param("branchId") String branchId, @Param("dgdcStatus") String dgdcStatus);
	    
		    
		    
		    @Query(value = "SELECT DISTINCT console_name FROM import WHERE company_id = :companyId AND branch_id = :branchId AND DATE(do_date) = DATE(:doDate)", nativeQuery = true)
			List<String> findByCompanyAndBranchAndDoDate(@Param("companyId") String companyId, @Param("branchId") String branchId, @Param("doDate") String doDate);

			@Query(value = "SELECT  * FROM import WHERE company_id = :companyId AND branch_id = :branchId AND DATE(do_date) = DATE(:doDate) AND console_name = :exId", nativeQuery = true)
			List<Import> findByCompanyAndBranchAndDoDateAndexternalPId(@Param("companyId") String companyId,
					@Param("branchId") String branchId, @Param("doDate") String doDate, @Param("exId") String exId

			);

//				  select distinct do_number from import where company_id="C00001" and branch_id="B00001" and mawb="MAWB0001";
			@Query(value = "SELECT DISTINCT do_number FROM import WHERE company_id = :companyId AND branch_id = :branchId AND mawb = :mawb LIMIT 1", nativeQuery = true)
			String findByCompanyAndBranchAndMawb(@Param("companyId") String companyId, @Param("branchId") String branchId,
					@Param("mawb") String mawb

			);

			@Query(value = "SELECT  * FROM import WHERE company_id = :companyId AND branch_id = :branchId AND mawb = :mawb", nativeQuery = true)
			List<Import> findByCompanyAndBranchAndMawbList(@Param("companyId") String companyId,
					@Param("branchId") String branchId, @Param("mawb") String mawb

			);

			@Query(value = "SELECT * FROM import WHERE company_id = :companyId AND branch_id = :branchId AND (do_number IS NULL OR do_number = '')", nativeQuery = true)
			List<Import> findByCompanyAndBranchNUllDo(@Param("companyId") String companyId, @Param("branchId") String branchId);

			@Query(value = "select distinct mawb FROM import WHERE company_id = :companyId AND branch_id = :branchId", nativeQuery = true)
			List<String> findMawbByCompanyAndBranch(@Param("companyId") String companyId, @Param("branchId") String branchId);
			
			
//			
//		    @Query(nativeQuery = true, value = "SELECT\n" +
//		            "    combined.Date AS Combined_Date,\n" +
//		            "    SUM(combined.Import_total_nop_party_cha) AS Import_total_nop_party_cha,\n" +
//		            "    SUM(combined.Import_total_nop_seepz) AS Import_total_nop_seepz,\n" +
//		            "    SUM(combined.Export_total_nop_party_cha) AS Export_total_nop_party_cha,\n" +
//		            "    SUM(combined.Export_total_nop_seepz) AS Export_total_nop_seepz,\n" +
//		            "    SUM(combined.SubImport_total_nop_party_cha) AS SubImport_total_nop_party_cha,\n" +
//		            "    SUM(combined.SubImport_total_nop_seepz) AS SubImport_total_nop_seepz,\n" +
//		            "    SUM(combined.SubExport_total_nop_party_cha) AS SubExport_total_nop_party_cha,\n" +
//		            "    SUM(combined.SubExport_total_nop_seepz) AS SubExport_total_nop_seepz,\n" +
//		            "    SUM(combined.DetentionExport_out) AS ExportDetention_out,\n" +
//		            "    SUM(combined.DetentionExport_in) AS ExportDetention_in,\n" +
//		            "    SUM(combined.DetentionImport_out) AS ImportDetention_out,\n" +
//		            "    SUM(combined.DetentionImport_in) AS ImportDetention_in\n" +
//		            "FROM (\n" +
//		            "    SELECT\n" +
//		            "        SIR_Date AS Date,\n" +
//		            "        'Import' AS source,\n" +
//		            "        SUM(CASE WHEN DGDC_Status = 'Handed Over to Party/CHA' AND Status <> 'D' THEN nop ELSE 0 END) AS Import_total_nop_party_cha,\n" +
//		            "        SUM(CASE WHEN Status <> 'D' THEN nop ELSE 0 END) AS Import_total_nop_seepz,\n" +
//		            "        0 AS Export_total_nop_party_cha,\n" +
//		            "        0 AS Export_total_nop_seepz,\n" +
//		            "        0 AS SubImport_total_nop_party_cha,\n" +
//		            "        0 AS SubImport_total_nop_seepz,\n" +
//		            "        0 AS SubExport_total_nop_party_cha,\n" +
//		            "        0 AS SubExport_total_nop_seepz,\n" +
//		            "        0 AS DetentionExport_out,\n" +
//		            "        0 AS DetentionExport_in,\n" +
//		            "        0 AS DetentionImport_out,\n" +
//		            "        0 AS DetentionImport_in\n" +
//		            "    FROM import\n" +
//		            "	WHERE company_id = :company_id AND branch_id = :branch_id " +
//		            "    GROUP BY Date\n" +
//		            "    UNION ALL\n" +
//		            "    SELECT\n" +
//		            "        SER_Date AS Date,\n" +
//		            "        'Export' AS source,\n" +
//		            "        0 AS Import_total_nop_party_cha,\n" +
//		            "        0 AS Import_total_nop_seepz,\n" +
//		            "        SUM(CASE WHEN DGDC_Status = 'Handed Over to Party/CHA' AND Status <> 'D' THEN No_Of_Packages ELSE 0 END) AS Export_total_nop_party_cha,\n" +
//		            "        SUM(CASE WHEN Status <> 'D' THEN No_Of_Packages ELSE 0 END) AS Export_total_nop_seepz,\n" +
//		            "        0 AS SubImport_total_nop_party_cha,\n" +
//		            "        0 AS SubImport_total_nop_seepz,\n" +
//		            "        0 AS SubExport_total_nop_party_cha,\n" +
//		            "        0 AS SubExport_total_nop_seepz,\n" +
//		            "        0 AS DetentionExport_out,\n" +
//		            "        0 AS DetentionExport_in,\n" +
//		            "        0 AS DetentionImport_out,\n" +
//		            "        0 AS DetentionImport_in\n" +
//		            "    FROM export\n" +
//		            "	WHERE company_id = :company_id AND branch_id = :branch_id " +
//		            "    GROUP BY Date\n" +
//		            "    UNION ALL\n" +
//		            "    SELECT\n" +
//		            "        SIR_Date AS Date,\n" +
//		            "        'importsub' AS source,\n" +
//		            "        0 AS Import_total_nop_party_cha,\n" +
//		            "        0 AS Import_total_nop_seepz,\n" +
//		            "        0 AS Export_total_nop_party_cha,\n" +
//		            "        0 AS Export_total_nop_seepz,\n" +
//		            "        SUM(CASE WHEN DGDC_Status = 'Handed Over to Party/CHA' AND Status <> 'D' THEN nop ELSE 0 END) AS SubImport_total_nop_party_cha,\n" +
//		            "        SUM(CASE WHEN Status <> 'D' THEN nop ELSE 0 END) AS SubImport_total_nop_seepz,\n" +
//		            "        0 AS SubExport_total_nop_party_cha,\n" +
//		            "        0 AS SubExport_total_nop_seepz,\n" +
//		            "        0 AS DetentionExport_out,\n" +
//		            "        0 AS DetentionExport_in,\n" +
//		            "        0 AS DetentionImport_out,\n" +
//		            "        0 AS DetentionImport_in\n" +
//		            "    FROM importsub\n" +
//		            "	WHERE company_id = :company_id AND branch_id = :branch_id " +
//		            "    GROUP BY Date\n" +
//		            "    UNION ALL\n" +
//		            "    SELECT\n" +
//		            "        SER_Date AS Date,\n" +
//		            "        'exportsub' AS source,\n" +
//		            "        0 AS Import_total_nop_party_cha,\n" +
//		            "        0 AS Import_total_nop_seepz,\n" +
//		            "        0 AS Export_total_nop_party_cha,\n" +
//		            "        0 AS Export_total_nop_seepz,\n" +
//		            "        0 AS SubImport_total_nop_party_cha,\n" +
//		            "        0 AS Import_total_nop_seepz,\n" +
//		            "        SUM(CASE WHEN DGDC_Status = 'Handed Over to Party/CHA' AND Status <> 'D' THEN nop ELSE 0 END) AS SubExport_total_nop_party_cha,\n" +
//		            "        SUM(CASE WHEN Status <> 'D' THEN nop ELSE 0 END) AS SubExport_total_nop_seepz,\n" +
//		            "        0 AS DetentionExport_out,\n" +
//		            "        0 AS DetentionExport_in,\n" +
//		            "        0 AS DetentionImport_out,\n" +
//		            "        0 AS DetentionImport_in\n" +
//		            "    FROM exportsub\n" +
//		            "	WHERE company_id = :company_id AND branch_id = :branch_id " +
//		            "    GROUP BY Date\n" +
//		            "    UNION ALL\n" +
//		            "    SELECT\n" +
//		            "        deposit_date AS Date,\n" +
//		            "        'detention' AS source,\n" +
//		            "        0 AS Import_total_nop_party_cha,\n" +
//		            "        0 AS Import_total_nop_seepz,\n" +
//		            "        0 AS Export_total_nop_party_cha,\n" +
//		            "        0 AS Export_total_nop_seepz,\n" +
//		            "        0 AS SubImport_total_nop_party_cha,\n" +
//		            "        0 AS SubImport_total_nop_seepz,\n" +
//		            "        0 AS SubExport_total_nop_party_cha,\n" +
//		            "        0 AS SubExport_total_nop_seepz,\n" +
//		            "        0 AS DetentionExport_out,\n" +
//		            "        SUM(CASE WHEN parcel_Type = 'Export' THEN nop ELSE 0 END) AS DetentionExport_in,\n" +
//		            "        0 AS DetentionImport_out,\n" +
//		            "        SUM(CASE WHEN parcel_Type = 'Import' THEN nop ELSE 0 END) AS DetentionImport_in\n" +
//		            "    FROM detention\n" +
//		            "	WHERE company_id = :company_id AND branch_id = :branch_id " +
//		            "    GROUP BY Date\n" +
//		            "    UNION ALL\n" +
//		            "    SELECT\n" +
//		            "        withdraw_date AS Date,\n" +
//		            "        'detention' AS source,\n" +
//		            "        0 AS Import_total_nop_party_cha,\n" +
//		            "        0 AS Import_total_nop_seepz,\n" +
//		            "        0 AS Export_total_nop_party_cha,\n" +
//		            "        0 AS Export_total_nop_seepz,\n" +
//		            "        0 AS SubImport_total_nop_party_cha,\n" +
//		            "        0 AS SubImport_total_nop_seepz,\n" +
//		            "        0 AS SubExport_total_nop_party_cha,\n" +
//		            "        0 AS SubExport_total_nop_seepz,\n" +
//		            "        SUM(CASE WHEN parcel_Type = 'Export' THEN withdraw_nop ELSE 0 END) AS DetentionExport_out,\n" +
//		            "        0 AS DetentionExport_in,\n" +
//		            "        SUM(CASE WHEN parcel_Type = 'Import' THEN withdraw_nop ELSE 0 END) AS DetentionImport_out,\n" +
//		            "        0 AS DetentionImport_in\n" +
//		            "    FROM detention\n" +
//		            "	WHERE company_id = :company_id AND branch_id = :branch_id " +
//		            "    GROUP BY Date\n" +
//		            ") AS combined\n" +
//		            "WHERE combined.Date IS NOT NULL\n" +
//		            "GROUP BY combined.Date\n" +
//		            "ORDER BY combined.Date;")
//		    List<Object[]> getCombinedStockData(@Param("company_id") String companyId, @Param("branch_id") String branchId);
			
			
		    
			@Query(nativeQuery = true, value = "SELECT\n" +
		            "    combined.Date AS Combined_Date,\n" +
		            "    SUM(combined.Import_total_nop_party_cha) AS Import_total_nop_party_cha,\n" +
		            "    SUM(combined.Import_total_nop_seepz) AS Import_total_nop_seepz,\n" +
		            "    SUM(combined.Export_total_nop_party_cha) AS Export_total_nop_party_cha,\n" +
		            "    SUM(combined.Export_total_nop_seepz) AS Export_total_nop_seepz,\n" +
		            "    SUM(combined.SubImport_total_nop_party_cha) AS SubImport_total_nop_party_cha,\n" +
		            "    SUM(combined.SubImport_total_nop_seepz) AS SubImport_total_nop_seepz,\n" +
		            "    SUM(combined.SubExport_total_nop_party_cha) AS SubExport_total_nop_party_cha,\n" +
		            "    SUM(combined.SubExport_total_nop_seepz) AS SubExport_total_nop_seepz,\n" +
		            "    SUM(combined.NiptImport_out) AS NiptImport_out,\n" +
		            "    SUM(combined.NiptImport_in) AS NiptImport_in\n" +
		            "FROM (\n" +
		            "    SELECT\n" +
		            "        SIR_Date AS Date,\n" +
		            "        'Import' AS source,\n" +
		            "        SUM(CASE WHEN DGDC_Status = 'Exit from DGDC SEEPZ Gate' AND Status <> 'D' AND Nipt_Status = 'N' THEN nop ELSE 0 END) AS Import_total_nop_party_cha,\n" +
		            "        SUM(CASE WHEN Status <> 'D' AND Nipt_Status = 'N' THEN nop ELSE 0 END) AS Import_total_nop_seepz,\n" +
		            "        0 AS Export_total_nop_party_cha,\n" +
		            "        0 AS Export_total_nop_seepz,\n" +
		            "        0 AS SubImport_total_nop_party_cha,\n" +
		            "        0 AS SubImport_total_nop_seepz,\n" +
		            "        0 AS SubExport_total_nop_party_cha,\n" +
		            "        0 AS SubExport_total_nop_seepz,\n" +
		            "        SUM(CASE WHEN DGDC_Status = 'Exit from DGDC SEEPZ Gate' AND Status <> 'D' AND Nipt_Status = 'Y' THEN nop ELSE 0 END) AS NiptImport_out,\n" +
		            "        SUM(CASE WHEN Status <> 'D' AND Nipt_Status = 'Y' THEN nop ELSE 0 END) AS NiptImport_in\n" +
		            "    FROM import\n" +
		            "	WHERE company_id = :company_id AND branch_id = :branch_id " +
		            "    GROUP BY Date\n" +
		            "    UNION ALL\n" +
		            "    SELECT\n" +
		            "        SER_Date AS Date,\n" +
		            "        'Export' AS source,\n" +
		            "        0 AS Import_total_nop_party_cha,\n" +
		            "        0 AS Import_total_nop_seepz,\n" +
		            "        SUM(CASE WHEN DGDC_Status = 'Handed Over to Airline' AND Status <> 'D' THEN No_Of_Packages ELSE 0 END) AS Export_total_nop_party_cha,\n" +
		            "        SUM(CASE WHEN Status <> 'D' THEN No_Of_Packages ELSE 0 END) AS Export_total_nop_seepz,\n" +
		            "        0 AS SubImport_total_nop_party_cha,\n" +
		            "        0 AS SubImport_total_nop_seepz,\n" +
		            "        0 AS SubExport_total_nop_party_cha,\n" +
		            "        0 AS SubExport_total_nop_seepz,\n" +
		            "        0 AS NiptImport_out,\n" +
		            "        0 AS NiptImport_in\n" +
		            "    FROM export\n" +
		            "	WHERE company_id = :company_id AND branch_id = :branch_id " +
		            "    GROUP BY Date\n" +
		            "    UNION ALL\n" +
		            "    SELECT\n" +
		            "        SIR_Date AS Date,\n" +
		            "        'importsub' AS source,\n" +
		            "        0 AS Import_total_nop_party_cha,\n" +
		            "        0 AS Import_total_nop_seepz,\n" +
		            "        0 AS Export_total_nop_party_cha,\n" +
		            "        0 AS Export_total_nop_seepz,\n" +
		            "        SUM(CASE WHEN DGDC_Status = 'Exit from DGDC SEEPZ Gate' AND Status <> 'D' THEN nop ELSE 0 END) AS SubImport_total_nop_party_cha,\n" +
		            "        SUM(CASE WHEN Status <> 'D' THEN nop ELSE 0 END) AS SubImport_total_nop_seepz,\n" +
		            "        0 AS SubExport_total_nop_party_cha,\n" +
		            "        0 AS SubExport_total_nop_seepz,\n" +
		            "        0 AS NiptImport_out,\n" +
		            "        0 AS NiptImport_in\n" +
		            "    FROM importsub\n" +
		            "	WHERE company_id = :company_id AND branch_id = :branch_id " +
		            "    GROUP BY Date\n" +
		            "    UNION ALL\n" +
		            "    SELECT\n" +
		            "        SER_Date AS Date,\n" +
		            "        'exportsub' AS source,\n" +
		            "        0 AS Import_total_nop_party_cha,\n" +
		            "        0 AS Import_total_nop_seepz,\n" +
		            "        0 AS Export_total_nop_party_cha,\n" +
		            "        0 AS Export_total_nop_seepz,\n" +
		            "        0 AS SubImport_total_nop_party_cha,\n" +
		            "        0 AS Import_total_nop_seepz,\n" +
		            "        SUM(CASE WHEN DGDC_Status = 'Exit from DGDC SEEPZ Gate' AND Status <> 'D' THEN nop ELSE 0 END) AS SubExport_total_nop_party_cha,\n" +
		            "        SUM(CASE WHEN Status <> 'D' THEN nop ELSE 0 END) AS SubExport_total_nop_seepz,\n" +
		            "        0 AS NiptImport_out,\n" +
		            "        0 AS NiptImport_in\n" +
		            "    FROM exportsub\n" +
		            "	WHERE company_id = :company_id AND branch_id = :branch_id " +
		            "    GROUP BY Date\n" +
		            
		            ") AS combined\n" +
		            "WHERE combined.Date IS NOT NULL\n" +
		            "GROUP BY combined.Date\n" +
		            "ORDER BY combined.Date DESC;")
		    List<Object[]> getCombinedStockData(@Param("company_id") String companyId, @Param("branch_id") String branchId);
			
			
		    
		    @Query(nativeQuery = true, value = "SELECT\n" +
		            "    combined.Date AS Combined_Date,\n" +
		            "    SUM(combined.DetentionExport_out) AS ExportDetention_out,\n" +
		            "    SUM(combined.DetentionExport_in) AS ExportDetention_in,\n" +
		            "    SUM(combined.DetentionImport_out) AS ImportDetention_out,\n" +
		            "    SUM(combined.DetentionImport_in) AS ImportDetention_in\n" +
		            "FROM (\n" +
		            "    SELECT\n" +
		            "        deposit_date AS Date,\n" +
		            "        0 AS DetentionExport_out,\n" +
		            "        SUM(CASE WHEN parcel_Type = 'Export' THEN nop ELSE 0 END) AS DetentionExport_in,\n" +
		            "        0 AS DetentionImport_out,\n" +
		            "        SUM(CASE WHEN parcel_Type = 'Import' THEN nop ELSE 0 END) AS DetentionImport_in\n" +
		            "    FROM detention\n" +
		            "	WHERE company_id = :company_id AND branch_id = :branch_id " +
		            "    GROUP BY Date\n" +
		            "    UNION ALL\n" +
		            "    SELECT\n" +
		            "        withdraw_date AS Date,\n" +
		            "        SUM(CASE WHEN parcel_Type = 'Export' THEN withdraw_nop ELSE 0 END) AS DetentionExport_out,\n" +
		            "        0 AS DetentionExport_in,\n" +
		            "        SUM(CASE WHEN parcel_Type = 'Import' THEN withdraw_nop ELSE 0 END) AS DetentionImport_out,\n" +
		            "        0 AS DetentionImport_in\n" +
		            "    FROM detention\n" +
		            "	WHERE company_id = :company_id AND branch_id = :branch_id " +
		            "    GROUP BY Date\n" +
		            ") AS combined\n" +
		            "WHERE combined.Date IS NOT NULL\n" +
		            "GROUP BY combined.Date\n" +
		            "ORDER BY combined.Date DESC;")
		    List<Object[]> getCombinedStockDataDetention(@Param("company_id") String companyId, @Param("branch_id") String branchId);
		    
		    
		    
		    
		    Import findFirstByCompanyIdAndBranchIdAndSirDateAndNiptStatusAndStatusNot(String companyId,String branchId,Date sirDate, String niptStatus,String Status);
		    
		    List<Import> findByCompanyIdAndBranchIdAndImporterIdAndSirDateBetweenAndStatusNot(String companyId, String branchId,String partyId, Date startDate,Date endDate , String Status);
		    List<Import> findByCompanyIdAndBranchIdAndImporterIdAndSirDateAndStatusNot(String companyId, String branchId,String partyId, Date sirDate, String Status);
		    List<Import> findByCompanyIdAndBranchIdAndImporterIdAndHoldStatusNotAndForwardedStatusNot(
			        String companyId, String branchId, String importerId,String holdStatus,String forwardedStatus);	
		    
//			   Import findByCompanyIdAndBranchIdAndMawbAndHawbAndStatusNot(String CompId, String branchId, String mawb, String Hawb);
		    List<Import> findByCompanyIdAndBranchIdAndImporterIdAndHoldStatusNot(String companyId, String branchId,String partyId,String hold);
		    
		    boolean existsByCompanyIdAndBranchIdAndDetentionReceiptNo(String companyId, String branchId, String detentionNumber);
			   public Import findByCompanyIdAndBranchIdAndDetentionReceiptNo(String companyId, String branchId,String detentionReceiptNo);


			   @Query(value="select distinct tp_no from import where company_id=:cid and branch_id=:bid and tp_date=:tdate order by tp_no desc",nativeQuery=true)
			    List<String> getalltp(@Param("cid") String cid, @Param("bid") String bid ,@Param("tdate") Date date);
			   
			   boolean existsByCompanyIdAndBranchIdAndMawbAndHawb(String companyId, String branchId, String mawb, String hawb);
			   
			   
			   
			   @Query("SELECT i FROM Import i " +
				        "LEFT JOIN Party p ON i.importerId = p.partyId " +  // Join import table with party table
				        "WHERE " +
				        "(:pcStatus IS NULL OR :pcStatus = '' OR i.pcStatus = :pcStatus) " +
				        "AND (:scStatus IS NULL OR :scStatus = '' OR i.scStatus = :scStatus) " +
				        "AND (:hpStatus IS NULL OR :hpStatus = '' OR i.hpStatus = :hpStatus) " +
				        "AND (:holdStatus IS NULL OR :holdStatus = '' OR i.holdStatus = :holdStatus) " +
				        "AND (:dgdcStatus IS NULL OR :dgdcStatus = '' OR i.dgdcStatus = :dgdcStatus) " +
				        "AND ((:startDate IS NULL AND :endDate IS NULL) OR " +
				        "(:startDate IS NULL AND :endDate >= i.sirDate) OR " +
				        "(:startDate <= i.sirDate AND :endDate IS NULL) OR " +
				        "(:startDate <= i.sirDate AND :endDate >= i.sirDate)) " +
				        "AND i.companyId = :companyId " +
				        "AND i.branchId = :branchId " +
				        "AND i.importerId = :importerId " +
				        "AND i.status != 'D' " +
				        "AND ((:searchValue IS NULL OR :searchValue = '') OR " +
				        "(i.sirNo = :searchValue OR i.hawb = :searchValue OR i.mawb = :searchValue OR i.importerId IN " +
				        "(SELECT party.partyId FROM Party party WHERE party.partyName = :searchValue))) " + 
				        "ORDER BY i.sirNo DESC")
				List<Import> findByAttributes1(
				    @Param("companyId") String companyId,
				    @Param("branchId") String branchId,
				    @Param("pcStatus") String pcStatus,
				    @Param("scStatus") String scStatus,
				    @Param("hpStatus") String hpStatus,
				    @Param("holdStatus") String holdStatus,
				    @Param("dgdcStatus") String dgdcStatus,
				    @Param("startDate") Date startDate,
				    @Param("endDate") Date endDate,
				    @Param("searchValue") String searchValue,
				    @Param("importerId") String importerId);
			  
			  
			  
			  @Query("SELECT i FROM Import i " +
				        "LEFT JOIN Party p ON i.importerId = p.partyId " +  // Join import table with party table
				        "WHERE " +
				        "(:pcStatus IS NULL OR :pcStatus = '' OR i.pcStatus = :pcStatus) " +
				        "AND (:scStatus IS NULL OR :scStatus = '' OR i.scStatus = :scStatus) " +
				        "AND (:hpStatus IS NULL OR :hpStatus = '' OR i.hpStatus = :hpStatus) " +
				        "AND (:holdStatus IS NULL OR :holdStatus = '' OR i.holdStatus = :holdStatus) " +
				        "AND (:dgdcStatus IS NULL OR :dgdcStatus = '' OR i.dgdcStatus = :dgdcStatus) " +
				        "AND ((:startDate IS NULL AND :endDate IS NULL) OR " +
				        "(:startDate IS NULL AND :endDate >= i.sirDate) OR " +
				        "(:startDate <= i.sirDate AND :endDate IS NULL) OR " +
				        "(:startDate <= i.sirDate AND :endDate >= i.sirDate)) " +
				        "AND i.companyId = :companyId " +
				        "AND i.branchId = :branchId " +
				        "AND i.cartingAgent = :cartingAgent " +
				        "AND i.status != 'D' " +
				        "AND ((:searchValue IS NULL OR :searchValue = '') OR " +
				        "(i.sirNo = :searchValue OR i.hawb = :searchValue OR i.mawb = :searchValue OR i.importerId IN " +
				        "(SELECT party.partyId FROM Party party WHERE party.partyName = :searchValue))) " + 
				        "ORDER BY i.sirNo DESC")
				List<Import> findByAttributes2(
				    @Param("companyId") String companyId,
				    @Param("branchId") String branchId,
				    @Param("pcStatus") String pcStatus,
				    @Param("scStatus") String scStatus,
				    @Param("hpStatus") String hpStatus,
				    @Param("holdStatus") String holdStatus,
				    @Param("dgdcStatus") String dgdcStatus,
				    @Param("startDate") Date startDate,
				    @Param("endDate") Date endDate,
				    @Param("searchValue") String searchValue,
				    @Param("cartingAgent") String cartingAgent);
			  
			  
			  
			  
			  @Query("SELECT i FROM Import i " +
				        "LEFT JOIN Party p ON i.importerId = p.partyId " +  // Join import table with party table
				        "WHERE " +
				        "(:pcStatus IS NULL OR :pcStatus = '' OR i.pcStatus = :pcStatus) " +
				        "AND (:scStatus IS NULL OR :scStatus = '' OR i.scStatus = :scStatus) " +
				        "AND (:hpStatus IS NULL OR :hpStatus = '' OR i.hpStatus = :hpStatus) " +
				        "AND (:holdStatus IS NULL OR :holdStatus = '' OR i.holdStatus = :holdStatus) " +
				        "AND (:dgdcStatus IS NULL OR :dgdcStatus = '' OR i.dgdcStatus = :dgdcStatus) " +
				        "AND ((:startDate IS NULL AND :endDate IS NULL) OR " +
				        "(:startDate IS NULL AND :endDate >= i.sirDate) OR " +
				        "(:startDate <= i.sirDate AND :endDate IS NULL) OR " +
				        "(:startDate <= i.sirDate AND :endDate >= i.sirDate)) " +
				        "AND i.companyId = :companyId " +
				        "AND i.branchId = :branchId " +
				        "AND i.handedOverPartyId = :handedOverPartyId " +
				        "AND i.handedOverToType = 'C' " +
				        "AND i.status != 'D' " +
				        "AND ((:searchValue IS NULL OR :searchValue = '') OR " +
				        "(i.sirNo = :searchValue OR i.hawb = :searchValue OR i.mawb = :searchValue OR i.importerId IN " +
				        "(SELECT party.partyId FROM Party party WHERE party.partyName = :searchValue))) " + 
				        "ORDER BY i.sirNo DESC")
				List<Import> findByAttributes3(
				    @Param("companyId") String companyId,
				    @Param("branchId") String branchId,
				    @Param("pcStatus") String pcStatus,
				    @Param("scStatus") String scStatus,
				    @Param("hpStatus") String hpStatus,
				    @Param("holdStatus") String holdStatus,
				    @Param("dgdcStatus") String dgdcStatus,
				    @Param("startDate") Date startDate,
				    @Param("endDate") Date endDate,
				    @Param("searchValue") String searchValue,
				    @Param("handedOverPartyId") String handedOverPartyId);
			  

			  @Query("SELECT i FROM Import i " +
				        "LEFT JOIN Party p ON i.importerId = p.partyId " +  // Join import table with party table
				        "WHERE " +
				        "(:pcStatus IS NULL OR :pcStatus = '' OR i.pcStatus = :pcStatus) " +
				        "AND (:scStatus IS NULL OR :scStatus = '' OR i.scStatus = :scStatus) " +
				        "AND (:hpStatus IS NULL OR :hpStatus = '' OR i.hpStatus = :hpStatus) " +
				        "AND (:holdStatus IS NULL OR :holdStatus = '' OR i.holdStatus = :holdStatus) " +
				        "AND (:dgdcStatus IS NULL OR :dgdcStatus = '' OR i.dgdcStatus = :dgdcStatus) " +
				        "AND ((:startDate IS NULL AND :endDate IS NULL) OR " +
				        "(:startDate IS NULL AND :endDate >= i.sirDate) OR " +
				        "(:startDate <= i.sirDate AND :endDate IS NULL) OR " +
				        "(:startDate <= i.sirDate AND :endDate >= i.sirDate)) " +
				        "AND i.companyId = :companyId " +
				        "AND i.branchId = :branchId " +
				        "AND i.consoleName = :consoleName " +
				        "AND i.status != 'D' " +
				        "AND ((:searchValue IS NULL OR :searchValue = '') OR " +
				        "(i.sirNo = :searchValue OR i.hawb = :searchValue OR i.mawb = :searchValue OR i.importerId IN " +
				        "(SELECT party.partyId FROM Party party WHERE party.partyName = :searchValue))) " + 
				        "ORDER BY i.sirNo DESC")
				List<Import> findByAttributes4(
				    @Param("companyId") String companyId,
				    @Param("branchId") String branchId,
				    @Param("pcStatus") String pcStatus,
				    @Param("scStatus") String scStatus,
				    @Param("hpStatus") String hpStatus,
				    @Param("holdStatus") String holdStatus,
				    @Param("dgdcStatus") String dgdcStatus,
				    @Param("startDate") Date startDate,
				    @Param("endDate") Date endDate,
				    @Param("searchValue") String searchValue,
				    @Param("consoleName") String consoleName);
}