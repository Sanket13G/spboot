package com.cwms.entities;

import java.io.Serializable;

public class Import_HistoryId implements Serializable 
{
	private String companyId;
	private String branchId;
	private String mawb;
	private String hawb;
	private String sirNo;
	public Import_HistoryId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Import_HistoryId(String companyId, String branchId, String mawb, String hawb, String sirNo) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.mawb = mawb;
		this.hawb = hawb;
		this.sirNo = sirNo;
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

	
}