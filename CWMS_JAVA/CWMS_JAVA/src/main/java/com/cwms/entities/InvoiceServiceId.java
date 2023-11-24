package com.cwms.entities;
import java.io.Serializable;

public class InvoiceServiceId implements Serializable
{
	private String companyId;
	private String branchId;
	private String invoiceNO;
	private String partyId;
	public InvoiceServiceId() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	public String getPartyId() {
		return partyId;
	}



	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}



	public InvoiceServiceId(String companyId, String branchId, String invoiceNO, String partyId) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.invoiceNO = invoiceNO;
		this.partyId = partyId;
	}



	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getInvoiceNO() {
		return invoiceNO;
	}
	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}
	
	

}
