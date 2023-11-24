package com.cwms.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.cwms.entities.Branch;
import com.cwms.entities.Company;
import com.cwms.entities.DefaultPartyDetails;
import com.cwms.entities.Export;
import com.cwms.entities.ExportHeavyPackage;
import com.cwms.entities.ExportSub;
import com.cwms.entities.Export_History;
import com.cwms.entities.ExternalParty;
import com.cwms.entities.Import;
import com.cwms.entities.ImportSub;
import com.cwms.entities.InvoiceMain;
import com.cwms.entities.Party;
import com.cwms.entities.ReadURL;
import com.cwms.entities.RepresentParty;
import com.cwms.entities.ScanData;
import com.cwms.entities.ScannedParcels;
import com.cwms.helper.FileUploadProperties;
import com.cwms.repository.BranchRepo;
import com.cwms.repository.CompanyRepo;
import com.cwms.repository.DefaultParyDetailsRepository;
import com.cwms.repository.ExportHeavyPackageRepo;
import com.cwms.repository.ExportRepository;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.Export_HistoryRepository;
import com.cwms.repository.ExternalPartyRepository;
import com.cwms.repository.ImportRepository;
import com.cwms.repository.ImportSubRepository;
import com.cwms.repository.InvoiceRepositary;
import com.cwms.repository.PartyRepository;
import com.cwms.repository.RepresentPartyRepository;
import com.cwms.repository.ScannedParcelsRepo;
import com.cwms.service.ExportService;
import com.cwms.service.ImageService;
import com.cwms.service.ProcessNextIdService;
import com.itextpdf.text.Image;

@CrossOrigin("*")
@RequestMapping("/export")
@RestController
public class ExportController {
	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private BranchRepo branchRepo;
	
	@Autowired
	public DefaultParyDetailsRepository defaultrepo;
	
	@Autowired
	private RepresentPartyRepository representPartyRepository;
	
	@Autowired
	public InvoiceRepositary invoiceRepository;
	
	@Autowired
	private ImportRepository importRepository;
	
	@Autowired
	private ExportSubRepository exportSubRepository;
	
	@Autowired
	private ImportSubRepository importSubRepository;

	@Autowired
	private ExportRepository exportRepository;

	@Autowired
	private ScannedParcelsRepo scannedparcelsrepo;

	@Autowired
	private ExportService sbTransactionService;

	@Autowired
	private Export_HistoryRepository export_HistoryRepository;

	@Autowired
	public FileUploadProperties FileUploadProperties;

	@Autowired
	private ImageService imageService;

	@Autowired
	private ProcessNextIdService processNextIdService;
	@Autowired
	private ExternalPartyRepository externalPartyRepository;

	@Autowired
	private PartyRepository partyrepo;

	@Autowired
	private RepresentPartyRepository representReo;

	@Autowired
	private ExportHeavyPackageRepo eexportHeavyRepo;

	@Autowired
	private TemplateEngine templateEngine;

	
	@Autowired
	private com.cwms.service.ExternalParty_Service ExternalParty_Service;
	
	
	@GetMapping("/byser/{cid}/{bid}/{ser}")
	public Export getdatabyser(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("ser") String ser) {
		return exportRepository.findBySer(cid, bid, ser);
	}

	@GetMapping("/exportData")
	public ResponseEntity<List<Export>> findAllExportData(@RequestParam String companyId, @RequestParam String branchId,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate

	) {

		List<Export> exportData = sbTransactionService.findAllExportData(companyId, branchId, startDate, endDate);

		System.out.println(exportData);

		return ResponseEntity.ok(exportData);
	}
	
	@GetMapping("/bysbsbreq/{cid}/{bid}/{sbreqid}/{sbno}")
	public Export bysbnoandreq(@PathVariable("cid") String companyId,@PathVariable("bid") String branchId,@PathVariable("sbreqid") String sbreqid,@PathVariable("sbno") String sbno) {
		 Export export = exportRepository.findBySBNoandSbreqid(companyId,branchId,sbreqid,sbno);
		 return export;
	}

	@PostMapping("/updatePCTMAndTPNo")
	public List<Export> updatePCTMAndTPNo(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam("companyId") String companyId, @RequestParam("branchId") String branchId) {

		List<Export> exportDataToUpdate = exportRepository.findAllExportData(companyId, branchId, startDate, endDate);

		Map<String, String> airlinePctmMap = new HashMap<>();
		String exportTpNo = processNextIdService.getNextTPNo();
		for (Export export : exportDataToUpdate) {
			String airlineName = export.getAirlineName();

			export.setTpNo(exportTpNo);
			export.setTpDate(new Date());

			String pctmNo = airlinePctmMap.get(airlineName);

			if (pctmNo == null) {
				pctmNo = processNextIdService.getNextPctmNo();
				airlinePctmMap.put(airlineName, pctmNo);
			}

			// Set the generated pctmNo for the current Export record
			export.setPctmNo(pctmNo);
			export.setPctmDate(new Date());

			// Generate a new tpNo for each record

		}

		// Save the updated records
		exportRepository.saveAll(exportDataToUpdate);

		return exportDataToUpdate;
	}

