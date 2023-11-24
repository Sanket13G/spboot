package com.cwms.entities;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="InvoicePackages")
public class InvoicePackages 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long SrNo;
	
	@Column(name = "Company_Id", length = 6)
	private String companyId;

	@Column(name = "Branch_Id", length = 6)
	private String branchId;
	
	@Column(name = "Invoice_No", length = 6)
	private String invoiceNO;
	
	@Column(name = "Bill_No", length = 6)
	public String billNO;
	
	@Column(name = "Party_Id", length = 6)
	private String partyId;

    @Column(name = "Invoice_Date")
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;
    
    @Column(name = "Import_Nop", length = 6)
   	private int importNop;
       
    @Column(name = "ImportSub_Nop", length = 6)
	private int importSubNop;
    
    @Column(name = "Export_Nop", length = 6)
	private int exportNop;
    
    @Column(name = "ExportSub_Nop", length = 6)
	private int exportSubNop;
    
    
    @Column(name = "Holiday_Nop", length = 6)
	private int holidaySubNop;
    
    @Column(name = "ExportSPLCart_Nop", length = 6)
   	private int exportSplCartNop;
       
    @Column(name = "ExportPc_Nop", length = 6, nullable = false)
   	private int exportPcNop;
       
    @Column(name = "ExportHp_Nop", length = 6, nullable = false)
   	private int exportHpNop;
       
    @Column(name = "ExportOC_Nop", length = 6, nullable = false)
   	private int exportOcNop;
    
    

    @Column(name = "ImportSPLCart_Nop", length = 6)
	private int importSplCartNop;
    
    @Column(name = "ImportPc_Nop", length = 6)
	private int importPcNop;
    
    @Column(name = "ImportHp_Nop", length = 6)
	private int importHpNop;
    
    @Column(name = "ImportOC_Nop", length = 6)
	private int importOcNop;
    
