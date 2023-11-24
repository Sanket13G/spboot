package com.cwms.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.cwms.entities.Airline;
import com.cwms.entities.Branch;
import com.cwms.entities.Company;
import com.cwms.entities.ExternalParty;
import com.cwms.entities.Import;
import com.cwms.entities.Import_History;
import com.cwms.entities.Party;
import com.cwms.repository.AirlineRepository;
import com.cwms.repository.BranchRepo;
import com.cwms.repository.CompanyRepo;
import com.cwms.repository.ExternalPartyRepository;
import com.cwms.repository.ImportRepo;
import com.cwms.repository.ImportRepository;
import com.cwms.repository.PartyRepository;
import com.cwms.service.AirlineServiceImpliment;
import com.cwms.service.ImportService;
import com.cwms.service.ImportServiceImpl;
import com.cwms.service.Import_HistoryService;
import com.cwms.service.Importserviceforpctm;
import com.cwms.service.ProcessNextIdService;
import com.itextpdf.text.Image;

@RestController
@CrossOrigin("*")
@RequestMapping("/import")
public class ImportController {

	@Autowired
	private ImportRepository imprepo;
	
	
	@Autowired
	AirlineRepository airlineRepository;

	@Autowired
	AirlineServiceImpliment airlineServiceImpliment;
	
	
	@Autowired
	private TemplateEngine templateEngine;

	
	@Autowired
	private ImportServiceImpl importService;
	
	@Autowired
	private Import_HistoryService historyService;
	
	@Autowired
	public ImportService importServices;
	
	@Autowired
	private PartyRepository partyRepository;
	
	@Autowired
	public ProcessNextIdService proccessNextIdService;
	
	@Autowired
	private Importserviceforpctm importservicepctm;
	
	@Autowired
	private CompanyRepo companyRepo;
	
	@Autowired
	private BranchRepo branchRepo;
	
	
	@Autowired
	private ImportRepo importRepo;
	
	@Autowired
	private ExternalPartyRepository externalPartyRepository;
	
	
	@GetMapping("/all/{cid}/{bid}")
	public List<Import> getAll1(@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
		return this.imprepo.findByAll(cid,bid);
	}
	
