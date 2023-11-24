package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.DefaultPartyDetails;

@EnableJpaRepositories
public interface DefaultParyDetailsRepository extends JpaRepository<DefaultPartyDetails, String> {

	@Query(value="select * from default_party_dtls where company_id=:cid and branch_id=:bid and user_Id=:uid and status='A'",nativeQuery=true)
	public DefaultPartyDetails getdatabyuser_id(@Param("cid") String cid,@Param("bid") String bid,@Param("uid") String uid);

	 @Query(value="select * from default_party_dtls where company_id=:cid and branch_id=:bid and exp_cha=:expcha and status='A'",nativeQuery=true)
		public List<DefaultPartyDetails> findoutbyexportcha(@Param("cid") String cid,@Param("bid") String bid,@Param("expcha") String expcha);

	    @Query(value="select * from default_party_dtls where company_id=:cid and branch_id=:bid and imp_cha=:expcha and status='A'",nativeQuery=true)
		public List<DefaultPartyDetails> findoutbyimportcha(@Param("cid") String cid,@Param("bid") String bid,@Param("expcha") String expcha);

	    @Query("SELECT DISTINCT dpd.useId FROM DefaultPartyDetails dpd " +
		           "WHERE dpd.companyId = :companyId " +
		           "AND dpd.branchId = :branchId " +
		           "AND dpd.impCHA = :impCHA")
		    List<String> findUseIdsByCompanyIdAndBranchIdAndImpCHA(
		            @Param("companyId") String companyId,
		            @Param("branchId") String branchId,
		            @Param("impCHA") String impCHA
		    );
}
