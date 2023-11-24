
package com.cwms.entities;


import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@IdClass(PartyId.class)
public class Party implements Serializable {

    @Id
    @Column(name = "Company_Id", length = 6)
    private String companyId;

    @Id
    @Column(name = "Branch_Id", length = 6)
    private String branchId;

    @Id
    @Column(name = "Party_Id", length = 6)
    private String partyId;

    @Column(name = "Party_Name", length = 60, nullable = false)
    private String partyName;

    @Column(name = "Address_1", length = 35, nullable = false)
    private String address1;

    @Column(name = "Address_2", length = 35)
    private String address2;

    @Column(name = "Address_3", length = 35)
    private String address3;

    @Column(name = "City", length = 15)
    private String city;

    @Column(name = "PIN", length = 10)
    private String pin;

    @Column(name = "State", length = 15)
    private String state;

    @Column(name = "Country", length = 15)
    private String country;

    @Column(name = "Unit_Admin_Name", length = 60, nullable = false)
    private String unitAdminName;

    @Column(name = "Unit_Type", length = 20, nullable = false)
    private String unitType;

    @Column(name = "Email", length = 60, nullable = false)
    private String email;

    @Column(name = "Phone_No", length = 15)
    private String phoneNo;

    @Column(name = "Mobile_No", length = 15)
    private String mobileNo;

    @Column(name = "Party_Code", length = 15)
    private String partyCode;

    @Column(name = "ERP_Code", length = 15)
    private String erpCode;

    @Column(name = "Credit_Limit", precision = 10)
    private Double creditLimit;

    @Column(name = "IEC_No", length = 20, nullable = false)
    private String iecNo;

    @Column(name = "Entity_id", length = 20, nullable = false)
    private String entityId;

    @Column(name = "PAN_No", length = 25)
    private String panNo;

    @Column(name = "GST_No", length = 30, nullable = false)
    private String gstNo;

    @Column(name = "LOA_NUMBER", length = 80, nullable = false)
    private String loaNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOA_ISSUE_DATE")
    private Date loaIssueDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOA_EXPIRY_DATE")
    private Date loaExpiryDate;

    @Column(name = "Created_By", length = 10, nullable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Created_Date", nullable = false)
    private Date createdDate;

    @Column(name = "Edited_By", length = 10)
    private String editedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Edited_Date")
    private Date editedDate;

    @Column(name = "Approved_By", length = 10)
    private String approvedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Approved_Date")
    private Date approvedDate;

    @Column(name = "Status", length = 1, nullable = false)
    private String status;

    @Column(name = "Last_InVoice_No", length = 6)
	private String lastInVoiceNo;

	@Temporal(TemporalType.DATE)
	@Column(name = "Last_InVoice_Date", length = 25)
	private Date lastInVoiceDate;
	
	
	@Column(name="Invoice_Type",length = 10)
    private String invoiceType;
    
    
    @Column(name="Party_status",length = 10)
    private String partyStatus;
	
	@Column(name = "TaxApplicable", length = 1)
    private String taxApplicable;
	
	 @Column(name = "Correction", length = 255)
	    private String correction;
	 
	 
	 
	 
	 
	
	
	public String getCorrection() {
		return correction;
	}






	public String getInvoiceType() {
		return invoiceType;
	}






	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}






	public String getPartyStatus() {
		return partyStatus;
	}






	public void setPartyStatus(String partyStatus) {
		this.partyStatus = partyStatus;
	}






	public Party(String companyId, String branchId, String partyId, String partyName, String address1, String address2,
			String address3, String city, String pin, String state, String country, String unitAdminName,
			String unitType, String email, String phoneNo, String mobileNo, String partyCode, String erpCode,
			Double creditLimit, String iecNo, String entityId, String panNo, String gstNo, String loaNumber,
			Date loaIssueDate, Date loaExpiryDate, String createdBy, Date createdDate, String editedBy, Date editedDate,
			String approvedBy, Date approvedDate, String status, String lastInVoiceNo, Date lastInVoiceDate,
			String invoiceType, String partyStatus, String taxApplicable, String correction) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.partyId = partyId;
		this.partyName = partyName;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.pin = pin;
		this.state = state;
		this.country = country;
		this.unitAdminName = unitAdminName;
		this.unitType = unitType;
		this.email = email;
		this.phoneNo = phoneNo;
		this.mobileNo = mobileNo;
		this.partyCode = partyCode;
		this.erpCode = erpCode;
		this.creditLimit = creditLimit;
		this.iecNo = iecNo;
		this.entityId = entityId;
		this.panNo = panNo;
		this.gstNo = gstNo;
		this.loaNumber = loaNumber;
		this.loaIssueDate = loaIssueDate;
		this.loaExpiryDate = loaExpiryDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.status = status;
		this.lastInVoiceNo = lastInVoiceNo;
		this.lastInVoiceDate = lastInVoiceDate;
		this.invoiceType = invoiceType;
		this.partyStatus = partyStatus;
		this.taxApplicable = taxApplicable;
		this.correction = correction;
	}






	public void setCorrection(String correction) {
		this.correction = correction;
	}






	public String getTaxApplicable() {
		return taxApplicable;
	}






	public void setTaxApplicable(String taxApplicable) {
		this.taxApplicable = taxApplicable;
	}

	
	public Party() {
		
	}


public String getLastInVoiceNo() {
		return lastInVoiceNo;
	}


	public void setLastInVoiceNo(String lastInVoiceNo) {
		this.lastInVoiceNo = lastInVoiceNo;
	}


	public Date getLastInVoiceDate() {
		return lastInVoiceDate;
	}


	public void setLastInVoiceDate(Date lastInVoiceDate) {
		this.lastInVoiceDate = lastInVoiceDate;
	}






