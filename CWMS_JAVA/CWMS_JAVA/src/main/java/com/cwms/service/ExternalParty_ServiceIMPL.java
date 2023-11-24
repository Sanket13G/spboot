package com.cwms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cwms.repository.*;
import com.cwms.entities.ExternalParty;
@Service
public class ExternalParty_ServiceIMPL implements ExternalParty_Service
{
	@Autowired
	private ExternalParty_Repositary ExternalParty_Repositary;
	
	@Override
	public ExternalParty addExternalParty(ExternalParty ExternalParty) {
		// TODO Auto-generated method stub
		return ExternalParty_Repositary.save(ExternalParty);
	}

	@Override
	public ExternalParty getSingleRecord(String compaId, String BranchId, String UserId) {
		// TODO Auto-generated method stub
		return ExternalParty_Repositary.findByCompanyIdAndBranchIdAndExternaluserIdAndStatusNot(compaId, BranchId, UserId,"D");
	}

	@Override
	public List<ExternalParty> getAllRecords(String compaId, String BranchId) {
		// TODO Auto-generated method stub
		return ExternalParty_Repositary.findByCompanyIdAndBranchIdAndStatusNotAndUserTypeNot(compaId, BranchId,"D","Party");
	}
	@Override
	 public boolean checkMobileExist(String compaId, String BranchId,String mobile) {
	        // Implement the logic to check if the mobile number exists in your database.
	        // You can use externalPartyRepository or any other method to check.

	        boolean mobileExists = ExternalParty_Repositary.existsByCompanyIdAndBranchIdAndMobile(compaId,BranchId,mobile);
	        return mobileExists;
	    }

	@Override
	public boolean checkEmailExist(String compaId, String BranchId,String email) {
		// TODO Auto-generated method stub
		 boolean emailExists = ExternalParty_Repositary.existsByCompanyIdAndBranchIdAndEmail(compaId, BranchId, email);
	        return emailExists;
	}

	@Override
	public boolean checkLoginusenameExist(String compaId, String BranchId,String loginusername) {
		// TODO Auto-generated method stub
		boolean loginusernameExists = ExternalParty_Repositary.existsByCompanyIdAndBranchIdAndLoginUserName(compaId, BranchId, loginusername);
		return loginusernameExists;
	}

	@Override
	public boolean existsMobileForOtherRecords(String mobile,String companyId, String branchId, String userid) {
		// TODO Auto-generated method stub
		return ExternalParty_Repositary.existsMobileForOtherRecords(mobile, companyId, branchId, userid);
	}

	@Override
	public boolean existsEmailForOtherRecords(String email,String companyId, String branchId, String userid) {
		// TODO Auto-generated method stub
		return ExternalParty_Repositary.existsEmailForOtherRecords(email, companyId, branchId, userid);
	}

	@Override
	public boolean existsUsernameForOtherRecords(String username,String companyId, String branchId, String userid) {
		// TODO Auto-generated method stub
		return ExternalParty_Repositary.existsLoginUsernameForOtherRecords(username, companyId, branchId, userid);
	}

	@Override
	public List<ExternalParty> findByCompanyIdAndBranchIdAndUserType(String CompanyId, String BranchId,
			String usertype) {
		// TODO Auto-generated method stub
		return ExternalParty_Repositary.findByCompanyIdAndBranchIdAndUserTypeAndStatusNot(CompanyId, BranchId, usertype,"D");
	}

	@Override
	public List<ExternalParty> findByUserType(String CompanyId, String BranchId, String usertype) {
		// TODO Auto-generated method stub
		return ExternalParty_Repositary.findByCompanyIdAndBranchIdAndUserTypeAndStatusNotAndUserstatus(CompanyId, BranchId, usertype, "D", "A");
	}
	
	@Override
	public String findUsernameByExternalUserId(String CompanyId, String BranchId, String externaluserId) {
		// TODO Auto-generated method stub
		return ExternalParty_Repositary.findUsernameByExternaluserIdAndCompanyIdAndBranchId(CompanyId, BranchId,externaluserId);
	}
	
	@Override
	public ExternalParty getSingleRecord1(String UserId) {
		// TODO Auto-generated method stub
		return ExternalParty_Repositary.findByExternaluserIdAndStatusNot( UserId,"D");
	}

}
