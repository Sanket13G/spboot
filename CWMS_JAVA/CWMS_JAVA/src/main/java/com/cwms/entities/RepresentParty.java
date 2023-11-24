package com.cwms.entities;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "represent_party")
@IdClass(RepresentPartyID.class)
public class RepresentParty {

	@Id
	@Column(name = "Company_Id", length = 6)
	private String companyId;
	@Id
	@Column(name = "Branch_Id", length = 6)
	private String branchId;
	@Id
	@Column(name = "User_Id", length = 25)
	private String userId;
	@Id
	@Column(name = "Representative_Id", length = 10)
	private String representativeId;
	
    @Column(name = "User_Type", length = 25, nullable = false)
    private String userType;

    @Column(name = "FName", length = 50, nullable = false)
    private String firstName;

    @Column(name = "MName", length = 50, nullable = false)
    private String middleName;

    @Column(name = "LName", length = 50, nullable = false)
    private String lastName;

    @Column(name = "Mobile", length = 15, nullable = false)
    private String mobile;
    
    @Column(name = "Email", length =50, nullable = false)
    private String email;

    @Column(name = "OTP", length = 6, nullable = false)
    private String otp;

    @Column(name = "image_path", length = 100, nullable = false)
    private String imagePath;

    @Column(name = "User_Status", length = 1, nullable = false)
    private String userStatus;

    @Column(name = "Status", length = 1, nullable = false)
    private String status;

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
    
    
    @Column(name = "Sign_image_path", length = 100)
    private String signImagePath;
    
    
    
    

	public String getSignImagePath() {
		return signImagePath;
	}



	public void setSignImagePath(String signImagePath) {
		this.signImagePath = signImagePath;
	}



	public RepresentParty() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public RepresentParty(String companyId, String branchId, String userId, String representativeId, String userType,
			String firstName, String middleName, String lastName, String mobile, String email, String otp,
			String imagePath, String userStatus, String status, String createdBy, Date createdDate, String editedBy,
			Date editedDate, String approvedBy, Date approvedDate) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.userId = userId;
		this.representativeId = representativeId;
		this.userType = userType;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.email = email;
		this.otp = otp;
		this.imagePath = imagePath;
		this.userStatus = userStatus;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRepresentativeId() {
		return representativeId;
	}

	public void setRepresentativeId(String representativeId) {
		this.representativeId = representativeId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
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