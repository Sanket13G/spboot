package com.cwms.controller;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.cwms.entities.CfsTarrif;
import com.cwms.entities.DefaultPartyDetails;
import com.cwms.entities.Export;
import com.cwms.entities.ExportHeavyPackage;
import com.cwms.entities.ExportSub;
import com.cwms.entities.ExternalParty;
import com.cwms.entities.Import;
import com.cwms.entities.ImportHeavyPackage;
import com.cwms.entities.ImportPC;
import com.cwms.entities.ImportSub;
import com.cwms.entities.Import_History;
import com.cwms.entities.Party;
import com.cwms.entities.RepresentParty;
import com.cwms.helper.CombinedImportExport;
import com.cwms.helper.FileUploadProperties;
import com.cwms.invoice.ServiceIdMappingRepositary;
import com.cwms.repository.ExportHeavyPackageRepo;
import com.cwms.repository.ExportRepository;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.ImportRepo;
import com.cwms.repository.DefaultParyDetailsRepository;
import com.cwms.repository.ImportSubRepository;
import com.cwms.service.CFSService;
import com.cwms.service.CFSTariffRangeService;
import com.cwms.service.ExportService;
import com.cwms.service.ExternalParty_Service;
import com.cwms.service.HolidayService;
import com.cwms.service.ImportHeavyService;
import com.cwms.service.ImportPCService;
import com.cwms.service.ImportService;
import com.cwms.service.Import_HistoryService;
import com.cwms.service.PartyService;
import com.cwms.service.ProcessNextIdService;
import com.cwms.service.RepsentativeService;
import com.cwms.service.cfsTarrifServiceService;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/importmain")
@CrossOrigin("*")

public class ImportMainController {
	
	@Autowired
	public DefaultParyDetailsRepository DefaultParyDetailsRepository;
	
	
	@Autowired
	public ProcessNextIdService proccessNextIdService;
	
	@Autowired
	private Import_HistoryService historyService;
	@Autowired
	public FileUploadProperties FileUploadProperties;

	@Autowired
	private RepsentativeService RepsentativeService;

	@Autowired
	private ExternalParty_Service ExternalParty_Service;

	@Autowired
	private PartyService PartyService;

	@Autowired
	public ImportService importService;

	@Autowired
	public ImportPCService ImportPCService;

	@Autowired
	public ImportRepo importRepo;

	@Autowired
	public CFSService CFSService;

	@Autowired
	public CFSTariffRangeService CFSTariffRangeService;

	@Autowired
	private HolidayService holidayService;

	@Autowired
	private ServiceIdMappingRepositary ServiceIdMappingRepositary;

	@Autowired
	public cfsTarrifServiceService cfsTarrifServiceService;
	
	@Autowired
	public ExportService ExportService;
	
	@Autowired
	public ImportHeavyService ImportHeavyService;
	
	@Autowired
	public ExportHeavyPackageRepo ExportHeavyPackageRepo;
	
	@Autowired
	private ExportRepository exportrepo;

	
	@Autowired
	public ImportSubRepository impsubRepo;
	
	@Autowired
	public ExportSubRepository expsubRepo;
	
	
//	SearchDetention
	@GetMapping("/{compId}/{branchId}/{DetentionReceiptNo}/SearchDetention")
	public boolean findByDetentionreceiptNo(@PathVariable("compId") String compid, @PathVariable("branchId") String branchId, @PathVariable("DetentionReceiptNo") String DetentionReceiptNo)
	{		
		return importService.existdetentionNumber(compid, branchId, DetentionReceiptNo);
	}
	
//	Add Personal Carriage

	@PostMapping("/{compid}/{branchId}/{user}/addPersonal")
	public Import addPersonalImport(@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@RequestBody Import import2, @PathVariable("user") String User) {
		import2.setCompanyId(compid);
		import2.setBranchId(branchId);
		import2.setMawb(import2.getDetentionReceiptNo());
		import2.setHawb(import2.getDetentionReceiptNo());
		import2.setDGDC_Status("Handed over to DGDC SEEPZ");
		import2.setCloseStatus("Y");
		import2.setHoldStatus("N");
		import2.setPcStatus("Y");
		import2.setScStatus("N");
		import2.setHpStatus("N");
		import2.setNiptStatus("N");
		import2.setParcelType("PCP");
		import2.setNoc(0);
		import2.setDgdc_cargo_in_scan(0);
		import2.setDgdc_cargo_out_scan(0);
		import2.setDgdc_seepz_in_scan(0);
		import2.setDgdc_seepz_out_scan(0);
		import2.setCancelStatus("N");
		import2.setForwardedStatus("N");
		String autoIncrementIMPTransId = proccessNextIdService.autoIncrementIMPTransId();
		import2.setImpTransId(autoIncrementIMPTransId);
		String autoIncrementSIRId = proccessNextIdService.autoIncrementSIRId();
		import2.setSirNo(autoIncrementSIRId);
		import2.setSirDate(new Date());
		import2.setImpTransDate(new Date());
		import2.setCreatedBy(User);
		import2.setCreatedDate(new Date());
		import2.setStatus("A");
		import2.setApprovedBy(User);
		import2.setApprovedDate(new Date());
		import2.setEditedBy(User);
		import2.setEditedDate(new Date());

		Import_History history = new Import_History();
		history.setCompanyId(compid);
		history.setBranchId(branchId);
		history.setSirNo(autoIncrementSIRId);
		history.setMawb(import2.getDetentionReceiptNo());
		history.setHawb(import2.getDetentionReceiptNo());
		history.setTransport_Date(new Date());
		history.setOldStatus("Pending");
		history.setNewStatus("Handed over to DGDC SEEPZ");
		history.setUpdatedBy(User);
		historyService.addHistory(history);
		return importService.addImport(import2);
	}
	
	
	
	
	
	
//	Single Party / CHA Updated 
	
