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
@Table(name = "Detention_History")
public class Detention_History {

	@Id
	@GeneratedValue
	@Column(name = "Detenntion_History_Id", length = 6)
	private long detenHistoryId;
	

	@Column(name = "Company_Id", length = 6)
	private String companyId;


	@Column(name = "Branch_Id", length = 6)
	private String branchId;
	
	
	private long siNo;
	
	@Column(name = "File_No", length = 60)
	private String fileNo;
	
	@Column(name = "Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	
	
	@Column(name = "Transaction_Status")
	private String status;
	
	@Column(name = "Officer_Name", length = 30)
	private String officerName;

	@Column(name = "Remarks", length = 255)
	private String remarks;

	public Detention_History() {
		super();
	}

	public Detention_History(long detenHistoryId, String companyId, String branchId, long siNo, String fileNo,
			Date date, String status, String officerName, String remarks) {
		super();
		this.detenHistoryId = detenHistoryId;
		this.companyId = companyId;
		this.branchId = branchId;
		this.siNo = siNo;
		this.fileNo = fileNo;
		this.date = date;
		this.status = status;
		this.officerName = officerName;
		this.remarks = remarks;
	}

	public long getDetenHistoryId() {
		return detenHistoryId;
	}

	public void setDetenHistoryId(long detenHistoryId) {
		this.detenHistoryId = detenHistoryId;
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

	public long getSiNo() {
		return siNo;
	}

	public void setSiNo(long siNo) {
		this.siNo = siNo;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
}
