package com.cwms.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cwms.entities.ExternalParty;
@Service
public interface ExternalParty_Service 
{
	ExternalParty addExternalParty(ExternalParty ExternalParty);
	ExternalParty getSingleRecord(String compaId,String BranchId,String UserId);
	List<ExternalParty> getAllRecords(String compaId,String BranchId);
	List<ExternalParty> findByCompanyIdAndBranchIdAndUserType(String CompanyId,String BranchId,String usertype);
	public boolean checkMobileExist(String companyId, String branchId,String mobile);
	public boolean checkEmailExist(String companyId, String branchId,String email);
	public boolean checkLoginusenameExist(String companyId, String branchId,String loginusername);
	public boolean existsMobileForOtherRecords(String mobile,String companyId, String branchId,String userid);
	public boolean existsEmailForOtherRecords(String email,String companyId, String branchId,String userid);
	public boolean existsUsernameForOtherRecords(String username,String companyId, String branchId,String userid);
	public String findUsernameByExternalUserId(String CompanyId,String BranchId,String usertype);
	public List<ExternalParty> findByUserType(String CompanyId,String BranchId,String usertype);
	ExternalParty getSingleRecord1(String UserId);
}
