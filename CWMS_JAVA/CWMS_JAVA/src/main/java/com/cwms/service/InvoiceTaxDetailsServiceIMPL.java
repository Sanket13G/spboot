package com.cwms.service;

import java.util.Date;
import java.util.List;
import com.cwms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.DemuragePackagesHistory;
import com.cwms.entities.InvoiceTaxDetails;

@Service
public class InvoiceTaxDetailsServiceIMPL 
{
	@Autowired
	private InvoiceTaxDetailsRepo InvoiceTaxDetailsRepo;
	
	@Autowired
	private	DemuragePackagesHistoryRepositary demorepositary;
	
	
	public List<DemuragePackagesHistory> getbyDemuragesByPartyIdAndInvoiceNumber(String companyId,String branchId,String partyId,Date startDate,Date endDate,String invoiceno)
	{
		return demorepositary.findDemuragePackagesByCriteria(companyId, branchId, partyId, startDate, endDate, invoiceno);
	}
	
	
	public List<InvoiceTaxDetails> findByInvoiceNo(String companyId,String branchId,String partyId,String invoiceno)
	{
		return InvoiceTaxDetailsRepo.findByCompanyIdAndBranchIdAndPartyIdAndInvoiceNO(companyId, branchId, partyId, invoiceno);
	}
	
	public InvoiceTaxDetails saveInvoiceTaxDetails(InvoiceTaxDetails InvoiceTaxDetails)
	{
		return InvoiceTaxDetailsRepo.save(InvoiceTaxDetails);
	}
	
	public DemuragePackagesHistory saveDemuragePackage(DemuragePackagesHistory demorage)
	{
		return demorepositary.save(demorage);
	}
	
	public List<DemuragePackagesHistory> getbyDemuragesByPartyId(String companyId,String branchId,String partyId,String invoiceno)
	{
		return demorepositary.findByCompanyIdAndBranchIdAndPartyIdAndInviceNo(companyId, branchId, partyId, invoiceno);
	}
	
}
