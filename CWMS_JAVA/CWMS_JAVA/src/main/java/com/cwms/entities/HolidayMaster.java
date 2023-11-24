package com.cwms.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@IdClass(HolidayId.class)
public class HolidayMaster implements Serializable {

	@Id
	@Column(name = "Company_Id", nullable = false, length = 6)
	private String companyId;

	@Id
	@Column(name = "Branch_Id", nullable = false, length = 6)
	private String branchId;

	@Id
	@Column(name = "Holiday_Id", nullable = false, length = 6)
	private String holidayId;

	@Column(name = "Holiday_Name", nullable = false, length = 60)
	private String holidayName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Holiday_Date", nullable = false)
	private Date holidayDate;

	@Column(name = "Holiday_Day", nullable = false, length = 15)
	private String holidayDay;

	@Column(name = "Created_By", nullable = false, length = 10)
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

	@Column(name = "Status", nullable = false, length = 1)
	private char status;

	public HolidayMaster() {
		super();
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

	public String getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(String holidayId) {
		this.holidayId = holidayId;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getHolidayDay() {
		return holidayDay;
	}

	public void setHolidayDay(String holidayDay) {
		this.holidayDay = holidayDay;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public HolidayMaster(String companyId, String branchId, String holidayId, String holidayName, Date holidayDate,
			String holidayDay, String createdBy, Date createdDate, String editedBy, Date editedDate, String approvedBy,
			Date approvedDate, char status) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.holidayId = holidayId;
		this.holidayName = holidayName;
		this.holidayDate = holidayDate;
		this.holidayDay = holidayDay;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.status = status;
	}




	@PrePersist
	protected void onCreate() {
		this.createdDate = new Date();
	
		this.approvedDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.editedDate = new Date();

	}

	
}
