package com.cwms.entities;

import java.io.Serializable;
import java.util.Objects;

public class CFSTariffRangeId implements Serializable {

	private String companyId;
	private String branchId;
	private String cfsTariffNo;
	private String cfsAmndNo;
	private String serviceId;
	private String partyId;
	private int srlNo;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ServicesId))
			return false;
		ServicesId that = (ServicesId) o;
		return Objects.equals(getServiceId(), that.getService_Id());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getServiceId());
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

	
	public String getCfsTariffNo() {
		return cfsTariffNo;
	}

	public void setCfsTariffNo(String cfsTariffNo) {
		this.cfsTariffNo = cfsTariffNo;
	}

	public String getCfsAmndNo() {
		return cfsAmndNo;
	}

	public void setCfsAmndNo(String cfsAmndNo) {
		this.cfsAmndNo = cfsAmndNo;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public int getSrlNo() {
		return srlNo;
	}

	public void setSrlNo(int srlNo) {
		this.srlNo = srlNo;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	

}