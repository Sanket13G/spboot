package com.cwms.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "detention")
@IdClass(DetentionId.class)
public class Detention implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long siNo;
	
	
	@Id
	@Column(name = "Company_Id", length = 6)
	private String companyId;

	@Id
	@Column(name = "Branch_Id", length = 6)
	private String branchId;

	@Id
	@Column(name = "Detention_Id", length = 10)
	private String detentionId;

	@Id
	@Column(name = "File_No", length = 60)
	private String fileNo;

	@Column(name = "Deposit_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date depositDate;

	@Column(name = "Parcel_Detained_By", length = 15)
	private String parcelDetainedBy;

	@Column(name = "Officer_Name", length = 30)
	private String officerName;

	@Column(name = "Officer_Designation", length = 15)
	private String officerDesignation;

	@Column(name = "DGDC_Officer_Name", length = 30)
	private String dgdcOfficerName;

	@Column(name = "DGDC_Officer_Designation", length = 15)
	private String dgdcOfficerDesignation;

	@Column(name = "Nop")
	private int nop;

	@Column(name = "Parcel_Type", length = 10)
	private String parcelType;

	@Column(name = "Party_Id", length = 63)
	private String partyId;

	@Column(name = "Other_Party", length = 63)
	private String otherParty;

	@Column(name = "Remarks", length = 255)
	private String remarks;

	@Column(name = "Status")
	private String status;

	@Column(name = "Created_By", length = 10)
	private String createdBy;

	@Column(name = "Created_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "Edited_By", length = 10)
	private String editedBy;

	@Column(name = "Edited_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date editedDate;

	@Column(name = "Approved_By", length = 10)
	private String approvedBy;

	@Column(name = "Approved_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	@Column(name = "Withdraw_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date withdrawDate;

	
	@Column(name = "Withdraw_Officer_Name", length = 30)
	private String withdrawOfficerName;

	@Column(name = "Withdraw_Officer_Designation", length = 15)
	private String withdrawOfficerDesignation;

	@Column(name = "Withdraw_DGDC_Officer_Name", length = 30)
	private String withdrawDgdcOfficerName;

	@Column(name = "Withdraw_DGDC_Officer_Designation", length = 15)
	private String withdrawDgdcOfficerDesignation;

	@Column(name = "Withdraw_Nop")
	private int withdrawNop;
	
	@Column(name = "Withdraw_Party_Id", length = 63)
	private String withdrawPartyId;
	
	@Column(name = "Withdraw_Remarks", length = 255)
	private String withdrawRemarks;
	
	@Column(name = "Issue_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueDate;

	
	@Column(name = "Issue_Officer_Name", length = 30)
	private String issueOfficerName;

	@Column(name = "Issue_Officer_Designation", length = 15)
	private String issueOfficerDesignation;

	@Column(name = "Issue_DGDC_Officer_Name", length = 30)
	private String issueDgdcOfficerName;

	@Column(name = "Issue_DGDC_Officer_Designation", length = 15)
	private String issueDgdcOfficerDesignation;
	
	@Column(name = "Issue_Nop")
	private int issueNop;
	
	@Column(name = "Issue_Type")
	private String issueType;
	
	
	@Column(name = "Issue_Reason", length = 255)
	private String issueReason;
	
	@Column(name = "Issue_Remarks", length = 255)
	private String issueRemarks;
	
	
	
	@Column(name = "Redeposite_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date redepositeDate;

	
	@Column(name = "Redeposite_Officer_Name", length = 30)
	private String redepositeOfficerName;

	@Column(name = "Redeposite_Officer_Designation", length = 15)
	private String redepositeOfficerDesignation;

	@Column(name = "Redeposite_DGDC_Officer_Name", length = 30)
	private String redepositeDgdcOfficerName;

	@Column(name = "Redeposite_DGDC_Officer_Designation", length = 15)
	private String redepositeDgdcOfficerDesignation;
	
	@Column(name = "Redeposite_Nop")
	private int redepositeNop;
	
	@Column(name = "Redeposite_Type")
	private String redepositeType;
	

	
	@Column(name = "Redeposite_Remarks", length = 255)
	private String redepositeRemarks;



	public Detention() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Detention(long siNo, String companyId, String branchId, String detentionId, String fileNo, Date depositDate,
			String parcelDetainedBy, String officerName, String officerDesignation, String dgdcOfficerName,
			String dgdcOfficerDesignation, int nop, String parcelType, String partyId, String otherParty,
			String remarks, String status, String createdBy, Date createdDate, String editedBy, Date editedDate,
			String approvedBy, Date approvedDate, Date withdrawDate, String withdrawOfficerName,
			String withdrawOfficerDesignation, String withdrawDgdcOfficerName, String withdrawDgdcOfficerDesignation,
			int withdrawNop, String withdrawPartyId, String withdrawRemarks, Date issueDate, String issueOfficerName,
			String issueOfficerDesignation, String issueDgdcOfficerName, String issueDgdcOfficerDesignation,
			int issueNop, String issueType, String issueReason, String issueRemarks, Date redepositeDate,
			String redepositeOfficerName, String redepositeOfficerDesignation, String redepositeDgdcOfficerName,
			String redepositeDgdcOfficerDesignation, int redepositeNop, String redepositeType,
			String redepositeRemarks) {
		super();
		this.siNo = siNo;
		this.companyId = companyId;
		this.branchId = branchId;
		this.detentionId = detentionId;
		this.fileNo = fileNo;
		this.depositDate = depositDate;
		this.parcelDetainedBy = parcelDetainedBy;
		this.officerName = officerName;
		this.officerDesignation = officerDesignation;
		this.dgdcOfficerName = dgdcOfficerName;
		this.dgdcOfficerDesignation = dgdcOfficerDesignation;
		this.nop = nop;
		this.parcelType = parcelType;
		this.partyId = partyId;
		this.otherParty = otherParty;
		this.remarks = remarks;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.withdrawDate = withdrawDate;
		this.withdrawOfficerName = withdrawOfficerName;
		this.withdrawOfficerDesignation = withdrawOfficerDesignation;
		this.withdrawDgdcOfficerName = withdrawDgdcOfficerName;
		this.withdrawDgdcOfficerDesignation = withdrawDgdcOfficerDesignation;
		this.withdrawNop = withdrawNop;
		this.withdrawPartyId = withdrawPartyId;
		this.withdrawRemarks = withdrawRemarks;
		this.issueDate = issueDate;
		this.issueOfficerName = issueOfficerName;
		this.issueOfficerDesignation = issueOfficerDesignation;
		this.issueDgdcOfficerName = issueDgdcOfficerName;
		this.issueDgdcOfficerDesignation = issueDgdcOfficerDesignation;
		this.issueNop = issueNop;
		this.issueType = issueType;
		this.issueReason = issueReason;
		this.issueRemarks = issueRemarks;
		this.redepositeDate = redepositeDate;
		this.redepositeOfficerName = redepositeOfficerName;
		this.redepositeOfficerDesignation = redepositeOfficerDesignation;
		this.redepositeDgdcOfficerName = redepositeDgdcOfficerName;
		this.redepositeDgdcOfficerDesignation = redepositeDgdcOfficerDesignation;
		this.redepositeNop = redepositeNop;
		this.redepositeType = redepositeType;
		this.redepositeRemarks = redepositeRemarks;
	}



	public long getSiNo() {
		return siNo;
	}



	public void setSiNo(long siNo) {
		this.siNo = siNo;
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



	public String getDetentionId() {
		return detentionId;
	}



	public void setDetentionId(String detentionId) {
		this.detentionId = detentionId;
	}



	public String getFileNo() {
		return fileNo;
	}



	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}



	public Date getDepositDate() {
		return depositDate;
	}



	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}



	public String getParcelDetainedBy() {
		return parcelDetainedBy;
	}



	public void setParcelDetainedBy(String parcelDetainedBy) {
		this.parcelDetainedBy = parcelDetainedBy;
	}



	public String getOfficerName() {
		return officerName;
	}



	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}



	public String getOfficerDesignation() {
		return officerDesignation;
	}



	public void setOfficerDesignation(String officerDesignation) {
		this.officerDesignation = officerDesignation;
	}



	public String getDgdcOfficerName() {
		return dgdcOfficerName;
	}



	public void setDgdcOfficerName(String dgdcOfficerName) {
		this.dgdcOfficerName = dgdcOfficerName;
	}



	public String getDgdcOfficerDesignation() {
		return dgdcOfficerDesignation;
	}



	public void setDgdcOfficerDesignation(String dgdcOfficerDesignation) {
		this.dgdcOfficerDesignation = dgdcOfficerDesignation;
	}



	public int getNop() {
		return nop;
	}



	public void setNop(int nop) {
		this.nop = nop;
	}



	public String getParcelType() {
		return parcelType;
	}



	public void setParcelType(String parcelType) {
		this.parcelType = parcelType;
	}



	public String getPartyId() {
		return partyId;
	}



	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}



	public String getOtherParty() {
		return otherParty;
	}



	public void setOtherParty(String otherParty) {
		this.otherParty = otherParty;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
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



	public Date getWithdrawDate() {
		return withdrawDate;
	}



	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}



	public String getWithdrawOfficerName() {
		return withdrawOfficerName;
	}



	public void setWithdrawOfficerName(String withdrawOfficerName) {
		this.withdrawOfficerName = withdrawOfficerName;
	}



	public String getWithdrawOfficerDesignation() {
		return withdrawOfficerDesignation;
	}



	public void setWithdrawOfficerDesignation(String withdrawOfficerDesignation) {
		this.withdrawOfficerDesignation = withdrawOfficerDesignation;
	}



	public String getWithdrawDgdcOfficerName() {
		return withdrawDgdcOfficerName;
	}



	public void setWithdrawDgdcOfficerName(String withdrawDgdcOfficerName) {
		this.withdrawDgdcOfficerName = withdrawDgdcOfficerName;
	}



	public String getWithdrawDgdcOfficerDesignation() {
		return withdrawDgdcOfficerDesignation;
	}



	public void setWithdrawDgdcOfficerDesignation(String withdrawDgdcOfficerDesignation) {
		this.withdrawDgdcOfficerDesignation = withdrawDgdcOfficerDesignation;
	}



	public int getWithdrawNop() {
		return withdrawNop;
	}



	public void setWithdrawNop(int withdrawNop) {
		this.withdrawNop = withdrawNop;
	}



	public String getWithdrawPartyId() {
		return withdrawPartyId;
	}



	public void setWithdrawPartyId(String withdrawPartyId) {
		this.withdrawPartyId = withdrawPartyId;
	}



	public String getWithdrawRemarks() {
		return withdrawRemarks;
	}



	public void setWithdrawRemarks(String withdrawRemarks) {
		this.withdrawRemarks = withdrawRemarks;
	}



	public Date getIssueDate() {
		return issueDate;
	}



	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}



	public String getIssueOfficerName() {
		return issueOfficerName;
	}



	public void setIssueOfficerName(String issueOfficerName) {
		this.issueOfficerName = issueOfficerName;
	}



	public String getIssueOfficerDesignation() {
		return issueOfficerDesignation;
	}



	public void setIssueOfficerDesignation(String issueOfficerDesignation) {
		this.issueOfficerDesignation = issueOfficerDesignation;
	}



	public String getIssueDgdcOfficerName() {
		return issueDgdcOfficerName;
	}



	public void setIssueDgdcOfficerName(String issueDgdcOfficerName) {
		this.issueDgdcOfficerName = issueDgdcOfficerName;
	}



	public String getIssueDgdcOfficerDesignation() {
		return issueDgdcOfficerDesignation;
	}



	public void setIssueDgdcOfficerDesignation(String issueDgdcOfficerDesignation) {
		this.issueDgdcOfficerDesignation = issueDgdcOfficerDesignation;
	}



	public int getIssueNop() {
		return issueNop;
	}



	public void setIssueNop(int issueNop) {
		this.issueNop = issueNop;
	}



	public String getIssueType() {
		return issueType;
	}



	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}



	public String getIssueReason() {
		return issueReason;
	}



	public void setIssueReason(String issueReason) {
		this.issueReason = issueReason;
	}



	public String getIssueRemarks() {
		return issueRemarks;
	}



	public void setIssueRemarks(String issueRemarks) {
		this.issueRemarks = issueRemarks;
	}



	public Date getRedepositeDate() {
		return redepositeDate;
	}



	public void setRedepositeDate(Date redepositeDate) {
		this.redepositeDate = redepositeDate;
	}



	public String getRedepositeOfficerName() {
		return redepositeOfficerName;
	}



	public void setRedepositeOfficerName(String redepositeOfficerName) {
		this.redepositeOfficerName = redepositeOfficerName;
	}



	public String getRedepositeOfficerDesignation() {
		return redepositeOfficerDesignation;
	}



	public void setRedepositeOfficerDesignation(String redepositeOfficerDesignation) {
		this.redepositeOfficerDesignation = redepositeOfficerDesignation;
	}



	public String getRedepositeDgdcOfficerName() {
		return redepositeDgdcOfficerName;
	}



	public void setRedepositeDgdcOfficerName(String redepositeDgdcOfficerName) {
		this.redepositeDgdcOfficerName = redepositeDgdcOfficerName;
	}



	public String getRedepositeDgdcOfficerDesignation() {
		return redepositeDgdcOfficerDesignation;
	}



	public void setRedepositeDgdcOfficerDesignation(String redepositeDgdcOfficerDesignation) {
		this.redepositeDgdcOfficerDesignation = redepositeDgdcOfficerDesignation;
	}



	public int getRedepositeNop() {
		return redepositeNop;
	}



	public void setRedepositeNop(int redepositeNop) {
		this.redepositeNop = redepositeNop;
	}



	public String getRedepositeType() {
		return redepositeType;
	}



	public void setRedepositeType(String redepositeType) {
		this.redepositeType = redepositeType;
	}



	public String getRedepositeRemarks() {
		return redepositeRemarks;
	}



	public void setRedepositeRemarks(String redepositeRemarks) {
		this.redepositeRemarks = redepositeRemarks;
	}



	
	
	



	
	

}