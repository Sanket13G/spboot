package com.cwms.entities;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "email_Scheduler")
@IdClass(InvoiceServiceId.class)
public class EmailScedulerEntity 
{
	@Id
	@Column(name = "Company_Id", length = 6, nullable = false)
	private String companyId;

	@Id
	@Column(name = "Branch_Id", length = 6, nullable = false)
	private String branchId;
	
	@Id
	@Column(name = "Invoice_No", length = 6, nullable = false)
	private String invoiceNO;
	
	@Column(name = "Bill_No", length = 6, nullable = false)
	public String billNO;	
	
	@Id
	@Column(name = "Party_Id", length = 6, nullable = false)
	private String partyId;	
	
		
	@Column(name = "Period_From", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date periodFrom;

    @Column(name = "Period_To", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date periodTo;
	
    @Column(name = "Mail_Flag", length = 1)
    private String mailFlag;

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

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}

	public String getBillNO() {
		return billNO;
	}

	public void setBillNO(String billNO) {
		this.billNO = billNO;
	}

	public Date getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(Date periodFrom) {
		this.periodFrom = periodFrom;
	}

	public Date getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(Date periodTo) {
		this.periodTo = periodTo;
	}

	public String getMailFlag() {
		return mailFlag;
	}

	public void setMailFlag(String mailFlag) {
		this.mailFlag = mailFlag;
	}


	public EmailScedulerEntity(String companyId, String branchId, String invoiceNO, String billNO, String partyId,
			Date periodFrom, Date periodTo, String mailFlag) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.invoiceNO = invoiceNO;
		this.billNO = billNO;
		this.partyId = partyId;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.mailFlag = mailFlag;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public EmailScedulerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	

}