package com.cwms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShippingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Serial_ID;
	private String DC_Office;
	private String SEZ_Name;
	private String SEZ_Unit_Developer_Co_Developer;
	private String Import_Export_Code;
	private String entityId;
	private String Request_Details;
	private String Request_ID;
	private String Port_of_Loading;
	private String Port_of_Destination;
	private String Country_of_Destination;
	private String SB_No_Date;
	private String Custom_House_Agent_Name_Code;
	private String Assessment_Date;
	private String Request_Status;
	private String Consignment_Details;
	private String Rotation_Number_Date;
	private String Cargo_Details;
	private String Net_Realisable_Value_in_Rs;
	
	
	public ShippingDetails(String dC_Office, String sEZ_Name, String sEZ_Unit_Developer_Co_Developer,
			String import_Export_Code, String entityId, String request_Details, String request_ID,
			String port_of_Loading, String port_of_Destination, String country_of_Destination, String sB_No_Date,
			String custom_House_Agent_Name_Code, String assessment_Date, String request_Status,
			String consignment_Details, String rotation_Number_Date, String cargo_Details,
			String net_Realisable_Value_in_Rs) {
		super();
		DC_Office = dC_Office;
		SEZ_Name = sEZ_Name;
		SEZ_Unit_Developer_Co_Developer = sEZ_Unit_Developer_Co_Developer;
		Import_Export_Code = import_Export_Code;
		this.entityId = entityId;
		Request_Details = request_Details;
		Request_ID = request_ID;
		Port_of_Loading = port_of_Loading;
		Port_of_Destination = port_of_Destination;
		Country_of_Destination = country_of_Destination;
		SB_No_Date = sB_No_Date;
		Custom_House_Agent_Name_Code = custom_House_Agent_Name_Code;
		Assessment_Date = assessment_Date;
		Request_Status = request_Status;
		Consignment_Details = consignment_Details;
		Rotation_Number_Date = rotation_Number_Date;
		Cargo_Details = cargo_Details;
		Net_Realisable_Value_in_Rs = net_Realisable_Value_in_Rs;
	}



	public Integer getSerial_ID() {
		return Serial_ID;
	}

	public void setSerial_ID(Integer serial_ID) {
		Serial_ID = serial_ID;
	}

	public String getDC_Office() {
		return DC_Office;
	}

	public void setDC_Office(String dC_Office) {
		DC_Office = dC_Office;
	}

	public String getSEZ_Name() {
		return SEZ_Name;
	}

	public void setSEZ_Name(String sEZ_Name) {
		SEZ_Name = sEZ_Name;
	}

	public String getSEZ_Unit_Developer_Co_Developer() {
		return SEZ_Unit_Developer_Co_Developer;
	}

	public void setSEZ_Unit_Developer_Co_Developer(String sEZ_Unit_Developer_Co_Developer) {
		SEZ_Unit_Developer_Co_Developer = sEZ_Unit_Developer_Co_Developer;
	}

	public String getImport_Export_Code() {
		return Import_Export_Code;
	}

	public void setImport_Export_Code(String import_Export_Code) {
		Import_Export_Code = import_Export_Code;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getRequest_Details() {
		return Request_Details;
	}

	public void setRequest_Details(String request_Details) {
		Request_Details = request_Details;
	}

	public String getRequest_ID() {
		return Request_ID;
	}

	public void setRequest_ID(String request_ID) {
		Request_ID = request_ID;
	}

	public String getPort_of_Loading() {
		return Port_of_Loading;
	}

	public void setPort_of_Loading(String port_of_Loading) {
		Port_of_Loading = port_of_Loading;
	}

	public String getPort_of_Destination() {
		return Port_of_Destination;
	}

	public void setPort_of_Destination(String port_of_Destination) {
		Port_of_Destination = port_of_Destination;
	}

	public String getCountry_of_Destination() {
		return Country_of_Destination;
	}

	public void setCountry_of_Destination(String country_of_Destination) {
		Country_of_Destination = country_of_Destination;
	}

	public String getSB_No_Date() {
		return SB_No_Date;
	}

	public void setSB_No_Date(String sB_No_Date) {
		SB_No_Date = sB_No_Date;
	}

	public String getCustom_House_Agent_Name_Code() {
		return Custom_House_Agent_Name_Code;
	}

	public void setCustom_House_Agent_Name_Code(String custom_House_Agent_Name_Code) {
		Custom_House_Agent_Name_Code = custom_House_Agent_Name_Code;
	}

	public String getAssessment_Date() {
		return Assessment_Date;
	}

	public void setAssessment_Date(String assessment_Date) {
		Assessment_Date = assessment_Date;
	}

	public String getRequest_Status() {
		return Request_Status;
	}

	public void setRequest_Status(String request_Status) {
		Request_Status = request_Status;
	}

	public String getConsignment_Details() {
		return Consignment_Details;
	}

	public void setConsignment_Details(String consignment_Details) {
		Consignment_Details = consignment_Details;
	}

	public String getRotation_Number_Date() {
		return Rotation_Number_Date;
	}

	public void setRotation_Number_Date(String rotation_Number_Date) {
		Rotation_Number_Date = rotation_Number_Date;
	}

	public String getCargo_Details() {
		return Cargo_Details;
	}

	public void setCargo_Details(String cargo_Details) {
		Cargo_Details = cargo_Details;
	}

	public String getNet_Realisable_Value_in_Rs() {
		return Net_Realisable_Value_in_Rs;
	}

	public void setNet_Realisable_Value_in_Rs(String net_Realisable_Value_in_Rs) {
		Net_Realisable_Value_in_Rs = net_Realisable_Value_in_Rs;
	}

	public ShippingDetails() {
		super();
	}

	@Override
	public String toString() {
		return "ShippingDetails [Serial_ID=" + Serial_ID + ", DC_Office=" + DC_Office + ", SEZ_Name=" + SEZ_Name
				+ ", SEZ_Unit_Developer_Co_Developer=" + SEZ_Unit_Developer_Co_Developer + ", Import_Export_Code="
				+ Import_Export_Code + ", entityId=" + entityId + ", Request_Details=" + Request_Details
				+ ", Request_ID=" + Request_ID + ", Port_of_Loading=" + Port_of_Loading + ", Port_of_Destination="
				+ Port_of_Destination + ", Country_of_Destination=" + Country_of_Destination + ", SB_No_Date="
				+ SB_No_Date + ", Custom_House_Agent_Name_Code=" + Custom_House_Agent_Name_Code + ", Assessment_Date="
				+ Assessment_Date + ", Request_Status=" + Request_Status + ", Consignment_Details="
				+ Consignment_Details + ", Rotation_Number_Date=" + Rotation_Number_Date + ", Cargo_Details="
				+ Cargo_Details + ", Net_Realisable_Value_in_Rs=" + Net_Realisable_Value_in_Rs + "]";
	}
	

	
}