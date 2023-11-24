package com.cwms.controller;

import java.util.Date;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.cwms.entities.Branch;
import com.cwms.entities.Company;
import com.cwms.entities.Detention;
import com.cwms.entities.Detention_History;
import com.cwms.repository.BranchRepo;
import com.cwms.repository.CompanyRepo;
import com.cwms.repository.DetantionRepository;
import com.cwms.repository.Detention_HistoryRepository;
import com.cwms.repository.PartyRepository;
import com.cwms.service.DetantionService;
import com.cwms.service.ProcessNextIdService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/detention")
@CrossOrigin("*")
public class DetentionController {

	
	@Autowired
	private CompanyRepo companyRepo;
	
	@Autowired
	private BranchRepo branchRepo;
	
	
	@Autowired
	public PartyRepository partyRepository;
	
	
	@Autowired
	public DetantionService detentionService;

	@Autowired
	public DetantionRepository detentionRepository;

	@Autowired
	public ProcessNextIdService proccessNextIdService;

	@Autowired
	private Detention_HistoryRepository detentionHistoryRepository;

	@GetMapping("/all")
	public List<Detention> getAllDataByCompanyAndBranch(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId) {
		return detentionService.getAllDataByCompanyAndBranch(companyId, branchId);
	}

	@PostMapping("/add/{cid}/{bid}")
	public ResponseEntity<Object> createDetention(@RequestBody Detention deten, @PathVariable("cid") String cid,
			@PathVariable("bid") String bid) {
		String nextDetainID = proccessNextIdService.autoIncrementDetentionId();

		deten.setDetentionId(nextDetainID);
		deten.setApprovedDate(new Date());
		deten.setCreatedDate(new Date());
		deten.setStatus("Deposited");
		deten.setBranchId(bid);
		deten.setCompanyId(cid);

		Detention d = detentionService.adddetention(deten);

		Detention_History gh = new Detention_History();

		gh.setCompanyId(cid);
		gh.setBranchId(bid);
		gh.setSiNo(d.getSiNo());
		gh.setOfficerName(deten.getOfficerName());
		gh.setFileNo(deten.getFileNo());
		gh.setDate(deten.getDepositDate());
		gh.setStatus(deten.getStatus());
		gh.setRemarks(deten.getRemarks());

		detentionHistoryRepository.save(gh);

		return new ResponseEntity<>("Detention added successfully.", HttpStatus.OK);
	}

	@PutMapping("/withdraw/{detentionId}")
	public ResponseEntity<Detention> updateDetentionById(@PathVariable String detentionId,
			@RequestBody Detention updatedWithdraw) {
		Detention updatedDetentionResult = detentionService.updateWithraw(detentionId, updatedWithdraw);
		updatedDetentionResult.setStatus("Withdraw");
		detentionRepository.save(updatedDetentionResult);
		Detention_History gh = new Detention_History();

		gh.setCompanyId(updatedDetentionResult.getCompanyId());
		gh.setBranchId(updatedDetentionResult.getBranchId());
		gh.setSiNo(updatedDetentionResult.getSiNo());
		gh.setOfficerName(updatedDetentionResult.getWithdrawOfficerName());
		gh.setFileNo(updatedDetentionResult.getFileNo());
		gh.setDate(updatedDetentionResult.getWithdrawDate());
		gh.setStatus(updatedDetentionResult.getStatus());
		gh.setRemarks(updatedDetentionResult.getWithdrawRemarks());

		detentionHistoryRepository.save(gh);
		return new ResponseEntity<>(updatedDetentionResult, HttpStatus.OK);
	}

	@PreAuthorize("hasPermission(#detentionId, 'com.cwms.entities.Detention', 'WRITE')")

	@PutMapping("/issued/{detentionId}")
	public ResponseEntity<Detention> updateIssueData(@PathVariable String detentionId,
			@RequestBody Detention updatedIssue) {
		Detention updatedDetentionIssue = detentionService.updateIssue(detentionId, updatedIssue);
		if ("Full".equals(updatedDetentionIssue.getIssueType())) {
			updatedDetentionIssue.setStatus("Issued");
		} else if ("Part".equals(updatedDetentionIssue.getIssueType())) {
			updatedDetentionIssue.setStatus("Partly Issued");
		}

		detentionRepository.save(updatedDetentionIssue);

		Detention_History gh = new Detention_History();

		gh.setCompanyId(updatedDetentionIssue.getCompanyId());
		gh.setBranchId(updatedDetentionIssue.getBranchId());
		gh.setSiNo(updatedDetentionIssue.getSiNo());
		gh.setOfficerName(updatedDetentionIssue.getIssueOfficerName());
		gh.setFileNo(updatedDetentionIssue.getFileNo());
		gh.setDate(updatedDetentionIssue.getIssueDate());
		gh.setStatus(updatedDetentionIssue.getStatus());
		gh.setRemarks(updatedDetentionIssue.getIssueRemarks());

		detentionHistoryRepository.save(gh);
		return new ResponseEntity<>(updatedDetentionIssue, HttpStatus.OK);
	}

