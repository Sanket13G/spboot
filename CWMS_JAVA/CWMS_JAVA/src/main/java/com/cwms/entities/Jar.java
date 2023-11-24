package com.cwms.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "jar") 
@IdClass(JarId.class)
public class Jar extends JarId{

	
	@Id
	@Column(name = "Company_Id", length = 6, nullable = false)
	private String companyId;

	@Id
	@Column(name = "Jar_Id", length = 6, nullable = false)
	private String jarId;

	@Column(name = "Jar_Desc", length = 60, nullable = false)
	private String jarDesc;

	@Column(name = "Soundex_Desc", length = 25)
	private String soundexDesc;

	@Column(name = "Jar_Type", length = 20, nullable = false)
	private String jarType;

	@Column(name = "Detail_Auto_Flag", length = 1, nullable = true)
	private String detailAutoFlag;

	@Column(name = "Import_Appl", length = 1)
	private String importAppl;

	@Column(name = "Reference_1", length = 6)
	private String reference1;

	@Column(name = "Reference_2", length = 6)
	private String reference2;

	@Column(name = "Workflow_Id", length = 6)
	private String workflowId;

	@Column(name = "Process_Id", length = 6)
	private String processId;

	@Column(name = "Comments", length = 150)
	private String comments;

	@Column(name = "Created_By", length = 10, nullable = false)
	private String createdBy;

	@Column(name = "Created_Date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "Edited_By", length = 10)
	private String editedBy;

	@Column(name = "Edited_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date editedDate;

	@Column(name = "Approved_By", length = 20)
	private String approvedBy;

	@Column(name = "Approved_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	@Column(name = "Status", length = 1, nullable = false)
	private String status;

	public void setdate() {
		Date d = new Date();
		if (createdDate == null) {
			createdDate = d;
		}
		if (approvedDate== null) {
			approvedDate= d;
		}
		if (editedDate == null) {
			editedDate = d;
		}
	}

	
	
	@Override
	public String toString() {
		return "Jar [companyId=" + companyId + ", jarId=" + jarId + ", jarDesc=" + jarDesc + ", soundexDesc="
				+ soundexDesc + ", jarType=" + jarType + ", detailAutoFlag=" + detailAutoFlag + ", importAppl="
				+ importAppl + ", reference1=" + reference1 + ", reference2=" + reference2 + ", workflowId="
				+ workflowId + ", processId=" + processId + ", comments=" + comments + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", editedBy=" + editedBy + ", editedDate=" + editedDate
				+ ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + ", status=" + status + "]";
	}



	public Jar(String companyId, String jarId, String companyId2, String jarId2, String jarDesc, String soundexDesc,
			String jarType, String detailAutoFlag, String importAppl, String reference1, String reference2,
			String workflowId, String processId, String comments, String createdBy, Date createdDate, String editedBy,
			Date editedDate, String approvedBy, Date approvedDate, String status) {
		super(companyId, jarId);
		companyId = companyId2;
		jarId = jarId2;
		this.jarDesc = jarDesc;
		this.soundexDesc = soundexDesc;
		this.jarType = jarType;
		this.detailAutoFlag = detailAutoFlag;
		this.importAppl = importAppl;
		this.reference1 = reference1;
		this.reference2 = reference2;
		this.workflowId = workflowId;
		this.processId = processId;
		this.comments = comments;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.status = status;
	}



	public String getCompanyId() {
		return companyId;
	}



	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}



	public String getJarId() {
		return jarId;
	}



	public void setJarId(String jarId) {
		this.jarId = jarId;
	}



	public String getJarDesc() {
		return jarDesc;
	}



	public void setJarDesc(String jarDesc) {
		this.jarDesc = jarDesc;
	}



	public String getSoundexDesc() {
		return soundexDesc;
	}



	public void setSoundexDesc(String soundexDesc) {
		this.soundexDesc = soundexDesc;
	}



	public String getJarType() {
		return jarType;
	}



	public void setJarType(String jarType) {
		this.jarType = jarType;
	}



	public String getDetailAutoFlag() {
		return detailAutoFlag;
	}



	public void setDetailAutoFlag(String detailAutoFlag) {
		this.detailAutoFlag = detailAutoFlag;
	}



	public String getImportAppl() {
		return importAppl;
	}



	public void setImportAppl(String importAppl) {
		this.importAppl = importAppl;
	}



	public String getReference1() {
		return reference1;
	}



	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}



	public String getReference2() {
		return reference2;
	}



	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}



	public String getWorkflowId() {
		return workflowId;
	}



	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}



	public String getProcessId() {
		return processId;
	}



	public void setProcessId(String processId) {
		this.processId = processId;
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



	public Jar() {
		super();
	}
	
	// Add constructors, getters, and setters
}