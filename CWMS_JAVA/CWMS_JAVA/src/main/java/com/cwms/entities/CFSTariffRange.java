package com.cwms.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cfstdtrfrng")
@IdClass(CFSTariffRangeId.class)
public class CFSTariffRange {

	@Id
	@Column(name = "Company_Id", length = 6, nullable = false)
	private String companyId;

	@Id
	@Column(name = "Branch_Id", length = 6, nullable = false)
	private String branchId;

	@Id
	@Column(name = "CFS_Tariff_No", length = 10, nullable = false)
	private String cfsTariffNo;

	@Id
	@Column(name = "CFS_Amnd_No", length = 3, nullable = false)
	private String cfsAmndNo;

	@Id
	@Column(name = "Service_Id", length = 10, nullable = false)
	private String serviceId;

	@Id
	@Column(name = "Srl_No", nullable = false)
	private int srlNo;

	@Column(name = "Party_Id", length = 6)
	private String partyId;

	@Column(name = "Pay_Party", length = 6)
	private String payParty;

	@Column(name = "Commodity", length = 10)
	private String commodity;

	@Column(name = "Importer_id", length = 10)
	private String importerId;

	@Column(name = "Forwarder_id", length = 6)
	private String forwarderId;

	@Column(name = "Billing_Party", length = 6)
	private String billingParty;

	@Column(name = "On_Account_Of", length = 6)
	private String onAccountOf;

	@Column(name = "CFS_Doc_Ref_No", length = 35, nullable = false)
	private String cfsDocRefNo;

	@Column(name = "Currency_Id", length = 6, nullable = false)
	private String currencyId;

	@Column(name = "Range_From", nullable = false)
	private Double rangeFrom;

	@Column(name = "Range_To", nullable = false)
	private Double rangeTo;

	@Column(name = "Range_Rate", nullable = false)
	private Double rangeRate;

	 @Column(name = "Range_Type", length = 10)
	    private String rangeType;
	
	@Column(name = "Created_By", length = 30)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created_Date")
	private Date createdDate;
	
	@Column(name = "Rate")
    private Double rate;

	@Column(name = "Edited_By", length = 30)
	private String editedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Edited_Date")
	private Date editedDate;

	@Column(name = "Approved_By", length = 30)
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Approved_Date")
	private Date approvedDate;
	
	@Column(name = "Tax_Applicable", length = 1)
    private String taxApplicable;
	
	
	@Column(name = "Status", length = 1, nullable = false)
	private String status;

	
	public CFSTariffRange() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CFSTariffRange(String companyId, String branchId, String cfsTariffNo, String cfsAmndNo, String serviceId,
			int srlNo, String partyId, String payParty, String commodity, String importerId, String forwarderId,
			String billingParty, String onAccountOf, String cfsDocRefNo, String currencyId, Double rangeFrom,
			Double rangeTo, Double rangeRate, String rangeType, String createdBy, Date createdDate, Double rate,
			String editedBy, Date editedDate, String approvedBy, Date approvedDate, String taxApplicable,
			String status) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.cfsTariffNo = cfsTariffNo;
		this.cfsAmndNo = cfsAmndNo;
		this.serviceId = serviceId;
		this.srlNo = srlNo;
		this.partyId = partyId;
		this.payParty = payParty;
		this.commodity = commodity;
		this.importerId = importerId;
		this.forwarderId = forwarderId;
		this.billingParty = billingParty;
		this.onAccountOf = onAccountOf;
		this.cfsDocRefNo = cfsDocRefNo;
		this.currencyId = currencyId;
		this.rangeFrom = rangeFrom;
		this.rangeTo = rangeTo;
		this.rangeRate = rangeRate;
		this.rangeType = rangeType;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.rate = rate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.taxApplicable = taxApplicable;
		this.status = status;
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


	public String getCfsTariffNo() {
		return cfsTariffNo;
	}


	public void setCfsTariffNo(String cfsTariffNo) {
		this.cfsTariffNo = cfsTariffNo;
	}


	public String getCfsAmndNo() {
		return cfsAmndNo;
	}


	public void setCfsAmndNo(String cfsAmndNo) {
		this.cfsAmndNo = cfsAmndNo;
	}


	public String getServiceId() {
		return serviceId;
	}


	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}


	public int getSrlNo() {
		return srlNo;
	}


	public void setSrlNo(int srlNo) {
		this.srlNo = srlNo;
	}


	public String getPartyId() {
		return partyId;
	}


	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}


	public String getPayParty() {
		return payParty;
	}


	public void setPayParty(String payParty) {
		this.payParty = payParty;
	}


	public String getCommodity() {
		return commodity;
	}


	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}


	public String getImporterId() {
		return importerId;
	}


	public void setImporterId(String importerId) {
		this.importerId = importerId;
	}


	public String getForwarderId() {
		return forwarderId;
	}


	public void setForwarderId(String forwarderId) {
		this.forwarderId = forwarderId;
	}


	public String getBillingParty() {
		return billingParty;
	}


	public void setBillingParty(String billingParty) {
		this.billingParty = billingParty;
	}


	public String getOnAccountOf() {
		return onAccountOf;
	}


	public void setOnAccountOf(String onAccountOf) {
		this.onAccountOf = onAccountOf;
	}


	public String getCfsDocRefNo() {
		return cfsDocRefNo;
	}


	public void setCfsDocRefNo(String cfsDocRefNo) {
		this.cfsDocRefNo = cfsDocRefNo;
	}


	public String getCurrencyId() {
		return currencyId;
	}


	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}


	public Double getRangeFrom() {
		return rangeFrom;
	}


	public void setRangeFrom(Double rangeFrom) {
		this.rangeFrom = rangeFrom;
	}


	public Double getRangeTo() {
		return rangeTo;
	}


	public void setRangeTo(Double rangeTo) {
		this.rangeTo = rangeTo;
	}


	public Double getRangeRate() {
		return rangeRate;
	}


	public void setRangeRate(Double rangeRate) {
		this.rangeRate = rangeRate;
	}


	public String getRangeType() {
		return rangeType;
	}


	public void setRangeType(String rangeType) {
		this.rangeType = rangeType;
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


	public Double getRate() {
		return rate;
	}


	public void setRate(Double rate) {
		this.rate = rate;
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


	public String getTaxApplicable() {
		return taxApplicable;
	}


	public void setTaxApplicable(String taxApplicable) {
		this.taxApplicable = taxApplicable;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	
	

}