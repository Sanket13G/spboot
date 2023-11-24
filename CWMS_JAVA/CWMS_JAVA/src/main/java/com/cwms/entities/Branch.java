package com.cwms.entities;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="branch")
public class Branch {

	@EmbeddedId
    private BranchId id;

	
    @Column(name = "Branch_Name", nullable = false,length = 35)
    private String branchName;

    @Column(name = "Biz_Centre_Type",length = 1)
    private Character bizCentreType;

    @Column(name = "Base_Currency", nullable = false,length = 6)
    private String baseCurrency;

    @Column(name = "Foreign_Currency",length = 6)
    private String foreignCurrency;

    @Column(name = "Status", nullable = false,length = 1)
    private Character status;

    @Column(name = "Address_1",length = 30)
    private String address1;

    @Column(name = "Address_2",length = 30)
    private String address2;
    
    @Column(length = 30,nullable = false)
    private String GST_No;
    
   @Column(length = 25)
    private String Pan_No;
   
   @Column(length = 25)
   private String cin;
    
    @Column(name = "Address_3",length = 30)
    private String address3;

    @Column(name = "City",length = 15)
    private String city;

    @Column(name = "State",length = 15)
    private String state;

    @Column(name = "Country",length = 15)
    private String country;

    @Column(name = "PIN",length = 6)
    private String pin;

    @Column(name = "STD_Code",length = 5)
    private String stdCode;

    @Column(name = "Phone_No",length = 12)
    private String phoneNo;

    @Column(name = "Email_Id",length = 50)
    private String emailId;

    @Column(name = "SAC_Code",length = 10)
    private String sacCode;
    
    @Column(name = "TDS_Ac_Code",length = 10)
    private String tdsAcCode;

    @Column(name = "TDS_Range",length = 150)
    private String tdsRange;

    @Column(name = "TAN_No",length = 25)
    private String tanNo;

    @Column(name = "Comments",length = 150)
    private String comments;

    @Column(name = "Created_By", nullable = false,length = 10)
    private String createdBy;

    @Column(name = "Created_Date", nullable = false)
    private java.util.Date createdDate;

    @Column(name = "Edited_By",length = 10)
    private String editedBy;

    @Column(name = "Edited_Date")
    private java.util.Date editedDate;

    @Column(name = "Approved_By",length = 10)
    private String approvedBy;

    @Column(name = "Approved_Date")
    private java.util.Date approvedDate;
 

	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	public String getCin() {
		return cin;
	}



	public void setCin(String cin) {
		this.cin = cin;
	}



	public String getGST_No() {
		return GST_No;
	}



	public void setGST_No(String gST_No) {
		GST_No = gST_No;
	}



	public String getPan_No() {
		return Pan_No;
	}



	public void setPan_No(String pan_No) {
		Pan_No = pan_No;
	}



	

	


	public String getSacCode() {
		return sacCode;
	}



	public void setSacCode(String sacCode) {
		this.sacCode = sacCode;
	}



	public Branch(BranchId id, String branchName, Character bizCentreType, String baseCurrency, String foreignCurrency,
			Character status, String address1, String address2, String gST_No, String pan_No, String address3,
			String city, String state, String country, String pin, String stdCode, String phoneNo, String emailId,
			String sacCode, String tdsAcCode, String tdsRange, String tanNo, String comments, String createdBy,
			Date createdDate, String editedBy, Date editedDate, String approvedBy, Date approvedDate) {
		super();
		this.id = id;
		this.branchName = branchName;
		this.bizCentreType = bizCentreType;
		this.baseCurrency = baseCurrency;
		this.foreignCurrency = foreignCurrency;
		this.status = status;
		this.address1 = address1;
		this.address2 = address2;
		GST_No = gST_No;
		Pan_No = pan_No;
		this.address3 = address3;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pin = pin;
		this.stdCode = stdCode;
		this.phoneNo = phoneNo;
		this.emailId = emailId;
		this.sacCode = sacCode;
		this.tdsAcCode = tdsAcCode;
		this.tdsRange = tdsRange;
		this.tanNo = tanNo;
		this.comments = comments;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
	}



	public BranchId getId() {
		return id;
	}


	public void setId(BranchId id) {
		this.id = id;
	}


	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}


	public Character getBizCentreType() {
		return bizCentreType;
	}


	public void setBizCentreType(Character bizCentreType) {
		this.bizCentreType = bizCentreType;
	}


	public String getBaseCurrency() {
		return baseCurrency;
	}


	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}


	public String getForeignCurrency() {
		return foreignCurrency;
	}


	public void setForeignCurrency(String foreignCurrency) {
		this.foreignCurrency = foreignCurrency;
	}


	public Character getStatus() {
		return status;
	}


	public void setStatus(Character status) {
		this.status = status;
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


	public String getPin() {
		return pin;
	}


	public void setPin(String pin) {
		this.pin = pin;
	}


	public String getStdCode() {
		return stdCode;
	}


	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getTdsAcCode() {
		return tdsAcCode;
	}


	public void setTdsAcCode(String tdsAcCode) {
		this.tdsAcCode = tdsAcCode;
	}


	public String getTdsRange() {
		return tdsRange;
	}


	public void setTdsRange(String tdsRange) {
		this.tdsRange = tdsRange;
	}


	public String getTanNo() {
		return tanNo;
	}


	public void setTanNo(String tanNo) {
		this.tanNo = tanNo;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public java.util.Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getEditedBy() {
		return editedBy;
	}


	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}


	public java.util.Date getEditedDate() {
		return editedDate;
	}


	public void setEditedDate(java.util.Date editedDate) {
		this.editedDate = editedDate;
	}


	public String getApprovedBy() {
		return approvedBy;
	}


	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}


	public java.util.Date getApprovedDate() {
		return approvedDate;
	}


	public void setApprovedDate(java.util.Date approvedDate) {
		this.approvedDate = approvedDate;
	}


	
	
}