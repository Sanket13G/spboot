package com.cwms.entities;

import java.io.Serializable;

public class DefaultPartyDetailsId implements Serializable {
	private String companyId;
	private String branchId;
	private String useId;
	public DefaultPartyDetailsId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DefaultPartyDetailsId(String companyId, String branchId, String useId) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.useId = useId;
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
	public String getUseId() {
		return useId;
	}
	public void setUseId(String useId) {
		this.useId = useId;
	}
	@Override
	public String toString() {
		return "DefaultPartyDetailsId [companyId=" + companyId + ", branchId=" + branchId + ", useId=" + useId + "]";
	}
	
	
}
