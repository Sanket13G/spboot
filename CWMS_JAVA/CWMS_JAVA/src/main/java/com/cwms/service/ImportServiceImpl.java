package com.cwms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cwms.entities.Import;
import com.cwms.entities.ImportDgdcDeliveryStatus;
import com.cwms.entities.ImportNsdlDeliveryStatus;
import com.cwms.repository.ImportDgdcDeliveryStatusRepositary;
import com.cwms.repository.ImportNsdlDeliveryStatusRepositary;
import com.cwms.repository.ImportRepo;
import com.cwms.repository.ImportRepository;
import com.cwms.repository.DefaultParyDetailsRepository;
@Service
public class ImportServiceImpl implements ImportService {

	@Autowired
	public DefaultParyDetailsRepository defaultParty;
	
	@Autowired
	public ImportRepository importRepositary;

	
	@Autowired
	public ImportRepo importRepo;
	@Autowired
	private ImportDgdcDeliveryStatusRepositary ImportDgdcDeliveryStatusRepositary;
	@Autowired
	private ImportNsdlDeliveryStatusRepositary ImportNsdlDeliveryStatusRepositary;
	
	@Override
	public List<Import> getByMAWB(String CompId,String branchId,String mawbno) {
		// TODO Auto-generated method stub
		return importRepo.findByCompanyIdAndBranchIdAndMawbAndStatusNotOrderBySirNoDesc(CompId,branchId,mawbno,"D");
	}

	@Override
	public Import addImport(Import import2) {
		// TODO Auto-generated method stub
		return importRepo.save(import2);
	}

	@Override
	public Import updateImport(Import import2) {
		// TODO Auto-generated method stub
		return importRepo.save(import2);
	}

	@Override
	public Import findByRequestId(String CompId, String branchId, String mawb, String requestId) {
		// TODO Auto-generated method stub
		return importRepo.findByCompanyIdAndBranchIdAndMawbAndBeRequestIdAndStatusNot(CompId, branchId, mawb, requestId,"D");
	}

	
	
	@Override
	public Import getByMAWBANdHAWB(String CompId,String branchId,String transId,String MAWB, String HAWB, String SirNo) {
		// TODO Auto-generated method stub
		return importRepo.findByCompanyIdAndBranchIdAndImpTransIdAndMawbAndHawbAndSirNo(CompId,branchId,transId,MAWB,HAWB,SirNo);
	}

	@Override
	public List<Import> getAll(String CompId,String branchId) {
		// TODO Auto-generated method stub
		return importRepo.findByCompanyIdAndBranchIdAndStatusNot(CompId,branchId,"D");
	}

//	@Override
//	public Import findByCompanyIdAnsBranchIdAndimpTransId(String compId, String branchId, String impTransId) {
//		// TODO Auto-generated method stub
//		return importRepo.findByCompanyIdAndBranchIdAndImpTransId(compId, branchId, impTransId);
//	}

	@Override
	public void deleteImport(Import import2) {
		
		importRepo.delete(import2);
	}

	@Override
	public Import findBytransIdAndSirNo(String CompId, String branchId, String transId, String Sirno) {
		// TODO Auto-generated method stub
		return importRepo.findByCompanyIdAndBranchIdAndImpTransIdAndSirNo(CompId, branchId, transId, Sirno);
	}

	@Override
	public List<Import> updateAll(List<Import> Import) {
		// TODO Auto-generated method stub
		return importRepo.saveAll(Import);
	}

	@Override
	public List<Import> findByCompanyIdAndBranchIdAndDgdcStatus(String companyId, String branchId) {
		
		return importRepo.findByCompanyIdAndBranchIdAndDgdcStatusAndStatusNotAndCloseStatusAndPcStatusAndHoldStatusNot(companyId, branchId,"Handed over to DGDC Cargo","D","Y","N","H");
	}

//	@Override
//	public List<Import> findByCompanyIdAndBranchIdAndImporterIdAndDgdcStatus(String companyId, String branchId,
//			String importerId) {
//		// TODO Auto-generated method stub
//		return importRepo.findByCompanyIdAndBranchIdAndImporterIdAndDgdcStatusAndNsdlStatusContaining(companyId, branchId, importerId, "Handed over to DGDC SEEPZ","Approved");
//	}
	
	
	@Override
	public List<Import> findByCompanyIdAndBranchIdAndImporterIdAndDgdcStatus(String companyId, String branchId,
	        String importerId,String type) {
		
		
		System.out.println("*****************************"+ type +"**********************");
	    List<ImportDgdcDeliveryStatus> dgdcDeliveryStatusList = ImportDgdcDeliveryStatusRepositary.findByCompanyIdAndBranchId(companyId, branchId);
	    List<ImportNsdlDeliveryStatus> nsdlDeliveryStatusList = ImportNsdlDeliveryStatusRepositary.findByCompanyIdAndBranchId(companyId, branchId);

	    List<Import> matchedImports = new ArrayList();
;
	    List<Import> importList = new ArrayList();
	    
	    if("party".equals(type))
	    {
	    	System.out.println("*****************************Party**********************");
	    	 importList = importRepo.findByCompanyIdAndBranchIdAndImporterIdAndHoldStatusNotAndForwardedStatusNot(companyId, branchId, importerId,"Y","FWD_OUT");
	    }
	    if("cha".equals(type))
	    {
	    	List<String> partyIds = defaultParty.findUseIdsByCompanyIdAndBranchIdAndImpCHA(companyId, branchId, importerId);
	    	
	    	System.out.println("*****************************Cha**********************");
	    	
	    	for (String partyId : partyIds) {
	            List<Import> imports = importRepo.findByCompanyIdAndBranchIdAndImporterIdAndHoldStatusNotAndForwardedStatusNot(companyId, branchId, partyId, "Y", "FWD_OUT");
	            
	            // Add the found imports to the importList
	            importList.addAll(imports);
	        }
	    	
	    }
	    
	    
	 	    // Filter Import records based on whether nsdlStatus and dgdcStatus match any records in the respective lists
	    matchedImports = importList.stream()
	            .filter(importItem ->
	                    nsdlDeliveryStatusList.stream().anyMatch(nsdl -> nsdl.getNsdlDeliveryStaus().equals(importItem.getNSDL_Status())) &&
	                    dgdcDeliveryStatusList.stream().anyMatch(dgdc -> dgdc.getDgdcDeliveryStaus().equals(importItem.getDGDC_Status())))
	            .collect(Collectors.toList());

	    return matchedImports;
	}



