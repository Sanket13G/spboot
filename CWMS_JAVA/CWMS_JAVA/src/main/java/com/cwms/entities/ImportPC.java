package com.cwms.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "importpc")
@IdClass(ImportPC_Id.class)
public class ImportPC {

   
	@Id
    @Column(name = "Company_Id",length = 6)
    private String companyId;

    @Id
    @Column(name = "Branch_Id",length = 6)
    private String branchId;
	
	
    @Column(name = "Passport_No",length = 12)
    private String passportNo;

	

    @Column(name = "MAWB",length = 40)
    private String mawb;

    @Column(name = "HAWB",length = 40)
    private String hawb;
    
    @Column(name = "SIR_No",length = 10)
    private String sirNo;
    
    
    @Column(name = "Passenger_Name", length = 50, nullable = false)
    private String passengerName;

    @Column(name = "Address", length = 100)
    private String address;

    @Column(name = "Flight_No", length = 20, nullable = false)
    private String flightNo;

    @Column(name = "Flight_Date")
    @Temporal(TemporalType.DATE)
    private Date flightDate;

    @Column(name = "Nationality", length = 20)
    private String nationality;

    @Column(name = "Deputed_CO_Name", length = 30)
    private String deputedCoName;

    @Column(name = "Deputed_CO_Designation", length = 15)
    private String deputedCoDesignation;

    @Column(name = "Deputed_from_Destination", length = 15)
    private String deputedFromDestination;

    @Column(name = "Deputed_to_Destination", length = 15)
    private String deputedToDestination;

    @Column(name = "Escort_Date")
    @Temporal(TemporalType.DATE)
    private Date escortDate;

    @Column(name = "Approver_Name", length = 50)
    private String approverName;

    @Column(name = "Approver_Designation", length = 15)
    private String approverDesignation;

    @Column(name = "Approver_Date")
    @Temporal(TemporalType.DATE)
    private Date approverDate;

    @Column(name = "Confirmation", length = 1)
    private char confirmation;

    @Column(name = "Status", length = 1)
    private String status;

    @Column(name = "Created_By", length = 30)
    private String createdBy;

    @Column(name = "Created_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "Edited_By", length = 30)
    private String editedBy;

    @Column(name = "Edited_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editedDate;

    @Column(name = "Approved_By", length = 30)
    private String approvedBy;

    @Column(name = "Approved_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedDate;

	public ImportPC() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public String getPassportNo() {
		return passportNo;
	}


	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}


	public ImportPC(String companyId, String branchId, String passportNo, String mawb, String hawb, String sirNo,
			String passengerName, String address, String flightNo, Date flightDate, String nationality,
			String deputedCoName, String deputedCoDesignation, String deputedFromDestination,
			String deputedToDestination, Date escortDate, String approverName, String approverDesignation,
			Date approverDate, char confirmation, String status, String createdBy, Date createdDate, String editedBy,
			Date editedDate, String approvedBy, Date approvedDate) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.passportNo = passportNo;
		this.mawb = mawb;
		this.hawb = hawb;
		this.sirNo = sirNo;
		this.passengerName = passengerName;
		this.address = address;
		this.flightNo = flightNo;
		this.flightDate = flightDate;
		this.nationality = nationality;
		this.deputedCoName = deputedCoName;
		this.deputedCoDesignation = deputedCoDesignation;
		this.deputedFromDestination = deputedFromDestination;
		this.deputedToDestination = deputedToDestination;
		this.escortDate = escortDate;
		this.approverName = approverName;
		this.approverDesignation = approverDesignation;
		this.approverDate = approverDate;
		this.confirmation = confirmation;
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

	

	public String getMawb() {
		return mawb;
	}

	public void setMawb(String mawb) {
		this.mawb = mawb;
	}

	public String getHawb() {
		return hawb;
	}

	public void setHawb(String hawb) {
		this.hawb = hawb;
	}

	public String getSirNo() {
		return sirNo;
	}

	public void setSirNo(String sirNo) {
		this.sirNo = sirNo;
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

	public String getDeputedCoName() {
		return deputedCoName;
	}

	public void setDeputedCoName(String deputedCoName) {
		this.deputedCoName = deputedCoName;
	}

	public String getDeputedCoDesignation() {
		return deputedCoDesignation;
	}

	public void setDeputedCoDesignation(String deputedCoDesignation) {
		this.deputedCoDesignation = deputedCoDesignation;
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

	public Date getEscortDate() {
		return escortDate;
	}

	public void setEscortDate(Date escortDate) {
		this.escortDate = escortDate;
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

	public char getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(char confirmation) {
		this.confirmation = confirmation;
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