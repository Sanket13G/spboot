package com.cwms.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cwms.entities.Import;
@Service
public interface ImportService 
{
	public List<Import> getByMAWB(String CompId,String branchId,String mawbno);
	public Import addImport(Import import2);
	public Import updateImport(Import import2);
	public Import getByMAWBANdHAWB(String CompId,String branchId,String transId,String MAWB, String HAWB, String SirNo);	
	public List<Import> getAll(String CompId,String branchId);
//	Import findByCompanyIdAnsBranchIdAndimpTransId(String compId,String branchId,String impTransId);
	public void deleteImport(Import import2);
	public List<Import> updateAll(List<Import>Import);
	public Import findByRequestId(String CompId,String branchId,String mawb,String requestId);
	public Import findBytransIdAndSirNo(String CompId,String branchId,String transId,String Sirno);
	public List<Import> findByCompanyIdAndBranchIdAndDgdcStatus (String companyId, String branchId);
	public List<Import> findByCompanyIdAndBranchIdAndImporterIdAndDgdcStatus( String companyId, String branchId,String importerId,String type);
	List<Import> findByImportsForReceivedCratingAgents(String companyId, String branchId,String carting, String representatavi);
	public Import findForBillNumber(String CompId,String branchId,String mawb,String Hawb,String IgmNo);
	public Import findForInvoice(String companyId, String branchId, Date sirDate,String importerId , int ImpoNop, String pcStatus, String scStatus,String hpStatus);
	public List<Object[]> getStockData(String compId,String branchId);
	public List<Object[]> getStockDataForDetention(String compId,String branchId);
	
	
	public Import findImportWithCriteria(String CompId,String branchId,Date sirDate);
	public List<Import> findByPartyId(String companyId, String branchId, String partyId ,Date startDate,Date endDate);	
	public List<Import> findByPartyIdofSirDate(String companyId, String branchId, String partyId ,Date startDate);
	public Import getByMAWBANdHAWB222(String CompId,String branchId,String MAWB, String HAWB);
	public boolean existdetentionNumber(String companyId, String branchId, String detentionNumber);
	public Import findForPersonalCarriage(String CompId,String branchId,String detentionNumber);
	public boolean getByMAWBAndHawbDuplicate(String CompId,String branchId,String mawbno ,String hawbno);
}