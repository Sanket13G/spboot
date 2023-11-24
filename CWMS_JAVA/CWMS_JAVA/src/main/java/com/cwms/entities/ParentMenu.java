package com.cwms.entities;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.*;

@Entity
@Table(name="PMenu")
@IdClass(ParentMenuId.class)
public class ParentMenu {
	@Id
	@Column(length = 6,nullable = false)
	private String Company_Id;
	@Id
	@Column(length = 6,nullable = false)
	private String Branch_Id;
	@Id
	@Column(length = 5,nullable = false)
	private String PMenu_Id;

	@Column(name = "Process_id",length = 6)
	private String processId;
	
	@Column(length = 30,nullable = false)
	private String PMenu_Name;
	@Column(length = 1,nullable = false)
	private char Child_Menu_Status;
	@Column(length = 1,nullable = false)
    private char page_status;
	@Column(length = 50)
	private String picons;
	@Column(length = 30)
	private String parent_page_links;
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "pmenu_Id",orphanRemoval = true)
	@JsonIgnore
	private List<ChildMenu> cm = new ArrayList<>();

	
	public ParentMenu() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ParentMenu(String pMenu_Id, String processId, String pMenu_Name, char child_Menu_Status, char page_status,
			String picons, String parent_page_links, List<ChildMenu> cm) {
		super();
		PMenu_Id = pMenu_Id;
		this.processId = processId;
		PMenu_Name = pMenu_Name;
		Child_Menu_Status = child_Menu_Status;
		this.page_status = page_status;
		this.picons = picons;
		this.parent_page_links = parent_page_links;
		this.cm = cm;
	}


	public String getPMenu_Id() {
		return PMenu_Id;
	}


	public void setPMenu_Id(String pMenu_Id) {
		PMenu_Id = pMenu_Id;
	}


	public String getProcessId() {
		return processId;
	}


	public void setProcessId(String processId) {
		this.processId = processId;
	}


	public String getPMenu_Name() {
		return PMenu_Name;
	}


	public void setPMenu_Name(String pMenu_Name) {
		PMenu_Name = pMenu_Name;
	}


	public char getChild_Menu_Status() {
		return Child_Menu_Status;
	}


	public void setChild_Menu_Status(char child_Menu_Status) {
		Child_Menu_Status = child_Menu_Status;
	}


	public char getPage_status() {
		return page_status;
	}


	public void setPage_status(char page_status) {
		this.page_status = page_status;
	}


	public String getPicons() {
		return picons;
	}


	public void setPicons(String picons) {
		this.picons = picons;
	}


	public String getParent_page_links() {
		return parent_page_links;
	}


	public void setParent_page_links(String parent_page_links) {
		this.parent_page_links = parent_page_links;
	}


	public List<ChildMenu> getCm() {
		return cm;
	}


	public void setCm(List<ChildMenu> cm) {
		this.cm = cm;
	}

	
}
