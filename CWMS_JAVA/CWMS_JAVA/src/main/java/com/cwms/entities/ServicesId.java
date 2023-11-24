package com.cwms.entities;

import java.io.Serializable;
import java.util.Objects;

public class ServicesId implements Serializable
{
	private String Company_Id;
	private String Service_Id;
	private String Branch_Id;
	
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof ServicesId)) return false;
	        ServicesId that = (ServicesId) o;
	        return Objects.equals(getService_Id(), that.getService_Id());
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(getService_Id());
	    }
	
	
	public ServicesId() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ServicesId(String company_Id, String service_Id, String branch_Id) {
		super();
		Company_Id = company_Id;
		Service_Id = service_Id;
		Branch_Id = branch_Id;
	}

	public String getCompany_Id() {
		return Company_Id;
	}

	public void setCompany_Id(String company_Id) {
		Company_Id = company_Id;
	}

	public String getService_Id() {
		return Service_Id;
	}

	public void setService_Id(String service_Id) {
		Service_Id = service_Id;
	}

	public String getBranch_Id() {
		return Branch_Id;
	}

	public void setBranch_Id(String branch_Id) {
		Branch_Id = branch_Id;
	}
	
	
	

}