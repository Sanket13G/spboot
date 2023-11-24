package com.cwms.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@IdClass(ExportId.class)
@Entity
@Table(name = "Export")
public class Export {

	@Id
	@Column(name = "Company_Id", length = 6)
	private String companyId;

	@Id
	@Column(name = "Branch_Id", length = 6)
	private String branchId;

	@Id
	@Column(name = "SB_Number", length = 15)
	private String sbNo;

	@Id
	@Column(name = "SB_Request_Id", length = 20)
	private String sbRequestId;

	@Column(name = "SB_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sbDate;

	@Column(name = "IEC_Code", length = 25)
	private String iecCode;

	@Column(name = "Entity_Id", length = 20)
	private String entityId;

	@Column(name = "Exporter_Name", length = 50)
	private String nameOfExporter;
	
	@Column(name = "Gate_In-Id", length = 20)
	private String gateInId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Gate_In_Date")
	private Date gateInDate;

	@Column(name = "SER_No", length = 20)
	private String serNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="SER_Date")
	private Date serDate;

	@Column(name = "gross_Weight")
	private Double grossWeight;

	@Column(name = "UOMGrossWeight")
	private String uomGrossWeight;

	@Column(name = "CountryOfDestination", length = 75)
	private String countryOfDestination;

	@Column(name = "PortOfDestination", length = 75)
	private String portOfDestination;

	@Column(name = "Airway_Bill_No", length = 25)
	private String airwayBillNo;

	@Column(name = "Description_Of_Goods", length = 100)
	private String descriptionOfGoods;

	@Column(name = "NSDL_Status", length = 50)
	private String nsdlStatus;

	@Column(name = "DGDC_Status", length = 50)
	private String dgdcStatus;

	@Column(name = "CHANo", length = 20)
	private String chaNo;

	@Column(name = "CHAName", length = 50)
	private String chaName;

	@Column(name = "ConsoleAgent", length = 25)
	private String consoleAgent;

	@Column(name = "FOBValueInINR")
	private Double fobValueInINR;

	@Column(name = "NoOfPackages")
	private int noOfPackages;

	@Column(name = "UOMOfPackages")
	private String uomOfPackages;

	@Column(name = "Status", length = 1)
	private String status;

	@Column(name = "Created_By")
	private String createdBy;

