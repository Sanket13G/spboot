package com.cwms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.Import;
import com.cwms.entities.ImportSub;
import com.cwms.repository.ImportSubRepository;

@Service
public class ImportSubService {

	@Autowired
	public ImportSubRepository importSubRepo;
	
	public List<ImportSub> findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(String sirDate, String companyId, String branchId, String dgdcStatus) {
        return importSubRepo.findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(sirDate, companyId, branchId, dgdcStatus);
    }
}