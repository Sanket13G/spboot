package com.cwms.entities;
import java.util.Date;

import jakarta.persistence.*;
@Entity
@Table(name = "external_party")
@IdClass(ExternalParty_Id.class)
public class ExternalParty {
	
	@Id
	@Column(name = "External_UserId",length = 6)
	private String externaluserId;
	
	@Id
    @Column(name = "Company_Id",length = 6)
    private String companyId;

    @Id
    @Column(name = "Branch_Id",length = 6)
    private String branchId;

	@Column(name = "User_Type", length = 25, nullable = false)
    private String userType;

    @Column(name = "User_Name", length = 150, nullable = false)
    private String userName;

    @Column(name = "Login_User_Name", length = 40, nullable = false)
    private String loginUserName;

    @Column(name = "Login_Password", length = 255)
    private String loginPassword;

    @Column(name = "Email", length = 40, nullable = false)
    private String email;

    @Column(name = "Mobile", length = 15, nullable = false)
    private String mobile;
    @Column(name = "User_Status", length = 1)
    private String userstatus;
    
    @Column(name = "Status", length = 1)
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

	public ExternalParty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExternalParty(String externaluserId, String companyId, String branchId, String userType, String userName,
			String loginUserName, String loginPassword, String email, String mobile, String userstatus, String status,
			String createdBy, Date createdDate, String editedBy, Date editedDate, String approvedBy,
			Date approvedDate) {
		super();
		this.externaluserId = externaluserId;
		this.companyId = companyId;
		this.branchId = branchId;
		this.userType = userType;
		this.userName = userName;
		this.loginUserName = loginUserName;
		this.loginPassword = loginPassword;
		this.email = email;
		this.mobile = mobile;
		this.userstatus = userstatus;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
	}

	

	public String getExternaluserId() {
		return externaluserId;
	}

	public void setExternaluserId(String externaluserId) {
		this.externaluserId = externaluserId;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
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
    
    
    
    
}
