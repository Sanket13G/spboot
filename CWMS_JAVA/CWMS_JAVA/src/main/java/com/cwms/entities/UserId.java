package com.cwms.entities;

import java.io.Serializable;
import jakarta.persistence.*;

//@Embeddable
public class UserId implements Serializable {
	//@Column(length = 6)
	private String Company_Id;
//	@Column(length = 10)
	private String User_Id;
	
	private String Branch_Id;
	
	

	public UserId() {
		super();
		// TODO Auto-generated constructor stub
	}



	public UserId(String company_Id, String user_Id, String branch_Id) {
		super();
		Company_Id = company_Id;
		User_Id = user_Id;
		Branch_Id = branch_Id;
	}



	public String getCompany_Id() {
		return Company_Id;
	}



	public void setCompany_Id(String company_Id) {
		Company_Id = company_Id;
	}



	public String getUser_Id() {
		return User_Id;
	}



	public void setUser_Id(String user_Id) {
		User_Id = user_Id;
	}



	public String getBranch_Id() {
		return Branch_Id;
	}



	public void setBranch_Id(String branch_Id) {
		Branch_Id = branch_Id;
	}



	@Override
	public String toString() {
		return "Usercompany [Company_Id=" + Company_Id + ", User_Id=" + User_Id + ", Branch_Id=" + Branch_Id + "]";
	}

}
