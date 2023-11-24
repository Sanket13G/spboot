package com.cwms.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "import")
@IdClass(ImportIds.class)
public class Import {

	@Id
    @Column(name = "Company_Id",length = 6)
    private String companyId;

    @Id
    @Column(name = "Branch_Id",length = 6)
    private String branchId;

    @Id
    @Column(name = "Imp_Trans_Id",length = 10)
    private String impTransId;

    @Column(name = "Imp_Trans_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date impTransDate;

    @Column(name = "MAWB",length = 40)
    private String mawb;

    @Column(name = "HAWB",length = 40)
    private String hawb;

    @Column(name = "IGM_No",length = 30)
    private String igmNo;

    @Column(name = "Carting_Agent",length = 30)
    private String cartingAgent;
    
    @Column(name = "Representative_Id",length = 10)
    private String partyRepresentativeId;
    
    @Column(name = "IGM_Date")
    @Temporal(TemporalType.DATE)
    private Date igmDate;

    @Column(name = "SIR_No",length = 10)
    private String sirNo;

    @Column(name = "SIR_Date")
    @Temporal(TemporalType.DATE)
    private Date sirDate;

    @Column(name = "PCTM_No")
    private String pctmNo;

    @Column(name = "TP_No")
    private String tpNo;

    @Column(name = "TP_Date")
    @Temporal(TemporalType.DATE)
    private Date tpDate;

    @Column(name = "Airline_Name")
    private String airlineName;
    
    @Column(name="Airline_Code",length=10)
    private String airlineCode;

    @Column(name = "Flight_No")
    private String flightNo;

    @Column(name = "Flight_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date flightDate;

    @Column(name = "COUNTRY_ORIGIN")
    private String countryOrigin;

    @Column(name = "PORT_ORIGIN")
    private String portOrigin;

    @Column(name = "Importer_Id")
    private String importerId;
    
    @Column(name = "GatePass_Status", length = 1)
	private String gatePassStatus;


    @Column(name = "IEC")
    private String iec;

    @Column(name = "SEZ_ENTITY_ID")
    private String sezEntityId;

    @Column(name = "consoleName")
    private String consoleName;

    @Column(name = "Package_Content_Type")
    private String packageContentType;

    @Column(name = "Parcel_Type")
    private String parcelType;

    @Column(name = "UOM_PACKAGES")
    private String uomPackages;   
      
    

    @Column(name = "Nop")
    private int nop;

    @Column(name = "Import_Remarks")
    private String importRemarks;

    @Column(name = "DESCRIPTION_OF_GOODS")
    private String descriptionOfGoods;

    @Column(name = "CHA_CDE")
    private String chaCde;

    @Column(name = "CHA_Name")
    private String chaName;
    
    @Column(name = "ASSESSABLE_VALUE")
    private String assessableValue;

    @Column(name = "Gross_Weight")
    private BigDecimal grossWeight;

    @Column(name = "BE_REQUEST_ID")
    private String beRequestId;

    @Column(name = "UOM_Weight")
    private String uomWeight;
    
    @Column(name = "BE_No")
    private String beNo;

    @Column(name = "BE_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date beDate;

    @Column(name = "Import_Address")
    private String importAddress;

    @Column(name = "Status",length = 1)
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
    
    @Column(name = "NSDL_Status")
    private String nsdlStatus;
    
    @Column(name = "DGDC_Status")
    private String dgdcStatus;
    
    @Column(name = "CLOSE_STATUS",length=1)
    private String closeStatus;
    
    @Column(name = "Hold_Status",length = 1)
    private String holdStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Hold_Date")
    private Date holdDate;

    @Column(name = "Hold_by")
    private String holdBy;

    @Column(name = "HP_Status",length = 1)
    private String hpStatus;
    
    @Column(name = "HP_Package_No",length = 5)
    private String hppackageno;

    @Column(name = "HP_Weight")
    private BigDecimal hpWeight;

    @Column(name = "PC_Status",length = 1)
    private String pcStatus;

    @Column(name = "SC_Status",length = 1)
    private String scStatus;

    @Column(name = "Cancel_Status",length = 1)
    private String cancelStatus;

    @Column(name = "Cancel_Remarks")
    private String cancelRemarks;

    @Column(name = "Impose_Penalty_Amt")
    private double imposePenaltyAmount;

    @Column(name = "Impose_Penalty_Remarks")
    private String imposePenaltyRemarks;
    
    @Column(name = "Reason_for_Override",length=255)
    private String reasonforOverride;
    
    @Column(name = "NSDL_Override_Docs",length=100)
    private String nsdlStatusDocs;
    
    @Column(name = "Handed_Over_To_Type",length = 1)
    private String handedOverToType;
    
    @Column(name = "Handed_Over_Party_Id",length = 10)
    private String handedOverPartyId;
    
    @Column(name = "Handed_Over_Representative_Id",length = 10)
    private String handedOverRepresentativeId;
    
    
    @Column(name = "Nipt_Status",length = 1)
    private String niptStatus;
    
    @Column(name = "Importer_NameOn_Parcel",length = 250)
    private String importernameOnParcel;
    
    @Column(name = "Qr_Code_Url",length = 255)
    private String qrcodeUrl;
    
    @GeneratedValue
	@Column(name = "Do_Number", length = 10)
	private String doNumber;

    @Temporal(TemporalType.DATE)
	@Column(name = "Do_Date", length = 10)
	private Date doDate;
    
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
	 
	 
	 @Column(name = "NIPT_Custom_Officer_Name",length = 40)
	    private String niptCustomOfficerName;
	 
	 @Column(name = "NIPT_Customs_Officer_Designation",length = 40)
	    private String niptCustomsOfficerDesignation;
	 
	 @Column(name = "NIPT_Deputed_From_Destination:",length = 40)
	    private String niptDeputedFromDestination;
	 
	 @Column(name = "NIPT_Deputed_To_Destination",length = 40)
	    private String niptDeputedToDestination;
	 
	 
	 @Column(name = "NIPT_Date_Of_Escort:")
	    private Date niptDateOfEscort;
	 
	 @Column(name = "NIPT_Approver_Name",length = 40)
	    private String niptApproverName;
	 
	 @Column(name = "NIPT_Approver Designation",length = 40)
	    private String niptApproverDesignation;
	 
	 @Column(name = "NIPT_Approver_Date:")
	    private Date niptApproverDate;
	 
	 
	 @Column(name = "wrong_Deposit_FilePath",length = 150)
	    private String wrongDepositFilePath;
	 
	 @Column(name = "wrong_Deposit_Remarks",length = 100)
	    private String wrongDepositwrongDepositRemarks;
	 
	 @Column(name = "wrong_Deposit_Status",length = 1)
	    private String wrongDepositStatus;  
	 
	 
	 @Column(name = "Detention_Receipt_No",length = 30)
	    private String detentionReceiptNo; 



	public String getDetentionReceiptNo() {
		return detentionReceiptNo;
	}



	public void setDetentionReceiptNo(String detentionReceiptNo) {
		this.detentionReceiptNo = detentionReceiptNo;
	}



	public Import(String companyId, String branchId, String impTransId, Date impTransDate, String mawb, String hawb,
			String igmNo, String cartingAgent, String partyRepresentativeId, Date igmDate, String sirNo, Date sirDate,
			String pctmNo, String tpNo, Date tpDate, String airlineName, String airlineCode, String flightNo,
			Date flightDate, String countryOrigin, String portOrigin, String importerId, String gatePassStatus,
			String iec, String sezEntityId, String consoleName, String packageContentType, String parcelType,
			String uomPackages, int nop, String importRemarks, String descriptionOfGoods, String chaCde, String chaName,
			String assessableValue, BigDecimal grossWeight, String beRequestId, String uomWeight, String beNo,
			Date beDate, String importAddress, String status, String createdBy, Date createdDate, String editedBy,
			Date editedDate, String approvedBy, Date approvedDate, String nsdlStatus, String dgdcStatus,
			String closeStatus, String holdStatus, Date holdDate, String holdBy, String hpStatus, String hppackageno,
			BigDecimal hpWeight, String pcStatus, String scStatus, String cancelStatus, String cancelRemarks,
			double imposePenaltyAmount, String imposePenaltyRemarks, String reasonforOverride, String nsdlStatusDocs,
			String handedOverToType, String handedOverPartyId, String handedOverRepresentativeId, String niptStatus,
			String importernameOnParcel, String qrcodeUrl, String doNumber, Date doDate, String forwardedStatus,
			int noc, int dgdc_seepz_in_scan, int dgdc_seepz_out_scan, int dgdc_cargo_in_scan, int dgdc_cargo_out_scan,
			Date outDate, String niptCustomOfficerName, String niptCustomsOfficerDesignation,
			String niptDeputedFromDestination, String niptDeputedToDestination, Date niptDateOfEscort,
			String niptApproverName, String niptApproverDesignation, Date niptApproverDate, String wrongDepositFilePath,
			String wrongDepositwrongDepositRemarks, String wrongDepositStatus, String detentionReceiptNo) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.impTransId = impTransId;
		this.impTransDate = impTransDate;
		this.mawb = mawb;
		this.hawb = hawb;
		this.igmNo = igmNo;
		this.cartingAgent = cartingAgent;
		this.partyRepresentativeId = partyRepresentativeId;
		this.igmDate = igmDate;
		this.sirNo = sirNo;
		this.sirDate = sirDate;
		this.pctmNo = pctmNo;
		this.tpNo = tpNo;
		this.tpDate = tpDate;
		this.airlineName = airlineName;
		this.airlineCode = airlineCode;
		this.flightNo = flightNo;
		this.flightDate = flightDate;
		this.countryOrigin = countryOrigin;
		this.portOrigin = portOrigin;
		this.importerId = importerId;
		this.gatePassStatus = gatePassStatus;
		this.iec = iec;
		this.sezEntityId = sezEntityId;
		this.consoleName = consoleName;
		this.packageContentType = packageContentType;
		this.parcelType = parcelType;
		this.uomPackages = uomPackages;
		this.nop = nop;
		this.importRemarks = importRemarks;
		this.descriptionOfGoods = descriptionOfGoods;
		this.chaCde = chaCde;
		this.chaName = chaName;
		this.assessableValue = assessableValue;
		this.grossWeight = grossWeight;
		this.beRequestId = beRequestId;
		this.uomWeight = uomWeight;
		this.beNo = beNo;
		this.beDate = beDate;
		this.importAddress = importAddress;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.nsdlStatus = nsdlStatus;
		this.dgdcStatus = dgdcStatus;
		this.closeStatus = closeStatus;
		this.holdStatus = holdStatus;
		this.holdDate = holdDate;
		this.holdBy = holdBy;
		this.hpStatus = hpStatus;
		this.hppackageno = hppackageno;
		this.hpWeight = hpWeight;
		this.pcStatus = pcStatus;
		this.scStatus = scStatus;
		this.cancelStatus = cancelStatus;
		this.cancelRemarks = cancelRemarks;
		this.imposePenaltyAmount = imposePenaltyAmount;
		this.imposePenaltyRemarks = imposePenaltyRemarks;
		this.reasonforOverride = reasonforOverride;
		this.nsdlStatusDocs = nsdlStatusDocs;
		this.handedOverToType = handedOverToType;
		this.handedOverPartyId = handedOverPartyId;
		this.handedOverRepresentativeId = handedOverRepresentativeId;
		this.niptStatus = niptStatus;
		this.importernameOnParcel = importernameOnParcel;
		this.qrcodeUrl = qrcodeUrl;
		this.doNumber = doNumber;
		this.doDate = doDate;
		this.forwardedStatus = forwardedStatus;
		this.noc = noc;
		this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
		this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
		this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
		this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
		this.outDate = outDate;
		this.niptCustomOfficerName = niptCustomOfficerName;
		this.niptCustomsOfficerDesignation = niptCustomsOfficerDesignation;
		this.niptDeputedFromDestination = niptDeputedFromDestination;
		this.niptDeputedToDestination = niptDeputedToDestination;
		this.niptDateOfEscort = niptDateOfEscort;
		this.niptApproverName = niptApproverName;
		this.niptApproverDesignation = niptApproverDesignation;
		this.niptApproverDate = niptApproverDate;
		this.wrongDepositFilePath = wrongDepositFilePath;
		this.wrongDepositwrongDepositRemarks = wrongDepositwrongDepositRemarks;
		this.wrongDepositStatus = wrongDepositStatus;
		this.detentionReceiptNo = detentionReceiptNo;
	}



	public String getGatePassStatus() {
		return gatePassStatus;
	}



	public void setGatePassStatus(String gatePassStatus) {
		this.gatePassStatus = gatePassStatus;
	}



	public String getNiptCustomOfficerName() {
		return niptCustomOfficerName;
	}



	public void setNiptCustomOfficerName(String niptCustomOfficerName) {
		this.niptCustomOfficerName = niptCustomOfficerName;
	}



	public String getNiptCustomsOfficerDesignation() {
		return niptCustomsOfficerDesignation;
	}



	public void setNiptCustomsOfficerDesignation(String niptCustomsOfficerDesignation) {
		this.niptCustomsOfficerDesignation = niptCustomsOfficerDesignation;
	}



	public String getNiptDeputedFromDestination() {
		return niptDeputedFromDestination;
	}



	public void setNiptDeputedFromDestination(String niptDeputedFromDestination) {
		this.niptDeputedFromDestination = niptDeputedFromDestination;
	}



	public String getNiptDeputedToDestination() {
		return niptDeputedToDestination;
	}



	public void setNiptDeputedToDestination(String niptDeputedToDestination) {
		this.niptDeputedToDestination = niptDeputedToDestination;
	}



	public Date getNiptDateOfEscort() {
		return niptDateOfEscort;
	}



	public void setNiptDateOfEscort(Date niptDateOfEscort) {
		this.niptDateOfEscort = niptDateOfEscort;
	}



	public String getNiptApproverName() {
		return niptApproverName;
	}



	public void setNiptApproverName(String niptApproverName) {
		this.niptApproverName = niptApproverName;
	}



	public String getNiptApproverDesignation() {
		return niptApproverDesignation;
	}



	public void setNiptApproverDesignation(String niptApproverDesignation) {
		this.niptApproverDesignation = niptApproverDesignation;
	}



	public Date getNiptApproverDate() {
		return niptApproverDate;
	}



	public void setNiptApproverDate(Date niptApproverDate) {
		this.niptApproverDate = niptApproverDate;
	}



	public String getWrongDepositFilePath() {
		return wrongDepositFilePath;
	}



	public void setWrongDepositFilePath(String wrongDepositFilePath) {
		this.wrongDepositFilePath = wrongDepositFilePath;
	}



	public String getWrongDepositwrongDepositRemarks() {
		return wrongDepositwrongDepositRemarks;
	}



	public void setWrongDepositwrongDepositRemarks(String wrongDepositwrongDepositRemarks) {
		this.wrongDepositwrongDepositRemarks = wrongDepositwrongDepositRemarks;
	}



	public String getWrongDepositStatus() {
		return wrongDepositStatus;
	}



	public void setWrongDepositStatus(String wrongDepositStatus) {
		this.wrongDepositStatus = wrongDepositStatus;
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



	public Import() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getNsdlStatus() {
		return nsdlStatus;
	}

	


	public String getChaName() {
		return chaName;
	}



	public void setChaName(String chaName) {
		this.chaName = chaName;
	}

	
	
	
	


	public Import(String companyId, String branchId, String impTransId, Date impTransDate, String mawb, String hawb,
			String igmNo, String cartingAgent, String partyRepresentativeId, Date igmDate, String sirNo, Date sirDate,
			String pctmNo, String tpNo, Date tpDate, String airlineName, String airlineCode, String flightNo,
			Date flightDate, String countryOrigin, String portOrigin, String importerId, String iec, String sezEntityId,
			String consoleName, String packageContentType, String parcelType, String uomPackages, int nop,
			String importRemarks, String descriptionOfGoods, String chaCde, String chaName, String assessableValue,
			BigDecimal grossWeight, String beRequestId, String uomWeight, String beNo, Date beDate,
			String importAddress, String status, String createdBy, Date createdDate, String editedBy, Date editedDate,
			String approvedBy, Date approvedDate, String nsdlStatus, String dgdcStatus, String closeStatus,
			String holdStatus, Date holdDate, String holdBy, String hpStatus, String hppackageno, BigDecimal hpWeight,
			String pcStatus, String scStatus, String cancelStatus, String cancelRemarks, double imposePenaltyAmount,
			String imposePenaltyRemarks, String reasonforOverride, String nsdlStatusDocs, String handedOverToType,
			String handedOverPartyId, String handedOverRepresentativeId, String niptStatus, String importernameOnParcel,
			String qrcodeUrl, String doNumber, Date doDate, String forwardedStatus, int noc, int dgdc_seepz_in_scan,
			int dgdc_seepz_out_scan, int dgdc_cargo_in_scan, int dgdc_cargo_out_scan, Date outDate) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.impTransId = impTransId;
		this.impTransDate = impTransDate;
		this.mawb = mawb;
		this.hawb = hawb;
		this.igmNo = igmNo;
		this.cartingAgent = cartingAgent;
		this.partyRepresentativeId = partyRepresentativeId;
		this.igmDate = igmDate;
		this.sirNo = sirNo;
		this.sirDate = sirDate;
		this.pctmNo = pctmNo;
		this.tpNo = tpNo;
		this.tpDate = tpDate;
		this.airlineName = airlineName;
		this.airlineCode = airlineCode;
		this.flightNo = flightNo;
		this.flightDate = flightDate;
		this.countryOrigin = countryOrigin;
		this.portOrigin = portOrigin;
		this.importerId = importerId;
		this.iec = iec;
		this.sezEntityId = sezEntityId;
		this.consoleName = consoleName;
		this.packageContentType = packageContentType;
		this.parcelType = parcelType;
		this.uomPackages = uomPackages;
		this.nop = nop;
		this.importRemarks = importRemarks;
		this.descriptionOfGoods = descriptionOfGoods;
		this.chaCde = chaCde;
		this.chaName = chaName;
		this.assessableValue = assessableValue;
		this.grossWeight = grossWeight;
		this.beRequestId = beRequestId;
		this.uomWeight = uomWeight;
		this.beNo = beNo;
		this.beDate = beDate;
		this.importAddress = importAddress;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.nsdlStatus = nsdlStatus;
		this.dgdcStatus = dgdcStatus;
		this.closeStatus = closeStatus;
		this.holdStatus = holdStatus;
		this.holdDate = holdDate;
		this.holdBy = holdBy;
		this.hpStatus = hpStatus;
		this.hppackageno = hppackageno;
		this.hpWeight = hpWeight;
		this.pcStatus = pcStatus;
		this.scStatus = scStatus;
		this.cancelStatus = cancelStatus;
		this.cancelRemarks = cancelRemarks;
		this.imposePenaltyAmount = imposePenaltyAmount;
		this.imposePenaltyRemarks = imposePenaltyRemarks;
		this.reasonforOverride = reasonforOverride;
		this.nsdlStatusDocs = nsdlStatusDocs;
		this.handedOverToType = handedOverToType;
		this.handedOverPartyId = handedOverPartyId;
		this.handedOverRepresentativeId = handedOverRepresentativeId;
		this.niptStatus = niptStatus;
		this.importernameOnParcel = importernameOnParcel;
		this.qrcodeUrl = qrcodeUrl;
		this.doNumber = doNumber;
		this.doDate = doDate;
		this.forwardedStatus = forwardedStatus;
		this.noc = noc;
		this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
		this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
		this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
		this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
		this.outDate = outDate;
	}



	public String getForwardedStatus() {
		return forwardedStatus;
	}



	public void setForwardedStatus(String forwardedStatus) {
		this.forwardedStatus = forwardedStatus;
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



	public String getDoNumber() {
		return doNumber;
	}



	public void setDoNumber(String doNumber) {
		this.doNumber = doNumber;
	}



	public Date getDoDate() {
		return doDate;
	}



	public void setDoDate(Date doDate) {
		this.doDate = doDate;
	}







	


	



	



	public Import(String companyId, String branchId, String impTransId, Date impTransDate, String mawb, String hawb,
			String igmNo, String cartingAgent, String partyRepresentativeId, Date igmDate, String sirNo, Date sirDate,
			String pctmNo, String tpNo, Date tpDate, String airlineName, String airlineCode, String flightNo,
			Date flightDate, String countryOrigin, String portOrigin, String importerId, String iec, String sezEntityId,
			String consoleName, String packageContentType, String parcelType, String uomPackages, int nop,
			String importRemarks, String descriptionOfGoods, String chaCde, String chaName, String assessableValue,
			BigDecimal grossWeight, String beRequestId, String uomWeight, String beNo, Date beDate,
			String importAddress, String status, String createdBy, Date createdDate, String editedBy, Date editedDate,
			String approvedBy, Date approvedDate, String nsdlStatus, String dgdcStatus, String closeStatus,
			String holdStatus, Date holdDate, String holdBy, String hpStatus, String hppackageno, BigDecimal hpWeight,
			String pcStatus, String scStatus, String cancelStatus, String cancelRemarks, double imposePenaltyAmount,
			String imposePenaltyRemarks, String reasonforOverride, String nsdlStatusDocs, String handedOverToType,
			String handedOverPartyId, String handedOverRepresentativeId, String niptStatus, String importernameOnParcel,
			String qrcodeUrl, String doNumber, Date doDate, String forwardedStatus, int noc, int dgdc_seepz_in_scan,
			int dgdc_seepz_out_scan, int dgdc_cargo_in_scan, int dgdc_cargo_out_scan) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.impTransId = impTransId;
		this.impTransDate = impTransDate;
		this.mawb = mawb;
		this.hawb = hawb;
		this.igmNo = igmNo;
		this.cartingAgent = cartingAgent;
		this.partyRepresentativeId = partyRepresentativeId;
		this.igmDate = igmDate;
		this.sirNo = sirNo;
		this.sirDate = sirDate;
		this.pctmNo = pctmNo;
		this.tpNo = tpNo;
		this.tpDate = tpDate;
		this.airlineName = airlineName;
		this.airlineCode = airlineCode;
		this.flightNo = flightNo;
		this.flightDate = flightDate;
		this.countryOrigin = countryOrigin;
		this.portOrigin = portOrigin;
		this.importerId = importerId;
		this.iec = iec;
		this.sezEntityId = sezEntityId;
		this.consoleName = consoleName;
		this.packageContentType = packageContentType;
		this.parcelType = parcelType;
		this.uomPackages = uomPackages;
		this.nop = nop;
		this.importRemarks = importRemarks;
		this.descriptionOfGoods = descriptionOfGoods;
		this.chaCde = chaCde;
		this.chaName = chaName;
		this.assessableValue = assessableValue;
		this.grossWeight = grossWeight;
		this.beRequestId = beRequestId;
		this.uomWeight = uomWeight;
		this.beNo = beNo;
		this.beDate = beDate;
		this.importAddress = importAddress;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.nsdlStatus = nsdlStatus;
		this.dgdcStatus = dgdcStatus;
		this.closeStatus = closeStatus;
		this.holdStatus = holdStatus;
		this.holdDate = holdDate;
		this.holdBy = holdBy;
		this.hpStatus = hpStatus;
		this.hppackageno = hppackageno;
		this.hpWeight = hpWeight;
		this.pcStatus = pcStatus;
		this.scStatus = scStatus;
		this.cancelStatus = cancelStatus;
		this.cancelRemarks = cancelRemarks;
		this.imposePenaltyAmount = imposePenaltyAmount;
		this.imposePenaltyRemarks = imposePenaltyRemarks;
		this.reasonforOverride = reasonforOverride;
		this.nsdlStatusDocs = nsdlStatusDocs;
		this.handedOverToType = handedOverToType;
		this.handedOverPartyId = handedOverPartyId;
		this.handedOverRepresentativeId = handedOverRepresentativeId;
		this.niptStatus = niptStatus;
		this.importernameOnParcel = importernameOnParcel;
		this.qrcodeUrl = qrcodeUrl;
		this.doNumber = doNumber;
		this.doDate = doDate;
		this.forwardedStatus = forwardedStatus;
		this.noc = noc;
		this.dgdc_seepz_in_scan = dgdc_seepz_in_scan;
		this.dgdc_seepz_out_scan = dgdc_seepz_out_scan;
		this.dgdc_cargo_in_scan = dgdc_cargo_in_scan;
		this.dgdc_cargo_out_scan = dgdc_cargo_out_scan;
	}



	public String getAirlineCode() {
		return airlineCode;
	}



	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}



	public String getHppackageno() {
		return hppackageno;
	}


	public void setHppackageno(String hppackageno) {
		this.hppackageno = hppackageno;
	}



	public String getReasonforOverride() {
		return reasonforOverride;
	}


	public void setReasonforOverride(String reasonforOverride) {
		this.reasonforOverride = reasonforOverride;
	}


	public String getNsdlStatusDocs() {
		return nsdlStatusDocs;
	}


	public void setNsdlStatusDocs(String nsdlStatusDocs) {
		this.nsdlStatusDocs = nsdlStatusDocs;
	}


	

	public String getHandedOverToType() {
		return handedOverToType;
	}


	public void setHandedOverToType(String handedOverToType) {
		this.handedOverToType = handedOverToType;
	}


	public String getHandedOverPartyId() {
		return handedOverPartyId;
	}


	public void setHandedOverPartyId(String handedOverPartyId) {
		this.handedOverPartyId = handedOverPartyId;
	}


	public String getHandedOverRepresentativeId() {
		return handedOverRepresentativeId;
	}


	public void setHandedOverRepresentativeId(String handedOverRepresentativeId) {
		this.handedOverRepresentativeId = handedOverRepresentativeId;
	}




	public String getCartingAgent() {
		return cartingAgent;
	}



	
	
	


	public String getHoldStatus() {
		return holdStatus;
	}
		
	public String getNiptStatus() {
		return niptStatus;
	}


	public void setNiptStatus(String niptStatus) {
		this.niptStatus = niptStatus;
	}


	public String getImporternameOnParcel() {
		return importernameOnParcel;
	}


	public void setImporternameOnParcel(String importernameOnParcel) {
		this.importernameOnParcel = importernameOnParcel;
	}


	public String getQrcodeUrl() {
		return qrcodeUrl;
	}


	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}


