package com.cwms.entities;

import java.io.Serializable;

public class ChildMenuId implements Serializable {

	private String Company_Id;
	private String Branch_Id;
	private String Child_Menu_Id;

	public ChildMenuId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChildMenuId(String company_Id, String branch_Id, String child_Menu_Id) {
		super();
		Company_Id = company_Id;
		Branch_Id = branch_Id;
		Child_Menu_Id = child_Menu_Id;
		
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
	public String getChild_Menu_Id() {
		return Child_Menu_Id;
	}
	public void setChild_Menu_Id(String child_Menu_Id) {
		Child_Menu_Id = child_Menu_Id;
	}

	@Override
	public String toString() {
		return "ChildMenuId [Company_Id=" + Company_Id + ", Branch_Id=" + Branch_Id + ", Child_Menu_Id=" + Child_Menu_Id
				+ ", processId=" +  "]";
	}
	
	
}