	@PutMapping("/{compid}/{branchId}/{user}/{otp}/{userId}/{ReprentativeId}/PartyOrCHAupdateSingle")
    public ResponseEntity<?> updateImportPartOrChaSingle(@PathVariable("compid") String compid,
            @PathVariable("branchId") String branchId, @RequestBody Import import1,
            @PathVariable("user") String user, @PathVariable("otp") String OTP, @PathVariable("userId") String userId,
            @PathVariable("ReprentativeId") String ReprentativeId) {

        try {
            // Retrieve data from services or repositories
            RepresentParty Representative = RepsentativeService.findByRepresentativeId(compid, branchId, userId, ReprentativeId);
//            ExternalParty singleRecord = ExternalParty_Service.getSingleRecord(compid, branchId, userId);

//            char firstLetter = userId.charAt(0);

            
            
            
            // Check if the OTP matches
            if (Representative.getOtp().equals(OTP)) {
                // Loop through importList and update each import
               
                    Import_History history = new Import_History();
                    history.setCompanyId(compid);
                    history.setBranchId(branchId);
                    history.setSirNo(import1.getSirNo());
                    history.setMawb(import1.getMawb());
                    history.setHawb(import1.getHawb());
                    history.setTransport_Date(new Date());
                    history.setOldStatus("Handed over to DGDC SEEPZ");
                    history.setNewStatus("Handed Over to Party/CHA");
                    history.setUpdatedBy(user);
                    historyService.addHistory(history);

                    import1.setDGDC_Status("Handed over to Party/CHA");
//                    import1.setNSDL_Status("Out Of Charge");
                    import1.setOutDate(new Date());
                    import1.setHandedOverPartyId(userId);
                    import1.setHandedOverRepresentativeId(ReprentativeId);
                    import1.setHandedOverToType(
                            userId.charAt(0) == 'E' ? "C" :
                            userId.charAt(0) == 'M' ? "P" :
                            // Add more conditions as needed
                            "d"
                        );	               

                // Perform your import updates and return a success response
                Import updatedImports = importService.updateImport(import1);
                return ResponseEntity.ok(updatedImports);
            } else {
                // Return an unauthorized response if the OTP doesn't match
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
        } catch (Exception e) {
            // Handle any unexpected errors here without showing specific error messages.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
	
	
	
	
	
	
	

	@GetMapping("/{compId}/{branchId}/{servicename}/ss")
	public String getASe(@PathVariable("compId") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("servicename") String servicename) {
//			System.out.println(ServiceIdMappingRepositary.findServieIdByKeys(compid, branchId, servicename));
		return ServiceIdMappingRepositary.findServieIdByKeys(compid, branchId, servicename);
	}

	@GetMapping("/{compid}/{branchId}/{tranId}/{MAWB}/{HAWB}/{sirNo}/getSingle")
	public Import findByMAWBANDHAWB(@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("tranId") String transId, @PathVariable("sirNo") String sirNo) {

		return importService.getByMAWBANdHAWB(compid, branchId, transId, MAWB, HAWB, sirNo);
	}

	@GetMapping("/{cid}/{bid}/{MAWBNo}")
	public List<Import> getByMawbNo(@PathVariable("MAWBNo") String MAWBNo, @PathVariable("cid") String cid,
			@PathVariable("bid") String bid) {
		return importService.getByMAWB(cid, bid, MAWBNo);
	}

	@GetMapping("/{cid}/{bid}/All")
	public List<Import> getAll(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		return importService.getAll(cid, bid);
	}

	@PostMapping("/{compid}/{branchId}/{user}/add")
	public ResponseEntity<?> addImport(@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@RequestBody Import import2, @PathVariable("user") String User) {
		
		 if (import2.getHawb() != null && !import2.getHawb().trim().isEmpty()) {
		        boolean duplicate = importService.getByMAWBAndHawbDuplicate(compid, branchId, import2.getMawb(), import2.getHawb());

		        if (duplicate) {
		            // Return an error response to React
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                    .body("Duplicate Mawb and Hawb combination");
		        }
		    }	
		
		import2.setCompanyId(compid);
		import2.setBranchId(branchId);

		
		
		import2.setDGDC_Status("Handed over to DGDC Cargo");
		import2.setCloseStatus("N");
		import2.setHoldStatus("N");
		import2.setPcStatus("N");
		import2.setScStatus("N");
		import2.setHpStatus("N");
		import2.setNiptStatus("N");
		import2.setForwardedStatus("N");
		import2.setNoc(0);
		import2.setDgdc_cargo_in_scan(0);
		import2.setDgdc_cargo_out_scan(0);
		import2.setDgdc_seepz_in_scan(0);
		import2.setDgdc_seepz_out_scan(0);
		import2.setCancelStatus("N");

		String autoIncrementIMPTransId = proccessNextIdService.autoIncrementIMPTransId();
		import2.setImpTransId(autoIncrementIMPTransId);
		String autoIncrementSIRId = proccessNextIdService.autoIncrementSIRId();
		import2.setSirNo(autoIncrementSIRId);
		
		if (import2.getHawb() == null || import2.getHawb().isEmpty()) {
		    // Handle the case where getHawb() is null or empty
			
			import2.setHawb("000"+autoIncrementSIRId);
		}
		import2.setSirDate(new Date());
		import2.setImpTransDate(new Date());
		import2.setCreatedBy(User);
		import2.setCreatedDate(new Date());
		import2.setStatus("A");
		import2.setApprovedBy(User);
		import2.setApprovedDate(new Date());
		import2.setEditedBy(User);
		import2.setEditedDate(new Date());

		Import_History history = new Import_History();
		history.setCompanyId(compid);
		history.setBranchId(branchId);
		history.setSirNo(autoIncrementSIRId);
		history.setMawb(import2.getMawb());
		history.setHawb(import2.getHawb());
		history.setTransport_Date(new Date());
		history.setOldStatus("Pending");
		history.setNewStatus("Handed over to DGDC Cargo");
		history.setUpdatedBy(User);
		historyService.addHistory(history);
		
		 Import savedImport = importService.addImport(import2);

		    // Return the saved import object to React
		    return ResponseEntity.ok(savedImport);
		 
		 
	}


	@PutMapping("/{compid}/{branchId}/{user}/update")
	public List<Import> updateImport(@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@RequestBody Import import2, @PathVariable("user") String User) {
		List<Import> ImportList = importService.getByMAWB(compid, branchId, import2.getMawb());

		for (Import imp : ImportList) {
			imp.setCloseStatus("Y");
			imp.setEditedBy(User);
			imp.setEditedDate(new Date());
		}

		return importService.updateAll(ImportList);
	}

	@PutMapping("/{compid}/{branchId}/{user}/modifyupdate")
	public Import updateImportByIMpTransId(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId, @RequestBody Import import2, @PathVariable("user") String User) {
//		import2.setBranchId(branchId);

		Import existingImport = importService.findBytransIdAndSirNo(compid, branchId, import2.getImpTransId(),
				import2.getSirNo());

		if (existingImport != null) {

			importService.deleteImport(existingImport);
			Import newImport = new Import();

			newImport.setEditedBy(User);
			newImport.setEditedDate(new Date());
			newImport.setAirlineName(import2.getAirlineName());
			newImport.setCreatedBy(import2.getCreatedBy());
			newImport.setCreatedDate(import2.getCreatedDate());
			newImport.setApprovedBy(import2.getApprovedBy());
			newImport.setApprovedDate(import2.getApprovedDate());
			newImport.setCompanyId(import2.getCompanyId());
			newImport.setBranchId(import2.getBranchId());
			newImport.setMawb(import2.getMawb());
			newImport.setHawb(import2.getHawb());
			newImport.setSirNo(import2.getSirNo());
			newImport.setNop(import2.getNop());
			newImport.setImporterId(import2.getImporterId());
			newImport.setConsoleName(import2.getConsoleName());
			newImport.setImportRemarks(import2.getImportRemarks());
			newImport.setBeDate(import2.getBeDate());
			newImport.setSirDate(import2.getSirDate());
			newImport.setImpTransId(import2.getImpTransId());
			newImport.setImpTransDate(import2.getImpTransDate());
			newImport.setBeDate(import2.getBeDate());
			newImport.setCloseStatus(import2.getCloseStatus());
			newImport.setIec(import2.getIec());
			newImport.setBeNo(import2.getBeNo());
			newImport.setBeRequestId(import2.getBeRequestId());
			newImport.setIgmNo(import2.getIgmNo());
			newImport.setIgmDate(import2.getIgmDate());
			newImport.setPctmNo(import2.getPctmNo());
			newImport.setPackageContentType(import2.getPackageContentType());
			newImport.setUomPackages(import2.getUomPackages());
			newImport.setUomWeight(import2.getUomWeight());
			newImport.setTpNo(import2.getTpNo());
			newImport.setTpDate(import2.getTpDate());
			newImport.setFlightNo(import2.getFlightNo());
			newImport.setFlightDate(import2.getFlightDate());
			newImport.setCountryOrigin(import2.getCountryOrigin());
			newImport.setPortOrigin(import2.getPortOrigin());
			newImport.setNSDL_Status(import2.getNSDL_Status());
			newImport.setDGDC_Status(import2.getDGDC_Status());
			newImport.setDescriptionOfGoods(import2.getDescriptionOfGoods());
			newImport.setImportAddress(import2.getImportAddress());
			newImport.setChaCde(import2.getChaCde());
			newImport.setAssessableValue(import2.getAssessableValue());
			newImport.setGrossWeight(import2.getGrossWeight());
			newImport.setStatus(import2.getStatus());
			newImport.setCartingAgent(import2.getCartingAgent());
			newImport.setPartyRepresentativeId(import2.getPartyRepresentativeId());
			newImport.setCancelStatus(import2.getCancelStatus());
			newImport.setCancelRemarks(import2.getCancelRemarks());
			newImport.setHoldBy(import2.getHoldBy());
			newImport.setHoldDate(import2.getHoldDate());
			newImport.setHoldStatus(import2.getHoldStatus());
			newImport.setReasonforOverride(import2.getReasonforOverride());
			newImport.setNsdlStatusDocs(import2.getNsdlStatusDocs());
			newImport.setHppackageno(import2.getHppackageno());
			newImport.setImposePenaltyAmount(import2.getImposePenaltyAmount());
			newImport.setImposePenaltyRemarks(import2.getImposePenaltyRemarks());
			newImport.setPcStatus(import2.getPcStatus());
			newImport.setScStatus(import2.getScStatus());
			newImport.setHpStatus(import2.getHpStatus());
			newImport.setHpWeight(import2.getHpWeight());
			newImport.setCancelRemarks(import2.getCancelRemarks());
			newImport.setHandedOverPartyId(import2.getHandedOverPartyId());
			newImport.setHandedOverRepresentativeId(import2.getHandedOverRepresentativeId());
			newImport.setHandedOverToType(import2.getHandedOverToType());
			newImport.setNiptStatus(import2.getNiptStatus());
			newImport.setQrcodeUrl(import2.getQrcodeUrl());
			newImport.setImporternameOnParcel(import2.getImporternameOnParcel());
			newImport.setDoNumber(import2.getDoNumber());
			newImport.setDoDate(import2.getDoDate());
			newImport.setChaName(import2.getChaName());
			newImport.setAirlineCode(import2.getAirlineCode());
			newImport.setNoc(import2.getNoc());
			newImport.setDgdc_cargo_in_scan(import2.getDgdc_cargo_in_scan());
			newImport.setDgdc_cargo_out_scan(import2.getDgdc_cargo_out_scan());
			newImport.setDgdc_seepz_in_scan(import2.getDgdc_seepz_in_scan());
			newImport.setDgdc_seepz_out_scan(import2.getDgdc_seepz_out_scan());
			newImport.setOutDate(import2.getOutDate());
			newImport.setNiptCustomOfficerName(import2.getNiptCustomOfficerName());
			newImport.setNiptCustomsOfficerDesignation(import2.getNiptCustomsOfficerDesignation());
			newImport.setNiptDeputedFromDestination(import2.getNiptDeputedFromDestination());
			newImport.setNiptDeputedToDestination(import2.getNiptDeputedToDestination());
			newImport.setNiptDateOfEscort(import2.getNiptDateOfEscort());
			newImport.setNiptApproverName(import2.getNiptApproverName());
			newImport.setNiptApproverDesignation(import2.getNiptApproverDesignation());
			newImport.setNiptApproverDate(import2.getNiptApproverDate());		
			newImport.setWrongDepositFilePath(import2.getWrongDepositFilePath());
			newImport.setWrongDepositwrongDepositRemarks(import2.getWrongDepositwrongDepositRemarks());
			newImport.setWrongDepositStatus(import2.getWrongDepositStatus());
			newImport.setDetentionReceiptNo(import2.getDetentionReceiptNo());
			return importService.updateImport(newImport);
		}

		return null;
	}

	@GetMapping("/{compid}/{branchId}/carting")
	public List<Import> findImportsByStatus(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId)

	{
		return importService.findByCompanyIdAndBranchIdAndDgdcStatus(compid, branchId);
	}

	@DeleteMapping("/{compid}/{branchId}/{tranId}/{MAWB}/{HAWB}/{sirNo}/delete")
	public void deleteImport(@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("tranId") String transId, @PathVariable("sirNo") String sirNo) {
		Import byMAWBANdHAWB = importService.getByMAWBANdHAWB(compid, branchId, transId, MAWB, HAWB, sirNo);
		if (byMAWBANdHAWB != null) {
			byMAWBANdHAWB.setStatus("D");
			importService.updateImport(byMAWBANdHAWB);
		}
	}

	@PutMapping("/{compid}/{branchId}/{user}/{otp}/{userId}/{ReprentativeId}/{tp}/CartingAgentupdate")
	public List<Import> updateImportCartingAgent(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId, @RequestBody List<Import> importList,
			@PathVariable("user") String user, @PathVariable("otp") String OTP, @PathVariable("userId") String userId,@PathVariable("tp") String tp,
			@PathVariable("ReprentativeId") String ReprentativeId) {

		RepresentParty Representative = RepsentativeService.findByRepresentativeId(compid, branchId, userId,
				ReprentativeId);
		String replacedString = tp.replace("@", "/");
	

		 String tpNo = null;

         if ("N".equals(replacedString)) {
        	 tpNo = proccessNextIdService.generateAndIncrementTPumber();
         } else {
        	 tpNo = replacedString;
         }
		
		
		if (Representative.getOtp().equals(OTP)) {

			for (Import existingImport : importList) {

				Import_History history = new Import_History();
				history.setCompanyId(compid);
				history.setBranchId(branchId);
				history.setSirNo(existingImport.getSirNo());
				history.setMawb(existingImport.getMawb());
				history.setHawb(existingImport.getHawb());
				history.setTransport_Date(new Date());
				history.setOldStatus("Handed over to DGDC Cargo");
				history.setNewStatus("Handed over to Carting Agent");
				history.setUpdatedBy(user);
				historyService.addHistory(history);

				existingImport.setCartingAgent(userId);
				existingImport.setPartyRepresentativeId(ReprentativeId);
				existingImport.setDGDC_Status("Handed over to Carting Agent");
				existingImport.setTpNo(tpNo);
				existingImport.setTpDate(new Date());
			}
			Representative.setOtp("");
			RepsentativeService.addrepresentative(Representative);
			return importService.updateAll(importList);
		} else {
			return null;
		}
	}

//	Party or Cha Update

	@PutMapping("/{compid}/{branchId}/{user}/{otp}/{userId}/{ReprentativeId}/PartyOrCHAupdate")
    public ResponseEntity<Object> updateImportPartOrCha(@PathVariable("compid") String compid,
            @PathVariable("branchId") String branchId, @RequestBody List<Import> importList,
            @PathVariable("user") String user, @PathVariable("otp") String OTP, @PathVariable("userId") String userId,
            @PathVariable("ReprentativeId") String ReprentativeId) {

        try {
            // Retrieve data from services or repositories
            RepresentParty Representative = RepsentativeService.findByRepresentativeId(compid, branchId, userId, ReprentativeId);
       //     ExternalParty singleRecord = ExternalParty_Service.getSingleRecord(compid, branchId, userId);

         //   char firstLetter = singleRecord.getUserType().charAt(0);

            
            
            
            // Check if the OTP matches
            if (Representative.getOtp().equals(OTP)) {
                // Loop through importList and update each import
                for (Import existingImport : importList) {
                    Import_History history = new Import_History();
                    history.setCompanyId(compid);
                    history.setBranchId(branchId);
                    history.setSirNo(existingImport.getSirNo());
                    history.setMawb(existingImport.getMawb());
                    history.setHawb(existingImport.getHawb());
                    history.setTransport_Date(new Date());
                    history.setOldStatus("Handed over to DGDC SEEPZ");
                    history.setNewStatus("Handed Over to Party/CHA");
                    history.setUpdatedBy(user);
                    historyService.addHistory(history);

                    existingImport.setDGDC_Status("Handed over to Party/CHA");
                    existingImport.setNSDL_Status("Out Of Charge");
                    existingImport.setOutDate(new Date());
                    existingImport.setHandedOverPartyId(userId);
                    existingImport.setHandedOverRepresentativeId(ReprentativeId);
                    existingImport.setHandedOverToType(
                            userId.charAt(0) == 'E' ? "C" :
                            userId.charAt(0) == 'M' ? "P" :
                            // Add more conditions as needed
                            "d"
                        );
                }

                // Perform your import updates and return a success response
                List<Import> updatedImports = importService.updateAll(importList);
                return ResponseEntity.ok(updatedImports);
            } else {
                // Return an unauthorized response if the OTP doesn't match
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
        } catch (Exception e) {
            // Handle any unexpected errors here without showing specific error messages.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

	@PutMapping("/{compid}/{branchId}/{tranId}/{MAWB}/{HAWB}/{sirNo}/{user}/{condition}/ChangeStatus")
	public Import updateSingleImport(@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("tranId") String transId, @PathVariable("sirNo") String sirNo,
			@PathVariable("condition") String condition, @PathVariable("user") String user,
			@RequestBody Import import2) {
		Import byMAWBANdHAWB = importService.getByMAWBANdHAWB(compid, branchId, transId, MAWB, HAWB, sirNo);
		if (byMAWBANdHAWB != null) {
			switch (condition) {
			case "cancel":

				if ("Y".equals(import2.getCancelStatus())) {
					// Only update the cancel remarks if the cancel status is "Y"
					byMAWBANdHAWB.setCancelRemarks(import2.getCancelRemarks());
				} else {
					// If cancel status is not "Y", perform other operations
					Import_History history = new Import_History();
					history.setCompanyId(compid);
					history.setBranchId(branchId);
					history.setSirNo(byMAWBANdHAWB.getSirNo());
					history.setMawb(byMAWBANdHAWB.getMawb());
					history.setHawb(byMAWBANdHAWB.getHawb());
					history.setTransport_Date(new Date());
					history.setOldStatus(byMAWBANdHAWB.getDGDC_Status());
					history.setNewStatus("Cancelled");
					history.setUpdatedBy(user);
					historyService.addHistory(history);

					byMAWBANdHAWB.setCancelStatus("Y");
					byMAWBANdHAWB.setDGDC_Status("Cancelled");
					byMAWBANdHAWB.setCancelRemarks(import2.getCancelRemarks());
				}

				break;

			case "Uncancel":
				Import_History history2 = new Import_History();
				history2.setCompanyId(compid);
				history2.setBranchId(branchId);
				history2.setSirNo(byMAWBANdHAWB.getSirNo());
				history2.setMawb(byMAWBANdHAWB.getMawb());
				history2.setHawb(byMAWBANdHAWB.getHawb());
				history2.setTransport_Date(new Date());
				history2.setOldStatus(byMAWBANdHAWB.getDGDC_Status());
				history2.setNewStatus("Handed over to DGDC Cargo");
				history2.setUpdatedBy(user);
				historyService.addHistory(history2);

				byMAWBANdHAWB.setCancelStatus("N");
				byMAWBANdHAWB.setDGDC_Status("Handed over to DGDC Cargo");
				byMAWBANdHAWB.setCancelRemarks("");
				break;

			case "hold":
				byMAWBANdHAWB.setHoldStatus("Y");
				byMAWBANdHAWB.setHoldDate(new Date());
				byMAWBANdHAWB.setHoldBy(user);
				break;

			case "unhold":
				byMAWBANdHAWB.setHoldStatus("R");
				byMAWBANdHAWB.setHoldDate(null);
				byMAWBANdHAWB.setHoldBy("");
				break;

			case "personal-carriage":
				byMAWBANdHAWB.setPcStatus("Y");
				break;

			case "unpersonal-carriage":
				byMAWBANdHAWB.setPcStatus("N");
				ImportPC byIDS = ImportPCService.getByIDS(compid, branchId, MAWB, HAWB, sirNo);
				if (byIDS != null) {
					ImportPCService.deleteImportPc(compid, branchId, MAWB, HAWB, sirNo);
				}

				break;

			case "special-carting":
				byMAWBANdHAWB.setScStatus("Y");
				break;

			case "unspecial-carting":
				byMAWBANdHAWB.setScStatus("N");
				break;

			case "heavy":
				byMAWBANdHAWB.setHpStatus("Y");
				byMAWBANdHAWB.setHpWeight(import2.getHpWeight());
				byMAWBANdHAWB.setHppackageno(import2.getHppackageno());
				break;

			case "Unheavy":
				byMAWBANdHAWB.setHpStatus("N");
				byMAWBANdHAWB.setHpWeight(null);
				byMAWBANdHAWB.setHppackageno("");
				break;

			case "impose-Penalty":
				byMAWBANdHAWB.setImposePenaltyAmount(import2.getImposePenaltyAmount());
				byMAWBANdHAWB.setImposePenaltyRemarks(import2.getImposePenaltyRemarks());
				break;

			case "heavy-Report":
				System.out.println("Heavy Report");
				break;
				
			case "NIPT":
				byMAWBANdHAWB.setNiptCustomOfficerName(import2.getNiptCustomOfficerName());
				byMAWBANdHAWB.setNiptCustomsOfficerDesignation(import2.getNiptCustomsOfficerDesignation());
				byMAWBANdHAWB.setNiptDeputedFromDestination(import2.getNiptDeputedFromDestination());
				byMAWBANdHAWB.setNiptDeputedToDestination(import2.getNiptDeputedToDestination());
				byMAWBANdHAWB.setNiptDateOfEscort(import2.getNiptDateOfEscort());
				byMAWBANdHAWB.setNiptApproverName(import2.getNiptApproverName());
				byMAWBANdHAWB.setNiptApproverDesignation(import2.getNiptApproverDesignation());
				byMAWBANdHAWB.setNiptApproverDate(import2.getNiptApproverDate());		
				break;

			default:
				// Handle unknown condition
				break;
			}

			importService.updateImport(byMAWBANdHAWB);
		}

		return null;
	}

	@PutMapping("/{compid}/{branchId}/{user}/{otp}/{userID}/{ReprentativeId}/{tp}/SingleCartingAgent")
	public Import updateSingleImportCartingAgent(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId, @RequestBody Import existingImport,
			@PathVariable("user") String user, @PathVariable("otp") String OTP, @PathVariable("userID") String userID,
			@PathVariable("ReprentativeId") String ReprentativeId,@PathVariable("tp") String tpdata) {
		System.out.println(compid + "" + branchId + "" + userID + "" + ReprentativeId);

		RepresentParty Representative = RepsentativeService.findByRepresentativeId(compid, branchId, userID,
				ReprentativeId);
		 String replacedString = tpdata.replace("@", "/");
	
		
		
	    String tpNo = null;

        if ("N".equals(replacedString)) {
        	tpNo = proccessNextIdService.generateAndIncrementTPumber();
        } else {
        	tpNo = replacedString;
        }
        
		if (Representative.getOtp().equals(OTP)) {

			Import_History history = new Import_History();
			history.setCompanyId(compid);
			history.setBranchId(branchId);
			history.setSirNo(existingImport.getSirNo());
			history.setMawb(existingImport.getMawb());
			history.setHawb(existingImport.getHawb());
			history.setTransport_Date(new Date());
			history.setOldStatus("Handed over to DGDC Cargo");
			history.setNewStatus("Handed over to Carting Agent");
			history.setUpdatedBy(user);
			historyService.addHistory(history);

			existingImport.setCartingAgent(userID);
			existingImport.setPartyRepresentativeId(ReprentativeId);
			existingImport.setDGDC_Status("Handed over to Carting Agent");
			existingImport.setTpNo(tpNo);
			existingImport.setTpDate(new Date());
			return importService.updateImport(existingImport);
		} else {
			return null;
		}
	}
	@GetMapping("/search")
	public List<Import> searchImports(@RequestParam(name = "pcStatus", required = false) String pcStatus,
			@RequestParam(name = "scStatus", required = false) String scStatus,
			@RequestParam(name = "searchValue", required = false) String searchValue,
			@RequestParam(name = "companyid", required = false) String companyid,
			@RequestParam(name = "branchId", required = false) String branchId,
			@RequestParam(name = "holdStatus", required = false) String holdStatus,
			@RequestParam(name = "hpStatus", required = false) String hpStatus,
			@RequestParam(name = "dgdcStatus", required = false) String dgdcStatus,
			@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

		
//		System.out.println("searchValue "+searchValue);
		
		
		return importRepo.findByAttributes(companyid, branchId, pcStatus, scStatus, hpStatus, holdStatus, dgdcStatus,
				startDate, endDate,searchValue);
	}

	@PostMapping("/override")
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("sirNo") String sirNo, @RequestParam("reasonforOverride") String reasonforOverride,
			@RequestParam("newnsdlStatus") String nsdlStatus, @RequestParam("mawb") String mawb,
			@RequestParam("hawb") String hawb, @RequestParam("companyid") String companyid,
			@RequestParam("branchId") String branchId, @RequestParam("transId") String transId)
			throws java.io.IOException {
		try {
			Import byMAWBANdHAWB = importService.getByMAWBANdHAWB(companyid, branchId, transId, mawb, hawb, sirNo);

			if (byMAWBANdHAWB != null) {
				// Get the original file name
				String originalFileName = file.getOriginalFilename();

				// Generate a unique file name to avoid duplicates
				String uniqueFileName = generateUniqueFileName(originalFileName);

				// Set the unique file name in the database
				byMAWBANdHAWB.setNsdlStatusDocs(FileUploadProperties.getPath() + uniqueFileName);
				byMAWBANdHAWB.setNSDL_Status(nsdlStatus);
				// Save the file to your local system with the unique name
				Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName));

				// Set other fields in the Import object
				byMAWBANdHAWB.setReasonforOverride(reasonforOverride);
				byMAWBANdHAWB.setNSDL_Status(nsdlStatus);

				// Update the Import object in the database
				Import updateImport = importService.updateImport(byMAWBANdHAWB);
				return ResponseEntity.ok(updateImport);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
		}
	}

	// Helper method to generate a unique file name
	private String generateUniqueFileName(String originalFileName) {
		String uniqueFileName = originalFileName;
		int suffix = 1;

		// Check if the file with the same name already exists
		while (Files.exists(Paths.get(FileUploadProperties.getPath() + uniqueFileName))) {
			int dotIndex = originalFileName.lastIndexOf('.');
			String nameWithoutExtension = dotIndex != -1 ? originalFileName.substring(0, dotIndex) : originalFileName;
			String fileExtension = dotIndex != -1 ? originalFileName.substring(dotIndex) : "";
			uniqueFileName = nameWithoutExtension + "_" + suffix + fileExtension;
			suffix++;
		}

		return uniqueFileName;
	}


	@GetMapping("/{compid}/{branchId}/{tranId}/{MAWB}/{HAWB}/{sirNo}/getImage")
	public ResponseEntity<?> getImageOrPdf(@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("tranId") String transId, @PathVariable("sirNo") String sirNo) throws IOException {

		Import importObject = importService.getByMAWBANdHAWB(compid, branchId, transId, MAWB, HAWB, sirNo);

		if (importObject != null) {
			String nsdlStatusDocsPath = importObject.getNsdlStatusDocs();
			Path filePath = Paths.get(nsdlStatusDocsPath);

			// Check if the file exists
			if (Files.exists(filePath)) {
				try {
					String fileExtension = getFileExtension(nsdlStatusDocsPath);

					if (isImageFile(fileExtension)) {
						// If it's an image file, return a data URL
						byte[] imageBytes = Files.readAllBytes(filePath);
						String base64Image = Base64.getEncoder().encodeToString(imageBytes);
						String dataURL = "data:image/jpeg;base64," + base64Image;

						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.TEXT_PLAIN); // Set the content type to text/plain

						return new ResponseEntity<>(dataURL, headers, HttpStatus.OK);
					} else if (isPdfFile(fileExtension)) {
					    // If it's a PDF file, return the PDF data as base64
					    byte[] pdfBytes = Files.readAllBytes(filePath);
					    String pdfBase64 = Base64.getEncoder().encodeToString(pdfBytes);

					    HttpHeaders headers = new HttpHeaders();
					    headers.setContentType(MediaType.APPLICATION_PDF); // Set the content type to application/pdf

					    return new ResponseEntity<>(pdfBase64, headers, HttpStatus.OK);
					}
				} catch (IOException | java.io.IOException e) {
					// Handle the IOException appropriately (e.g., log it)
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
			}
		}

		return ResponseEntity.notFound().build();
	}

	
	
	
	@GetMapping("/{compid}/{branchId}/{tranId}/{MAWB}/{HAWB}/{sirNo}/getImageWrongDeposit")
	public ResponseEntity<?> getImageOrPdfWrongDeposit(@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("tranId") String transId, @PathVariable("sirNo") String sirNo) throws IOException {

		Import importObject = importService.getByMAWBANdHAWB(compid, branchId, transId, MAWB, HAWB, sirNo);

		if (importObject != null) {
			String nsdlStatusDocsPath = importObject.getWrongDepositFilePath();
			Path filePath = Paths.get(nsdlStatusDocsPath);

			// Check if the file exists
			if (Files.exists(filePath)) {
				try {
					String fileExtension = getFileExtension(nsdlStatusDocsPath);

					if (isImageFile(fileExtension)) {
						// If it's an image file, return a data URL
						byte[] imageBytes = Files.readAllBytes(filePath);
						String base64Image = Base64.getEncoder().encodeToString(imageBytes);
						String dataURL = "data:image/jpeg;base64," + base64Image;

						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.TEXT_PLAIN); // Set the content type to text/plain

						return new ResponseEntity<>(dataURL, headers, HttpStatus.OK);
					} else if (isPdfFile(fileExtension)) {
					    // If it's a PDF file, return the PDF data as base64
					    byte[] pdfBytes = Files.readAllBytes(filePath);
					    String pdfBase64 = Base64.getEncoder().encodeToString(pdfBytes);

					    HttpHeaders headers = new HttpHeaders();
					    headers.setContentType(MediaType.APPLICATION_PDF); // Set the content type to application/pdf

					    return new ResponseEntity<>(pdfBase64, headers, HttpStatus.OK);
					}
				} catch (IOException | java.io.IOException e) {
					// Handle the IOException appropriately (e.g., log it)
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
			}
		}

		return ResponseEntity.notFound().build();
	}

	
	
	
	
	
	
	
	
	
	
	
	private String getFileExtension(String filePath) {
		int dotIndex = filePath.lastIndexOf('.');
		if (dotIndex >= 0 && dotIndex < filePath.length() - 1) {
			return filePath.substring(dotIndex + 1).toLowerCase();
		}
		return "";
	}

	private boolean isImageFile(String fileExtension) {
		return fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png")
				|| fileExtension.equals("gif");
	}

	private boolean isPdfFile(String fileExtension) {
		return fileExtension.equals("pdf");
	}

	@GetMapping("/{compid}/{branchId}/{importerId}/{type}/ForPartyorCha")
	public List<Import> findForPArtyOrCha(@PathVariable("compid") String compid,@PathVariable("type") String type,
			@PathVariable("branchId") String branchId, @PathVariable("importerId") String importerId) {
		return importService.findByCompanyIdAndBranchIdAndImporterIdAndDgdcStatus(compid, branchId, importerId,type);
	}

	@GetMapping("/{compid}/{branchId}/{carting}/{representative}/Receivedcarting")
	public List<Import> findImportsForReceivedCartingagents(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId, @PathVariable("carting") String carting,
			@PathVariable("representative") String representative) {
		return importService.findByImportsForReceivedCratingAgents(compid, branchId, carting, representative);
	}

	@PutMapping("/{compid}/{branchId}/{user}/{otp}/{userId}/{ReprentativeId}/ReceivedFromCarting")
	public List<Import> updateImporReceivedFromtCartingAgent(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId, @RequestBody List<Import> importList,
			@PathVariable("user") String user, @PathVariable("otp") String OTP, @PathVariable("userId") String userId,
			@PathVariable("ReprentativeId") String ReprentativeId) {

		RepresentParty Representative = RepsentativeService.findByRepresentativeId(compid, branchId, userId,
				ReprentativeId);

		if (Representative.getOtp().equals(OTP)) {

			for (Import existingImport : importList) {

				Import_History history = new Import_History();
				history.setCompanyId(compid);
				history.setBranchId(branchId);
				history.setSirNo(existingImport.getSirNo());
				history.setMawb(existingImport.getMawb());
				history.setHawb(existingImport.getHawb());
				history.setTransport_Date(new Date());
				history.setOldStatus("Entry at DGDC SEEPZ Gate");
				history.setNewStatus("Handed over to DGDC SEEPZ");
				history.setUpdatedBy(user);
				historyService.addHistory(history);

				existingImport.setCartingAgent(userId);
				existingImport.setPartyRepresentativeId(ReprentativeId);
				existingImport.setDGDC_Status("Handed over to DGDC SEEPZ");

			}

			return importService.updateAll(importList);
		} else {
			return null;
		}
	}

	
	
//	NIPT
	@GetMapping("/{compid}/{branchId}/{user}/addNIPT")
	public ResponseEntity<?> addNIPTImport(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId, @PathVariable("user") String User,
			@RequestParam("url") String url) {

		try {

			RestTemplate restTemplate = new RestTemplate();
			String htmlContent = restTemplate.getForObject(url, String.class);

			// Parse the HTML string using Jsoup
			Document doc = Jsoup.parse(htmlContent);

			// Extract data from specific HTML elements using CSS selectors
			String dcName = doc.select("#lblDCName").text();
			String sezName = doc.select("#lblSEZName").text();
			String entityName = doc.select("#lblEntityName").text();
			String importExportCode = doc.select("#lblImportExportCode").text();
			String entityID = doc.select("#lblEntityID").text();

			// Extract additional fields as needed
			String requestDetails = doc.select("#lblRequestDetails").text();
			String requestID = doc.select("#lblRequestID").text();
			String portCode = doc.select("#lblBOEPortCode").text();
			String portOfOrigin = doc.select("#lblBOEPortOrgn").text();
			String countryOfOrigin = doc.select("#lblBOECntryOrgn").text();
			String importDeptDetails = doc.select("#lblBEThokaNo").text();
			String chaDetails = doc.select("#lblCHADetails").text();
			String assessmentDate = doc.select("#lblAssesmentDate").text();
			String requestStatus = doc.select("#lblRequestStatus").text();
			String assessableValue = doc.select("#lblAssessableValue").text();

				        
	        // Split the extracted text into chaName and chaCode using the '-' sign
	        String[] parts5 = chaDetails.split(" - ");
	        String chaName = parts5[0];
	        String chaCode = parts5.length > 1 ? parts5[1] : "";
			
			
			Element beElement = doc.select("#lblBEThokaNo").first();
			String beText = beElement.text();

			// Split the extracted text into BeNumber and BeDate
			String[] values = beText.split(", ");
			String beNumber = values[0];
			String beDate = values[1];
			
			// Extract data from the table
			// Extract data from the table
			Element table = doc.select("table#gvConsigneeDetails").first();
			Elements rows = table.select("tr");

			String igmNoDate = "";
			String mawbNoDate = "";
			String hawbNoDate = "";
			String weight = "";
			String packets = "";
			String packageMarksNumbers = "";
			String noOfContainers = "";

			if (rows.size() >= 2) { // Check if there are at least two rows (header row and data row)
				Element dataRow = rows.get(1); // Assuming the data row is the second row (index 1)
				Elements columns = dataRow.select("td");

				if (columns.size() >= 7) { // Check if there are at least seven columns in the data row
					igmNoDate = columns.get(0).text();
					mawbNoDate = columns.get(1).text();
					hawbNoDate = columns.get(2).text();
					weight = columns.get(3).text();
					packets = columns.get(4).text();
					packageMarksNumbers = columns.get(5).text();
					noOfContainers = columns.get(6).text();
				}
			}

			String[] parts = mawbNoDate.split(" ");

			String mawbNo = "";
			String mawbDate = "";

			if (parts.length >= 2) {
				mawbNo = parts[0]; // "3111795912"
				mawbDate = parts[1]; // "22/08/2023"
			}

			String[] parts2 = hawbNoDate.split(" ");

			String HawbNo = "";
			String HawbDate = "";

			if (parts2.length >= 2) {
				HawbNo = parts2[0]; // "3111795912"
				HawbDate = parts2[1]; // "22/08/2023"
			}

			String[] parts3 = igmNoDate.split(" ");

			String igmNo = "";
			String igmDate = "";

			if (parts3.length >= 2) {
				igmNo = parts3[0]; // "3111795912"
				igmDate = parts3[1]; // "22/08/2023"
			}
			// Create a JSON object to store all extracted fields
			String extractedData = "{" + "\"dcName\":\"" + dcName + "\"," + "\"sezName\":\"" + sezName + "\","
					+ "\"entityName\":\"" + entityName + "\"," + "\"importExportCode\":\"" + importExportCode + "\","
					+ "\"entityID\":\"" + entityID + "\"," + "\"requestDetails\":\"" + requestDetails + "\","
					+ "\"requestID\":\"" + requestID + "\"," + "\"portCode\":\"" + portCode + "\","
					+ "\"portOfOrigin\":\"" + portOfOrigin + "\"," + "\"countryOfOrigin\":\"" + countryOfOrigin + "\","
					+ "\"importDeptDetails\":\"" + importDeptDetails + "\"," + "\"chaDetails\":\"" + chaDetails + "\","
					+ "\"assessmentDate\":\"" + assessmentDate + "\"," + "\"requestStatus\":\"" + requestStatus + "\","
					+ "\"assessableValue\":\"" + assessableValue + "\","
//                 + "\"igmNoDate\":\"" + igmNoDate + "\","
//                 + "\"mawbNoDate\":\"" + mawbNoDate + "\","
					+ "\"mawbNo\":\"" + mawbNo + "\"," + "\"mawbDate\":\"" + mawbDate + "\"," + "\"HawbNo\":\"" + HawbNo
					+ "\"," + "\"HawbDate\":\"" + HawbDate + "\","
//                 + "\"hawbNoDate\":\"" + hawbNoDate + "\","
					+ "\"igmNo\":\"" + igmNo + "\"," + "\"igmDate\":\"" + igmDate + "\"," + "\"weight\":\"" + weight
					+ "\"," + "\"packets\":\"" + packets + "\"," + "\"packageMarksNumbers\":\"" + packageMarksNumbers
					+ "\"," + "\"noOfContainers\":\"" + noOfContainers + "\"" + "}";

			Import findByRequestId = importService.findByRequestId(compid, branchId, mawbNo, requestID);

			if (findByRequestId != null) {
				return ResponseEntity.ok("Duplicate Scanning");
			} else {
				Import import2 = new Import();
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				import2.setBeNo(beNumber);
				import2.setBeDate(dateFormat.parse(beDate));
				import2.setCompanyId(compid);
				import2.setBranchId(branchId);
				import2.setChaCde(chaCode);
				import2.setChaName(chaName);
				import2.setCloseStatus("Y");
				import2.setHoldStatus("N");
				import2.setPcStatus("N");
				import2.setScStatus("N");
				import2.setHpStatus("N");
				import2.setNiptStatus("Y");

				import2.setCancelStatus("N");

				String autoIncrementIMPTransId = proccessNextIdService.autoIncrementIMPTransId();
				import2.setImpTransId(autoIncrementIMPTransId);
				String autoIncrementSIRId = proccessNextIdService.autoIncrementSIRId();
				import2.setSirNo(autoIncrementSIRId);
				import2.setSirDate(new Date());
				import2.setImpTransDate(new Date());
				import2.setCreatedBy(User);
				import2.setCreatedDate(new Date());
				import2.setStatus("A");
				import2.setApprovedBy(User);
				import2.setForwardedStatus("N");
				import2.setApprovedDate(new Date());
				import2.setEditedBy(User);
				import2.setEditedDate(new Date());
				import2.setMawb(mawbNo);
				import2.setAssessableValue(assessableValue);
				import2.setBeRequestId(requestID);
				import2.setPortOrigin(portOfOrigin);
				import2.setCountryOrigin(countryOfOrigin);
				import2.setNiptStatus("Y");
				import2.setQrcodeUrl(url);
				import2.setImporternameOnParcel(entityName);
				
//				String extractedString = entityName.replaceAll("\\.\\s*$", "");
				
//				entityID
				
				
//				System.out.println("Entity Id   *************" +entityID);
				Party findByPartyName = PartyService.findByEntityId(compid, branchId, entityID);
				DefaultPartyDetails getdatabyParty = DefaultParyDetailsRepository.getdatabyuser_id(compid, branchId,findByPartyName.getPartyId());
//				System.out.println(findByPartyName);
				
				if (findByPartyName != null) {
					import2.setImporterId(findByPartyName.getPartyId());
					import2.setSezEntityId(findByPartyName.getEntityId());
					import2.setIec(findByPartyName.getIecNo());
				}
				
				if (HawbNo == null || HawbNo.trim().isEmpty()) {
					import2.setHawb("0000");
				}
				else
				{
					import2.setHawb(HawbNo);
				}
				import2.setNSDL_Status(requestStatus);
				import2.setDGDC_Status("Handed over to DGDC SEEPZ");
				import2.setIgmNo(igmNo);
				import2.setChaCde(chaCode);
//				import2.setChaName(getdatabyParty.getImpCHA());
//				import2.setConsoleName(getdatabyParty.getImpConsole());
//				import2.setConsoleName(getdatabyParty.getImpConsole());
				Import FirstNipt = importService.findImportWithCriteria(compid, branchId, new Date());
				
				if(FirstNipt != null)
				{
					import2.setNiptCustomOfficerName(FirstNipt.getNiptCustomOfficerName());
					import2.setNiptCustomsOfficerDesignation(FirstNipt.getNiptCustomsOfficerDesignation());
					import2.setNiptDeputedFromDestination(FirstNipt.getNiptDeputedFromDestination());
					import2.setNiptDeputedToDestination(FirstNipt.getNiptDeputedToDestination());
					import2.setNiptDateOfEscort(FirstNipt.getNiptDateOfEscort());
					import2.setNiptApproverName(FirstNipt.getNiptApproverName());
					import2.setNiptApproverDesignation(FirstNipt.getNiptApproverDesignation());
					import2.setNiptApproverDate(FirstNipt.getNiptApproverDate());		
				}
				
				

//				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date igmDate2 = dateFormat.parse(igmDate);
				import2.setIgmDate(igmDate2);
				String firstCharacter = String.valueOf(packets.charAt(0));

				import2.setNop(Integer.parseInt(firstCharacter));

				BigDecimal grossWeight = null;
				String uomWeight = null;

				// Split the input string by space
				String[] parts4 = weight.split(" ");

				if (parts4.length == 2) {
					try {
						// Attempt to parse the first part as a BigDecimal
						grossWeight = new BigDecimal(parts4[0]);
					} catch (NumberFormatException e) {
						// Handle the exception if parsing fails
					}

					// Assign the second part to uomWeight
					uomWeight = parts4[1];
				}

				import2.setGrossWeight(grossWeight);
				if (uomWeight.equals("KILOGRAMS")) {

					import2.setUomWeight(uomWeight);
				}

				Import_History history = new Import_History();
				history.setCompanyId(compid);
				history.setBranchId(branchId);
				history.setSirNo(autoIncrementSIRId);
				history.setMawb(import2.getMawb());
				history.setHawb(import2.getHawb());
				history.setTransport_Date(new Date());
				history.setOldStatus("Pending");
				history.setNewStatus("Handed over to DGDC SEEPZ");
				history.setUpdatedBy(User);
				historyService.addHistory(history);

				importService.addImport(import2);
				return ResponseEntity.ok(autoIncrementSIRId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("Error extracting data from the provided URL");
		}

	}

	
	@PostMapping("/wrongDeposit")
	public ResponseEntity<?> handlewrongDeposit(@RequestParam("file") MultipartFile file,
			@RequestParam("sirNo") String sirNo, @RequestParam("reasonwrongDeposit") String reasonwrongDeposit,
			@RequestParam("mawb") String mawb,
			@RequestParam("hawb") String hawb, @RequestParam("companyid") String companyid,
			@RequestParam("branchId") String branchId, @RequestParam("transId") String transId)
			throws java.io.IOException {
		try {
			Import byMAWBANdHAWB = importService.getByMAWBANdHAWB(companyid, branchId, transId, mawb, hawb, sirNo);

			if (byMAWBANdHAWB != null) {
				// Get the original file name
				String originalFileName = file.getOriginalFilename();

				// Generate a unique file name to avoid duplicates
				String uniqueFileName = generateUniqueFileName(originalFileName);

				// Set the unique file name in the database
				byMAWBANdHAWB.setWrongDepositFilePath((FileUploadProperties.getPath() + uniqueFileName));
				byMAWBANdHAWB.setDgdcStatus("Wrong Deposit");
//				byMAWBANdHAWB.setNSDL_Status(nsdlStatus);
				// Save the file to your local system with the unique name
				Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName));

				// Set other fields in the Import object
				byMAWBANdHAWB.setWrongDepositwrongDepositRemarks(reasonwrongDeposit);
//				byMAWBANdHAWB.setNSDL_Status(nsdlStatus);
				byMAWBANdHAWB.setWrongDepositStatus("Y");
				// Update the Import object in the database
				Import updateImport = importService.updateImport(byMAWBANdHAWB);
				return ResponseEntity.ok(updateImport);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
		}
	}
	
	
	
	@PostMapping("/{compid}/{branchId}/{tranId}/{MAWB}/{HAWB}/{sirNo}/{userId}/updateNIPT")
	public ResponseEntity<?> updateNipt(@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("tranId") String transId, @PathVariable("userId") String userId,
			@PathVariable("sirNo") String sirNo) {

		Import importObject = importService.getByMAWBANdHAWB(compid, branchId, transId, MAWB, HAWB, sirNo);

		try {
			RestTemplate restTemplate = new RestTemplate();
			String htmlContent = restTemplate.getForObject(importObject.getQrcodeUrl(), String.class);

			// Parse the HTML string using Jsoup
			Document doc = Jsoup.parse(htmlContent);

			String requestStatus = doc.select("#lblRequestStatus").text();

			
			// Extract data from the table
						// Extract data from the table
						Element table = doc.select("table#gvConsigneeDetails").first();
						Elements rows = table.select("tr");

						
						String hawbNoDate = "";
						

						if (rows.size() >= 2) { // Check if there are at least two rows (header row and data row)
							Element dataRow = rows.get(1); // Assuming the data row is the second row (index 1)
							Elements columns = dataRow.select("td");

							if (columns.size() >= 7) { // Check if there are at least seven columns in the data row
							
								hawbNoDate = columns.get(2).text();
								
							}
						}
			
			String HawbNo = "";			
			String[] parts2 = hawbNoDate.split(" ");

			
			if (parts2.length >= 2) 
			{
				HawbNo = parts2[0];				
			}
			if (HawbNo == null || HawbNo.trim().isEmpty()) {
				importObject.setHawb("0000");
			}
			else
			{				
				importObject.setHawb(HawbNo);
			}
			importObject.setNSDL_Status(requestStatus);
			importObject.setEditedBy(userId);
			importObject.setEditedDate(new Date());
			Import addImport = importService.addImport(importObject);
			return ResponseEntity.ok(addImport);
		} catch (Exception e) {
			e.printStackTrace();
			// Handle other exceptions here if needed
			return ResponseEntity.status(422).body("Error extracting data from the provided URL");
		}

	}

//	@GetMapping("/searchBillinTransaction")
//	public List<CombinedImportExport> searchBillingTransation(
//			@RequestParam(name = "companyid", required = false) String companyid,
//			@RequestParam(name = "branchId", required = false) String branchId,
//			@RequestParam(name = "PartyId", required = false) String PartyId,
//			@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
//			@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
//
//		List<Object[]> combinedImportExportData = importRepo.getCombinedImportExportData(companyid, branchId, PartyId,
//				startDate, endDate);
//		String findPartyNameById = PartyService.findPartyNameById(companyid, branchId, PartyId);
//		holidayService.findByDate(companyid, branchId, endDate);
//
//		List<CombinedImportExport> combinedDataList = combinedImportExportData.stream()
//				.map(array -> new CombinedImportExport((String) array[0], findPartyNameById,
//						new Date(((java.util.Date) array[1]).getTime()), (Integer) array[2],
//						array[3] != null ? array[3].toString() : null, array[4] != null ? array[4].toString() : null,
//						array[5] != null ? array[5].toString() : null, (BigDecimal) array[6], (Integer) array[7],
//						array[8] != null ? array[8].toString() : null, array[9] != null ? array[9].toString() : null,
//						array[10] != null ? array[10].toString() : null, (BigDecimal) array[11]))
//				.collect(Collectors.toList());
//
//		return combinedDataList;
//
//	}
//	

	@GetMapping("/searchBillinTransaction")
	public List<CombinedImportExport> searchBillingTransation2(
			@RequestParam(name = "companyid", required = false) String companyid,
			@RequestParam(name = "branchId", required = false) String branchId,
			@RequestParam(name = "PartyId", required = false) String PartyId,
			@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

		Party findPartyById = PartyService.findPartyById(companyid, branchId, PartyId);

		String findPartyNameById = findPartyById.getPartyName();

		Date invoiceDate = findPartyById.getLastInVoiceDate();

		if (invoiceDate != null) {

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(invoiceDate);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			startDate = calendar.getTime();
		}

		List<Object[]> combinedImportExportData = importRepo.getCombinedImportExportData(companyid, branchId, PartyId,
				startDate, endDate);

		CfsTarrif finalCfsTarrif;

		String PARTYID;

		CfsTarrif bypartyId = CFSService.getBypartyId(companyid, branchId, PartyId);

		if (bypartyId != null) {
			finalCfsTarrif = bypartyId;
			PARTYID = bypartyId.getPartyId();
		} else {
			CfsTarrif findByAll = CFSService.getBypartyId(companyid, branchId, "ALL");
			finalCfsTarrif = findByAll;
			PARTYID = findByAll.getPartyId();
		}
		;

		List<CombinedImportExport> combinedDataList = combinedImportExportData.stream().map(array -> {

			String partyId = (String) array[0];
//			String importScStatus = (array[3] != null) ? array[3].toString() : null;
//			String importPcStatus = (array[4] != null) ? array[4].toString() : null;
//			String importHpStatus = (array[5] != null) ? array[5].toString() : null;
			BigDecimal importHpWeight = (array[6] != null) ? (BigDecimal) array[6] : BigDecimal.ZERO;
			int exportNoOfPackages = (array[7] != null) ? Integer.parseInt(array[7].toString()) : 0;
//			String exportScStatus = (array[8] != null) ? array[8].toString() : null;
//			String exportPcStatus = (array[9] != null) ? array[9].toString() : null;
//			String exportHpStatus = (array[10] != null) ? array[10].toString() : null;
			BigDecimal exportHpWeight = (array[11] != null) ? (BigDecimal) array[11] : BigDecimal.ZERO;
			double exportPenaltyLocal = 0.0;
			double importPenaltyLocal = 0.0;

			int exportSubNoOfPackages = (array[16] != null) ? Integer.parseInt(array[16].toString()) : 0;
			int importSubNoOfPackages = (array[14] != null) ? Integer.parseInt(array[14].toString()) : 0;

			

//			String niptStatus = (array[18] != null) ? array[18].toString() : null;

			Date date = new Date(((java.util.Date) array[1]).getTime());

////			Date ImportOutDate = (array[19] != null && !((java.util.Date) array[19]).equals(new Date(0)))
////					? new Date(((java.util.Date) array[19]).getTime())
////					: null;
////			Date ExportOutDate = (array[20] != null && !((java.util.Date) array[20]).equals(new Date(0)))
////					? new Date(((java.util.Date) array[20]).getTime())
////					: null;
////			Date ImportSubOutDate = (array[21] != null && !((java.util.Date) array[21]).equals(new Date(0)))
////					? new Date(((java.util.Date) array[21]).getTime())
////					: null;
////			Date ExportSubOutDate = (array[22] != null && !((java.util.Date) array[22]).equals(new Date(0)))
//					? new Date(((java.util.Date) array[22]).getTime())
//					: null;

//			System.out.println("Import Out Date "+ImportOutDate);
//			System.out.println("Export Out Date "+ExportOutDate);
//			System.out.println("ImportSub Out Date "+ImportSubOutDate);
//			System.out.println("ExportSub Out Date "+ExportSubOutDate);

			int importNoOfPackages = (array[2] != null) ? Integer.parseInt(array[2].toString()) : 0;
			// Total Packages
			int totalPackages = importNoOfPackages + exportNoOfPackages ;

			int niptPackages = 0;
		

//			importPenaltyLocal += importSubPenaltyLocal;
//			exportPenaltyLocal += exportSubPenaltyLocal;

//			Rates for specific conditions 
			double demuragesRate = 0.0;
			int demuragesNop = 0;
			Double importRate = 0.0;
			Double exportRate = 0.0;
			Double importPcRate = 0.0;
			Double importHeavyRate = 0.0;
			Double HolidayRate = 0.0;
			Double importScRate = 0.0;
			Double exportPcRate = 0.0;
			Double exportHeavyRate = 0.0;
//			BigDecimal exportHolidayRate = BigDecimal.ZERO;
			Double exportScRate = 0.0;
			Double importSubRate = 0.0;
			Double exportSubRate = 0.0;

			boolean isHoliday = holidayService.findByDate(companyid, branchId, date);
			boolean isSecondSaturday = isSecondSaturday(date);

			// Set holidayStatus based on whether it's a holiday
			String holidayStatus = (isHoliday || isSecondSaturday) ? "Y" : "N";
			
			
			if (holidayStatus != null && holidayStatus.equals("Y")) {
				String importHolidayServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
						"Holiday");
				HolidayRate = CFSTariffRangeService.findRateForDervice(companyid, branchId,
						finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), importHolidayServiceId, PARTYID,
						totalPackages + importSubNoOfPackages + exportSubNoOfPackages);
			}
			
			
			

//			Import
			
			if (importNoOfPackages > 0) {

				
				List<Import> findByPartyIdofSirDate = importService.findByPartyIdofSirDate(companyid, branchId, PartyId, date);
				
				for(Import imp:findByPartyIdofSirDate)
				{
					
					
					
					
					if(imp.getNop() > 0)
					{
						
						if(imp.getNiptStatus() != null && imp.getNiptStatus().equals("Y"))
						{
							niptPackages += imp.getNop();
						}						
						
						importPenaltyLocal += imp.getImposePenaltyAmount(); 
						
						String importserviceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"import pckgs");					
						double SingleimportRate = cfsTarrifServiceService.findRateService(companyid, branchId,
								finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), importserviceId, PARTYID);
						importRate += SingleimportRate * imp.getNop();						
					}
					if(imp.getPcStatus() != null && imp.getPcStatus().equals("Y"))
					{						
						String importPcServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"import PC");
						importPcRate += CFSTariffRangeService.findRateForDervice(companyid, branchId,
								finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), importPcServiceId, PARTYID,
								imp.getNop());							
					}
					if(imp.getScStatus() != null && imp.getScStatus().equals("Y"))
					{
						String importScServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"import SC");
						double SingleimportScRate = CFSTariffRangeService.findRateForDervice(companyid, branchId,
								finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), importScServiceId, PARTYID,
								imp.getNop());

						importScRate += SingleimportScRate;							
					}
					
					if(imp.getHpStatus() != null && imp.getHpStatus().equals("Y"))
					{
						
						String importHPServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"import HP");

						List<ImportHeavyPackage> byMAWBImport = ImportHeavyService.getByMAWB(companyid, branchId,
								imp.getImpTransId(), imp.getMawb(), imp.getHawb(),
								imp.getSirNo());

						List<BigDecimal> weights = byMAWBImport.stream().map(ImportHeavyPackage::getHpWeight)
								.collect(Collectors.toList());

						System.out.println("************Weights  "+weights+"****************************");
						
						double SingleimportHeavyRate = CFSTariffRangeService.findRateForHeavy(companyid, branchId,
								finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), importHPServiceId, PARTYID,
								weights);
						
						importHeavyRate += SingleimportHeavyRate;						
					}
					
