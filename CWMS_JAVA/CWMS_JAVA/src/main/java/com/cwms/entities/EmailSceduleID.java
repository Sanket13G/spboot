package com.cwms.entities;

import java.io.Serializable;
import java.util.Date;


public class EmailSceduleID implements Serializable {
	private String companyId;
	private String branchId;
	private String partyId;
	public String billNO;
	private String invoiceNO;
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
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getBillNO() {
		return billNO;
	}
	public void setBillNO(String billNO) {
		this.billNO = billNO;
	}
	public String getInvoiceNO() {
		return invoiceNO;
	}
	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}
	public EmailSceduleID(String companyId, String branchId, String partyId, String billNO, String invoiceNO) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.partyId = partyId;
		this.billNO = billNO;
		this.invoiceNO = invoiceNO;
	}
	public EmailSceduleID() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}