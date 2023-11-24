package com.cwms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.ExportSub;
import com.cwms.entities.ImportSub;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.ImportSubRepository;
@Service
public class ExportSubService {

	
	@Autowired
	public ExportSubRepository exportsubRepo;
	
	public List<ExportSub> findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(String serDate, String companyId, String branchId, String dgdcStatus) {
        return exportsubRepo.findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(serDate, companyId, branchId, dgdcStatus);
    }
}