					Date toBeSend = (imp.getOutDate() != null) ? imp.getOutDate() : new Date();
					long timeDifferenceMillis = toBeSend.getTime() - date.getTime();
					int daysDifference = (int) (timeDifferenceMillis / (1000 * 60 * 60 * 24));

					if (daysDifference > 0) {
						String importdemurage = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"export DM");

						double importBillAmount = CFSTariffRangeService.findRateForDervice(companyid, branchId,
								finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), importdemurage, PARTYID,
								daysDifference);

						demuragesNop += imp.getNop();
						demuragesRate += importBillAmount * imp.getNop();					
					}
				}				
			}

//		Export
			
			
			if (exportNoOfPackages > 0) {
				
				List<Export> findByExportsBySerDate = exportrepo.findByCompanyIdAndBranchIdAndNameOfExporterAndSerDateAndStatusNot(companyid, branchId, PartyId, date, "D");
				
				for(Export exp:findByExportsBySerDate)
				{					
					
					if(exp.getNoOfPackages() > 0)
					{	
						
						exportPenaltyLocal += exp.getImposePenaltyAmount();
						
						
					String exportserviceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
							"export pckgs");
					double SingleexportRate = cfsTarrifServiceService.findRateService(companyid, branchId,
							finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), exportserviceId, PARTYID);
					exportRate += SingleexportRate * exp.getNoOfPackages();

					}
					
					if(exp.getPcStatus() !=null && exp.getPcStatus().equals("Y"))
					{					
						
						String exportPcServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"export PC");
						exportPcRate += CFSTariffRangeService.findRateForDervice(companyid, branchId,
								finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), exportPcServiceId, PARTYID,
								exp.getNoOfPackages());						
						
					}
					if(exp.getScStatus() != null && exp.getScStatus().equals("Y"))
					{
						
						String exportScServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"export SC");
						double SingleexportScRate = CFSTariffRangeService.findRateForDervice(companyid, branchId,
								finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), exportScServiceId, PARTYID,
								exp.getNoOfPackages());
						exportScRate += SingleexportScRate;
						
						
					}
					
					if(exp.getHpStatus() != null && exp.getHpStatus().equals("Y"))
					{
						String importPcServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"export HP");

