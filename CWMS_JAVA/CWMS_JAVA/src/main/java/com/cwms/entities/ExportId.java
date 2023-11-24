package com.cwms.entities;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class ExportId implements Serializable {
	@Id
	@Column(name = "Company_Id", length = 6)
	private String companyId;

	@Id
	@Column(name = "Branch_Id", length = 6)
	private String branchId;

	@Id
	@Column(name = "SB_Number", length = 15)
	private String sbNo;

	@Id
	@Column(name = "SB_Request_Id", length = 20)
	private String sbRequestId;

	@Override
	public String toString() {
		return "SBTransactionId [companyId=" + companyId + ", branchId=" + branchId + ", sbNo=" + sbNo + ", sbRequestId="
				+ sbRequestId + "]";
	}
	

	public ExportId() {
		super();
	}


	public ExportId(String companyId, String branchId, String sbNo, String sbRequestId) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.sbNo = sbNo;
		this.sbRequestId = sbRequestId;
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

	public String getsbRequestId() {
		return sbRequestId;
	}

	public void setsbRequestId(String sbRequestId) {
		this.sbRequestId = sbRequestId;
	}

	
	
}
