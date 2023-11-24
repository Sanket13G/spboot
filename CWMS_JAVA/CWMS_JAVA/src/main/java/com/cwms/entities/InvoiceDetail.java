package com.cwms.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cfinvsrvdtl")
@IdClass(InvoiceServiceDetailId.class)
public class InvoiceDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SrlNo")
	private Long SrlNo;

	@Id
	@Column(name = "Company_Id", length = 6, nullable = false)
	private String companyId;

	@Id
	@Column(name = "Branch_Id", length = 6, nullable = false)
	private String branchId;

	@Id
	@Column(name = "Invoice_No", length = 6, nullable = false)
	private String invoiceNO;

	@Id
	@Column(name = "Party_Id", length = 6, nullable = false)
	private String partyId;

	@Id
	@Column(name = "Service_Id", length = 6, nullable = false)
	private String serviceId;

	@Column(name = "Invoice_Date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date invoiceDate;

	@Column(name = "Execution_Unit", nullable = false, length = 13)
	private String executionUnit;

	@Column(name = "service_unit1", length = 40)
	private String serviceUnit1;

	@Column(name = "Execution_Unit1", length = 40)
	private String executionUnit1;

	@Column(name = "Rate")
	private double rate;

	@Column(name = "Currency_Id", nullable = false, length = 6)
	private String currencyId;

	@Column(name = "Tax_Perc", scale = 2)
	private double taxPercentage;

	@Column(name = "Tax_Amt", scale = 2)
	private double taxAmount;

	@Column(name = "Bill_Amt", scale = 2)
	private double billAmount;

	@Column(name = "Total_Invoice_Amt", scale = 2)
	private double totalInvoiceAmount;

	@Column(name = "Status", nullable = false, length = 1)
	private String status;

	@Column(name = "Created_By", nullable = false, length = 30)
	private String createdBy;

	@Column(name = "Created_Date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "Edited_By", length = 30)
	private String editedBy;

	@Column(name = "Edited_Date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date editedDate;

	@Column(name = "Approved_By", length = 30)
	private String approvedBy;

	@Column(name = "Approved_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	public InvoiceDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getSrlNo() {
		return SrlNo;
	}

	public void setSrlNo(Long srlNo) {
		SrlNo = srlNo;
	}

	public InvoiceDetail(String companyId, String branchId, String invoiceNO, String partyId, String serviceId,
			Date invoiceDate, String executionUnit, String serviceUnit1, String executionUnit1, double rate,
			String currencyId, double taxPercentage, double taxAmount, double billAmount, double totalInvoiceAmount,
			String status, String createdBy, Date createdDate, String editedBy, Date editedDate, String approvedBy,
			Date approvedDate) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.invoiceNO = invoiceNO;
		this.partyId = partyId;
		this.serviceId = serviceId;
		this.invoiceDate = invoiceDate;
		this.executionUnit = executionUnit;
		this.serviceUnit1 = serviceUnit1;
		this.executionUnit1 = executionUnit1;
		this.rate = rate;
		this.currencyId = currencyId;
		this.taxPercentage = taxPercentage;
		this.taxAmount = taxAmount;
		this.billAmount = billAmount;
		this.totalInvoiceAmount = totalInvoiceAmount;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
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

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getExecutionUnit() {
		return executionUnit;
	}

	public void setExecutionUnit(String executionUnit) {
		this.executionUnit = executionUnit;
	}

	public String getServiceUnit1() {
		return serviceUnit1;
	}

	public void setServiceUnit1(String serviceUnit1) {
		this.serviceUnit1 = serviceUnit1;
	}

	public String getExecutionUnit1() {
		return executionUnit1;
	}

	public void setExecutionUnit1(String executionUnit1) {
		this.executionUnit1 = executionUnit1;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public double getTotalInvoiceAmount() {
		return totalInvoiceAmount;
	}

	public void setTotalInvoiceAmount(double totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEditedBy() {
		return editedBy;
	}

	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}

	public Date getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(Date editedDate) {
		this.editedDate = editedDate;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	@Override
	public String toString() {
		return "InvoiceDetail [companyId=" + companyId + ", branchId=" + branchId + ", InvoiceNO=" + invoiceNO
				+ ", partyId=" + partyId + ", serviceId=" + serviceId + ", invoiceDate=" + invoiceDate
				+ ", executionUnit=" + executionUnit + ", serviceUnit1=" + serviceUnit1 + ", executionUnit1="
				+ executionUnit1 + ", rate=" + rate + ", currencyId=" + currencyId + ", taxPercentage=" + taxPercentage
				+ ", taxAmount=" + taxAmount + ", billAmount=" + billAmount + ", totalInvoiceAmount="
				+ totalInvoiceAmount + ", status=" + status + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", editedBy=" + editedBy + ", editedDate=" + editedDate + ", approvedBy=" + approvedBy
				+ ", approvedDate=" + approvedDate + "]";
	}

}
