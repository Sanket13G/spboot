package com.cwms.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class BranchId implements Serializable {
    @Column(name = "Company_Id",length = 6)
    private String companyId;
    
    @Column(name = "Branch_Id",length = 6)
    private String branchId;

    public BranchId() {
        // Default constructor required for JPA
    }

    public BranchId(String companyId, String branchId) {
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

	@Override
	public String toString() {
		return "BranchCompany [companyId=" + companyId + ", branchId=" + branchId + "]";
	}
    
}
