package com.cwms.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@IdClass(CfsTarrifID.class)
@Table(name = "cfstdtrf")
public class CfsTarrif {
    @Id
	@Column(name = "CFS_Tariff_No", length = 10, nullable = false)
    private String cfsTariffNo;
	
    @Column(name = "Company_Id", length = 6, nullable = false)
    private String companyId;


    @Column(name = "Branch_Id", length = 6, nullable = false)
    private String branchId;

    @Column(name = "Party_Name", length = 50)
    private String party_Name;

    @Column(name = "CFS_Amnd_No", length = 3, nullable = false)
    private String cfsAmndNo;

    @Column(name = "Cargo_Movement", length = 1)
    private String cargoMovement;

   
    @Column(name = "CFS_Doc_Ref_No", length = 35, nullable = false)
    private String cfsDocRefNo;

    @Column(name = "Party_Id", length = 6, nullable = false)
    private String partyId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CFS_Tariff_Date")
    private Date cfsTariffDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CFS_Validate_Date")
    private Date cfsValidateDate;

    @Column(name = "Type_Of_Shipment", length = 1)
    private String typeOfShipment;

    @Column(name = "Comments", length = 150)
    private String comments;

    @Column(name = "Created_By", length = 30)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Created_Date")
    private Date createdDate;

    @Column(name = "Edited_By", length = 30)
    private String editedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Edited_Date")
    private Date editedDate;

    @Column(name = "Approved_By", length = 30)
    private String approvedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Approved_Date")
    private Date approvedDate;

    @Column(name = "Status", length = 1)
    private String status;

	public CfsTarrif() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public CfsTarrif(String cfsTariffNo, String companyId, String branchId, String party_Name, String cfsAmndNo,
			String cargoMovement, String cfsDocRefNo, String partyId, Date cfsTariffDate, Date cfsValidateDate,
			String typeOfShipment, String comments, String createdBy, Date createdDate, String editedBy,
			Date editedDate, String approvedBy, Date approvedDate, String status) {
		super();
		this.cfsTariffNo = cfsTariffNo;
		this.companyId = companyId;
		this.branchId = branchId;
		this.party_Name = party_Name;
		this.cfsAmndNo = cfsAmndNo;
		this.cargoMovement = cargoMovement;
		this.cfsDocRefNo = cfsDocRefNo;
		this.partyId = partyId;
		this.cfsTariffDate = cfsTariffDate;
		this.cfsValidateDate = cfsValidateDate;
		this.typeOfShipment = typeOfShipment;
		this.comments = comments;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.status = status;
	}







	public String getParty_Name() {
		return party_Name;
	}



	public void setParty_Name(String party_Name) {
		this.party_Name = party_Name;
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


	public String getCfsTariffNo() {
		return cfsTariffNo;
	}

	public void setCfsTariffNo(String cfsTariffNo) {
		this.cfsTariffNo = cfsTariffNo;
	}

	public String getCfsAmndNo() {
		return cfsAmndNo;
	}

	public void setCfsAmndNo(String cfsAmndNo) {
		this.cfsAmndNo = cfsAmndNo;
	}

	public String getCargoMovement() {
		return cargoMovement;
	}

	public void setCargoMovement(String cargoMovement) {
		this.cargoMovement = cargoMovement;
	}


	public String getCfsDocRefNo() {
		return cfsDocRefNo;
	}

	public void setCfsDocRefNo(String cfsDocRefNo) {
		this.cfsDocRefNo = cfsDocRefNo;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public Date getCfsTariffDate() {
		return cfsTariffDate;
	}

	public void setCfsTariffDate(Date cfsTariffDate) {
		this.cfsTariffDate = cfsTariffDate;
	}

	public Date getCfsValidateDate() {
		return cfsValidateDate;
	}

	public void setCfsValidateDate(Date cfsValidateDate) {
		this.cfsValidateDate = cfsValidateDate;
	}

	public String getTypeOfShipment() {
		return typeOfShipment;
	}

	public void setTypeOfShipment(String typeOfShipment) {
		this.typeOfShipment = typeOfShipment;
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

   
}