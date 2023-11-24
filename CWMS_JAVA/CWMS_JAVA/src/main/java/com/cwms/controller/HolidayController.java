package com.cwms.controller;

import java.util.List;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.HolidayMaster;
import com.cwms.repository.HolidayRepository;
import com.cwms.service.HolidayService;
import com.cwms.service.ProcessNextIdService;

@CrossOrigin("*")
@RestController
@RequestMapping("/holiday")

public class HolidayController {

	
	public  final HolidayService holidayService;
	
	@Autowired
	public HolidayRepository holidayrepo;
	
	@Autowired
	public HolidayController(HolidayService holidayService) {
		this.holidayService = holidayService;
	}

	@Autowired
	private ProcessNextIdService proccessNextIdService;
	
	
	@PostMapping("/addHoliday")
	public ResponseEntity<HolidayMaster> createHoliday(@RequestBody HolidayMaster holiday,@RequestHeader("React-Page-Name") String reactPageName)
	{
		//String nextHolidayId=holidayService.autoIncrementHoliday();
		//holiday.setHolidayId(nextHolidayId);
		MDC.put("reactPageName", reactPageName);
		 String nextholi = proccessNextIdService.autoIncrementNextIdHoliday();
    	 holiday.setHolidayId(nextholi);
		HolidayMaster createHoliday=holidayService.createHoliday(holiday);
		return ResponseEntity.status(HttpStatus.CREATED).body(createHoliday);
	}
	
	@GetMapping("/allHoliday/{cid}/{bid}")
	public List<HolidayMaster> getAllHoliday(@PathVariable("cid") String cid,@PathVariable("bid") String bid)
	{
		
		
		return this.holidayrepo.getalldata(cid, bid);
	}

	@GetMapping("/getHoliday/{holidayId}")
	public ResponseEntity<HolidayMaster> getHolidayById(@PathVariable String holidayId,@RequestHeader("React-Page-Name") String reactPageName)
	{
		MDC.put("reactPageName", reactPageName);
		HolidayMaster holiday=holidayService.getHoliday(holidayId);
		if(holiday!=null)
		{
			return ResponseEntity.ok(holiday);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
//	@PutMapping("/update/{holidayId}")
//	public ResponseEntity<HolidayMaster> updateHolidayMaster(@PathVariable String holidayId,@RequestBody HolidayMaster holidayUpdate)
//	{
//		HolidayMaster holidayMaster=holidayService.updateHoliday(holidayId, holidayUpdate);
//		
//		return new ResponseEntity<>(holidayMaster,HttpStatus.OK);
//		
//	}
	
	 @PutMapping("/update/{holidayId}")
	    public ResponseEntity<HolidayMaster> updateHolidayMaster(
	            @PathVariable String holidayId,
	            @RequestBody HolidayMaster holidayUpdate) {
	
	        try {
	            HolidayMaster updatedHoliday = holidayService.updateHoliday(holidayId, holidayUpdate);
	            return ResponseEntity.ok(updatedHoliday);
	        } catch (Exception e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
	
	
	@PutMapping("/delete/{cid}/{bid}/{holidayId}")
	public ResponseEntity<Void> deleteHolidayById(@PathVariable("holidayId") String holidayId,@PathVariable("cid") String cid,@PathVariable("bid") String bid)
	{
	
		this.holidayrepo.deleteByCompanyholidayid(cid, bid, holidayId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	
	
	
}