package com.cwms.entities;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@IdClass(ProcessNextIdPK.class)
@Table(name = "processnextid", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"company_Id", "branch_Id", "fin_Year", "process_Id","next_Id"})
})
public class ProcessNextId  implements Serializable{
    @Id
    @Column(name = "Company_Id", length = 6, nullable = false)
    private String companyId;

    @Id
    @Column(name = "Branch_Id", length = 6, nullable = false)
    private String branchId;

    @Id
    @Column(name = "Fin_Year", length = 4, nullable = false)
    private String finYear;

    @Id
    @Column(name = "Process_Id", length = 6, nullable = false)
    private String processId;

    @Column(name = "Next_Id", length = 16, nullable = false)
    private String nextId;

	public ProcessNextId() {
		
	}

	public ProcessNextId(String companyId, String branchId, String finYear, String processId, String nextId) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.finYear = finYear;
		this.processId = processId;
		this.nextId = nextId;
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

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getNextId() {
		return nextId;
	}

	public void setNextId(String nextId) {
		this.nextId = nextId;
	}

    // Getters and Setters (omitted for brevity)
    
    
    
}