public Party(String companyId, String branchId, String partyId, String partyName, String address1, String address2,
			String address3, String city, String pin, String state, String country, String unitAdminName,
			String unitType, String email, String phoneNo, String mobileNo, String partyCode, String erpCode,
			Double creditLimit, String iecNo, String entityId, String panNo, String gstNo, String loaNumber,
			Date loaIssueDate, Date loaExpiryDate, String createdBy, Date createdDate, String editedBy, Date editedDate,
			String approvedBy, Date approvedDate, String status, String lastInVoiceNo, Date lastInVoiceDate,
			String taxApplicable, String correction) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.partyId = partyId;
		this.partyName = partyName;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.pin = pin;
		this.state = state;
		this.country = country;
		this.unitAdminName = unitAdminName;
		this.unitType = unitType;
		this.email = email;
		this.phoneNo = phoneNo;
		this.mobileNo = mobileNo;
		this.partyCode = partyCode;
		this.erpCode = erpCode;
		this.creditLimit = creditLimit;
		this.iecNo = iecNo;
		this.entityId = entityId;
		this.panNo = panNo;
		this.gstNo = gstNo;
		this.loaNumber = loaNumber;
		this.loaIssueDate = loaIssueDate;
		this.loaExpiryDate = loaExpiryDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.status = status;
		this.lastInVoiceNo = lastInVoiceNo;
		this.lastInVoiceDate = lastInVoiceDate;
		this.taxApplicable = taxApplicable;
		this.correction = correction;
	}






//	
//	public Party(String companyId, String branchId, String partyId, String partyName, String address1, String address2,
//			String address3, String city, String pin, String state, String country, String unitAdminName,
//			String unitType, String email, String phoneNo, String mobileNo, String partyCode, String erpCode,
//			Double creditLimit, String iecNo, String entityId, String panNo, String gstNo, String loaNumber,
//			Date loaIssueDate, Date loaExpiryDate, String createdBy, Date createdDate, String editedBy, Date editedDate,
//			String approvedBy, Date approvedDate, String status) {
//		super();
//		this.companyId = companyId;
//		this.branchId = branchId;
//		this.partyId = partyId;
//		this.partyName = partyName;
//		this.email = email;
//		this.mobileNo = mobileNo;
//		this.iecNo = iecNo;
//		this.creditLimit = creditLimit;
//		this.status = status;
//		this.entityId = entityId;
//		this.address1 = address1;
//		this.address2 = address2;
//		this.address3 = address3;
//		this.city = city;
//		this.pin = pin;
//		this.state = state;
//		this.country = country;
//		this.unitAdminName = unitAdminName;
//		this.unitType = unitType;
//		
//		this.phoneNo = phoneNo;
//		
//		this.partyCode = partyCode;
//		this.erpCode = erpCode;
//		
//		
//	
//		this.panNo = panNo;
//		this.gstNo = gstNo;
//		this.loaNumber = loaNumber;
//		this.loaIssueDate = loaIssueDate;
//		this.loaExpiryDate = loaExpiryDate;
//		this.createdBy = createdBy;
//		this.createdDate = createdDate;
//		this.editedBy = editedBy;
//		this.editedDate = editedDate;
//		this.approvedBy = approvedBy;
//		this.approvedDate = approvedDate;
//		
//	}

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

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUnitAdminName() {
		return unitAdminName;
	}

	public void setUnitAdminName(String unitAdminName) {
		this.unitAdminName = unitAdminName;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getErpCode() {
		return erpCode;
	}

	public void setErpCode(String erpCode) {
		this.erpCode = erpCode;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getIecNo() {
		return iecNo;
	}

	public void setIecNo(String iecNo) {
		this.iecNo = iecNo;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getLoaNumber() {
		return loaNumber;
	}

	public void setLoaNumber(String loaNumber) {
		this.loaNumber = loaNumber;
	}

	public Date getLoaIssueDate() {
		return loaIssueDate;
	}

	public void setLoaIssueDate(Date loaIssueDate) {
		this.loaIssueDate = loaIssueDate;
	}

	public Date getLoaExpiryDate() {
		return loaExpiryDate;
	}

	public void setLoaExpiryDate(Date loaExpiryDate) {
		this.loaExpiryDate = loaExpiryDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Party [companyId=" + companyId + ", branchId=" + branchId + ", partyId=" + partyId + ", partyName="
				+ partyName + ", address1=" + address1 + ", address2=" + address2 + ", address3=" + address3 + ", city="
				+ city + ", pin=" + pin + ", state=" + state + ", country=" + country + ", unitAdminName="
				+ unitAdminName + ", unitType=" + unitType + ", email=" + email + ", phoneNo=" + phoneNo + ", mobileNo="
				+ mobileNo + ", partyCode=" + partyCode + ", erpCode=" + erpCode + ", creditLimit=" + creditLimit
				+ ", iecNo=" + iecNo + ", entityId=" + entityId + ", panNo=" + panNo + ", gstNo=" + gstNo
				+ ", loaNumber=" + loaNumber + ", loaIssueDate=" + loaIssueDate + ", loaExpiryDate=" + loaExpiryDate
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", editedBy=" + editedBy
				+ ", editedDate=" + editedDate + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate
				+ ", status=" + status + "]";
	}

	@PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
        this.loaIssueDate = new Date();
        this.loaExpiryDate = new Date();
        this.approvedDate=new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.editedDate = new Date();
        
        }
    
}

