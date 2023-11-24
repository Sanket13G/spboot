package com.cwms.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@IdClass(PartyLoaId.class)
@Table(name="Party_LOA")
public class PartyLOA{

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loaSerIdSeq")
    @SequenceGenerator(initialValue = 1, name = "loaSerIdSeq", sequenceName = "loaSerIdSeq")
    @Column(name = "loaSer_Id")
    private int loaSerId;

	@Id
	@Column(name = "Company_Id", length = 6)
	private String companyId;

	@Id
	@Column(name = "Branch_Id", length = 6)
	private String branchId;

	@Id
	@Column(name = "Party_Id", length = 6)
	private String partyId;

	@Id
	@Column(name = "LOA_NUMBER", length = 80, nullable = false)
	private String loaNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOA_ISSUE_DATE")
	private Date loaIssueDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OLD_LOA_EXPIRY_DATE")
	private Date oldLoaExpiryDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NEW_LOA_EXPIRY_DATE")
	private Date newLoaExpiryDate;

	@Lob
	@Column(name = "Image_Path", length = 155)
	private String imagePath;

	@Column(name = "Created_By", length = 20, nullable = false)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created_Date", nullable = false)
	private Date createdDate;

	@Column(name = "Status", length = 1)
	private String status;

	@Override
	public String toString() {
	    return String.format(
	        "+-------------------+----------------------------------------+\n" +
	        "| Column            | Value                                  |\n" +
	        "+-------------------+----------------------------------------+\n" +
	        "| loaSerId          | %-38s |\n" +
	        "| companyId          | %-38s |\n" +
	        "| branchId          | %-38s |\n" +
	        "| partyId           | %-38s |\n" +
	        "| loaNumber         | %-38s |\n" +
	        "| loaIssueDate      | %-38s |\n" +
	        "| oldLoaExpiryDate  | %-38s |\n" +
	        "| newLoaExpiryDate  | %-38s |\n" +
	        "| imagePath         | %-38s |\n" +
	        "| createdBy         | %-38s |\n" +
	        "| createdDate       | %-38s |\n" +
	        "| status            | %-38s |\n" +
	        "+-------------------+----------------------------------------+",
	        loaSerId, companyId, branchId, partyId, loaNumber, loaIssueDate, oldLoaExpiryDate,
	        newLoaExpiryDate, imagePath, createdBy, createdDate, status);
	}
	

	public PartyLOA() {
		super();
	}

	public PartyLOA(int loaSerId, String companyId, String branchId, String partyId, String loaNumber,
			Date loaIssueDate, Date oldLoaExpiryDate, Date newLoaExpiryDate, String imagePath, String createdBy,
			Date createdDate, String status) {
		super();
		this.loaSerId = loaSerId;
		this.companyId = companyId;
		this.branchId = branchId;
		this.partyId = partyId;
		this.loaNumber = loaNumber;
		this.loaIssueDate = loaIssueDate;
		this.oldLoaExpiryDate = oldLoaExpiryDate;
		this.newLoaExpiryDate = newLoaExpiryDate;
		this.imagePath = imagePath;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.status = status;
	}

	public int getLoaSerId() {
		return loaSerId;
	}

	public void setLoaSerId(int loaSerId) {
		this.loaSerId = loaSerId;
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

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getLoaNumber() {
		return loaNumber;
	}

	public void setLoaNumber(String loaNumber) {
		this.loaNumber = loaNumber;
	}

	public Date getLoaIssueDate() {
		return loaIssueDate;
	}

	public void setLoaIssueDate(Date loaIssueDate) {
		this.loaIssueDate = loaIssueDate;
	}

	public Date getOldLoaExpiryDate() {
		return oldLoaExpiryDate;
	}

	public void setOldLoaExpiryDate(Date oldLoaExpiryDate) {
		this.oldLoaExpiryDate = oldLoaExpiryDate;
	}

	public Date getNewLoaExpiryDate() {
		return newLoaExpiryDate;
	}

	public void setNewLoaExpiryDate(Date newLoaExpiryDate) {
		this.newLoaExpiryDate = newLoaExpiryDate;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCurrentDate() {
		Date date=  new Date();
		createdDate=date;
	}

	public PartyLOA(String companyId, String branchId, String partyId, String loaNumber, Date loaIssueDate,
			Date oldLoaExpiryDate, Date newLoaExpiryDate, String imagePath, String createdBy, Date createdDate,
			String status) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.partyId = partyId;
		this.loaNumber = loaNumber;
		this.loaIssueDate = loaIssueDate;
		this.oldLoaExpiryDate = oldLoaExpiryDate;
		this.newLoaExpiryDate = newLoaExpiryDate;
		this.imagePath = imagePath;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.status = status;
	}

	
	
}