//						Export InvoiceExport = ExportService.findForInvoiceExport(companyid, branchId, date, PartyId,
//								exportNoOfPackages, exportPcStatus, exportScStatus, exportHpStatus);

						List<ExportHeavyPackage> byMAWB = ExportHeavyPackageRepo.findalldata(companyid, branchId,
								exp.getSbRequestId(), exp.getSbNo());

						List<BigDecimal> weights = byMAWB.stream().map(ExportHeavyPackage::getWeight)
								.collect(Collectors.toList());

						
											
						double SingleexportHeavyRate = CFSTariffRangeService.findRateForHeavy(companyid, branchId,
								finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), importPcServiceId, PARTYID,
								weights);
						exportHeavyRate += SingleexportHeavyRate;					
						
					}
					
					
					
					Date toBeSend = (exp.getOutDate() != null) ? exp.getOutDate() : new Date();
					long timeDifferenceMillis = toBeSend.getTime() - date.getTime();
					int daysDifference = (int) (timeDifferenceMillis / (1000 * 60 * 60 * 24));

					if (daysDifference > 0) {
						String importdemurage = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"export DM");

						double importBillAmount = CFSTariffRangeService.findRateForDervice(companyid, branchId,
								finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), importdemurage, PARTYID,
								daysDifference);

						demuragesNop += exp.getNoOfPackages();
						demuragesRate += importBillAmount *  exp.getNoOfPackages();
					}				
				}		
			}
			
