package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cwms.entities.ImportHeavyPackage;

public interface ImportHeavyPackageRepo extends JpaRepository<ImportHeavyPackage, String>
{
	
	public List<ImportHeavyPackage> findByCompanyIdAndBranchIdAndImpTransIdAndMawbAndHawbAndSirNo(
			String compId,String branchId,String TransId,String mawb,String hawb,String Sir);
	
	public ImportHeavyPackage findByCompanyIdAndBranchIdAndImpTransIdAndMawbAndHawbAndSirNoAndHppackageno(
			String compId,String branchId,String TransId,String mawb,String hawb,String Sir,String packageNo);
	
	
	
	
	
	
	
	
}