package com.cwms.entities;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@IdClass(ExportSubId.class)
@Table(name="exportsub")
public class ExportSub {
	    @Id
	    @Column(name = "Company_Id", length = 6, nullable = false)
	    private String companyId;

	    @Id
	    @Column(name = "Branch_Id", length = 6, nullable = false)
	    private String branchId;

	    @Id
	    @Column(name = "Exp_Sub_Id", length = 10, nullable = false)
	    private String expSubId;

	    @Id
	    @Column(name = "Request_Id", length = 30, nullable = false)
	    private String requestId;

	    @Column(name = "SER_No", length = 20, nullable = false)
	    private String serNo;

	    @Column(name = "SER_Date", nullable = false)
	    @Temporal(TemporalType.DATE)
	    private Date serDate;

	    @Column(name = "Exporter", length = 6, nullable = false)
	    private String exporter;

	    @Column(name = "Challan_No", length = 30)
	    private String challanNo;

	    @Column(name = "Challan_Date")
	    @Temporal(TemporalType.DATE)
	    private Date challanDate;

	    @Column(name = "Invoice_No", length = 30)
	    private String invoiceNo;

	    @Column(name = "Invoice_Date")
	    @Temporal(TemporalType.DATE)
	    private Date invoiceDate;

	    @Column(name = "Nop")
	    private int nop;

	    @Column(name = "GW_Weight", precision = 15, scale = 3)
	    private BigDecimal gwWeight;

	    @Column(name = "GW_weight_unit", length = 20)
	    private String gwWeightUnit;

	    @Column(name = "Passout_Weight", precision = 15, scale = 3)
	    private BigDecimal passoutWeight;

	    @Column(name = "Passout_weight_unit", length = 20)
	    private String passoutWeightUnit;

	    @Column(name = "Product_Value", precision = 15, scale = 3)
	    private BigDecimal productValue;

	    @Column(name = "Currency", length = 15)
	    private String currency;

	    @Column(name = "nopieces", precision = 15, scale = 3)
	    private BigDecimal nopieces;

	    @Column(name = "Remarks", length = 255)
	    private String remarks;

	    @Column(name = "NSDL_Status", length = 30)
	    private String nsdlStatus;

	    @Column(name = "DGDC_Status", length = 30)
	    private String dgdcStatus;
	    
	    @Column(name= "Status_Doc",length = 100)
	    private String status_document;
	    
	    @Column(name = "received_wt", precision = 15, scale = 3)
	    private BigDecimal received_wt;
	    
	    @Column(name= "Received_Wt_Unit",length = 20)
	    private String received_wt_unit;

	    @Column(name = "Status", length = 1)
	    private char status;

	    @Column(name = "Created_By", length = 10)
	    private String createdBy;

	    
	    
	    @Column(name = "Created_Date")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date createdDate;

	    @Column(name = "Edited_By", length = 10)
	    private String editedBy;

	    @Column(name = "Edited_Date")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date editedDate;

	    @Column(name = "Approved_By", length = 10)
	    private String approvedBy;

	    @Column(name = "Approved_Date")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date approvedDate;
	    
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
		 @Column(name = "GatePass_Status", length = 1)
			private String gatePassStatus;

		    public String getGatePassStatus() {
				return gatePassStatus;
			}

			public void setGatePassStatus(String gatePassStatus) {
				this.gatePassStatus = gatePassStatus;
			}
	    
		 
	    @Temporal(TemporalType.DATE)
		@Column(name = "Out_Date", length = 10)
		private Date outDate;
	    
	    
	    
		public Date getOutDate() {
			return outDate;
		}

		public void setOutDate(Date outDate) {
			this.outDate = outDate;
		}
	    
		public ExportSub() {
			super();
			// TODO Auto-generated constructor stub
		}

	

		public String getForwardedStatus() {
			return forwardedStatus;
		}

		public void setForwardedStatus(String forwardedStatus) {
			this.forwardedStatus = forwardedStatus;
		}

		

