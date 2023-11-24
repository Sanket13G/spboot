package com.cwms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cwms.entities.Party;
import com.cwms.entities.PartyId;

@Repository
public interface PartyRepository extends JpaRepository<Party, PartyId> {

//	Party deletePartyByID(String partyID);
//
//	Optional<Party> findById(String partyId);
//	
//	@Query("SELECT  MAX(partyId) FROM Party")
//	String maxPartyId();

//	Optional<Party> findAll(String partyId);

	Party findByPartyId(String partyId);
	
	
//	List<Party> findByCompanyIdAndBranchIdAndInvoiceTypeAndStatusNot(String companyId,String branchId,String inviceType, String Status);

	// void deleteById( String partyId);

	void deleteByPartyId(String partyId);
	
	@Query(value="select * from party where company_id=:cid and branch_id=:bid and entity_id=:entity",nativeQuery=true)
	Party findbyentityid(@Param("cid") String cid,@Param("bid") String bid,@Param("entity") String entity);
	
	
	 List<Party> findByCompanyIdAndBranchIdAndPartyIdNotIn(String companyId, String branchId, List<String> excludedPartyIds);
	
	 @Query(value = "SELECT * FROM party WHERE company_id = :cid AND branch_id = :bid AND status <> 'D' ORDER BY party_Name ASC", nativeQuery = true)
	 public List<Party> getalldata(@Param("cid") String cid, @Param("bid") String bid);
	 
	 @Query(value = "SELECT * FROM party WHERE company_id = :cid AND branch_id = :bid AND status != 'D' and party_status != 'I' ORDER BY party_Name ASC", nativeQuery = true)
	 public List<Party> getalldata3(@Param("cid") String cid, @Param("bid") String bid);
	 
	 
	 
		
		@Query(value="select * from party where company_id=:cid and branch_id=:bid and party_name=:pname and status != 'D'",nativeQuery=true)
		public Party getdatabypartyname(@Param("cid") String cid,@Param("bid") String bid,@Param("pname") String pname );
		
		@Query(value="select * from party where company_id=:cid and branch_id=:bid and party_id=:pid and status != 'D'",nativeQuery=true)
		public Party getdatabyid(@Param("cid") String cid,@Param("bid") String bid,@Param("pid") String pid);
	
	
	Party findByCompanyIdAndBranchIdAndPartyName(String companyId, String branchId,String partyName);
	
	@Query("SELECT p.partyName FROM Party p WHERE p.companyId = :companyId AND p.branchId = :branchId AND p.partyId = :partyId")
    String findPartyNameByKeys(
        @Param("companyId") String companyId,
        @Param("branchId") String branchId,
        @Param("partyId") String partyId
    );
 
	Party findByCompanyIdAndBranchIdAndPartyId(String companyId, String branchId,String partyId);

 @Query("SELECT p.lastInVoiceDate FROM Party p WHERE p.companyId = :companyId AND p.branchId = :branchId AND p.partyId = :partyId")
    Date findInvoiceDateByKeys(
        @Param("companyId") String companyId,
        @Param("branchId") String branchId,
        @Param("partyId") String partyId
    );
 
 @Query("SELECT p.partyId FROM Party p WHERE p.companyId = :companyId AND p.branchId = :branchId AND p.entityId = :EntityId")
 String findPartyNameEntityId(
     @Param("companyId") String companyId,
     @Param("branchId") String branchId,
     @Param("EntityId") String EntityId
 );
 
 
 @Query(value="select * from party where company_id=:cid and branch_id=:bid and status <> 'D' order by party_id desc",nativeQuery=true)
	public List<Party> getalldata1(@Param("cid") String cid,@Param("bid") String bid );
 List<Party> findByCompanyIdAndBranchIdAndInvoiceTypeAndStatusNot(String companyId,String branchId,String inviceType, String Status);
 
 
}
