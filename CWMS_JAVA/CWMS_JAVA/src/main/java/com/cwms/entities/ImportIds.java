package com.cwms.entities;

import java.io.Serializable;

public class ImportIds implements Serializable
{

	 private String companyId;
	 private String branchId;
	 private String impTransId;
	 private String mawb;
//	 private String hawb;
	 private String sirNo;
	public ImportIds(String companyId, String branchId, String impTransId, String mawb, String sir_No) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.impTransId = impTransId;
		this.mawb = mawb;
//		this.hawb = hawb;
		this.sirNo = sir_No;
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
	public String getImpTransId() {
		return impTransId;
	}
	public void setImpTransId(String impTransId) {
		this.impTransId = impTransId;
	}
	public String getMawb() {
		return mawb;
	}
	public void setMawb(String mawb) {
		this.mawb = mawb;
	}
	
	public String getSir_No() {
		return sirNo;
	}
	public void setSir_No(String sir_No) {
		this.sirNo = sir_No;
	}
	
	public ImportIds() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	 
}