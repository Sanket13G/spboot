package com.cwms.entities;

import java.io.Serializable;

public class ImportSubId implements Serializable {
	private String companyId;
	 private String branchId;
	 private String impSubId;
	 private String requestId;
	public ImportSubId(String companyId, String branchId, String impSubId, String requestId) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.impSubId = impSubId;
		this.requestId = requestId;
	}
	public ImportSubId() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getImpSubId() {
		return impSubId;
	}
	public void setImpSubId(String impSubId) {
		this.impSubId = impSubId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	@Override
	public String toString() {
		return "ImportSubId [companyId=" + companyId + ", branchId=" + branchId + ", impSubId=" + impSubId
				+ ", requestId=" + requestId + "]";
	}
	 
	 
	 
}
