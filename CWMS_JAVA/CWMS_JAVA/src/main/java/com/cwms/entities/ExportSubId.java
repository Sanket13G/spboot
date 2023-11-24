package com.cwms.entities;

import java.io.Serializable;

public class ExportSubId implements Serializable {
	 private String companyId;
	    private String branchId;
	    private String expSubId;
	    private String requestId;
		public ExportSubId() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ExportSubId(String companyId, String branchId, String expSubId, String requestId) {
			super();
			this.companyId = companyId;
			this.branchId = branchId;
			this.expSubId = expSubId;
			this.requestId = requestId;
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
		public String getExpSubId() {
			return expSubId;
		}
		public void setExpSubId(String expSubId) {
			this.expSubId = expSubId;
		}
		public String getRequestId() {
			return requestId;
		}
		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}
		@Override
		public String toString() {
			return "ExportSubId [companyId=" + companyId + ", branchId=" + branchId + ", expSubId=" + expSubId
					+ ", requestId=" + requestId + "]";
		}
		
}
