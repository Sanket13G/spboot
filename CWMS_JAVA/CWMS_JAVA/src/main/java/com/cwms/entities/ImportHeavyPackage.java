package com.cwms.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="ImportHeavyPackages")
@IdClass(ImportHeavyPackageId.class)
public class ImportHeavyPackage 
{

	
    @Column(name = "Company_Id",length = 6)
    private String companyId;

    
    @Column(name = "Branch_Id",length = 6)
    private String branchId;

 
    @Column(name = "Imp_Trans_Id",length = 10)
    private String impTransId;
  
    @Column(name = "MAWB",length = 20)
    private String mawb;

    @Column(name = "HAWB",length = 20)
    private String hawb;
    
    @Column(name = "SIR_No",length = 10)
    private String sirNo;
    
    @Id
	@Column(name="Package_Number",length=5)
	private String hppackageno;
    
    @Column(name="Weight")
	private BigDecimal hpWeight;

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

	public String getImpTransId() {
		return impTransId;
	}

	public void setImpTransId(String impTransId) {
		this.impTransId = impTransId;
	}

	public String getMawb() {
		return mawb;
	}

	public void setMawb(String mawb) {
		this.mawb = mawb;
	}

	public String getHawb() {
		return hawb;
	}

	public void setHawb(String hawb) {
		this.hawb = hawb;
	}

	public String getSirNo() {
		return sirNo;
	}

	public void setSirNo(String sirNo) {
		this.sirNo = sirNo;
	}

	public String getHppackageno() {
		return hppackageno;
	}

	public void setHppackageno(String hppackageno) {
		this.hppackageno = hppackageno;
	}

	public BigDecimal getHpWeight() {
		return hpWeight;
	}

	public void setHpWeight(BigDecimal hpWeight) {
		this.hpWeight = hpWeight;
	}

	public ImportHeavyPackage(String companyId, String branchId, String impTransId, String mawb, String hawb,
			String sirNo, String hppackageno, BigDecimal hpWeight) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.impTransId = impTransId;
		this.mawb = mawb;
		this.hawb = hawb;
		this.sirNo = sirNo;
		this.hppackageno = hppackageno;
		this.hpWeight = hpWeight;
	}

	public ImportHeavyPackage() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ImportHeavyPackage [companyId=" + companyId + ", branchId=" + branchId + ", impTransId=" + impTransId
				+ ", mawb=" + mawb + ", hawb=" + hawb + ", sirNo=" + sirNo + ", hppackageno=" + hppackageno
				+ ", hpWeight=" + hpWeight + "]";
	}
    
    
    
    
	
}