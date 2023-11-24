package com.cwms.entities;

import java.io.Serializable;
import java.util.Objects;

public class PartyId implements Serializable {

//    private static final long serialVersionUID = 1L;
	private String companyId;
    private String branchId ;
    private String partyId   ;
    
	public PartyId() {
		super();
	}

	public PartyId(String companyId, String branchId, String partyId) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.partyId = partyId;
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

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

   
}

