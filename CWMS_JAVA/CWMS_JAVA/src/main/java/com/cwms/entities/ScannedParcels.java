package com.cwms.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="scanned_parcels")
public class ScannedParcels {
 
	@Id
	@Column(name = "Scanned_Id")
	@GeneratedValue(strategy =GenerationType.AUTO)
	public Long ScannedId;
	
	@Column(name="Company_Id",length = 6)
	public String companyId;
	
	@Column(name="Branch_Id",length=6)
	public String branchId;
	
	@Column(name="Type_Of_Transaction",length=30)
	public String typeOfTransaction;
	
	@Column(name="SR_No",length=15)
	public String srNo;
	
	@Column(name="Parcel_Type",length = 10)
	public String parcel_type;
	
	@Column(name="Doc_Ref_No",length=20)
	public String doc_Ref_No;
	
	@Column(name="Party",length=10)
	public String party;

	public int nop;
	
	@Column(length=5)
	public String packnum;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date gateiout;
	
	@Column(name="Scan_Parcel_Type",length=15)
	public String scan_parcel_type;
	
	@Column(length=1)
	public String status;
	
	

	public ScannedParcels() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getScan_parcel_type() {
		return scan_parcel_type;
	}



	public void setScan_parcel_type(String scan_parcel_type) {
		this.scan_parcel_type = scan_parcel_type;
	}







	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getPacknum() {
		return packnum;
	}



	public void setPacknum(String packnum) {
		this.packnum = packnum;
	}





	public ScannedParcels(Long scannedId, String companyId, String branchId, String typeOfTransaction, String srNo,
			String parcel_type, String doc_Ref_No, String party, int nop, String packnum, Date gateiout,
			String scan_parcel_type, String status) {
		super();
		ScannedId = scannedId;
		this.companyId = companyId;
		this.branchId = branchId;
		this.typeOfTransaction = typeOfTransaction;
		this.srNo = srNo;
		this.parcel_type = parcel_type;
		this.doc_Ref_No = doc_Ref_No;
		this.party = party;
		this.nop = nop;
		this.packnum = packnum;
		this.gateiout = gateiout;
		this.scan_parcel_type = scan_parcel_type;
		this.status = status;
	}



	public Long getScannedId() {
		return ScannedId;
	}

	public void setScannedId(Long scannedId) {
		ScannedId = scannedId;
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

	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}

	public void setTypeOfTransaction(String typeOfTransaction) {
		this.typeOfTransaction = typeOfTransaction;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getParcel_type() {
		return parcel_type;
	}

	public void setParcel_type(String parcel_type) {
		this.parcel_type = parcel_type;
	}

	public String getDoc_Ref_No() {
		return doc_Ref_No;
	}

	public void setDoc_Ref_No(String doc_Ref_No) {
		this.doc_Ref_No = doc_Ref_No;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public int getNop() {
		return nop;
	}

	public void setNop(int nop) {
		this.nop = nop;
	}


	public Date getGateiout() {
		return gateiout;
	}

	public void setGateiout(Date gateiout) {
		this.gateiout = gateiout;
	}
	
	
	
}
