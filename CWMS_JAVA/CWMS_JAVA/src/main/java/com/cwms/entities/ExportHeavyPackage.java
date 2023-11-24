package com.cwms.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="ExportHeavyPackages")
@IdClass(ExportHeavyPackageId.class)
public class ExportHeavyPackage {
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
	

	@Column(name="Total_Packages",length=5)
	private String totalPackages;
	
	@Id
	@Column(name="Package_Number",length=5)
	private String packageNumber;
	
	@Column(name="Weight")
	private BigDecimal weight;

	public ExportHeavyPackage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExportHeavyPackage(String companyId, String branchId, String sbNo, String sbRequestId, String totalPackages,
			String packageNumber, BigDecimal weight) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.sbNo = sbNo;
		this.sbRequestId = sbRequestId;
		this.totalPackages = totalPackages;
		this.packageNumber = packageNumber;
		this.weight = weight;
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

	public String getTotalPackages() {
		return totalPackages;
	}

	public void setTotalPackages(String totalPackages) {
		this.totalPackages = totalPackages;
	}

	public String getPackageNumber() {
		return packageNumber;
	}

	public void setPackageNumber(String packageNumber) {
		this.packageNumber = packageNumber;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	
}
