package com.cwms.entities;
import java.io.Serializable;

public class PartyRepresentativeId implements Serializable{

	private String companyId;
    private String branchId;
    private String partyId;
    private String  partyRepresentativeId ;
    
	public PartyRepresentativeId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PartyRepresentativeId(String companyId, String branchId, String partyId, String partyRepresentativeId) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.partyId = partyId;
		this.partyRepresentativeId = partyRepresentativeId;
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

	public String getPartyRepresentativeId() {
		return partyRepresentativeId;
	}

	public void setPartyRepresentativeId(String partyRepresentativeId) {
		this.partyRepresentativeId = partyRepresentativeId;
	}
    
    
}
