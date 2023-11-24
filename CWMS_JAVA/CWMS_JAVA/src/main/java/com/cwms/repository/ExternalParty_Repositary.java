package com.cwms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.ExternalParty;

public interface ExternalParty_Repositary extends JpaRepository<ExternalParty, String>
{

	ExternalParty findByCompanyIdAndBranchIdAndExternaluserIdAndStatusNot(String CompanyId,String BranchId,String UserId,String Status);
	List<ExternalParty> findByCompanyIdAndBranchIdAndUserTypeAndStatusNot(String CompanyId,String BranchId,String usertype,String status);
	
	
	
	
	List<ExternalParty> findByCompanyIdAndBranchIdAndStatusNotAndUserTypeNot(String CompanyId,String BranchId,String Status,String UserType);
	boolean existsByCompanyIdAndBranchIdAndMobile(String companyId, String branchId, String mobile);
	boolean existsByCompanyIdAndBranchIdAndEmail(String companyId, String branchId,String Email);
	boolean existsByCompanyIdAndBranchIdAndLoginUserName(String companyId, String branchId,String loginUserName);
	
	
	 // Check if a mobile number exists for other records excluding the given externalUserId
	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM ExternalParty e WHERE e.mobile = :mobile AND e.companyId = :companyId AND e.branchId = :branchId AND e.externaluserId <> :externalUserId")
	boolean existsMobileForOtherRecords(@Param("mobile") String mobile, @Param("companyId") String companyId, @Param("branchId") String branchId, @Param("externalUserId") String externalUserId);
    // Check if an email exists for other records excluding the given externalUserId
	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM ExternalParty e WHERE e.email = :email AND e.companyId = :companyId AND e.branchId = :branchId AND e.externaluserId <> :externalUserId")
	boolean existsEmailForOtherRecords(@Param("email") String email, @Param("companyId") String companyId, @Param("branchId") String branchId, @Param("externalUserId") String externalUserId);


    // Check if a login username exists for other records excluding the given externalUserId
	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM ExternalParty e WHERE e.loginUserName = :loginUsername AND e.companyId = :companyId AND e.branchId = :branchId AND e.externaluserId <> :externalUserId")
	boolean existsLoginUsernameForOtherRecords(@Param("loginUsername") String loginUsername, @Param("companyId") String companyId, @Param("branchId") String branchId, @Param("externalUserId") String externalUserId);

	
	List<ExternalParty> findByCompanyIdAndBranchIdAndUserTypeAndStatusNotAndUserstatus(String CompanyId,String BranchId,String usertype,String Status,String UserStatus);
	
	@Query("SELECT p.userName FROM ExternalParty p WHERE p.companyId = :companyId AND p.branchId = :branchId AND p.externaluserId = :externalId")
    String findUsernameByExternaluserIdAndCompanyIdAndBranchId(
        @Param("companyId") String companyId,
        @Param("branchId") String branchId,
        @Param("externalId") String externalId
    );
	ExternalParty findByExternaluserIdAndStatusNot(String userId, String string);
}