		public ExportSub(String companyId, String branchId, String expSubId, String requestId, String serNo,
				Date serDate, String exporter, String challanNo, Date challanDate, String invoiceNo, Date invoiceDate,
				int nop, BigDecimal gwWeight, String gwWeightUnit, BigDecimal passoutWeight, String passoutWeightUnit,
				BigDecimal productValue, String currency, BigDecimal nopieces, String remarks, String nsdlStatus,
				String dgdcStatus, String status_document, BigDecimal received_wt, String received_wt_unit, char status,
				String createdBy, Date createdDate, String editedBy, Date editedDate, String approvedBy,
				Date approvedDate, String handover_Party_CHA, String handover_Party_Name,
				String handover_Represntative_id, double imposePenaltyAmount, String imposePenaltyRemarks,
				String forwardedStatus, int noc, int dgdc_seepz_in_scan, int dgdc_seepz_out_scan,
				int dgdc_cargo_in_scan, int dgdc_cargo_out_scan, String gatePassStatus, Date outDate) {
			super();
			this.companyId = companyId;
			this.branchId = branchId;
			this.expSubId = expSubId;
			this.requestId = requestId;
			this.serNo = serNo;
			this.serDate = serDate;
			this.exporter = exporter;
			this.challanNo = challanNo;
			this.challanDate = challanDate;
			this.invoiceNo = invoiceNo;
			this.invoiceDate = invoiceDate;
			this.nop = nop;
			this.gwWeight = gwWeight;
			this.gwWeightUnit = gwWeightUnit;
			this.passoutWeight = passoutWeight;
			this.passoutWeightUnit = passoutWeightUnit;
			this.productValue = productValue;
			this.currency = currency;
			this.nopieces = nopieces;
			this.remarks = remarks;
			this.nsdlStatus = nsdlStatus;
			this.dgdcStatus = dgdcStatus;
			this.status_document = status_document;
			this.received_wt = received_wt;
			this.received_wt_unit = received_wt_unit;
			this.status = status;
			this.createdBy = createdBy;
			this.createdDate = createdDate;
			this.editedBy = editedBy;
			this.editedDate = editedDate;
			this.approvedBy = approvedBy;
			this.approvedDate = approvedDate;
			this.handover_Party_CHA = handover_Party_CHA;
			this.handover_Party_Name = handover_Party_Name;
			this.handover_Represntative_id = handover_Represntative_id;
			this.imposePenaltyAmount = imposePenaltyAmount;
			this.imposePenaltyRemarks = imposePenaltyRemarks;
			this.forwardedStatus = forwardedStatus;
			this.noc = noc;
			this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
			this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
			this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
			this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
			this.gatePassStatus = gatePassStatus;
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


		
		
		

		public ExportSub(String companyId, String branchId, String expSubId, String requestId, String serNo,
				Date serDate, String exporter, String challanNo, Date challanDate, String invoiceNo, Date invoiceDate,
				int nop, BigDecimal gwWeight, String gwWeightUnit, BigDecimal passoutWeight, String passoutWeightUnit,
				BigDecimal productValue, String currency, BigDecimal nopieces, String remarks, String nsdlStatus,
				String dgdcStatus, String status_document, BigDecimal received_wt, String received_wt_unit, char status,
				String createdBy, Date createdDate, String editedBy, Date editedDate, String approvedBy,
				Date approvedDate, String handover_Party_CHA, String handover_Party_Name,
				String handover_Represntative_id, double imposePenaltyAmount, String imposePenaltyRemarks, int noc,
				int dgdc_seepz_in_scan, int dgdc_seepz_out_scan, int dgdc_cargo_in_scan, int dgdc_cargo_out_scan,
				Date outDate) {
			super();
			this.companyId = companyId;
			this.branchId = branchId;
			this.expSubId = expSubId;
			this.requestId = requestId;
			this.serNo = serNo;
			this.serDate = serDate;
			this.exporter = exporter;
			this.challanNo = challanNo;
			this.challanDate = challanDate;
			this.invoiceNo = invoiceNo;
			this.invoiceDate = invoiceDate;
			this.nop = nop;
			this.gwWeight = gwWeight;
			this.gwWeightUnit = gwWeightUnit;
			this.passoutWeight = passoutWeight;
			this.passoutWeightUnit = passoutWeightUnit;
			this.productValue = productValue;
			this.currency = currency;
			this.nopieces = nopieces;
			this.remarks = remarks;
			this.nsdlStatus = nsdlStatus;
			this.dgdcStatus = dgdcStatus;
			this.status_document = status_document;
			this.received_wt = received_wt;
			this.received_wt_unit = received_wt_unit;
			this.status = status;
			this.createdBy = createdBy;
			this.createdDate = createdDate;
			this.editedBy = editedBy;
			this.editedDate = editedDate;
			this.approvedBy = approvedBy;
			this.approvedDate = approvedDate;
			this.handover_Party_CHA = handover_Party_CHA;
			this.handover_Party_Name = handover_Party_Name;
			this.handover_Represntative_id = handover_Represntative_id;
			this.imposePenaltyAmount = imposePenaltyAmount;
			this.imposePenaltyRemarks = imposePenaltyRemarks;
			this.noc = noc;
			this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
			this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
			this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
			this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
			this.outDate = outDate;
		}

		public ExportSub(String companyId, String branchId, String expSubId, String requestId, String serNo,
				Date serDate, String exporter, String challanNo, Date challanDate, String invoiceNo, Date invoiceDate,
				int nop, BigDecimal gwWeight, String gwWeightUnit, BigDecimal passoutWeight, String passoutWeightUnit,
				BigDecimal productValue, String currency, BigDecimal nopieces, String remarks, String nsdlStatus,
				String dgdcStatus, String status_document, BigDecimal received_wt, String received_wt_unit, char status,
				String createdBy, Date createdDate, String editedBy, Date editedDate, String approvedBy,
				Date approvedDate, String handover_Party_CHA, String handover_Party_Name,
				String handover_Represntative_id, double imposePenaltyAmount, String imposePenaltyRemarks, int noc,
				int dgdc_seepz_in_scan, int dgdc_seepz_out_scan, int dgdc_cargo_in_scan, int dgdc_cargo_out_scan) {
			super();
			this.companyId = companyId;
			this.branchId = branchId;
			this.expSubId = expSubId;
			this.requestId = requestId;
			this.serNo = serNo;
			this.serDate = serDate;
			this.exporter = exporter;
			this.challanNo = challanNo;
			this.challanDate = challanDate;
			this.invoiceNo = invoiceNo;
			this.invoiceDate = invoiceDate;
			this.nop = nop;
			this.gwWeight = gwWeight;
			this.gwWeightUnit = gwWeightUnit;
			this.passoutWeight = passoutWeight;
			this.passoutWeightUnit = passoutWeightUnit;
			this.productValue = productValue;
			this.currency = currency;
			this.nopieces = nopieces;
			this.remarks = remarks;
			this.nsdlStatus = nsdlStatus;
			this.dgdcStatus = dgdcStatus;
			this.status_document = status_document;
			this.received_wt = received_wt;
			this.received_wt_unit = received_wt_unit;
			this.status = status;
			this.createdBy = createdBy;
			this.createdDate = createdDate;
			this.editedBy = editedBy;
			this.editedDate = editedDate;
			this.approvedBy = approvedBy;
			this.approvedDate = approvedDate;
			this.handover_Party_CHA = handover_Party_CHA;
			this.handover_Party_Name = handover_Party_Name;
			this.handover_Represntative_id = handover_Represntative_id;
			this.imposePenaltyAmount = imposePenaltyAmount;
			this.imposePenaltyRemarks = imposePenaltyRemarks;
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

		public String getExpSubId() {
			return expSubId;
		}

		public void setExpSubId(String expSubId) {
			this.expSubId = expSubId;
		}

		public String getRequestId() {
			return requestId;
		}

		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}

		public String getSerNo() {
			return serNo;
		}

		public void setSerNo(String serNo) {
			this.serNo = serNo;
		}

		public Date getSerDate() {
			return serDate;
		}

		public void setSerDate(Date serDate) {
			this.serDate = serDate;
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

		public BigDecimal getGwWeight() {
			return gwWeight;
		}

		public void setGwWeight(BigDecimal gwWeight) {
			this.gwWeight = gwWeight;
		}

		public String getGwWeightUnit() {
			return gwWeightUnit;
		}

		public void setGwWeightUnit(String gwWeightUnit) {
			this.gwWeightUnit = gwWeightUnit;
		}

		public BigDecimal getPassoutWeight() {
			return passoutWeight;
		}

		public void setPassoutWeight(BigDecimal passoutWeight) {
			this.passoutWeight = passoutWeight;
		}

		public String getPassoutWeightUnit() {
			return passoutWeightUnit;
		}

		public void setPassoutWeightUnit(String passoutWeightUnit) {
			this.passoutWeightUnit = passoutWeightUnit;
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

		public BigDecimal getNopieces() {
			return nopieces;
		}

		public void setNopieces(BigDecimal nopieces) {
			this.nopieces = nopieces;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
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

		public BigDecimal getReceived_wt() {
			return received_wt;
		}

		public void setReceived_wt(BigDecimal received_wt) {
			this.received_wt = received_wt;
		}

		public String getReceived_wt_unit() {
			return received_wt_unit;
		}

		public void setReceived_wt_unit(String received_wt_unit) {
			this.received_wt_unit = received_wt_unit;
		}

		public char getStatus() {
			return status;
		}

		public void setStatus(char status) {
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

		@Override
		public String toString() {
			return "ExportSub [companyId=" + companyId + ", branchId=" + branchId + ", expSubId=" + expSubId
					+ ", requestId=" + requestId + ", serNo=" + serNo + ", serDate=" + serDate + ", exporter="
					+ exporter + ", challanNo=" + challanNo + ", challanDate=" + challanDate + ", invoiceNo="
					+ invoiceNo + ", invoiceDate=" + invoiceDate + ", nop=" + nop + ", gwWeight=" + gwWeight
					+ ", gwWeightUnit=" + gwWeightUnit + ", passoutWeight=" + passoutWeight + ", passoutWeightUnit="
					+ passoutWeightUnit + ", productValue=" + productValue + ", currency=" + currency + ", nopieces="
					+ nopieces + ", remarks=" + remarks + ", nsdlStatus=" + nsdlStatus + ", dgdcStatus=" + dgdcStatus
					+ ", status_document=" + status_document + ", received_wt=" + received_wt + ", received_wt_unit="
					+ received_wt_unit + ", status=" + status + ", createdBy=" + createdBy + ", createdDate="
					+ createdDate + ", editedBy=" + editedBy + ", editedDate=" + editedDate + ", approvedBy="
					+ approvedBy + ", approvedDate=" + approvedDate + ", handover_Party_CHA=" + handover_Party_CHA
					+ ", handover_Party_Name=" + handover_Party_Name + ", handover_Represntative_id="
					+ handover_Represntative_id + ", imposePenaltyAmount=" + imposePenaltyAmount
					+ ", imposePenaltyRemarks=" + imposePenaltyRemarks + "]";
		}

	

	
	
}
