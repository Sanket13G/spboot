package com.cwms.entities;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@IdClass(JarDetailId.class)
public class JarDetail {

    @Id
    @Column(name = "Jar_Dtl_Id")
    private String jarDtlId;

    @Column(name = "Company_Id")
    private String companyId;

    @Column(name = "Jar_Id")
    private String jarId;

    @Column(name = "Jar_Dtl_Desc")
    private String jarDtlDesc;

    @Column(name = "Percentage")
    private BigDecimal percentage;

    @Column(name = "Ref_Attribute")
    private String refAttribute;

    @Column(name = "Workflow_Id")
    private String workflowId;

    @Column(name = "Process_Id")
    private String processId;

    @Column(name = "Comments")
    private String comments;

    @Column(name = "Created_By")
    private String createdBy;

    @Column(name = "Created_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "Edited_By")
    private String editedBy;

    @Column(name = "Edited_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editedDate;

    @Column(name = "Approved_By",length=20)
    private String approvedBy;

    @Column(name = "Approved_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedDate;

    @Column(name = "Status")
    private String status;

	public void setCurrentDate() {
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

	public String getJarDtlId() {
		return jarDtlId;
	}

	public void setJarDtlId(String jarDtlId) {
		this.jarDtlId = jarDtlId;
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

	public String getJarDtlDesc() {
		return jarDtlDesc;
	}

	public void setJarDtlDesc(String jarDtlDesc) {
		this.jarDtlDesc = jarDtlDesc;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public String getRefAttribute() {
		return refAttribute;
	}

	public void setRefAttribute(String refAttribute) {
		this.refAttribute = refAttribute;
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

	public JarDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JarDetail(String jarDtlId, String companyId, String jarId, String jarDtlDesc, BigDecimal percentage,
			String refAttribute, String workflowId, String processId, String comments, String createdBy,
			Date createdDate, String editedBy, Date editedDate, String approvedBy, Date approvedDate, String status) {
		super();
		this.jarDtlId = jarDtlId;
		this.companyId = companyId;
		this.jarId = jarId;
		this.jarDtlDesc = jarDtlDesc;
		this.percentage = percentage;
		this.refAttribute = refAttribute;
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


    
    
}