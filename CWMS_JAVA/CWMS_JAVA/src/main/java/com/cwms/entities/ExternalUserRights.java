package com.cwms.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.*;

@Entity
@IdClass(ExternalUserRightsId.class)
@Table(name = "externaluserrights")
public class ExternalUserRights implements Serializable {
	@Id
    @Column(name = "Company_Id", length = 6, nullable = false)
    private String company_Id;

    @Id
    @Column(name = "User_Id", length = 14, nullable = false)
    private String user_Id;

    @Id
    @Column(name = "Process_Id", length = 6, nullable = false)
    private String process_Id;

    @Id
    @Column(name = "Branch_Id", length = 6, nullable = false)
    private String branch_Id;

    @Column(name = "Allow_Read", length = 1)
    private String allow_Read;

    @Column(name = "Allow_Create", length = 1)
    private String allow_Create;

    @Column(name = "Allow_Update", length = 1)
    private String allow_Update;

    @Column(name = "Allow_Delete", length = 1)
    private String allow_Delete;

    @Column(name = "Allow_Approve", length = 1)
    private String allow_Approve;

    @Column(name = "Allow_Close", length = 1)
    private String allow_Close;

    @Column(name = "Allow_Unclose", length = 1)
    private String allow_Unclose;

    @Column(name = "Allow_Post", length = 1)
    private String allow_Post;

    @Column(name = "Allow_Unpost", length = 1)
    private String allow_Unpost;

    @Column(name = "Favorite", length = 1)
    private String favorite;

    @Column(name = "Created_By", length = 10, nullable = false)
    private String created_By;

    @Column(name = "Created_Date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_Date;

    @Column(name = "Edited_By", length = 10)
    private String edited_By;

    @Column(name = "Edited_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date edited_Date;

    @Column(name = "Approved_By", length = 10)
    private String approved_By;

    @Column(name = "Approved_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approved_Date;

    @Column(name = "Status", length = 1)
    private String status;

	public ExternalUserRights() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExternalUserRights(String company_Id, String user_Id, String process_Id, String branch_Id, String allow_Read,
			String allow_Create, String allow_Update, String allow_Delete, String allow_Approve, String allow_Close,
			String allow_Unclose, String allow_Post, String allow_Unpost, String favorite, String created_By,
			Date created_Date, String edited_By, Date edited_Date, String approved_By, Date approved_Date,
			String status) {
		super();
		this.company_Id = company_Id;
		this.user_Id = user_Id;
		this.process_Id = process_Id;
		this.branch_Id = branch_Id;
		this.allow_Read = allow_Read;
		this.allow_Create = allow_Create;
		this.allow_Update = allow_Update;
		this.allow_Delete = allow_Delete;
		this.allow_Approve = allow_Approve;
		this.allow_Close = allow_Close;
		this.allow_Unclose = allow_Unclose;
		this.allow_Post = allow_Post;
		this.allow_Unpost = allow_Unpost;
		this.favorite = favorite;
		this.created_By = created_By;
		this.created_Date = created_Date;
		this.edited_By = edited_By;
		this.edited_Date = edited_Date;
		this.approved_By = approved_By;
		this.approved_Date = approved_Date;
		this.status = status;
	}

	public String getCompany_Id() {
		return company_Id;
	}

	public void setCompany_Id(String company_Id) {
		this.company_Id = company_Id;
	}

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public String getProcess_Id() {
		return process_Id;
	}

	public void setProcess_Id(String process_Id) {
		this.process_Id = process_Id;
	}

	public String getBranch_Id() {
		return branch_Id;
	}

	public void setBranch_Id(String branch_Id) {
		this.branch_Id = branch_Id;
	}

	public String getAllow_Read() {
		return allow_Read;
	}

	public void setAllow_Read(String allow_Read) {
		this.allow_Read = allow_Read;
	}

	public String getAllow_Create() {
		return allow_Create;
	}

	public void setAllow_Create(String allow_Create) {
		this.allow_Create = allow_Create;
	}

	public String getAllow_Update() {
		return allow_Update;
	}

	public void setAllow_Update(String allow_Update) {
		this.allow_Update = allow_Update;
	}

	public String getAllow_Delete() {
		return allow_Delete;
	}

	public void setAllow_Delete(String allow_Delete) {
		this.allow_Delete = allow_Delete;
	}

	public String getAllow_Approve() {
		return allow_Approve;
	}

	public void setAllow_Approve(String allow_Approve) {
		this.allow_Approve = allow_Approve;
	}

	public String getAllow_Close() {
		return allow_Close;
	}

	public void setAllow_Close(String allow_Close) {
		this.allow_Close = allow_Close;
	}

	public String getAllow_Unclose() {
		return allow_Unclose;
	}

	public void setAllow_Unclose(String allow_Unclose) {
		this.allow_Unclose = allow_Unclose;
	}

	public String getAllow_Post() {
		return allow_Post;
	}

	public void setAllow_Post(String allow_Post) {
		this.allow_Post = allow_Post;
	}

	public String getAllow_Unpost() {
		return allow_Unpost;
	}

	public void setAllow_Unpost(String allow_Unpost) {
		this.allow_Unpost = allow_Unpost;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public String getCreated_By() {
		return created_By;
	}

	public void setCreated_By(String created_By) {
		this.created_By = created_By;
	}

	public Date getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Date created_Date) {
		this.created_Date = created_Date;
	}

	public String getEdited_By() {
		return edited_By;
	}

	public void setEdited_By(String edited_By) {
		this.edited_By = edited_By;
	}

	public Date getEdited_Date() {
		return edited_Date;
	}

	public void setEdited_Date(Date edited_Date) {
		this.edited_Date = edited_Date;
	}

	public String getApproved_By() {
		return approved_By;
	}

	public void setApproved_By(String approved_By) {
		this.approved_By = approved_By;
	}

	public Date getApproved_Date() {
		return approved_Date;
	}

	public void setApproved_Date(Date approved_Date) {
		this.approved_Date = approved_Date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserRights [company_Id=" + company_Id + ", user_Id=" + user_Id + ", process_Id=" + process_Id
				+ ", branch_Id=" + branch_Id + ", allow_Read=" + allow_Read + ", allow_Create=" + allow_Create
				+ ", allow_Update=" + allow_Update + ", allow_Delete=" + allow_Delete + ", allow_Approve="
				+ allow_Approve + ", allow_Close=" + allow_Close + ", allow_Unclose=" + allow_Unclose + ", allow_Post="
				+ allow_Post + ", allow_Unpost=" + allow_Unpost + ", favorite=" + favorite + ", created_By="
				+ created_By + ", created_Date=" + created_Date + ", edited_By=" + edited_By + ", edited_Date="
				+ edited_Date + ", approved_By=" + approved_By + ", approved_Date=" + approved_Date + ", status="
				+ status + "]";
	}
    
    
    
}
