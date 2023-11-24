package com.cwms.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="importsub_history")
public class ImportSub_History {
	@Id
	@Column(name = "HId")
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long HistoryId;

	@Column(name = "Company_Id", length = 6)
	private String companyId;

	@Column(name = "Branch_Id", length = 6)
	private String branchId;


	@Column(name = "Request_Id", length = 20)
	private String requestId;

	@Column(name = "Sir_No", length = 20)
	private String sirNo;

	@Column(name = "TransacrtionDate")
	private Date transport_Date;

	@Column(name = "updatedBy", length = 20)
	private String updatedBy;

	@Column(name = "oldStatus", length = 30)
	private String oldStatus;
	@Column(name = "newStatus", length = 30)
	private String newStatus;
	@Column(name = "remark", length = 50)
	private String remark;
	public ImportSub_History() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ImportSub_History(Long historyId, String companyId, String branchId, String requestId, String sirNo,
			Date transport_Date, String updatedBy, String oldStatus, String newStatus, String remark) {
		super();
		HistoryId = historyId;
		this.companyId = companyId;
		this.branchId = branchId;
		this.requestId = requestId;
		this.sirNo = sirNo;
		this.transport_Date = transport_Date;
		this.updatedBy = updatedBy;
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
		this.remark = remark;
	}
	public Long getHistoryId() {
		return HistoryId;
	}
	public void setHistoryId(Long historyId) {
		HistoryId = historyId;
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
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getSirNo() {
		return sirNo;
	}
	public void setSirNo(String sirNo) {
		this.sirNo = sirNo;
	}
	public Date getTransport_Date() {
		return transport_Date;
	}
	public void setTransport_Date(Date transport_Date) {
		this.transport_Date = transport_Date;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getOldStatus() {
		return oldStatus;
	}
	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}
	public String getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
