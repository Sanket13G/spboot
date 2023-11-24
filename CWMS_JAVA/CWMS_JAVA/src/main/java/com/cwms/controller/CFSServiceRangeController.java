package com.cwms.controller;
import java.util.Date;
import java.util.List;
import com.cwms.entities.CFSTariffRange;
import com.cwms.repository.CFSTariffRangeRepositary;
import com.cwms.service.CFSTariffRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/range")
@CrossOrigin("*")
@RestController
public class CFSServiceRangeController 
{
	@Autowired
	public CFSTariffRangeService CFSTariffRangeService;
	
	@Autowired
	public CFSTariffRangeRepositary CFSTariffRangeRepositary;

	
	@GetMapping("/{CompId}/{BranchId}")
	public List<CFSTariffRange> getAll(@PathVariable("CompId")String CompId,@PathVariable("BranchId") String BranchId)
	{
		return CFSTariffRangeService.getcfsTarrifServiceRange(CompId,BranchId);
	}
	
	@GetMapping("/ById/{CompId}/{BranchId}/{cfsTarrifNo}/{amndno}")
	public List<CFSTariffRange> getTarrifByNo(@PathVariable("cfsTarrifNo") String cfsTarrifNo ,
			@PathVariable("CompId")String CompId,@PathVariable("BranchId") String BranchId,
			@PathVariable("amndno") String amndno)
	{
		return CFSTariffRangeService.getcfsTarrifServiceRangeById(CompId,BranchId,cfsTarrifNo,amndno);
	}
	

	@PostMapping("/{cid}/{bid}/{currentUser}/add")
	public ResponseEntity<?> updateCFSTariffRange(@PathVariable("cid")String compId,@PathVariable("bid")String BranId,
			@PathVariable("currentUser")String currentUser,@RequestBody CFSTariffRange CFSTariffRange)
	{
		
		
		List<CFSTariffRange> getcfsTarrifServiceRangeById = CFSTariffRangeService.getcfsTarrifServiceRangeById(compId,BranId,CFSTariffRange.getCfsTariffNo(),CFSTariffRange.getCfsAmndNo());
		
		for (CFSTariffRange range : getcfsTarrifServiceRangeById) 
			if (range.getStatus() == null || range.getStatus().isEmpty()) {
	            range.setStatus("N");
	            range.setCreatedBy(currentUser);
	            range.setCreatedDate(new Date());
	        } else if (range.getStatus().equals("N")) {
	            range.setStatus("U");
	            range.setEditedBy(currentUser);
	            range.setEditedDate(new Date());
	        }
		CFSTariffRangeService.saveAllTariffRanges(getcfsTarrifServiceRangeById);
		CFSTariffRange.setCompanyId(compId);
		CFSTariffRange.setBranchId(BranId);
		if (CFSTariffRange.getStatus() == null || CFSTariffRange.getStatus().isEmpty()) {
		    CFSTariffRange.setStatus("N");
		    CFSTariffRange.setCreatedBy(currentUser);
			CFSTariffRange.setCreatedDate(new Date());
		} else if ("N".equals(CFSTariffRange.getStatus())) {
		    CFSTariffRange.setStatus("U");
		    CFSTariffRange.setEditedBy(currentUser);
		    CFSTariffRange.setEditedDate(new Date());
		} 
				
		CFSTariffRange addcfsTarrifServiceRange = CFSTariffRangeService.updatecfsTarrifServiceRange(CFSTariffRange);
		
		
		
		return ResponseEntity.ok(addcfsTarrifServiceRange);
		
	}
	
	@PutMapping("/cid/bid/currentUser/tarrifno/status")
	public ResponseEntity<?> updateCFSTariffRangeStatus(@PathVariable("cid")String compId,@PathVariable("bid")String BranId,
			@PathVariable("currentUser")String currentUser,@RequestBody CFSTariffRange CFSTariffRange)
	{
		
//		CFSTariffRange.setCompanyId(compId);
//		CFSTariffRange.setBranchId(BranId);
		CFSTariffRange.setApprovedBy(currentUser);
		CFSTariffRange.setApprovedDate(new Date());
		CFSTariffRange.setStatus("A");
		CFSTariffRange addcfsTarrifServiceRange = CFSTariffRangeService.updatecfsTarrifServiceRange(CFSTariffRange);
		
		return ResponseEntity.ok(addcfsTarrifServiceRange);
		
	}
 
	@GetMapping("/{cid}/{bid}/{tarrifno}/{amndno}/{serviceId}/ser")
	public List<CFSTariffRange> getCFSTariffRange(@PathVariable("tarrifno")String tarrifno,@PathVariable("serviceId") String serviceId,
			@PathVariable("cid")String cid,@PathVariable("bid")String bid,@PathVariable("amndno") String amndno)
	{
		return CFSTariffRangeService.findByCfsTariffNoAndServiceId(cid,bid,tarrifno,amndno,serviceId);
	}
	
	@GetMapping("/{cid}/{bid}/{tarrifNo}/join")
	public List<Object[]> findByCfsTariffNoAndServiceIdAndSrlNo(@PathVariable("tarrifNo")String tarrifNo,@PathVariable("cid")String cid,@PathVariable("bid")String bid)
	{
		return CFSTariffRangeRepositary.getCombinedResultsForTariff(cid,bid,tarrifNo);
	}
	
	
	@PostMapping("/tariffRanges/saveAll/{user}")
	public List<CFSTariffRange> saveAll(@RequestBody List<CFSTariffRange> tariffRanges,
			@PathVariable("user")String User)
	{
		for (CFSTariffRange range : tariffRanges) 
			if (range.getStatus() == null || range.getStatus().isEmpty()) {
	            range.setStatus("N");
	            range.setCreatedBy(User);
	            range.setCreatedDate(new Date());

	        } else if (range.getStatus().equals("N")) {
	            range.setStatus("U");
	            range.setEditedBy(User);
	            range.setEditedDate(new Date());
	        }
	
		return CFSTariffRangeService.saveAllTariffRanges(tariffRanges);
	}
	
	@DeleteMapping("/{cid}/{bid}/{TarrifNo}/{amndno}/{ServiceId}/delete")
	public void DeleteTarrif(@PathVariable("TarrifNo") String TarrifNo,
			@PathVariable("ServiceId") String ServiceId,@PathVariable("cid")String cid,@PathVariable("bid")String bid,@PathVariable("amndno") String amndno)
	{
		List<CFSTariffRange> findByCfsTariffNoAndServiceId = CFSTariffRangeService.findByCfsTariffNoAndServiceId(cid,bid,TarrifNo,amndno ,ServiceId);
		for(CFSTariffRange range:findByCfsTariffNoAndServiceId)
		{
			range.setStatus("D");
		}
		CFSTariffRangeService.saveAllTariffRanges(findByCfsTariffNoAndServiceId);
	}
	
}
