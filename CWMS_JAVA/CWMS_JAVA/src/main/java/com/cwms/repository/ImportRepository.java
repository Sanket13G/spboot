package com.cwms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.cwms.entities.Import;

import jakarta.transaction.Transactional;

@EnableJpaRepositories
public interface ImportRepository extends JpaRepository<Import, String> {
	
	@Query(value="select * from import where company_id=:cid and branch_id=:bid",nativeQuery=true)
	public List<Import> findByAll(@Param("cid") String cid,@Param("bid") String bid);

	   @Query(value="select distinct i.tp_no from import i where i.tp_date=:date and i.company_id=:cid and i.branch_id=:bid" , nativeQuery=true)
	    public List<String> findByTp(@Param("date") Date date,@Param("cid") String cid,@Param("bid") String bid);
	
	
	public List<Import> findByTpDate(Date tpDate);
	
	
//	@Query(value = "select * from import i where i.company_id=:cid and i.branch_id=:bid and i.tp_date=:date and i.tp_no=:tpno", nativeQuery = true)
//	public List<Import> findByTpdateTpno(
//	    @Param("cid") String cid,
//	    @Param("bid") String bid,
//	    @Param("date") Date date,
//	    @Param("tpno") String tp_no
//	   // @Param("status") char status
//	    ); // Use "status" parameter here
	
	
	@Query(value = "SELECT * FROM import i WHERE i.company_id = :cid AND i.branch_id = :bid AND i.tp_date = :date AND i.tp_no = :tpno  ORDER BY sir_no ASC", nativeQuery = true)
	public List<Import> findByTpdateTpno(
	    @Param("cid") String cid,
	    @Param("bid") String bid,
	    @Param("date") Date date,
	    @Param("tpno") String tp_no
	);
	@Query(value = "SELECT airline_name FROM import WHERE company_id = ?1 AND branch_id = ?2 AND sir_date BETWEEN ?3 AND ?4 AND status = 'A'  GROUP BY airline_name", nativeQuery = true)
	   List<String> findAirlineNames(String companyId, String branchId, Date startDate, Date endDate);
	   
	   
	   @Query(value = "SELECT DISTINCT airline_name, mawb,  sir_no,sir_date, parcel_type, hawb, nop ,flight_date,flight_no FROM import  WHERE company_id =:companyId AND branch_id =:branchId  AND sir_date BETWEEN :startDate AND :endDate AND status = 'A' AND (pctm_no = '' OR pctm_no IS NULL) AND airline_name = :airlineName", nativeQuery = true)
	    List<Object[]> findImportData(@Param("companyId") String companyId, @Param("branchId") String branchId,@Param("startDate") Date startDate,@Param("endDate") Date endDate,
	    		@Param("airlineName") String airlineName);

	    
	    @Query(value = "SELECT DISTINCT * " +
	            "FROM import " +
	            "WHERE company_id = :companyId " +
	            "AND branch_id = :branchId " +
	            "AND sir_date BETWEEN :startDate AND :endDate " +
	            "AND status = 'A'" +
	            "AND  nipt_status ='N'" +
	            "AND (pctm_no = '' OR pctm_no IS NULL) " +
	            "AND airline_code = :flightNo", nativeQuery = true)
	     List<Import> findImportAllData(String companyId, String branchId, Date startDate, Date endDate,
	                                    String flightNo);
	    
	    @Query(value = "SELECT DISTINCT * " +
	            "FROM import " +
	            "WHERE company_id = :companyId " +
	            "AND branch_id = :branchId " + 
	            "AND cancel_status='N'" +
	            "AND sir_date BETWEEN :startDate AND :endDate " +
	            "AND console_name = :consoleName", nativeQuery = true)
	     List<Import> findIMMportAllData(String companyId, String branchId, Date startDate, Date endDate,
	                                    String consoleName);
	 
	   
	   
	   @Query(value = "SELECT DISTINCT * " +
	            "FROM import " +
	            "WHERE company_id = :companyId " +
	            "AND branch_id = :branchId " + 
	            "AND cancel_status='N'" +
	            "AND sir_date BETWEEN :startDate AND :endDate ",
	            nativeQuery = true)
	     List<Import> findImportData(String companyId, String branchId, Date startDate, Date endDate);


	   //shubham
	   @Query(value = "SELECT SUM(Nop) FROM Import WHERE Company_Id =:cid AND Branch_Id =:bid AND DGDC_Status =:string1 AND SIR_Date < :date1", nativeQuery = true)
			int findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDateNot(@Param("cid") String cid, @Param("bid") String bid, @Param("string1") String string1, @Param("date1") Date date1);

		    @Query(value = "SELECT COALESCE(SUM(Nop), 0) FROM Import WHERE Company_Id =:cid AND Branch_Id =:bid AND Nipt_Status =:string1 AND SIR_Date =:date1", nativeQuery = true)
		  		int findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDateNot1(@Param("cid") String cid, @Param("bid") String bid,@Param("string1") String string1, @Param("date1") Date date1);

		  
		    
		    @Query(value = "SELECT SUM(Nop) FROM Import WHERE Company_Id = :cid AND Branch_Id = :bid AND DGDC_Status = :string AND Nipt_Status = :string1 AND SIR_Date <  :date1", nativeQuery = true)
	  		int findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDateNot1(@Param("cid") String cid, @Param("bid") String bid, @Param("string") String string,@Param("string1") String string1, @Param("date1") Date date1);

	    @Query(value = "SELECT SUM(Nop) FROM Import WHERE Company_Id = :cid AND Branch_Id = :bid AND DGDC_Status = :string AND Nipt_Status = :string1 AND SIR_Date = :date1", nativeQuery = true)
	    Integer findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate3(@Param("cid") String cid, @Param("bid") String bid, @Param("string") String string,@Param("string1") String string1, @Param("date1") Date date1);


	    @Query(value = "SELECT * FROM Import WHERE Company_Id = :companyId AND Branch_Id = :branchId AND DGDC_Status = 'Handed over to Party/CHA' AND Out_Date =  :startDate AND  handed_over_party_id =:paryCHAId AND handed_over_representative_id=:representativeId AND (gate_pass_status IS NULL OR gate_pass_status != 'Y')", nativeQuery = true)
	    List<Import> findByCompanyAndBranchAndDate1(String companyId, String branchId, Date startDate, String paryCHAId, String representativeId);
	    
//	    @Query(value = "SELECT * FROM Import WHERE Company_Id = :companyId AND Branch_Id = :branchId AND DGDC_Status = 'Handed over to Party/CHA' AND SIR_Date =  :startDate AND  handed_over_party_id =:paryCHAId AND handed_over_representative_id=:representativeId ", nativeQuery = true)
//	    List<Import> findByCompanyAndBranchAndDate4(String companyId, String branchId, Date startDate, String paryCHAId, String representativeId);
	    
	    @Query(value = "SELECT * FROM Import WHERE Company_Id = :companyId AND Branch_Id = :branchId AND DGDC_Status = 'Handed over to Party/CHA' AND Out_Date =  :startDate AND  handed_over_party_id =:paryCHAId AND handed_over_representative_id=:representativeId ", nativeQuery = true)
	    List<Import> findByCompanyAndBranchAndDate4(String companyId, String branchId, Date startDate, String paryCHAId, String representativeId);  
}
