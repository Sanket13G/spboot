package com.cwms.service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.function.ToDoubleFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.cwms.entities.Branch;
import com.cwms.entities.InvoiceMain;
import com.cwms.entities.InvoicePackages;
import com.cwms.entities.InvoiceTaxDetails;
import com.cwms.entities.Party;
import com.cwms.invoice.ServiceIdMappingRepositary;
import com.cwms.repository.BranchRepo;
import com.cwms.repository.InvoicePackagesRepositary;
import com.cwms.repository.InvoiceRepositary;

@Service
public class InvoiceServiceIMPL 
{	
	@Autowired
	private InvoiceRepositary invoiceRepositary;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	public cfsTarrifServiceService cfsTarrifServiceService;

	@Autowired
	public CFSTariffRangeService CFSTariffRangeService;
	
	@Autowired
	private PartyService PartyService;
	
	@Autowired
	private BranchRepo branchRepo;
	
	@Autowired
	private InvoicePackagesRepositary InvoicePackagesRepositary;
	
	@Autowired
	private ServiceIdMappingRepositary ServiceIdMappingRepositary;
	
//	@Autowired
//	private InvoiceDetailRepostary detailRepostary;
	
	
	public List<String> getInvoiceNoListByPartyId(String compId,String branchId,String partyid,Date StartDate,Date endDate)
	{
		return invoiceRepositary.findInvoiceNumbersByCriteria(compId, branchId, partyid,StartDate,endDate);
	}
	
	
	
	
	public InvoiceMain addInvoice(InvoiceMain invoiceMain)
	{		
		return invoiceRepositary.save(invoiceMain);
	}
	
	public InvoiceMain getByInvoiceNo(String compId,String branchId,String partyid,String invoiceNo)
	{
		return invoiceRepositary.findByCompanyIdAndBranchIdAndPartyIdAndInvoiceNO(compId, branchId, partyid, invoiceNo);
	}
	public List<InvoiceMain> getByPartyId(String compId,String branchId,String partyid)
	{
		return invoiceRepositary.findByCompanyIdAndBranchIdAndPartyId(compId, branchId, partyid);
	}

//	Invoice Report 
	
//	Single Invice Report

	public byte[] FunctionSingleInvice(String companyId, String branchId, String invoiceNo,
			List<InvoiceTaxDetails> byInvoiceNo) throws Exception {
		try {

			// Create a Thymeleaf context

			Context context = new Context();

			String partyId = byInvoiceNo.get(0).getPartyId();

			Party byParty = PartyService.findPartyById(companyId, branchId, partyId);
			context.setVariable("party", byParty);
//						context.setVariable("gstNo", byParty.getGstNo());

			Branch findByCompany_Id = branchRepo.findByCompanyIdAndBranchId(companyId, branchId);

			context.setVariable("Branch", findByCompany_Id);

			InvoiceMain byInvoiceNo2 = getByInvoiceNo(companyId, branchId, partyId, invoiceNo);

			String importserviceId = ServiceIdMappingRepositary.findServieIdByKeys(companyId, branchId, "import pckgs");
			String exportserviceId = ServiceIdMappingRepositary.findServieIdByKeys(companyId, branchId, "export pckgs");

			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
			context.setVariable("InvoiceDate", dateFormat2.format(byInvoiceNo2.getInvoiceDate()));
			double taxAmount = byInvoiceNo2.getTaxAmount();
			double invoiceAmount = byInvoiceNo2.getTotalInvoiceAmount();
			context.setVariable("TotalInvoiceAmount", Math.round(invoiceAmount));

			context.setVariable("TotalTax", Math.round(taxAmount));
			if ("Y".equals(byInvoiceNo2.getIgst())) {
				context.setVariable("IGST", Math.round(taxAmount));
				context.setVariable("CGST", 0);
				context.setVariable("SGST", 0);
			} else {
				context.setVariable("IGST", 0);
				context.setVariable("CGST", Math.round(taxAmount / 2));
				context.setVariable("SGST", Math.round(taxAmount / 2));

			}

			double SingleimportRate;

			double SingleexportRate;
			SingleimportRate = cfsTarrifServiceService.findRateServiceByTarrifNo(companyId, branchId,
					byInvoiceNo2.getTariffNo(), byInvoiceNo2.getTariffAmndNo(), importserviceId);

			SingleexportRate = cfsTarrifServiceService.findRateServiceByTarrifNo(companyId, branchId,
					byInvoiceNo2.getTariffNo(), byInvoiceNo2.getTariffAmndNo(), exportserviceId);

			context.setVariable("importSimpleRate", Math.round(SingleimportRate));
			context.setVariable("exportSimpleRate", Math.round(SingleexportRate));
			context.setVariable("Invoice", byInvoiceNo2);
			context.setVariable("invoiceDetails", byInvoiceNo);

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String currentDateTime = dateFormat.format(new Date());
			context.setVariable("currentDateTime", currentDateTime);

			InvoicePackages InvoicePackageDetails = InvoicePackagesRepositary
					.findByCompanyIdAndBranchIdAndPartyIdAndInvoiceNO(companyId, branchId, partyId, invoiceNo);

			double sumOfRates = InvoicePackageDetails.getImportRate() + InvoicePackageDetails.getExportRate()
					+ InvoicePackageDetails.getImportSubRate() + InvoicePackageDetails.getExportSubRate()
					+ InvoicePackageDetails.getExportSplCartRate() + InvoicePackageDetails.getExportPcRate()
					+ InvoicePackageDetails.getHolidayRate() + InvoicePackageDetails.getExportHpRate()
					+ InvoicePackageDetails.getExportOcRate() + InvoicePackageDetails.getImportSplCartRate()
					+ InvoicePackageDetails.getImportPcRate() + InvoicePackageDetails.getImportHpRate()
					+ InvoicePackageDetails.getImportOcRate() + InvoicePackageDetails.getDemuragesRate();

			context.setVariable("sumNiptNoOfPackages", InvoicePackageDetails.getNiptPackages());

//						System.out.println("Total Rates "+sumOfRates);

			String amountWords = convertToIndianCurrency((long) sumOfRates);

//						 System.out.println("Total Rates "+amountWords);
			context.setVariable("amountWords", amountWords);

			context.setVariable("sumTotalRates", Math.round(sumOfRates));

//						context.setVariable("sumTotalRates", Math.round(sumOfRates));

			int sumOfNop = InvoicePackageDetails.getImportNop() + InvoicePackageDetails.getExportNop()
					+ InvoicePackageDetails.getImportSubNop() + InvoicePackageDetails.getExportSubNop();

			context.setVariable("sumTotalNop", Math.round(sumOfNop));
			context.setVariable("packageDetails", InvoicePackageDetails);
			context.setVariable("Branch", findByCompany_Id);
			context.setVariable("Invoice", byInvoiceNo2);

			// Process the HTML template with dynamic values
			String htmlContent = templateEngine.process("Invoice", context);

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
//			String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);

			return pdfBytes;
		} catch (Exception e) {
			// Handle exceptions appropriately
			return null;
		}
	}
	
	
	
	
	
//	Single BIll Pdf Download by Invice Number

