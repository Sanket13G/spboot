package com.cwms.helper;

import java.math.BigDecimal;
import java.util.Date;

public class CombinedImportExport {

	private String partyId;
	private String partyName;
	private double holidayStatus;
	private Date date;
	private int nop;
	private int totalPackages;
	private double importScStatus;
	private double importPcStatus;
	private double importHpStatus;
	private BigDecimal importHpWeight;
	private double importpenalty;
	private int exportNoOfPackages;
	private double exportScStatus;
	private double exportPcStatus;
	private double exportHpStatus;
	private BigDecimal exportHpWeight;
	private double exportpenalty;
	private double importRate;
	private double exportRate;
	private int importSubNop;
	private int exportSubNop;
	private double importSubRate;
	private double exportSubRate;
	
	private int demuragesNop;
	private double demuragesRate;
	
	private int niptPackages;
	public CombinedImportExport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDemuragesNop() {
		return demuragesNop;
	}







	public int getNiptPackages() {
		return niptPackages;
	}
	public void setNiptPackages(int niptPackages) {
		this.niptPackages = niptPackages;
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


	public double getImportpenalty() {
		return importpenalty;
	}

	public void setImportpenalty(double importpenalty) {
		this.importpenalty = importpenalty;
	}

	public double getExportpenalty() {
		return exportpenalty;
	}

	public void setExportpenalty(double exportpenalty) {
		this.exportpenalty = exportpenalty;
	}

	





	






		







	






	public CombinedImportExport(String partyId, String partyName, double holidayStatus, Date date, int nop,
			int totalPackages, double importScStatus, double importPcStatus, double importHpStatus,
			BigDecimal importHpWeight, double importpenalty, int exportNoOfPackages, double exportScStatus,
			double exportPcStatus, double exportHpStatus, BigDecimal exportHpWeight, double exportpenalty,
			double importRate, double exportRate, int importSubNop, int exportSubNop, double importSubRate,
			double exportSubRate, int demuragesNop, double demuragesRate, int niptPackages) {
		super();
		this.partyId = partyId;
		this.partyName = partyName;
		this.holidayStatus = holidayStatus;
		this.date = date;
		this.nop = nop;
		this.totalPackages = totalPackages;
		this.importScStatus = importScStatus;
		this.importPcStatus = importPcStatus;
		this.importHpStatus = importHpStatus;
		this.importHpWeight = importHpWeight;
		this.importpenalty = importpenalty;
		this.exportNoOfPackages = exportNoOfPackages;
		this.exportScStatus = exportScStatus;
		this.exportPcStatus = exportPcStatus;
		this.exportHpStatus = exportHpStatus;
		this.exportHpWeight = exportHpWeight;
		this.exportpenalty = exportpenalty;
		this.importRate = importRate;
		this.exportRate = exportRate;
		this.importSubNop = importSubNop;
		this.exportSubNop = exportSubNop;
		this.importSubRate = importSubRate;
		this.exportSubRate = exportSubRate;
		this.demuragesNop = demuragesNop;
		this.demuragesRate = demuragesRate;
		this.niptPackages = niptPackages;
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



	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	
	public double getHolidayStatus() {
		return holidayStatus;
	}

	public void setHolidayStatus(double holidayStatus) {
		this.holidayStatus = holidayStatus;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNop() {
		return nop;
	}
	public void setNop(int nop) {
		this.nop = nop;
	}
	public int getTotalPackages() {
		return totalPackages;
	}
	public void setTotalPackages(int totalPackages) {
		this.totalPackages = totalPackages;
	}
	public double getImportScStatus() {
		return importScStatus;
	}
	public void setImportScStatus(double importScStatus) {
		this.importScStatus = importScStatus;
	}
	public double getImportPcStatus() {
		return importPcStatus;
	}
	public void setImportPcStatus(double importPcStatus) {
		this.importPcStatus = importPcStatus;
	}
	public double getImportHpStatus() {
		return importHpStatus;
	}
	public void setImportHpStatus(double importHpStatus) {
		this.importHpStatus = importHpStatus;
	}
	public BigDecimal getImportHpWeight() {
		return importHpWeight;
	}
	public void setImportHpWeight(BigDecimal importHpWeight) {
		this.importHpWeight = importHpWeight;
	}
	public int getExportNoOfPackages() {
		return exportNoOfPackages;
	}
	public void setExportNoOfPackages(int exportNoOfPackages) {
		this.exportNoOfPackages = exportNoOfPackages;
	}
	public double getExportScStatus() {
		return exportScStatus;
	}
	public void setExportScStatus(double exportScStatus) {
		this.exportScStatus = exportScStatus;
	}
	public double getExportPcStatus() {
		return exportPcStatus;
	}
	public void setExportPcStatus(double exportPcStatus) {
		this.exportPcStatus = exportPcStatus;
	}
	public double getExportHpStatus() {
		return exportHpStatus;
	}
	public void setExportHpStatus(double exportHpStatus) {
		this.exportHpStatus = exportHpStatus;
	}
	public BigDecimal getExportHpWeight() {
		return exportHpWeight;
	}
	public void setExportHpWeight(BigDecimal exportHpWeight) {
		this.exportHpWeight = exportHpWeight;
	}
	
}
