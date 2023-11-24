package com.cwms.entities;

import java.io.Serializable;

public class RepresentPartyID implements Serializable {
	private String companyId;
	private String branchId;
	private String userId;
	private String representativeId;
	public RepresentPartyID() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RepresentPartyID(String companyId, String branchId, String userId, String representativeId) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.userId = userId;
		this.representativeId = representativeId;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRepresentativeId() {
		return representativeId;
	}
	public void setRepresentativeId(String representativeId) {
		this.representativeId = representativeId;
	}

	
}