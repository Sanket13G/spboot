package com.cwms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.InvoiceMain;

public interface InvoiceRepositary extends JpaRepository<InvoiceMain, String>
{

	
	
	@Query("SELECT im.invoiceNO FROM InvoiceMain im " +
	         "WHERE im.companyId = :companyId " +
	         "AND im.branchId = :branchId " +
	         "AND im.partyId = :partyId " +
	         "AND im.invoiceDate BETWEEN :startDate AND :endDate")
	 List<String> findInvoiceNumbersByCriteria(
	         @Param("companyId") String companyId,
	         @Param("branchId") String branchId,
	         @Param("partyId") String partyId,
	         @Param("startDate") Date startDate,
	         @Param("endDate") Date endDate
	 );
	
	
	
	InvoiceMain findByCompanyIdAndBranchIdAndPartyIdAndInvoiceNO(String compId,String branchId,String partyid,String invoiceNo);
	List<InvoiceMain> findByCompanyIdAndBranchIdAndPartyId(String compId,String branchId,String partyid); 
	

	@Query(value = "SELECT * FROM cfinvsrv WHERE invoice_Date BETWEEN ?1 AND ?2 "
			+ "AND company_id = ?3 AND branch_id = ?4 AND party_id = ?5 ", nativeQuery = true)
	List<InvoiceMain> findInvoicesMainBetweenDatesAndConditions(Date startDate, Date endDate, String cid, String bid,
			String pid);
	
	
	@Query(value ="SELECT  *  FROM cfinvsrv  " +
            "WHERE company_id = :companyId " +
            "AND branch_id = :branchId " +
            "AND invoice_date BETWEEN :startDate AND :endDate " +
            "AND party_id = :partyId "
            , nativeQuery = true)
           
    List<InvoiceMain>  findInvoiceData(String companyId, String branchId, Date startDate, Date endDate,
                                    String partyId );    
 
 
 @Query(value ="SELECT  *  FROM cfinvsrv  " +
            "WHERE company_id = :companyId " +
            "AND branch_id = :branchId " +
            "AND invoice_date BETWEEN :startDate AND :endDate ",
             nativeQuery = true)     
    List<InvoiceMain>  findAllInvoiceData(String companyId, String branchId, Date startDate, Date endDate );    
 
 
 
 
 @Query(value = "SELECT c.* " +
            "FROM cfinvsrv c " +
            "WHERE c.company_id = :companyId " +
            "  AND c.branch_id = :branchId " +
            "  AND c.invoice_date BETWEEN :startDate AND :endDate " +
            "  AND ( " +
            "    SELECT p.unit_type " +
            "    FROM party p " +
            "    WHERE p.party_Id = c.party_id " +
            "  ) = :unitType ", nativeQuery = true)
    List<InvoiceMain> findAllInvoiceDataOfPartyType(String companyId, String branchId, Date startDate, Date endDate, String unitType);

	
}
