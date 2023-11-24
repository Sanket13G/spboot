package com.cwms.entities;

import java.io.Serializable;

public class ExternalParty_Id implements Serializable {
	private String externaluserId;
	
    private String companyId;

    private String branchId;

	public ExternalParty_Id() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getExternaluserId() {
		return externaluserId;
	}



	public void setExternaluserId(String externaluserId) {
		this.externaluserId = externaluserId;
	}



	public ExternalParty_Id(String externaluserId, String companyId, String branchId) {
		super();
		this.externaluserId = externaluserId;
		this.companyId = companyId;
		this.branchId = branchId;
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
}
