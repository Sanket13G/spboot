package com.cwms.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class JarId   implements Serializable{
	@Id
	@Column(name = "Company_Id", length = 6, nullable = false)
	private String companyId;

	@Id
	@Column(name = "Jar_Id", length = 6, nullable = false)
	private String jarId;

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

	public JarId(String companyId, String jarId) {
		super();
		this.companyId = companyId;
		this.jarId = jarId;
	}

	public JarId() {
		super();
	}

	

}