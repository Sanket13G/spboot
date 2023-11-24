package com.cwms.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class JarDetailId implements Serializable {

	@Id
	@Column(name = "Jar_Dtl_Id")
	private String jarDtlId;
	@Id
	@Column(name = "Company_Id")
	private String companyId;
	@Id
	@Column(name = "Jar_Id")
	private String jarId;
	
	public String getJarDtlId() {
		return jarDtlId;
	}
	public void setJarDtlId(String jarDtlId) {
		this.jarDtlId = jarDtlId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getJarId() {
		return jarId;
	}
	public void setJarId(String jarId) {
		this.jarId = jarId;
	}
	public JarDetailId(String jarDtlId, String companyId, String jarId) {
		super();
		this.jarDtlId = jarDtlId;
		this.companyId = companyId;
		this.jarId = jarId;
	}
	public JarDetailId() {
		super();
	}
	@Override
	public String toString() {
		return "JarDetailId [jarDtlId=" + jarDtlId + ", companyId=" + companyId + ", jarId=" + jarId + "]";
	}

	
}