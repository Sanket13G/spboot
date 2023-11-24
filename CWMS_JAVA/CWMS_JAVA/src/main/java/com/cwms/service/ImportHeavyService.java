package com.cwms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.ImportHeavyPackage;
import com.cwms.repository.ImportHeavyPackageRepo;
@Service
public class ImportHeavyService 
{
	@Autowired
	public ImportHeavyPackageRepo ImportHeavyPackageRepo;
	
	public ImportHeavyPackage getByPackageNo(String compId,String branchId,String TransId,String mawb,String hawb,String Sir,String packageNo)
	{
		return ImportHeavyPackageRepo.findByCompanyIdAndBranchIdAndImpTransIdAndMawbAndHawbAndSirNoAndHppackageno(compId, branchId, TransId, mawb, hawb, Sir, packageNo);
	}
	
	public List<ImportHeavyPackage> getByMAWB(String compId,String branchId,String TransId,String mawb,String hawb,String Sir)
	{
		return ImportHeavyPackageRepo.findByCompanyIdAndBranchIdAndImpTransIdAndMawbAndHawbAndSirNo(compId, branchId, TransId, mawb, hawb, Sir);
	}
	
	public ImportHeavyPackage addImportHeavy(ImportHeavyPackage  ImportHeavyPackage)
	{
		return ImportHeavyPackageRepo.save(ImportHeavyPackage);
	}
	
	public ImportHeavyPackage deleteImportHeavyPackage(ImportHeavyPackage ImportHeavyPackage)
	{
		
		 ImportHeavyPackageRepo.delete(ImportHeavyPackage);
		 return ImportHeavyPackage;
	}
	
}