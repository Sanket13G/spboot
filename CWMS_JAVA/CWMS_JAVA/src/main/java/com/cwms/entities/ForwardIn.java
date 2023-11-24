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
@Table(name = "forward_in")
@IdClass(ForwardId.class)
public class ForwardIn {
	@Id
	@Column(name = "Company_Id",length=6)
	private String companyId;
	@Id
	@Column(name = "Branch_Id",length=6)
	private String branchId;
	@Id
	@Column(name = "ERP_DOC_Ref_No",length=15)
	private String erp_doc_ref_no;
	@Id
	@Column(name = "DOC_Ref_No",length=15)
	private String doc_ref_no;
	@Id
	@Column(name="Package_NO",length=5)
	private String packageNo;
	@Column(name="SIR_No",length=15)
	private String sirNo;
	@Column(name="Type_of_transaction",length=15)
	private String typeOfTransaction;
	@Temporal(TemporalType.DATE)
	@Column(name="SIR_Date")
	private Date sirDate;
	@Column(name="BE_No",length=15)
	private String beNumber;
	@Column(name="Party",length=10)
	private String party;
	@Column(name="NOP",length=3)
	private String nop;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Forward_in_date")
	private Date forwardinDate;
	public ForwardIn() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ForwardIn(String companyId, String branchId, String erp_doc_ref_no, String doc_ref_no, String packageNo,
			String sirNo, String typeOfTransaction, Date sirDate, String beNumber, String party, String nop,
			Date forwardinDate) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.erp_doc_ref_no = erp_doc_ref_no;
		this.doc_ref_no = doc_ref_no;
		this.packageNo = packageNo;
		this.sirNo = sirNo;
		this.typeOfTransaction = typeOfTransaction;
		this.sirDate = sirDate;
		this.beNumber = beNumber;
		this.party = party;
		this.nop = nop;
		this.forwardinDate = forwardinDate;
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
	public String getErp_doc_ref_no() {
		return erp_doc_ref_no;
	}
	public void setErp_doc_ref_no(String erp_doc_ref_no) {
		this.erp_doc_ref_no = erp_doc_ref_no;
	}
	public String getDoc_ref_no() {
		return doc_ref_no;
	}
	public void setDoc_ref_no(String doc_ref_no) {
		this.doc_ref_no = doc_ref_no;
	}
	public String getPackageNo() {
		return packageNo;
	}
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}
	public String getSirNo() {
		return sirNo;
	}
	public void setSirNo(String sirNo) {
		this.sirNo = sirNo;
	}
	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}
	public void setTypeOfTransaction(String typeOfTransaction) {
		this.typeOfTransaction = typeOfTransaction;
	}
	public Date getSirDate() {
		return sirDate;
	}
	public void setSirDate(Date sirDate) {
		this.sirDate = sirDate;
	}
	public String getBeNumber() {
		return beNumber;
	}
	public void setBeNumber(String beNumber) {
		this.beNumber = beNumber;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getNop() {
		return nop;
	}
	public void setNop(String nop) {
		this.nop = nop;
	}
	public Date getForwardinDate() {
		return forwardinDate;
	}
	public void setForwardinDate(Date forwardinDate) {
		this.forwardinDate = forwardinDate;
	}
	
	
	
}