	@Column(name = "Created_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "Edited_By")
	private String editedBy;

	@Column(name = "Edited_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date editedDate;

	@Column(name = "Approved_By")
	private String approvedBy;

	@Column(name = "Approved_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	@Column(name = "Carting_Agent",length = 30)
    private String cartingAgent;
    
	@Column(name = "Representative_Id",length = 10)
    private String partyRepresentativeId;
	
	
    @Column(name = "Qr_Code_Url",length = 255)
    private String qrcodeUrl;
    
    @Column(name = "PCTM_No")
	private String pctmNo;

	@Column(name = "TP_No")
	private String tpNo;

	@Column(name = "TP_Date")
	@Temporal(TemporalType.DATE)
	private Date tpDate;

	@Column(name = "PCTM_Date")
	@Temporal(TemporalType.DATE)
	private Date pctmDate;

	
	 @Column(name = "MAWB",length = 12)
	    private String mawb;
    
    @Column(name = "Airline_Name")
    private String airlineName;

    @Column(name = "Flight_No")
    private String flightNo;
    
    
    @Column(name="Airline_Code",length=10)
    private String airlineCode;

    @Column(name = "Flight_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date flightDate;
    
    
    @Column(name = "Hold_Status",length = 1)
    private String holdStatus;
    
    @Column(name = "SC_Status",length = 1)
    private String scStatus;
    
    @Column(name = "PC_Status",length = 1)
    private String pcStatus;
    
    @Column(name = "HP_Status",length = 1)
    private String hpStatus;
    
    @Column(name = "HP_Package_No",length = 5)
    private String hppackageno;

    @Column(name = "HP_Weight")
    private BigDecimal hpWeight;
    
    @Column(name = "Impose_Penalty_Amt")
    private double imposePenaltyAmount;

    @Column(name = "Impose_Penalty_Remarks")
    private String imposePenaltyRemarks;
    
    @Column(name = "Reason_for_Override",length=255)
    private String reasonforOverride;
    
    @Column(name = "Override_Document",length=255)
    private String overrideDocument;
   
    
    @Column(name = "Cancel_Status",length = 1)
    private String cancelStatus;

    @Column(name = "Cancel_Remarks")
    private String cancelRemarks;
    
    
    
    
    @Column(name = "GatePass_Vehicle_No", length = 20)
	private String gatePassVehicleNo;
    
    
    @Column(name = "PO_Name", length = 30)
	private String pOName;
	
	@Column(name = "GatePass_Status", length = 1)
	private String gatePassStatus;
	
	@Column(name = "image_path", length = 252)
    private String imagePath;
    
    @Column(name = "Redeposite_Remark", length = 252)
    private String redepositeRemark;
    
    @Column(name="Back_To_Town_Remark")
    private String backtotownRemark;
    
    @Column(name="Back_To_Town_FilePath")
    private String backtotownFilePath;
    
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
	
	


	public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

		public String getRedepositeRemark() {
			return redepositeRemark;
		}

		public void setRedepositeRemark(String redepositeRemark) {
			this.redepositeRemark = redepositeRemark;
		}


		
		

	public String getBacktotownRemark() {
			return backtotownRemark;
		}

		public void setBacktotownRemark(String backtotownRemark) {
			this.backtotownRemark = backtotownRemark;
		}

		public String getBacktotownFilePath() {
			return backtotownFilePath;
		}

		public void setBacktotownFilePath(String backtotownFilePath) {
			this.backtotownFilePath = backtotownFilePath;
		}

	public Export(String companyId, String branchId, String sbNo, String sbRequestId, Date sbDate, String iecCode,
				String entityId, String nameOfExporter, String gateInId, Date gateInDate, String serNo, Date serDate,
				Double grossWeight, String uomGrossWeight, String countryOfDestination, String portOfDestination,
				String airwayBillNo, String descriptionOfGoods, String nsdlStatus, String dgdcStatus, String chaNo,
				String chaName, String consoleAgent, Double fobValueInINR, int noOfPackages, String uomOfPackages,
				String status, String createdBy, Date createdDate, String editedBy, Date editedDate, String approvedBy,
				Date approvedDate, String cartingAgent, String partyRepresentativeId, String qrcodeUrl, String pctmNo,
				String tpNo, Date tpDate, Date pctmDate, String mawb, String airlineName, String flightNo,
				String airlineCode, Date flightDate, String holdStatus, String scStatus, String pcStatus,
				String hpStatus, String hppackageno, BigDecimal hpWeight, double imposePenaltyAmount,
				String imposePenaltyRemarks, String reasonforOverride, String overrideDocument, String cancelStatus,
				String cancelRemarks, String gatePassVehicleNo, String pOName, String gatePassStatus, String imagePath,
				String redepositeRemark, String backtotownRemark, String backtotownFilePath, int noc,
				int dgdc_seepz_in_scan, int dgdc_seepz_out_scan, int dgdc_cargo_in_scan, int dgdc_cargo_out_scan,
				Date outDate) {
			super();
			this.companyId = companyId;
			this.branchId = branchId;
			this.sbNo = sbNo;
			this.sbRequestId = sbRequestId;
			this.sbDate = sbDate;
			this.iecCode = iecCode;
			this.entityId = entityId;
			this.nameOfExporter = nameOfExporter;
			this.gateInId = gateInId;
			this.gateInDate = gateInDate;
			this.serNo = serNo;
			this.serDate = serDate;
			this.grossWeight = grossWeight;
			this.uomGrossWeight = uomGrossWeight;
			this.countryOfDestination = countryOfDestination;
			this.portOfDestination = portOfDestination;
			this.airwayBillNo = airwayBillNo;
			this.descriptionOfGoods = descriptionOfGoods;
			this.nsdlStatus = nsdlStatus;
			this.dgdcStatus = dgdcStatus;
			this.chaNo = chaNo;
			this.chaName = chaName;
			this.consoleAgent = consoleAgent;
			this.fobValueInINR = fobValueInINR;
			this.noOfPackages = noOfPackages;
			this.uomOfPackages = uomOfPackages;
			this.status = status;
			this.createdBy = createdBy;
			this.createdDate = createdDate;
			this.editedBy = editedBy;
			this.editedDate = editedDate;
			this.approvedBy = approvedBy;
			this.approvedDate = approvedDate;
			this.cartingAgent = cartingAgent;
			this.partyRepresentativeId = partyRepresentativeId;
			this.qrcodeUrl = qrcodeUrl;
			this.pctmNo = pctmNo;
			this.tpNo = tpNo;
			this.tpDate = tpDate;
			this.pctmDate = pctmDate;
			this.mawb = mawb;
			this.airlineName = airlineName;
			this.flightNo = flightNo;
			this.airlineCode = airlineCode;
			this.flightDate = flightDate;
			this.holdStatus = holdStatus;
			this.scStatus = scStatus;
			this.pcStatus = pcStatus;
			this.hpStatus = hpStatus;
			this.hppackageno = hppackageno;
			this.hpWeight = hpWeight;
			this.imposePenaltyAmount = imposePenaltyAmount;
			this.imposePenaltyRemarks = imposePenaltyRemarks;
			this.reasonforOverride = reasonforOverride;
			this.overrideDocument = overrideDocument;
			this.cancelStatus = cancelStatus;
			this.cancelRemarks = cancelRemarks;
			this.gatePassVehicleNo = gatePassVehicleNo;
			this.pOName = pOName;
			this.gatePassStatus = gatePassStatus;
			this.imagePath = imagePath;
			this.redepositeRemark = redepositeRemark;
			this.backtotownRemark = backtotownRemark;
			this.backtotownFilePath = backtotownFilePath;
			this.noc = noc;
			this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
			this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
			this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
			this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
			this.outDate = outDate;
		}

	public Date getOutDate() {
			return outDate;
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

	public String getGatePassVehicleNo() {
		return gatePassVehicleNo;
	}

	public void setGatePassVehicleNo(String gatePassVehicleNo) {
		this.gatePassVehicleNo = gatePassVehicleNo;
	}

	public String getpOName() {
		return pOName;
	}

	public void setpOName(String pOName) {
		this.pOName = pOName;
	}

	public String getGatePassStatus() {
		return gatePassStatus;
	}

	public void setGatePassStatus(String gatePassStatus) {
		this.gatePassStatus = gatePassStatus;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getCancelStatus() {
		return cancelStatus;
	}

	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	public String getCancelRemarks() {
		return cancelRemarks;
	}

	public void setCancelRemarks(String cancelRemarks) {
		this.cancelRemarks = cancelRemarks;
	}

	public String getOverrideDocument() {
		return overrideDocument;
	}

	public void setOverrideDocument(String overrideDocument) {
		this.overrideDocument = overrideDocument;
	}

	public String getReasonforOverride() {
		return reasonforOverride;
	}

	public void setReasonforOverride(String reasonforOverride) {
		this.reasonforOverride = reasonforOverride;
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

	public String getHpStatus() {
		return hpStatus;
	}

	public void setHpStatus(String hpStatus) {
		this.hpStatus = hpStatus;
	}

	public String getHppackageno() {
		return hppackageno;
	}

	public void setHppackageno(String hppackageno) {
		this.hppackageno = hppackageno;
	}

	public BigDecimal getHpWeight() {
		return hpWeight;
	}

	public void setHpWeight(BigDecimal hpWeight) {
		this.hpWeight = hpWeight;
	}

	public String getScStatus() {
		return scStatus;
	}

	public void setScStatus(String scStatus) {
		this.scStatus = scStatus;
	}

	public String getHoldStatus() {
		return holdStatus;
	}

	public void setHoldStatus(String holdStatus) {
		this.holdStatus = holdStatus;
	}

	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

	public void setAppovedate() {
		Date date = new Date();
		approvedDate = date;

	}

	public void setCurrentDate() {
		Date date = new Date();
		Date formattedDate = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDateStr = sdf.format(date);
		try {
			 formattedDate = sdf.parse(formattedDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (editedDate == null) {

			editedDate = formattedDate ;
		}
		if (createdDate == null) {
			createdDate = formattedDate ;
		}
		if (approvedDate == null) {
			approvedDate = formattedDate ;
		}
	}

	public Export() {
		super();
	}






	public Date getSerDate() {
		return serDate;
	}

	public void setSerDate(Date serDate) {
		this.serDate = serDate;
	}



	public String getGateInId() {
		return gateInId;
	}

	public void setGateInId(String gateInId) {
		this.gateInId = gateInId;
	}

	public Date getGateInDate() {
		return gateInDate;
	}

	public void setGateInDate(Date gateInDate) {
		this.gateInDate = gateInDate;
	}

	

	

	public String getPcStatus() {
		return pcStatus;
	}

	public void setPcStatus(String pcStatus) {
		this.pcStatus = pcStatus;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}




	public String getPctmNo() {
		return pctmNo;
	}

	public void setPctmNo(String pctmNo) {
		this.pctmNo = pctmNo;
	}

	public String getTpNo() {
		return tpNo;
	}

	public void setTpNo(String tpNo) {
		this.tpNo = tpNo;
	}

	public Date getTpDate() {
		return tpDate;
	}

	public void setTpDate(Date tpDate) {
		this.tpDate = tpDate;
	}

	public Date getPctmDate() {
		return pctmDate;
	}

	public void setPctmDate(Date pctmDate) {
		this.pctmDate = pctmDate;
	}

	public String getMawb() {
		return mawb;
	}

	public void setMawb(String mawb) {
		this.mawb = mawb;
	}



	
	public Export(String companyId, String branchId, String sbNo, String sbRequestId, Date sbDate, String iecCode,
			String entityId, String nameOfExporter, String gateInId, Date gateInDate, String serNo, Date serDate,
			Double grossWeight, String uomGrossWeight, String countryOfDestination, String portOfDestination,
			String airwayBillNo, String descriptionOfGoods, String nsdlStatus, String dgdcStatus, String chaNo,
			String chaName, String consoleAgent, Double fobValueInINR, int noOfPackages, String uomOfPackages,
			String status, String createdBy, Date createdDate, String editedBy, Date editedDate, String approvedBy,
			Date approvedDate, String cartingAgent, String partyRepresentativeId, String qrcodeUrl, String pctmNo,
			String tpNo, Date tpDate, Date pctmDate, String mawb, String airlineName, String flightNo,
			String airlineCode, Date flightDate, String holdStatus, String scStatus, String pcStatus, String hpStatus,
			String hppackageno, BigDecimal hpWeight, double imposePenaltyAmount, String imposePenaltyRemarks,
			String reasonforOverride, String overrideDocument, String cancelStatus, String cancelRemarks,
			String gatePassVehicleNo, String pOName, String gatePassStatus, int noc, int dgdc_seepz_in_scan,
			int dgdc_seepz_out_scan, int dgdc_cargo_in_scan, int dgdc_cargo_out_scan) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.sbNo = sbNo;
		this.sbRequestId = sbRequestId;
		this.sbDate = sbDate;
		this.iecCode = iecCode;
		this.entityId = entityId;
		this.nameOfExporter = nameOfExporter;
		this.gateInId = gateInId;
		this.gateInDate = gateInDate;
		this.serNo = serNo;
		this.serDate = serDate;
		this.grossWeight = grossWeight;
		this.uomGrossWeight = uomGrossWeight;
		this.countryOfDestination = countryOfDestination;
		this.portOfDestination = portOfDestination;
		this.airwayBillNo = airwayBillNo;
		this.descriptionOfGoods = descriptionOfGoods;
		this.nsdlStatus = nsdlStatus;
		this.dgdcStatus = dgdcStatus;
		this.chaNo = chaNo;
		this.chaName = chaName;
		this.consoleAgent = consoleAgent;
		this.fobValueInINR = fobValueInINR;
		this.noOfPackages = noOfPackages;
		this.uomOfPackages = uomOfPackages;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.cartingAgent = cartingAgent;
		this.partyRepresentativeId = partyRepresentativeId;
		this.qrcodeUrl = qrcodeUrl;
		this.pctmNo = pctmNo;
		this.tpNo = tpNo;
		this.tpDate = tpDate;
		this.pctmDate = pctmDate;
		this.mawb = mawb;
		this.airlineName = airlineName;
		this.flightNo = flightNo;
		this.airlineCode = airlineCode;
		this.flightDate = flightDate;
		this.holdStatus = holdStatus;
		this.scStatus = scStatus;
		this.pcStatus = pcStatus;
		this.hpStatus = hpStatus;
		this.hppackageno = hppackageno;
		this.hpWeight = hpWeight;
		this.imposePenaltyAmount = imposePenaltyAmount;
		this.imposePenaltyRemarks = imposePenaltyRemarks;
		this.reasonforOverride = reasonforOverride;
		this.overrideDocument = overrideDocument;
		this.cancelStatus = cancelStatus;
		this.cancelRemarks = cancelRemarks;
		this.gatePassVehicleNo = gatePassVehicleNo;
		this.pOName = pOName;
		this.gatePassStatus = gatePassStatus;
		this.noc = noc;
		this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
		this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
		this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
		this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		System.out.println();
		sb.append("+----------------------+-------------------------------------").append(System.lineSeparator());
		sb.append("| Field                | Value                               |").append(System.lineSeparator());
		sb.append("+----------------------+-------------------------------------").append(System.lineSeparator());
		sb.append(String.format("| Company ID            | %-34s |%n", companyId));
		sb.append(String.format("| Branch ID             | %-34s |%n", branchId));
		sb.append(String.format("| SB No                 | %-34s |%n", sbNo));
		sb.append(String.format("| SB Request ID         | %-34s |%n", sbRequestId));
		sb.append(String.format("| SB Date               | %-34s |%n", sbDate));
		sb.append(String.format("| IEC Code              | %-34s |%n", iecCode));
		sb.append(String.format("| Entity ID             | %-34s |%n", entityId));
		sb.append(String.format("| Name of Exporter      | %-34s |%n", nameOfExporter));
		sb.append(String.format("| Ser No                | %-34s |%n", serNo));
		sb.append(String.format("| Gross Weight          | %-34s |%n", grossWeight));
		sb.append(String.format("| UOM Gross Weight      | %-34s |%n", uomGrossWeight));
		sb.append(String.format("| Country of Destination| %-34s |%n", countryOfDestination));
		sb.append(String.format("| Port of Destination   | %-34s |%n", portOfDestination));
		sb.append(String.format("| Airway Bill No        | %-34s |%n", airwayBillNo));
		sb.append(String.format("| Description of Goods  | %-34s |%n", descriptionOfGoods));
		sb.append(String.format("| NSDL Status           | %-34s |%n", nsdlStatus));
		sb.append(String.format("| DGDC Status           | %-34s |%n", dgdcStatus));
		sb.append(String.format("| CHA No                | %-34s |%n", chaNo));
		sb.append(String.format("| CHA Name              | %-34s |%n", chaName));
		sb.append(String.format("| Console Agent         | %-34s |%n", consoleAgent));
		sb.append(String.format("| FOB Value in INR      | %-34s |%n", fobValueInINR));
		sb.append(String.format("| No of Packages        | %-34s |%n", noOfPackages));
		sb.append(String.format("| UOM of Packages       | %-34s |%n", uomOfPackages));
		sb.append(String.format("| Status                | %-34s |%n", status));
		sb.append(String.format("| Created By            | %-34s |%n", createdBy));
		sb.append(String.format("| Created Date          | %-34s |%n", createdDate));
		sb.append(String.format("| Edited By             | %-34s |%n", editedBy));
		sb.append(String.format("| Edited Date           | %-34s |%n", editedDate));
		sb.append(String.format("| Approved By           | %-34s |%n", approvedBy));
		sb.append(String.format("| Approved Date         | %-34s |%n", approvedDate));
		sb.append(String.format("| cartingAgent          | %-34s |%n", cartingAgent));
		sb.append(String.format("| partyRepresentativeId | %-34s |%n", partyRepresentativeId));
		sb.append("+----------------------+-----------------------------------+%n");

		return sb.toString();
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

	public String getSbNo() {
		return sbNo;
	}

	public void setSbNo(String sbNo) {
		this.sbNo = sbNo;
	}

	public String getSbRequestId() {
		return sbRequestId;
	}
	

	public String getCartingAgent() {
		return cartingAgent;
	}

	public void setCartingAgent(String cartingAgent) {
		this.cartingAgent = cartingAgent;
	}

	public String getPartyRepresentativeId() {
		return partyRepresentativeId;
	}

	public void setPartyRepresentativeId(String partyRepresentativeId) {
		this.partyRepresentativeId = partyRepresentativeId;
	}

	public void setSbRequestId(String sbRequestId) {
		this.sbRequestId = sbRequestId;
	}

	public Date getSbDate() {
		return sbDate;
	}

	public void setSbDate(Date sbDate) {
		this.sbDate = sbDate;
	}

	public String getIecCode() {
		return iecCode;
	}

	public void setIecCode(String iecCode) {
		this.iecCode = iecCode;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getNameOfExporter() {
		return nameOfExporter;
	}

	public void setNameOfExporter(String nameOfExporter) {
		this.nameOfExporter = nameOfExporter;
	}

	public String getSerNo() {
		return serNo;
	}

	public void setSerNo(String serNo) {
		this.serNo = serNo;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getUomGrossWeight() {
		return uomGrossWeight;
	}

	public void setUomGrossWeight(String uomGrossWeight) {
		this.uomGrossWeight = uomGrossWeight;
	}

	public String getCountryOfDestination() {
		return countryOfDestination;
	}

	public void setCountryOfDestination(String countryOfDestination) {
		this.countryOfDestination = countryOfDestination;
	}

	public String getPortOfDestination() {
		return portOfDestination;
	}

	public void setPortOfDestination(String portOfDestination) {
		this.portOfDestination = portOfDestination;
	}

	public String getAirwayBillNo() {
		return airwayBillNo;
	}

	public void setAirwayBillNo(String airwayBillNo) {
		this.airwayBillNo = airwayBillNo;
	}

	public String getDescriptionOfGoods() {
		return descriptionOfGoods;
	}

	public void setDescriptionOfGoods(String descriptionOfGoods) {
		this.descriptionOfGoods = descriptionOfGoods;
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

	public String getChaNo() {
		return chaNo;
	}

	public void setChaNo(String chaNo) {
		this.chaNo = chaNo;
	}

	public String getChaName() {
		return chaName;
	}

	public void setChaName(String chaName) {
		this.chaName = chaName;
	}

	public String getConsoleAgent() {
		return consoleAgent;
	}

	public void setConsoleAgent(String consoleAgent) {
		this.consoleAgent = consoleAgent;
	}

	public Double getFobValueInINR() {
		return fobValueInINR;
	}

	public void setFobValueInINR(Double fobValueInINR) {
		this.fobValueInINR = fobValueInINR;
	}

	public int getNoOfPackages() {
		return noOfPackages;
	}

	public void setNoOfPackages(int noOfPackages) {
		this.noOfPackages = noOfPackages;
	}

	public String getUomOfPackages() {
		return uomOfPackages;
	}

	public void setUomOfPackages(String uomOfPackages) {
		this.uomOfPackages = uomOfPackages;
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

}