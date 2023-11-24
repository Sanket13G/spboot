package com.cwms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cwms.entities.ImportPC;
import com.cwms.repository.ImportPCRepositary;
@Service
public class ImportPCServiceIMPL implements ImportPCService
{
	@Autowired
	public ImportPCRepositary importPCRepositary;
	@Override
	public ImportPC getByIDS(String companyId, String branchId, String MAWb, String HAWB, String sirNo) {
		// TODO Auto-generated method stub
		return importPCRepositary.findByCompanyIdAndBranchIdAndMawbAndHawbAndSirNo(companyId, branchId, MAWb, HAWB, sirNo);
	}

	@Override
	public ImportPC AddImportPC(ImportPC AddImportPC) {
		// TODO Auto-generated method stub
		return importPCRepositary.save(AddImportPC);
	}

	@Override
	public void deleteImportPc(String companyId, String branchId, String MAWb, String HAWB, String sirNo) {
		ImportPC ImportPcDetail = importPCRepositary.findByCompanyIdAndBranchIdAndMawbAndHawbAndSirNo(companyId, branchId, MAWb, HAWB, sirNo);
		importPCRepositary.delete(ImportPcDetail);
	}

}