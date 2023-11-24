package com.cwms.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "export_history")
public class Export_History {

	@Id
	@Column(name = "HId")
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long HistoryId;

	@Column(name = "Company_Id", length = 6)
	private String companyId;

	@Column(name = "Branch_Id", length = 6)
	private String branchId;

	@Column(name = "SB_Number", length = 15)
	private String sbNo;

	@Column(name = "SB_Request_Id", length = 20)
	private String sbRequestId;

	private String serNo;

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

	public Export_History(Long historyId, String companyId, String branchId, String sbNo, String sbRequestId,
			String serNo, Date transport_Date, String updatedBy, String oldStatus, String newStatus, String remark) {
		super();
		HistoryId = historyId;
		this.companyId = companyId;
		this.branchId = branchId;
		this.sbNo = sbNo;
		this.sbRequestId = sbRequestId;
		this.serNo = serNo;
		this.transport_Date = transport_Date;
		this.updatedBy = updatedBy;
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
		this.remark = remark;
	}

	public Export_History(String companyId, String branchId, String sbNo, String sbRequestId, String serNo,
			String updatedBy, String oldStatus, String newStatus, String remark) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.sbNo = sbNo;
		this.sbRequestId = sbRequestId;
		this.serNo = serNo;
		this.updatedBy = updatedBy;
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Export_History [HistoryId=" + HistoryId + ", companyId=" + companyId + ", branchId=" + branchId
				+ ", sbNo=" + sbNo + ", sbRequestId=" + sbRequestId + ", serNo=" + serNo + ", transport_Date="
				+ transport_Date + ", updatedBy=" + updatedBy + ", oldStatus=" + oldStatus + ", newStatus=" + newStatus
				+ ", remark=" + remark + "]";
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

	public String getSbNo() {
		return sbNo;
	}

	public void setSbNo(String sbNo) {
		this.sbNo = sbNo;
	}

	public String getSbRequestId() {
		return sbRequestId;
	}

	public void setSbRequestId(String sbRequestId) {
		this.sbRequestId = sbRequestId;
	}

	public String getserNo() {
		return serNo;
	}

	public void setserNo(String serNo) {
		this.serNo = serNo;
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

	public void SetHistoryDate() {
		Date date = new Date();
		transport_Date = date;
	}

	public Export_History() {
		super();
	}

}