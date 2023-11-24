package com.cwms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwms.entities.HolidayMaster;

import jakarta.transaction.Transactional;

public interface HolidayRepository extends JpaRepository<HolidayMaster, String>{

	HolidayMaster getById(String holidayId);

//	 HolidayMaster findHolidayById(String holidayId);
	 void deleteByHolidayId(String holidayId);
	 
	 @Query(value="select * from holiday_master where company_id=:cid and branch_id=:bid and status <> 'D'",nativeQuery=true)
	public List<HolidayMaster> getalldata(@Param("cid") String cid,@Param("bid") String bid);
	 
	 @Transactional
		@Modifying
		@Query(value="update holiday_master set status='D' where company_id=:cid and branch_id=:bid and holiday_id=:hid",nativeQuery=true)
	 void deleteByCompanyholidayid(@Param("cid") String cid,@Param("bid") String bid,@Param("hid") String hid);
	 
	 public HolidayMaster findByHolidayId(String holidayId);
	 @Query("SELECT COUNT(h) > 0 FROM HolidayMaster h WHERE h.companyId = :companyId AND h.branchId = :branchId AND DATE(h.holidayDate) = DATE(:holidayDate) AND h.status <> 'D'")
	 public boolean existsByCompanyBranchAndDateAndNotDeleted(
	      @Param("companyId") String companyId,
	      @Param("branchId") String branchId,
	      @Param("holidayDate") Date holidayDate
	 );
}
