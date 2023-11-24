package com.cwms.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.Party;
import com.cwms.entities.Services;


public interface SerViceRepositary extends JpaRepository<Services, String>
{	 
	 
	@Query(value = "SELECT * FROM Services AS s WHERE s.Company_Id = :companyId AND s.Branch_Id = :branchId AND s.Service_Id = :serviceId  AND s.status <> 'D'", nativeQuery = true)
	public Services findByService_Id(@Param("companyId") String companyId, @Param("branchId") String branchId, @Param("serviceId") String serviceId);


	 @Query(value = "SELECT * FROM services s WHERE s.Company_Id = ?1 AND s.Branch_Id = ?2 AND s.status <> 'D'", nativeQuery = true)
	    List<Services> getActiveServices(String companyId, String branchId);
	 
	 @Query("SELECT s FROM Services s WHERE s.Service_Id NOT IN :excludedServiceIds AND s.Company_Id = :companyId AND s.Branch_Id = :branchId AND s.status <> 'D'")
	    List<Services> findServicesNotInIdsAndCompanyAndBranch(  @Param("companyId") String companyId, @Param("branchId") String branchId,  @Param("excludedServiceIds") List<String> excludedServiceIds);
	 
	 
//	 List<Services> findByCompanyIdAndBranchIdAndserviceShortDescription(String companyId,String branchId,String shortDesc);
	 
	 @Query("SELECT s.taxPercentage FROM Services s WHERE s.Company_Id = :companyId " +
		       "AND s.Branch_Id = :branchId " +
		       "AND s.Service_Id = :serviceId " +
		       "AND s.status <> 'D'")
		String findTaxPercentage(
		    @Param("companyId") String companyId,
		    @Param("branchId") String branchId,
		    @Param("serviceId") String serviceId
		);
}