	@GetMapping("/carting-agents")
	public List<String> getDistinctCartingAgents(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("serDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date serDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(serDate);

		List<String> cartingAgents = exportRepository.findAllCartingAgentNames(companyId, branchId, formattedDate);

		for (String cr : cartingAgents) {
			System.out.println(cr);
		}
		return cartingAgents;
	}

	@GetMapping("/tpNumbers")
	public List<String> getDistinctTpNumbers(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("serDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date serDate,
			@RequestParam("cartingAgent") String cartingAgent) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(serDate);

		List<String> tpNumbers = exportRepository.findDistinctTpNos(companyId, branchId, formattedDate, cartingAgent);

		for (String cr : tpNumbers) {
			System.out.println(cr);
		}
		return tpNumbers;
	}

	@GetMapping("/exportByTpNo")
	public List<Export> getExportDataByTpNo(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("serDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date serDate,
			@RequestParam("cartingAgent") String cartingAgent, @RequestParam("tpNo") String tpNo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(serDate);

		List<Export> tpDataAll = exportRepository.findImportTPData(companyId, branchId, formattedDate, cartingAgent,
				tpNo);
		// Call the repository method with the given parameters
		for (Export cr : tpDataAll) {
			System.out.println(cr);
		}
		return tpDataAll;
	}

	@GetMapping("/all/{companyId}/{branchId}")
	public List<Export> getAll1(@PathVariable("companyId") String companyId,
			@PathVariable("branchId") String branchId) {
		return this.exportRepository.findByAll(companyId, branchId);
	}

	@GetMapping("/tpNo")
	public List<String> getAllbytpdate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("cid") String cid, @RequestParam("bid") String bid) {

		return exportRepository.findByTp(date, cid, bid);
	}

	@GetMapping("/getalldata")
	public List<Export> getallbyTpnoandTpdate(@RequestParam("cid") String cid, @RequestParam("bid") String bid,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam("tpno") String tpno
	// @RequestParam("status") char status
	) { // Change the parameter name to "status"
		return exportRepository.findByTpdateTpno(cid, bid, date, tpno); // Use "status" parameter here
	}

	@GetMapping(value = "/getCartingAgent/{companyId}/{branchId}/{sirDate}")
	public List<ExternalParty> getCartingAgent(@PathVariable("companyId") String companyId,
			@PathVariable("branchId") String branchId,
			@PathVariable("sirDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sirDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(sirDate);
		List<String> cIds = exportRepository.findByCompanyAndBranchAndSerDate(companyId, branchId, formattedDate);

		List<ExternalParty> externalParties = new ArrayList<>();
		for (String string : cIds) {
			externalParties.add(externalPartyRepository.findBycompbranchexternal(companyId, branchId, string));
		}

		return externalParties;
	}

	@GetMapping(value = "/getExportTpList/{companyId}/{branchId}/{sirDate}/{exId}")
	public List<Export> getExportTpList(@PathVariable("companyId") String companyId,
			@PathVariable("branchId") String branchId,
			@PathVariable("sirDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sirDate,
			@PathVariable("exId") String exId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(sirDate);

		List<Export> exports = exportRepository.findByCompanyAndBranchAndserDateAndexternalPId(companyId, branchId,
				formattedDate, exId);
		return exports;
	}

	@PostMapping(value = "/readurl")
	public Map<String, String> getlink(@RequestBody ReadURL readURL) throws IOException {
		String s = readURL.getLink();
		URL url = new URL(s);
		System.out.println(s);
		int timeoutMillis = 50000; // 5 seconds
		Document document = Jsoup.parse(url, timeoutMillis);
		Map<String, String> hashMap = new LinkedHashMap<>();

		Elements labels = document.select(".Label, .LabelHeader, .SubHeader");

		String currentKey = "";
		String Demo = "";
		String key = "";

		for (Element label : labels) {

			if (label.hasClass("LabelHeader") || label.hasClass("SubHeader")) {
				key = "";
				currentKey = label.text();
				currentKey = currentKey.toLowerCase();

				for (int i = 0; i < currentKey.length(); i++) {
					if ((int) currentKey.charAt(i) >= 97 && (int) currentKey.charAt(i) <= 122) {
						key += currentKey.charAt(i);
					}
				}

				if (label.text().equals("Consignment Details:")) {
					Demo = label.text();
					currentKey = Demo;
					hashMap.put(key, "");
				}

			} else {
				String value = label.text();

				hashMap.put(key, value);
			}
		}

		return hashMap;
	}

	@PostMapping(value = "/readurlSBD") // read url SBD read url Shipping Bill Details
	public Export getlinkToChange(@RequestBody ReadURL readURL) throws IOException {
		String s = readURL.getLink();
		URL url = new URL(s);
		// System.out.println(s);
		int timeoutMillis = 50000; // 5 seconds
		Document document = Jsoup.parse(url, timeoutMillis);
		Map<String, String> hashMap = new LinkedHashMap<>();

		Elements labels = document.select(".Label, .LabelHeader, .SubHeader");

		String currentKey = "";
		String Demo = "";
		String key = "";

		for (Element label : labels) {

			if (label.hasClass("LabelHeader") || label.hasClass("SubHeader")) {
				key = "";
				currentKey = label.text();
				currentKey = currentKey.toLowerCase();

				for (int i = 0; i < currentKey.length(); i++) {
					if ((int) currentKey.charAt(i) >= 97 && (int) currentKey.charAt(i) <= 122) {
						key += currentKey.charAt(i);
					}
				}

				if (label.text().equals("Consignment Details:")) {
					Demo = label.text();
					currentKey = Demo;
					hashMap.put(key, "");
				}

			} else {
				String value = label.text();

				hashMap.put(key, value);
			}
		}
		ScanData ScanData = new ScanData();
		Export sbTransaction = new Export();

		ScanData.setDcoffice(hashMap.get("dcoffice"));
		ScanData.setSezname(hashMap.get("sezname"));
		ScanData.setSezunitdevelopercodeveloper(hashMap.get("sezunitdevelopercodeveloper"));

		ScanData.setImportexportcode(hashMap.get("importexportcode"));
		sbTransaction.setIecCode(hashMap.get("importexportcode"));

		ScanData.setEntityid(hashMap.get("entityid"));
		sbTransaction.setEntityId(hashMap.get("entityid"));

		ScanData.setRequestdetails(hashMap.get("requestdetails"));
		sbTransaction.setDescriptionOfGoods(hashMap.get("requestdetails"));

		ScanData.setRequestid(hashMap.get("requestid"));
		sbTransaction.setSbRequestId(hashMap.get("requestid"));

		ScanData.setPortofloading(hashMap.get("portofloading"));

		ScanData.setPortofdestination(hashMap.get("portofdestination"));
		sbTransaction.setPortOfDestination(hashMap.get("portofdestination"));

		ScanData.setCountryofdestination(hashMap.get("countryofdestination"));
		sbTransaction.setCountryOfDestination(hashMap.get("countryofdestination"));

		ScanData.setSbnodate(hashMap.get("sbnodate"));
		String sbnodate = hashMap.get("sbnodate");
		String[] parts = sbnodate.split(",");
		String sbno = parts[0].trim();
		sbTransaction.setSbNo(sbno);

		String inputDateStr = parts[1].trim();

		SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Parse the input date string
			Date inputDate = inputDateFormat.parse(inputDateStr);
			sbTransaction.setSbDate(inputDate);
////			System.out.println(
//					"===========================================================================================");
//			System.out.println(sbTransaction.getSbDate());
//			System.out.println(
//					"===========================================================================================");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ScanData.setCustomhouseagentnamecode(hashMap.get("customhouseagentnamecode"));
		sbTransaction.setChaName(hashMap.get("customhouseagentnamecode"));
		sbTransaction.setChaNo(hashMap.get("customhouseagentnamecode"));
		ScanData.setAssessmentdate(hashMap.get("assessmentdate"));

		ScanData.setRequeststatus(hashMap.get("requeststatus"));
		sbTransaction.setNsdlStatus(hashMap.get("requeststatus"));
		ScanData.setConsignmentdetails(hashMap.get("consignmentdetails"));

		ScanData.setRotationnumberdate(hashMap.get("rotationnumberdate"));

		ScanData.setCargodetails(hashMap.get("cargodetails"));

		String cargoDetails = hashMap.get("cargodetails");
		String[] parts1 = cargoDetails.split(",");

		// Create two String variables to store the split cargo details
		String cargoDetail1 = parts1[0].trim();
		String cargoDetail2 = parts1[1].trim();

		Pattern pattern = Pattern.compile("Weight:\\s*(\\d+\\.\\d+)\\s*(\\w+)");
		Matcher matcher = pattern.matcher(cargoDetail1);

		if (matcher.find()) {
			String value1 = matcher.group(1); // This will be "1700.0000"
			String value2 = matcher.group(2); // This will be "GRAMS"
			sbTransaction.setGrossWeight(Double.valueOf(value1));
			sbTransaction.setUomGrossWeight(value2);
		}

		cargoDetail2 = cargoDetail2.replace("Packets: ", "");

		String[] subparts = cargoDetail2.split("\\s+");

		if (subparts.length >= 2) {
			String value1 = subparts[0].trim(); // This will be "1"
			String value2 = subparts[1].trim(); // This will be "PACKAGES"
			sbTransaction.setNoOfPackages(Integer.parseInt(value1));
			sbTransaction.setUomOfPackages(value2);
		}

		ScanData.setNetrealisablevalueinrs(hashMap.get("netrealisablevalueinrs"));
		try {
			double doubleValue = Double.parseDouble(hashMap.get("netrealisablevalueinrs"));
			sbTransaction.setFobValueInINR(doubleValue);

		} catch (NumberFormatException e) {
			System.err.println("Error: Unable to convert the string to a double.");
			e.printStackTrace();
		}

		sbTransaction.setCurrentDate();
		sbTransaction.setCompanyId(readURL.getCompanyId());
		sbTransaction.setBranchId(readURL.getBranchId());

		sbTransaction.setNameOfExporter(ScanData.getSezname());
		sbTransaction.setCreatedBy(readURL.getCreatedBy());
		sbTransaction.setEditedBy(readURL.getEditedBy());
		sbTransaction.setApprovedBy(readURL.getApprovedBy());
		sbTransaction.setStatus("A");
		sbTransaction.setSerNo(processNextIdService.autoIncrementSIRExportId());
		sbTransaction.setDgdcStatus("Handed over to DGDC SEEPZ");

		System.out.println("----------------------------------------------------------");
		System.out.println(ScanData);
		System.out.println(sbTransaction);

		Export_History export_History = new Export_History(sbTransaction.getCompanyId(), sbTransaction.getBranchId(),
				sbTransaction.getSbNo(), sbTransaction.getSbRequestId(), sbTransaction.getSerNo(),
				sbTransaction.getCreatedBy(), "panding . .", sbTransaction.getDgdcStatus(), null);
		export_History.SetHistoryDate();
		export_HistoryRepository.saveAndFlush(export_History);

		return sbTransactionService.createSBTransaction(sbTransaction);
	}

	@GetMapping("/{sbTransId}")
	public Export getSBTransaction(@PathVariable String sbTransId) {

		// Implement the logic to retrieve a single SBTransaction by sbTransId.
		return sbTransactionService.getSBTransaction(sbTransId);
	}

	@PostMapping("/save1")
	public Export createSBTransaction(@RequestBody Export export) throws Exception {

		Export existingSub = exportRepository.findBySBNoandSbreqid(export.getCompanyId(), export.getBranchId(),
				export.getSbRequestId(), export.getSbNo());

		if (existingSub != null) {
			throw new Exception("RequestId already present");
		}
		export.setSerNo(processNextIdService.autoIncrementSIRExportId());
		export.setSerDate(new Date());
		export.setAppovedate();
		if (export.getStatus() == "N") {
			export.setStatus("A");
		} else {
			export.setStatus("A");
		}
		export.setScStatus("N");
		export.setHpStatus("N");
		export.setPcStatus("N");
		export.setHoldStatus("N");
		export.setCancelStatus("N");
		export.setNoc(0);
		export.setDgdc_cargo_in_scan(0);
		export.setDgdc_cargo_out_scan(0);
		export.setDgdc_seepz_in_scan(0);
		export.setDgdc_seepz_out_scan(0);
		export.setDgdcStatus("Handed over to DGDC SEEPZ");
		export.setNsdlStatus("Pending");
		BigDecimal big = new BigDecimal("0.0");
		export.setImposePenaltyAmount(0);

		Export_History export_History = new Export_History(export.getCompanyId(), export.getBranchId(),
				export.getSbNo(), export.getSbRequestId(), export.getSerNo(), export.getCreatedBy(), "Pending",
				export.getDgdcStatus(), null);
		export_History.SetHistoryDate();
		export_HistoryRepository.save(export_History);

		return sbTransactionService.createSBTransaction(export);
	}

	@PostMapping("/submit")
	public Export createSBTransactionSubmit(@RequestBody Export sbTransaction) {
		sbTransaction.setAppovedate();
		sbTransaction.setStatus("A");

//		Export_History export_History = new Export_History(sbTransaction.getCompanyId(), sbTransaction.getBranchId(),
//				sbTransaction.getSbNo(), sbTransaction.getSbRequestId(), sbTransaction.getSerNo(),
//				sbTransaction.getCreatedBy(), "panding . .", sbTransaction.getDgdcStatus(), null);
//		export_History.SetHistoryDate();
//		export_HistoryRepository.saveAndFlush(export_History);

		return sbTransactionService.createSBTransaction(sbTransaction);

	}

	@PostMapping("/delete")
	public Export ToSetStatusD(@RequestBody Export sbTransaction) { // Implement the logic to

		sbTransaction.setStatus("D");
		System.out.println(sbTransaction);
		return sbTransactionService.createSBTransaction(sbTransaction);
	}

	@GetMapping(value = "/listSBTransaction/{cid}/{bid}")
	public List<Export> getListOfSBTransaction(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {

		return exportRepository.findAllData(cid, bid);
	}

	@GetMapping(value = "/listSBTransaction1/{cid}/{bid}")
	public List<Export> getListOfSBTransaction1(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {

		return exportRepository.findAllData1(cid, bid);
	}

	@GetMapping("/filtered/{company_id}/{branch_id}")
	public List<Export> getFilteredExports(@PathVariable("company_id") String companyId,
			@PathVariable("branch_id") String branchId) {
		return sbTransactionService.getExportsByCompanyAndBranch(companyId, branchId);
	}

	@GetMapping("/getExportHistoryList/{company_id}/{branch_id}/{sbno}/{sbRId}/{ser}")
	public List<Export_History> getFilteredExportsHistory(@PathVariable("company_id") String companyId,
			@PathVariable("branch_id") String branchId, @PathVariable("sbno") String sbno,
			@PathVariable("sbRId") String sbRId, @PathVariable("ser") String ser) {
		return export_HistoryRepository.findRecordsByCriteria1(branchId, companyId, sbno, sbRId);
	}

	@DeleteMapping("/{sbTransId}")
	public void deleteSBTransaction(@PathVariable String sbTransId) {
		sbTransactionService.deleteSBTransaction(sbTransId);
	}

	@PostMapping(value = "/updateExportC_A/{cartingAgent}/{respectiveId}")
	public String getUpdateExportC_A(@RequestBody List<Export> items, @PathVariable("cartingAgent") String cartingAgent,
			@PathVariable("respectiveId") String respectiveId) {

		for (Export export : items) {
			export.setPartyRepresentativeId(respectiveId);
			export.setCartingAgent(cartingAgent);

			Export_History export_History = new Export_History(export.getCompanyId(), export.getBranchId(),
					export.getSbNo(), export.getSbRequestId(), export.getSerNo(), export.getCreatedBy(),
					export.getDgdcStatus(), "Handed over To Carting Agent", null);

			export_History.SetHistoryDate();

			export_HistoryRepository.saveAndFlush(export_History);

			export.setDgdcStatus("Hand Over To Carting Agent");

		}
		exportRepository.saveAll(items);

		return "Export Update Successfully.";
	}

	@GetMapping("/getExportsBySbDate/{companyid}/{branchId}/{SbDate}/{dgdcStatus}")
	public List<Export> getExportsByFormattedSbDate(@PathVariable("companyid") String companyId,
			@PathVariable("branchId") String branchId,
			@PathVariable("SbDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sbdate,
			@PathVariable("dgdcStatus") String status) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(sbdate);
		List<Export> exports = sbTransactionService.findRecordsByFormattedSbDate(formattedDate, companyId, branchId,
				status);
		return exports;
	}

	@GetMapping(value = "/findCompanyname/{cid}")
	public String findCompanyname(@PathVariable("cid") String param) {
		Company company = companyRepo.findByCompany_Id(param);

		return company.getCompany_name();
	}

	@GetMapping(value = "/findBranchName/{cid}/{bid}")
	public String findBranchName(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		Branch branch = branchRepo.findByBranchIdWithCompanyId(cid, bid);
		return branch.getBranchName();
	}// exportcontroller

	@GetMapping("/getalldataa")
	public List<Export> getalldata() {
		return exportRepository.findAll();
	}

	@PostMapping(value = "/readgateinurl")
	public String getlinkforgateIn(@RequestBody ReadURL readURL) throws IOException {
		String s = readURL.getLink();
		URL url = new URL(s);
		System.out.println(s);
		int timeoutMillis = 50000; // 5 seconds
		Document document = Jsoup.parse(url, timeoutMillis);
		Map<String, String> hashMap = new LinkedHashMap<>();

		Elements labels = document.select(".Label, .LabelHeader, .SubHeader");

		String currentKey = "";
		String Demo = "";
		String key = "";

		for (Element label : labels) {

			if (label.hasClass("LabelHeader") || label.hasClass("SubHeader")) {
				key = "";
				currentKey = label.text();
				currentKey = currentKey.toLowerCase();

				for (int i = 0; i < currentKey.length(); i++) {
					if ((int) currentKey.charAt(i) >= 97 && (int) currentKey.charAt(i) <= 122) {
						key += currentKey.charAt(i);
					}
				}

				if (label.text().equals("Consignment Details:")) {
					Demo = label.text();
					currentKey = Demo;
					hashMap.put(key, "");
				}

			} else {
				String value = label.text();

				hashMap.put(key, value);
			}
		}

		List<Export> export2 = exportRepository.findAllData(readURL.getCompanyId(), readURL.getBranchId());
		String sbnodate1 = hashMap.get("sbnodate");
		String[] parts2 = sbnodate1.split(",");
		String sbno1 = parts2[0].trim();
		boolean found = false;
		for (Export export : export2) {
			if (export.getSbRequestId().equals(hashMap.get("requestid")) && export.getSbNo().equals(sbno1)) {
				found = true;
				break;
			}
		}

		if (found) {

			return "found";
		}

		Party party = partyrepo.findbyentityid(readURL.getCompanyId(), readURL.getBranchId(),
				hashMap.get("entityid"));

		Export export = new Export();
		export.setSerNo("");
        
		export.setCompanyId(readURL.getCompanyId());
		export.setSbRequestId(hashMap.get("requestid"));
		export.setBranchId(readURL.getBranchId());
		export.setGateInId(processNextIdService.autoIncrementGateInId());
		export.setGateInDate(new Date());
		export.setNoc(0);
		export.setDgdc_cargo_in_scan(0);
		export.setDgdc_cargo_out_scan(0);
		export.setDgdc_seepz_in_scan(0);
		export.setDgdc_seepz_out_scan(0);
		String sbnodate = hashMap.get("sbnodate");
		String[] parts = sbnodate.split(",");
		String sbno = parts[0].trim();
		export.setSbNo(sbno);

		String inputDateStr = parts[1].trim();

		SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {

			Date inputDate = inputDateFormat.parse(inputDateStr);
			export.setSbDate(inputDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		String inputString = hashMap.get("customhouseagentnamecode");
		  String s1 = "";
	        String s2 = "";

	        if (inputString.contains(" - ")) {
	            String[] parts1 = inputString.split(" - ", 2);  // Split into two parts at the first " - "
	            s1 = parts1[0];
	            s2 = parts1[1];
	        	export.setChaName(hashMap.get(s1));
	    		export.setChaNo(hashMap.get(s2));
	        } else {
	            s1 = inputString;
	        	export.setChaName(s1);
	    		export.setChaNo("");
	        }
		
	//	DefaultPartyDetails defaultparty = defaultrepo.getdatabyuser_id(readURL.getCompanyId(),readURL.getBranchId(),party.getPartyId());
	        DefaultPartyDetails defaultparty = defaultrepo.getdatabyuser_id(readURL.getCompanyId(), readURL.getBranchId(), party.getPartyId());
			

			if (defaultparty == null || (defaultparty.getExpCHA() == null && defaultparty.getExpConsole() == null)) {
			    // Handle the case when defaultparty is null or both expCHA and expConsole are null or empty
			    export.setChaNo("EU0021");
			    export.setChaName("SELF");
			    export.setConsoleAgent("EU0009");
			} else if (defaultparty.getExpCHA() == null) {
			    // Handle the case when expCHA is null or empty
			    export.setChaNo("EU0021");
			    export.setChaName("SELF");
			    export.setConsoleAgent(defaultparty.getExpConsole());
			} else if (defaultparty.getExpConsole() == null) {
			    // Handle the case when expConsole is null or empty
			    ExternalParty external = externalPartyRepository.getalldatabyid(readURL.getCompanyId(), readURL.getBranchId(), defaultparty.getExpCHA());
			    export.setChaNo(defaultparty.getExpCHA());
			    export.setChaName(external.getUserName());
			    export.setConsoleAgent("EU0009");
			} else {
			    // Handle the case when both expCHA and expConsole have values
			    ExternalParty external = externalPartyRepository.getalldatabyid(readURL.getCompanyId(), readURL.getBranchId(), defaultparty.getExpCHA());
			    export.setChaNo(defaultparty.getExpCHA());
			    export.setChaName(external.getUserName());
			    export.setConsoleAgent(defaultparty.getExpConsole());
			}
	
		export.setCountryOfDestination(hashMap.get("countryofdestination"));
		export.setDescriptionOfGoods(hashMap.get("requestdetails"));
		export.setEntityId(hashMap.get("entityid"));
		try {
			double doubleValue = Double.parseDouble(hashMap.get("netrealisablevalueinrs"));
			export.setFobValueInINR(doubleValue);

		} catch (NumberFormatException e) {
			System.err.println("Error: Unable to convert the string to a double.");
			e.printStackTrace();
		}

		String cargoDetails = hashMap.get("cargodetails");
		String[] parts1 = cargoDetails.split(",");

		// Create two String variables to store the split cargo details
		String cargoDetail1 = parts1[0].trim();
		String cargoDetail2 = parts1[1].trim();

		Pattern pattern = Pattern.compile("Weight:\\s*(\\d+\\.\\d+)\\s*(\\w+)");
		Matcher matcher = pattern.matcher(cargoDetail1);

		if (matcher.find()) {
			String value1 = matcher.group(1); // This will be "1700.0000"
			String value2 = matcher.group(2); // This will be "GRAMS"
			export.setGrossWeight(Double.valueOf(value1));
			export.setUomGrossWeight(value2);
		}

		cargoDetail2 = cargoDetail2.replace("Packets: ", "");

		String[] subparts = cargoDetail2.split("\\s+");

		if (subparts.length >= 2) {
			String value1 = subparts[0].trim(); // This will be "1"
			String value2 = subparts[1].trim(); // This will be "PACKAGES"
			export.setNoOfPackages(Integer.parseInt(value1));
			export.setUomOfPackages(value2);
		}

		export.setIecCode(hashMap.get("importexportcode"));
		export.setNameOfExporter(party.getPartyId());
		export.setIecCode(party.getIecNo());
		export.setNsdlStatus(hashMap.get("requeststatus"));
		export.setPortOfDestination(hashMap.get("portofdestination"));
		export.setStatus("A");
		export.setCreatedBy(readURL.getCreatedBy());
		export.setCreatedDate(new Date());
		export.setDgdcStatus("Entry at DGDC SEEPZ Gate");
		export.setQrcodeUrl(readURL.getLink());
		export.setHoldStatus("N");
		export.setGatePassStatus("N");
		export.setScStatus("N");
		export.setPcStatus("N");
		export.setHpStatus("N");
		export.setCancelStatus("N");
		BigDecimal big = new BigDecimal("0.0");
		export.setImposePenaltyAmount(0);

		this.exportRepository.save(export);

		Export_History export_History = new Export_History(export.getCompanyId(), export.getBranchId(),
				export.getSbNo(), export.getSbRequestId(), export.getSerNo(), export.getCreatedBy(), "Pending",
				export.getDgdcStatus(), null);
		export_History.SetHistoryDate();
		export_HistoryRepository.save(export_History);

		   ScannedParcels scanparcels = new ScannedParcels();
           scanparcels.setBranchId(export.getBranchId());
           scanparcels.setCompanyId(export.getCompanyId());
           scanparcels.setDoc_Ref_No(export.getSbNo());
           scanparcels.setNop(export.getNoOfPackages());
          // scanparcels.setPacknum(packnum);
           scanparcels.setGateiout(new Date());
           scanparcels.setStatus("N");
           scanparcels.setParcel_type("");
           scanparcels.setParty(export.getNameOfExporter());
           scanparcels.setSrNo("");
           scanparcels.setTypeOfTransaction("Export-in");
           scanparcels.setScan_parcel_type("seepz");
           scannedparcelsrepo.save(scanparcels);
		return "success";
	}

	@GetMapping("/alldata/{cid}/{bid}")
	public List<Export> getalldata(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		return exportRepository.findAllData(cid, bid);
	}

	@PostMapping("/existingdata/{id}")
	public ResponseEntity<Map<String, String>> updateExport(@RequestBody ReadURL readURL, @PathVariable("id") String id)
			throws IOException {
		String s = readURL.getLink();
		URL url = new URL(s);
		System.out.println(s);
		int timeoutMillis = 50000; // 5 seconds
		Document document = Jsoup.parse(url, timeoutMillis);
		Map<String, String> hashMap = new LinkedHashMap<>();

		Elements labels = document.select(".Label, .LabelHeader, .SubHeader");

		String currentKey = "";
		String Demo = "";
		String key = "";

		for (Element label : labels) {

			if (label.hasClass("LabelHeader") || label.hasClass("SubHeader")) {
				key = "";
				currentKey = label.text();
				currentKey = currentKey.toLowerCase();

				for (int i = 0; i < currentKey.length(); i++) {
					if ((int) currentKey.charAt(i) >= 97 && (int) currentKey.charAt(i) <= 122) {
						key += currentKey.charAt(i);
					}
				}

				if (label.text().equals("Consignment Details:")) {
					Demo = label.text();
					currentKey = Demo;
					hashMap.put(key, "");
				}

			} else {
				String value = label.text();

				hashMap.put(key, value);
			}
		}

		String sbnodate = hashMap.get("sbnodate");
		String[] parts = sbnodate.split(",");
		String sbno = parts[0].trim();

		Export export = exportRepository.findBySBNoandSbreqid(readURL.getCompanyId(), readURL.getBranchId(),
				hashMap.get("requestid"), sbno);
		Export_History exphis = export_HistoryRepository.findSingledata(export.getCompanyId(), export.getBranchId(),
				export.getSbNo(), export.getSbRequestId(), export.getSerNo());
		export.setDgdcStatus("Handed over to DGDC SEEPZ");

		exportRepository.save(export);

		Export_History export_History = new Export_History(export.getCompanyId(), export.getBranchId(),
				export.getSbNo(), export.getSbRequestId(), export.getSerNo(), id, exphis.getNewStatus(),
				export.getDgdcStatus(), null);
		export_History.SetHistoryDate();
		export_HistoryRepository.save(export_History);

		return new ResponseEntity<>(hashMap, HttpStatus.OK);
	}

	@GetMapping("/holdStatus/{cid}/{bid}/{reqid}/{sbno}")
	public Export updateHoldStatus(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("reqid") String reqid, @PathVariable("sbno") String sbno) {
		Export export = exportRepository.findBySBNoandSbreqid(cid, bid, reqid, sbno);
		export.setHoldStatus("Y");
		return exportRepository.save(export);
	}

	@GetMapping("/unholdStatus/{cid}/{bid}/{reqid}/{sbno}")
	public Export updateUnHoldStatus(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("reqid") String reqid, @PathVariable("sbno") String sbno) {
		Export export = exportRepository.findBySBNoandSbreqid(cid, bid, reqid, sbno);
		export.setHoldStatus("N");
		return exportRepository.save(export);
	}

	@GetMapping("/specialStatus/{cid}/{bid}/{reqid}/{sbno}")
	public Export updateSpecialCartingStatus(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("reqid") String reqid, @PathVariable("sbno") String sbno) {
		Export export = exportRepository.findBySBNoandSbreqid(cid, bid, reqid, sbno);
		export.setScStatus("Y");
		return exportRepository.save(export);
	}

	@GetMapping("/cancelSpecialStatus/{cid}/{bid}/{reqid}/{sbno}")
	public Export updateCancelSpecialCartingStatus(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("reqid") String reqid, @PathVariable("sbno") String sbno) {
		Export export = exportRepository.findBySBNoandSbreqid(cid, bid, reqid, sbno);
		export.setScStatus("N");
		return exportRepository.save(export);
	}

	@GetMapping("/pcStatus/{cid}/{bid}/{reqid}/{sbno}")
	public Export updatePCStatus(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("reqid") String reqid, @PathVariable("sbno") String sbno) {
		Export export = exportRepository.findBySBNoandSbreqid(cid, bid, reqid, sbno);
		if(!export.getSerNo().startsWith("EX")) {
			export.setSerNo(processNextIdService.autoIncrementSIRExportId());
			export.setSerDate(new Date());
			export.setDgdcStatus("Handed over to DGDC SEEPZ");
		}
		export.setPcStatus("Y");
		return exportRepository.save(export);
	}

	@GetMapping("/cancelPCStatus/{cid}/{bid}/{reqid}/{sbno}")
	public Export updateCancelPCStatus(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("reqid") String reqid, @PathVariable("sbno") String sbno) {
		Export export = exportRepository.findBySBNoandSbreqid(cid, bid, reqid, sbno);
		export.setPcStatus("N");
		return exportRepository.save(export);
	}

	@PostMapping("/penalty")
	public Export savePenalty(@RequestBody Export export) {
		return exportRepository.save(export);
	}

	@PostMapping("/getNSDLStatus")
	public Export updateNSDLStatus(@RequestBody Export export) throws IOException {
		String s = export.getQrcodeUrl();
		URL url = new URL(s);
		System.out.println(s);
		int timeoutMillis = 50000; // 5 seconds
		Document document = Jsoup.parse(url, timeoutMillis);
		Map<String, String> hashMap = new LinkedHashMap<>();

		Elements labels = document.select(".Label, .LabelHeader, .SubHeader");

		String currentKey = "";
		String Demo = "";
		String key = "";

		for (Element label : labels) {

			if (label.hasClass("LabelHeader") || label.hasClass("SubHeader")) {
				key = "";
				currentKey = label.text();
				currentKey = currentKey.toLowerCase();

				for (int i = 0; i < currentKey.length(); i++) {
					if ((int) currentKey.charAt(i) >= 97 && (int) currentKey.charAt(i) <= 122) {
						key += currentKey.charAt(i);
					}
				}

				if (label.text().equals("Consignment Details:")) {
					Demo = label.text();
					currentKey = Demo;
					hashMap.put(key, "");
				}

			} else {
				String value = label.text();

				hashMap.put(key, value);
			}
		}

		export.setNsdlStatus(hashMap.get("requeststatus"));
		return exportRepository.save(export);
	}

	@PostMapping("/override/{nsdl}/{reason}/{cid}/{bid}/{sbid}/{sbno}")
	public void changeDeliveryUpdate(@PathVariable("nsdl") String nsdl, @PathVariable("reason") String reason,
			@PathVariable("cid") String cid, @PathVariable("bid") String bid, @PathVariable("sbid") String sbid,
			@PathVariable("sbno") String sbno, @RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {

		// Save the file to a folder (you need to specify the folder path)
		String folderPath = FileUploadProperties.getPath(); // Update with your folder path
		String fileName = file.getOriginalFilename();
		String filePath = folderPath + "/" + fileName;

		// Use the provided logic to generate a unique file name
		String uniqueFileName = generateUniqueFileName(folderPath, fileName);

		// Construct the full path for the unique file
		String uniqueFilePath = folderPath + "/" + uniqueFileName;
		file.transferTo(new File(uniqueFilePath));

		List<Export> export = exportRepository.findAllBySBNoandSbreqid(cid, bid, sbid, sbno);

		// Update the repository with the unique file path
		this.exportRepository.updateOverride(nsdl, reason, uniqueFilePath, cid, bid, sbid, sbno);
	}

	private String generateUniqueFileName(String folderPath, String originalFileName) {
		int suffix = 1;
		String uniqueFileName = originalFileName;

		while (Files.exists(Paths.get(folderPath + "/" + uniqueFileName))) {
			int dotIndex = originalFileName.lastIndexOf('.');
			String nameWithoutExtension = dotIndex != -1 ? originalFileName.substring(0, dotIndex) : originalFileName;
			String fileExtension = dotIndex != -1 ? originalFileName.substring(dotIndex) : "";
			uniqueFileName = nameWithoutExtension + "_" + suffix + fileExtension;
			suffix++;
		}

		return uniqueFileName;
	}

	@GetMapping("/allheavydata")
	public List<ExportHeavyPackage> alldata() {
		return eexportHeavyRepo.findAll();
	}

	@PostMapping("/saveheavydata")
	public ResponseEntity<String> saveHeavydata(@RequestBody ExportHeavyPackage exportheavy) {
		// BigDecimal big = new BigDecimal(wt);

		Export existexport = exportRepository.findBySBNoandSbreqid(exportheavy.getCompanyId(),
				exportheavy.getBranchId(), exportheavy.getSbRequestId(), exportheavy.getSbNo());
		existexport.setHpStatus("Y");
		exportRepository.save(existexport);
		this.eexportHeavyRepo.save(exportheavy);
		return new ResponseEntity<>("Data inserted successfully", HttpStatus.CREATED);

	}

	@GetMapping("/allheavydata/{cid}/{bid}/{reqid}/{sbno}")
	public List<ExportHeavyPackage> getallheavydata(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("reqid") String reqid, @PathVariable("sbno") String sbno) {
		return this.eexportHeavyRepo.findalldata(cid, bid, reqid, sbno);
	}

	@PostMapping("/deletedata")
	public ResponseEntity<String> deleteByPackNo(@RequestBody ExportHeavyPackage exportHeavy) {
		ExportHeavyPackage existingHeavy = eexportHeavyRepo.finddata(exportHeavy.getCompanyId(),
				exportHeavy.getBranchId(), exportHeavy.getSbRequestId(), exportHeavy.getSbNo(),
				exportHeavy.getPackageNumber());

		if (existingHeavy == null) {
			// If the ExportHeavyPackage object doesn't exist, return a 404 Not Found
			// response
			return new ResponseEntity<>("ExportHeavyPackage not found", HttpStatus.NOT_FOUND);
		}

		// Attempt to delete the ExportHeavyPackage object
		try {
			eexportHeavyRepo.delete(existingHeavy);
		} catch (Exception e) {
			// Handle any exceptions that occur during deletion
			return new ResponseEntity<>("Error deleting data", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Check if there are any remaining heavy packages with the same SB data
		List<ExportHeavyPackage> remainingHeavyPackages = eexportHeavyRepo.findalldata(exportHeavy.getCompanyId(),
				exportHeavy.getBranchId(), exportHeavy.getSbRequestId(), exportHeavy.getSbNo());

		if (remainingHeavyPackages.isEmpty()) {
			// If no remaining heavy packages, update the Export object status
			Export export = exportRepository.findBySBNoandSbreqid(exportHeavy.getCompanyId(), exportHeavy.getBranchId(),
					exportHeavy.getSbRequestId(), exportHeavy.getSbNo());
			export.setHpStatus("N");
			exportRepository.save(export);
		}

		return new ResponseEntity<>("Data deleted successfully", HttpStatus.OK);
	}

	@PostMapping("/cancelparcel")
	public void cancelparcel(@RequestBody Export export) {
		Export_History exphis = export_HistoryRepository.findSingledata(export.getCompanyId(), export.getBranchId(),
				export.getSbNo(), export.getSbRequestId(), export.getSerNo());
		export.setCancelStatus("Y");
		export.setDgdcStatus("Cancelled");

		exportRepository.save(export);

		Export_History export_History = new Export_History(export.getCompanyId(), export.getBranchId(),
				export.getSbNo(), export.getSbRequestId(), export.getSerNo(), export.getCreatedBy(),
				exphis.getNewStatus(), export.getDgdcStatus(), null);
		export_History.SetHistoryDate();
		export_HistoryRepository.save(export_History);

	}

	@PostMapping("/removecancelparcel")
	public void removecancelparcel(@RequestBody Export export) {
		Export_History exphis = export_HistoryRepository.findSingledata(export.getCompanyId(), export.getBranchId(),
				export.getSbNo(), export.getSbRequestId(), export.getSerNo());
		export.setCancelStatus("N");
		export.setDgdcStatus(exphis.getOldStatus());
		exportRepository.save(export);
		Export_History export_History = new Export_History(export.getCompanyId(), export.getBranchId(),
				export.getSbNo(), export.getSbRequestId(), export.getSerNo(), export.getCreatedBy(),
				exphis.getNewStatus(), export.getDgdcStatus(), null);
		export_History.SetHistoryDate();
		export_HistoryRepository.save(export_History);
	}

	@PostMapping("/updatecancelparcel")
	public void updatecancelparcel(@RequestBody Export export) {

		export.setCancelStatus("Y");
		export.setDgdcStatus("Cancelled");

		exportRepository.save(export);

	}

	@GetMapping("/receivecarting/{cid}/{bid}/{carting}/{represent}")
	public List<Export> getalldataforreceivecartingagent(@PathVariable("cid") String cid,
			@PathVariable("bid") String bid, @PathVariable("carting") String carting,
			@PathVariable("represent") String represent) {
		return exportRepository.getalldataforreceivecarting(cid, bid, carting, represent);
	}

	@GetMapping("/byairline/{cid}/{bid}/{air}")
	public List<Export> getalldatabyairline(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("air") String air) {
		return exportRepository.getdataByairline(cid, bid, air);
	}

	@PostMapping("/handoverairline/{id}")
	public void updatedataforhandovertoairline(@RequestBody List<Export> export, @PathVariable("id") String id) {
		List<Export_History> exportHistoryList = new ArrayList<>();
		for (Export exp : export) {
			exp.setDgdcStatus("Handed Over to Airline");
			exportRepository.save(exp);

			List<ScannedParcels> scan = scannedparcelsrepo.getbydocrefid(exp.getCompanyId(), exp.getBranchId(),
					exp.getSbNo());
			for (ScannedParcels scan1 : scan) {
				scan1.setStatus("Y");
				scannedparcelsrepo.save(scan1);
			}

			Export_History export_History = new Export_History();
			export_History.setCompanyId(exp.getCompanyId());
			export_History.setBranchId(exp.getBranchId());
			export_History.setNewStatus("Handed Over to Airline");
			export_History.setOldStatus("Handed over to DGDC Cargo");
			export_History.setSbNo(exp.getSbNo());
			export_History.setSbRequestId(exp.getSbRequestId());
			export_History.setserNo(exp.getSerNo());
			export_History.setTransport_Date(new Date());
			export_History.setUpdatedBy(id);
			export_History.SetHistoryDate();
			exportHistoryList.add(export_History);
		}
		this.export_HistoryRepository.saveAll(exportHistoryList);
	}

	@GetMapping("/getdataforhandover/{cid}/{bid}")
	public List<Export> getdataforHandover(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		return exportRepository.getalldataforhandover(cid, bid);
	}

	@GetMapping("/getdataforedit/{cid}/{bid}/{reqid}/{sbno}")
	public Export findbysbidandsbreq(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("reqid") String reqid, @PathVariable("sbno") String sbno) {
		return exportRepository.findBySBNoandSbreqid(cid, bid, reqid, sbno);
	}

	@PostMapping("/editexport")
	public Export saveedit(@RequestBody Export export) {
		return exportRepository.save(export);
	}

	@GetMapping("/generateotp/{cid}/{bid}/{rid}/{mobile}")
	public String generateCartingOTP(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("rid") String rid, @PathVariable("mobile") String mobile) {
		String otp = generateOTP();
		RepresentParty represent = representReo.checkOTP(cid, bid, rid, mobile);
		represent.setOtp(otp);
		this.representReo.save(represent);

		try {
			String apiKey = "apikey=" + URLEncoder.encode("N2E2ZjU4NmU1OTY5Njg2YjczNjI3OTMxNjg3MjQ4NjM=", "UTF-8");
			String message = "Dear Sir/Madam, Please validate your identity in DGDC E-Custodian with OTP " + otp + " .";
			String sender = "sender=" + URLEncoder.encode("DGDCSZ", "UTF-8");
			String numbers = "numbers=" + URLEncoder.encode("91" + mobile, "UTF-8");

			// Send data
			String data = "https://api.textlocal.in/send/?" + apiKey + "&" + numbers + "&message="
					+ URLEncoder.encode(message, "UTF-8") + "&" + sender;
			URL url = new URL(data);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuilder sResult = new StringBuilder();
			while ((line = rd.readLine()) != null) {
				sResult.append(line).append(" ");
			}
			rd.close();

			return sResult.toString();
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}

	private String generateOTP() {
		Random random = new Random();
		int otp = random.nextInt(900000) + 100000; // Generates a random number between 1000 and 9999
		return String.valueOf(otp);
	}

	@GetMapping("/findExportAllData")
	public List<Export> findExportSubData(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate,

			@RequestParam("cartingAgent") String cartingAgent) {
		return exportRepository.findExportAllData(companyId, branchId, startDate, endDate, cartingAgent);
	}

	@GetMapping("/findExportData")
	public List<Export> findExportSubData(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate

	) {
		return exportRepository.findExportData(companyId, branchId, startDate, endDate);
	}

	@GetMapping("/findPartyName/{companyId}/{branchId}/{partyId}")
	public String findPartyNameByKeys(@PathVariable String companyId, @PathVariable String branchId,
			@PathVariable String partyId) {
		String partyName = partyrepo.findPartyNameByKeys(companyId, branchId, partyId);

		if (partyName != null) {
			return partyName;
		} else {
			// Handle the case where partyName is not found
			return "Party Name Not Found";
		}
	}

	@GetMapping("/findConsoleName/{companyId}/{branchId}/{externaluserId}")
	public String findConsoleName(@PathVariable String companyId, @PathVariable String branchId,
			@PathVariable String externaluserId) {
		String user_name = externalPartyRepository.findUserNameByKeys(companyId, branchId, externaluserId);

		if (user_name != null) {
			return user_name;
		} else {
			// Handle the case where partyName is not found
			return "User Name Not Found";
		}
	}

	@PostMapping("/getdataforairline")
	public ResponseEntity<Export> getdataforairline(@RequestBody ReadURL readURL) throws IOException {
		String s = readURL.getLink();
		URL url = new URL(s);
		System.out.println(s);
		int timeoutMillis = 50000; // 5 seconds
		Document document = Jsoup.parse(url, timeoutMillis);
		Map<String, String> hashMap = new LinkedHashMap<>();

		Elements labels = document.select(".Label, .LabelHeader, .SubHeader");

		String currentKey = "";
		String Demo = "";
		String key = "";

		for (Element label : labels) {

			if (label.hasClass("LabelHeader") || label.hasClass("SubHeader")) {
				key = "";
				currentKey = label.text();
				currentKey = currentKey.toLowerCase();

				for (int i = 0; i < currentKey.length(); i++) {
					if ((int) currentKey.charAt(i) >= 97 && (int) currentKey.charAt(i) <= 122) {
						key += currentKey.charAt(i);
					}
				}

				if (label.text().equals("Consignment Details:")) {
					Demo = label.text();
					currentKey = Demo;
					hashMap.put(key, "");
				}

			} else {
				String value = label.text();

				hashMap.put(key, value);
			}
		}
		String sbnodate = hashMap.get("sbnodate");
		String[] parts = sbnodate.split(",");
		String sbno = parts[0].trim();

		Export export = exportRepository.findBySBNoandSbreqid(readURL.getCompanyId(), readURL.getBranchId(),
				hashMap.get("requestid"), sbno);
    export.setNsdlStatus(hashMap.get("requeststatus"));
    exportRepository.save(export);
		if (export != null) {
			return ResponseEntity.ok(export); // Return the export data if found.
		} else {
			// Export data not found, return a 401 Unauthorized response.
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@GetMapping("/download/{cid}/{bid}/{reqid}/{sbno}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("sbno") String sbno, @PathVariable("reqid") String reqid) throws IOException {
		// Retrieve the image path from the database based on imageId
		Export exp = exportRepository.findBySBNoandSbreqid(cid, bid, reqid, sbno);
		String imagePath = exp.getOverrideDocument();

		if (imagePath != null) {

			MediaType mediaType = MediaType.IMAGE_JPEG; // Default to JPEG

			if (imagePath.endsWith(".pdf")) {
				mediaType = MediaType.APPLICATION_PDF;
			} else if (imagePath.endsWith(".png")) {
				mediaType = MediaType.IMAGE_PNG;
			} else if (imagePath.endsWith(".jpg") || imagePath.endsWith(".jpeg")) {
				mediaType = MediaType.IMAGE_JPEG;
			}
			// Load the image file as a byte array
			byte[] imageBytes = imageService.loadImage(imagePath);

			// Determine the content type based on the image file type (e.g., image/jpeg)
			HttpHeaders headers = new HttpHeaders();
			// Adjust as needed
			headers.setContentType(mediaType);

			return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/saveairway/{id}")
	public void saveAirwaydata(@RequestBody Export exp, @PathVariable("id") String id) {
	 if(!exp.getSerNo().startsWith("EX")) {
		 String ser = processNextIdService.autoIncrementSIRExportId();
			exp.setSerNo(ser);
			exp.setSerDate(new Date());
			exp.setDgdcStatus("Handed over to DGDC SEEPZ");
			
			 Export_History export_History = new Export_History();
				export_History.setCompanyId(exp.getCompanyId());
				export_History.setBranchId(exp.getBranchId());
				export_History.setNewStatus("Handed over to DGDC SEEPZ");
				export_History.setOldStatus("Entry at DGDC SEEPZ Gate");
				export_History.setSbNo(exp.getSbNo());
				export_History.setSbRequestId(exp.getSbRequestId());
				export_History.setserNo(ser);
				export_History.setTransport_Date(new Date());
				export_History.setUpdatedBy(id);
				export_History.SetHistoryDate();
				export_HistoryRepository.save(export_History);
	 }

		
		 exportRepository.save(exp);
	
		
		 
		
	}
//
//	@PostMapping("/saveairway/{id}")
//	public void saveAirwaydata(@RequestBody Export exp, @PathVariable("id") String id) {
//	 if(!exp.getSerNo().startsWith("EX")) {
//			exp.setSerNo(processNextIdService.autoIncrementSIRExportId());
//			exp.setSerDate(new Date());
//			exp.setDgdcStatus("Handed over to DGDC SEEPZ");
//	 }
//
//		
//		 exportRepository.save(exp);
//	
//		 Export_History export_History = new Export_History();
//			export_History.setCompanyId(exp.getCompanyId());
//			export_History.setBranchId(exp.getBranchId());
//			export_History.setNewStatus("Handed over to DGDC SEEPZ");
//			export_History.setOldStatus("Entry at DGDC SEEPZ Gate");
//			export_History.setSbNo(exp.getSbNo());
//			export_History.setSbRequestId(exp.getSbRequestId());
//			export_History.setserNo(exp.getSerNo());
//			export_History.setTransport_Date(new Date());
//			export_History.setUpdatedBy(id);
//			export_History.SetHistoryDate();
//			export_HistoryRepository.save(export_History);
//		 
//		
//	}
	
	
	@GetMapping("/serDate")
	public List<String> getAllbySerdate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("cid") String cid, @RequestParam("bid") String bid) {

		return exportRepository.findByTp(date, cid, bid);
	}

	@GetMapping("/exportDataBySerDateAndAirlineCode")
	public List<Export> getExportsBySerDateAndAirlineCode(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("serDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date serDate,
			@RequestParam("airlineCode") String airlineCode) {
		return exportRepository.findBySerDateAndAirlineCode(companyId, branchId, serDate, airlineCode);
	}

	@PostMapping("/redeposite/{id}")
	public void updatedataforRedeposite(@RequestBody List<Export> export, @PathVariable("id") String id) {
		List<Export_History> exportHistoryList = new ArrayList<>();
		for (Export exp : export) {
			exp.setDgdcStatus("Handed over to DGDC Cargo");
			exportRepository.save(exp);

			Export_History export_History = new Export_History();
			export_History.setCompanyId(exp.getCompanyId());
			export_History.setBranchId(exp.getBranchId());
			export_History.setNewStatus("Handed over to DGDC Cargo");
			export_History.setOldStatus("Handed Over to Airline");
			export_History.setSbNo(exp.getSbNo());
			export_History.setSbRequestId(exp.getSbRequestId());
			export_History.setserNo(exp.getSerNo());
			export_History.setTransport_Date(new Date());
			export_History.setUpdatedBy(id);
			export_History.SetHistoryDate();
			
			exportHistoryList.add(export_History);
		}
		this.export_HistoryRepository.saveAll(exportHistoryList);
	}

	@GetMapping("/getdatabyserNoandDGDCStatus/{cid}/{bid}/{serNo}")
	public List<Export> getdatabyserNoandDGDCStatus(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("serNo") String serNo) {
		return exportRepository.findByCompanyIdAndBranchIdAndSerNoAndPcStatusAndDgdcStatusAndGatePassStatusNot(cid, bid,
				serNo, "Y", "Handed over to DGDC SEEPZ", "Handed over to Carting Agent", "Y");
	}

	@PostMapping("/printgatepass/{compId}/{branchId}")
	public ResponseEntity<String> generateGatePassPdf(@PathVariable("compId") String companyId,
			@PathVariable("branchId") String branchId, @RequestParam("serNoArray") List<String> serNoArray)
			throws Exception {
		try {
			// Create a Thymeleaf context

			Context context = new Context();
			List<Export> exportList = new ArrayList<>();
			List<String> names = new ArrayList<>();

			int totalNoPackages = 0;

			for (String serNo : serNoArray) {
				Export exp = exportRepository.findByCompanyIdAndBranchIdAndSerNo(companyId, branchId, serNo);

				if (exp != null) {

					Party cname = partyrepo.findByPartyId(exp.getNameOfExporter());

					exp.setCompanyId(cname.getPartyName());

					names.add(cname.getPartyName());
					System.out.println(cname.getPartyName());

					context.setVariable("officerName", exp.getpOName());
					context.setVariable("vehNo", exp.getGatePassVehicleNo());
					exportList.add(exp); // Add the found object to the list
					totalNoPackages = totalNoPackages + exp.getNoOfPackages();
				} else {

				}
			}
			System.out.println(totalNoPackages);

			String imagePath = "C:/DGDC/Java Code/CWMS_JAVA/src/main/resources/static/image/DGDC1.png";

			File imageFile = new File(imagePath);
			if (imageFile.exists()) {
				Image image = Image.getInstance(imagePath);
				image.scaleToFit(400, 300); // Adjust the dimensions as needed
// 				image.setAlignment(Element.ALIGN_CENTER);
// 				document.add(image);
				context.setVariable("dgdclogo", image);
			} else {
				System.out.println("img not here");// Handle the case where the image does not exist
			}

			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
			Date d1 = new Date();
			context.setVariable("Date", dateFormat2.format(d1));
			context.setVariable("companyId", companyId);
			context.setVariable("exportList", exportList);
			context.setVariable("totalNoPackages", totalNoPackages);
			context.setVariable("names", names);

			// Process the HTML template with dynamic values
			String htmlContent = templateEngine.process("GatePass", context);

			// Create an ITextRenderer instance
			ITextRenderer renderer = new ITextRenderer();

			// Set the PDF page size and margins
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();

			// Generate PDF content
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			renderer.createPDF(outputStream);

			// Get the PDF bytes
			byte[] pdfBytes = outputStream.toByteArray();

			// Encode the PDF content as Base64
			String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);

//			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//					.body(base64Pdf);
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(base64Pdf);

		} catch (Exception e) {
			// Handle exceptions appropriately
			return ResponseEntity.badRequest().body("Error generating PDF");
		}
	}

	// code for Generating GatePass
	@PostMapping("/generateGatePass/{compId}/{branchId}")
	public ResponseEntity<String> generateGatePass(@PathVariable("compId") String companyId,
			@PathVariable("branchId") String branchId, @RequestParam("serNoArray") List<String> serNoArray,
			@RequestParam("vehNo") String vehNo, @RequestParam("OfficerName") String officerName) throws Exception {
		try {

			for (String serNo : serNoArray) {
				Export exp = exportRepository.findByCompanyIdAndBranchIdAndSerNo(companyId, branchId, serNo);

				if (exp != null) {
					exp.setGatePassVehicleNo(vehNo);
					exp.setpOName(officerName); // Assuming 'pOName' was a typo and should be 'POName'
					exportRepository.save(exp);
				} else {
					// Handle the case where Export with the given serNo is not found
				}
			}

			System.out.println(serNoArray);
			System.out.println(serNoArray.get(0));
//				System.out.println(serNoArray.get(1));

			return ResponseEntity.ok().body("Gate Pass generated Successfully");
		} catch (Exception e) {
			// Handle exceptions appropriately
			return ResponseEntity.badRequest().body("Error generating Gate Pass");
		}
	}
	
	@GetMapping("/provisional/{cid}/{bid}/{sbreqid}/{sbno}/{id}")
	public String provisionalSER(@PathVariable("cid") String companyId,@PathVariable("bid") String branchId,@PathVariable("sbreqid") String sbreqid,@PathVariable("sbno") String sbno,@PathVariable("id") String id) {
		 Export export = exportRepository.findBySBNoandSbreqid(companyId,branchId,sbreqid,sbno);
		 export.setSerNo(processNextIdService.autoIncrementSIRExportId());
		 export.setSerDate(new Date());
		 export.setDgdcStatus("Handed over to DGDC SEEPZ");
		 exportRepository.save(export);
		 
		 Export_History export_History = new Export_History();
			export_History.setCompanyId(export.getCompanyId());
			export_History.setBranchId(export.getBranchId());
			export_History.setNewStatus("Handed over to DGDC SEEPZ");
			export_History.setOldStatus("Entry at DGDC SEEPZ Gate");
			export_History.setSbNo(export.getSbNo());
			export_History.setSbRequestId(export.getSbRequestId());
			export_History.setserNo(export.getSerNo());
			export_History.setTransport_Date(new Date());
			export_History.setUpdatedBy(id);
			export_History.SetHistoryDate();
			export_HistoryRepository.save(export_History);
			
			return "success";
	}
	
	//invoiceData Print 
	 @PostMapping("/Print1/{companyid}/{branchId}")
	    public ResponseEntity<?> generateInvoicePdf(
	            @PathVariable("companyid") String companyId,
	            @PathVariable("branchId") String branchId,	           
	            @RequestParam("formattedStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date StartDate,
	            @RequestParam("formattedEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date EndDate
          
	           
	            ) throws Exception {
		try {
			// Create a Thymeleaf context

			Context context = new Context();		
			List<String> names = new ArrayList<>();
			
			
			double totalInvoiceAmount = 0;
			double totalAmountPaid = 0;
		
			List<InvoiceMain> InvoiceList =	invoiceRepository.findAllInvoiceData(companyId, branchId, StartDate, EndDate);

			System.out.println("InvoiceList"+InvoiceList);
			for (InvoiceMain InvoiceObject : InvoiceList) {
				
				System.out.println("InvoiceObject"+InvoiceObject);
				System.out.println("InvoiceObject.getPartyId()"+InvoiceObject.getPartyId());
				
			    	Party partyName=partyrepo.findByPartyId(InvoiceObject.getPartyId());
			    	
			    	System.out.println("partyName"+partyName.getPartyName());
			    	
			    	
			    	names.add(partyName.getPartyName());

			    	totalInvoiceAmount= totalInvoiceAmount+InvoiceObject.getTotalInvoiceAmount();
			    	totalAmountPaid= totalAmountPaid+InvoiceObject.getClearedAmt();
			}
    
			double total = totalInvoiceAmount - totalAmountPaid;
			
			
			System.out.println(names +"names11");
			
			System.out.println(total+ "total1");
			System.out.println(totalInvoiceAmount + "totalInvoiceAmount11");
			System.out.println(totalAmountPaid + " totalAmountPaid11");
			
         String imagePath = "G:/Updated DGDC/Project-13-10-23/java/CWMS_JAVA/CWMS_JAVA/src/main/resources/static/image/DGDC1.png";

			File imageFile = new File(imagePath);
			if (imageFile.exists()) {
				Image image = Image.getInstance(imagePath);
				image.scaleToFit(400, 300); // Adjust the dimensions as needed
//				image.setAlignment(Element.ALIGN_CENTER);
//				document.add(image);
				context.setVariable("dgdclogo", image);
			} else {
				System.out.println("img not here");// Handle the case where the image does not exist
			}
			
			
		
			context.setVariable("StartDate", StartDate);
			context.setVariable("EndDate", EndDate);
			context.setVariable("companyId", companyId);
			context.setVariable("InvoiceList", InvoiceList);			
			context.setVariable("totalInvoiceAmount", totalInvoiceAmount);
			context.setVariable("totalAmountPaid", totalAmountPaid);
			context.setVariable("total", total);
			context.setVariable("names", names);

			System.out.println(StartDate+ "StartDate");
			System.out.println(EndDate+ "EndDate");
			System.out.println(companyId+ "companyId");

			// Process the HTML template with dynamic values
			String htmlContent = templateEngine.process("PartyBillPaymentsReport", context);

			// Create an ITextRenderer instance
			ITextRenderer renderer = new ITextRenderer();

			// Set the PDF page size and margins
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();

			// Generate PDF content
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			renderer.createPDF(outputStream);

			// Get the PDF bytes
			byte[] pdfBytes = outputStream.toByteArray();

			// Encode the PDF content as Base64
			String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);


			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				    .body(base64Pdf);

		} catch (Exception e) {
		    // Log the exception for debugging
		    e.printStackTrace();
		    return ResponseEntity.badRequest().body("Error generating PDF");
		}
	}
	 
	 
	 
	 
//		code for getting Search Data of Export Sub , Import and Import Sub
	 @PostMapping("/commongatepass/search/{compId}/{branchId}")
	 public ResponseEntity<?> getdatabyserNo(
	         @PathVariable("compId") String companyId,
	         @PathVariable("branchId") String branchId,		
	         @RequestParam("representativeId")  String representativeId	,
	            @RequestParam("paryCHAId")  String paryCHAId	,
	         @RequestParam("formattedStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date formattedStartDate) throws Exception {
	     try {
	         // No need to parse the formattedStartDate, it's already a Date object
	        
	         
	         List<ExportSub> expsub = exportSubRepository.findByCompanyAndBranchAndSerDate(companyId, branchId, formattedStartDate ,paryCHAId , representativeId);

	         List<ImportSub> impsub = importSubRepository.findByCompanyAndBranchAndDate2(companyId, branchId, formattedStartDate,paryCHAId , representativeId);
	        
	         List<Import> imp = importRepository.findByCompanyAndBranchAndDate1(companyId, branchId, formattedStartDate,paryCHAId , representativeId);

	         
	         System.out.println("Out Date " +formattedStartDate );
	         
//	         System.out.println(imp);
	         
	         
	         
	     // Combine all lists into a single list
	        List<Object> combinedList = new ArrayList<>();
	        combinedList.addAll(expsub);
	        combinedList.addAll(impsub);
	        combinedList.addAll(imp);
	    	 
	        
	       System.out.println("expsub: " + expsub);
	      System.out.println("imp: " + imp);


	         // Check if the list is not empty before returning
	         if (!combinedList.isEmpty()) {
	             return ResponseEntity.ok()
	                     .body(combinedList); // Return the list of Export objects
	         } else {
	             // Handle the case where no records were found
	             return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                     .body(combinedList);
	         }
	     } catch (Exception e) {
	         // Handle exceptions appropriately
	         return ResponseEntity.badRequest().body("Error combinedList ");
	     }
	 }

	 
	 
//	 code for print common gatepass
	 @PostMapping("/common/printgatepass/{compId}/{branchId}")
	    public ResponseEntity<String> generateCommonGatePassPdf(
	            @PathVariable("compId") String companyId,
	            @PathVariable("branchId") String branchId,		            
	            @RequestParam("printRecordArray")  List<String> searchData	,		           
	            @RequestParam("removedRecordArray")  List<String> removedRecordArray,
	            @RequestParam("representativeId")  String representativeId	,
	            @RequestParam("paryCHAId")  String paryCHAId	,
	            @RequestParam("typeName")  String typeName	,
	            @RequestParam("formattedStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date formattedStartDate

	            ) throws Exception {
		try {
			// Create a Thymeleaf context

			Context context = new Context();
			List<String> names1 = new ArrayList<>();
			List<String> names2 = new ArrayList<>();
			List<String> names3 = new ArrayList<>();
			List<ExportSub> FilteredExportSubList =new ArrayList<>();
		    List<ImportSub> FilteredImportSubList =new ArrayList<>();
		    List<Import> FilteredImportList =new ArrayList<>();
		    
		    
		    
		    System.out.println("Out Date " +formattedStartDate );
			
		   List<ExportSub> expsub = exportSubRepository.findByCompanyAndBranchAndSerDate3(companyId, branchId, formattedStartDate ,paryCHAId , representativeId);

	         List<ImportSub> impsub = importSubRepository.findByCompanyAndBranchAndDate5(companyId, branchId, formattedStartDate,paryCHAId , representativeId);
	        
	         List<Import> imp = importRepository.findByCompanyAndBranchAndDate4(companyId, branchId, formattedStartDate,paryCHAId , representativeId);

	         
//		       Party party1=partyrepo.findByPartyId(paryCHAId);
//		       String partyName= party1.getPartyName();
	         
	         
	         String partyName = "";
	         
		       Party party1=partyrepo.findByPartyId(paryCHAId);
		       
		       if(party1 != null)
		       {
		    	   partyName= party1.getPartyName(); 
		       }
		       else
		       {
		    	   ExternalParty singleRecord = ExternalParty_Service.getSingleRecord(companyId, branchId, paryCHAId);
		    	   partyName = singleRecord.getUserName();
		    	  			    	   
		       }
	         
		       
		      RepresentParty representParty = representPartyRepository.findByFNameAndLName(companyId, branchId, representativeId);
		       
		     String RName= representParty.getFirstName() +" "+ representParty.getLastName();
		       
	     // Combine all lists into a single list
	        List<Object> combinedList = new ArrayList<>();


	       for (ExportSub list1 : expsub) {
			    
				
				 // Check if either invoiceNo or hawb is not in the removedRecordArray
			    if (!removedRecordArray.contains(list1.getRequestId()) && searchData.contains(list1.getSerNo()) ) {
			    	
			    	Party party=partyrepo.findByPartyId(list1.getExporter());
			    	names1.add(party.getPartyName());
			    	
//			    	ExportSub exsub=exportSubRepository.findExportSub1( companyId, branchId,);
			    	FilteredExportSubList.add(list1);
			    }

	       }
//	       update gate_pass_Status as "Y" to db 
	      List<String> exportSubIds = FilteredExportSubList.stream()
	    	        .map(ExportSub::getExpSubId)
	    	        .collect(Collectors.toList());
	    	exportSubRepository.setGatePassStatusToY(exportSubIds);

	       
	      for (ImportSub list2 : impsub) {
			    
				
			    if (!removedRecordArray.contains(list2.getRequestId()) && searchData.contains(list2.getSirNo()) ) {
			    	FilteredImportSubList.add(list2);
			    	
			    	Party party=partyrepo.findByPartyId(list2.getExporter());
			    	names2.add(party.getPartyName());
			    }

	       }
	      
	     List<String> importSubIds = FilteredImportSubList.stream()
	            .map(ImportSub::getImpSubId)
	            .collect(Collectors.toList());

	    importSubRepository.setGatePassStatusToY(importSubIds);

	      
	     for (Import list3 : imp) {
			    
				
				 // Check if either invoiceNo or hawb is not in the removedRecordArray
			    if (!removedRecordArray.contains(list3.getHawb())  && searchData.contains(list3.getSirNo())) {
			    	FilteredImportList.add(list3);
			    	Party party=partyrepo.findByPartyId(list3.getImporterId());
			    	names3.add(party.getPartyName());
			    }

	       }
	     
	    for (Import importItem : FilteredImportList) {
	       // Replace setGatePassStatus with your actual method to set the gate_pass_status
	       importItem.setGatePassStatus("Y");
	   }

	   // Save the changes to the database if needed
	   importRepository.saveAll(FilteredImportList);



	       
            String imagePath = "G:/Updated DGDC/Project-13-10-23/java/CWMS_JAVA/CWMS_JAVA/src/main/resources/static/image/DGDC1.png";

			File imageFile = new File(imagePath);
			if (imageFile.exists()) {
				Image image = Image.getInstance(imagePath);
				image.scaleToFit(400, 300); // Adjust the dimensions as needed
//				image.setAlignment(Element.ALIGN_CENTER);
//				document.add(image);
				context.setVariable("dgdclogo", image);
			} else {
				System.out.println("img not here");// Handle the case where the image does not exist
			}
			
			 // Get the current time in milliseconds
	        long currentTimeMillis = System.currentTimeMillis();

	        // Create a Date object using the current time
	        Date currentDate = new Date(currentTimeMillis);

	        // Create a SimpleDateFormat object for formatting the time
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	        // Format the current time
	        String formattedTime = sdf.format(currentDate);
	        
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
			Date d1= new Date();
			context.setVariable("Date", dateFormat2.format(d1));
			context.setVariable("companyId", companyId);
			context.setVariable("combinedList", combinedList);
			context.setVariable("formattedTime", formattedTime);
			context.setVariable("FilteredExportSubList", FilteredExportSubList);
			context.setVariable("FilteredImportSubList", FilteredImportSubList);
			context.setVariable("FilteredImportList", FilteredImportList);	
			context.setVariable("names1", names1);
			context.setVariable("names2", names2);
			context.setVariable("names3", names3); 		
			context.setVariable("partyName", partyName); 
			context.setVariable("RName", RName);
			context.setVariable("typeName", typeName);
			// Process the HTML template with dynamic values
			String htmlContent = templateEngine.process("CommonGatePass", context);

			// Create an ITextRenderer instance
			ITextRenderer renderer = new ITextRenderer();

			// Set the PDF page size and margins
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();

			// Generate PDF content
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			renderer.createPDF(outputStream);

			// Get the PDF bytes
			byte[] pdfBytes = outputStream.toByteArray();

			// Encode the PDF content as Base64
			String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);


			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				    .body(base64Pdf);

		} catch (Exception e) {
			// Handle exceptions appropriately
			return ResponseEntity.badRequest().body("Error generating PDF");
		}
	}
//	 
//	 @GetMapping("/findbytpno/{cid}/{bid}")
//	 public List<String> finduniquetpno(@PathVariable("cid") String companyId,@PathVariable("bid") String branchId) throws ParseException {
//		 
//		// Get the current LocalDate
//	        LocalDate localDate = LocalDate.now();
//
//		   
//		 List<String> tpdata = exportRepository.findtpbytpdata(companyId,branchId,date);
//		 return tpdata;
//	 }
	 
	 
	 
	 @GetMapping("/alltp/{cid}/{bid}/{date}")
		public List<String> getalltp(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
			
			List<String> tp = exportRepository.getalltp(cid, bid, date);
			return tp;
		}
	 
	 
	 
	 private String generateUniqueFileName(String originalFileName) {
			String uniqueFileName = originalFileName;
			int suffix = 1;

			while (Files.exists(Paths.get(FileUploadProperties.getPath() + uniqueFileName))) {
				int dotIndex = originalFileName.lastIndexOf('.');
				String nameWithoutExtension = dotIndex != -1 ? originalFileName.substring(0, dotIndex) : originalFileName;
				String fileExtension = dotIndex != -1 ? originalFileName.substring(dotIndex) : "";
				uniqueFileName = nameWithoutExtension + "_" + suffix + fileExtension;
				suffix++;
			}

			return uniqueFileName;
		}

	 
		@PostMapping("/redeposite/{id}/{cid}/{bid}")
		public ResponseEntity<String> updatedataforRedeposite(@PathVariable("cid") String cid,
				@PathVariable("bid") String bid, @PathVariable("id") String id, @RequestParam("file") MultipartFile file,
				@RequestParam("remarks") String remarks,
				@RequestParam("RemovedRecordArray") List <String> RemovedRecordArray
				) {


		
			
			List<Export_History> exportHistoryList = new ArrayList<>();

			System.out.println(file + "file");
			System.out.println(remarks + "remarks");
			System.out.println(RemovedRecordArray + "RemovedRecordArray");

			List<Export> export = exportRepository.findAll();
			
			
			

			for (Export exp : export) {

				String pc = exp.getSerNo();

				if (RemovedRecordArray.contains(pc)) {
					exp.setDgdcStatus("Handed over to DGDC Cargo");
					exp.setRedepositeRemark(remarks);
					exportRepository.save(exp);

					Export_History export_History = new Export_History(exp.getCompanyId(), exp.getBranchId(), exp.getSbNo(),
							exp.getSbRequestId(), exp.getSerNo(), id, "Handed Over to Airline", exp.getDgdcStatus(), null);
					export_History.SetHistoryDate();
					exportHistoryList.add(export_History);

					// Get the original file name
					String originalFileName = file.getOriginalFilename();

					// Generate a unique file name to avoid duplicates
					String uniqueFileName = generateUniqueFileName(originalFileName);

					exp.setImagePath(FileUploadProperties.getPath() + uniqueFileName);

					// Save the file to your local system with the unique name
					try {
						Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName));
					} catch (IOException e) {
						e.printStackTrace(); // Consider proper error handling here
						return ResponseEntity.badRequest().body("Failed to save the file.");
					}

				}
			}
			export_HistoryRepository.saveAll(exportHistoryList);

			return ResponseEntity.ok("Data and file saved successfully.");
		}
		//Get Data By SER Number 
				@GetMapping("/getdatabyserNo/{cid}/{bid}/{serNo}")
				public List<Export> getdatabyserNo(@PathVariable("cid") String cid,@PathVariable("bid") String bid, @PathVariable("serNo") String serNo ){
					return exportRepository.findByCompanyIdAndBranchIdAndSerNoAndPcStatus(cid, bid, serNo,"Y");
				}
		

				@GetMapping("/alldatabyparty/{cid}/{bid}/{party}")
				public List<Export> alldatabyexportername(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("party") String party){
					return this.exportRepository.findAllDataforparty(cid, bid, party);
				}
				
				@GetMapping("/alldatabycartingagent/{cid}/{bid}/{party}")
				public List<Export> alldatabycartingagent(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("party") String party){
					return this.exportRepository.findAllDataforcartingagent(cid, bid, party);
				}
				
				@GetMapping("/alldatabycha/{cid}/{bid}/{party}")
				public List<Export> alldatabyCHA(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("party") String party){
					return this.exportRepository.findAllDataforcha(cid, bid, party);
				}
				
				@GetMapping("/alldatabyconsole/{cid}/{bid}/{party}")
				public List<Export> alldatabyConsole(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("party") String party){
					return this.exportRepository.findAllDataforconsole(cid, bid, party);
				}
				
				@PostMapping("/backtotown/{cid}/{bid}/{id}/{status}/{sbno}/{reqid}/{remark}")
				public Export backtotown(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("status") String status,@PathVariable("id") String id,@PathVariable("sbno") String sbno,@PathVariable("reqid") String reqid,@PathVariable("remark") String remark,@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
					   
				    String folderPath = FileUploadProperties.getPath(); // Update with your folder path
				    String fileName = file.getOriginalFilename();
				    String filePath = folderPath + "/" + fileName;

				    // Use the provided logic to generate a unique file name
				    String uniqueFileName = generateUniqueFileName(folderPath, fileName);

				    // Construct the full path for the unique file
				    String uniqueFilePath = folderPath + "/" + uniqueFileName;
				    file.transferTo(new File(uniqueFilePath));
				    
				    Export export = exportRepository.findBySBNoandSbreqid(cid, bid, reqid, sbno);
				    export.setBacktotownRemark(remark);
				    export.setDgdcStatus("Back To Town");
				    export.setBacktotownFilePath(uniqueFilePath);
				    exportRepository.save(export);
				    
				    Export_History export_History = new Export_History();
					export_History.setCompanyId(export.getCompanyId());
					export_History.setBranchId(export.getBranchId());
					export_History.setNewStatus("Back To Town");
					export_History.setOldStatus(status);
					export_History.setSbNo(export.getSbNo());
					export_History.setSbRequestId(export.getSbRequestId());
					
					export_History.setTransport_Date(new Date());
					export_History.setUpdatedBy(id);
					export_History.SetHistoryDate();
					export_HistoryRepository.save(export_History);
					
				    
				    return export;
				}
				
				
				
				@GetMapping("/download1/{cid}/{bid}/{reqid}/{sbno}")
				public ResponseEntity<byte[]> downloadImage1(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
						@PathVariable("sbno") String sbno, @PathVariable("reqid") String reqid) throws IOException {
					// Retrieve the image path from the database based on imageId
					Export exp = exportRepository.findBySBNoandSbreqid(cid, bid, reqid, sbno);
					String imagePath = exp.getBacktotownFilePath();

					if (imagePath != null) {

						MediaType mediaType = MediaType.IMAGE_JPEG; // Default to JPEG

						if (imagePath.endsWith(".pdf")) {
							mediaType = MediaType.APPLICATION_PDF;
						} else if (imagePath.endsWith(".png")) {
							mediaType = MediaType.IMAGE_PNG;
						} else if (imagePath.endsWith(".jpg") || imagePath.endsWith(".jpeg")) {
							mediaType = MediaType.IMAGE_JPEG;
						}
						// Load the image file as a byte array
						byte[] imageBytes = imageService.loadImage(imagePath);

						// Determine the content type based on the image file type (e.g., image/jpeg)
						HttpHeaders headers = new HttpHeaders();
						// Adjust as needed
						headers.setContentType(mediaType);

						return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
					} else {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
				}
}