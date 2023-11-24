package com.cwms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cwms.entities.EmailScedulerEntity;

public interface EmailScedulerRepositary extends JpaRepository<EmailScedulerEntity, String>
{

	public List<EmailScedulerEntity> findByCompanyIdAndBranchIdAndMailFlagNot(String companyId,String branchId,String mailFlag);
	
}