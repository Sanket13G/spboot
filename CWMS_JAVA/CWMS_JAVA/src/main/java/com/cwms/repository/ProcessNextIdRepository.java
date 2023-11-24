package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cwms.entities.ProcessNextId;
import com.cwms.entities.ProcessNextIdPK;

import jakarta.transaction.Transactional;

@Repository
public interface ProcessNextIdRepository extends JpaRepository<ProcessNextId, ProcessNextIdPK> {

	List<ProcessNextId> findByProcessId(String processId);

	@Query(value = "Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00037' AND  Fin_Year = '2006' AND  Process_Id = 'P00011'", nativeQuery = true)
	String findNextId();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00001' AND Branch_Id = 'B00037' AND Process_Id = 'P00011' AND fin_year='2006'", nativeQuery = true)
	void updateNextId(String nextId);

	@Query(value = "Select Next_Id from  processnextid  where Company_Id ='C00011' AND Branch_Id = 'B00038' AND  Fin_Year = '2006' AND  Process_Id = 'P00012'", nativeQuery = true)
	String findNextIdforHoliday();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00011' AND Branch_Id = 'B00038' AND Process_Id = 'P00012' AND fin_year='2006'", nativeQuery = true)
	void updateNextIdforHoliday(String nextholi);

	@Query(value = "Select Next_Id from  processnextid  where Company_Id ='C00090' AND Branch_Id = 'B00090' AND  Fin_Year = '2007' AND  Process_Id = 'P00009'", nativeQuery = true)
	String findNextMailId();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00090' AND Branch_Id = 'B00090' AND Process_Id = 'P00009' AND fin_year='2007'", nativeQuery = true)
	void updateNextMailId(String mailId);
	
	
	
	@Query(value = "Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2006' AND  Process_Id = 'P00001'", nativeQuery = true)
	String findNextJarDId();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND Process_Id = 'P00001' AND fin_year='2006'", nativeQuery = true)
	void updateNextJarDId(String nextJarDId);

	@Query(value = "Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2006' AND  Process_Id = 'P00001'", nativeQuery = true)
	String findNextJarId();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND Process_Id = 'P00001' AND fin_year='2006'", nativeQuery = true)
	void updateNextJarId(String nextJarId);
	
	@Query(value = 
			"Select Next_Id from  processnextid  where Company_Id ='C00099' AND Branch_Id = 'B00099' AND  Fin_Year = '2009' AND  Process_Id = 'P00099'", nativeQuery = true)
	String findNextReId();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00099' AND Branch_Id = 'B00099' AND Process_Id = 'P00099' AND fin_year='2009'", nativeQuery = true)
	void updateNextReId(String mailId);
	
	
	@Query(value = 
			"Select Next_Id from  processnextid  where Company_Id ='C00012' AND Branch_Id = 'B00039' AND  Fin_Year = '2007' AND  Process_Id = 'P00013'", nativeQuery = true)
	String findNextServiceId();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00012' AND Branch_Id = 'B00039' AND Process_Id = 'P00013' AND fin_year='2007'", nativeQuery = true)
	void updateNextServiceId(String nextServiceId);
	
	@Query(value = 
			"Select Next_Id from  processnextid  where Company_Id ='C00013' AND Branch_Id = 'B00040' AND  Fin_Year = '2007' AND  Process_Id = 'P00014'", nativeQuery = true)
	String findNextCFSTarrifNo();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00013' AND Branch_Id = 'B00040' AND Process_Id = 'P00014' AND fin_year='2007'", nativeQuery = true)
	void updateNextCFSTarrifNo(String nextCFSTarrifNo);
	
	@Query(value = 
			"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2007' AND  Process_Id = 'P00015'", nativeQuery = true)
	String findNextSIRNo();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND Process_Id = 'P00015' AND fin_year='2007'", nativeQuery = true)
	void updateNextSIRNo(String nextSIRNo);
	
	@Query(value = 
			"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2007' AND  Process_Id = 'P00016'", nativeQuery = true)
	String findNextimpTransId();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND Process_Id = 'P00016' AND fin_year='2007'", nativeQuery = true)
	void updateNextimpTransId(String nextimpTransId);
	
	@Query(value = 
			"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2111' AND Process_Id = 'P00100' ", nativeQuery = true)
	String findNextsubimpid();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET  Next_Id = :nextimpTransId WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND fin_year='2111' and Process_Id = 'P00100'", nativeQuery = true)
	void updateNexsubimpid(@Param("nextimpTransId")String nextimpTransId);
	
	
	@Query(value = 
			"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2123' AND  Process_Id = 'P1320'", nativeQuery = true)
	String findNextSIRExportNo();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND Process_Id = 'P1320' AND fin_year='2123'", nativeQuery = true)
	void updateNextSIRExportNo(String nextSIRNo);
	
	
	@Query(value = 
			"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2222' AND Process_Id = 'P00200' ", nativeQuery = true)
	String findNextsubimptransid();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET  Next_Id = :nextimpTransId WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND fin_year='2222' and Process_Id = 'P00200'", nativeQuery = true)
	void updateNexsubimptransid(@Param("nextimpTransId")String nextimpTransId);
	
