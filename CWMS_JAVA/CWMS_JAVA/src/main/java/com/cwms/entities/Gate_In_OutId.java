package com.cwms.entities;

import java.io.Serializable;

public class Gate_In_OutId implements Serializable {
	private String companyId;
    private String branchId;
    private String erp_doc_ref_no;
    private String doc_ref_no;
    private String sr_No;
	public Gate_In_OutId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Gate_In_OutId(String companyId, String branchId, String erp_doc_ref_no, String doc_ref_no, String sr_No) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.erp_doc_ref_no = erp_doc_ref_no;
		this.doc_ref_no = doc_ref_no;
		this.sr_No = sr_No;
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
	public String getSr_No() {
		return sr_No;
	}
	public void setSr_No(String sr_No) {
		this.sr_No = sr_No;
	}
    
    
}
