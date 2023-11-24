package com.cwms.entities;


import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
@IdClass(ServicesId.class)
public class Services {
	
	@Id
	@Column(name = "Service_Id", length = 6, nullable = false)
	private String Service_Id;

	@Id
	@Column(name = "Company_Id", length = 6, nullable = false)
	private String Company_Id;
	
	@Id
	@Column(name = "Branch_Id", length = 6, nullable = false)
	private String Branch_Id;
	
	
	@Column(name = "Service_Short_desc", length = 75, nullable = false)
	private String serviceShortDescription;

	@Column(name = "Service_Long_desc", length = 150, nullable = false)
	private String serviceLongDescription;

	@Column(name = "Service_Unit", length = 15, nullable = false)
	private String serviceUnit;
	
	@Column(name = "Type_Of_Charges", length = 6, nullable = false)
	private String typeOfCharges;

	@Column(name = "Service_Unit1", length = 15)
	private String serviceUnit1;

	@Column(name = "Service_Type", length = 15, nullable = false)
	private String serviceType;

	@Column(name = "Tax_Applicable", length = 1, nullable = false)
	private String taxApplicable;

	@Column(name = "Tax_Perc", length = 6, nullable = false)
	private String taxPercentage;

	@Column(name = "Discount_Percentage", length = 1)
	private String discountPercentage;

	@Column(name = "Discount_Days", length = 1)
	private String discountDays;

	@Column(name = "Discount_Amt", length = 1)
	private String discountAmount;

	@Column(name = "Sac_Code", length = 7, nullable = false)
	private String sacCode;

	@Column(name = "Service_New_desc", length = 50, nullable = false)
	private String serviceNewDescription;

	 @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Service_Change_Date", nullable = false)
	private Date serviceChangeDate;

	@Column(name = "Service_Group", length = 1, nullable = false)
	private String serviceGroup;

	@Column(name = "Status", length = 1)
	private String status;

	@Column(name = "Created_By", length = 15)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created_Date", nullable = false)
	private Date createdDate;

	@Column(name = "Edited_By", length = 15)
	private String editedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Edited_Date")
	private Date editedDate;

	@Column(name = "Approved_By", length = 15)
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Approved_Date")
	private Date approvedDate;
	
	@Column(name = "Rate_Calculation")
	private String rateCalculation;
	
	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Services(String service_Id, String company_Id, String branch_Id, String serviceShortDescription,
			String serviceLongDescription, String serviceUnit, String typeOfCharges, String serviceUnit1,
			String serviceType, String taxApplicable, String taxPercentage, String discountPercentage,
			String discountDays, String discountAmount, String sacCode, String serviceNewDescription,
			Date serviceChangeDate, String serviceGroup, String status, String createdBy,
			Date createdDate, String editedBy, Date editedDate, String approvedBy,
			Date approvedDate, String rateCalculation) {
		super();
		Service_Id = service_Id;
		Company_Id = company_Id;
		Branch_Id = branch_Id;
		this.serviceShortDescription = serviceShortDescription;
		this.serviceLongDescription = serviceLongDescription;
		this.serviceUnit = serviceUnit;
		this.typeOfCharges = typeOfCharges;
		this.serviceUnit1 = serviceUnit1;
		this.serviceType = serviceType;
		this.taxApplicable = taxApplicable;
		this.taxPercentage = taxPercentage;
		this.discountPercentage = discountPercentage;
		this.discountDays = discountDays;
		this.discountAmount = discountAmount;
		this.sacCode = sacCode;
		this.serviceNewDescription = serviceNewDescription;
		this.serviceChangeDate = serviceChangeDate;
		this.serviceGroup = serviceGroup;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.rateCalculation = rateCalculation;
	}

	public String getService_Id() {
		return Service_Id;
	}

	public void setService_Id(String service_Id) {
		Service_Id = service_Id;
	}

	public String getCompany_Id() {
		return Company_Id;
	}

	public void setCompany_Id(String company_Id) {
		Company_Id = company_Id;
	}

	public String getBranch_Id() {
		return Branch_Id;
	}

	public void setBranch_Id(String branch_Id) {
		Branch_Id = branch_Id;
	}

	public String getServiceShortDescription() {
		return serviceShortDescription;
	}

	public void setServiceShortDescription(String serviceShortDescription) {
		this.serviceShortDescription = serviceShortDescription;
	}

	public String getServiceLongDescription() {
		return serviceLongDescription;
	}

	public void setServiceLongDescription(String serviceLongDescription) {
		this.serviceLongDescription = serviceLongDescription;
	}

	public String getServiceUnit() {
		return serviceUnit;
	}

	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
	}

	public String getTypeOfCharges() {
		return typeOfCharges;
	}

	public void setTypeOfCharges(String typeOfCharges) {
		this.typeOfCharges = typeOfCharges;
	}

	public String getServiceUnit1() {
		return serviceUnit1;
	}

	public void setServiceUnit1(String serviceUnit1) {
		this.serviceUnit1 = serviceUnit1;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getTaxApplicable() {
		return taxApplicable;
	}

	public void setTaxApplicable(String taxApplicable) {
		this.taxApplicable = taxApplicable;
	}

	public String getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(String taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public String getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getDiscountDays() {
		return discountDays;
	}

	public void setDiscountDays(String discountDays) {
		this.discountDays = discountDays;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getSacCode() {
		return sacCode;
	}

	public void setSacCode(String sacCode) {
		this.sacCode = sacCode;
	}

	public String getServiceNewDescription() {
		return serviceNewDescription;
	}

	public void setServiceNewDescription(String serviceNewDescription) {
		this.serviceNewDescription = serviceNewDescription;
	}

	public Date getServiceChangeDate() {
		return serviceChangeDate;
	}

	public void setServiceChangeDate(Date serviceChangeDate) {
		this.serviceChangeDate = serviceChangeDate;
	}

	public String getServiceGroup() {
		return serviceGroup;
	}

	public void setServiceGroup(String serviceGroup) {
		this.serviceGroup = serviceGroup;
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

	public String getRateCalculation() {
		return rateCalculation;
	}

	public void setRateCalculation(String rateCalculation) {
		this.rateCalculation = rateCalculation;
	}

	@Override
	public String toString() {
		return "Services [Service_Id=" + Service_Id + ", Company_Id=" + Company_Id + ", Branch_Id=" + Branch_Id
				+ ", serviceShortDescription=" + serviceShortDescription + ", serviceLongDescription="
				+ serviceLongDescription + ", serviceUnit=" + serviceUnit + ", typeOfCharges=" + typeOfCharges
				+ ", serviceUnit1=" + serviceUnit1 + ", serviceType=" + serviceType + ", taxApplicable=" + taxApplicable
				+ ", taxPercentage=" + taxPercentage + ", discountPercentage=" + discountPercentage + ", discountDays="
				+ discountDays + ", discountAmount=" + discountAmount + ", sacCode=" + sacCode
				+ ", serviceNewDescription=" + serviceNewDescription + ", serviceChangeDate=" + serviceChangeDate
				+ ", serviceGroup=" + serviceGroup + ", status=" + status + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", editedBy=" + editedBy + ", editedDate=" + editedDate
				+ ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + ", rateCalculation="
				+ rateCalculation + "]";
	}
	
	

	




	







	

}