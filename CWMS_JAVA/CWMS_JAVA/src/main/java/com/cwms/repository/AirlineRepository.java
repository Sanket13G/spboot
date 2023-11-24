package com.cwms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cwms.entities.Airline;
import com.cwms.entities.AirlineId;


@Repository
public interface AirlineRepository extends JpaRepository<Airline, AirlineId> {
	
	
	
	Airline findByCompanyIdAndBranchIdAndAirlineShortNameAndStatusNot(String companyId,String branchId,String shortName,String status);

	@Query(value = "SELECT * FROM airline WHERE branch_id = :branchId and company_id = :companyId AND status != 'D'", nativeQuery = true)
	List<Airline> findByBranchIdOrCompanyId(@Param("branchId") String branchId, @Param("companyId") String companyId);

	Airline findByFlightNo(String id);

	@Query(value = "SELECT * FROM airline "
			+ "WHERE company_id = :companyId and branch_id = :branchId and flight_id = :flightId LIMIT 1", nativeQuery = true)
	Optional<Airline> findByCompanyIdOrBranchIdOrFlightId(@Param("companyId") String companyId,
			@Param("branchId") String branchId, @Param("flightId") String flightId);
	
	@Query(value = "SELECT * FROM airline WHERE company_id = :companyId and branch_id = :branchId and airline_code = :flightId", nativeQuery = true)
	Airline findbyAirlineID(@Param("companyId") String companyId,@Param("branchId") String branchId, @Param("flightId") String flightId);
	
	@Query(value = "SELECT * FROM airline WHERE company_id = :companyId and branch_id = :branchId and flight_no = :flightId", nativeQuery = true)
	Airline findbyID(@Param("companyId") String companyId,@Param("branchId") String branchId, @Param("flightId") String flightId);
	
	@Query(value="SELECT * FROM airline WHERE company_id = :companyId and branch_id = :branchId and airline_name = :flightId",nativeQuery=true)
	Airline findByAirlineName(@Param("companyId") String companyId,@Param("branchId") String branchId, @Param("flightId") String flightId);
	
	@Query(value="SELECT * FROM airline WHERE company_id = :companyId and branch_id = :branchId and airline_code = :flightId",nativeQuery=true)
	Airline findByAirlineCode(@Param("companyId") String companyId,@Param("branchId") String branchId, @Param("flightId") String flightId);
	
	@Query(value="SELECT Airline_Name FROM airline WHERE company_id = :companyId and branch_id = :branchId and airline_code = :flightId",nativeQuery=true)
	String findByNameAirlineCode(@Param("companyId") String companyId,@Param("branchId") String branchId, @Param("flightId") String flightId);
}