	@Query(value = 
			"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2224' AND Process_Id = 'P00208' ", nativeQuery = true)
	String findNextsubexpid();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET  Next_Id = :nextimpTransId WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND fin_year='2224' and Process_Id = 'P00208'", nativeQuery = true)
	void updateNexsubexpid(@Param("nextimpTransId")String nextimpTransId);
	
	@Query(value = 
			"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2225' AND Process_Id = 'P00209' ", nativeQuery = true)
	String findNextsubexptransid();

	@Modifying
	@Transactional
	@Query(value = "UPDATE processnextid SET  Next_Id = :nextimpTransId WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND fin_year='2225' and Process_Id = 'P00209'", nativeQuery = true)
	void updateNexsubexptransid(@Param("nextimpTransId")String nextimpTransId);
	
	  @Query(value = 
				"Select Next_Id from  processnextid  where Company_Id ='C00018' AND Branch_Id = 'B00018' AND  Fin_Year = '2018' AND  Process_Id = 'P00018'", nativeQuery = true)
		String findNextPctmNo();

		@Modifying
		@Transactional
		@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00018' AND Branch_Id = 'B00018' AND Process_Id = 'P00018' AND fin_year='2018'", nativeQuery = true)
		void updateNextPctmNo(String PctmNo);
		

		@Query(value = 
				"Select Next_Id from  processnextid  where Company_Id ='C00019' AND Branch_Id = 'B00019' AND  Fin_Year = '2019' AND  Process_Id = 'P00019'", nativeQuery = true)
		String findNexttpNo();

		@Modifying
		@Transactional
		@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00019' AND Branch_Id = 'B00019' AND Process_Id = 'P00019' AND fin_year='2019'", nativeQuery = true)
		void updateNexttpNo(String tpNo);
		
		
		@Query(value = 
				"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2023' AND Process_Id = 'P01400' ", nativeQuery = true)
		String findNextexternalUserId();

		@Modifying
		@Transactional
		@Query(value = "UPDATE processnextid SET  Next_Id = :externalUserId WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND fin_year='2023' and Process_Id = 'P01400'", nativeQuery = true)
		void updateNextexternalUserId(@Param("externalUserId")String externalUserId);
		
		@Query(value = 
				"Select Next_Id from  processnextid  where Company_Id ='C00099' AND Branch_Id = 'B00099' AND  Fin_Year = '2090' AND  Process_Id = 'P00099'", nativeQuery = true)
		String findDetentionId();

		@Modifying
		@Transactional
		@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00099' AND Branch_Id = 'B00099' AND Process_Id = 'P00099' AND fin_year='2090'", nativeQuery = true)
		void updateNextDetentionId(String detentionId);
		
		
		@Query(value = "Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2023' AND  Process_Id = 'P00300'", nativeQuery = true)
		String findDoNumber();

		@Modifying
		@Transactional
		@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND Process_Id = 'P00300' AND fin_year='2023'", nativeQuery = true)
		void updateNextDoNumber(String detentionId);
		
		  @Query(value = 
					"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2023' AND  Process_Id = 'P01313'", nativeQuery = true)
			String findNextEXPPctmNo();

			@Modifying
			@Transactional
			@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND Process_Id = 'P01313' AND fin_year='2023'", nativeQuery = true)
			void updateNextEXPPctmNo(String PctmNo);
			

			@Query(value = 
					"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2023' AND  Process_Id = 'P01414'", nativeQuery = true)
			String findNextEXPtpNo();

			@Modifying
			@Transactional
			@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND Process_Id = 'P01414' AND fin_year='2023'", nativeQuery = true)
			void updateNextEXPtpNo(String tpNo);
			
			@Query(value = 
					"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2111' AND  Process_Id = 'P01099'", nativeQuery = true)
			String findGateInId();

			@Modifying
			@Transactional
			@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND Process_Id = 'P01099' AND fin_year='2111'", nativeQuery = true)
			void updateGateInId(String gateInId);
			
			
			@Query(value = 
					"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2024' AND Process_Id = 'P03030' ", nativeQuery = true)
			String findNextInvoiceNumber();

			@Modifying
			@Transactional
			@Query(value = "UPDATE processnextid SET  Next_Id = :InvoiceNumber WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND fin_year='2024' and Process_Id = 'P03030'", nativeQuery = true)
			void updateNextextInvoiceNumber(@Param("InvoiceNumber")String InvoiceNumber);
			
			
//			Bill Number
			@Query(value = 
					"Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2024' AND Process_Id = 'P04040' ", nativeQuery = true)
			String findNextBillNumber();

			@Modifying
			@Transactional
			@Query(value = "UPDATE processnextid SET  Next_Id = :InvoiceNumber WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND fin_year='2024' and Process_Id = 'P04040'", nativeQuery = true)
			void updateNextextBillNumber(@Param("InvoiceNumber")String InvoiceNumber);
			
			@Query(value = "Select Next_Id from  processnextid  where Company_Id ='C00001' AND Branch_Id = 'B00001' AND  Fin_Year = '2226' AND  Process_Id = 'P00210'", nativeQuery = true)
			String findNextRepresentativePartyId();

			@Modifying
			@Transactional
			@Query(value = "UPDATE processnextid SET Next_Id = ?1 WHERE Company_Id = 'C00001' AND Branch_Id = 'B00001' AND Process_Id = 'P00210' AND fin_year='2226'", nativeQuery = true)
			void updateNextRepresentativePartyId(String nextId);
}
