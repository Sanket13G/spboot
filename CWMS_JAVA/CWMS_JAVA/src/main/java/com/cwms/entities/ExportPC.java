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
@Table(name="exportpc")
@IdClass(ExportPcId.class)
public class ExportPC {
	@Id
	@Column(name = "Company_Id", length = 6)
	private String companyId;

	@Id
	@Column(name = "Branch_Id", length = 6)
	private String branchId;

	@Id
	@Column(name = "SB_Number", length = 15)
	private String sbNo;

	@Id
	@Column(name = "SB_Request_Id", length = 20)
	private String sbRequestId;
	
	@Id
	@Column(name = "SER_No", length = 20)
	private String serNo;
	
	@Column(name="Passenger_Name",length=50)
	private String passengerName;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Passport_No",length=20)
	private String passportNo;
	
	@Column(name="Flight_No",length=20)
	private String flightNo;
	
	@Column(name = "Flight_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date flightDate;
	
	@Column(name="Nationality",length=20)
	private String nationality;
	
	@Column(name="Deputed_Customs_Officer_Name",length=30)
	private String deputedCustomsOfficerName;
	
	@Column(name="Deputed_Customs_Officer_Designation",length=30)
	private String deputedCustomsOfficerDesignation;
	
	@Column(name="Deputed_From_Destination",length=30)
	private String deputedFromDestination;
	
	@Column(name="Deputed_To_Destination",length=30)
	private String deputedToDestination;
	
	@Column(name = "Date_Of_Escort")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfEscort;
	
	@Column(name="Approver_Name",length=30)
	private String approverName;
	
	@Column(name="Approver_Designation",length=30)
	private String approverDesignation;
	
	
	@Column(name = "Approver_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date approverDate;
	
	@Column(name = "Status", length = 1)
	private String status;

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

	@Column(name = "Approved_By")
	private String approvedBy;

	@Column(name = "Approved_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	public ExportPC() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExportPC(String companyId, String branchId, String sbNo, String sbRequestId, String serNo,
			String passengerName, String address, String passportNo, String flightNo, Date flightDate,
			String nationality, String deputedCustomsOfficerName, String deputedCustomsOfficerDesignation,
			String deputedFromDestination, String deputedToDestination, Date dateOfEscort, String approverName,
			String approverDesignation, Date approverDate, String status, String createdBy, Date createdDate,
			String editedBy, Date editedDate, String approvedBy, Date approvedDate) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.sbNo = sbNo;
		this.sbRequestId = sbRequestId;
		this.serNo = serNo;
		this.passengerName = passengerName;
		this.address = address;
		this.passportNo = passportNo;
		this.flightNo = flightNo;
		this.flightDate = flightDate;
		this.nationality = nationality;
		this.deputedCustomsOfficerName = deputedCustomsOfficerName;
		this.deputedCustomsOfficerDesignation = deputedCustomsOfficerDesignation;
		this.deputedFromDestination = deputedFromDestination;
		this.deputedToDestination = deputedToDestination;
		this.dateOfEscort = dateOfEscort;
		this.approverName = approverName;
		this.approverDesignation = approverDesignation;
		this.approverDate = approverDate;
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

	public String getSerNo() {
		return serNo;
	}

	public void setSerNo(String serNo) {
		this.serNo = serNo;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDeputedCustomsOfficerName() {
		return deputedCustomsOfficerName;
	}

	public void setDeputedCustomsOfficerName(String deputedCustomsOfficerName) {
		this.deputedCustomsOfficerName = deputedCustomsOfficerName;
	}

	public String getDeputedCustomsOfficerDesignation() {
		return deputedCustomsOfficerDesignation;
	}

	public void setDeputedCustomsOfficerDesignation(String deputedCustomsOfficerDesignation) {
		this.deputedCustomsOfficerDesignation = deputedCustomsOfficerDesignation;
	}

	public String getDeputedFromDestination() {
		return deputedFromDestination;
	}

	public void setDeputedFromDestination(String deputedFromDestination) {
		this.deputedFromDestination = deputedFromDestination;
	}

	public String getDeputedToDestination() {
		return deputedToDestination;
	}

	public void setDeputedToDestination(String deputedToDestination) {
		this.deputedToDestination = deputedToDestination;
	}

	public Date getDateOfEscort() {
		return dateOfEscort;
	}

	public void setDateOfEscort(Date dateOfEscort) {
		this.dateOfEscort = dateOfEscort;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getApproverDesignation() {
		return approverDesignation;
	}

	public void setApproverDesignation(String approverDesignation) {
		this.approverDesignation = approverDesignation;
	}

	public Date getApproverDate() {
		return approverDate;
	}

	public void setApproverDate(Date approverDate) {
		this.approverDate = approverDate;
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

	@Override
	public String toString() {
		return "ExportPC [companyId=" + companyId + ", branchId=" + branchId + ", sbNo=" + sbNo + ", sbRequestId="
				+ sbRequestId + ", serNo=" + serNo + ", passengerName=" + passengerName + ", address=" + address
				+ ", passportNo=" + passportNo + ", flightNo=" + flightNo + ", flightDate=" + flightDate
				+ ", nationality=" + nationality + ", deputedCustomsOfficerName=" + deputedCustomsOfficerName
				+ ", deputedCustomsOfficerDesignation=" + deputedCustomsOfficerDesignation + ", deputedFromDestination="
				+ deputedFromDestination + ", deputedToDestination=" + deputedToDestination + ", dateOfEscort="
				+ dateOfEscort + ", approverName=" + approverName + ", approverDesignation=" + approverDesignation
				+ ", approverDate=" + approverDate + ", status=" + status + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", editedBy=" + editedBy + ", editedDate=" + editedDate
				+ ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + "]";
	}
	
	
	
}
