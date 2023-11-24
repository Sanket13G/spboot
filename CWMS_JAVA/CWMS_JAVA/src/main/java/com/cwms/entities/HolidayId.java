package com.cwms.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;


public class HolidayId implements Serializable {

//	private static final long serialVersionUID = 1L;
	private String companyId;
    private String branchId;
    private String holidayId;
    
	public HolidayId() {
		
	}

	public HolidayId(String companyId, String branchId, String holidayId) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.holidayId = holidayId;
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

	public String getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(String holidayId) {
		this.holidayId = holidayId;
	}
	
    
    
    
	
}
