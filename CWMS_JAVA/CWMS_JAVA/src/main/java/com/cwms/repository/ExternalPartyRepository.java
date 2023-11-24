package com.cwms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.*;
import com.cwms.entities.ExternalParty;

@EnableJpaRepositories
public interface ExternalPartyRepository extends JpaRepository<ExternalParty, String> {

	@Query(value="select * from external_party where company_id=:cid and branch_id=:bid  and user_status='A' and user_type='CHA'",nativeQuery=true)
	public List<ExternalParty> getalldata(@Param("cid") String cid,@Param("bid") String bid);
	
	@Query(value="select * from external_party where company_id=:cid and branch_id=:bid  and user_status='A' and user_type='Console'",nativeQuery=true)
	public List<ExternalParty> getallConsoledata(@Param("cid") String cid,@Param("bid") String bid);
	
	@Query(value="select * from external_party where company_id=:cid and branch_id=:bid and external_user_id=:eid and user_status='A'",nativeQuery=true)
	public ExternalParty getalldatabyid(@Param("cid") String cid,@Param("bid") String bid,@Param("eid") String eid);
	
	
	@Query(value = "SELECT * from external_party WHERE company_id =:companyId AND branch_id =:branchId AND external_user_id =:externa", nativeQuery = true)
    ExternalParty findBycompbranchexternal(@Param("companyId") String companyId,@Param("branchId") String branchId,@Param("externa") String externalUserId);

	@Query(value="select * from external_party where company_id=:cid and branch_id=:bid  and user_status='A' and user_type='Carting Agent'",nativeQuery=true)
	public List<ExternalParty> getallCartingdata(@Param("cid") String cid,@Param("bid") String bid);

	@Query(value = "select * from external_party where company_id=:cid and branch_id=:bid", nativeQuery = true)
	public List<ExternalParty> getalldataExternalParties(@Param("cid") String cid, @Param("bid") String bid);
	
	@Query(value = "SELECT * FROM external_party WHERE company_id = :cid AND branch_id = :bid AND user_type = 'console' AND user_status = 'A'", nativeQuery = true)
	public List<ExternalParty> getAllExternalParties(@Param("cid") String cid, @Param("bid") String bid);

	
	
	@Query(value ="SELECT p.user_name FROM external_party p WHERE p.company_Id = :companyId AND p.branch_Id = :branchId AND external_user_id = :externaluserId", nativeQuery = true)
    String findUserNameByKeys(
        @Param("companyId") String companyId,
        @Param("branchId") String branchId,
        @Param("externaluserId") String externaluserId
 
    		);
}
