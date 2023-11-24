package com.cwms.entities;

import java.io.Serializable;

public class DetentionId implements Serializable {

	private long siNo;
	private String companyId;
	private String branchId;
	private String detentionId;
	private String fileNo;
	
	public DetentionId() {
		super();
	}

	public DetentionId(long siNo, String companyId, String branchId, String detentionId, String fileNo) {
		super();
		this.siNo = siNo;
		this.companyId = companyId;
		this.branchId = branchId;
		this.detentionId = detentionId;
		this.fileNo = fileNo;
	}

	public long getSiNo() {
		return siNo;
	}

	public void setSiNo(long siNo) {
		this.siNo = siNo;
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

	public String getDetentionId() {
		return detentionId;
	}

	public void setDetentionId(String detentionId) {
		this.detentionId = detentionId;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	
	
}
