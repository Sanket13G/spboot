package com.cwms.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;


@Entity
@Table(name = "importsub")
@IdClass(ImportSubId.class)
public class ImportSub   {

    @Id
    @Column(name = "Company_Id", length = 6, nullable = false)
    private String companyId;

    @Id
    @Column(name = "Branch_Id", length = 6, nullable = false)
    private String branchId;

    @Id
    @Column(name = "Imp_Sub_Id", length = 10, nullable = false)
    private String impSubId;

    @Id
    @Column(name = "Request_Id", length = 30)
    private String requestId;

   
    @Column(name = "SIR_No", length = 20, nullable = false)
    private String sirNo;

    @Temporal(TemporalType.DATE)
    @Column(name = "SIR_Date", nullable = false)
    private Date sirDate;

    @Column(name = "Import_Type", length = 20)
    private String importType;

    @Column(name = "Exporter", length = 80, nullable = false)
    private String exporter;

    @Column(name = "Challan_No", length = 30)
    private String challanNo;

    @Temporal(TemporalType.DATE)
    @Column(name = "Challan_Date")
    private Date challanDate;

    @Column(name = "Invoice_No", length = 30)
    private String invoiceNo;

    @Temporal(TemporalType.DATE)
    @Column(name = "Invoice_Date")
    private Date invoiceDate;
    
    @Column(name = "Exporter_name", length = 255)
    private String exporterName;

    @Temporal(TemporalType.DATE)
    @Column(name = "Exporter_Date")
    private Date exporterDate;

    @Column(name = "Nop", columnDefinition = "int default 0")
    private int nop;

    @Column(name = "Net_Weight",  precision = 15, scale = 3)
    private BigDecimal netWeight;

    @Column(name = "Net_weight_unit", length = 20)
    private String netWeightUnit;
    
    
    @Column(name = "PassedIn_Weight", precision = 15, scale = 3)
    private BigDecimal passedInWt;

    @Column(name = "PassedIn_weight_unit", length = 20)
    private String passedInWtUnit;
    

    @Column(name = "Product_Value", precision = 15, scale = 3)
    private BigDecimal productValue;

    @Column(name = "Currency", length = 15)
    private String currency;

    @Column(name = "Remarks", length = 255)
    private String remarks;

    @Temporal(TemporalType.DATE)
    @Column(name = "Reentry_Date")
    private Date reentryDate;

    @Column(name = "GatePass_Status", length = 1)
   	private String gatePassStatus;
    
    @Column(name = "NSDL_Status", length = 30)
    private String nsdlStatus;

    @Column(name = "DGDC_Status", length = 30)
    private String dgdcStatus;
    
    @Column(name= "Status_Doc",length = 100)
    private String status_document;

    @Column(name = "Status", length = 1)
    private String status;
    
    @Column(name = "LGD_Status", length = 1)
    private String lgdStatus;
    
    @Column(name="Handover_Party_CHA",length = 1)
    private String handover_Party_CHA;
    
    @Column(name="Handover_Party_Name",length=50)
    private String handover_Party_Name;
    
    @Column(name="Handover_Represntative_id",length=10)
    private String handover_Represntative_id;
    
    @Column(name = "Impose_Penalty_Amt")
    private double imposePenaltyAmount;

    @Column(name = "Impose_Penalty_Remarks")
    private String imposePenaltyRemarks;

    @Column(name = "Created_By", length = 10)
    private String createdBy;

    @Column(name = "Created_Date")
    private Date createdDate;

    @Column(name = "Edited_By", length = 10)
    private String editedBy;

    @Column(name = "Edited_Date")
    private Date editedDate;

    @Column(name = "Approved_By", length = 10)
    private String approvedBy;

    @Column(name = "Approved_Date")
    private Date approvedDate;
    
    @Column(name="Forwarded_status",length=15)
    private String forwardedStatus;
    
    
    @Column(length=5)
    private int noc;
    
	@Column(length=1)
	private int dgdc_seepz_in_scan;
	@Column(length=1)
	private int dgdc_seepz_out_scan;
	@Column(length=1)
	private int dgdc_cargo_in_scan;
	@Column(length=1)
	private int dgdc_cargo_out_scan;
	
	 @Temporal(TemporalType.DATE)
		@Column(name = "Out_Date", length = 10)
		private Date outDate;
	
	 
	 

	public Date getOutDate() {
		return outDate;
	}


	

	public String getGatePassStatus() {
		return gatePassStatus;
	}




