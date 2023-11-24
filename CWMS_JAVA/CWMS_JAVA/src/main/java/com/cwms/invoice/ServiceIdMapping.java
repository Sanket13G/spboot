package com.cwms.invoice;
import jakarta.persistence.*;

@Entity
public class ServiceIdMapping 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyId;

    @Column(nullable = false)
    private String branchId;
    
    @Column(name="Service_Name")
    private String serviceName;
    
   @Column(name="Service_Id")
   private String serviceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	

	public ServiceIdMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public ServiceIdMapping(Long id, String companyId, String branchId, String serviceName, String serviceId) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.branchId = branchId;
		this.serviceName = serviceName;
		this.serviceId = serviceId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	
    


}
