package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cwms.entities.RepresentParty;

@Repository
public interface RepresentPartyRepository extends JpaRepository<RepresentParty, String> {

	
	@Query(value="select * from represent_party where company_id=:cid and branch_id=:bid and user_id=:uid and status='A' and user_status='A' ",nativeQuery = true)
	public List<RepresentParty> getbyuserId(@Param("cid") String cid,@Param("bid") String bid,@Param("uid") String uid);
	
	@Query(value="select * from represent_party where representative_id=:rid and company_id=:cid and branch_id=:bid",nativeQuery=true)
	public RepresentParty getrepresentbyid(@Param("rid") String rid,@Param("cid") String cid,@Param("bid") String bid);
	
	@Query(value="select * from represent_party where company_id=:cid and branch_id=:bid and representative_id=:rid and mobile=:mid ",nativeQuery=true)
	public RepresentParty checkOTP(@Param("cid") String cid,@Param("bid") String bid,@Param("rid") String rid,@Param("mid") String mid);
	
	@Query(value="select * from represent_party where company_id=:companyId and branch_id=:branchId  and representative_id=:representativeId  ", nativeQuery=true)
	public RepresentParty findByFNameAndLName(
	    @Param("companyId") String companyId,
	    @Param("branchId") String branchId,
	    @Param("representativeId") String representativeId
	);
	
	List<RepresentParty> findByCompanyIdAndBranchIdAndUserTypeAndUserStatusAndStatusNot(String companyId,String branchId,String userType,String userStatus,String Status);
	RepresentParty findByCompanyIdAndBranchIdAndAndUserIdAndRepresentativeId(String companyId,String branchId,String userid,String representativeId);
	List<RepresentParty> findByCompanyIdAndBranchIdAndUserIdAndUserStatusAndStatusNot(String companyId,String branchId,String userId,String userStatus,String Status);
	
	
	public RepresentParty getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(String compid, String branchId,
			String pid, String rpid);

	public RepresentParty getByRepresentativeId(String rpid);

	@Query(value="select * from represent_party where company_id=:cid and branch_id=:bid and user_id=:pid and status='A' and user_status='A' ",nativeQuery = true)
	public List<RepresentParty> getAllRepresent1(@Param("cid") String cid,@Param("bid") String bid,@Param("pid") String pid);


	public List<RepresentParty> findByCompanyIdAndBranchIdAndUserIdAndStatusNot(String cid, String bid, String rid,
			String string);
	
	@Query(value="select * from represent_party where company_id=:cid and branch_id=:bid",nativeQuery=true)
	public List<RepresentParty> getAllrepresent(@Param("cid") String cid,@Param("bid") String bid);
	
}
