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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cwms.entities.Services;
import com.cwms.repository.UserRepository;
import com.cwms.service.ProcessNextIdService;
import com.cwms.service.ServicesInterface;

@RestController
@RequestMapping("service")
@CrossOrigin("*")
public class ServiceController {
	@Autowired
	public UserRepository UserRepository;
	@Autowired
	private ServicesInterface serviceOfService;
	@Autowired
	public ProcessNextIdService proccessNextIdService;

	@PostMapping("/{cid}/{bid}/{currentUser}")
	public ResponseEntity<?> addServices(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("currentUser") String currentUser, @RequestBody Services service) {
		service.setBranch_Id(bid);
		service.setCreatedBy(currentUser);
		service.setCreatedDate(new Date());
		service.setStatus("N");
		String autoIncrementServiceId = proccessNextIdService.autoIncrementServiceNextId();
		service.setService_Id(autoIncrementServiceId);
		service.setCompany_Id(cid);
		Services addService = serviceOfService.addService(service);

		return ResponseEntity.ok(addService);
	}

	@PutMapping("/{cid}/{bid}/{sid}/{currentUser}")
	public ResponseEntity<?> updateServices(@PathVariable("sid") String sid, @PathVariable("cid") String cid,
			@PathVariable("bid") String bid, @PathVariable("currentUser") String currentUser,
			@RequestBody Services service) {

		Services servicesById = serviceOfService.getServicesById(cid, bid, sid);

		if (servicesById != null)

		{

			if ("N".equals(service.getStatus())) {
				servicesById.setStatus("U");
				servicesById.setEditedBy(currentUser);
				servicesById.setEditedDate(new Date());
			}
			if ("A".equals(service.getStatus())) {
				servicesById.setStatus("A");
			}

			servicesById.setServiceShortDescription(service.getServiceShortDescription());
			servicesById.setServiceLongDescription(service.getServiceLongDescription());
			servicesById.setServiceUnit(service.getServiceUnit());
			servicesById.setTypeOfCharges(service.getTypeOfCharges());
			servicesById.setServiceUnit1(service.getServiceUnit1());
			servicesById.setServiceType(service.getServiceType());
			servicesById.setTaxApplicable(service.getTaxApplicable());
			servicesById.setTaxPercentage(service.getTaxPercentage());
			servicesById.setDiscountPercentage(service.getDiscountPercentage());
			servicesById.setDiscountDays(service.getDiscountDays());
			servicesById.setDiscountAmount(service.getDiscountAmount());
			servicesById.setSacCode(service.getSacCode());
			servicesById.setServiceNewDescription(service.getServiceNewDescription());
			servicesById.setServiceChangeDate(service.getServiceChangeDate());
			servicesById.setServiceGroup(service.getServiceGroup());
			servicesById.setCreatedBy(service.getCreatedBy());
			servicesById.setCreatedDate(service.getCreatedDate());
			servicesById.setRateCalculation(service.getRateCalculation());

			Services updateService = serviceOfService.updateService(servicesById);
			return ResponseEntity.ok(updateService);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{cid}/{bid}/{sid}/{currentUser}/status")
	public ResponseEntity<?> updateServicesStutus(@PathVariable("sid") String sid, @PathVariable("cid") String cid,
			@PathVariable("bid") String bid, @PathVariable("currentUser") String currentUser,
			@RequestBody Services service) {

		Services servicesById = serviceOfService.getServicesById(cid, bid, sid);

		if (servicesById != null)

		{

			if ("A".equals(service.getStatus())) {
				servicesById.setStatus("A");
			} else {
				servicesById.setApprovedBy(currentUser);
				servicesById.setStatus("A");
				servicesById.setApprovedDate(new Date());
			}

			servicesById.setServiceShortDescription(service.getServiceShortDescription());
			servicesById.setServiceLongDescription(service.getServiceLongDescription());
			servicesById.setServiceUnit(service.getServiceUnit());
			servicesById.setTypeOfCharges(service.getTypeOfCharges());
			servicesById.setServiceUnit1(service.getServiceUnit1());
			servicesById.setServiceType(service.getServiceType());
			servicesById.setTaxApplicable(service.getTaxApplicable());
			servicesById.setTaxPercentage(service.getTaxPercentage());
			servicesById.setDiscountPercentage(service.getDiscountPercentage());
			servicesById.setDiscountDays(service.getDiscountDays());
			servicesById.setDiscountAmount(service.getDiscountAmount());
			servicesById.setSacCode(service.getSacCode());
			servicesById.setServiceNewDescription(service.getServiceNewDescription());
			servicesById.setServiceChangeDate(service.getServiceChangeDate());
			servicesById.setServiceGroup(service.getServiceGroup());
			servicesById.setCreatedBy(service.getCreatedBy());
			servicesById.setCreatedDate(service.getCreatedDate());
			servicesById.setRateCalculation(service.getRateCalculation());

			Services updateService = serviceOfService.updateService(servicesById);
			return ResponseEntity.ok(updateService);
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{cid}/{bid}")
	public ResponseEntity<?> getServicess(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		return ResponseEntity.ok(serviceOfService.getServices(cid, bid));
	}

	@GetMapping("/{cid}/{bid}/{sid}")
	public Services getServicesById(@PathVariable("sid") String sid, @PathVariable("cid") String cid,
			@PathVariable("bid") String bid) {
		return serviceOfService.getServicesById(cid, bid, sid);
	}

	@DeleteMapping("/{cid}/{bid}/{sid}")
	public void deleteServiceById(@PathVariable("sid") String sid, @PathVariable("cid") String cid,
			@PathVariable("bid") String bid

	) {
		Services servicesById = serviceOfService.getServicesById(cid, bid, sid);
		servicesById.setStatus("D");
		serviceOfService.updateService(servicesById);
	}

	@GetMapping("/{cid}/{bid}/diffservice")
	public List<Services> getPartiesNotInIds(@RequestParam("excludedserviceIds") List<String> excludedserviceIds,
			@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		List<Services> excludedServices = serviceOfService.findByService_IdNotIn(cid, bid, excludedserviceIds);

		if (excludedserviceIds == null || excludedserviceIds.isEmpty()) {
			return serviceOfService.getServices(cid, bid); // Fetch all services
		}

		return excludedServices;
	}

}