//			Import Sub
			
			
			if (importSubNoOfPackages >  0) {
			
			List<ImportSub> findBySirDate = impsubRepo.findByCompanyIdAndBranchIdAndExporterAndSirDateAndStatusNot(companyid, branchId, PartyId, date, "D");
			
			for(ImportSub impsub : findBySirDate)
			{
				
				if(impsub.getNop() > 0)
				{			
					
					importPenaltyLocal += impsub.getImposePenaltyAmount(); 
					
					
					String importserviceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
							"import pckgs");

					double SingleimportRate = cfsTarrifServiceService.findRateService(companyid, branchId,
							finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), importserviceId, PARTYID);
					importSubRate += SingleimportRate * impsub.getNop();
					
					
				}
				
				Date toBeSend = (impsub.getOutDate() != null) ? impsub.getOutDate() : new Date();
				long timeDifferenceMillis = toBeSend.getTime() - date.getTime();
				int daysDifference = (int) (timeDifferenceMillis / (1000 * 60 * 60 * 24));

				if (daysDifference > 0) {
					String importdemurage = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
							"export DM");

					double importBillAmount = CFSTariffRangeService.findRateForDervice(companyid, branchId,
							finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), importdemurage, PARTYID,
							daysDifference);
					
//					System.out.println("importBillAmount Sub " + importBillAmount);

					demuragesNop += impsub.getNop();
					demuragesRate += importBillAmount * impsub.getNop();

				}				
			}
		}	
			
			
			
			if (exportSubNoOfPackages > 0) {
				
				List<ExportSub> findBySerDate = expsubRepo.findByCompanyIdAndBranchIdAndExporterAndSerDate(companyid, branchId, PartyId, date);
				
				for(ExportSub expsub:findBySerDate)
				{
					
					if(expsub.getNop() > 0)
					{
						exportPenaltyLocal += expsub.getImposePenaltyAmount();
						
						String exportserviceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"export pckgs");
						double SingleexportRate = cfsTarrifServiceService.findRateService(companyid, branchId,
								finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), exportserviceId, PARTYID);
						exportSubRate += SingleexportRate * expsub.getNop();
					}
					
					Date toBeSend = (expsub.getOutDate() != null) ? expsub.getOutDate() : new Date();
					long timeDifferenceMillis = toBeSend.getTime() - date.getTime();
					int daysDifference = (int) (timeDifferenceMillis / (1000 * 60 * 60 * 24));

					if (daysDifference > 0) {
						String importdemurage = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"export DM");

						double importBillAmount = CFSTariffRangeService.findRateForDervice(companyid, branchId,
								finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), importdemurage, PARTYID,
								daysDifference);
						demuragesNop += expsub.getNop();
						demuragesRate += importBillAmount * expsub.getNop();
					}				
					
				}		
				
			}			totalPackages += importSubNoOfPackages + exportSubNoOfPackages;

			System.out.println("Export penalty  "+ exportPenaltyLocal);
			
