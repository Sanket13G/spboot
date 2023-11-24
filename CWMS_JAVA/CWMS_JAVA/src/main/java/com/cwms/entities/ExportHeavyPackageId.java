package com.cwms.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class ExportHeavyPackageId implements Serializable {
	
	private String companyId;
	private String branchId;
	private String sbNo;
	private String sbRequestId;
    private String packageNumber;
	public ExportHeavyPackageId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExportHeavyPackageId(String companyId, String branchId, String sbNo, String sbRequestId,
			String packageNumber) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.sbNo = sbNo;
		this.sbRequestId = sbRequestId;
		this.packageNumber = packageNumber;
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
	public String getSbNo() {
		return sbNo;
	}
	public void setSbNo(String sbNo) {
		this.sbNo = sbNo;
	}
	public String getSbRequestId() {
		return sbRequestId;
	}
	public void setSbRequestId(String sbRequestId) {
		this.sbRequestId = sbRequestId;
	}

	public String getPackageNumber() {
		return packageNumber;
	}

	public void setPackageNumber(String packageNumber) {
		this.packageNumber = packageNumber;
	}

    
    
    
}
