package com.cwms.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cwms.entities.InvoicePackages;

public interface InvoicePackagesRepositary extends JpaRepository<InvoicePackages, String>
{

	InvoicePackages findByCompanyIdAndBranchIdAndPartyIdAndInvoiceNO(String companyId,String branchId,String partyId,String invoiceno);
	@Query(value = "SELECT * FROM Invoice_Packages WHERE invoice_Date BETWEEN ?1 AND ?2 "
			+ "AND company_id = ?3 AND branch_id = ?4 ", nativeQuery = true)
	List<InvoicePackages> findInvoicesBetweenDatesAndConditionsAll(Date startDate, Date endDate, String cid, String bid);
	

	@Query(value = "SELECT * FROM Invoice_Packages WHERE invoice_Date BETWEEN ?1 AND ?2 "
			+ "AND company_id = ?3 AND branch_id = ?4 AND party_id = ?5 ", nativeQuery = true)
	List<InvoicePackages> findInvoicesBetweenDatesAndConditions(Date startDate, Date endDate, String cid, String bid,
			String pid);
}
