package com.cwms.entities;

import jakarta.persistence.*;
@Entity
@Table(name="imp_dgdc_delivery_Status")
public class ImportDgdcDeliveryStatus {
	
	@Id
	@GeneratedValue
	@Column(name = "Sr_no",length = 5)
	private Long srno;
		
    @Column(name = "Company_Id",length = 6)
    private String companyId;
   
    @Column(name = "Branch_Id",length = 6)
    private String branchId;

    
    @Column(name = "dgdc_Delivery_Staus",length = 50)
    private String dgdcDeliveryStaus;


	public ImportDgdcDeliveryStatus() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ImportDgdcDeliveryStatus(Long srno, String companyId, String branchId, String dgdcDeliveryStaus) {
		super();
		this.srno = srno;
		this.companyId = companyId;
		this.branchId = branchId;
		this.dgdcDeliveryStaus = dgdcDeliveryStaus;
	}


	public Long getSrno() {
		return srno;
	}


	public void setSrno(Long srno) {
		this.srno = srno;
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


	public String getDgdcDeliveryStaus() {
		return dgdcDeliveryStaus;
	}


	public void setDgdcDeliveryStaus(String dgdcDeliveryStaus) {
		this.dgdcDeliveryStaus = dgdcDeliveryStaus;
	}


	@Override
	public String toString() {
		return "ImportDgdcDeliveryStatus [srno=" + srno + ", companyId=" + companyId + ", branchId=" + branchId
				+ ", dgdcDeliveryStaus=" + dgdcDeliveryStaus + "]";
	}
    
    
    

}