	@Override
	public List<Import> findByImportsForReceivedCratingAgents(String companyId, String branchId,String carting, String representatavi) {
		
		return importRepo.findByCompanyIdAndBranchIdAndCartingAgentAndPartyRepresentativeIdAndDgdcStatusAndStatusNotAndCloseStatusAndPcStatusAndHoldStatusNot(companyId, branchId,carting,representatavi,"Entry at DGDC SEEPZ Gate","D","Y","N","H");
	}
	
	
	
//	@Override
//	public List<Object[]> getStockData(String compId, String branchId) {
//		// TODO Auto-generated method stub
//		return importRepo.getCombinedStockData(compId, branchId);
//	}
//	
	
	
	@Override
	public List<Object[]> getStockData(String compId, String branchId) {
		// TODO Auto-generated method stub
		return importRepo.getCombinedStockData(compId, branchId);
	}
	
	@Override
	public List<Object[]> getStockDataForDetention(String compId, String branchId) {
		// TODO Auto-generated method stub
		return importRepo.getCombinedStockDataDetention(compId, branchId);
		
		
	}
	
	
	@Override
	public Import findImportWithCriteria(String CompId, String branchId, Date sirDate) {
		// TODO Auto-generated method stub
		return importRepo.findFirstByCompanyIdAndBranchIdAndSirDateAndNiptStatusAndStatusNot(CompId, branchId, sirDate, "Y", "D");
	}
	
	
	@Override
	public List<Import> findByPartyId(String companyId, String branchId, String partyId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return importRepo.findByCompanyIdAndBranchIdAndImporterIdAndSirDateBetweenAndStatusNot(companyId, branchId, partyId, startDate, endDate, "D");
	}
	
	@Override
	public List<Import> findByPartyIdofSirDate(String companyId, String branchId, String partyId, Date startDate) {
		// TODO Auto-generated method stub
		return importRepo.findByCompanyIdAndBranchIdAndImporterIdAndSirDateAndStatusNot(companyId, branchId, partyId, startDate, "D");
	}
	
	
	@Override
	public Import getByMAWBANdHAWB222(String CompId, String branchId, String MAWB, String HAWB) {
		// TODO Auto-generated method stub
//		 importRepo.findByCompanyIdAndBranchIdAndMawbAndHawbAndStatusNot(CompId, branchId, MAWB, HAWB);
		return null;
	}

	
	
	@Override
	public boolean existdetentionNumber(String companyId, String branchId, String detentionNumber) {
		// TODO Auto-generated method stub
		return importRepo.existsByCompanyIdAndBranchIdAndDetentionReceiptNo(companyId, branchId, detentionNumber);
	}

	@Override
	public Import findForPersonalCarriage(String CompId, String branchId, String detentionNumber) {
		// TODO Auto-generated method stub
		return importRepo.findByCompanyIdAndBranchIdAndDetentionReceiptNo(CompId, branchId, detentionNumber);
	}
	
	
	
	@Override
	public boolean getByMAWBAndHawbDuplicate(String CompId, String branchId, String mawbno, String hawbno) {
		// TODO Auto-generated method stub
		return importRepo.existsByCompanyIdAndBranchIdAndMawbAndHawb(CompId, branchId, mawbno, hawbno);
		
	}
	
	//end tuka
	
	
	
	@Override
	public Import findForInvoice(String companyId, String branchId, Date sirDate, String importerId, int ImpoNop,
			String pcStatus, String scStatus, String hpStatus) {
		// TODO Auto-generated method stub
		return importRepo.findByCompanyIdAndBranchIdAndSirDateAndImporterIdAndNopAndPcStatusAndScStatusAndHpStatus(companyId, branchId, sirDate, importerId, ImpoNop, pcStatus, scStatus, hpStatus);
	}
	
	
	@Override
	public Import findForBillNumber(String CompId, String branchId, String mawb, String Hawb, String IgmNo) {
		// TODO Auto-generated method stub
		return importRepo.findByCompanyIdAndBranchIdAndMawbAndHawbAndIgmNo(CompId, branchId, mawb, Hawb, IgmNo);
	}
	


	public List<Import> getAllImports() {
		return importRepo.findAll();
	}

	public List<Object[]> findImportData(String companyId, String branchId, Date startDate, Date endDate, 
			String airlineName) {
		return importRepo.findImportData(companyId, branchId, startDate, endDate, airlineName);
	}

	// Dyanamic
	public List<String> findAirlineName(String companyId, String branchId, Date startDate, Date endDate
			) {
		return importRepo.findAirlineNames(companyId, branchId, startDate, endDate);
	}
	public List<String> findAllAirlineName(String companyId, String branchId, Date startDate, Date endDate
			) {
		return importRepo.findAllAirlineNames(companyId, branchId, startDate, endDate);
	}

	
	

	
	
		
	
}