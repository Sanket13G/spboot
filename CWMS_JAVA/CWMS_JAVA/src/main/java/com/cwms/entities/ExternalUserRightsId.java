package com.cwms.entities;

import java.io.Serializable;

public class ExternalUserRightsId implements Serializable {
	 private String company_Id;
	    private String user_Id;
	    private String process_Id;
	    private String branch_Id;
		public ExternalUserRightsId() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ExternalUserRightsId(String company_Id, String user_Id, String process_Id, String branch_Id) {
			super();
			this.company_Id = company_Id;
			this.user_Id = user_Id;
			this.process_Id = process_Id;
			this.branch_Id = branch_Id;
		}
		public String getCompany_Id() {
			return company_Id;
		}
		public void setCompany_Id(String company_Id) {
			this.company_Id = company_Id;
		}
		public String getUser_Id() {
			return user_Id;
		}
		public void setUser_Id(String user_Id) {
			this.user_Id = user_Id;
		}
		public String getProcess_Id() {
			return process_Id;
		}
		public void setProcess_Id(String process_Id) {
			this.process_Id = process_Id;
		}
		public String getBranch_Id() {
			return branch_Id;
		}
		public void setBranch_Id(String branch_Id) {
			this.branch_Id = branch_Id;
		}
		@Override
		public String toString() {
			return "UserRightsId [company_Id=" + company_Id + ", user_Id=" + user_Id + ", process_Id=" + process_Id
					+ ", branch_Id=" + branch_Id + "]";
		}
	    
	    

}
