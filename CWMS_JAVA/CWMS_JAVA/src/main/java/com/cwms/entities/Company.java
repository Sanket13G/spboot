package com.cwms.entities;

import org.hibernate.resource.beans.internal.FallbackBeanInstanceProducer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="company")
public class Company {
    @Id
    @Column(length = 6)
	private String Company_Id;
    @Column(nullable = false,length = 50)
    private String Company_name;
    @Column(length = 15) 
    private String Company_Short_name;
    @Column(length = 6)
    private String Currency_Id;
    @Column(length = 10)
    private String FA_DB_Ac_Code;
    @Column(length = 40,nullable = false)
    private String Address_1;
    @Column(length = 40)
    private String Address_2;
    @Column(length = 40)
    private String Address_3;
    @Column(length = 6,nullable = false)
    private String City;
    @Column(length = 35,nullable = false)
    private String City_Name;
    @Column(length = 10)
    private String PIN;
    @Column(length = 25)
    private String Pan_No;
    @Column(length = 6,nullable = false)
    private String State;
    @Column(length = 2,nullable = false)
    private char State_Code;
    @Column(length = 8,nullable = false)
    private String EI_PIN;
    @Column(length = 6,nullable = false)
    private String Country;
    @Column(length = 15)
    private String Phone_No;
    @Column(length = 15)
    private String Fax_No;
    @Column(length = 35)
    private String Contact_Person;
    @Column(length = 35)
    private String Designation;
    @Column(length = 15)
    private String Contact_Phone_No;
    @Column(length = 15)
    private String Contact_Fax_no;
    @Column(length = 40)
    private String Contact_Email;
    @Column(length = 10,nullable = false)
    private String Created_By;
    @Column(nullable = false)
    private java.util.Date Created_Date;
    @Column(length = 10)
    private String Edited_By;
    @Column
    private java.util.Date Edited_Date;
    @Column(length = 10)
    private String Approved_By;
    @Column
    private java.util.Date Approved_Date;
    @Column(length = 30,nullable = false)
    private String GST_No;
    @Column(length = 30,nullable = false)
    private String CIN_No;
    @Column(length = 1,nullable = false)
    private char Status;
    @Column(length = 10)
    private String FA_Writeoff_Ac_Code;
   
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Company(String company_Id, String company_name, String company_Short_name, String currency_Id,
			String fA_DB_Ac_Code, String address_1, String address_2, String address_3, String city, String city_Name,
			String pIN, String pan_No, String state, char state_Code, String eI_PIN, String country, String phone_No,
			String fax_No, String contact_Person, String designation, String contact_Phone_No, String contact_Fax_no,
			String contact_Email, String created_By, Date created_Date, String edited_By, Date edited_Date,
			String approved_By, Date approved_Date, String gST_No, String cIN_No, char status,
			String fA_Writeoff_Ac_Code) {
		super();
		Company_Id = company_Id;
		Company_name = company_name;
		Company_Short_name = company_Short_name;
		Currency_Id = currency_Id;
		FA_DB_Ac_Code = fA_DB_Ac_Code;
		Address_1 = address_1;
		Address_2 = address_2;
		Address_3 = address_3;
		City = city;
		City_Name = city_Name;
		PIN = pIN;
		Pan_No = pan_No;
		State = state;
		State_Code = state_Code;
		EI_PIN = eI_PIN;
		Country = country;
		Phone_No = phone_No;
		Fax_No = fax_No;
		Contact_Person = contact_Person;
		Designation = designation;
		Contact_Phone_No = contact_Phone_No;
		Contact_Fax_no = contact_Fax_no;
		Contact_Email = contact_Email;
		Created_By = created_By;
		Created_Date = created_Date;
		Edited_By = edited_By;
		Edited_Date = edited_Date;
		Approved_By = approved_By;
		Approved_Date = approved_Date;
		GST_No = gST_No;
		CIN_No = cIN_No;
		Status = status;
		FA_Writeoff_Ac_Code = fA_Writeoff_Ac_Code;
	}

	public String getCompany_Id() {
		return Company_Id;
	}

	public void setCompany_Id(String company_Id) {
		Company_Id = company_Id;
	}

	public String getCompany_name() {
		return Company_name;
	}

	public void setCompany_name(String company_name) {
		Company_name = company_name;
	}

	public String getCompany_Short_name() {
		return Company_Short_name;
	}

	public void setCompany_Short_name(String company_Short_name) {
		Company_Short_name = company_Short_name;
	}

	public String getCurrency_Id() {
		return Currency_Id;
	}

	public void setCurrency_Id(String currency_Id) {
		Currency_Id = currency_Id;
	}

	public String getFA_DB_Ac_Code() {
		return FA_DB_Ac_Code;
	}

	public void setFA_DB_Ac_Code(String fA_DB_Ac_Code) {
		FA_DB_Ac_Code = fA_DB_Ac_Code;
	}

	public String getAddress_1() {
		return Address_1;
	}

	public void setAddress_1(String address_1) {
		Address_1 = address_1;
	}

