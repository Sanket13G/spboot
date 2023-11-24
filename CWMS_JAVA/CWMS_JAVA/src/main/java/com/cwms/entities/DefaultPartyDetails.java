package com.cwms.entities;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "default_party_dtls")
@IdClass(DefaultPartyDetailsId.class)
public class DefaultPartyDetails {
	@Id
	@Column(name = "Company_Id", length = 6)
	private String companyId;
	
	@Id
	@Column(name = "Branch_Id", length = 6)
	private String branchId;
	
	@Id
	@Column(name = "User_Id", length = 25)
	private String useId;
	
	@Column(name="Imp_CHA",length = 15,nullable = false)
	private String impCHA;
	
	@Column(name="Imp_Console",length = 15,nullable = false)
	private String impConsole;
	
	@Column(name="Exp_CHA",length = 15,nullable = false)
	private String expCHA;
	
	@Column(name="Exp_Console",length = 15,nullable = false)
	private String expConsole;
	
	
    @Column(name = "Status", length = 1, nullable = false)
    private char status;

    @Column(name = "Created_By", length = 10)
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

	public DefaultPartyDetails(String companyId, String branchId, String useId, String impCHA, String impConsole,
			String expCHA, String expConsole, char status, String createdBy, Date createdDate, String editedBy,
			Date editedDate, String approvedBy, Date approvedDate) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.useId = useId;
		this.impCHA = impCHA;
		this.impConsole = impConsole;
		this.expCHA = expCHA;
		this.expConsole = expConsole;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
	}

	public DefaultPartyDetails() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getUseId() {
		return useId;
	}

	public void setUseId(String useId) {
		this.useId = useId;
	}

	public String getImpCHA() {
		return impCHA;
	}

	public void setImpCHA(String impCHA) {
		this.impCHA = impCHA;
	}

	public String getImpConsole() {
		return impConsole;
	}

	public void setImpConsole(String impConsole) {
		this.impConsole = impConsole;
	}

	public String getExpCHA() {
		return expCHA;
	}

	public void setExpCHA(String expCHA) {
		this.expCHA = expCHA;
	}

	public String getExpConsole() {
		return expConsole;
	}

	public void setExpConsole(String expConsole) {
		this.expConsole = expConsole;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
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
		return "DefaultPartyDetails [companyId=" + companyId + ", branchId=" + branchId + ", useId=" + useId
				+ ", impCHA=" + impCHA + ", impConsole=" + impConsole + ", expCHA=" + expCHA + ", expConsole="
				+ expConsole + ", status=" + status + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", editedBy=" + editedBy + ", editedDate=" + editedDate + ", approvedBy=" + approvedBy
				+ ", approvedDate=" + approvedDate + "]";
	}
    
    
    
	public void setCurrentDate() {

		Date date = new Date();
		createdDate = date;
		editedDate = date;
		approvedDate = date;
	}
}
