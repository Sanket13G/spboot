package com.cwms.entities;
import java.net.URI;
import java.net.URL;

public class ReadURL {
	private String link;

	private String companyId;

	private String branchId;

	private String createdBy;

	private String editedBy;

	private String approvedBy;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEditedBy() {
		return editedBy;
	}

	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public ReadURL(String link, String companyId, String branchId, String createdBy, String editedBy,
			String approvedBy) {
		super();
		this.link = link;
		this.companyId = companyId;
		this.branchId = branchId;
		this.createdBy = createdBy;
		this.editedBy = editedBy;
		this.approvedBy = approvedBy;
	}

	@Override
	public String toString() {
		return "ReadURL [link=" + link + ", companyId=" + companyId + ", branchId=" + branchId + ", createdBy="
				+ createdBy + ", editedBy=" + editedBy + ", approvedBy=" + approvedBy + "]";
	}

	public ReadURL() {
		super();
	}

}