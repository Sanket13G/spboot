package com.cwms.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.cwms.entities.CFSTariffRange;
import com.cwms.entities.CFSTariffService;
import com.cwms.entities.InvoiceDetail;
import com.cwms.entities.InvoiceMain;
import com.cwms.entities.Party;
import com.cwms.entities.Services;
import com.cwms.repository.InvoiceDetailRepostary;
import com.cwms.repository.InvoiceRepositary;
import com.cwms.repository.PartyRepository;
import com.itextpdf.text.Image;

@Service
public class InvoiceDetailServiceIMPL {

//	@Autowired
//	private InvoiceRepositary invoiceRepositary;

	@Autowired
	private InvoiceDetailRepostary detailRepostary;

	@Autowired
	public cfsTarrifServiceService cfsTarrifServiceService;
	
	@Autowired
	public InvoiceRepositary invoiceRepository;

	@Autowired
	private PartyRepository partyrepo;
	
	@Autowired
	public CFSTariffRangeService CFSTariffRangeService;

	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	public ServicesInterface ServicesInterface;

	public InvoiceDetail addInvoiceDetail(String companyId, String branchId, String Invoiceno, String tarrifno,
			String amndno, String serviceId, String partyId, int noOfPackages, String user ,Date invoiceDate) {
		Services servicesById = ServicesInterface.getServicesById(companyId, branchId, serviceId);
		String rangeType = servicesById.getRateCalculation();
		String taxApplicable = servicesById.getTaxApplicable();
		String serviceUnit = servicesById.getServiceUnit();
		double totalPackageDecimal = (double) noOfPackages;
		double rate = 0.0;
		String currencyId = "INR";
		double TaxPercentage = 0.0;
		double billAmount = 0.0;
		double taxamount = 0.0;
		double totalAmount = 0.0;

		if (taxApplicable != null && taxApplicable.equals("Y")) {
			TaxPercentage = Double.parseDouble(servicesById.getTaxPercentage());
		}

//		If rate type is Plain

		if ("Plain".equals(rangeType)) {
			CFSTariffService cfsservice = cfsTarrifServiceService.findByTarrifNoandServiceIdPartyId(companyId, branchId,
					tarrifno, amndno, serviceId, partyId);

			currencyId = cfsservice.getCurrencyId();
			rate = cfsservice.getRate();

			if (TaxPercentage != 0.0) {
				taxamount = rate * (TaxPercentage / 100.0);
				billAmount = totalPackageDecimal * rate;
				totalAmount = billAmount + taxamount;
			} else {
				billAmount = rate * totalPackageDecimal;
				totalAmount = billAmount;
			}

		}
//		If rate type is Range or Slab 
		else {

			List<CFSTariffRange> tariffRanges = CFSTariffRangeService
					.findByCfsTariffNoAndCfsAmndNoAndServiceIdAndPartyId(companyId, branchId, tarrifno, amndno,
							serviceId, partyId);

			if (!tariffRanges.isEmpty()) {
				currencyId = tariffRanges.get(0).getCurrencyId();
			}

			 // Sort the tariffRanges by rangeFrom in ascending order
		    tariffRanges.sort(Comparator.comparingDouble(CFSTariffRange::getRangeFrom));
			for (CFSTariffRange tariffRange : tariffRanges) {
				double rangeFrom = tariffRange.getRangeFrom();
				double rangeTo = tariffRange.getRangeTo();
				double rangeRate = tariffRange.getRangeRate();
//				double ratePlain = 

//				If rate type is Range
				
				if ("Range".equals(rangeType)) {					
					

					if (totalPackageDecimal > rangeFrom && totalPackageDecimal <= rangeTo) {
						rate = rangeRate;
						
						if (TaxPercentage != 0.0) {
							taxamount = rate * (TaxPercentage / 100.0);
												
//							if("Per Kg".equals(serviceUnit))
//							{
								billAmount = rate;
//							}
//							else
//							{
//								billAmount = totalPackageDecimal * rate;
//							}

							totalAmount = billAmount + taxamount;
							
						} else {
							billAmount = rate;
							totalAmount = billAmount;
						}
						
						
						break;
					}
				

				}

//				If rate type is Slab
				if ("Slab".equals(rangeType)) {
					
//									
					if (totalPackageDecimal <= 0) {
						break; // No need to continue if we've accounted for the entire totalPackage
					}

					if (totalPackageDecimal >= (rangeTo - rangeFrom)) {
						// If remainingPackage is greater than or equal to the entire range, calculate
						// rate
						rate += (rangeTo - rangeFrom) * rangeRate;		
						totalPackageDecimal -= (int) (rangeTo - rangeFrom);
					} else {
						rate += totalPackageDecimal * rangeRate;
						totalPackageDecimal = 0.0;
					}
					
					
					if (TaxPercentage != 0.0) {
						taxamount = rate * (TaxPercentage / 100.0);
																		
						billAmount = rate;
											
						totalAmount = billAmount + taxamount;
						
					} else {
						billAmount = rate;
						totalAmount = billAmount;
					}	
					
				}

			}

		}

		InvoiceDetail detail = new InvoiceDetail(companyId, branchId, Invoiceno, partyId, serviceId,invoiceDate,
				serviceUnit, serviceUnit, serviceUnit, rate, currencyId, TaxPercentage, taxamount, billAmount,
				totalAmount, "A", user, new Date(), user, new Date(), user, new Date());

		return detailRepostary.save(detail);
	}
	
	
//	Range Service Heavy Weight
	
	
	public InvoiceDetail addInvoiceDetailHeavyWeight(String companyId, String branchId, String Invoiceno, String tarrifno,
			String amndno, String serviceId, String partyId, int noOfPackages, String user ,Date invoiceDate,List<BigDecimal> weights) {
		Services servicesById = ServicesInterface.getServicesById(companyId, branchId, serviceId);
		String rangeType = servicesById.getRateCalculation();
		String taxApplicable = servicesById.getTaxApplicable();
		String serviceUnit = servicesById.getServiceUnit();
		double totalPackageDecimal = (double) noOfPackages;
		double rate = 0.0;
		String currencyId = "INR";
		double TaxPercentage = 0.0;
		double billAmount = 0.0;
		double taxamount = 0.0;
		double totalAmount = 0.0;

		if (taxApplicable != null && taxApplicable.equals("Y")) {
			TaxPercentage = Double.parseDouble(servicesById.getTaxPercentage());
		}


//		If rate type is Range or Slab 
		

			List<CFSTariffRange> tariffRanges = CFSTariffRangeService
					.findByCfsTariffNoAndCfsAmndNoAndServiceIdAndPartyId(companyId, branchId, tarrifno, amndno,
							serviceId, partyId);

			if (!tariffRanges.isEmpty()) {
				currencyId = tariffRanges.get(0).getCurrencyId();
			}

			 // Sort the tariffRanges by rangeFrom in ascending order
		    tariffRanges.sort(Comparator.comparingDouble(CFSTariffRange::getRangeFrom));
			for (CFSTariffRange tariffRange : tariffRanges) {
				double rangeFrom = tariffRange.getRangeFrom();
				double rangeTo = tariffRange.getRangeTo();
				double rangeRate = tariffRange.getRangeRate();
				
								
				for (BigDecimal heavy : weights) {
					
					double hpWeightValue = heavy.doubleValue();
									
					
					if (hpWeightValue > rangeFrom && hpWeightValue <= rangeTo) {
						rate = rangeRate;
						
						if (TaxPercentage != 0.0) {
							taxamount = rate * (TaxPercentage / 100.0);
														
							billAmount = rate;				

							totalAmount = billAmount + taxamount;
							
						} else {
							billAmount = rate;
							totalAmount = billAmount;
						}					
						
						break;
					}				

				}	
							
				
			}

		

		InvoiceDetail detail = new InvoiceDetail(companyId, branchId, Invoiceno, partyId, serviceId,invoiceDate,
				serviceUnit, serviceUnit, serviceUnit, rate, currencyId, TaxPercentage, taxamount, billAmount,
				totalAmount, "A", user, new Date(), user, new Date(), user, new Date());

		return detailRepostary.save(detail);
	}
	
	
	
	
	
	
	
	
	
