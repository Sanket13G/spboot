package com.cwms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwms.entities.ImportPC;

public interface ImportPCRepositary extends JpaRepository<ImportPC, String>
{

	ImportPC findByCompanyIdAndBranchIdAndMawbAndHawbAndSirNo(String companyId, String branchId,String MAWb, String HAWB , String sirNo);
}