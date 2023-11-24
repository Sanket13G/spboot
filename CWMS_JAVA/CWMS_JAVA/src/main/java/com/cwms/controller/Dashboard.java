package com.cwms.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import com.cwms.repository.BranchRepo;
import com.cwms.repository.CompanyRepo;
import com.cwms.repository.DetantionRepository;
import com.cwms.repository.ExportHeavyPackageRepo;
import com.cwms.repository.ExportRepository;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.Export_HistoryRepository;
import com.cwms.repository.ExternalPartyRepository;
import com.cwms.repository.ForwardInRepo;
import com.cwms.repository.ForwardOutRepo;
import com.cwms.repository.ImportRepository;
import com.cwms.repository.ImportSubRepository;
import com.cwms.repository.PartyRepository;
import com.cwms.service.ExportService;
import com.cwms.service.ProcessNextIdService;

@CrossOrigin("*")
@RequestMapping("/dashboard")
@RestController
public class Dashboard {
	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private BranchRepo branchRepo;
	
	@Autowired
	private ForwardOutRepo forwardOutRepo;
	
	
	@Autowired
	private ForwardInRepo forwardInRepo;
	
	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private ExportRepository exportRepository;
	
	@Autowired
	private ImportRepository importRepository;
	
	@Autowired
	private ExportService sbTransactionService;

	@Autowired
	private Export_HistoryRepository export_HistoryRepository;

	@Autowired
	private ProcessNextIdService processNextIdService;
	@Autowired
	private ExternalPartyRepository externalPartyRepository;

	@Autowired
	private PartyRepository partyrepo;

	@Autowired
	private ExportHeavyPackageRepo eexportHeavyRepo;

	@Autowired
	private ExportSubRepository exportSubRepository;
	
	@Autowired
	private ImportSubRepository importSubRepository;
	
	@Autowired
	private DetantionRepository detantionRepository;

	// get Export Data for Dashboard
	@GetMapping("/getexportdata/{cid}/{bid}")
	public List<Integer> getexportdata(@PathVariable("cid") String cid, @PathVariable("bid") String bid

	) throws ParseException {
		Date date1 = new Date();


	     // Create a formatter for the desired date format
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        // Get the current date as LocalDate
	        LocalDate currentDate = LocalDate.now();

	        // Format the current date as a string
	        String formattedDateStr = currentDate.format(formatter);

	        // Parse the formatted string into a Date object
	        Date formattedDate = new SimpleDateFormat("yyyy-MM-dd").parse(formattedDateStr);
		List<Integer> ExportValues = new ArrayList<>();
		
		
		
//1) Query for Export Opening Balance

		Optional<Integer> exportOpeningBalanceOptional = Optional.ofNullable(exportRepository
				.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDateNot(cid, bid, "Handed over to DGDC SEEPZ", formattedDate));

		int exportOpeningBalance = exportOpeningBalanceOptional.orElse(0);

		ExportValues.add(exportOpeningBalance);
		System.out.println(exportOpeningBalance + "Export Opening balance Value");

// 2) Query for Export Received

		Integer exportReceived = exportRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate1(cid, bid, formattedDate);
         System.out.println("exportReceived1 "+exportReceived);
		int actualExportReceived = (exportReceived != null) ? exportReceived : 0;
		ExportValues.add(actualExportReceived);
		System.out.println(actualExportReceived + "Export Received Value");

// 3) Query for Export Received SUB

		Integer exportReceivedSub = exportSubRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate2(cid, bid, formattedDate);

		int actualExportReceivedSub = (exportReceivedSub != null) ? exportReceivedSub : 0;
		ExportValues.add(actualExportReceivedSub);
		System.out.println(actualExportReceivedSub + "Export Received SUB Value");

// 4) Query for Export Delivery

		Integer exportDeliveries = exportRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate3(cid, bid,
				"Handed Over to Airline", formattedDate);

		int actualExportDeliveries = (exportDeliveries != null) ? exportDeliveries : 0;
		ExportValues.add(actualExportDeliveries);
		System.out.println(actualExportDeliveries + "Export Delivery Value");

// 5) Query for Export Delivery SUB

		Integer exportDeliveriesSub = exportSubRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate4(cid,
				bid, "Exit from DGDC SEEPZ Gate", formattedDate);

		int actualExportDeliveriesSub = (exportDeliveriesSub != null) ? exportDeliveriesSub : 0;
		ExportValues.add(actualExportDeliveriesSub);
		System.out.println(actualExportDeliveriesSub + "Export Delivery SUB Value");

	
// 6) Query for Export Detention Opening Balance

				Integer exportDetentionOpeningBalance = detantionRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate5(cid,
						bid, "Deposited","Export", formattedDate);

				int actualExportDetentionOpeningBalance = (exportDetentionOpeningBalance != null) ? exportDetentionOpeningBalance : 0;
				ExportValues.add(actualExportDetentionOpeningBalance);
				System.out.println(actualExportDetentionOpeningBalance + "Export Detention OpeningBalance Value");


		
// 7) Query for Export Detention Deposite

		Integer exportDetentionDeposite = detantionRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate6(cid,
				bid,"Export", formattedDate);

		int actualExportDetentionDeposite = (exportDetentionDeposite != null) ? exportDetentionDeposite : 0;
		ExportValues.add(actualExportDetentionDeposite);
		System.out.println(actualExportDetentionDeposite + "Export Detention Deposite Value");
	

	
// 8) Query for Export Detention Withdrawn and Issue 

		Integer exportDetentionWithdrawn = detantionRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate7(cid,
				bid, "Issued","Withdraw","Export", formattedDate);

		int actualExportDetentionWithdrawn = (exportDetentionWithdrawn != null) ? exportDetentionWithdrawn : 0;

		System.out.println(actualExportDetentionWithdrawn + "Export Detention Withdrawn Value");

		ExportValues.add(actualExportDetentionWithdrawn);
		
		System.out.println(ExportValues + "Export List");
				return ExportValues;	
	
	}

	
	
