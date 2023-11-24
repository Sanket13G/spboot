package com.cwms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.Import;
import com.cwms.repository.ImportRepo;

import jakarta.transaction.Transactional;

@Service
public class Importserviceforpctm {
  
	@Autowired
	private ImportRepo importRepo;
	
	
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
	

	 @Transactional
	    public String updatePCTMAndTPNo(
	            String newPCTMNo,
	            String newTPNo,
	            String companyId,
	            String branchId,
	            String sirNo,
	            String mawb
	    ) {
	        // Call the repository method to update both PCTM and TP numbers
	        return importRepo.updatePCTMAndTPNo(newPCTMNo, newTPNo, companyId, branchId, sirNo, mawb);
	    }
	 
	 public List<Import> findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(String sirDate, String companyId, String branchId, String dgdcStatus) {
	        return importRepo.findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(sirDate, companyId, branchId, dgdcStatus);
	    }
}
