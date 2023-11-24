package com.cwms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.HolidayMaster;
import com.cwms.repository.HolidayRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HolidayService {

	@Autowired
	private final HolidayRepository holidayRepo;

	@Autowired
	public HolidayService(HolidayRepository holidayRepo) {
		this.holidayRepo = holidayRepo;
	}
	
	
	public List<HolidayMaster> getAllHoliday(String cid,String bid)
	{
		return holidayRepo.getalldata(cid,bid);
	}
	
	public boolean findByDate(String companyId,String branchId,Date holidayDate)
	{
		
		return holidayRepo.existsByCompanyBranchAndDateAndNotDeleted(companyId, branchId, holidayDate);
	}
	
	public HolidayMaster createHoliday(HolidayMaster holiday)
	{
		return holidayRepo.save(holiday);
	}
	
	public HolidayMaster getHoliday(String Id)
	{
		return holidayRepo.getById(Id);
		
	}
	
//	public HolidayMaster getHolidayByHolidayId(HolidayId Id) {
//		return holidayRepo.findById(Id).orElse(null);
//	}
	
//	@Transactional
//	public HolidayMaster updateHoliday(String holidayId,HolidayMaster holiday)
//	{
//		HolidayMaster existingHoliday=holidayRepo.getById(holidayId);
//		if(existingHoliday!=null)
//		{
//			
//			existingHoliday.setHolidayName(holiday.getHolidayName());
//            existingHoliday.setHolidayDate(holiday.getHolidayDate());
//            existingHoliday.setHolidayDay(holiday.getHolidayDay());
//            existingHoliday.setEditedBy(holiday.getEditedBy());
//            existingHoliday.setEditedDate(holiday.getEditedDate());
//            existingHoliday.setApprovedBy(holiday.getApprovedBy());
//            existingHoliday.setApprovedDate(holiday.getApprovedDate());
//            existingHoliday.setStatus(holiday.getStatus());
//            return holidayRepo.save(existingHoliday);
//		}
//		return null;
//	}
	
    public HolidayMaster updateHoliday(String holidayId, HolidayMaster updatedHoliday) throws Exception {
        HolidayMaster existingHoliday = holidayRepo.findByHolidayId(holidayId);

        if (existingHoliday != null) {
            updateFields(existingHoliday, updatedHoliday);
            return holidayRepo.save(existingHoliday);
        } else {
            throw new Exception("Holiday with ID " + holidayId + " not found");
        }
    }

    private void updateFields(HolidayMaster existingHoliday, HolidayMaster updatedHoliday) {
        existingHoliday.setHolidayName(updatedHoliday.getHolidayName());
        existingHoliday.setHolidayDate(updatedHoliday.getHolidayDate());
        existingHoliday.setHolidayDay(updatedHoliday.getHolidayDay());
        existingHoliday.setEditedBy(updatedHoliday.getEditedBy());
        existingHoliday.setEditedDate(updatedHoliday.getEditedDate());
        existingHoliday.setApprovedBy(updatedHoliday.getApprovedBy());
        existingHoliday.setApprovedDate(updatedHoliday.getApprovedDate());
        existingHoliday.setStatus(updatedHoliday.getStatus());
    }



	
	@Transactional
	public void deleteHolidayById(String holidayId) {
		holidayRepo.deleteByHolidayId(holidayId);
	}

//	public String autoIncrementHolidayId()
//	{
//		String maxHolidayId=holidayRepo.findLastHolidayId();
//		
//		if(maxHolidayId==null)
//		{
//			maxHolidayId="H00000";
//		}
//		
//			int lastNumericId  = Integer.parseInt(maxHolidayId.substring(1));
//			int nextNumericId=lastNumericId+1;
//			String nextHolidayId=String.format("H%05d", nextNumericId);
//			return nextHolidayId;
//		
//	}
	
//	public String autoIncrementHoliday()
//	{
//		String HolidayId=holidayRepo.HolidayId();
//		
//			int lastNumericId  = Integer.parseInt(HolidayId.substring(1));
//			int nextNumericId=lastNumericId+1;
//			String nextHolidayId=String.format("H%05d", nextNumericId);
//			return nextHolidayId;
//		
//	}

	
}
