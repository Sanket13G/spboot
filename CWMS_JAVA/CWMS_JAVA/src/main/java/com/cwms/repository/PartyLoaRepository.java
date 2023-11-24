package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cwms.entities.PartyLOA;
import com.cwms.entities.PartyLoaId;

@Repository
public interface PartyLoaRepository extends JpaRepository<PartyLOA, PartyLoaId> {

	@Query(value = "SELECT * FROM Party_LOA WHERE Company_Id = :cid AND Branch_Id = :bid AND Party_Id = :partyid ORDER BY loa_ser_id DESC", nativeQuery = true)
	List<PartyLOA> findByCompanyBranchAndPartyId(@Param("cid") String companyId, @Param("bid") String branchId,
			@Param("partyid") String partyId);

	@Query(value = "SELECT * FROM Party_LOA WHERE company_id = :companyId AND branch_id = :branchId AND party_id = :partyId AND loa_ser_id = :loaSerId", nativeQuery = true)
	PartyLOA findPartyLOAByCompositeKey(String companyId, String branchId, String partyId,String loaSerId);

}