//			System.out.println("Total Demurage rate "+demuragesRate );
			
			return new CombinedImportExport(partyId, findPartyNameById, HolidayRate, date, importNoOfPackages,
					totalPackages, importScRate, importPcRate, importHeavyRate, importHpWeight, importPenaltyLocal,
					exportNoOfPackages, exportScRate, exportPcRate, exportHeavyRate, exportHpWeight, exportPenaltyLocal,
					importRate, exportRate, importSubNoOfPackages, exportSubNoOfPackages, importSubRate, exportSubRate,
					demuragesNop, demuragesRate, niptPackages);
		}).collect(Collectors.toList());

		return combinedDataList;

	}
	
	 public static boolean isSecondSaturday(Date date) {
	        // Convert java.util.Date to java.time.LocalDate
	        LocalDate localDate = date.toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId()).toLocalDate();

	        // Check if the day of the week is Saturday
	        if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
	            // Check if the date falls between the 8th and 14th day of the month
	            int dayOfMonth = localDate.getDayOfMonth();
	            return dayOfMonth >= 8 && dayOfMonth <= 14;
	        }
	        return false;
	    }
	 
	 
//	 Update Bill of Entry Number
	 
		@GetMapping("/{compid}/{branchId}/{user}/updateBillNumber")
		public ResponseEntity<?> updateBillNumber(@PathVariable("compid") String compid,
				@PathVariable("branchId") String branchId, @PathVariable("user") String User,
				@RequestParam("url") String url) {

			try {
             System.out.println(url);
				RestTemplate restTemplate = new RestTemplate();
				String htmlContent = restTemplate.getForObject(url, String.class);

				// Parse the HTML string using Jsoup
				Document doc = Jsoup.parse(htmlContent);

//				// Extract data from specific HTML elements using CSS selectors
//				String dcName = doc.select("#lblDCName").text();
//				String sezName = doc.select("#lblSEZName").text();
//				String entityName = doc.select("#lblEntityName").text();
//				String importExportCode = doc.select("#lblImportExportCode").text();
				String entityID = doc.select("#lblEntityID").text();
//
//				// Extract additional fields as needed
//				String requestDetails = doc.select("#lblRequestDetails").text();
				String requestID = doc.select("#lblRequestID").text();
//				String portCode = doc.select("#lblBOEPortCode").text();
//				String portOfOrigin = doc.select("#lblBOEPortOrgn").text();
//				String countryOfOrigin = doc.select("#lblBOECntryOrgn").text();
//				String importDeptDetails = doc.select("#lblBEThokaNo").text();
				String chaDetails = doc.select("#lblCHADetails").text();
//				String assessmentDate = doc.select("#lblAssesmentDate").text();
				String requestStatus = doc.select("#lblRequestStatus").text();
//				String assessableValue = doc.select("#lblAssessableValue").text();

				
				 // Split the extracted text into chaName and chaCode using the '-' sign
		        String[] parts5 = chaDetails.split(" - ");
		        String chaName = parts5[0];
		        String chaCode = parts5.length > 1 ? parts5[1] : "";
				
				
				Element beElement = doc.select("#lblBEThokaNo").first();
				String beText = beElement.text();

				// Split the extracted text into BeNumber and BeDate
				String[] values = beText.split(", ");
				String beNumber = values[0];
				String beDate = values[1];
				
				// Extract data from the table
				// Extract data from the table
				Element table = doc.select("table#gvConsigneeDetails").first();
				Elements rows = table.select("tr");
				

				String igmNoDate = "";
				String mawbNoDate = "";
				String hawbNoDate = "";
				String weight = "";
				String packets = "";
				String packageMarksNumbers = "";
				String noOfContainers = "";

				if (rows.size() >= 2) { // Check if there are at least two rows (header row and data row)
					Element dataRow = rows.get(1); // Assuming the data row is the second row (index 1)
					Elements columns = dataRow.select("td");

					if (columns.size() >= 7) { // Check if there are at least seven columns in the data row
						igmNoDate = columns.get(0).text();
						mawbNoDate = columns.get(1).text();
						hawbNoDate = columns.get(2).text();
						weight = columns.get(3).text();
						packets = columns.get(4).text();
						packageMarksNumbers = columns.get(5).text();
						noOfContainers = columns.get(6).text();
					}
				}

				String[] parts = mawbNoDate.split(" ");

				String mawbNo = "";
				String mawbDate = "";

				if (parts.length >= 2) {
					mawbNo = parts[0]; // "3111795912"
					mawbDate = parts[1]; // "22/08/2023"
				}

				String[] parts2 = hawbNoDate.split(" ");

				String HawbNo = "";
				String HawbDate = "";

				if (parts2.length >= 2) {
					HawbNo = parts2[0]; // "3111795912"
					HawbDate = parts2[1]; // "22/08/2023"
				}

				String[] parts3 = igmNoDate.split(" ");

				String igmNo = "";
				String igmDate = "";

				if (parts3.length >= 2) {
					igmNo = parts3[0]; // "3111795912"
					igmDate = parts3[1]; // "22/08/2023"
				}
				// Create a JSON object to store all extracted fields
//				String extractedData = "{" + "\"dcName\":\"" + dcName + "\"," + "\"sezName\":\"" + sezName + "\","
//						+ "\"entityName\":\"" + entityName + "\"," + "\"importExportCode\":\"" + importExportCode + "\","
//						+ "\"entityID\":\"" + entityID + "\"," + "\"requestDetails\":\"" + requestDetails + "\","
//						+ "\"requestID\":\"" + requestID + "\"," + "\"portCode\":\"" + portCode + "\","
//						+ "\"portOfOrigin\":\"" + portOfOrigin + "\"," + "\"countryOfOrigin\":\"" + countryOfOrigin + "\","
//						+ "\"importDeptDetails\":\"" + importDeptDetails + "\"," + "\"chaDetails\":\"" + chaDetails + "\","
//						+ "\"assessmentDate\":\"" + assessmentDate + "\"," + "\"requestStatus\":\"" + requestStatus + "\","
//						+ "\"assessableValue\":\"" + assessableValue + "\","
////	                 + "\"igmNoDate\":\"" + igmNoDate + "\","
////	                 + "\"mawbNoDate\":\"" + mawbNoDate + "\","
//						+ "\"mawbNo\":\"" + mawbNo + "\"," + "\"mawbDate\":\"" + mawbDate + "\"," + "\"HawbNo\":\"" + HawbNo
//						+ "\"," + "\"HawbDate\":\"" + HawbDate + "\","
////	                 + "\"hawbNoDate\":\"" + hawbNoDate + "\","
//						+ "\"igmNo\":\"" + igmNo + "\"," + "\"igmDate\":\"" + igmDate + "\"," + "\"weight\":\"" + weight
//						+ "\"," + "\"packets\":\"" + packets + "\"," + "\"packageMarksNumbers\":\"" + packageMarksNumbers
//						+ "\"," + "\"noOfContainers\":\"" + noOfContainers + "\"" + "}";

				Import import2 = importService.findForBillNumber(compid, branchId, mawbNo,HawbNo,igmNo);

				String findByEntityId = PartyService.findByEntityIdPartyId(compid, branchId, entityID);
				System.out.println("findByEntityId "+findByEntityId);
				DefaultPartyDetails getdatabyParty = DefaultParyDetailsRepository.getdatabyuser_id(compid, branchId,findByEntityId);
				System.out.println("getdatabyParty "+getdatabyParty);
				if (import2 != null) {
					import2.setBeRequestId(requestID);
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					import2.setBeNo(beNumber);
					import2.setBeDate(dateFormat.parse(beDate));
					import2.setChaCde(chaCode);
//					import2.setChaName(getdatabyParty.getImpCHA());
//								
//					import2.setConsoleName(getdatabyParty.getImpConsole());
					import2.setNsdlStatus(requestStatus);
					importService.addImport(import2);
					return ResponseEntity.ok("Bill Number Update Successfully");
				} else 
				{					
					return ResponseEntity.ok("Data Not Found");
				}

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.ok("Error extracting data from the provided URL");
			}

		}
		
		
		