	public List<InvoiceDetail> getByInvoiceNo(String companyId,String branchId,String invoiceNo)
	{
		return detailRepostary.findByCompanyIdAndBranchIdAndInvoiceNO(companyId, branchId, invoiceNo);
	}
	
	
	//invoiceData with Party
	
	@GetMapping("/invoiceDataOfParty")
	public List<InvoiceMain> getAllInvoiceDataOfParty(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate,

			@RequestParam("partyId") String partyId) {

//		 System.out.println("Received companyId: " + companyId);
		return invoiceRepository.findInvoiceData(companyId, branchId, startDate, endDate, partyId);
	}

	
	//invoiceData without Party
	
	@GetMapping("/invoiceAllDataOfParty")
	public List<InvoiceMain> getAllInvoiceData(@RequestParam("companyId") String companyId,
			@RequestParam("branchId") String branchId,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate) {

//		 System.out.println("Received companyId: " + companyId);
		return invoiceRepository.findAllInvoiceData(companyId, branchId, startDate, endDate);
	}

// Excel file 1 without party 
	@PostMapping("/Excel1/{companyid}/{branchId}")
	public ResponseEntity<?> generateInvoiceExcel(
	        @PathVariable("companyid") String companyId,
	        @PathVariable("branchId") String branchId,	           
	        @RequestParam("formattedStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date StartDate,
	        @RequestParam("formattedEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date EndDate
	) throws Exception {
	    try {

	        List<String> names = new ArrayList<>();
	        double totalInvoiceAmount = 0;
	        double totalAmountPaid = 0;

	        List<InvoiceMain> InvoiceList = invoiceRepository.findAllInvoiceData(companyId, branchId, StartDate, EndDate);

	        // Create the XLS file
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Party Data");

	        // Create a font for bold
	        Font boldFont = workbook.createFont();
	        boldFont.setBold(true);

	        // Create a cell style with bold font
	        CellStyle boldStyle = workbook.createCellStyle();
	        boldStyle.setFont(boldFont);

	        Row headerRow = sheet.createRow(0);
	        headerRow.createCell(0).setCellValue("Sr. No.");
	        headerRow.createCell(1).setCellValue("Party Name");
	        headerRow.createCell(2).setCellValue("Invoice Number");
	        headerRow.createCell(3).setCellValue("Invoice Date");
	        headerRow.createCell(4).setCellValue("Invoice Amount");
	        headerRow.createCell(5).setCellValue("Amount Paid");
	        headerRow.createCell(6).setCellValue("Balance Amount");

	        // Apply the bold style to the header cells
	        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
	            headerRow.getCell(i).setCellStyle(boldStyle);
	        }

	        int rowNum = 1; // Start from the second row to add data
	        for (int i = 0; i < InvoiceList.size(); i++) {
	            InvoiceMain invoiceObject = InvoiceList.get(i);
	            Party partyName = partyrepo.findByPartyId(invoiceObject.getPartyId());
	            System.out.println("partyName" + partyName.getPartyName());
	            names.add(partyName.getPartyName());
	            totalInvoiceAmount += invoiceObject.getTotalInvoiceAmount();
	            totalAmountPaid += invoiceObject.getClearedAmt();

	            Row dataRow = sheet.createRow(rowNum++);

	            dataRow.createCell(0).setCellValue(i + 1); // Serial number
	            dataRow.createCell(1).setCellValue(partyName.getPartyName());
	            dataRow.createCell(2).setCellValue(invoiceObject.getInvoiceNO());
	            dataRow.createCell(3).setCellValue(invoiceObject.getInvoiceDate().toString()); // Assuming InvoiceDate is a Date object
	            dataRow.createCell(4).setCellValue(invoiceObject.getTotalInvoiceAmount());
	            dataRow.createCell(5).setCellValue(invoiceObject.getClearedAmt());
	            dataRow.createCell(6).setCellValue(invoiceObject.getTotalInvoiceAmount() - invoiceObject.getClearedAmt());
	        }
	        Row dataRow = sheet.createRow(rowNum++);
	        double total = totalInvoiceAmount - totalAmountPaid;
	        
	        dataRow.createCell(1).setCellValue("Total");
	        dataRow.createCell(4).setCellValue(totalInvoiceAmount);
	        dataRow.createCell(5).setCellValue(totalAmountPaid);
	        dataRow.createCell(6).setCellValue(total);

	        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        String formattedStartDate = sdf.format(StartDate);
	        String formattedStartDate1 = sdf.format(EndDate);

	        // Now you need to write the workbook to a ByteArrayOutputStream
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        workbook.write(bos);
	        byte[] xlsxBytes = bos.toByteArray();
	        String xlsxBase64 = Base64.getEncoder().encodeToString(xlsxBytes);

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	                .body(xlsxBase64);

	    } catch (Exception e) {
	        // Log the exception for debugging
	        e.printStackTrace();
	        return ResponseEntity.badRequest().body("Error generating Excel");
	    }
	}

	
	
	
	// Excel file 2 with party 
		@PostMapping("/Excel2/{companyid}/{branchId}/{partyId}")
		public ResponseEntity<?> generateInvoiceExcel2(
		        @PathVariable("companyid") String companyId,
		        @PathVariable("branchId") String branchId,
		        @PathVariable("partyId") String partyId,	 
		        @RequestParam("formattedStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date StartDate,
		        @RequestParam("formattedEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date EndDate
		) throws Exception {
		    try {

		        List<String> names = new ArrayList<>();
		        double totalInvoiceAmount = 0;
		        double totalAmountPaid = 0;

		        List<InvoiceMain> InvoiceList = invoiceRepository.findInvoiceData(companyId, branchId, StartDate, EndDate, partyId);
		        // Create the XLS file
		        Workbook workbook = new XSSFWorkbook();
		        Sheet sheet = workbook.createSheet("Party Data");

		        // Create a font for bold
		        Font boldFont = workbook.createFont();
		        boldFont.setBold(true);

		        // Create a cell style with bold font
		        CellStyle boldStyle = workbook.createCellStyle();
		        boldStyle.setFont(boldFont);

		        Row headerRow = sheet.createRow(0);
		        headerRow.createCell(0).setCellValue("Sr. No.");
		        headerRow.createCell(1).setCellValue("Party Name");
		        headerRow.createCell(2).setCellValue("Invoice Number");
		        headerRow.createCell(3).setCellValue("Invoice Date");
		        headerRow.createCell(4).setCellValue("Invoice Amount");
		        headerRow.createCell(5).setCellValue("Amount Paid");
		        headerRow.createCell(6).setCellValue("Balance Amount");

		        // Apply the bold style to the header cells
		        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
		            headerRow.getCell(i).setCellStyle(boldStyle);
		        }

		        int rowNum = 1; // Start from the second row to add data
		        for (int i = 0; i < InvoiceList.size(); i++) {
		            InvoiceMain invoiceObject = InvoiceList.get(i);
		            Party partyName = partyrepo.findByPartyId(invoiceObject.getPartyId());
		            System.out.println("partyName" + partyName.getPartyName());
		            names.add(partyName.getPartyName());
		            totalInvoiceAmount += invoiceObject.getTotalInvoiceAmount();
		            totalAmountPaid += invoiceObject.getClearedAmt();

		            Row dataRow = sheet.createRow(rowNum++);

		            dataRow.createCell(0).setCellValue(i + 1); // Serial number
		            dataRow.createCell(1).setCellValue(partyName.getPartyName());
		            dataRow.createCell(2).setCellValue(invoiceObject.getInvoiceNO());
		            dataRow.createCell(3).setCellValue(invoiceObject.getInvoiceDate().toString()); // Assuming InvoiceDate is a Date object
		            dataRow.createCell(4).setCellValue(invoiceObject.getTotalInvoiceAmount());
		            dataRow.createCell(5).setCellValue(invoiceObject.getClearedAmt());
		            dataRow.createCell(6).setCellValue(invoiceObject.getTotalInvoiceAmount() - invoiceObject.getClearedAmt());
		        }
		        Row dataRow = sheet.createRow(rowNum++);
		        double total = totalInvoiceAmount - totalAmountPaid;
		        
		        dataRow.createCell(1).setCellValue("Total");
		        dataRow.createCell(4).setCellValue(totalInvoiceAmount);
		        dataRow.createCell(5).setCellValue(totalAmountPaid);
		        dataRow.createCell(6).setCellValue(total);

		        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		        String formattedStartDate = sdf.format(StartDate);
		        String formattedStartDate1 = sdf.format(EndDate);

		        // Now you need to write the workbook to a ByteArrayOutputStream
		        ByteArrayOutputStream bos = new ByteArrayOutputStream();
		        workbook.write(bos);
		        byte[] xlsxBytes = bos.toByteArray();
		        String xlsxBase64 = Base64.getEncoder().encodeToString(xlsxBytes);

		        return ResponseEntity.ok()
		                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		                .body(xlsxBase64);

		    } catch (Exception e) {
		        // Log the exception for debugging
		        e.printStackTrace();
		        return ResponseEntity.badRequest().body("Error generating Excel");
		    }
		}
	
	
	
	
	
	//invoiceData Print 1
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
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String formattedStartDate = sdf.format(StartDate);
			String formattedStartDate1 = sdf.format(EndDate);
			
			context.setVariable("StartDate", formattedStartDate);
			context.setVariable("EndDate", formattedStartDate1);
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
	 
	 
	 
	//invoiceData Print 2
		 @PostMapping("/Print2/{companyid}/{branchId}/{selectedParty}")
		    public ResponseEntity<?> generateInvoicePdf2(
		            @PathVariable("companyid") String companyId,
		            @PathVariable("branchId") String branchId,	
		            @PathVariable("selectedParty") String partyId,	 
		            @RequestParam("formattedStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date StartDate,
		            @RequestParam("formattedEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date EndDate
	           
		           
		            ) throws Exception {
			try {
				// Create a Thymeleaf context

				Context context = new Context();		
				List<String> names = new ArrayList<>();
				
				double totalInvoiceAmount = 0;
				double totalAmountPaid = 0;
			
				List<InvoiceMain> InvoiceList =	invoiceRepository.findInvoiceData(companyId, branchId, StartDate, EndDate, partyId);

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
//					image.setAlignment(Element.ALIGN_CENTER);
//					document.add(image);
					context.setVariable("dgdclogo", image);
				} else {
					System.out.println("img not here");// Handle the case where the image does not exist
				}
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String formattedStartDate = sdf.format(StartDate);
				String formattedStartDate1 = sdf.format(EndDate);
				
				context.setVariable("StartDate", formattedStartDate);
				context.setVariable("EndDate", formattedStartDate1);
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
		 

}
