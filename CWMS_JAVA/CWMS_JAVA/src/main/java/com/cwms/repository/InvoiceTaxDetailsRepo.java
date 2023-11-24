package com.cwms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cwms.entities.InvoiceTaxDetails;

public interface InvoiceTaxDetailsRepo extends JpaRepository<InvoiceTaxDetails, String>
{
	List<InvoiceTaxDetails> findByCompanyIdAndBranchIdAndPartyIdAndInvoiceNO(String companyId,String branchId,String partyId,String invoiceno);
}