	public void setGatePassStatus(String gatePassStatus) {
		this.gatePassStatus = gatePassStatus;
	}




	public ImportSub(String companyId, String branchId, String impSubId, String requestId, String sirNo, Date sirDate,
			String importType, String exporter, String challanNo, Date challanDate, String invoiceNo, Date invoiceDate,
			String exporterName, Date exporterDate, int nop, BigDecimal netWeight, String netWeightUnit,
			BigDecimal passedInWt, String passedInWtUnit, BigDecimal productValue, String currency, String remarks,
			Date reentryDate, String gatePassStatus, String nsdlStatus, String dgdcStatus, String status_document,
			String status, String lgdStatus, String handover_Party_CHA, String handover_Party_Name,
			String handover_Represntative_id, double imposePenaltyAmount, String imposePenaltyRemarks,
			String createdBy, Date createdDate, String editedBy, Date editedDate, String approvedBy, Date approvedDate,
			String forwardedStatus, int noc, int dgdc_seepz_in_scan, int dgdc_seepz_out_scan, int dgdc_cargo_in_scan,
			int dgdc_cargo_out_scan, Date outDate) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.impSubId = impSubId;
		this.requestId = requestId;
		this.sirNo = sirNo;
		this.sirDate = sirDate;
		this.importType = importType;
		this.exporter = exporter;
		this.challanNo = challanNo;
		this.challanDate = challanDate;
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.exporterName = exporterName;
		this.exporterDate = exporterDate;
		this.nop = nop;
		this.netWeight = netWeight;
		this.netWeightUnit = netWeightUnit;
		this.passedInWt = passedInWt;
		this.passedInWtUnit = passedInWtUnit;
		this.productValue = productValue;
		this.currency = currency;
		this.remarks = remarks;
		this.reentryDate = reentryDate;
		this.gatePassStatus = gatePassStatus;
		this.nsdlStatus = nsdlStatus;
		this.dgdcStatus = dgdcStatus;
		this.status_document = status_document;
		this.status = status;
		this.lgdStatus = lgdStatus;
		this.handover_Party_CHA = handover_Party_CHA;
		this.handover_Party_Name = handover_Party_Name;
		this.handover_Represntative_id = handover_Represntative_id;
		this.imposePenaltyAmount = imposePenaltyAmount;
		this.imposePenaltyRemarks = imposePenaltyRemarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.forwardedStatus = forwardedStatus;
		this.noc = noc;
		this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
		this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
		this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
		this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
		this.outDate = outDate;
	}




	public String getExporterName() {
		return exporterName;
	}




	public void setExporterName(String exporterName) {
		this.exporterName = exporterName;
	}




	public Date getExporterDate() {
		return exporterDate;
	}




	public void setExporterDate(Date exporterDate) {
		this.exporterDate = exporterDate;
	}




	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}



	public int getNoc() {
		return noc;
	}



	public void setNoc(int noc) {
		this.noc = noc;
	}



	public int getDgdc_seepz_in_scan() {
		return dgdc_seepz_in_scan;
	}



	public void setDgdc_seepz_in_scan(int dgdc_seepz_in_scan) {
		this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
	}



	public int getDgdc_seepz_out_scan() {
		return dgdc_seepz_out_scan;
	}



	public void setDgdc_seepz_out_scan(int dgdc_seepz_out_scan) {
		this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
	}



	public int getDgdc_cargo_in_scan() {
		return dgdc_cargo_in_scan;
	}



	public void setDgdc_cargo_in_scan(int dgdc_cargo_in_scan) {
		this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
	}



	public int getDgdc_cargo_out_scan() {
		return dgdc_cargo_out_scan;
	}



	public void setDgdc_cargo_out_scan(int dgdc_cargo_out_scan) {
		this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
	}



	public ImportSub() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getForwardedStatus() {
		return forwardedStatus;
	}



	public void setForwardedStatus(String forwardedStatus) {
		this.forwardedStatus = forwardedStatus;
	}




	public ImportSub(String companyId, String branchId, String impSubId, String requestId, String sirNo, Date sirDate,
			String importType, String exporter, String challanNo, Date challanDate, String invoiceNo, Date invoiceDate,
			String exporterName, Date exporterDate, int nop, BigDecimal netWeight, String netWeightUnit,
			BigDecimal passedInWt, String passedInWtUnit, BigDecimal productValue, String currency, String remarks,
			Date reentryDate, String nsdlStatus, String dgdcStatus, String status_document, String status,
			String lgdStatus, String handover_Party_CHA, String handover_Party_Name, String handover_Represntative_id,
			double imposePenaltyAmount, String imposePenaltyRemarks, String createdBy, Date createdDate,
			String editedBy, Date editedDate, String approvedBy, Date approvedDate, String forwardedStatus, int noc,
			int dgdc_seepz_in_scan, int dgdc_seepz_out_scan, int dgdc_cargo_in_scan, int dgdc_cargo_out_scan,
			Date outDate) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.impSubId = impSubId;
		this.requestId = requestId;
		this.sirNo = sirNo;
		this.sirDate = sirDate;
		this.importType = importType;
		this.exporter = exporter;
		this.challanNo = challanNo;
		this.challanDate = challanDate;
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.exporterName = exporterName;
		this.exporterDate = exporterDate;
		this.nop = nop;
		this.netWeight = netWeight;
		this.netWeightUnit = netWeightUnit;
		this.passedInWt = passedInWt;
		this.passedInWtUnit = passedInWtUnit;
		this.productValue = productValue;
		this.currency = currency;
		this.remarks = remarks;
		this.reentryDate = reentryDate;
		this.nsdlStatus = nsdlStatus;
		this.dgdcStatus = dgdcStatus;
		this.status_document = status_document;
		this.status = status;
		this.lgdStatus = lgdStatus;
		this.handover_Party_CHA = handover_Party_CHA;
		this.handover_Party_Name = handover_Party_Name;
		this.handover_Represntative_id = handover_Represntative_id;
		this.imposePenaltyAmount = imposePenaltyAmount;
		this.imposePenaltyRemarks = imposePenaltyRemarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.forwardedStatus = forwardedStatus;
		this.noc = noc;
		this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
		this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
		this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
		this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
		this.outDate = outDate;
	}




	public ImportSub(String companyId, String branchId, String impSubId, String requestId, String sirNo, Date sirDate,
			String importType, String exporter, String challanNo, Date challanDate, String invoiceNo, Date invoiceDate,
			int nop, BigDecimal netWeight, String netWeightUnit, BigDecimal passedInWt, String passedInWtUnit,
			BigDecimal productValue, String currency, String remarks, Date reentryDate, String nsdlStatus,
			String dgdcStatus, String status_document, String status, String lgdStatus, String handover_Party_CHA,
			String handover_Party_Name, String handover_Represntative_id, double imposePenaltyAmount,
			String imposePenaltyRemarks, String createdBy, Date createdDate, String editedBy, Date editedDate,
			String approvedBy, Date approvedDate, String forwardedStatus, int noc, int dgdc_seepz_in_scan,
			int dgdc_seepz_out_scan, int dgdc_cargo_in_scan, int dgdc_cargo_out_scan) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.impSubId = impSubId;
		this.requestId = requestId;
		this.sirNo = sirNo;
		this.sirDate = sirDate;
		this.importType = importType;
		this.exporter = exporter;
		this.challanNo = challanNo;
		this.challanDate = challanDate;
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.nop = nop;
		this.netWeight = netWeight;
		this.netWeightUnit = netWeightUnit;
		this.passedInWt = passedInWt;
		this.passedInWtUnit = passedInWtUnit;
		this.productValue = productValue;
		this.currency = currency;
		this.remarks = remarks;
		this.reentryDate = reentryDate;
		this.nsdlStatus = nsdlStatus;
		this.dgdcStatus = dgdcStatus;
		this.status_document = status_document;
		this.status = status;
		this.lgdStatus = lgdStatus;
		this.handover_Party_CHA = handover_Party_CHA;
		this.handover_Party_Name = handover_Party_Name;
		this.handover_Represntative_id = handover_Represntative_id;
		this.imposePenaltyAmount = imposePenaltyAmount;
		this.imposePenaltyRemarks = imposePenaltyRemarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.forwardedStatus = forwardedStatus;
		this.noc = noc;
		this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
		this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
		this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
		this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
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

	public String getImpSubId() {
		return impSubId;
	}

	public void setImpSubId(String impSubId) {
		this.impSubId = impSubId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSirNo() {
		return sirNo;
	}

	public void setSirNo(String sirNo) {
		this.sirNo = sirNo;
	}

	public Date getSirDate() {
		return sirDate;
	}

	public void setSirDate(Date sirDate) {
		this.sirDate = sirDate;
	}

	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	public String getExporter() {
		return exporter;
	}

	public void setExporter(String exporter) {
		this.exporter = exporter;
	}

	public String getChallanNo() {
		return challanNo;
	}

	public void setChallanNo(String challanNo) {
		this.challanNo = challanNo;
	}

	public Date getChallanDate() {
		return challanDate;
	}

	public void setChallanDate(Date challanDate) {
		this.challanDate = challanDate;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public int getNop() {
		return nop;
	}

	public void setNop(int nop) {
		this.nop = nop;
	}

	public BigDecimal getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	public String getNetWeightUnit() {
		return netWeightUnit;
	}

	public void setNetWeightUnit(String netWeightUnit) {
		this.netWeightUnit = netWeightUnit;
	}

	public BigDecimal getPassedInWt() {
		return passedInWt;
	}

	public void setPassedInWt(BigDecimal passedInWt) {
		this.passedInWt = passedInWt;
	}

	public String getPassedInWtUnit() {
		return passedInWtUnit;
	}

	public void setPassedInWtUnit(String passedInWtUnit) {
		this.passedInWtUnit = passedInWtUnit;
	}

	public BigDecimal getProductValue() {
		return productValue;
	}

	public void setProductValue(BigDecimal productValue) {
		this.productValue = productValue;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getReentryDate() {
		return reentryDate;
	}

	public void setReentryDate(Date reentryDate) {
		this.reentryDate = reentryDate;
	}

	public String getNsdlStatus() {
		return nsdlStatus;
	}

	public void setNsdlStatus(String nsdlStatus) {
		this.nsdlStatus = nsdlStatus;
	}

	public String getDgdcStatus() {
		return dgdcStatus;
	}

	public void setDgdcStatus(String dgdcStatus) {
		this.dgdcStatus = dgdcStatus;
	}

	public String getStatus_document() {
		return status_document;
	}

	public void setStatus_document(String status_document) {
		this.status_document = status_document;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLgdStatus() {
		return lgdStatus;
	}

	public void setLgdStatus(String lgdStatus) {
		this.lgdStatus = lgdStatus;
	}

	public String getHandover_Party_CHA() {
		return handover_Party_CHA;
	}

	public void setHandover_Party_CHA(String handover_Party_CHA) {
		this.handover_Party_CHA = handover_Party_CHA;
	}

	public String getHandover_Party_Name() {
		return handover_Party_Name;
	}

	public void setHandover_Party_Name(String handover_Party_Name) {
		this.handover_Party_Name = handover_Party_Name;
	}

	public String getHandover_Represntative_id() {
		return handover_Represntative_id;
	}

	public void setHandover_Represntative_id(String handover_Represntative_id) {
		this.handover_Represntative_id = handover_Represntative_id;
	}

	public double getImposePenaltyAmount() {
		return imposePenaltyAmount;
	}

	public void setImposePenaltyAmount(double imposePenaltyAmount) {
		this.imposePenaltyAmount = imposePenaltyAmount;
	}

	public String getImposePenaltyRemarks() {
		return imposePenaltyRemarks;
	}

	public void setImposePenaltyRemarks(String imposePenaltyRemarks) {
		this.imposePenaltyRemarks = imposePenaltyRemarks;
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
		return "ImportSub [companyId=" + companyId + ", branchId=" + branchId + ", impSubId=" + impSubId
				+ ", requestId=" + requestId + ", sirNo=" + sirNo + ", sirDate=" + sirDate + ", importType="
				+ importType + ", exporter=" + exporter + ", challanNo=" + challanNo + ", challanDate=" + challanDate
				+ ", invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate + ", nop=" + nop + ", netWeight="
				+ netWeight + ", netWeightUnit=" + netWeightUnit + ", passedInWt=" + passedInWt + ", passedInWtUnit="
				+ passedInWtUnit + ", productValue=" + productValue + ", currency=" + currency + ", remarks=" + remarks
				+ ", reentryDate=" + reentryDate + ", nsdlStatus=" + nsdlStatus + ", dgdcStatus=" + dgdcStatus
				+ ", status_document=" + status_document + ", status=" + status + ", lgdStatus=" + lgdStatus
				+ ", handover_Party_CHA=" + handover_Party_CHA + ", handover_Party_Name=" + handover_Party_Name
				+ ", handover_Represntative_id=" + handover_Represntative_id + ", imposePenaltyAmount="
				+ imposePenaltyAmount + ", imposePenaltyRemarks=" + imposePenaltyRemarks + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", editedBy=" + editedBy + ", editedDate=" + editedDate
				+ ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + "]";
	}

	
    
}

