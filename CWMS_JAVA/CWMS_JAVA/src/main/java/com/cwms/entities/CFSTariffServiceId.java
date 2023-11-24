package com.cwms.entities;

import java.io.Serializable;
import java.util.Objects;

public class CFSTariffServiceId implements Serializable {

	private String companyId;
	private String branchId;
	private String serviceId;
	private String cfsTariffNo;
	private String cfsAmndNo;
	private int srNo;
	private String partyId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CFSTariffServiceId that = (CFSTariffServiceId) o;
		return srNo == that.srNo && Objects.equals(companyId, that.companyId)
				&& Objects.equals(branchId, that.branchId) && Objects.equals(serviceId, that.serviceId)
			    && Objects.equals(cfsTariffNo, that.cfsTariffNo)
			    && Objects.equals(partyId, that.partyId)
				&& Objects.equals(cfsAmndNo, that.cfsAmndNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(companyId, branchId,partyId, serviceId, cfsTariffNo, cfsAmndNo, srNo);
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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

}