//    Rates
    
    @Column(name = "Import_Rate", length = 6)
   	private double importRate;
    
    @Column(name = "ImportSub_Rate", length = 6)
	private double importSubRate;
    
    @Column(name = "Export_Rate", length = 6)
	private double exportRate;   
      
    @Column(name = "ExportSub_Rate", length = 6)
	private double exportSubRate;
     
    @Column(name = "Holiday_Rate", length = 6)
	private double holidayRate;
    
    
    
    @Column(name = "ExportSPLCart_Rate", length = 6)
   	private double exportSplCartRate;
       
    @Column(name = "ExportPc_Rate", length = 6, nullable = false)
   	private double exportPcRate;
       
    @Column(name = "ExportHp_Rate", length = 6, nullable = false)
   	private double exportHpRate;
       
    @Column(name = "ExportOC_Rate", length = 6, nullable = false)
   	private double exportOcRate;
    
    
    @Column(name = "ImportSPLCart_Rate", length = 6)
	private double importSplCartRate;
    
    @Column(name = "ImportPc_Rate", length = 6)
	private double importPcRate;
    
    @Column(name = "ImportHp_Rate", length = 6)
	private double importHpRate;
    
    @Column(name = "ImportOC_Rate", length = 6)
	private double importOcRate;
    
    private int demuragesNop;
	private double demuragesRate;
	
	private int niptPackages;
    
    
    
	public int getNiptPackages() {
		return niptPackages;
	}






	public void setNiptPackages(int niptPackages) {
		this.niptPackages = niptPackages;
	}






	public int getDemuragesNop() {
		return demuragesNop;
	}






	public void setDemuragesNop(int demuragesNop) {
		this.demuragesNop = demuragesNop;
	}






	public double getDemuragesRate() {
		return demuragesRate;
	}






	public void setDemuragesRate(double demuragesRate) {
		this.demuragesRate = demuragesRate;
	}






	public InvoicePackages() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	







	





	public InvoicePackages(String companyId, String branchId, String invoiceNO, String billNO,
			String partyId, Date invoiceDate, int importNop, int importSubNop, int exportNop, int exportSubNop,
			int holidaySubNop, int exportSplCartNop, int exportPcNop, int exportHpNop, int exportOcNop,
			int importSplCartNop, int importPcNop, int importHpNop, int importOcNop, double importRate,
			double importSubRate, double exportRate, double exportSubRate, double holidayRate, double exportSplCartRate,
			double exportPcRate, double exportHpRate, double exportOcRate, double importSplCartRate,
			double importPcRate, double importHpRate, double importOcRate, int demuragesNop, double demuragesRate,
			int niptPackages) {
		super();
		
		this.companyId = companyId;
		this.branchId = branchId;
		this.invoiceNO = invoiceNO;
		this.billNO = billNO;
		this.partyId = partyId;
		this.invoiceDate = invoiceDate;
		this.importNop = importNop;
		this.importSubNop = importSubNop;
		this.exportNop = exportNop;
		this.exportSubNop = exportSubNop;
		this.holidaySubNop = holidaySubNop;
		this.exportSplCartNop = exportSplCartNop;
		this.exportPcNop = exportPcNop;
		this.exportHpNop = exportHpNop;
		this.exportOcNop = exportOcNop;
		this.importSplCartNop = importSplCartNop;
		this.importPcNop = importPcNop;
		this.importHpNop = importHpNop;
		this.importOcNop = importOcNop;
		this.importRate = importRate;
		this.importSubRate = importSubRate;
		this.exportRate = exportRate;
		this.exportSubRate = exportSubRate;
		this.holidayRate = holidayRate;
		this.exportSplCartRate = exportSplCartRate;
		this.exportPcRate = exportPcRate;
		this.exportHpRate = exportHpRate;
		this.exportOcRate = exportOcRate;
		this.importSplCartRate = importSplCartRate;
		this.importPcRate = importPcRate;
		this.importHpRate = importHpRate;
		this.importOcRate = importOcRate;
		this.demuragesNop = demuragesNop;
		this.demuragesRate = demuragesRate;
		this.niptPackages = niptPackages;
	}






	public Long getSrNo() {
		return SrNo;
	}

	public void setSrNo(Long srNo) {
		SrNo = srNo;
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

	public String getBillNO() {
		return billNO;
	}

	public void setBillNO(String billNO) {
		this.billNO = billNO;
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

	public int getImportNop() {
		return importNop;
	}

	public void setImportNop(int importNop) {
		this.importNop = importNop;
	}

	public int getExportNop() {
		return exportNop;
	}

	public void setExportNop(int exportNop) {
		this.exportNop = exportNop;
	}

	public int getImportSubNop() {
		return importSubNop;
	}

	public void setImportSubNop(int importSubNop) {
		this.importSubNop = importSubNop;
	}

	public int getExportSubNop() {
		return exportSubNop;
	}

	public void setExportSubNop(int exportSubNop) {
		this.exportSubNop = exportSubNop;
	}

	public int getHolidaySubNop() {
		return holidaySubNop;
	}

	public void setHolidaySubNop(int holidaySubNop) {
		this.holidaySubNop = holidaySubNop;
	}

	public int getImportSplCartNop() {
		return importSplCartNop;
	}

	public void setImportSplCartNop(int importSplCartNop) {
		this.importSplCartNop = importSplCartNop;
	}

	public int getImportPcNop() {
		return importPcNop;
	}

	public void setImportPcNop(int importPcNop) {
		this.importPcNop = importPcNop;
	}

	public int getImportHpNop() {
		return importHpNop;
	}

	public void setImportHpNop(int importHpNop) {
		this.importHpNop = importHpNop;
	}

	public int getImportOcNop() {
		return importOcNop;
	}

	public void setImportOcNop(int importOcNop) {
		this.importOcNop = importOcNop;
	}

	public int getExportSplCartNop() {
		return exportSplCartNop;
	}

	public void setExportSplCartNop(int exportSplCartNop) {
		this.exportSplCartNop = exportSplCartNop;
	}

	public int getExportPcNop() {
		return exportPcNop;
	}

	public void setExportPcNop(int exportPcNop) {
		this.exportPcNop = exportPcNop;
	}

	public int getExportHpNop() {
		return exportHpNop;
	}

	public void setExportHpNop(int exportHpNop) {
		this.exportHpNop = exportHpNop;
	}

	public int getExportOcNop() {
		return exportOcNop;
	}

	public void setExportOcNop(int exportOcNop) {
		this.exportOcNop = exportOcNop;
	}

	public double getImportRate() {
		return importRate;
	}

	public void setImportRate(double importRate) {
		this.importRate = importRate;
	}

	public double getExportRate() {
		return exportRate;
	}

	public void setExportRate(double exportRate) {
		this.exportRate = exportRate;
	}

	public double getImportSubRate() {
		return importSubRate;
	}

	public void setImportSubRate(double importSubRate) {
		this.importSubRate = importSubRate;
	}

	public double getExportSubRate() {
		return exportSubRate;
	}

	public void setExportSubRate(double exportSubRate) {
		this.exportSubRate = exportSubRate;
	}

	public double getHolidayRate() {
		return holidayRate;
	}

	public void setHolidayRate(double holidayRate) {
		this.holidayRate = holidayRate;
	}

	public double getImportSplCartRate() {
		return importSplCartRate;
	}

	public void setImportSplCartRate(double importSplCartRate) {
		this.importSplCartRate = importSplCartRate;
	}

	public double getImportPcRate() {
		return importPcRate;
	}

	public void setImportPcRate(double importPcRate) {
		this.importPcRate = importPcRate;
	}

	public double getImportHpRate() {
		return importHpRate;
	}

	public void setImportHpRate(double importHpRate) {
		this.importHpRate = importHpRate;
	}

	public double getImportOcRate() {
		return importOcRate;
	}

	public void setImportOcRate(double importOcRate) {
		this.importOcRate = importOcRate;
	}

	public double getExportSplCartRate() {
		return exportSplCartRate;
	}

	public void setExportSplCartRate(double exportSplCartRate) {
		this.exportSplCartRate = exportSplCartRate;
	}

	public double getExportPcRate() {
		return exportPcRate;
	}

	public void setExportPcRate(double exportPcRate) {
		this.exportPcRate = exportPcRate;
	}

	public double getExportHpRate() {
		return exportHpRate;
	}

	public void setExportHpRate(double exportHpRate) {
		this.exportHpRate = exportHpRate;
	}

	public double getExportOcRate() {
		return exportOcRate;
	}

	public void setExportOcRate(double exportOcRate) {
		this.exportOcRate = exportOcRate;
	}






	@Override
	public String toString() {
		return "InvoicePackages [SrNo=" + SrNo + ", companyId=" + companyId + ", branchId=" + branchId + ", invoiceNO="
				+ invoiceNO + ", billNO=" + billNO + ", partyId=" + partyId + ", invoiceDate=" + invoiceDate
				+ ", importNop=" + importNop + ", importSubNop=" + importSubNop + ", exportNop=" + exportNop
				+ ", exportSubNop=" + exportSubNop + ", holidaySubNop=" + holidaySubNop + ", exportSplCartNop="
				+ exportSplCartNop + ", exportPcNop=" + exportPcNop + ", exportHpNop=" + exportHpNop + ", exportOcNop="
				+ exportOcNop + ", importSplCartNop=" + importSplCartNop + ", importPcNop=" + importPcNop
				+ ", importHpNop=" + importHpNop + ", importOcNop=" + importOcNop + ", importRate=" + importRate
				+ ", importSubRate=" + importSubRate + ", exportRate=" + exportRate + ", exportSubRate=" + exportSubRate
				+ ", holidayRate=" + holidayRate + ", exportSplCartRate=" + exportSplCartRate + ", exportPcRate="
				+ exportPcRate + ", exportHpRate=" + exportHpRate + ", exportOcRate=" + exportOcRate
				+ ", importSplCartRate=" + importSplCartRate + ", importPcRate=" + importPcRate + ", importHpRate="
				+ importHpRate + ", importOcRate=" + importOcRate + "]";
	}

	
	
	
}