	public byte[] FunctionForBillGeneration(String companyId, String branchId, String invoiceNo,
			List<InvoiceTaxDetails> byInvoiceNo) throws Exception {

		try {

			Context context = new Context();

			double sumImportNoOfPackages = calculateSum(byInvoiceNo, InvoiceTaxDetails::getImportNoOfPackages);
			double sumExportNoOfPackages = calculateSum(byInvoiceNo, InvoiceTaxDetails::getExportNoOfPackages);

			double sumHoliday = calculateSum(byInvoiceNo, InvoiceTaxDetails::getHolidayRate);
			double sumImportSCRate = calculateSum(byInvoiceNo, InvoiceTaxDetails::getImportScRate);
			double sumImportPCRate = calculateSum(byInvoiceNo, InvoiceTaxDetails::getImportPcRate);
			double sumImportHeavyRate = calculateSum(byInvoiceNo, InvoiceTaxDetails::getImportHpRate);
			double sumImportPenalty = calculateSum(byInvoiceNo, InvoiceTaxDetails::getImportPenalty);
			double sumExportSCRate = calculateSum(byInvoiceNo, InvoiceTaxDetails::getExportScRate);
			double sumExportPCRate = calculateSum(byInvoiceNo, InvoiceTaxDetails::getExportPcRate);
			double sumExportHeavyRate = calculateSum(byInvoiceNo, InvoiceTaxDetails::getExportHpRate);
			double sumExportPenalty = calculateSum(byInvoiceNo, InvoiceTaxDetails::getExportPenalty);
			double sumSubImportNop = calculateSum(byInvoiceNo, InvoiceTaxDetails::getImportSubNop);
			double sumSubExportNop = calculateSum(byInvoiceNo, InvoiceTaxDetails::getExportSubNop);
			double sumDemuragesRate = calculateSum(byInvoiceNo, InvoiceTaxDetails::getDemuragesRate);

			double sumTotal = sumImportNoOfPackages + sumExportNoOfPackages + sumSubImportNop + sumSubExportNop;

			double sumNiptNoOfPackages = calculateSum(byInvoiceNo, InvoiceTaxDetails::getNiptPackages);
			context.setVariable("sumNiptNoOfPackages", Math.round(sumNiptNoOfPackages));

			context.setVariable("sumDemuragesRate", Math.round(sumDemuragesRate));
			context.setVariable("sumSubExportNop", Math.round(sumSubExportNop));
			context.setVariable("sumSubImportNop", Math.round(sumSubImportNop));
			context.setVariable("sumImportNoOfPackages", Math.round(sumImportNoOfPackages));
			context.setVariable("sumExportNoOfPackages", Math.round(sumExportNoOfPackages));
			context.setVariable("sumTotal", Math.round(sumTotal));
			context.setVariable("sumHoliday", Math.round(sumHoliday));
			context.setVariable("sumImportSCRate", Math.round(sumImportSCRate));
			context.setVariable("sumImportPCRate", Math.round(sumImportPCRate));
			context.setVariable("sumImportHeavyRate", Math.round(sumImportHeavyRate));
			context.setVariable("sumImportPenalty", Math.round(sumImportPenalty));
			context.setVariable("sumExportSCRate", Math.round(sumExportSCRate));
			context.setVariable("sumExportPCRate", Math.round(sumExportPCRate));
			context.setVariable("sumExportHeavyRate", Math.round(sumExportHeavyRate));
			context.setVariable("sumExportPenalty", Math.round(sumExportPenalty));

			Date fromDate = byInvoiceNo.stream().map(InvoiceTaxDetails::getInvoiceDate).min(Date::compareTo)
					.orElse(null);

			Date toDate = byInvoiceNo.stream().map(InvoiceTaxDetails::getInvoiceDate).max(Date::compareTo).orElse(null);

			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

			context.setVariable("fromDate", dateFormat2.format(fromDate));
			context.setVariable("toDate", dateFormat2.format(toDate));

			String partyId = byInvoiceNo.get(0).getPartyId();

			Party byParty = PartyService.findPartyById(companyId, branchId, partyId);
			context.setVariable("party", byParty.getPartyName());
			context.setVariable("gstNo", byParty.getGstNo());

			Branch findByCompany_Id = branchRepo.findByCompanyIdAndBranchId(companyId, branchId);

			context.setVariable("Branch", findByCompany_Id);

			InvoiceMain byInvoiceNo2 = getByInvoiceNo(companyId, branchId, partyId, invoiceNo);

			String importserviceId = ServiceIdMappingRepositary.findServieIdByKeys(companyId, branchId, "import pckgs");
			String exportserviceId = ServiceIdMappingRepositary.findServieIdByKeys(companyId, branchId, "export pckgs");
			double SingleimportRate;

			double SingleexportRate;
			SingleimportRate = cfsTarrifServiceService.findRateServiceByTarrifNo(companyId, branchId,
					byInvoiceNo2.getTariffNo(), byInvoiceNo2.getTariffAmndNo(), importserviceId);

			SingleexportRate = cfsTarrifServiceService.findRateServiceByTarrifNo(companyId, branchId,
					byInvoiceNo2.getTariffNo(), byInvoiceNo2.getTariffAmndNo(), exportserviceId);

			context.setVariable("importSimpleRate", Math.round(SingleimportRate));
			context.setVariable("exportSimpleRate", Math.round(SingleexportRate));

			context.setVariable("Invoice", byInvoiceNo2);
			context.setVariable("invoiceDetails", byInvoiceNo);
			context.setVariable("title", "Sample PDF");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String currentDateTime = dateFormat.format(new Date());
			context.setVariable("currentDateTime", currentDateTime);

			double taxAmount = byInvoiceNo2.getTaxAmount();
			double invoiceAmount = byInvoiceNo2.getTotalInvoiceAmount();
			context.setVariable("TotalInvoiceAmount", Math.round(invoiceAmount));

			context.setVariable("TotalTax", Math.round(taxAmount));
			if ("Y".equals(byInvoiceNo2.getIgst())) {
				context.setVariable("IGST", Math.round(taxAmount));
				context.setVariable("CGST", 0);
				context.setVariable("SGST", 0);
			} else {
				context.setVariable("IGST", 0);
				context.setVariable("CGST", Math.round(taxAmount / 2));
				context.setVariable("SGST", Math.round(taxAmount / 2));

			}
			InvoicePackages InvoicePackageDetails = InvoicePackagesRepositary
					.findByCompanyIdAndBranchIdAndPartyIdAndInvoiceNO(companyId, branchId, partyId, invoiceNo);

			context.setVariable("invoicePackage", InvoicePackageDetails);

			// Process the HTML template with dynamic values
			String htmlContent = templateEngine.process("Bill_Invoice", context);

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
			context.clearVariables();

			return pdfBytes;
		} catch (Exception e) {
			// Handle exceptions appropriately
			return null;
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 private static final String[] ones = {
	            "", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine",
	            " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen"
	    };

	    private static final String[] tens = {
	            "", "", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety"
	    };

	    public static String convertToIndianCurrency(long number) {
	        if (number == 0) {
	            return "Zero Rupees";
	        }

	        if (number > 10000000) {
	            return "Amount exceeds 10 Crores";
	        }

	        return convertToWords((int) (number / 10000000), " Crore") +
	                convertToWords((int) ((number / 100000) % 100), " Lakh") +
	                convertToWords((int) ((number / 1000) % 100), " Thousand") +
	                convertToWords((int) ((number / 100) % 10), " Hundred") +
	                convertToWords((int) (number % 100), " Rupees");
	    }

	    private static String convertToWords(int num, String unit) {
	        if (num == 0) {
	            return "";
	        } else if (num < 20) {
	            return ones[num] + unit;
	        } else {
	            return tens[num / 10] + ones[num % 10] + unit;
	        }
	    }
	    private double calculateSum(List<InvoiceTaxDetails> invoiceTaxDetails,
				ToDoubleFunction<InvoiceTaxDetails> columnExtractor) {
			return invoiceTaxDetails.stream().mapToDouble(columnExtractor).sum();
		}
}