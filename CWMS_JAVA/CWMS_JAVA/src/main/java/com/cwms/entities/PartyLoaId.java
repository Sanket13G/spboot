package com.cwms.entities;

import java.io.Serializable;

public class PartyLoaId implements Serializable{
	
	private String companyId;
    private String branchId ;
    private String partyId;
    private String loaNumber;
	private int loaSerId;
	public PartyLoaId(String companyId, String branchId, String partyId, String loaNumber, int loaSerId) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.partyId = partyId;
		this.loaNumber = loaNumber;
		this.loaSerId = loaSerId;
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
	public String getLoaNumber() {
		return loaNumber;
	}
	public void setLoaNumber(String loaNumber) {
		this.loaNumber = loaNumber;
	}
	public int getLoaSerId() {
		return loaSerId;
	}
	public void setLoaSerId(int loaSerId) {
		this.loaSerId = loaSerId;
	}
	public PartyLoaId() {
		super();
	}
		
	

}