	@GetMapping("/single/{cid}/{bid}/{sir}")
	public Import getSingledata(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("sir") String sir) {
		return this.importRepo.Singledata(cid, bid, sir);
	}

	
	@GetMapping("/tpdate")
	public List<String> getAllbytpdate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,@RequestParam("cid") String cid,@RequestParam("bid") String bid) {
		
		return imprepo.findByTp(date,cid,bid);
	}
	
	@GetMapping("/getalldata")
	public List<Import> getallbyTpnoandTpdate(
	    @RequestParam("cid") String cid,
	    @RequestParam("bid") String bid,
	    @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
	    @RequestParam("tpno") String tpno
	  //  @RequestParam("status") char status
	    ) { // Change the parameter name to "status"
	    return imprepo.findByTpdateTpno(cid, bid, date, tpno); // Use "status" parameter here
	}
	
	@GetMapping("/importData")
	public List<Object[]> getImportData(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,

			@RequestParam("airlineName") String airlineName) {
		
//		 System.out.println("Received companyId: " + companyId);
//		    System.out.println("Received branchId: " + branchId);
//		    System.out.println("Received startDate: " + startDate);
//		    System.out.println("Received endDate: " + endDate);
//		    System.out.println("Received airlineName: " + airlineName);
		List<Object[]> imp = importService.findImportData(companyId, branchId, startDate, endDate, airlineName);

		
		for (Object[] i : imp) {
			System.out.println(i);
		}
		return imp;
	}

	// Dyanamic
	@GetMapping("/airline-names")
	public List<String> getAirlineNames(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
//		List<String> airlineNames = importService.findAirlineName(companyId, branchId, startDate, endDate);
//		 
//		Import i= new Import();
//		i.getSirDate();
//		System.out.println( "Sir Date is" +i.getSirDate());
//
//		// Print each airline name individually
//		for (String name : airlineNames) {
//			System.out.println(name);
//		}
//
//		return airlineNames;
		
		return importRepo.findAirlineNames(companyId, branchId, startDate, endDate);
	}
	
	
	
	@GetMapping("/airlineNames/{companyId}/{branchId}/{startDate}/{endDate}")
	public List<String> getallAirlineNames(@PathVariable("companyId") String companyId,
			@PathVariable("branchId") String branchId,
			@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
//		List<String> airlineNames = importService.findAirlineName(companyId, branchId, startDate, endDate);
//		 
//		Import i= new Import();
//		i.getSirDate();
//		System.out.println( "Sir Date is" +i.getSirDate());
//
//		// Print each airline name individually
//		for (String name : airlineNames) {
//			System.out.println(name);
//		}
//
//		return airlineNames;
		
		return importRepo.findAirlineNames(companyId, branchId, startDate, endDate);
	}
	
	
	
	

	@GetMapping("/allimportData")
	public List<Import> getAllImportData(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,

			@RequestParam("flightNo") String flightNo) {

//		 System.out.println("Received companyId: " + companyId);
		return imprepo.findImportAllData(companyId, branchId, startDate, endDate, flightNo);
	}
	
	@PutMapping("/importDataAndUpdatePCTM")
	public List<Import> getImportDataAndUpdatePCTM(@RequestBody List<Import> importList) {
//		List<String> removedRecords = requestMap.get("removedRecords");
//		System.out.println(removedRecords + " ABC");
	
		

//		List<Import> importDataToUpdate = importRepo.findImportAllData(companyId, branchId, startDate, endDate,
//				flightNo);

		String updatedCount = proccessNextIdService.generateAndIncrementPCTMNumber();
		

		for (Import impo : importList) {

			impo.setPctmNo(updatedCount);

		}

		// Save the updated records
		importRepo.saveAll(importList);

		return importList;
	}
		
	
	
	
	@GetMapping("/getPctmNo")
	public List<String> getPctmNo(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam("flightNo") String flightNo) {

//		 System.out.println("Received companyId: " + companyId);
		return importRepo.findDistinctPctmNos(companyId, branchId, startDate, endDate, flightNo);
	}
	
	// Dyanamic
		@GetMapping("/Allairline-names")
		public List<String> getAllAirlineNames(@RequestParam("companyId") String companyId,
				@RequestParam("branchId") String branchId,
				@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
				@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
			
			
			
			List<String> flightNo = importService.findAllAirlineName(companyId, branchId, startDate, endDate);
//			 System.out.println(airlineName);
			return flightNo;
		}

	
		@GetMapping("/allimportPCTMData")
		public List<Import> getAllImportPCTMData(@RequestParam("companyId") String companyId,
				@RequestParam("branchId") String branchId,
				@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
				@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,

				@RequestParam("flightNo") String flightNo, @RequestParam("pctmNo") String pctmNo) {

//			 System.out.println("Received companyId: " + companyId);
			return importRepo.findImportPCTMData(companyId, branchId, startDate, endDate, flightNo, pctmNo);
		}
		
		
		
		
		
		
		
		
		public Set<Party> FilterParty(List<Import> list, String cid, String bid) {

			Set<Party> filterParty = new LinkedHashSet<>();
			List<Party> parties = partyRepository.getalldata(cid, bid);

			for (Import packages : list) {
				String partyIdToMatch = packages.getImporterId();
				for (Party party : parties) {
					if (partyIdToMatch.equals(party.getPartyId())) {
						filterParty.add(party);
						break; // Break the inner loop once a match is found
					}
				}
			}

			for (Party party : filterParty) {
				System.out.println(party);
			}

			return filterParty;
		}

		public String getAirlineName(String companyId, String branchId, String flightNo) {
			List<Airline> airlines = airlineServiceImpliment.getListAirlines(companyId, branchId);
			String flightName = null;
			System.out.println(airlines);
			for (Airline a : airlines) {
				if (a.getflightNo() == flightNo || a.getflightNo().equals(flightNo)) {
					System.out.println(a + "ABCD");
					flightName = a.getAirlineName();

				}
			}

			return flightName;
		}

		public Date getFlightDate(List<Import> pctmAll) {

			Date flightDate = null;

			Import fdate = pctmAll.get(0);

			flightDate = fdate.getFlightDate();

			return flightDate;
		}

		public String getConsoleNameFromExternal(List<ExternalParty> extparty, String consoleName) {
			String userNAme = null;
			for (ExternalParty a : extparty) {
				if (a.getExternaluserId().equals(consoleName)) {

					System.out.println(a.getUserName());
					userNAme = a.getUserName();

				}
			}

			return userNAme;
		}
		
		public int getDistinctMawbFromImport(List<Import> pctmAll) {
			
			Set<String> distinctMwab= new HashSet<>();
			
			for (Import a : pctmAll) {
				distinctMwab.add(a.getMawb());
			}

			return distinctMwab.size();
		}
		
	public int getDistinctIgmNoFromImport(List<Import> pctmAll) {
			
			Set<String> distinctIgmNo= new HashSet<>();
			
			for (Import a : pctmAll) {
				distinctIgmNo.add(a.getIgmNo());
			}

			return distinctIgmNo.size();
		}

	private String formatMAWBNumber(String mawb) {
	    if (mawb != null && mawb.length() == 11) {
	        return String.format("%s-%s-%s", mawb.substring(0, 3), mawb.substring(3, 7), mawb.substring(7));
	    } else {
	        // Handle invalid or unexpected input, or just return the input if it doesn't match the expected format.
	        return mawb;
	    }
	}

		
		
		
		
		
	
	
		public String formatPctmNo(String pctmNo) {

			String formatedPCTM = pctmNo.replaceFirst("^0+", "");
			return formatedPCTM;

		}
	
		private static final String[] units = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
				"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
				"Nineteen" };
		private static final String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
				"Ninety" };

		public String convertToWords(int number) {
			if (number < 20) {
				return units[number];
			}
			if (number < 100) {
				return tens[number / 10] + " " + units[number % 10];
			}
			if (number < 1000) {
				return units[number / 100] + " Hundred " + convertToWords(number % 100);
			}
			if (number < 1000000) {
				return convertToWords(number / 1000) + " Thousand " + convertToWords(number % 1000);
			}
			if (number < 1000000000) {
				return convertToWords(number / 1000000) + " Million " + convertToWords(number % 1000000);
			}
			return "Number out of range";
		}
		
		
		@PostMapping("/printOfImportPctm")
		public ResponseEntity<String> generateGateAllInvoiceDataNewGst(@RequestParam("companyId") String companyId,
				@RequestParam("branchId") String branchId,

				@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
				@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,

				@RequestParam("flightNo") String airlineCode, @RequestParam("pctmNo") String pctmNo) {

			try {
				Context context = new Context();			
				
				List<Import> pctmAll = importRepo.findImportPCTMData(companyId, branchId, startDate, endDate, airlineCode, pctmNo);

				 Import firstImport = pctmAll.get(0);

				    String flightNo = firstImport.getFlightNo();
				    String airlineName = firstImport.getAirlineName();

				    // Now you can use flightNo and airlineName as needed
				    System.out.println("Airline Code: " + flightNo);
				    
				    System.out.println("Airline Name: " + airlineName);
				    
				    context.setVariable("airlineCode", airlineCode);
					context.setVariable("AirlineName", airlineName);
				
				Set<String> distinctPctmNos = pctmAll.stream()
				        .filter(invoice -> pctmNo.equals(invoice.getPctmNo()))
				        .map(Import::getPctmNo)
				        .collect(Collectors.toSet());

				List<String> selectedPctmNos = new ArrayList<>(distinctPctmNos);

				Set<String> distinctIgmNos = pctmAll.stream()
				        .filter(invoice -> selectedPctmNos.contains(invoice.getPctmNo()))
				        .map(Import::getIgmNo)
				        .collect(Collectors.toSet());

				List<String> modifiedPctmNos = new ArrayList<>();
				for (String pctmNo1 : selectedPctmNos) {
				    modifiedPctmNos.add(pctmNo1.replaceFirst("^0+", ""));
				}

				List<String> selectedIgmNos = new ArrayList<>(distinctIgmNos);

				context.setVariable("selectedPctmNos", modifiedPctmNos);
				context.setVariable("selectedIgmNos", selectedIgmNos);
				
				
//				List<Import> pctmAll = importRepo.findImportPCTMData(companyId, branchId, startDate, endDate, flightNo,
//						pctmNo);
	//
//				Set<String> distinctPctmNos = pctmAll.stream().filter(invoice -> pctmNo.equals(invoice.getPctmNo()))
//						.map(Import::getPctmNo).collect(Collectors.toSet());
	//
//				List<String> selectedPctmNos = new ArrayList<>(distinctPctmNos);
	//
//				List<String> selectedIgmNos = pctmAll.stream().filter(invoice -> pctmNo.equals(invoice.getPctmNo()))
//						.map(Import::getIgmNo).collect(Collectors.toList());
	//
//				context.setVariable("selectedPctmNos", selectedPctmNos);
//				context.setVariable("selectedIgmNos", selectedIgmNos);

				List<Party> partyData = partyRepository.getalldata(companyId, branchId);

				Set<Party> filterParty = FilterParty(pctmAll, companyId, branchId);

//				String flightName = getAirlineName(companyId, branchId, flightNo);

				Date flightDate = getFlightDate(pctmAll);
	 
				int totalDistinctMawbNo=getDistinctMawbFromImport(pctmAll);
				
				int totalDistinctIgmNo=getDistinctIgmNoFromImport(pctmAll);
				
				List<ExternalParty> extparty = externalPartyRepository.getalldataExternalParties(companyId, branchId);

				int totalNoOfPackages=0;
				for (Import i : pctmAll) {
//					System.out.println(i.getConsoleName());
					i.setConsoleName(getConsoleNameFromExternal(extparty, i.getConsoleName()));
					i.setMawb(formatMAWBNumber(i.getMawb()));
					totalNoOfPackages=totalNoOfPackages+i.getNop();
					
				}
				
				if (pctmAll != null) {
					context.setVariable("invoiceAll", pctmAll);
					context.setVariable("partyData", partyData);
					context.setVariable("filterData", filterParty);
					context.setVariable("startDate", startDate);
					context.setVariable("endDate", endDate);
					context.setVariable("flightNo", flightNo);
					context.setVariable("flightNameOnly",airlineName);
					context.setVariable("flightName", airlineCode+" / "+airlineName);
					context.setVariable("flightDate", flightDate);
					context.setVariable("totalNoOfPackages", totalNoOfPackages);
					context.setVariable("totalDistinctMawbNo", totalDistinctMawbNo);
					context.setVariable("totalDistinctIgmNo", totalDistinctIgmNo);

					if (filterParty != null && !filterParty.isEmpty()) {
						context.setVariable("filterParty", filterParty);

					} else {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}

//					for (Import im : pctmAll) {
////							
	//
//					}
				}
//				file:///C:/DGDC/Java%20Code/CWMS_JAVA/src/main/resources/static/image/DGDC1.png

				String imagePath = "file:///C:/DGDC/Java%20Code/CWMS_JAVA/src/main/resources/static/image/DGDC.png";
				File imageFile = new File(imagePath);
				if (imageFile.exists()) {
					Image image = Image.getInstance(imagePath);
					image.scaleToFit(400, 300); //
					context.setVariable("dgdclogo", image);
				} else {
					System.out.println("Image not found"); // Handle the case where the image does not exist
				}

				// Process the HTML template with dynamic values
				String htmlContent = templateEngine.process("import_pctm_Report", context);

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
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		
		
		
		
		
		
		
		@PostMapping("/importTpPrint")
		public ResponseEntity<String> generateNewGstPartReport(@RequestParam("cid") String cid,
				@RequestParam("bid") String bid, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
				@RequestParam("tpno") String tpno) {

			try {
				List<Party> partyData = partyRepository.getalldata(cid, bid);

				List<Import> imp = imprepo.findByTpdateTpno(cid, bid, date, tpno);

				List<ExternalParty> extparty = externalPartyRepository.getAllExternalParties(cid, bid);

				if (imp != null) {
					Context context = new Context();

					context.setVariable("imp", imp);
					context.setVariable("extparty", extparty);
					context.setVariable("date", date);

					tpno = tpno.replaceFirst("^0+", "");
					context.setVariable("tpno", tpno);

					int TotalNoOfPackage = 0;
				

					for (Import im : imp) {
						TotalNoOfPackage = TotalNoOfPackage + im.getNop();

						im.setPctmNo(formatPctmNo(im.getPctmNo()));
						im.setConsoleName(getConsoleNameFromExternal(extparty, im.getConsoleName()));

					}
					System.out.println(convertToWords(TotalNoOfPackage));

					context.setVariable("TotalNoOfPackage", TotalNoOfPackage);
					context.setVariable("TotalNoOfPackageInWord", convertToWords(TotalNoOfPackage));

					String imagePath = "/Users/macbook/Mahesh1310/CWMS_JAVA/src/main/resources/static/image/DGDC1.png";
					File imageFile = new File(imagePath);
					if (imageFile.exists()) {
						Image image = Image.getInstance(imagePath);
						image.scaleToFit(400, 300); //
						context.setVariable("dgdclogo", image);
					} else {
						System.out.println("Image not found"); // Handle the case where the image does not exist
					}

					// Process the HTML template with dynamic values
					String htmlContent = templateEngine.process("import_tp_report", context);

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
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				// Handle exceptions appropriately
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	
	
	
	
	
	@GetMapping("/{compid}/{branchId}/{tranId}/{MAWB}/{HAWB}/{sirNo}/getSingle")
	public Import findByMAWBANDHAWB(
	        @PathVariable("MAWB") String MAWB,
	        @PathVariable("HAWB") String HAWB,
	        @PathVariable("compid") String compid,
	        @PathVariable("branchId") String branchId,
	        @PathVariable("tranId") String transId,
	        @PathVariable("sirNo") String sirNo) {
	    
	    return importServices.getByMAWBANdHAWB(compid, branchId, transId, MAWB, HAWB, sirNo);
	}
	
	
	@GetMapping("/{cid}/{bid}/{MAWBNo}")
	public List<Import> getByMawbNo(@PathVariable("MAWBNo")String MAWBNo,@PathVariable("cid") String cid, @PathVariable("bid") String bid)
	{
		return importServices.getByMAWB(cid,bid,MAWBNo);
	}
	
	@GetMapping("/{cid}/{bid}/All")
	public List<Import> getAll(@PathVariable("cid") String cid, @PathVariable("bid") String bid)
	{
		return importServices.getAll(cid,bid);
	}
	
	
	@PostMapping("/{compid}/{branchId}/{user}/add")
	public Import addImport(@PathVariable("compid")String compid,@PathVariable("branchId")String branchId,
			@RequestBody Import import2,@PathVariable("user")String User)
	{
		import2.setCompanyId(compid);
		import2.setBranchId(branchId);
		import2.setNSDL_Status("");
		import2.setDGDC_Status("Handed over to DGDC Cargo");
		
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
		
		Import_History history=new Import_History();
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
		return importServices.addImport(import2);
	}
	
	@PutMapping("/{compid}/{branchId}/{user}/update")
	public Import updateImport(@PathVariable("compid")String compid,@PathVariable("branchId")String branchId,
			@RequestBody Import import2,@PathVariable("user")String User)
	{
//		import2.setBranchId(branchId);
		import2.setEditedBy(User);
		import2.setEditedDate(new Date());
//		
		return importServices.updateImport(import2);
	}
	
	@PutMapping("/{compid}/{branchId}/{user}/modifyupdate")
	public Import updateImportByIMpTransId(@PathVariable("compid")String compid,@PathVariable("branchId")String branchId,
			@RequestBody Import import2,@PathVariable("user")String User)
	{
//		import2.setBranchId(branchId);
		
		Import existingImport = importServices.findBytransIdAndSirNo(compid,branchId,import2.getImpTransId(),import2.getSirNo());
		
		if(existingImport != null)
		{
			
			importServices.deleteImport(existingImport);
			Import newImport=new Import();
			
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
		
		return importServices.updateImport(newImport);
		}
		
		return null;
	}
	
	@DeleteMapping("/{compid}/{branchId}/{tranId}/{MAWB}/{HAWB}/{sirNo}/delete")
	public void deleteImport(@PathVariable("MAWB") String MAWB,
	        @PathVariable("HAWB") String HAWB,
	        @PathVariable("compid") String compid,
	        @PathVariable("branchId") String branchId,
	        @PathVariable("tranId") String transId,
	        @PathVariable("sirNo") String sirNo)
	{
		Import byMAWBANdHAWB = importServices.getByMAWBANdHAWB(compid, branchId, transId, MAWB, HAWB, sirNo);
		if(byMAWBANdHAWB != null)
		{
			byMAWBANdHAWB.setStatus("D");
			importServices.updateImport(byMAWBANdHAWB);
		}
	}
	
	@GetMapping("/importTransaction")
    public ResponseEntity<List<Import>> findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(
			@RequestParam("sirDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sirDate,
            @RequestParam("companyId") String companyId,
            @RequestParam("branchId") String branchId,
            @RequestParam("dgdcStatus") String dgdcStatus) {

	 
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(sirDate);
		
	 System.out.println(formattedDate);
	 
        List<Import> imports = importservicepctm.findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(formattedDate, companyId, branchId, dgdcStatus);
        
        System.out.println(imports);
        if (imports.isEmpty()) {
            return ResponseEntity.notFound().build();
            
        }

        return ResponseEntity.ok(imports);
    }

	@GetMapping(value = "/findCompanyname/{cid}")
	public String findCompanyname(@PathVariable("cid") String param) {
	Company company=	 companyRepo.findByCompany_Id(param);
		
		return company.getCompany_name();
	}

	@GetMapping(value = "/findBranchName/{cid}/{bid}")
	public String findBranchName(@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
	Branch branch =branchRepo.findByBranchIdWithCompanyId(cid, bid);
		return branch.getBranchName();
	}
	
	

	@GetMapping(value = "/getConsole/{companyId}/{branchId}/{doDate}")
    public List<ExternalParty> getConsoleList(
            @PathVariable("companyId") String companyId,
            @PathVariable("branchId") String branchId,
            @PathVariable("doDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date doDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(doDate);

        List<String> cIds = importRepo.findByCompanyAndBranchAndDoDate(companyId, branchId, formattedDate);

        List<ExternalParty> externalParties = new ArrayList<>();
       
        for (String string : cIds) {
           
            ExternalParty external = externalPartyRepository.findBycompbranchexternal(companyId, branchId, string);
            if (external != null) {
                externalParties.add(external);
            }
        }

        return externalParties;
    }

	@GetMapping(value = "/getImportList/{companyId}/{branchId}/{doDate}/{exId}")
	public List<Import> getImportList(@PathVariable("companyId") String companyId,
			@PathVariable("branchId") String branchId,
			@PathVariable("doDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date doDate,
			@PathVariable("exId") String exId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(doDate);
		List<Import> imports = importRepo.findByCompanyAndBranchAndDoDateAndexternalPId(companyId, branchId,
				formattedDate, exId);
		return imports;
	}

	@GetMapping(value = "/getDoNumber/{companyId}/{branchId}/{mawb}")
	public String getDoNumber(@PathVariable("companyId") String companyId, @PathVariable("branchId") String branchId,
			@PathVariable("mawb") String mawb) {
//		System.out.println(companyId + "\t" + branchId + "\t" + mawb);
//		System.out.println(importRepo.findByCompanyAndBranchAndMawb(companyId, branchId, mawb));
		return importRepo.findByCompanyAndBranchAndMawb(companyId, branchId, mawb);
	}

	@GetMapping(value = "/getDoNumberForUpdate/{companyId}/{branchId}")
	public List<Import> getDoNumberForUpdate(@PathVariable("companyId") String companyId,
			@PathVariable("branchId") String branchId) {

		return importRepo.findByCompanyAndBranchNUllDo(companyId, branchId);
	}

	@GetMapping(value = "/getUpdateDoNumber/{companyId}/{branchId}")
	public List<String> getUpdateDoNumber(@PathVariable("companyId") String companyId,
			@PathVariable("branchId") String branchId) {
		List<String> strings = new ArrayList<>();
		List<String> mawbStrings = importRepo.findMawbByCompanyAndBranch(companyId, branchId);
		System.out.println(mawbStrings);

		for (String mawb : mawbStrings) {
			List<Import> importList = importRepo.findByCompanyAndBranchAndMawbList(companyId, branchId, mawb);
			String olddo = importRepo.findByCompanyAndBranchAndMawb(companyId, branchId, mawb);
			String temp = null;

			if (olddo != null && !olddo.trim().isEmpty()) {
				temp = olddo;
			} else
				temp = proccessNextIdService.autoIncrementDoNumber();
			for (Import import1 : importList) {

				if (import1.getDoDate() != null) {
					import1.setDoNumber(temp);
				} else {
					import1.setDoNumber(temp);
					import1.setDoDate(new Date());
				}

			}
			importRepo.saveAllAndFlush(importList);

		}

		return strings;
	}

	@GetMapping(value = "/getExternalPartys/{companyId}/{branchId}")
	public List<ExternalParty> getExternalPartyUserName(@PathVariable("companyId") String companyId,
			@PathVariable("branchId") String branchId) 
	{

		return externalPartyRepository.getalldataExternalParties(companyId, branchId);
	}
	
	@GetMapping("/findImportAllData")
	public List<Import> findExportSubData(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,

			@RequestParam("consoleName") String consoleName) {
		return imprepo.findIMMportAllData(companyId, branchId, startDate, endDate, consoleName);
	}

	@GetMapping("/findImportData")
	public List<Import> findExportSubData(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate

	) {
		return imprepo.findImportData(companyId, branchId, startDate, endDate);
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

	@GetMapping("/findConsoleName/{companyId}/{branchId}/{externaluserId}")
	public String findConsoleName(@PathVariable String companyId, @PathVariable String branchId,
			@PathVariable String externaluserId) {
		String console_name = externalPartyRepository.findUserNameByKeys(companyId, branchId, externaluserId);

		if (console_name != null) {
			return console_name;
		} else {
			// Handle the case where partyName is not found
			return "User Name Not Found";
		}
	}
}
