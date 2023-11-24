package com.cwms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.ImportPC;
import com.cwms.service.ImportPCService;

@CrossOrigin("*")
@RestController
@RequestMapping("importpc")
public class ImportPCController 
{
	@Autowired
	private ImportPCService importPCService;
	
	
	@GetMapping("/{compid}/{branchId}/{MAWB}/{HAWB}/{sirNo}/getSingle")
	public ImportPC getbyIds(
			@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			 @PathVariable("sirNo") String sirNo)
	{
		return importPCService.getByIDS(compid, branchId, MAWB, HAWB, sirNo);
	}
	
	
	@PostMapping("/{compid}/{branchId}/{User}/{MAWB}/{HAWB}/{sirNo}/addimportpc")
	public ImportPC AddIMportPc (@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("User") String User,@RequestBody ImportPC ImportPC ,
			@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("sirNo") String sirNo)
	{
		
		
		ImportPC.setCompanyId(compid);
		ImportPC.setBranchId(branchId);
		ImportPC.setMawb(MAWB);
		ImportPC.setHawb(HAWB);
		ImportPC.setSirNo(sirNo);
		
		ImportPC.setCreatedBy(User);
		ImportPC.setCreatedDate(new Date());
//		ImportPC.setEditedBy(User);
//		ImportPC.setEditedDate(new Date());
		ImportPC.setApprovedBy(User);
		ImportPC.setApprovedDate(new Date());
		ImportPC.setStatus("N");
		
		return importPCService.AddImportPC(ImportPC);
	}
	
	@PostMapping("/{compid}/{branchId}/{User}/{MAWB}/{HAWB}/{sirNo}/updateimportpc")
	public ImportPC updateIMportPc (@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("User") String User,@RequestBody ImportPC byIDS ,
			@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("sirNo") String sirNo)
	{
		ImportPC importPC = importPCService.getByIDS(compid, branchId, MAWB, HAWB, sirNo);
		
		
		
		if(importPC != null)
		{
			
			
//			importPC.getId().setCompanyId(byIDS.getCompanyId());
//			importPC.getId().setBranchId(byIDS.getBranchId());
//			importPC.getId().setMawb(byIDS.getMawb());
//			importPC.getId().setHawb(byIDS.getHawb());
//			importPC.getId().setSirNo(byIDS.getSirNo());

			// Set values for other attributes
			importPC.setPassengerName(byIDS.getPassengerName());
			importPC.setAddress(byIDS.getAddress());
			importPC.setFlightNo(byIDS.getFlightNo());
			importPC.setFlightDate(byIDS.getFlightDate());
			importPC.setNationality(byIDS.getNationality());
			importPC.setDeputedCoName(byIDS.getDeputedCoName());
			importPC.setDeputedCoDesignation(byIDS.getDeputedCoDesignation());
			importPC.setDeputedFromDestination(byIDS.getDeputedFromDestination());
			importPC.setDeputedToDestination(byIDS.getDeputedToDestination());
			importPC.setEscortDate(byIDS.getEscortDate());
			importPC.setApproverName(byIDS.getApproverName());
			importPC.setApproverDesignation(byIDS.getApproverDesignation());
			importPC.setApproverDate(byIDS.getApproverDate());
			importPC.setConfirmation(byIDS.getConfirmation());
			importPC.setStatus("E");
			importPC.setCreatedBy(byIDS.getCreatedBy());
			importPC.setCreatedDate(byIDS.getCreatedDate());
			importPC.setEditedBy(User);
			importPC.setEditedDate(new Date());
			importPC.setApprovedBy(byIDS.getApprovedBy());
			importPC.setApprovedDate(byIDS.getApprovedDate());
			importPC.setPassportNo(byIDS.getPassengerName());
		
		return importPCService.AddImportPC(importPC);
		}
		return null;
	}
}