package com.cwms.entities;

public class ImportHeavyPackageId 
{
	private String companyId;
	private String branchId;
	 private String impTransId;
	private String sirNo;
	private String mawb;
	private String hawb;
	private String hppackageno;
	
	public ImportHeavyPackageId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ImportHeavyPackageId(String companyId, String branchId, String impTransId, String sirNo, String mawb,
			String hawb, String hppackageno) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.impTransId = impTransId;
		this.sirNo = sirNo;
		this.mawb = mawb;
		this.hawb = hawb;
		this.hppackageno = hppackageno;
	}

	public String getHppackageno() {
		return hppackageno;
	}

	public void setHppackageno(String hppackageno) {
		this.hppackageno = hppackageno;
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
	public String getSirNo() {
		return sirNo;
	}
	public void setSirNo(String sirNo) {
		this.sirNo = sirNo;
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
	
	
	
	
}