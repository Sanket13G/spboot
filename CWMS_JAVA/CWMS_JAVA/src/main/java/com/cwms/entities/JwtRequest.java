package com.cwms.entities;

public class JwtRequest {
	
	private String username;
	private String password;
	private String branchid;
	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtRequest(String username, String password, String branchid) {
		super();
		this.username = username;
		this.password = password;
		this.branchid = branchid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBranchid() {
		return branchid;
	}
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}
	@Override
	public String toString() {
		return "JwtRequest [username=" + username + ", password=" + password + ", branchid=" + branchid + "]";
	}
	
	
}
