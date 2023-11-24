package com.cwms.entities;

import java.io.Serializable;

public class ForwardId implements Serializable {

	 private String companyId;
	    private String branchId;
	    private String erp_doc_ref_no;
	    private String doc_ref_no;
	    private String packageNo;
		public ForwardId() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ForwardId(String companyId, String branchId, String erp_doc_ref_no, String doc_ref_no,
				String packageNo) {
			super();
			this.companyId = companyId;
			this.branchId = branchId;
			this.erp_doc_ref_no = erp_doc_ref_no;
			this.doc_ref_no = doc_ref_no;
			this.packageNo = packageNo;
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
		public String getErp_doc_ref_no() {
			return erp_doc_ref_no;
		}
		public void setErp_doc_ref_no(String erp_doc_ref_no) {
			this.erp_doc_ref_no = erp_doc_ref_no;
		}
		public String getDoc_ref_no() {
			return doc_ref_no;
		}
		public void setDoc_ref_no(String doc_ref_no) {
			this.doc_ref_no = doc_ref_no;
		}
		public String getPackageNo() {
			return packageNo;
		}
		public void setPackageNo(String packageNo) {
			this.packageNo = packageNo;
		}
		
		
		
	    
}
