package com.cwms.entities;

import java.io.Serializable;

public class ParentMenuId implements Serializable {
	private String Company_Id;
	private String Branch_Id;
	private String PMenu_Id;

	public ParentMenuId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ParentMenuId(String company_Id, String branch_Id, String pMenu_Id) {
		super();
		Company_Id = company_Id;
		Branch_Id = branch_Id;
		PMenu_Id = pMenu_Id;
	
	}
	public String getCompany_Id() {
		return Company_Id;
	}
	public void setCompany_Id(String company_Id) {
		Company_Id = company_Id;
	}
	public String getBranch_Id() {
		return Branch_Id;
	}
	public void setBranch_Id(String branch_Id) {
		Branch_Id = branch_Id;
	}
	public String getPMenu_Id() {
		return PMenu_Id;
	}
	public void setPMenu_Id(String pMenu_Id) {
		PMenu_Id = pMenu_Id;
	}

	@Override
	public String toString() {
		return "ParentMenuId [Company_Id=" + Company_Id + ", Branch_Id=" + Branch_Id + ", PMenu_Id=" + PMenu_Id
				+ ", Process_Id=" +  "]";
	}
	
}
