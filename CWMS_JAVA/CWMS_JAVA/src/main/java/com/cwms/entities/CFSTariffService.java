package com.cwms.entities;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cfstdtrfsrv")
@IdClass(CFSTariffServiceId.class)
public class CFSTariffService 
{
	
	@Id
    @Column(name = "CFS_Tariff_No", length = 10, nullable = false)
    private String cfsTariffNo;

    
    @Column(name = "Company_Id", length = 6, nullable = false)
    private String companyId;
    
    
    
    @Column(name = "Branch_Id", length = 6, nullable = false)
    private String branchId;

      
    @Column(name = "CFS_Amnd_No", length = 3, nullable = false)
    private String cfsAmndNo;

    
    @Column(name = "Service_Id", length = 10, nullable = false)
    private String serviceId;

    @Column(name = "Service_Name", length = 30)
    private String service;
    
    
    @Column(name = "Sr_No")
    private int srNo;

    @Column(name = "CFS_Doc_Ref_No", length = 35, nullable = false)
    private String cfsDocRefNo;

    @Column(name = "Party_Id", length = 10, nullable = false)
    private String partyId;

    @Column(name = "Pay_Party", length = 50, nullable = false)
    private String payParty;

    @Column(name = "Importer_id", length = 10)
    private String importerId;

    @Column(name = "Forwarder_id", length = 6)
    private String forwarderId;

    @Column(name = "Billing_Party", length = 6)
    private String billingParty;

    @Column(name = "On_Account_Of", length = 6)
    private String onAccountOf;

    @Column(name = "Cargo_Movement", length = 1)
    private String cargoMovement;

    @Column(name = "Type_Of_Charges", length = 6)
    private String typeOfCharges;
    
    @Column(name = "Service_Unit", length = 10)
    private String serviceUnit;

    @Column(name = "Service_unit1", length = 10)
    private String serviceUnit1;

    @Column(name = "Criteria", length = 4)
    private String criteria;

    @Column(name = "Negeotiable", length = 3)
    private String negotiable;

    @Column(name = "Currency_Id", length = 3)
    private String currencyId;

    @Column(name = "Commodity", length = 150)
    private String commodity;

    @Column(name = "POL", length = 6)
    private String pol;

    @Column(name = "Cargo_Type", length = 100)
    private String cargoType;

    @Column(name = "Container_Type", length = 10)
    private String containerType;

    @Column(name = "Container_Size", length = 10)
    private String containerSize;

    @Column(name = "Container_Status", length = 1)
    private String containerStatus;

    @Column(name = "Rate")
    private Double rate;

    @Column(name = "Movement_Code_From", length = 6)
    private String movementCodeFrom;

    @Column(name = "Movement_Code_To", length = 6)
    private String movementCodeTo;

    @Column(name = "Minimum_Amt", precision = 18, scale = 2)
    private BigDecimal minimumAmount;

    @Column(name = "Range_Type", length = 10)
    private String rangeType;

    @Column(name = "Discount_Days", length = 4)
    private String discountDays;

    @Column(name = "Discount_Amt", precision = 18, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "Discount_Perc", precision = 4, scale = 2)
    private BigDecimal discountPercentage;

    @Column(name = "Tax_Applicable", length = 1)
    private String taxApplicable;

    @Column(name = "Hazardous", length = 1)
    private String hazardous;

    @Column(name = "Created_By", length = 30)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Created_Date")
    private Date createdDate;

    @Column(name = "Edited_By")
    private String editedBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cfs_ValidateDate")
    private Date cfsValidateDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Edited_Date")
    private Date editedDate;
    

    @Column(name = "Approved_By", length = 30)
    private String approvedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Approved_Date")
    private Date approvedDate;

    @Column(name = "Status", length = 1)
    private String status;
    
    
    @Column(name = "Rate_Calculation")
	private String rateCalculation;
    

    
    
	public String getRateCalculation() {
		return rateCalculation;
	}





