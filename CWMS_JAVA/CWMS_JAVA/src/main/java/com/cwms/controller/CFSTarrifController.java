package com.cwms.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cwms.entities.CfsTarrif;
import com.cwms.service.CFSService;
import com.cwms.service.CFSTariffRangeService;
import com.cwms.service.ProcessNextIdService;

@RequestMapping("tarrif")
@CrossOrigin("*")
@RestController
public class CFSTarrifController {
	@Autowired
	public CFSService CFSService;
	@Autowired
	public CFSTariffRangeService CFSTariffRangeService;
	
	@Autowired
	public ProcessNextIdService proccessNextIdService;

	@GetMapping("/{cid}/{bid}")
	public List<CfsTarrif> getAllTarrifServices(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		return CFSService.getTarrif(cid,bid);
	}

	
	@GetMapping("/{cid}/{bid}/{cfsTarrifNo}/cfstarrif")
	public CfsTarrif getTarrifyCfsTarrifNo(@PathVariable("cid") String cid, @PathVariable("bid") String bid,@PathVariable("cfsTarrifNo") String cfsTarrifNo) {

		return CFSService.getTarrifById(cfsTarrifNo,cid,bid);
	}
	
	
	@PostMapping("/{CompId}/{BranchId}/{CurrentUser}")
	public ResponseEntity<?> addTarrf(@PathVariable("CompId") String CompId, @PathVariable("BranchId") String BranchId,
			@RequestBody CfsTarrif CfsTarrif, @PathVariable("CurrentUser") String CurrentUser) {
		CfsTarrif.setCompanyId(CompId);
		CfsTarrif.setCfsTariffDate(new Date());
		CfsTarrif.setBranchId(BranchId);
		CfsTarrif.setCreatedDate(new Date());
		CfsTarrif.setCreatedBy(CurrentUser);
		CfsTarrif.setCfsAmndNo("000");
		CfsTarrif.setStatus("N");
		String autoIncrementCFSTarrifNextId = proccessNextIdService.autoIncrementCFSTarrifNextId();
		CfsTarrif.setCfsTariffNo(autoIncrementCFSTarrifNextId);

		CfsTarrif addcfsTarrifService = CFSService.addTarrifService(CfsTarrif);
		return ResponseEntity.ok(addcfsTarrifService);
	}

	@PutMapping("/{CompId}/{BranchId}/{CurrentUser}/{cfstarrifno}/update")
	public ResponseEntity<?> updateTarrif(@PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId,
			@PathVariable("cfstarrifno") String cfstarrifno,
			@RequestBody CfsTarrif CfsTarrif,
			@PathVariable("CurrentUser") String CurrentUser) {
		
		
		CfsTarrif tarrifById = CFSService.getTarrifById(cfstarrifno,CompId,BranchId);
		
		
		if(tarrifById != null)
		{
			
			String currentStatus = CfsTarrif.getStatus();

			if (currentStatus == null || currentStatus.isEmpty()) {
				tarrifById.setStatus("N");
				tarrifById.setCreatedBy(CurrentUser);
			    tarrifById.setCreatedDate(new Date());
				
			} else if ("N".equals(currentStatus)) {
				tarrifById.setStatus("U");
				tarrifById.setEditedBy(CurrentUser);
				tarrifById.setEditedDate(new Date());
			}
			
			tarrifById.setParty_Name(CfsTarrif.getParty_Name());
		    tarrifById.setCompanyId(CfsTarrif.getCompanyId());
		    tarrifById.setBranchId(CfsTarrif.getBranchId());
		    tarrifById.setCfsAmndNo(CfsTarrif.getCfsAmndNo());
		    tarrifById.setCargoMovement(CfsTarrif.getCargoMovement());
		    tarrifById.setCfsDocRefNo(CfsTarrif.getCfsDocRefNo());
		    tarrifById.setPartyId(CfsTarrif.getPartyId());
		    tarrifById.setCfsTariffDate(CfsTarrif.getCfsTariffDate());
		    tarrifById.setCfsValidateDate(CfsTarrif.getCfsValidateDate());
		    tarrifById.setTypeOfShipment(CfsTarrif.getTypeOfShipment());
		    tarrifById.setComments(CfsTarrif.getComments());   
		    tarrifById.setApprovedBy(CfsTarrif.getApprovedBy());
		    tarrifById.setApprovedDate(CfsTarrif.getApprovedDate());
//		    tarrifById.setStatus(CfsTarrif.getStatus());
		
		
		
		CfsTarrif updateTarrifService = CFSService.updateTarrif(tarrifById);
		return ResponseEntity.ok(updateTarrifService);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{CompId}/{BranchId}/{CurrentUser}/{cfstarrifno}/status")
	public ResponseEntity<?> UpdateTarrifStatus(@PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId, @RequestBody CfsTarrif CfsTarrif,
			@PathVariable("CurrentUser") String CurrentUser,
			@PathVariable("cfstarrifno") String cfstarrifno
			) {
		
		CfsTarrif tarrifById = CFSService.getTarrifById(cfstarrifno,CompId,BranchId);
		if(tarrifById != null)
		{
			tarrifById.setCfsTariffNo(cfstarrifno);
			
			tarrifById.setParty_Name(CfsTarrif.getParty_Name());
		    tarrifById.setCompanyId(CfsTarrif.getCompanyId());
		    tarrifById.setBranchId(CfsTarrif.getBranchId());
		    tarrifById.setCfsAmndNo(CfsTarrif.getCfsAmndNo());
		    tarrifById.setCargoMovement(CfsTarrif.getCargoMovement());
		    tarrifById.setCfsDocRefNo(CfsTarrif.getCfsDocRefNo());
		    tarrifById.setPartyId(CfsTarrif.getPartyId());
		    tarrifById.setCfsTariffDate(CfsTarrif.getCfsTariffDate());
		    tarrifById.setCfsValidateDate(CfsTarrif.getCfsValidateDate());
		    tarrifById.setTypeOfShipment(CfsTarrif.getTypeOfShipment());
		    tarrifById.setComments(CfsTarrif.getComments());   
		    tarrifById.setApprovedBy(CfsTarrif.getApprovedBy());
		    tarrifById.setApprovedDate(CfsTarrif.getApprovedDate());
		    tarrifById.setEditedBy(CfsTarrif.getEditedBy());
			tarrifById.setEditedDate(CfsTarrif.getEditedDate());
			tarrifById.setCreatedBy(CfsTarrif.getCreatedBy());
		    tarrifById.setCreatedDate(CfsTarrif.getCreatedDate());
			tarrifById.setStatus("A");
			tarrifById.setApprovedBy(CurrentUser);
			tarrifById.setApprovedDate(new Date());
			
//			Make all the services status to approved
//			List<CFSTariffRange> findByCfsTariffNoAndServiceId = CFSTariffRangeService.findByCfsTariffNoAndServiceId(CompId,BranchId,cfstarrifno,CfsTarrif.getCfsAmndNo() ,ServiceId);
			
		CfsTarrif addcfsTarrifService = CFSService.updateTarrif(tarrifById);
		return ResponseEntity.ok(addcfsTarrifService);
		}
		return ResponseEntity.notFound().build();
	}

}
