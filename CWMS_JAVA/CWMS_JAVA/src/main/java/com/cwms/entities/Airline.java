package com.cwms.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(AirlineId.class)
public class Airline implements Serializable {

	@Id
	private String companyId;

	@Id
	private String branchId;


	private String airlineName;

	@Id
	private String flightNo;

	private String airlineShortName;
	private String airlineDesc;
	private String airlineCode;
	private String status;
	private String createdBy;
	private Date createdDate;
	private String editedBy;
	private Date editedDate;
	private String approvedBy;
	private Date approvedDate;

	public Airline() {
		super();
	}

	public Airline(String companyId, String branchId, String airlineName, String flightNo, String airlineShortName,
			String airlineDesc, String airlineCode, String status, String createdBy, Date createdDate, String editedBy,
			Date editedDate, String approvedBy, Date approvedDate) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.airlineName = airlineName;
		this.flightNo = flightNo;
		this.airlineShortName = airlineShortName;
		this.airlineDesc = airlineDesc;
		this.airlineCode = airlineCode;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
	}

	// Constructors, getters, and setters (omitted for brevity)

	@Override
	public String toString() {
		return "Airline [companyId=" + companyId + ", branchId=" + branchId + ", airlineName=" + airlineName
				+ ", flightNo=" + flightNo + ", airlineShortName=" + airlineShortName + ", airlineDesc=" + airlineDesc
				+ ", airlineCode=" + airlineCode + ", status=" + status + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", editedBy=" + editedBy + ", editedDate=" + editedDate + ", approvedBy=" + approvedBy
				+ ", approvedDate=" + approvedDate + "]";
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

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getflightNo() {
		return flightNo;
	}

	public void setflightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getAirlineShortName() {
		return airlineShortName;
	}

	public void setAirlineShortName(String airlineShortName) {
		this.airlineShortName = airlineShortName;
	}

	public String getAirlineDesc() {
		return airlineDesc;
	}

	public void setAirlineDesc(String airlineDesc) {
		this.airlineDesc = airlineDesc;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
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

	public void setCurrentSystemDate() {
		Date currentDate = new Date();
		if (createdDate == null) {
			createdDate = currentDate;
		}
		if (approvedDate == null) {
			approvedDate = currentDate;
		}
		if (editedDate == null) {
			editedDate = currentDate;
		}
		// You can set other fields like editedDate similarly if needed
	}
}
