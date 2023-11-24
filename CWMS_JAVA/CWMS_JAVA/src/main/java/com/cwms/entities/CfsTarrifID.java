package com.cwms.entities;

import java.io.Serializable;
import java.util.Objects;

public class CfsTarrifID implements Serializable {

	private String companyId;
	private String branchId;
	private String cfsTariffNo;
	private String cfsAmndNo;
	private String partyId;
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CfsTarrifID))
			return false;
		CfsTarrifID that = (CfsTarrifID) o;
		return Objects.equals(getCompanyId(), that.getCompanyId()) && Objects.equals(getBranchId(), that.getBranchId())
				&& Objects.equals(getCfsTariffNo(), that.getCfsTariffNo())
				&& Objects.equals(getCfsAmndNo(), that.getCfsAmndNo());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCompanyId(), getBranchId(), getCfsTariffNo(), getCfsAmndNo(), getPartyId());
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

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	

}