	// get Import Data for Dashboard
		@GetMapping("/getimportdata/{cid}/{bid}")
		public List<Integer> getimportdata(@PathVariable("cid") String cid, @PathVariable("bid") String bid

		) throws ParseException {
			Date date1 = new Date();
			// Create a formatter for the desired date format
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        // Get the current date as LocalDate
	        LocalDate currentDate = LocalDate.now();

	        // Format the current date as a string
	        String formattedDateStr = currentDate.format(formatter);

	        // Parse the formatted string into a Date object
	        Date formattedDate = new SimpleDateFormat("yyyy-MM-dd").parse(formattedDateStr);
	        
	        
	        LocalDateTime startOfDay = currentDate.atStartOfDay();

	        // Get the end of the day (just before midnight) as LocalDateTime
	        LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);

	        // Format the start and end of the day as strings
	        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedStartOfDayStr = startOfDay.format(formatter1);
	        String formattedEndOfDayStr = endOfDay.format(formatter1);

	        // Parse the formatted strings into Date objects
	        Date formattedStartOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedStartOfDayStr);
	        Date formattedEndOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedEndOfDayStr);
			List<Integer> ImportValues = new ArrayList<>();
			
			
	//1) Query for Import Opening Balance

			Optional<Integer> importOpeningBalanceOptional = Optional.ofNullable(importRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDateNot(cid, bid, "Handed over to DGDC SEEPZ", formattedDate));

			int importOpeningBalance = importOpeningBalanceOptional.orElse(0);

			ImportValues.add(importOpeningBalance);
			System.out.println(importOpeningBalance + "Import Opening balance Value");

//	 2) Query for Import Received

			Integer importReceived = importRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDateNot1(cid, bid,"N", formattedDate);

			int actualImportReceived = (importReceived != null) ? importReceived : 0;
			ImportValues.add(actualImportReceived);
			System.out.println(actualImportReceived + "Import Received Value");

//	 3) Query for Import Received SUB

			Integer importReceivedSub = importSubRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate2(cid, bid,formattedDate);

			int actualImportReceivedSub = (importReceivedSub != null) ? importReceivedSub : 0;
			ImportValues.add(actualImportReceivedSub);
			System.out.println(actualImportReceivedSub + "Import Received SUB Value");
			
//	 4) Query for Import NIPT

			Integer importReceivedNIPT = importRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDateNot1(cid, bid,"Y", formattedDate);

			int actualimportReceivedNIPT = (importReceivedNIPT != null) ? importReceivedNIPT : 0;
			ImportValues.add(actualimportReceivedNIPT);
			System.out.println(actualimportReceivedNIPT + "Import Received NIPT Value");

//	 5) Query for Import Delivery 

			Integer importDeliveries = importRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate3(cid,bid, "Exit from DGDC SEEPZ Gate","N", formattedDate);

			int actualImportDeliveries = (importDeliveries != null) ? importDeliveries : 0;
			ImportValues.add(actualImportDeliveries);
			System.out.println(actualImportDeliveries + "Import Delivery  Value");
			
			
			
//			 6) Query for Import Delivery SUB

			Integer importDeliveriesSUB = importSubRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate3(cid,bid, "Exit from DGDC SEEPZ Gate", formattedDate);

			int actualImportDeliveriesSUB = (importDeliveriesSUB != null) ? importDeliveriesSUB : 0;
			ImportValues.add(actualImportDeliveriesSUB);
			System.out.println(actualImportDeliveriesSUB + "Import Delivery SUB  Value");		
			

