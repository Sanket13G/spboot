package com.cwms.entities;

public class ScanData {
	private String dcoffice;
	private String sezname;
	private String sezunitdevelopercodeveloper;
	private String importexportcode;
	private String entityid;
	private String requestdetails;
	private String requestid;
	private String portofloading;
	private String portofdestination;
	private String countryofdestination;
	private String sbnodate;
	private String customhouseagentnamecode;
	private String assessmentdate;
	private String requeststatus;
	private String consignmentdetails;
	private String rotationnumberdate;
	private String cargodetails;
	private String netrealisablevalueinrs;

	public ScanData() {
		super();
	}

	public ScanData(String dcoffice, String sezname, String sezunitdevelopercodeveloper, String importexportcode,
			String entityid, String requestdetails, String requestid, String portofloading, String portofdestination,
			String countryofdestination, String sbnodate, String customhouseagentnamecode, String assessmentdate,
			String requeststatus, String consignmentdetails, String rotationnumberdate, String cargodetails,
			String netrealisablevalueinrs) {
		super();
		this.dcoffice = dcoffice;
		this.sezname = sezname;
		this.sezunitdevelopercodeveloper = sezunitdevelopercodeveloper;
		this.importexportcode = importexportcode;
		this.entityid = entityid;
		this.requestdetails = requestdetails;
		this.requestid = requestid;
		this.portofloading = portofloading;
		this.portofdestination = portofdestination;
		this.countryofdestination = countryofdestination;
		this.sbnodate = sbnodate;
		this.customhouseagentnamecode = customhouseagentnamecode;
		this.assessmentdate = assessmentdate;
		this.requeststatus = requeststatus;
		this.consignmentdetails = consignmentdetails;
		this.rotationnumberdate = rotationnumberdate;
		this.cargodetails = cargodetails;
		this.netrealisablevalueinrs = netrealisablevalueinrs;
	}

	public String toString() {
		String format = "| %-30s | %-50s |%n";

		String result = "+--------------------------------+--------------------------------------------------+%n";
		result += String.format(format, "Field", "Value");
		result += "+--------------------------------+--------------------------------------------------+%n";
		result += String.format(format, "dcoffice", dcoffice);
		result += String.format(format, "sezname", sezname);
		result += String.format(format, "sezunitdevelopercodeveloper", sezunitdevelopercodeveloper);
		result += String.format(format, "importexportcode", importexportcode);
		result += String.format(format, "entityid", entityid);
		result += String.format(format, "requestdetails", requestdetails);
		result += String.format(format, "requestid", requestid);
		result += String.format(format, "portofloading", portofloading);
		result += String.format(format, "portofdestination", portofdestination);
		result += String.format(format, "countryofdestination", countryofdestination);
		result += String.format(format, "sbnodate", sbnodate);
		result += String.format(format, "customhouseagentnamecode", customhouseagentnamecode);
		result += String.format(format, "assessmentdate", assessmentdate);
		result += String.format(format, "requeststatus", requeststatus);
		result += String.format(format, "consignmentdetails", consignmentdetails);
		result += String.format(format, "rotationnumberdate", rotationnumberdate);
		result += String.format(format, "cargodetails", cargodetails);
		result += String.format(format, "netrealisablevalueinrs", netrealisablevalueinrs);
		result += "+--------------------------------+--------------------------------------------------+%n";

		return result;
	}

	public String getDcoffice() {
		return dcoffice;
	}

	public void setDcoffice(String dcoffice) {
		this.dcoffice = dcoffice;
	}

	public String getSezname() {
		return sezname;
	}

	public void setSezname(String sezname) {
		this.sezname = sezname;
	}

	public String getSezunitdevelopercodeveloper() {
		return sezunitdevelopercodeveloper;
	}

	public void setSezunitdevelopercodeveloper(String sezunitdevelopercodeveloper) {
		this.sezunitdevelopercodeveloper = sezunitdevelopercodeveloper;
	}

	public String getImportexportcode() {
		return importexportcode;
	}

	public void setImportexportcode(String importexportcode) {
		this.importexportcode = importexportcode;
	}

	public String getEntityid() {
		return entityid;
	}

	public void setEntityid(String entityid) {
		this.entityid = entityid;
	}

	public String getRequestdetails() {
		return requestdetails;
	}

	public void setRequestdetails(String requestdetails) {
		this.requestdetails = requestdetails;
	}

	public String getRequestid() {
		return requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

	public String getPortofloading() {
		return portofloading;
	}

	public void setPortofloading(String portofloading) {
		this.portofloading = portofloading;
	}

	public String getPortofdestination() {
		return portofdestination;
	}

	public void setPortofdestination(String portofdestination) {
		this.portofdestination = portofdestination;
	}

	public String getCountryofdestination() {
		return countryofdestination;
	}

	public void setCountryofdestination(String countryofdestination) {
		this.countryofdestination = countryofdestination;
	}

	public String getSbnodate() {
		return sbnodate;
	}

	public void setSbnodate(String sbnodate) {
		this.sbnodate = sbnodate;
	}

	public String getCustomhouseagentnamecode() {
		return customhouseagentnamecode;
	}

	public void setCustomhouseagentnamecode(String customhouseagentnamecode) {
		this.customhouseagentnamecode = customhouseagentnamecode;
	}

	public String getAssessmentdate() {
		return assessmentdate;
	}

	public void setAssessmentdate(String assessmentdate) {
		this.assessmentdate = assessmentdate;
	}

	public String getRequeststatus() {
		return requeststatus;
	}

	public void setRequeststatus(String requeststatus) {
		this.requeststatus = requeststatus;
	}

	public String getConsignmentdetails() {
		return consignmentdetails;
	}

	public void setConsignmentdetails(String consignmentdetails) {
		this.consignmentdetails = consignmentdetails;
	}

	public String getRotationnumberdate() {
		return rotationnumberdate;
	}

	public void setRotationnumberdate(String rotationnumberdate) {
		this.rotationnumberdate = rotationnumberdate;
	}

	public String getCargodetails() {
		return cargodetails;
	}

	public void setCargodetails(String cargodetails) {
		this.cargodetails = cargodetails;
	}

	public String getNetrealisablevalueinrs() {
		return netrealisablevalueinrs;
	}

	public void setNetrealisablevalueinrs(String netrealisablevalueinrs) {
		this.netrealisablevalueinrs = netrealisablevalueinrs;
	}

}
