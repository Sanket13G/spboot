package com.cwms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="gate_in_out")
@IdClass(Gate_In_OutId.class)
public class Gate_In_Out {
	@Id
	@Column(length = 6)
	private String companyId;
	@Id
	@Column(length = 6)
    private String branchId;
	@Id
	@Column(length = 40)
    private String erp_doc_ref_no;
	@Id
	@Column(length = 40)
    private String doc_ref_no;
	@Id
	@Column(length = 15)
    private String sr_No;
	
	private int nop;
	@Column(length=1)
	private String dgdc_seepz_in_scan;
	@Column(length=1)
	private String dgdc_seepz_out_scan;
	@Column(length=1)
	private String dgdc_cargo_in_scan;
	@Column(length=1)
	private String dgdc_cargo_out_scan;
	public Gate_In_Out() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Gate_In_Out(String companyId, String branchId, String erp_doc_ref_no, String doc_ref_no, String sr_No,
			int nop, String dgdc_seepz_in_scan, String dgdc_seepz_out_scan, String dgdc_cargo_in_scan,
			String dgdc_cargo_out_scan) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.erp_doc_ref_no = erp_doc_ref_no;
		this.doc_ref_no = doc_ref_no;
		this.sr_No = sr_No;
		this.nop = nop;
		this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
		this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
		this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
		this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
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
	public int getNop() {
		return nop;
	}
	public void setNop(int nop) {
		this.nop = nop;
	}
	public String getDgdc_seepz_in_scan() {
		return dgdc_seepz_in_scan;
	}
	public void setDgdc_seepz_in_scan(String dgdc_seepz_in_scan) {
		this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
	}
	public String getDgdc_seepz_out_scan() {
		return dgdc_seepz_out_scan;
	}
	public void setDgdc_seepz_out_scan(String dgdc_seepz_out_scan) {
		this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
	}
	public String getDgdc_cargo_in_scan() {
		return dgdc_cargo_in_scan;
	}
	public void setDgdc_cargo_in_scan(String dgdc_cargo_in_scan) {
		this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
	}
	public String getDgdc_cargo_out_scan() {
		return dgdc_cargo_out_scan;
	}
	public void setDgdc_cargo_out_scan(String dgdc_cargo_out_scan) {
		this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
	}
	
	
	
}
