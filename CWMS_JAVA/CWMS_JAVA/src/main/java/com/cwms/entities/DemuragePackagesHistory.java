package com.cwms.entities;
import java.util.Date;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name="Demurage_History")
public class DemuragePackagesHistory
{
	@Id
    @GeneratedValue
    private UUID srId;
    private String companyId;
    private String inviceNo;
    private String billNo;
	private String branchId; 	
	private String partyId;
	private String MasterNo;
	private String SubMasterNo;
	@Temporal(TemporalType.DATE)
	private Date inDate;
	@Temporal(TemporalType.DATE)
	private Date outDate;
	private int packages; 
	private double demurageRate;
	private String packageType;
	@Temporal(TemporalType.DATE)
	private Date inviceDate;
		
	
	
	
	
	
	
	public DemuragePackagesHistory(UUID srId, String companyId, String inviceNo, String billNo, String branchId,
			String partyId, String masterNo, String subMasterNo, Date inDate, Date outDate, int packages,
			double demurageRate, String packageType, Date inviceDate) {
		super();
		this.srId = srId;
		this.companyId = companyId;
		this.inviceNo = inviceNo;
		this.billNo = billNo;
		this.branchId = branchId;
		this.partyId = partyId;
		MasterNo = masterNo;
		SubMasterNo = subMasterNo;
		this.inDate = inDate;
		this.outDate = outDate;
		this.packages = packages;
		this.demurageRate = demurageRate;
		this.packageType = packageType;
		this.inviceDate = inviceDate;
	}
	public String getInviceNo() {
		return inviceNo;
	}
	public void setInviceNo(String inviceNo) {
		this.inviceNo = inviceNo;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getMasterNo() {
		return MasterNo;
	}
	public void setMasterNo(String masterNo) {
		MasterNo = masterNo;
	}
	public String getSubMasterNo() {
		return SubMasterNo;
	}
	public void setSubMasterNo(String subMasterNo) {
		SubMasterNo = subMasterNo;
	}
	public Date getInviceDate() {
		return inviceDate;
	}
	public void setInviceDate(Date inviceDate) {
		this.inviceDate = inviceDate;
	}
	public double getDemurageRate() {
		return demurageRate;
	}
	public void setDemurageRate(double demurageRate) {
		this.demurageRate = demurageRate;
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
	
	
	public DemuragePackagesHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UUID getSrId() {
		return srId;
	}
	public void setSrId(UUID srId) {
		this.srId = srId;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public int getPackages() {
		return packages;
	}
	public void setPackages(int packages) {
		this.packages = packages;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	
	
	
	
	
	

}
