package com.cwms.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Party_Representative")
@IdClass(PartyRepresentativeId.class)
public class PartyRepresentative implements Serializable {
//    private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "Representative_Id", nullable = false, length = 6)
    private String partyRepresentativeId;

	
	
    @Id
    @Column(name = "Company_Id", nullable = false, length = 6)
    private String companyId;

    @Id
    @Column(name = "Branch_Id", nullable = false, length = 6)
    private String branchId;

    @Id
    @Column(name = "Party_Id", nullable = false, length = 6)
    private String partyId;

    @Column(name = "Party_Name",  length = 60)
    private String partyName;

    @Column(name = "Contact_Name",  length = 30)
    private String contactName;
    
    @Column(name = "Carting_Agent",  length = 30)
    private String cartingAgent;
    
    @Column(name = "OTP",  length = 4)
    private String otp;
    
    @Column(name = "Representative_Name",  length = 30)
    private String representativeName;

    @Column(name = "Mobile_No", nullable = false, length = 15)
    private String mobileNo;

    @Lob
    @Column(name = "Image_Path",  length = 155)
    private String imagePath;

    @Column(name = "Contact_Status", length = 10)
    private String contactStatus;

    @Column(name = "Created_By", length = 10)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Created_Date")
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

    @Column(name = "Status", length = 1)
    private String status;


	public PartyRepresentative() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PartyRepresentative(String partyRepresentativeId, String companyId, String branchId, String partyId,
			String partyName, String contactName, String cartingAgent, String otp, String representativeName,
			String mobileNo, String imagePath, String contactStatus, String createdBy, Date createdDate,
			String editedBy, Date editedDate, String approvedBy, Date approvedDate, String status) {
		super();
		this.partyRepresentativeId = partyRepresentativeId;
		this.companyId = companyId;
		this.branchId = branchId;
		this.partyId = partyId;
		this.partyName = partyName;
		this.contactName = contactName;
		this.cartingAgent = cartingAgent;
		this.otp = otp;
		this.representativeName = representativeName;
		this.mobileNo = mobileNo;
		this.imagePath = imagePath;
		this.contactStatus = contactStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.status = status;
	}


	public String getPartyRepresentativeId() {
		return partyRepresentativeId;
	}


	public void setPartyRepresentativeId(String partyRepresentativeId) {
		this.partyRepresentativeId = partyRepresentativeId;
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


	public String getContactName() {
		return contactName;
	}


	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


	public String getCartingAgent() {
		return cartingAgent;
	}


	public void setCartingAgent(String cartingAgent) {
		this.cartingAgent = cartingAgent;
	}


	public String getOtp() {
		return otp;
	}


	public void setOtp(String otp) {
		this.otp = otp;
	}


	public String getRepresentativeName() {
		return representativeName;
	}


	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public String getContactStatus() {
		return contactStatus;
	}


	public void setContactStatus(String contactStatus) {
		this.contactStatus = contactStatus;
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
		return "PartyRepresentative [partyRepresentativeId=" + partyRepresentativeId + ", companyId=" + companyId
				+ ", branchId=" + branchId + ", partyId=" + partyId + ", partyName=" + partyName + ", contactName="
				+ contactName + ", cartingAgent=" + cartingAgent + ", otp=" + otp + ", representativeName="
				+ representativeName + ", mobileNo=" + mobileNo + ", imagePath=" + imagePath + ", contactStatus="
				+ contactStatus + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", editedBy=" + editedBy
				+ ", editedDate=" + editedDate + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate
				+ ", status=" + status + "]";
	}


	


    
}