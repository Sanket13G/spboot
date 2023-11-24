package com.cwms.entities;

import java.io.Serializable;

public class ProcessNextIdPK implements Serializable {
    private String companyId;
    private String branchId;
    private String finYear;
    private String processId;
    private String nextId;
    
	public ProcessNextIdPK() {
	
	}

	

	public ProcessNextIdPK(String companyId, String branchId, String finYear, String processId, String nextId) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.finYear = finYear;
		this.processId = processId;
		this.nextId = nextId;
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

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getNextId() {
		return nextId;
	}

	public void setNextId(String nextId) {
		this.nextId = nextId;
	}



	@Override
	public String toString() {
		return "ProcessNextIdPK [companyId=" + companyId + ", branchId=" + branchId + ", finYear=" + finYear
				+ ", processId=" + processId + ", nextId=" + nextId + "]";
	}

    // Constructors, equals, hashCode (omitted for brevity)
    // Getters and Setters (omitted for brevity)
	
	
    
}

