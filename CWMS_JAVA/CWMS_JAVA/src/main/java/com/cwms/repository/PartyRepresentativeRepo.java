package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cwms.entities.PartyRepresentative;
import com.cwms.entities.PartyRepresentativeId;





@Repository
@EnableJpaRepositories
public interface PartyRepresentativeRepo extends JpaRepository<PartyRepresentative, PartyRepresentativeId> {

	PartyRepresentative findRepresentivBypartyRepresentativeId(String partyRepresentativeId);

    @Modifying
    @Query("DELETE FROM PartyRepresentative pr WHERE pr.partyRepresentativeId = :partyRepresentativeId")
    void deleteRepresentiveById(@Param("partyRepresentativeId") String partyRepresentativeId);
    
    

	List<PartyRepresentative> findByCompanyIdAndBranchIdAndCartingAgent(String companyId, String branchId,
			String cartingAgent);
	
	List<PartyRepresentative> findByCompanyIdAndBranchId(String companyId, Object branchId);
	
	PartyRepresentative findByCompanyIdAndBranchIdAndCartingAgentAndPartyRepresentativeId(String companyId, String branchId,String CartingAgent, String ReprentativeId);

//	List<PartyRepresentative> findByCompanyIdAndBranchId(String companyId, String branchId);

	 @Query("SELECT DISTINCT pr.cartingAgent FROM PartyRepresentative pr " +
	           "WHERE pr.companyId = :companyId AND pr.branchId = :branchId")
	    List<String> findUniqueCartingAgentByCompanyIdAndBranchId(String companyId, String branchId);

}
