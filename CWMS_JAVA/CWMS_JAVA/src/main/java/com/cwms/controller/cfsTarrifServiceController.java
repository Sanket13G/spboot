package com.cwms.controller;

import java.util.Date;
import java.util.List;
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
import com.cwms.entities.CFSTariffService;
import com.cwms.entities.Services;
import com.cwms.service.*;

@RestController
@RequestMapping("cfstarrif")
@CrossOrigin("*")
public class cfsTarrifServiceController {
	@Autowired
	private cfsTarrifServiceService cfsTarrifServiceService;

	@Autowired
	public ServiceImpl ServiceImpl;

	@GetMapping("/{CompId}/{BranchId}")
	public List<CFSTariffService> getAllCFSTariffService(@PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId) {

		return cfsTarrifServiceService.getcfsTarrifService(CompId, BranchId);
	}

	@GetMapping("/{CompId}/{BranchId}/{cfsTarrifNo}/cfstservices")
	public List<CFSTariffService> getTRFServicesByCfsTarrifNo(@PathVariable("cfsTarrifNo") String cfsTarrifNo,
			@PathVariable("CompId") String CompId, @PathVariable("BranchId") String BranchId) {

		return cfsTarrifServiceService.getcfsTarrifServiceById(cfsTarrifNo, CompId, BranchId);
	}

	@PostMapping("/{CompId}/{BranchId}/{CurrentUser}")
	public ResponseEntity<?> addCFSService(@PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId, @RequestBody CFSTariffService CFSTariffService,
			@PathVariable("CurrentUser") String CurrentUser) {

		String currentStatus = CFSTariffService.getStatus();

		if (currentStatus == null || currentStatus.isEmpty()) {
			CFSTariffService.setStatus("N");
			CFSTariffService.setEditedBy(CurrentUser);
		} else if ("N".equals(currentStatus)) {
			CFSTariffService.setStatus("U");
			CFSTariffService.setEditedBy(CurrentUser);
		}

		CFSTariffService.setCreatedBy(CurrentUser);
		CFSTariffService.setCompanyId(CompId);
		CFSTariffService.setBranchId(BranchId);
		CFSTariffService addcfsTarrifService = cfsTarrifServiceService.addcfsTarrifService(CFSTariffService);
		return ResponseEntity.ok(addcfsTarrifService);
	}