	public String getAddress_2() {
		return Address_2;
	}

	public void setAddress_2(String address_2) {
		Address_2 = address_2;
	}

	public String getAddress_3() {
		return Address_3;
	}

	public void setAddress_3(String address_3) {
		Address_3 = address_3;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getCity_Name() {
		return City_Name;
	}

	public void setCity_Name(String city_Name) {
		City_Name = city_Name;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String pIN) {
		PIN = pIN;
	}

	public String getPan_No() {
		return Pan_No;
	}

	public void setPan_No(String pan_No) {
		Pan_No = pan_No;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public char getState_Code() {
		return State_Code;
	}

	public void setState_Code(char state_Code) {
		State_Code = state_Code;
	}

	public String getEI_PIN() {
		return EI_PIN;
	}

	public void setEI_PIN(String eI_PIN) {
		EI_PIN = eI_PIN;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getPhone_No() {
		return Phone_No;
	}

	public void setPhone_No(String phone_No) {
		Phone_No = phone_No;
	}

	public String getFax_No() {
		return Fax_No;
	}

	public void setFax_No(String fax_No) {
		Fax_No = fax_No;
	}

	public String getContact_Person() {
		return Contact_Person;
	}

	public void setContact_Person(String contact_Person) {
		Contact_Person = contact_Person;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getContact_Phone_No() {
		return Contact_Phone_No;
	}

	public void setContact_Phone_No(String contact_Phone_No) {
		Contact_Phone_No = contact_Phone_No;
	}

	public String getContact_Fax_no() {
		return Contact_Fax_no;
	}

	public void setContact_Fax_no(String contact_Fax_no) {
		Contact_Fax_no = contact_Fax_no;
	}

	public String getContact_Email() {
		return Contact_Email;
	}

	public void setContact_Email(String contact_Email) {
		Contact_Email = contact_Email;
	}

	public String getCreated_By() {
		return Created_By;
	}

	public void setCreated_By(String created_By) {
		Created_By = created_By;
	}

	public java.util.Date getCreated_Date() {
		return Created_Date;
	}

	public void setCreated_Date(java.util.Date created_Date) {
		Created_Date = created_Date;
	}

	public String getEdited_By() {
		return Edited_By;
	}

	public void setEdited_By(String edited_By) {
		Edited_By = edited_By;
	}

	public java.util.Date getEdited_Date() {
		return Edited_Date;
	}

	public void setEdited_Date(java.util.Date edited_Date) {
		Edited_Date = edited_Date;
	}

	public String getApproved_By() {
		return Approved_By;
	}

	public void setApproved_By(String approved_By) {
		Approved_By = approved_By;
	}

	public java.util.Date getApproved_Date() {
		return Approved_Date;
	}

	public void setApproved_Date(java.util.Date approved_Date) {
		Approved_Date = approved_Date;
	}

	public String getGST_No() {
		return GST_No;
	}

	public void setGST_No(String gST_No) {
		GST_No = gST_No;
	}

	public String getCIN_No() {
		return CIN_No;
	}

	public void setCIN_No(String cIN_No) {
		CIN_No = cIN_No;
	}

	public char getStatus() {
		return Status;
	}

	public void setStatus(char status) {
		Status = status;
	}

	public String getFA_Writeoff_Ac_Code() {
		return FA_Writeoff_Ac_Code;
	}

	public void setFA_Writeoff_Ac_Code(String fA_Writeoff_Ac_Code) {
		FA_Writeoff_Ac_Code = fA_Writeoff_Ac_Code;
	}

	@Override
	public String toString() {
		return "Company [Company_Id=" + Company_Id + ", Company_name=" + Company_name + ", Company_Short_name="
				+ Company_Short_name + ", Currency_Id=" + Currency_Id + ", FA_DB_Ac_Code=" + FA_DB_Ac_Code
				+ ", Address_1=" + Address_1 + ", Address_2=" + Address_2 + ", Address_3=" + Address_3 + ", City="
				+ City + ", City_Name=" + City_Name + ", PIN=" + PIN + ", Pan_No=" + Pan_No + ", State=" + State
				+ ", State_Code=" + State_Code + ", EI_PIN=" + EI_PIN + ", Country=" + Country + ", Phone_No="
				+ Phone_No + ", Fax_No=" + Fax_No + ", Contact_Person=" + Contact_Person + ", Designation="
				+ Designation + ", Contact_Phone_No=" + Contact_Phone_No + ", Contact_Fax_no=" + Contact_Fax_no
				+ ", Contact_Email=" + Contact_Email + ", Created_By=" + Created_By + ", Created_Date=" + Created_Date
				+ ", Edited_By=" + Edited_By + ", Edited_Date=" + Edited_Date + ", Approved_By=" + Approved_By
				+ ", Approved_Date=" + Approved_Date + ", GST_No=" + GST_No + ", CIN_No=" + CIN_No + ", Status="
				+ Status + ", FA_Writeoff_Ac_Code=" + FA_Writeoff_Ac_Code + "]";
	}

	
    
	
}
