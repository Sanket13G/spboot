package com.cwms.entities;

import java.io.Serializable;

public class ExportPcId implements Serializable {
  
	private String companyId;
	private String branchId;
	private String serNo;
	private String sbNo;
	private String sbRequestId;
	public ExportPcId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExportPcId(String companyId, String branchId, String serNo, String sbNo, String sbRequestId) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.serNo = serNo;
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
	public String getSerNo() {
		return serNo;
	}
	public void setSerNo(String serNo) {
		this.serNo = serNo;
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
	@Override
	public String toString() {
		return "ExportPcId [companyId=" + companyId + ", branchId=" + branchId + ", serNo=" + serNo + ", sbNo=" + sbNo
				+ ", sbRequestId=" + sbRequestId + "]";
	}

	
	
}