	@PutMapping("/redeposite/{detentionId}")
	@Transactional
	public ResponseEntity<Detention> updateRedepositeData(@PathVariable String detentionId,
			@RequestBody Detention updatedRedeposite) {
		Detention updatedDetentionRedeposite = detentionService.updateRedeposite(detentionId, updatedRedeposite);
		updatedDetentionRedeposite.setStatus("Redeposited");
		detentionRepository.save(updatedDetentionRedeposite);

		Detention_History gh = new Detention_History();

		gh.setCompanyId(updatedDetentionRedeposite.getCompanyId());
		gh.setBranchId(updatedDetentionRedeposite.getBranchId());
		gh.setSiNo(updatedDetentionRedeposite.getSiNo());
		gh.setOfficerName(updatedDetentionRedeposite.getRedepositeOfficerName());
		gh.setFileNo(updatedDetentionRedeposite.getFileNo());
		gh.setDate(updatedDetentionRedeposite.getRedepositeDate());
		gh.setStatus(updatedDetentionRedeposite.getStatus());
		gh.setRemarks(updatedDetentionRedeposite.getRedepositeRemarks());

		detentionHistoryRepository.save(gh);
		return new ResponseEntity<>(updatedDetentionRedeposite, HttpStatus.OK);
	}

	@PutMapping("/update/{detentionId}")
	public ResponseEntity<Detention> updatePartyById(@PathVariable String detentionId,
			@RequestBody Detention updatedDetention) {
		Detention updatedDetentionResult = detentionService.update(detentionId, updatedDetention);
		updatedDetentionResult.setEditedDate(new Date());
		detentionRepository.save(updatedDetentionResult);

		return new ResponseEntity<>(updatedDetentionResult, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{detentionId}")
	@Transactional
	public ResponseEntity<Void> deletePartyById(@PathVariable String detentionId) {
		Detention d = detentionService.getDetentionById(detentionId);
		if (detentionId != null) {
			d.setStatus("D");
			detentionService.update(detentionId, d);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	
	@GetMapping(value = "/findCompanyname/{cid}")
	public String findCompanyname(@PathVariable("cid") String param) {
	Company company= companyRepo.findByCompany_Id(param);
		
		return company.getCompany_name();
	}

	@GetMapping(value = "/findBranchName/{cid}/{bid}")
	public String findBranchName(@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
	Branch branch =branchRepo.findByBranchIdWithCompanyId(cid, bid);
		return branch.getBranchName();
	}
	
	@GetMapping("/findPartyName/{companyId}/{branchId}/{partyId}")
	public String findPartyNameByKeys(@PathVariable String companyId, @PathVariable String branchId,
			@PathVariable String partyId) {
		String partyName = partyRepository.findPartyNameByKeys(companyId, branchId, partyId);

		if (partyName != null) {
			return partyName;
		} else {
			// Handle the case where partyName is not found
			return "Party Name Not Found";
		}
	}
	
	
	
//	@PostMapping("/updateIssue")
//	public Detention updateDetention(@RequestParam Date issueDate, @RequestParam String issueOfficerName,
//			@RequestParam String issueOfficerDesignation, @RequestParam String issueDgdcOfficerName,
//			@RequestParam String issueDgdcOfficerDesignation, @RequestParam String issueNop,
//			@RequestParam String issueType, @RequestParam String issueReason, @RequestParam String issueRemarks,
//			@RequestParam String companyId, @RequestParam String branchId, @RequestParam String sirNo,
//			@RequestParam String fileNo,
//			@RequestParam String detentionId) {
//		
//		
//		
//		return detentionService.updateDetention(issueDate, issueOfficerName, issueOfficerDesignation,
//				issueDgdcOfficerName, issueDgdcOfficerDesignation, issueNop, issueType, issueReason, issueRemarks,
//				companyId, branchId, sirNo, fileNo,detentionId);
//	}
}
