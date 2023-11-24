package com.cwms.entities;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cfinvsrv")
@IdClass(InvoiceServiceId.class)
public class InvoiceMain {
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

    @Column(name = "Invoice_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;

    @Column(name = "Tariff_No", nullable = false, length = 10)
    private String tariffNo;

    @Column(name = "Tariff_Amnd_No", nullable = false, length = 3)
    private String tariffAmndNo;

    @Column(name = "Invoice_Due_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date invoiceDueDate;

    @Column(name = "Tax_Amt")
    private double taxAmount;

    @Column(name = "Bill_Amt")
    private double billAmount;

    @Column(name = "Total_Invoice_Amt")
    private double totalInvoiceAmount;

    @Column(name = "Receipt_Trans_Id", length = 10)
    private String receiptTransactionId;

    @Column(name = "Receipt_Trans_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiptTransactionDate;

    @Column(name = "Period_From", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date periodFrom;

    @Column(name = "Period_To", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date periodTo;

    
    @Column(name = "Comments" , length=500)
    private String comments;

    @Column(name = "Mail_Flag", length = 1)
    private String mailFlag;

    @Column(name = "IGST", length = 1)
    private String igst;

    @Column(name = "CGST", length = 1)
    private String cgst;

    @Column(name = "SGST", length = 1)
    private String sgst;
    
    @Column(name = "Extra_Taxes")
    private double extraTaxes;

    @Column(name = "Status", length = 1)
    private String status;

    @Column(name = "Created_By", length = 30)
    private String createdBy;

    @Column(name = "Created_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "Edited_By", length = 30)
    private String editedBy;

    @Column(name = "Edited_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editedDate;

    @Column(name = "Approved_By", length = 30)
    private String approvedBy;

    @Column(name = "Approved_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedDate;

    @Column(name = "Cleared_Amt")
    private double clearedAmt;
    
    @Column(name = "Payment_Status", length = 1)
    private String paymentStatus;
    
    
    
    
	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public double getClearedAmt() {
		return clearedAmt;
	}


	public void setClearedAmt(double clearedAmt) {
		this.clearedAmt = clearedAmt;
	}


	public InvoiceMain() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public double getExtraTaxes() {
		return extraTaxes;
	}

	public void setExtraTaxes(double extraTaxes) {
		this.extraTaxes = extraTaxes;
	}

	
	public String getBillNO() {
		return billNO;
	}


	public void setBillNO(String billNO) {
		this.billNO = billNO;
	}


	public InvoiceMain(String companyId, String branchId, String invoiceNO, String billNO, String partyId,
			Date invoiceDate, String tariffNo, String tariffAmndNo, Date invoiceDueDate, double taxAmount,
			double billAmount, double totalInvoiceAmount, String receiptTransactionId, Date receiptTransactionDate,
			Date periodFrom, Date periodTo, String comments, String mailFlag, String igst, String cgst, String sgst,
			double extraTaxes, String status, String createdBy, Date createdDate, String editedBy, Date editedDate,
			String approvedBy, Date approvedDate) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.invoiceNO = invoiceNO;
		this.billNO = billNO;
		this.partyId = partyId;
		this.invoiceDate = invoiceDate;
		this.tariffNo = tariffNo;
		this.tariffAmndNo = tariffAmndNo;
		this.invoiceDueDate = invoiceDueDate;
		this.taxAmount = taxAmount;
		this.billAmount = billAmount;
		this.totalInvoiceAmount = totalInvoiceAmount;
		this.receiptTransactionId = receiptTransactionId;
		this.receiptTransactionDate = receiptTransactionDate;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.comments = comments;
		this.mailFlag = mailFlag;
		this.igst = igst;
		this.cgst = cgst;
		this.sgst = sgst;
		this.extraTaxes = extraTaxes;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
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

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getTariffNo() {
		return tariffNo;
	}

	public void setTariffNo(String tariffNo) {
		this.tariffNo = tariffNo;
	}

	public String getTariffAmndNo() {
		return tariffAmndNo;
	}

	public void setTariffAmndNo(String tariffAmndNo) {
		this.tariffAmndNo = tariffAmndNo;
	}

	public Date getInvoiceDueDate() {
		return invoiceDueDate;
	}

	public void setInvoiceDueDate(Date invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}

	

	public double getTaxAmount() {
		return taxAmount;
	}






	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}






	public double getBillAmount() {
		return billAmount;
	}






	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}






	public double getTotalInvoiceAmount() {
		return totalInvoiceAmount;
	}






	public void setTotalInvoiceAmount(double totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}






	public String getReceiptTransactionId() {
		return receiptTransactionId;
	}

	public void setReceiptTransactionId(String receiptTransactionId) {
		this.receiptTransactionId = receiptTransactionId;
	}

	public Date getReceiptTransactionDate() {
		return receiptTransactionDate;
	}

	public void setReceiptTransactionDate(Date receiptTransactionDate) {
		this.receiptTransactionDate = receiptTransactionDate;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	public String getMailFlag() {
		return mailFlag;
	}



	public void setMailFlag(String mailFlag) {
		this.mailFlag = mailFlag;
	}



	public String getIgst() {
		return igst;
	}



	public void setIgst(String igst) {
		this.igst = igst;
	}



	public String getCgst() {
		return cgst;
	}



	public void setCgst(String cgst) {
		this.cgst = cgst;
	}



	public String getSgst() {
		return sgst;
	}



	public void setSgst(String sgst) {
		this.sgst = sgst;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEditedBy() {
		return editedBy;
	}

	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}

	public Date getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(Date editedDate) {
		this.editedDate = editedDate;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}






	@Override
	public String toString() {
		return "InvoiceMain [companyId=" + companyId + ", branchId=" + branchId + ", InvoiceNO=" + invoiceNO
				+ ", partyId=" + partyId + ", invoiceDate=" + invoiceDate + ", tariffNo=" + tariffNo + ", tariffAmndNo="
				+ tariffAmndNo + ", invoiceDueDate=" + invoiceDueDate + ", taxAmount=" + taxAmount + ", billAmount="
				+ billAmount + ", totalInvoiceAmount=" + totalInvoiceAmount + ", receiptTransactionId="
				+ receiptTransactionId + ", receiptTransactionDate=" + receiptTransactionDate + ", periodFrom="
				+ periodFrom + ", periodTo=" + periodTo + ", comments=" + comments + ", mailFlag=" + mailFlag
				+ ", igst=" + igst + ", cgst=" + cgst + ", sgst=" + sgst + ", status=" + status + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", editedBy=" + editedBy + ", editedDate=" + editedDate
				+ ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + "]";
	}


}