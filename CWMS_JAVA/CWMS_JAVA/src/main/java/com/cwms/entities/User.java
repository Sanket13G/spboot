package com.cwms.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Entity
@Table(name = "userinfo")
@IdClass(UserId.class)
public class User implements Serializable, UserDetails {
	@Id
	@Column(length = 6)
	private String Company_Id;
	@Id
	@Column(length = 100)
	private String User_Id;
	@Id
	@Column(length = 6, nullable = false)
	private String Branch_Id;
	
	@Column(length = 255, nullable = false)
	private String User_Name;
	@Column(length = 200, nullable = true)
	private String User_Type;
	@Column(length = 200, nullable = false)
	private String User_Password;
	@Column(length = 50)
	private String Mapped_User;
	@Column(length = 50)
	private String User_Email;
	@Column(length = 1)
	private char Stop_Trans;
	@Column(length = 150)
	private String Comments;
	@Column(length = 50)
	private String Created_By;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date Created_Date;
	@Column(length = 30)
	private String Edited_By;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date Edited_Date;
	@Column(length = 30)
	private String Approved_By;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date Approved_Date;
	@Column(length = 1)
	private String Status;
	@Column(length = 30)
	private String role;
	
	@Column(length=10)
	private String mobile;
	
	@Column(length=10)
	private String OTP;
	
	@Column(length=10)
	private String defaultotp;

	@Column(name="Login_type",length=15)
	private String logintype;
	
	@Column(name="Login_type_Id",length=15)
	private String logintypeid;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	



	public String getLogintype() {
		return logintype;
	}






	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}






	public String getLogintypeid() {
		return logintypeid;
	}






	public void setLogintypeid(String logintypeid) {
		this.logintypeid = logintypeid;
	}






	public String getDefaultotp() {
		return defaultotp;
	}



	public void setDefaultotp(String defaultotp) {
		this.defaultotp = defaultotp;
	}







	public User(String company_Id, String user_Id, String branch_Id, String user_Name, String user_Type,
			String user_Password, String mapped_User, String user_Email, char stop_Trans, String comments,
			String created_By, Date created_Date, String edited_By, Date edited_Date, String approved_By,
			Date approved_Date, String status, String role, String mobile, String oTP, String defaultotp,
			String logintype, String logintypeid) {
		super();
		Company_Id = company_Id;
		User_Id = user_Id;
		Branch_Id = branch_Id;
		User_Name = user_Name;
		User_Type = user_Type;
		User_Password = user_Password;
		Mapped_User = mapped_User;
		User_Email = user_Email;
		Stop_Trans = stop_Trans;
		Comments = comments;
		Created_By = created_By;
		Created_Date = created_Date;
		Edited_By = edited_By;
		Edited_Date = edited_Date;
		Approved_By = approved_By;
		Approved_Date = approved_Date;
		Status = status;
		this.role = role;
		this.mobile = mobile;
		OTP = oTP;
		this.defaultotp = defaultotp;
		this.logintype = logintype;
		this.logintypeid = logintypeid;
	}






	public String getCompany_Id() {
		return Company_Id;
	}

	public void setCompany_Id(String company_Id) {
		Company_Id = company_Id;
	}

	public String getUser_Id() {
		return User_Id;
	}

	public void setUser_Id(String user_Id) {
		User_Id = user_Id;
	}

	public String getBranch_Id() {
		return Branch_Id;
	}

	public void setBranch_Id(String branch_Id) {
		Branch_Id = branch_Id;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	public String getUser_Password() {
		return User_Password;
	}

	public void setUser_Password(String user_Password) {
		User_Password = user_Password;
	}

	public String getMapped_User() {
		return Mapped_User;
	}

	public void setMapped_User(String mapped_User) {
		Mapped_User = mapped_User;
	}

	public String getUser_Email() {
		return User_Email;
	}

	public void setUser_Email(String user_Email) {
		User_Email = user_Email;
	}

	public char getStop_Trans() {
		return Stop_Trans;
	}

	public void setStop_Trans(char stop_Trans) {
		Stop_Trans = stop_Trans;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
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

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getUser_Id();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return getUser_Password();
	}

	
	
	
	@Override
	public String toString() {
		return "User [Company_Id=" + Company_Id + ", User_Id=" + User_Id + ", Branch_Id=" + Branch_Id + ", User_Name="
				+ User_Name + ", User_Type=" + User_Type + ", User_Password=" + User_Password + ", Mapped_User="
				+ Mapped_User + ", User_Email=" + User_Email + ", Stop_Trans=" + Stop_Trans + ", Comments=" + Comments
				+ ", Created_By=" + Created_By + ", Created_Date=" + Created_Date + ", Edited_By=" + Edited_By
				+ ", Edited_Date=" + Edited_Date + ", Approved_By=" + Approved_By + ", Approved_Date=" + Approved_Date
				+ ", Status=" + Status + ", role=" + role + "]";
	}

	
	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}



	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public User(String company_Id, String user_Id, String branch_Id, String user_Name, String user_Type,
			String user_Password, String mapped_User, String user_Email, char stop_Trans, String comments,
			String created_By, Date created_Date, String edited_By, Date edited_Date, String approved_By,
			Date approved_Date, String status, String role, String mobile, String oTP) {
		super();
		Company_Id = company_Id;
		User_Id = user_Id;
		Branch_Id = branch_Id;
		User_Name = user_Name;
		User_Type = user_Type;
		User_Password = user_Password;
		Mapped_User = mapped_User;
		User_Email = user_Email;
		Stop_Trans = stop_Trans;
		Comments = comments;
		Created_By = created_By;
		Created_Date = created_Date;
		Edited_By = edited_By;
		Edited_Date = edited_Date;
		Approved_By = approved_By;
		Approved_Date = approved_Date;
		Status = status;
		this.role = role;
		this.mobile = mobile;
		OTP = oTP;
	}

	public String getUser_Type() {
		return User_Type;
	}

	public void setUser_Type(String user_Type) {
		User_Type = user_Type;
	}

public void setCurrentSystemDate() {
Date date = new Date();
	if(Created_Date==null)
	{
		Created_Date=date;
		
	}
	if(Approved_Date==null)
		Approved_Date=date;
	}

}