	public Import(String companyId, String branchId, String impTransId, Date impTransDate, String mawb, String hawb,
			String igmNo, String cartingAgent, String partyRepresentativeId, Date igmDate, String sirNo, Date sirDate,
			String pctmNo, String tpNo, Date tpDate, String airlineName, String flightNo, Date flightDate,
			String countryOrigin, String portOrigin, String importerId, String iec, String sezEntityId,
			String consoleName, String packageContentType, String parcelType, String uomPackages, int nop,
			String importRemarks, String descriptionOfGoods, String chaCde, String assessableValue,
			BigDecimal grossWeight, String beRequestId, String uomWeight, String beNo, Date beDate,
			String importAddress, String status, String createdBy, Date createdDate, String editedBy, Date editedDate,
			String approvedBy, Date approvedDate, String nsdlStatus, String dgdcStatus, String closeStatus,
			String holdStatus, Date holdDate, String holdBy, String hpStatus, String hppackageno, BigDecimal hpWeight,
			String pcStatus, String scStatus, String cancelStatus, String cancelRemarks, double imposePenaltyAmount,
			String imposePenaltyRemarks, String reasonforOverride, String nsdlStatusDocs, String handedOverToType,
			String handedOverPartyId, String handedOverRepresentativeId, String niptStatus, String importernameOnParcel,
			String qrcodeUrl) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.impTransId = impTransId;
		this.impTransDate = impTransDate;
		this.mawb = mawb;
		this.hawb = hawb;
		this.igmNo = igmNo;
		this.cartingAgent = cartingAgent;
		this.partyRepresentativeId = partyRepresentativeId;
		this.igmDate = igmDate;
		this.sirNo = sirNo;
		this.sirDate = sirDate;
		this.pctmNo = pctmNo;
		this.tpNo = tpNo;
		this.tpDate = tpDate;
		this.airlineName = airlineName;
		this.flightNo = flightNo;
		this.flightDate = flightDate;
		this.countryOrigin = countryOrigin;
		this.portOrigin = portOrigin;
		this.importerId = importerId;
		this.iec = iec;
		this.sezEntityId = sezEntityId;
		this.consoleName = consoleName;
		this.packageContentType = packageContentType;
		this.parcelType = parcelType;
		this.uomPackages = uomPackages;
		this.nop = nop;
		this.importRemarks = importRemarks;
		this.descriptionOfGoods = descriptionOfGoods;
		this.chaCde = chaCde;
		this.assessableValue = assessableValue;
		this.grossWeight = grossWeight;
		this.beRequestId = beRequestId;
		this.uomWeight = uomWeight;
		this.beNo = beNo;
		this.beDate = beDate;
		this.importAddress = importAddress;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.nsdlStatus = nsdlStatus;
		this.dgdcStatus = dgdcStatus;
		this.closeStatus = closeStatus;
		this.holdStatus = holdStatus;
		this.holdDate = holdDate;
		this.holdBy = holdBy;
		this.hpStatus = hpStatus;
		this.hppackageno = hppackageno;
		this.hpWeight = hpWeight;
		this.pcStatus = pcStatus;
		this.scStatus = scStatus;
		this.cancelStatus = cancelStatus;
		this.cancelRemarks = cancelRemarks;
		this.imposePenaltyAmount = imposePenaltyAmount;
		this.imposePenaltyRemarks = imposePenaltyRemarks;
		this.reasonforOverride = reasonforOverride;
		this.nsdlStatusDocs = nsdlStatusDocs;
		this.handedOverToType = handedOverToType;
		this.handedOverPartyId = handedOverPartyId;
		this.handedOverRepresentativeId = handedOverRepresentativeId;
		this.niptStatus = niptStatus;
		this.importernameOnParcel = importernameOnParcel;
		this.qrcodeUrl = qrcodeUrl;
	}


	public void setHoldStatus(String holdStatus) {
		this.holdStatus = holdStatus;
	}





	public Date getHoldDate() {
		return holdDate;
	}





	public void setHoldDate(Date holdDate) {
		this.holdDate = holdDate;
	}





	public String getHoldBy() {
		return holdBy;
	}





	public void setHoldBy(String holdBy) {
		this.holdBy = holdBy;
	}





	public String getHpStatus() {
		return hpStatus;
	}





	public void setHpStatus(String hpStatus) {
		this.hpStatus = hpStatus;
	}





	public BigDecimal getHpWeight() {
		return hpWeight;
	}





	public void setHpWeight(BigDecimal hpWeight) {
		this.hpWeight = hpWeight;
	}





	public String getPcStatus() {
		return pcStatus;
	}





	public void setPcStatus(String pcStatus) {
		this.pcStatus = pcStatus;
	}





	public String getScStatus() {
		return scStatus;
	}





	public void setScStatus(String scStatus) {
		this.scStatus = scStatus;
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





	public void setCartingAgent(String cartingAgent) {
		this.cartingAgent = cartingAgent;
	}





	public String getPartyRepresentativeId() {
		return partyRepresentativeId;
	}





	public void setPartyRepresentativeId(String partyRepresentativeId) {
		this.partyRepresentativeId = partyRepresentativeId;
	}





	public String getCloseStatus() {
		return closeStatus;
	}





	public void setCloseStatus(String closeStatus) {
		this.closeStatus = closeStatus;
	}





	public String getNSDL_Status() {
		return nsdlStatus;
	}


	public void setNSDL_Status(String nSDL_Status) {
		nsdlStatus = nSDL_Status;
	}


	public String getDGDC_Status() {
		return dgdcStatus;
	}


	public void setDGDC_Status(String dGDC_Status) {
		dgdcStatus = dGDC_Status;
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

	public String getImpTransId() {
		return impTransId;
	}

	public void setImpTransId(String impTransId) {
		this.impTransId = impTransId;
	}

	public Date getImpTransDate() {
		return impTransDate;
	}

	public void setImpTransDate(Date impTransDate) {
		this.impTransDate = impTransDate;
	}

	public String getMawb() {
		return mawb;
	}

	public void setMawb(String mawb) {
		this.mawb = mawb;
	}

	public String getHawb() {
		return hawb;
	}

	public void setHawb(String hawb) {
		this.hawb = hawb;
	}

	public String getIgmNo() {
		return igmNo;
	}

	public void setIgmNo(String igmNo) {
		this.igmNo = igmNo;
	}

	public Date getIgmDate() {
		return igmDate;
	}

	public void setIgmDate(Date igmDate) {
		this.igmDate = igmDate;
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

	public String getCountryOrigin() {
		return countryOrigin;
	}

	public void setCountryOrigin(String countryOrigin) {
		this.countryOrigin = countryOrigin;
	}

	public String getPortOrigin() {
		return portOrigin;
	}

	public void setPortOrigin(String portOrigin) {
		this.portOrigin = portOrigin;
	}

	public String getImporterId() {
		return importerId;
	}

	public void setImporterId(String importerId) {
		this.importerId = importerId;
	}

	public String getIec() {
		return iec;
	}

	public void setIec(String iec) {
		this.iec = iec;
	}

	public String getSezEntityId() {
		return sezEntityId;
	}

	public void setSezEntityId(String sezEntityId) {
		this.sezEntityId = sezEntityId;
	}

	public String getConsoleName() {
		return consoleName;
	}

	public void setConsoleName(String consoleName) {
		this.consoleName = consoleName;
	}

	public String getPackageContentType() {
		return packageContentType;
	}

	public void setPackageContentType(String packageContentType) {
		this.packageContentType = packageContentType;
	}

	public String getParcelType() {
		return parcelType;
	}

	public void setParcelType(String parcelType) {
		this.parcelType = parcelType;
	}

	public String getUomPackages() {
		return uomPackages;
	}

	public void setUomPackages(String uomPackages) {
		this.uomPackages = uomPackages;
	}

	public int getNop() {
		return nop;
	}

	public void setNop(int nop) {
		this.nop = nop;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public String getDescriptionOfGoods() {
		return descriptionOfGoods;
	}

	public void setDescriptionOfGoods(String descriptionOfGoods) {
		this.descriptionOfGoods = descriptionOfGoods;
	}

	public String getChaCde() {
		return chaCde;
	}

	public void setChaCde(String chaCde) {
		this.chaCde = chaCde;
	}

	public String getAssessableValue() {
		return assessableValue;
	}

	public void setAssessableValue(String assessableValue) {
		this.assessableValue = assessableValue;
	}

	public BigDecimal getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getBeRequestId() {
		return beRequestId;
	}

	public void setBeRequestId(String beRequestId) {
		this.beRequestId = beRequestId;
	}

	public String getUomWeight() {
		return uomWeight;
	}

	public void setUomWeight(String uomWeight) {
		this.uomWeight = uomWeight;
	}

	public String getBeNo() {
		return beNo;
	}

	public void setBeNo(String beNo) {
		this.beNo = beNo;
	}

	public Date getBeDate() {
		return beDate;
	}

	public void setBeDate(Date beDate) {
		this.beDate = beDate;
	}

	public String getImportAddress() {
		return importAddress;
	}

	public void setImportAddress(String importAddress) {
		this.importAddress = importAddress;
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
		return "Import [companyId=" + companyId + ", branchId=" + branchId + ", impTransId=" + impTransId
				+ ", impTransDate=" + impTransDate + ", mawb=" + mawb + ", hawb=" + hawb + ", igmNo=" + igmNo
				+ ", cartingAgent=" + cartingAgent + ", partyRepresentativeId=" + partyRepresentativeId + ", igmDate="
				+ igmDate + ", sirNo=" + sirNo + ", sirDate=" + sirDate + ", pctmNo=" + pctmNo + ", tpNo=" + tpNo
				+ ", tpDate=" + tpDate + ", airlineName=" + airlineName + ", flightNo=" + flightNo + ", flightDate="
				+ flightDate + ", countryOrigin=" + countryOrigin + ", portOrigin=" + portOrigin + ", importerId="
				+ importerId + ", iec=" + iec + ", sezEntityId=" + sezEntityId + ", consoleName=" + consoleName
				+ ", packageContentType=" + packageContentType + ", parcelType=" + parcelType + ", uomPackages="
				+ uomPackages + ", nop=" + nop + ", importRemarks=" + importRemarks + ", descriptionOfGoods="
				+ descriptionOfGoods + ", chaCde=" + chaCde + ", assessableValue=" + assessableValue + ", grossWeight="
				+ grossWeight + ", beRequestId=" + beRequestId + ", uomWeight=" + uomWeight + ", beNo=" + beNo
				+ ", beDate=" + beDate + ", importAddress=" + importAddress + ", status=" + status + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", editedBy=" + editedBy + ", editedDate=" + editedDate
				+ ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + ", nsdlStatus=" + nsdlStatus
				+ ", dgdcStatus=" + dgdcStatus + ", closeStatus=" + closeStatus + ", holdStatus=" + holdStatus
				+ ", holdDate=" + holdDate + ", holdBy=" + holdBy + ", hpStatus=" + hpStatus + ", hppackageno="
				+ hppackageno + ", hpWeight=" + hpWeight + ", pcStatus=" + pcStatus + ", scStatus=" + scStatus
				+ ", cancelStatus=" + cancelStatus + ", cancelRemarks=" + cancelRemarks + ", imposePenaltyAmount="
				+ imposePenaltyAmount + ", imposePenaltyRemarks=" + imposePenaltyRemarks + ", reasonforOverride="
				+ reasonforOverride + ", nsdlStatusDocs=" + nsdlStatusDocs + "]";
	}

	

}