	public void setRateCalculation(String rateCalculation) {
		this.rateCalculation = rateCalculation;
	}

	public String getCfsAmndNo() {
		return cfsAmndNo;
	}

	public void setCfsAmndNo(String cfsAmndNo) {
		this.cfsAmndNo = cfsAmndNo;
	}


	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}





	public CFSTariffService() {
		super();
		// TODO Auto-generated constructor stub
	}

	



	public CFSTariffService(String cfsTariffNo, String companyId, String branchId, String cfsAmndNo, String serviceId,
			String service, int srNo, String cfsDocRefNo, String partyId, String payParty, String importerId,
			String forwarderId, String billingParty, String onAccountOf, String cargoMovement, String typeOfCharges,
			String serviceUnit, String serviceUnit1, String criteria, String negotiable, String currencyId,
			String commodity, String pol, String cargoType, String containerType, String containerSize,
			String containerStatus, Double rate, String movementCodeFrom, String movementCodeTo,
			BigDecimal minimumAmount, String rangeType, String discountDays, BigDecimal discountAmount,
			BigDecimal discountPercentage, String taxApplicable, String hazardous, String createdBy, Date createdDate,
			String editedBy, Date cfsValidateDate, Date editedDate, String approvedBy, Date approvedDate, String status,
			String rateCalculation) {
		super();
		this.cfsTariffNo = cfsTariffNo;
		this.companyId = companyId;
		this.branchId = branchId;
		this.cfsAmndNo = cfsAmndNo;
		this.serviceId = serviceId;
		this.service = service;
		this.srNo = srNo;
		this.cfsDocRefNo = cfsDocRefNo;
		this.partyId = partyId;
		this.payParty = payParty;
		this.importerId = importerId;
		this.forwarderId = forwarderId;
		this.billingParty = billingParty;
		this.onAccountOf = onAccountOf;
		this.cargoMovement = cargoMovement;
		this.typeOfCharges = typeOfCharges;
		this.serviceUnit = serviceUnit;
		this.serviceUnit1 = serviceUnit1;
		this.criteria = criteria;
		this.negotiable = negotiable;
		this.currencyId = currencyId;
		this.commodity = commodity;
		this.pol = pol;
		this.cargoType = cargoType;
		this.containerType = containerType;
		this.containerSize = containerSize;
		this.containerStatus = containerStatus;
		this.rate = rate;
		this.movementCodeFrom = movementCodeFrom;
		this.movementCodeTo = movementCodeTo;
		this.minimumAmount = minimumAmount;
		this.rangeType = rangeType;
		this.discountDays = discountDays;
		this.discountAmount = discountAmount;
		this.discountPercentage = discountPercentage;
		this.taxApplicable = taxApplicable;
		this.hazardous = hazardous;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.editedBy = editedBy;
		this.cfsValidateDate = cfsValidateDate;
		this.editedDate = editedDate;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.status = status;
		this.rateCalculation = rateCalculation;
	}





	public String getService() {
		return service;
	}





	public void setService(String service) {
		this.service = service;
	}





	public String getCfsTariffNo() {
		return cfsTariffNo;
	}





	public void setCfsTariffNo(String cfsTariffNo) {
		this.cfsTariffNo = cfsTariffNo;
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





	public String getServiceId() {
		return serviceId;
	}





	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}





	public String getCfsDocRefNo() {
		return cfsDocRefNo;
	}





	public void setCfsDocRefNo(String cfsDocRefNo) {
		this.cfsDocRefNo = cfsDocRefNo;
	}





	public String getPartyId() {
		return partyId;
	}





	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}





	public String getPayParty() {
		return payParty;
	}





	public void setPayParty(String payParty) {
		this.payParty = payParty;
	}





	public String getImporterId() {
		return importerId;
	}





	public void setImporterId(String importerId) {
		this.importerId = importerId;
	}





	public String getForwarderId() {
		return forwarderId;
	}





	public void setForwarderId(String forwarderId) {
		this.forwarderId = forwarderId;
	}





	public String getBillingParty() {
		return billingParty;
	}





	public void setBillingParty(String billingParty) {
		this.billingParty = billingParty;
	}





	public String getOnAccountOf() {
		return onAccountOf;
	}





	public void setOnAccountOf(String onAccountOf) {
		this.onAccountOf = onAccountOf;
	}





	public String getCargoMovement() {
		return cargoMovement;
	}





	public void setCargoMovement(String cargoMovement) {
		this.cargoMovement = cargoMovement;
	}





	public String getTypeOfCharges() {
		return typeOfCharges;
	}





	public void setTypeOfCharges(String typeOfCharges) {
		this.typeOfCharges = typeOfCharges;
	}





	public String getServiceUnit() {
		return serviceUnit;
	}





	public void setServiceUnit(String serviceUnit) {
		this.serviceUnit = serviceUnit;
	}





	public String getServiceUnit1() {
		return serviceUnit1;
	}





	public void setServiceUnit1(String serviceUnit1) {
		this.serviceUnit1 = serviceUnit1;
	}





	public String getCriteria() {
		return criteria;
	}





	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}





	public String getNegotiable() {
		return negotiable;
	}





	public void setNegotiable(String negotiable) {
		this.negotiable = negotiable;
	}





	public String getCurrencyId() {
		return currencyId;
	}





	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}





	public String getCommodity() {
		return commodity;
	}





	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}





	public String getPol() {
		return pol;
	}





	public void setPol(String pol) {
		this.pol = pol;
	}





	public String getCargoType() {
		return cargoType;
	}





	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}





	public String getContainerType() {
		return containerType;
	}





	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}





	public String getContainerSize() {
		return containerSize;
	}





	public void setContainerSize(String containerSize) {
		this.containerSize = containerSize;
	}





	public String getContainerStatus() {
		return containerStatus;
	}





	public void setContainerStatus(String containerStatus) {
		this.containerStatus = containerStatus;
	}









	public Double getRate() {
		return rate;
	}





	public void setRate(Double rate) {
		this.rate = rate;
	}





	public String getMovementCodeFrom() {
		return movementCodeFrom;
	}





	public void setMovementCodeFrom(String movementCodeFrom) {
		this.movementCodeFrom = movementCodeFrom;
	}





	public String getMovementCodeTo() {
		return movementCodeTo;
	}





	public void setMovementCodeTo(String movementCodeTo) {
		this.movementCodeTo = movementCodeTo;
	}





	public BigDecimal getMinimumAmount() {
		return minimumAmount;
	}





	public void setMinimumAmount(BigDecimal minimumAmount) {
		this.minimumAmount = minimumAmount;
	}





	public String getRangeType() {
		return rangeType;
	}





	public void setRangeType(String rangeType) {
		this.rangeType = rangeType;
	}





	public String getDiscountDays() {
		return discountDays;
	}





	public void setDiscountDays(String discountDays) {
		this.discountDays = discountDays;
	}





	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}





	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}





	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}





	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}





	public String getTaxApplicable() {
		return taxApplicable;
	}





	public void setTaxApplicable(String taxApplicable) {
		this.taxApplicable = taxApplicable;
	}





	public String getHazardous() {
		return hazardous;
	}





	public void setHazardous(String hazardous) {
		this.hazardous = hazardous;
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





	public String getEditedBy() {
		return editedBy;
	}





	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}





	public Date getCfsValidateDate() {
		return cfsValidateDate;
	}





	public void setCfsValidateDate(Date cfsValidateDate) {
		this.cfsValidateDate = cfsValidateDate;
	}





	public Date getEditedDate() {
		return editedDate;
	}





	public void setEditedDate(Date editedDate) {
		this.editedDate = editedDate;
	}





	public String getApprovedBy() {
		return approvedBy;
	}





	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}





	public Date getApprovedDate() {
		return approvedDate;
	}





	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}







	
	
	
}