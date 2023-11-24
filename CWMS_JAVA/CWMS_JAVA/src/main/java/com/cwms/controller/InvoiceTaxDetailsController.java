package com.cwms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.InvoicePackages;
import com.cwms.entities.InvoiceTaxDetails;
import com.cwms.repository.InvoicePackagesRepositary;
import com.cwms.service.InvoiceTaxDetailsServiceIMPL;

@RestController
@RequestMapping("invoicetaxdetails")
@CrossOrigin("*")
public class InvoiceTaxDetailsController
{
	@Autowired
	private InvoiceTaxDetailsServiceIMPL invoiceTaxDetailsServiceIMPL;
	
	@Autowired
	private InvoicePackagesRepositary InvoicePackagesRepositary;
	
	@GetMapping("/{CompId}/{BranchId}/{partyId}/{invoiceno}/byinvoicenumber")
	public List<InvoiceTaxDetails> getInvoiceByInvoiceNo(@PathVariable("partyId") String partyId,
			@PathVariable("CompId") String CompId, @PathVariable("BranchId") String BranchId ,
			@PathVariable("invoiceno") String invoiceno) {

		return invoiceTaxDetailsServiceIMPL.findByInvoiceNo(CompId, BranchId, partyId, invoiceno);
	}
	
	@GetMapping("/{CompId}/{BranchId}/{partyId}/{invoiceno}/byinvoicePackagenumber")
	public InvoicePackages getInvoicePackageByInvoiceNo(@PathVariable("partyId") String partyId,
			@PathVariable("CompId") String CompId, @PathVariable("BranchId") String BranchId ,
			@PathVariable("invoiceno") String invoiceno) {

		return InvoicePackagesRepositary.findByCompanyIdAndBranchIdAndPartyIdAndInvoiceNO(CompId, BranchId, partyId, invoiceno);
	}

}