//			 7) Query for Import Delivery NIPT

					Integer importDeliveriesNIPT = importRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate3(cid,bid, "Exit from DGDC SEEPZ Gate","Y", formattedDate);

					int actualImportDeliveriesNIPT = (importDeliveriesNIPT != null) ? importDeliveriesNIPT : 0;
					ImportValues.add(actualImportDeliveriesNIPT);
					System.out.println(actualImportDeliveriesNIPT + "Import Delivery NIPT Value");	
			

			
//	          8) Query for Import Detention Opening Balance

					Integer importDetentionOpeningBalance = detantionRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate5(cid,bid, "Deposited","Import", formattedDate);

					int actualImportDetentionOpeningBalance = (importDetentionOpeningBalance != null) ? importDetentionOpeningBalance : 0;
					ImportValues.add(actualImportDetentionOpeningBalance);
					System.out.println(actualImportDetentionOpeningBalance + "Import Detention Opening Balance Value");

					
// 9) Query for Import Detention Deposite

					Integer importDetentionDeposite = detantionRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate6(cid,bid,"Import", formattedDate);

					int actualImportDetentionDeposite = (importDetentionDeposite != null) ? importDetentionDeposite : 0;
					ImportValues.add(actualImportDetentionDeposite);
					System.out.println(actualImportDetentionDeposite + "Import Detention Deposite Value");
				

				
// 10) Query for Import Detention Withdrawn and Issue 

					Integer importDetentionWithdrawn = detantionRepository.findSumByCompanyIdAndBranchIdAndDgdcStatusAndSerDate7(cid,bid, "Issued","Withdraw","Import", formattedDate);

					int actualImportDetentionWithdrawn = (importDetentionWithdrawn != null) ? importDetentionWithdrawn : 0;

					System.out.println(actualImportDetentionWithdrawn + "Export Detention Withdrawn Value");

					ImportValues.add(actualImportDetentionWithdrawn);
					
					
					
					// 11) Query for Forward OUT

					Integer forwardOut = forwardOutRepo.findSumByCompanyIdAndBranchIdAndDate8(cid,bid,formattedStartOfDay,formattedEndOfDay);

					int actualforwardOut = (forwardOut != null) ? forwardOut : 0;

					System.out.println(actualforwardOut + "Forward Out Value");

					ImportValues.add(actualforwardOut);	
					
					
					
					// 12) Query for Forward IN

					Integer forwardIn = forwardInRepo.findSumByCompanyIdAndBranchIdAndDate9(cid,bid, formattedStartOfDay,formattedEndOfDay);

					int actualforwardIn = (forwardIn != null) ? forwardIn : 0;

					System.out.println(actualforwardIn + "Forward In Value");

					ImportValues.add(actualforwardIn);					
					
					

			System.out.println(ImportValues + "Import List");
					return ImportValues;	
		
		}
	
		
		
		
		// get Bar Graph Data for Dashboard
		
		@GetMapping("/getbargraphdata/{cid}/{bid}")
		public Optional<List<Object[]>> getbargraphdata(@PathVariable("cid") String cid, @PathVariable("bid") String bid
		) {
			

			Optional<List<Object[]>> BarGraphData = Optional.ofNullable(exportRepository
			    .getImportExportDataByCompanyAndBranch(cid, bid));

			if (BarGraphData.isPresent()) {
			   
			    
			    List<Object[]> bargraphList = BarGraphData.get();

			    // Print each element in the list
			    for (Object[] data : bargraphList) {
			        System.out.println("ImportTotalNop111: " + data[0] + ", ExportTotalNopPackages: " + data[1] + ", MonthYear: " + data[2]);
			    }
			    System.out.println("Bargraph List: " + bargraphList);
			} else {
			    // Handle the case when the Optional is empty
			    System.out.println("Bargraph List is empty");
			}
		
		
		return BarGraphData;	
		}
		
		
		// get Graph Data for Dashboard
		
		@GetMapping("/getgraphdata/{cid}/{bid}")
		public Optional<List<Object[]>> getgraphdata(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		    System.out.println("graph List is called");

		    Optional<List<Object[]>> graphData = Optional.ofNullable(exportRepository.getTotalInvoiceAmountByMonth(cid, bid));

		    graphData.ifPresent(dataList -> {
		        for (Object[] data : dataList) {
		            System.out.println("TotalAmount: " + data[0] + ", MonthYear: " + data[1]);
		            // You can modify the above print statement based on the actual structure of your Object array
		        }
		    });

		    return graphData;
		}


		
}
	