//		 Update Paersonal Carriage
		 
			@GetMapping("/{compid}/{branchId}/{user}/updatePersonalCarraige")
			public ResponseEntity<?> updatePersonalCarraige(@PathVariable("compid") String compid,
					@PathVariable("branchId") String branchId, @PathVariable("user") String User,
					@RequestParam("url") String url) {

				try {
//	             System.out.println(url);
					RestTemplate restTemplate = new RestTemplate();
					String htmlContent = restTemplate.getForObject(url, String.class);

					// Parse the HTML string using Jsoup
					Document doc = Jsoup.parse(htmlContent);

//					// Extract data from specific HTML elements using CSS selectors
//					String dcName = doc.select("#lblDCName").text();
//					String sezName = doc.select("#lblSEZName").text();
//					String entityName = doc.select("#lblEntityName").text();
//					String importExportCode = doc.select("#lblImportExportCode").text();
				String entityID = doc.select("#lblEntityID").text();
	//
//					// Extract additional fields as needed
//					String requestDetails = doc.select("#lblRequestDetails").text();
					String requestID = doc.select("#lblRequestID").text();
//					String portCode = doc.select("#lblBOEPortCode").text();
//					String portOfOrigin = doc.select("#lblBOEPortOrgn").text();
//					String countryOfOrigin = doc.select("#lblBOECntryOrgn").text();
//					String importDeptDetails = doc.select("#lblBEThokaNo").text();
					String chaDetails = doc.select("#lblCHADetails").text();
//					String assessmentDate = doc.select("#lblAssesmentDate").text();
					String requestStatus = doc.select("#lblRequestStatus").text();
//					String assessableValue = doc.select("#lblAssessableValue").text();

					
					 // Split the extracted text into chaName and chaCode using the '-' sign
			        String[] parts5 = chaDetails.split(" - ");
			        String chaName = parts5[0];
			        String chaCode = parts5.length > 1 ? parts5[1] : "";
					
					
					Element beElement = doc.select("#lblBEThokaNo").first();
					String beText = beElement.text();

					// Split the extracted text into BeNumber and BeDate
					String[] values = beText.split(", ");
					String beNumber = values[0];
					String beDate = values[1];
					
					// Extract data from the table
					// Extract data from the table
					Element table = doc.select("table#gvConsigneeDetails").first();
					Elements rows = table.select("tr");
					

					String igmNoDate = "";
					String mawbNoDate = "";
					String hawbNoDate = "";
					String weight = "";
					String packets = "";
					String packageMarksNumbers = "";
					String noOfContainers = "";

					if (rows.size() >= 2) { // Check if there are at least two rows (header row and data row)
						Element dataRow = rows.get(1); // Assuming the data row is the second row (index 1)
						Elements columns = dataRow.select("td");

						if (columns.size() >= 7) { // Check if there are at least seven columns in the data row
							igmNoDate = columns.get(0).text();
							mawbNoDate = columns.get(1).text();
							hawbNoDate = columns.get(2).text();
							weight = columns.get(3).text();
							packets = columns.get(4).text();
							packageMarksNumbers = columns.get(5).text();
							noOfContainers = columns.get(6).text();
						}
					}

					String[] parts = mawbNoDate.split(" ");

					String mawbNo = "";
					String mawbDate = "";

//					if (parts.length >= 2) {
//					    int firstSpaceIndex = mawbNoDate.indexOf(" ");
//					    int secondSpaceIndex = mawbNoDate.indexOf(" ", firstSpaceIndex + 1);
//
//					    if (secondSpaceIndex != -1) {
//					        mawbNo = mawbNoDate.substring(0, secondSpaceIndex);
//					        mawbDate = mawbNoDate.substring(secondSpaceIndex + 1);
//					    } else {
//					        mawbNo = parts[0];
//					        mawbDate = parts[1];
//					    }
//					}
					
					
					
					if (parts.length >= 2) {
						
						 Pattern pattern = Pattern.compile("\\b\\d{2}/\\d{2}/\\d{4}\\b"); // Matches date in the format dd/MM/yyyy
						 Matcher matcher = pattern.matcher(mawbNoDate);
						 
						 if (matcher.find()) {
							 mawbDate = matcher.group(); // Get the found date
							 mawbNo = mawbNoDate.substring(0, matcher.start()).trim(); // Get text before the date
					        }
						 
					}
					

					String[] parts2 = hawbNoDate.split(" ");

					String HawbNo = "";
					String HawbDate = "";

					if (parts2.length >= 2) {
						HawbNo = parts2[0]; // "3111795912"
						HawbDate = parts2[1]; // "22/08/2023"
					}

//					String[] parts3 = igmNoDate.split(" ");
//
//					String igmNo = "";
//					String igmDate = "";

//					if (parts3.length >= 2) {
//						igmNo = parts3[0]; // "3111795912"
//						igmDate = parts3[1]; // "22/08/2023"
//					}
					// Create a JSON object to store all extracted fields
//					String extractedData = "{" + "\"dcName\":\"" + dcName + "\"," + "\"sezName\":\"" + sezName + "\","
//							+ "\"entityName\":\"" + entityName + "\"," + "\"importExportCode\":\"" + importExportCode + "\","
//							+ "\"entityID\":\"" + entityID + "\"," + "\"requestDetails\":\"" + requestDetails + "\","
//							+ "\"requestID\":\"" + requestID + "\"," + "\"portCode\":\"" + portCode + "\","
//							+ "\"portOfOrigin\":\"" + portOfOrigin + "\"," + "\"countryOfOrigin\":\"" + countryOfOrigin + "\","
//							+ "\"importDeptDetails\":\"" + importDeptDetails + "\"," + "\"chaDetails\":\"" + chaDetails + "\","
//							+ "\"assessmentDate\":\"" + assessmentDate + "\"," + "\"requestStatus\":\"" + requestStatus + "\","
//							+ "\"assessableValue\":\"" + assessableValue + "\","
////		                 + "\"igmNoDate\":\"" + igmNoDate + "\","
////		                 + "\"mawbNoDate\":\"" + mawbNoDate + "\","
//							+ "\"mawbNo\":\"" + mawbNo + "\"," + "\"mawbDate\":\"" + mawbDate + "\"," + "\"HawbNo\":\"" + HawbNo
//							+ "\"," + "\"HawbDate\":\"" + HawbDate + "\","
////		                 + "\"hawbNoDate\":\"" + hawbNoDate + "\","
//							+ "\"igmNo\":\"" + igmNo + "\"," + "\"igmDate\":\"" + igmDate + "\"," + "\"weight\":\"" + weight
//							+ "\"," + "\"packets\":\"" + packets + "\"," + "\"packageMarksNumbers\":\"" + packageMarksNumbers
//							+ "\"," + "\"noOfContainers\":\"" + noOfContainers + "\"" + "}";

					
//					System.out.println("comapny "+compid +" branch "+branchId + "detention "+mawbNo);
					
					System.out.println("detention "+mawbNo);
					
					Import import2 = importService.findForPersonalCarriage(compid, branchId, mawbNo);
					String findByEntityId = PartyService.findByEntityIdPartyId(compid, branchId, entityID);
					
//					System.out.println("Entity Id "+entityID);
//					
//					System.out.println("Party Id  "+findByEntityId);
					
					DefaultPartyDetails getdatabyParty = DefaultParyDetailsRepository.getdatabyuser_id(compid, branchId,findByEntityId);			
					
					
					System.out.println(import2);
					if (import2 != null) {
						import2.setBeRequestId(requestID);						
//						import2.setMawb(mawbNo);					
//						import2.setHawb(mawbNo);
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						import2.setBeNo(beNumber);
						import2.setBeDate(dateFormat.parse(beDate));
						import2.setChaCde(chaCode);
						import2.setChaName(getdatabyParty.getImpCHA());
						import2.setConsoleName(getdatabyParty.getImpConsole());
						import2.setNsdlStatus(requestStatus);
						importService.addImport(import2);
						return ResponseEntity.ok("Import Update Successfully");
					} else 
					{					
						return ResponseEntity.ok("Data Not Found");
					}

				} catch (Exception e) {
					e.printStackTrace();
					return ResponseEntity.ok("Error extracting data from the provided URL");
				}

			}


			 @GetMapping("/alltp/{cid}/{bid}/{date}")
				public List<String> getalltp(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
					
					List<String> tp = importRepo.getalltp(cid, bid, date);
					return tp;
				}

			 
			 @GetMapping("/searchforparty/{importer}")
				public List<Import> searchImports1(@RequestParam(name = "pcStatus", required = false) String pcStatus,
						@RequestParam(name = "scStatus", required = false) String scStatus,
						@RequestParam(name = "searchValue", required = false) String searchValue,
						@RequestParam(name = "companyid", required = false) String companyid,
						@RequestParam(name = "branchId", required = false) String branchId,
						@RequestParam(name = "holdStatus", required = false) String holdStatus,
						@RequestParam(name = "hpStatus", required = false) String hpStatus,
						@RequestParam(name = "dgdcStatus", required = false) String dgdcStatus,
						@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
						@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
						@PathVariable("importer") String importer) {

					
//					System.out.println("searchValue "+searchValue);
					
					System.out.println(importer);
					return importRepo.findByAttributes1(companyid, branchId, pcStatus, scStatus, hpStatus, holdStatus, dgdcStatus,
							startDate, endDate,searchValue,importer);
				}
				
				@GetMapping("/searchforcartingagent/{importer}")
				public List<Import> searchImports2(@RequestParam(name = "pcStatus", required = false) String pcStatus,
						@RequestParam(name = "scStatus", required = false) String scStatus,
						@RequestParam(name = "searchValue", required = false) String searchValue,
						@RequestParam(name = "companyid", required = false) String companyid,
						@RequestParam(name = "branchId", required = false) String branchId,
						@RequestParam(name = "holdStatus", required = false) String holdStatus,
						@RequestParam(name = "hpStatus", required = false) String hpStatus,
						@RequestParam(name = "dgdcStatus", required = false) String dgdcStatus,
						@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
						@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
						@PathVariable("importer") String importer) {

					
//					System.out.println("searchValue "+searchValue);
					
					System.out.println(importer);
					return importRepo.findByAttributes2(companyid, branchId, pcStatus, scStatus, hpStatus, holdStatus, dgdcStatus,
							startDate, endDate,searchValue,importer);
				}
				
				
				@GetMapping("/searchforcha/{importer}")
				public List<Import> searchImports3(@RequestParam(name = "pcStatus", required = false) String pcStatus,
						@RequestParam(name = "scStatus", required = false) String scStatus,
						@RequestParam(name = "searchValue", required = false) String searchValue,
						@RequestParam(name = "companyid", required = false) String companyid,
						@RequestParam(name = "branchId", required = false) String branchId,
						@RequestParam(name = "holdStatus", required = false) String holdStatus,
						@RequestParam(name = "hpStatus", required = false) String hpStatus,
						@RequestParam(name = "dgdcStatus", required = false) String dgdcStatus,
						@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
						@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
						@PathVariable("importer") String importer) {

					
//					System.out.println("searchValue "+searchValue);
					
					System.out.println(importer);
					return importRepo.findByAttributes3(companyid, branchId, pcStatus, scStatus, hpStatus, holdStatus, dgdcStatus,
							startDate, endDate,searchValue,importer);
				}
				
				
				@GetMapping("/searchforconsole/{importer}")
				public List<Import> searchImports4(@RequestParam(name = "pcStatus", required = false) String pcStatus,
						@RequestParam(name = "scStatus", required = false) String scStatus,
						@RequestParam(name = "searchValue", required = false) String searchValue,
						@RequestParam(name = "companyid", required = false) String companyid,
						@RequestParam(name = "branchId", required = false) String branchId,
						@RequestParam(name = "holdStatus", required = false) String holdStatus,
						@RequestParam(name = "hpStatus", required = false) String hpStatus,
						@RequestParam(name = "dgdcStatus", required = false) String dgdcStatus,
						@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
						@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
						@PathVariable("importer") String importer) {

					
//					System.out.println("searchValue "+searchValue);
					
					System.out.println(importer);
					return importRepo.findByAttributes4(companyid, branchId, pcStatus, scStatus, hpStatus, holdStatus, dgdcStatus,
							startDate, endDate,searchValue,importer);
				}
		
		
}