	@PutMapping("/{CompId}/{BranchId}/{CurrentUser}/{cfstarrifno}")
	public ResponseEntity<?> UpdateCFSService(@PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId, @RequestBody CFSTariffService CFSTariffService,
			@PathVariable("CurrentUser") String CurrentUser, @PathVariable("cfstarrifno") String cfstarrifno) {

		CFSTariffService findByTarrifNoandServiceId = cfsTarrifServiceService.findByTarrifNoandServiceId(CompId,
				BranchId, cfstarrifno, CFSTariffService.getCfsAmndNo(), CFSTariffService.getServiceId());

		if (findByTarrifNoandServiceId != null) {
			String currentStatus = CFSTariffService.getStatus();

			if (currentStatus == null || currentStatus.isEmpty()) {
				findByTarrifNoandServiceId.setStatus("N");
				findByTarrifNoandServiceId.setCreatedBy(CurrentUser);
				findByTarrifNoandServiceId.setCreatedDate(new Date());
				findByTarrifNoandServiceId.setEditedBy(CFSTariffService.getEditedBy());
				findByTarrifNoandServiceId.setEditedDate(CFSTariffService.getEditedDate());
			} else if ("N".equals(currentStatus)) {
				findByTarrifNoandServiceId.setStatus("U");
				findByTarrifNoandServiceId.setEditedBy(CurrentUser);
				findByTarrifNoandServiceId.setEditedDate(new Date());

				findByTarrifNoandServiceId.setCreatedBy(CFSTariffService.getCreatedBy());
				findByTarrifNoandServiceId.setCreatedDate(CFSTariffService.getCreatedDate());

			}

			findByTarrifNoandServiceId.setCfsTariffNo(CFSTariffService.getCfsTariffNo());
			findByTarrifNoandServiceId.setCompanyId(CFSTariffService.getCompanyId());
			findByTarrifNoandServiceId.setBranchId(CFSTariffService.getBranchId());
			findByTarrifNoandServiceId.setCfsAmndNo(CFSTariffService.getCfsAmndNo());
			findByTarrifNoandServiceId.setServiceId(CFSTariffService.getServiceId());
			findByTarrifNoandServiceId.setService(CFSTariffService.getService());
			findByTarrifNoandServiceId.setSrNo(CFSTariffService.getSrNo());
			findByTarrifNoandServiceId.setCfsDocRefNo(CFSTariffService.getCfsDocRefNo());
			findByTarrifNoandServiceId.setPartyId(CFSTariffService.getPartyId());
			findByTarrifNoandServiceId.setPayParty(CFSTariffService.getPayParty());
			findByTarrifNoandServiceId.setImporterId(CFSTariffService.getImporterId());
			findByTarrifNoandServiceId.setForwarderId(CFSTariffService.getForwarderId());
			findByTarrifNoandServiceId.setBillingParty(CFSTariffService.getBillingParty());
			findByTarrifNoandServiceId.setOnAccountOf(CFSTariffService.getOnAccountOf());
			findByTarrifNoandServiceId.setCargoMovement(CFSTariffService.getCargoMovement());
			findByTarrifNoandServiceId.setTypeOfCharges(CFSTariffService.getTypeOfCharges());
			findByTarrifNoandServiceId.setServiceUnit(CFSTariffService.getServiceUnit());
			findByTarrifNoandServiceId.setServiceUnit1(CFSTariffService.getServiceUnit1());
			findByTarrifNoandServiceId.setCriteria(CFSTariffService.getCriteria());
			findByTarrifNoandServiceId.setNegotiable(CFSTariffService.getNegotiable());
			findByTarrifNoandServiceId.setCurrencyId(CFSTariffService.getCurrencyId());
			findByTarrifNoandServiceId.setCommodity(CFSTariffService.getCommodity());
			findByTarrifNoandServiceId.setPol(CFSTariffService.getPol());
			findByTarrifNoandServiceId.setCargoType(CFSTariffService.getCargoType());
			findByTarrifNoandServiceId.setContainerType(CFSTariffService.getContainerType());
			findByTarrifNoandServiceId.setContainerSize(CFSTariffService.getContainerSize());
			findByTarrifNoandServiceId.setContainerStatus(CFSTariffService.getContainerStatus());
			findByTarrifNoandServiceId.setRate(CFSTariffService.getRate());
			findByTarrifNoandServiceId.setMovementCodeFrom(CFSTariffService.getMovementCodeFrom());
			findByTarrifNoandServiceId.setMovementCodeTo(CFSTariffService.getMovementCodeTo());
			findByTarrifNoandServiceId.setMinimumAmount(CFSTariffService.getMinimumAmount());
			findByTarrifNoandServiceId.setRangeType(CFSTariffService.getRangeType());
			findByTarrifNoandServiceId.setDiscountDays(CFSTariffService.getDiscountDays());
			findByTarrifNoandServiceId.setDiscountAmount(CFSTariffService.getDiscountAmount());
			findByTarrifNoandServiceId.setDiscountPercentage(CFSTariffService.getDiscountPercentage());
			findByTarrifNoandServiceId.setTaxApplicable(CFSTariffService.getTaxApplicable());
			findByTarrifNoandServiceId.setHazardous(CFSTariffService.getHazardous());

			findByTarrifNoandServiceId.setCfsValidateDate(CFSTariffService.getCfsValidateDate());

			findByTarrifNoandServiceId.setApprovedBy(CFSTariffService.getApprovedBy());
			findByTarrifNoandServiceId.setApprovedDate(CFSTariffService.getApprovedDate());
//	        findByTarrifNoandServiceId.setStatus(CFSTariffService.getStatus());
			findByTarrifNoandServiceId.setRateCalculation(CFSTariffService.getRateCalculation());

			CFSTariffService addcfsTarrifService = cfsTarrifServiceService.updatecfsTarrifService(findByTarrifNoandServiceId);
			return ResponseEntity.ok(addcfsTarrifService);

		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{CompId}/{BranchId}/{CurrentUser}/{cfstarrifno}/status")
	public ResponseEntity<?> UpdateCFSServiceStatus(@PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId, @RequestBody CFSTariffService CFSTariffService,
			@PathVariable("CurrentUser") String CurrentUser,
			@PathVariable("cfstarrifno") String cfstarrifno
			) {
		
		
		CFSTariffService findByTarrifNoandServiceId = cfsTarrifServiceService.findByTarrifNoandServiceId(CompId,
				BranchId, cfstarrifno, CFSTariffService.getCfsAmndNo(), CFSTariffService.getServiceId());
		
		if (findByTarrifNoandServiceId != null) {
			
			
			findByTarrifNoandServiceId.setCfsTariffNo(CFSTariffService.getCfsTariffNo());
	        findByTarrifNoandServiceId.setCompanyId(CFSTariffService.getCompanyId());
	        findByTarrifNoandServiceId.setBranchId(CFSTariffService.getBranchId());
	        findByTarrifNoandServiceId.setCfsAmndNo(CFSTariffService.getCfsAmndNo());
	        findByTarrifNoandServiceId.setServiceId(CFSTariffService.getServiceId());
	        findByTarrifNoandServiceId.setService(CFSTariffService.getService());
	        findByTarrifNoandServiceId.setSrNo(CFSTariffService.getSrNo());
	        findByTarrifNoandServiceId.setCfsDocRefNo(CFSTariffService.getCfsDocRefNo());
	        findByTarrifNoandServiceId.setPartyId(CFSTariffService.getPartyId());
	        findByTarrifNoandServiceId.setPayParty(CFSTariffService.getPayParty());
	        findByTarrifNoandServiceId.setImporterId(CFSTariffService.getImporterId());
	        findByTarrifNoandServiceId.setForwarderId(CFSTariffService.getForwarderId());
	        findByTarrifNoandServiceId.setBillingParty(CFSTariffService.getBillingParty());
	        findByTarrifNoandServiceId.setOnAccountOf(CFSTariffService.getOnAccountOf());
	        findByTarrifNoandServiceId.setCargoMovement(CFSTariffService.getCargoMovement());
	        findByTarrifNoandServiceId.setTypeOfCharges(CFSTariffService.getTypeOfCharges());
	        findByTarrifNoandServiceId.setServiceUnit(CFSTariffService.getServiceUnit());
	        findByTarrifNoandServiceId.setServiceUnit1(CFSTariffService.getServiceUnit1());
	        findByTarrifNoandServiceId.setCriteria(CFSTariffService.getCriteria());
	        findByTarrifNoandServiceId.setNegotiable(CFSTariffService.getNegotiable());
	        findByTarrifNoandServiceId.setCurrencyId(CFSTariffService.getCurrencyId());
	        findByTarrifNoandServiceId.setCommodity(CFSTariffService.getCommodity());
	        findByTarrifNoandServiceId.setPol(CFSTariffService.getPol());
	        findByTarrifNoandServiceId.setCargoType(CFSTariffService.getCargoType());
	        findByTarrifNoandServiceId.setContainerType(CFSTariffService.getContainerType());
	        findByTarrifNoandServiceId.setContainerSize(CFSTariffService.getContainerSize());
	        findByTarrifNoandServiceId.setContainerStatus(CFSTariffService.getContainerStatus());
	        findByTarrifNoandServiceId.setRate(CFSTariffService.getRate());
	        findByTarrifNoandServiceId.setMovementCodeFrom(CFSTariffService.getMovementCodeFrom());
	        findByTarrifNoandServiceId.setMovementCodeTo(CFSTariffService.getMovementCodeTo());
	        findByTarrifNoandServiceId.setMinimumAmount(CFSTariffService.getMinimumAmount());
	        findByTarrifNoandServiceId.setRangeType(CFSTariffService.getRangeType());
	        findByTarrifNoandServiceId.setDiscountDays(CFSTariffService.getDiscountDays());
	        findByTarrifNoandServiceId.setDiscountAmount(CFSTariffService.getDiscountAmount());
	        findByTarrifNoandServiceId.setDiscountPercentage(CFSTariffService.getDiscountPercentage());
	        findByTarrifNoandServiceId.setTaxApplicable(CFSTariffService.getTaxApplicable());
	        findByTarrifNoandServiceId.setHazardous(CFSTariffService.getHazardous());
	        findByTarrifNoandServiceId.setCreatedBy(CFSTariffService.getCreatedBy());
	        findByTarrifNoandServiceId.setCreatedDate(CFSTariffService.getCreatedDate());
	        findByTarrifNoandServiceId.setEditedBy(CFSTariffService.getEditedBy());
	        findByTarrifNoandServiceId.setCfsValidateDate(CFSTariffService.getCfsValidateDate());
	        findByTarrifNoandServiceId.setEditedDate(CFSTariffService.getEditedDate());
	        findByTarrifNoandServiceId.setRateCalculation(CFSTariffService.getRateCalculation());

	 
			
			
			
	        findByTarrifNoandServiceId.setStatus("A");
	        findByTarrifNoandServiceId.setApprovedBy(CurrentUser);
	        findByTarrifNoandServiceId.setApprovedDate(new Date());
		CFSTariffService addcfsTarrifService = cfsTarrifServiceService.updatecfsTarrifService(findByTarrifNoandServiceId);
		return ResponseEntity.ok(addcfsTarrifService);
		}
		
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{CompId}/{BranchId}/{TarrifNo}/{amndno}/{ServiceId}/Single")
	public CFSTariffService findByTarrifNoAndService(@PathVariable("TarrifNo") String TarrifNo,
			@PathVariable("amndno") String amndno, @PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId, @PathVariable("ServiceId") String ServiceId) {
		return cfsTarrifServiceService.findByTarrifNoandServiceId(CompId, BranchId, TarrifNo, amndno, ServiceId);
	}

	@DeleteMapping("/{CompId}/{BranchId}/{TarrifNo}/{amndno}/{ServiceId}/delete")
	public void DeleteTarrif(@PathVariable("TarrifNo") String TarrifNo, @PathVariable("ServiceId") String ServiceId,
			@PathVariable("amndno") String amndno, @PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId) {
		CFSTariffService findByTarrifNoandServiceId = cfsTarrifServiceService.findByTarrifNoandServiceId(CompId,
				BranchId, TarrifNo, amndno, ServiceId);
		findByTarrifNoandServiceId.setStatus("D");
		cfsTarrifServiceService.updatecfsTarrifService(findByTarrifNoandServiceId);

	}
}
