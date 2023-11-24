package com.cwms.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.cwms.entities.Branch;
import com.cwms.entities.CfsTarrif;
import com.cwms.entities.DemuragePackagesHistory;
import com.cwms.entities.Export;
import com.cwms.entities.ExportHeavyPackage;
import com.cwms.entities.ExportSub;
import com.cwms.entities.Import;
import com.cwms.entities.ImportHeavyPackage;
import com.cwms.entities.ImportSub;
import com.cwms.entities.InvoiceDetail;
import com.cwms.entities.InvoiceMain;
import com.cwms.entities.InvoicePackages;
import com.cwms.entities.InvoiceTaxDetails;
import com.cwms.entities.Party;
import com.cwms.helper.CombinedImportExport;
import com.cwms.invoice.ServiceIdMappingRepositary;
import com.cwms.repository.BranchRepo;
import com.cwms.repository.ExportHeavyPackageRepo;
import com.cwms.repository.ExportRepository;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.ImportRepo;
import com.cwms.repository.ImportSubRepository;
import com.cwms.repository.InvoicePackagesRepositary;
import com.cwms.repository.InvoiceRepositary;
import com.cwms.repository.PartyRepository;
import com.cwms.service.CFSService;
import com.cwms.service.CFSTariffRangeService;
import com.cwms.service.ExportService;
import com.cwms.service.HolidayService;
import com.cwms.service.ImportHeavyService;
import com.cwms.service.ImportService;
import com.cwms.service.InvoiceDetailServiceIMPL;
import com.cwms.service.InvoiceServiceIMPL;
import com.cwms.service.InvoiceTaxDetailsServiceIMPL;
import com.cwms.service.PartyService;
import com.cwms.service.ProcessNextIdService;
import com.cwms.service.cfsTarrifServiceService;
import com.itextpdf.text.Image;

@RestController
@RequestMapping("Invoice")
@CrossOrigin("*")
public class InvoiceController {
	
	@Autowired
	private InvoiceRepositary invoiceRepositary;
	
	@Autowired
	public PartyRepository partyRepository; 
	
	
	@Autowired
	private PartyRepository partyrepo;
	
	@Autowired
	public ProcessNextIdService proccessNextIdService;

	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	public ImportRepo importRepo;
	
	@Autowired
	private ExportRepository exportrepo;

	@Autowired
	public InvoiceRepositary invoiceRepository;


	@Autowired
	public ImportSubRepository impsubRepo;
	
	@Autowired
	public ExportSubRepository expsubRepo;
	
	@Autowired
	private PartyService PartyService;

	@Autowired
	public CFSService CFSService;

	@Autowired
	private ServiceIdMappingRepositary ServiceIdMappingRepositary;

	@Autowired
	private HolidayService holidayService;

	@Autowired
	public cfsTarrifServiceService cfsTarrifServiceService;

	@Autowired
	public CFSTariffRangeService CFSTariffRangeService;

	@Autowired
	private InvoiceDetailServiceIMPL InvoiceDetailServiceIMPL;

	@Autowired
	private InvoiceServiceIMPL invoiceServiceIMPL;

	@Autowired
	private InvoiceTaxDetailsServiceIMPL InvoiceTaxDetailsServiceIMPL;

	@Autowired
	private BranchRepo branchRepo;

	@Autowired
	private InvoicePackagesRepositary InvoicePackagesRepositary;

	@Autowired
	public ExportService ExportService;

	@Autowired
	public ImportHeavyService ImportHeavyService;

	@Autowired
	public ExportHeavyPackageRepo ExportHeavyPackageRepo;

	@Autowired
	public ImportService importService;
	
	
	
	
	@GetMapping("/getInvoiceNoListByParty")
	public List<String> getInvoiceNoListByParty(@RequestParam(name = "companyid", required = false) String CompanyId,
			@RequestParam(name = "branchId", required = false) String branchId,
			@RequestParam(name = "PartyId", required = false) String PartyId,
			@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		return invoiceServiceIMPL.getInvoiceNoListByPartyId(CompanyId, branchId, PartyId,startDate,endDate);
	}
	
	
	@GetMapping("/getInvoiceNoListByPartyAndInvoiceNumber")
	public List<DemuragePackagesHistory> getInvoiceNoListByPartyAndInvoiceNo(@RequestParam(name = "companyid", required = false) String CompanyId,
			@RequestParam(name = "branchId", required = false) String branchId,
			@RequestParam(name = "PartyId", required = false) String PartyId,
			@RequestParam(name = "invoiceNo", required = false) String invoiceNo,
			@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		return InvoiceTaxDetailsServiceIMPL.getbyDemuragesByPartyIdAndInvoiceNumber(CompanyId, branchId, PartyId,startDate,endDate,invoiceNo);
	}
	
	
	

	@GetMapping("/{compId}/{branchId}/{invoiceNo}/getDetailByInvoiceNo")
	public List<InvoiceDetail> getDetailById(@PathVariable("compId") String CompanyId,
			@PathVariable("branchId") String branchId, @PathVariable("invoiceNo") String invoiceNo) {
		return InvoiceDetailServiceIMPL.getByInvoiceNo(CompanyId, branchId, invoiceNo);
	}

	@GetMapping("/InvoiceGeneration")
	public ResponseEntity<?> generateInvoice(@RequestParam(name = "companyid", required = false) String companyid,
			@RequestParam(name = "branchId", required = false) String branchId,
			@RequestParam(name = "PartyId", required = false) String PartyId,
			@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		try {

			Party PartyByID = PartyService.findPartyById(companyid, branchId, PartyId);
			String findPartyNameById = PartyByID.getPartyName();
			String InvoiceNumber = proccessNextIdService.autoIncrementInvoiceNumber();
			String BillNumber = proccessNextIdService.autoIncrementBillNumber();
			double[] amounts = { 0.0, 0.0, 0.0 };

			double[] Penalty = { 0.0, 0.0 };

			double[] InvoicePackagesRates = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
			int[] InvoicePackageNo = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

			int[] niptPackages = { 0 };

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_MONTH, 10);
			Date futureDate = calendar.getTime();
			Date invoiceDate = PartyByID.getLastInVoiceDate();

			if (invoiceDate != null) {
				Calendar calendar2 = Calendar.getInstance();
				calendar2.setTime(invoiceDate);
				calendar2.add(Calendar.DAY_OF_MONTH, 1);
				startDate = calendar2.getTime();
			}

			List<Object[]> ExtractingInvoiceData = importRepo.getCombinedImportExportData(companyid, branchId, PartyId,
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

			List<CombinedImportExport> combinedDataList = ExtractingInvoiceData.stream().map(array -> {

				String partyId = (String) array[0];
				BigDecimal importHpWeight = (array[6] != null) ? (BigDecimal) array[6] : BigDecimal.ZERO;

				int exportNoOfPackages = (array[7] != null) ? Integer.parseInt(array[7].toString()) : 0;
				BigDecimal exportHpWeight = (array[11] != null) ? (BigDecimal) array[11] : BigDecimal.ZERO;

				
				double exportPenaltyLocal =0; 
				double importPenaltyLocal = 0;

				int exportSubNoOfPackages = (array[16] != null) ? Integer.parseInt(array[16].toString()) : 0;
				int importSubNoOfPackages = (array[14] != null) ? Integer.parseInt(array[14].toString()) : 0;

				double exportSubPenaltyLocal = 0;
				double importSubPenaltyLocal =0;
				


				Date date = new Date(((java.util.Date) array[1]).getTime());
		
				
				
				int importNoOfPackages = (array[2] != null) ? Integer.parseInt(array[2].toString()) : 0;
				// Total Packages
				int totalPackages = importNoOfPackages + exportNoOfPackages;

//			Rates for specific conditions 
				double demuragesRate = 0.0;
				int demuragesNop = 0;
				double importRate = 0.0;
				double exportRate = 0.0;
				double importPcRate = 0.0;
				double importHeavyRate = 0.0;
				double HolidayRate = 0.0;
				double importScRate = 0.0;
				double exportPcRate = 0.0;
				double exportHeavyRate = 0.0;
				double exportScRate = 0.0;

				double importSubRate = 0.0;
				double exportSubRate = 0.0;

				int InnerniptPackages = 0;

//				if ("Y".equals(niptStatus)) {
//					InnerniptPackages += importNoOfPackages;
//					niptPackages[0] += importNoOfPackages;
//				}

//				if (exportPenaltyLocal > 0.0) {
//					// If exportPenaltyLocal is greater than 0.0, add exportNoOfpackages to
//					// InvoicePackageNo[8]
//					InvoicePackageNo[8] += exportNoOfPackages;
//				}

//				if (exportSubPenaltyLocal > 0.0) {
//					// If exportSubPenaltyLocal is greater than 0.0, add exportsubnop to
//					// InvoicePackageNo[8]
//					InvoicePackageNo[8] += exportSubNoOfPackages;
//				}
//
//				if (importPenaltyLocal > 0.0) {
//					// If exportPenaltyLocal is greater than 0.0, add exportNoOfpackages to
//					// InvoicePackageNo[8]
//					InvoicePackageNo[12] += importNoOfPackages;
//				}
//
//				if (importSubPenaltyLocal > 0.0) {
//					// If exportSubPenaltyLocal is greater than 0.0, add exportsubnop to
//					// InvoicePackageNo[8]
//					InvoicePackageNo[12] += importSubNoOfPackages;
//				}

//				Date currentDate = new Date();
//
//				long timeDifferenceMillis = currentDate.getTime() - date.getTime();
//				int daysDifference = (int) (timeDifferenceMillis / (1000 * 60 * 60 * 24));

				boolean isHoliday = holidayService.findByDate(companyid, branchId, date);
				boolean isSecondSaturday = isSecondSaturday(date);

				// Set holidayStatus based on whether it's a holiday
				String holidayStatus = (isHoliday || isSecondSaturday) ? "Y" : "N";
	
				
				
//				Import
				
				if (importNoOfPackages > 0) {
					
					List<Import> findByPartyIdofSirDate = importService.findByPartyIdofSirDate(companyid, branchId, PartyId, date);
					
					
					
					for(Import imp:findByPartyIdofSirDate)
					{
						Date toBeSend = (imp.getOutDate() != null) ? imp.getOutDate() : new Date();
						long timeDifferenceMillis = toBeSend.getTime() - date.getTime();
						int daysDifference = (int) (timeDifferenceMillis / (1000 * 60 * 60 * 24));						
						
						
						
						
						if(imp.getNop() > 0)
						{
							
							if(imp.getNiptStatus() != null && imp.getNiptStatus().equals("Y"))
							{
								InnerniptPackages += imp.getNop();
								niptPackages[0] += imp.getNop();
							}						
							
							if(imp.getImposePenaltyAmount() > 0)
							{
								importPenaltyLocal += imp.getImposePenaltyAmount(); 
								Penalty[0] += imp.getImposePenaltyAmount();
								InvoicePackageNo[12] += imp.getNop();
							}
							
							
						
						String importserviceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"import pckgs");

						InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
								InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
								importserviceId, PARTYID, imp.getNop(), userId, date);
						double importTaxAmount = addInvoiceDetail.getTaxAmount();
						double importBillAmount = addInvoiceDetail.getBillAmount();
						double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();

						importRate += importBillAmount;

						amounts[0] += importTaxAmount;
						amounts[1] += importBillAmount;
						amounts[2] += importTotalInvoiceAmount;	
						
						
						DemuragePackagesHistory demorage =new DemuragePackagesHistory();
						demorage.setCompanyId(companyid);
						demorage.setBranchId(branchId);
						demorage.setPartyId(PartyId);
						demorage.setInDate(date);
						demorage.setInviceDate(new Date());
						demorage.setMasterNo(imp.getMawb());
						demorage.setInviceNo(InvoiceNumber);
						demorage.setBillNo(BillNumber);
						demorage.setSubMasterNo(imp.getHawb());
						demorage.setOutDate(toBeSend);
						demorage.setPackages(imp.getNop());
						demorage.setDemurageRate(importBillAmount *  imp.getNop());
						demorage.setPackageType("Import Nop");
						InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);		
						
						
						
						
						
						
						}
						
						if(imp.getPcStatus() != null && imp.getPcStatus().equals("Y"))
						{
							
							String importPcServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
									"import PC");
							InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
									InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
									importPcServiceId, PARTYID, imp.getNop(), userId, date);
							double importTaxAmount = addInvoiceDetail.getTaxAmount();
							double importBillAmount = addInvoiceDetail.getBillAmount();
							double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();

							importPcRate += importBillAmount;
							InvoicePackageNo[10] += imp.getNop();
							amounts[0] += importTaxAmount;
							amounts[1] += importBillAmount;
							amounts[2] += importTotalInvoiceAmount;	
							
							DemuragePackagesHistory demorage =new DemuragePackagesHistory();
							demorage.setCompanyId(companyid);
							demorage.setBranchId(branchId);
							demorage.setPartyId(PartyId);
							demorage.setInDate(date);
							demorage.setInviceDate(new Date());
							demorage.setMasterNo(imp.getMawb());
							demorage.setInviceNo(InvoiceNumber);
							demorage.setBillNo(BillNumber);
							demorage.setSubMasterNo(imp.getHawb());
							demorage.setOutDate(toBeSend);
							demorage.setPackages(imp.getNop());
							demorage.setDemurageRate(importBillAmount *  imp.getNop());
							demorage.setPackageType("Import PC");
							InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);		
							
							
							
							
						}
						
						if(imp.getScStatus() != null && imp.getScStatus().equals("Y"))
						{
							
							String importScServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
									"import SC");
							InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
									InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
									importScServiceId, PARTYID, imp.getNop(), userId, date);
							double importTaxAmount = addInvoiceDetail.getTaxAmount();
							double importBillAmount = addInvoiceDetail.getBillAmount();
							double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();

							importScRate += importBillAmount;
							InvoicePackageNo[9] += imp.getNop();

							amounts[0] += importTaxAmount;
							amounts[1] += importBillAmount;
							amounts[2] += importTotalInvoiceAmount;	
							
							
							DemuragePackagesHistory demorage =new DemuragePackagesHistory();
							demorage.setCompanyId(companyid);
							demorage.setBranchId(branchId);
							demorage.setPartyId(PartyId);
							demorage.setInDate(date);
							demorage.setInviceDate(new Date());
							demorage.setMasterNo(imp.getMawb());
							demorage.setInviceNo(InvoiceNumber);
							demorage.setBillNo(BillNumber);
							demorage.setSubMasterNo(imp.getHawb());
							demorage.setOutDate(toBeSend);
							demorage.setPackages(imp.getNop());
							demorage.setDemurageRate(importBillAmount *  imp.getNop());
							demorage.setPackageType("Import SC");
							InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);	
							
							
							
							
						}
						
						if(imp.getHpStatus() != null && imp.getHpStatus().equals("Y"))
						{
							
							String importHPServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
									"import HP");

//							Import InvoiceImport = importService.findForInvoice(companyid, branchId, date, PartyId,
//									importNoOfPackages, importPcStatus, importScStatus, importHpStatus);

							List<ImportHeavyPackage> byMAWBImport = ImportHeavyService.getByMAWB(companyid, branchId,
									imp.getImpTransId(), imp.getMawb(), imp.getHawb(),
									imp.getSirNo());

							List<BigDecimal> weights = byMAWBImport.stream().map(ImportHeavyPackage::getHpWeight)
									.collect(Collectors.toList());

							InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetailHeavyWeight(companyid,
									branchId, InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
									importHPServiceId, PARTYID, importHpWeight.intValue(), userId, date, weights);
							double importTaxAmount = addInvoiceDetail.getTaxAmount();
							double importBillAmount = addInvoiceDetail.getBillAmount();
							double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();

							importHeavyRate += importBillAmount;

//							System.out.println("Import Heavy Rate "+importHeavyRate );

							InvoicePackageNo[11] += imp.getNop();

							amounts[0] += importTaxAmount;
							amounts[1] += importBillAmount;
							amounts[2] += importTotalInvoiceAmount;	
							
							
							
							DemuragePackagesHistory demorage =new DemuragePackagesHistory();
							demorage.setCompanyId(companyid);
							demorage.setBranchId(branchId);
							demorage.setPartyId(PartyId);
							demorage.setInDate(date);
							demorage.setInviceDate(new Date());
							demorage.setMasterNo(imp.getMawb());
							demorage.setInviceNo(InvoiceNumber);
							demorage.setBillNo(BillNumber);
							demorage.setSubMasterNo(imp.getHawb());
							demorage.setOutDate(toBeSend);
							demorage.setPackages(imp.getNop());
							demorage.setDemurageRate(importBillAmount *  imp.getNop());
							demorage.setPackageType("Import HP");
							InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);	
							
							
							
						}
						
//						Demurages Charges For Import Sub Packages
						
						
						
						if(daysDifference > 0)
						{
						String demurageId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"export DM");

						InvoiceDetail addInvoiceDetail2 = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
								InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
								demurageId, PARTYID, daysDifference, userId, date);
						double importTaxAmount2 = addInvoiceDetail2.getTaxAmount();
						double importBillAmount2 = addInvoiceDetail2.getBillAmount();
						double importTotalInvoiceAmount2 = addInvoiceDetail2.getTotalInvoiceAmount();

						if(importTotalInvoiceAmount2 > 0)
						{							

						InvoicePackageNo[13] +=  imp.getNop();

						demuragesNop +=  imp.getNop() ;
						demuragesRate += importBillAmount2 *  imp.getNop() ;

						amounts[0] += importTaxAmount2 * imp.getNop() ;
						amounts[1] += importBillAmount2 * imp.getNop() ;
						amounts[2] += importTotalInvoiceAmount2	*  imp.getNop() ;
						
//						Save in Demurage Table 
						DemuragePackagesHistory demorage =new DemuragePackagesHistory();
						demorage.setCompanyId(companyid);
						demorage.setBranchId(branchId);
						demorage.setPartyId(PartyId);
						demorage.setInDate(date);
						demorage.setInviceDate(new Date());
						demorage.setMasterNo(imp.getMawb());
						demorage.setInviceNo(InvoiceNumber);
						demorage.setBillNo(BillNumber);
						demorage.setSubMasterNo(imp.getHawb());
						demorage.setOutDate(toBeSend);
						demorage.setPackages(imp.getNop());
						demorage.setDemurageRate(importBillAmount2 *  imp.getNop());
						demorage.setPackageType("Import DM");
						InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);			
					
						}
					}			
				}
			}				
				
//			EXPORT

				if (exportNoOfPackages > 0) {
					
					
					List<Export> findByExportsBySerDate = exportrepo.findByCompanyIdAndBranchIdAndNameOfExporterAndSerDateAndStatusNot(companyid, branchId, PartyId, date, "D");
					
					for(Export exp:findByExportsBySerDate)
					{
						
						Date toBeSend = (exp.getOutDate() != null) ? exp.getOutDate() : new Date();
						long timeDifferenceMillis = toBeSend.getTime() - date.getTime();
						int daysDifference = (int) (timeDifferenceMillis / (1000 * 60 * 60 * 24));
						
						
						if(exp.getNoOfPackages() > 0)
						{
							
							if(exp.getImposePenaltyAmount() > 0) 
							{
								
								exportPenaltyLocal += exp.getImposePenaltyAmount();
								Penalty[1] += exp.getImposePenaltyAmount();
								InvoicePackageNo[8] += exp.getNoOfPackages();								
								
							}
							
							
							String exportserviceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
									"export pckgs");
							InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
									InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
									exportserviceId, PARTYID, exp.getNoOfPackages(), userId, date);
							double importTaxAmount = addInvoiceDetail.getTaxAmount();
							double importBillAmount = addInvoiceDetail.getBillAmount();
							double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();
							exportRate += importBillAmount;

							amounts[0] += importTaxAmount;
							amounts[1] += importBillAmount;
							amounts[2] += importTotalInvoiceAmount;	
							
							DemuragePackagesHistory demorage =new DemuragePackagesHistory();
							demorage.setCompanyId(companyid);
							demorage.setBranchId(branchId);
							demorage.setPartyId(PartyId);
							demorage.setInDate(date);
							demorage.setInviceNo(InvoiceNumber);
							demorage.setBillNo(BillNumber);
							
							demorage.setInviceDate(new Date());
							demorage.setMasterNo(exp.getSbNo());
							demorage.setSubMasterNo(exp.getSbRequestId());		
							demorage.setOutDate(toBeSend);
							demorage.setPackages(exp.getNoOfPackages());
							demorage.setDemurageRate(importBillAmount *  exp.getNoOfPackages());
							demorage.setPackageType("Export NOP");
							InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);
							
							
							
							
						}

						if(exp.getPcStatus() !=null && exp.getPcStatus().equals("Y"))
						{
							
							String exportPcServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
									"export PC");
							InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
									InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
									exportPcServiceId, PARTYID, exp.getNoOfPackages(), userId, date);
							double importTaxAmount = addInvoiceDetail.getTaxAmount();
							double importBillAmount = addInvoiceDetail.getBillAmount();
							double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();

							exportPcRate += importBillAmount;

							InvoicePackageNo[6] += exp.getNoOfPackages();

							amounts[0] += importTaxAmount;
							amounts[1] += importBillAmount;
							amounts[2] += importTotalInvoiceAmount;
							
							DemuragePackagesHistory demorage =new DemuragePackagesHistory();
							demorage.setCompanyId(companyid);
							demorage.setBranchId(branchId);
							demorage.setPartyId(PartyId);
							demorage.setInDate(date);
							demorage.setInviceNo(InvoiceNumber);
							demorage.setBillNo(BillNumber);
							
							demorage.setInviceDate(new Date());
							demorage.setMasterNo(exp.getSbNo());
							demorage.setSubMasterNo(exp.getSbRequestId());		
							demorage.setOutDate(toBeSend);
							demorage.setPackages(exp.getNoOfPackages());
							demorage.setDemurageRate(importBillAmount *  exp.getNoOfPackages());
							demorage.setPackageType("Export PC");
							InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);
							
							
									
							
						}
						if(exp.getScStatus() != null && exp.getScStatus().equals("Y"))
						{
							
							String exportScServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
									"export SC");
							InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
									InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
									exportScServiceId, PARTYID, exp.getNoOfPackages(), userId, date);
							double importTaxAmount = addInvoiceDetail.getTaxAmount();
							double importBillAmount = addInvoiceDetail.getBillAmount();
							double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();

							exportScRate += importBillAmount;

							InvoicePackageNo[5] += exp.getNoOfPackages();

//							System.out.println("Export Nop " + exportNoOfPackages);

							amounts[0] += importTaxAmount;
							amounts[1] += importBillAmount;
							amounts[2] += importTotalInvoiceAmount;
							
							
							
							DemuragePackagesHistory demorage =	new DemuragePackagesHistory();
							demorage.setCompanyId(companyid);
							demorage.setBranchId(branchId);
							demorage.setPartyId(PartyId);
							demorage.setInDate(date);
							demorage.setInviceNo(InvoiceNumber);
							demorage.setBillNo(BillNumber);							
							demorage.setInviceDate(new Date());
							demorage.setMasterNo(exp.getSbNo());
							demorage.setSubMasterNo(exp.getSbRequestId());		
							demorage.setOutDate(toBeSend);
							demorage.setPackages(exp.getNoOfPackages());
							demorage.setDemurageRate(importBillAmount *  exp.getNoOfPackages());
							demorage.setPackageType("Export SC");
							InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);

							
						}
						if(exp.getHpStatus() != null && exp.getHpStatus().equals("Y"))
						{
							
							String exporthpServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
									"export HP");

//							Export InvoiceExport = ExportService.findForInvoiceExport(companyid, branchId, date, PartyId,
//									exportNoOfPackages, exportPcStatus, exportScStatus, exportHpStatus);

							List<ExportHeavyPackage> byMAWB = ExportHeavyPackageRepo.findalldata(companyid, branchId,
									exp.getSbRequestId(), exp.getSbNo());

							List<BigDecimal> weights = byMAWB.stream().map(ExportHeavyPackage::getWeight)
									.collect(Collectors.toList());

							InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetailHeavyWeight(companyid,
									branchId, InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
									exporthpServiceId, PARTYID, exportHpWeight.intValue(), userId, date, weights);
							double importTaxAmount = addInvoiceDetail.getTaxAmount();
							double importBillAmount = addInvoiceDetail.getBillAmount();
							double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();

							exportHeavyRate += importBillAmount;

							InvoicePackageNo[7] += exp.getNoOfPackages();

							amounts[0] += importTaxAmount;
							amounts[1] += importBillAmount;
							amounts[2] += importTotalInvoiceAmount;			
													
							
							DemuragePackagesHistory demorage =	new DemuragePackagesHistory();
							demorage.setCompanyId(companyid);
							demorage.setBranchId(branchId);
							demorage.setPartyId(PartyId);
							demorage.setInDate(date);
							demorage.setInviceNo(InvoiceNumber);
							demorage.setBillNo(BillNumber);							
							demorage.setInviceDate(new Date());
							demorage.setMasterNo(exp.getSbNo());
							demorage.setSubMasterNo(exp.getSbRequestId());		
							demorage.setOutDate(toBeSend);
							demorage.setPackages(exp.getNoOfPackages());
							demorage.setDemurageRate(importBillAmount *  exp.getNoOfPackages());
							demorage.setPackageType("Export HP");
							InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);
							
							
						}
						
						

						
						
						if(daysDifference > 0)
						{
						String demurageId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"export DM");

						InvoiceDetail addInvoiceDetail2 = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
								InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
								demurageId, PARTYID, daysDifference, userId, date);
						double importTaxAmount2 = addInvoiceDetail2.getTaxAmount();
						double importBillAmount2 = addInvoiceDetail2.getBillAmount();
						double importTotalInvoiceAmount2 = addInvoiceDetail2.getTotalInvoiceAmount();

						
						if(importTotalInvoiceAmount2 > 0)
						{
						InvoicePackageNo[13] +=  exp.getNoOfPackages();

						demuragesNop +=  exp.getNoOfPackages() ;
						demuragesRate += importBillAmount2 *  exp.getNoOfPackages() ;

						amounts[0] += importTaxAmount2 * exp.getNoOfPackages() ;
						amounts[1] += importBillAmount2 * exp.getNoOfPackages() ;
						amounts[2] += importTotalInvoiceAmount2	*  exp.getNoOfPackages() ;
					
//						Save in Demurage Table 
						DemuragePackagesHistory demorage =new DemuragePackagesHistory();
						demorage.setCompanyId(companyid);
						demorage.setBranchId(branchId);
						demorage.setPartyId(PartyId);
						demorage.setInDate(date);
						demorage.setInviceNo(InvoiceNumber);
						demorage.setBillNo(BillNumber);
						
						demorage.setInviceDate(new Date());
						demorage.setMasterNo(exp.getSbNo());
						demorage.setSubMasterNo(exp.getSbRequestId());		
						demorage.setOutDate(toBeSend);
						demorage.setPackages(exp.getNoOfPackages());
						demorage.setDemurageRate(importBillAmount2 *  exp.getNoOfPackages());
						demorage.setPackageType("Export DM");
						InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);						
						
						}
					}								
				}
			}			

				if (importSubNoOfPackages > 0) {
					
					
					List<ImportSub> findBySirDate = impsubRepo.findByCompanyIdAndBranchIdAndExporterAndSirDateAndStatusNot(companyid, branchId, PartyId, date, "D");
					
					for(ImportSub impsub : findBySirDate)
					{
						
						Date toBeSend = (impsub.getOutDate() != null) ? impsub.getOutDate() : new Date();
						long timeDifferenceMillis = toBeSend.getTime() - date.getTime();
						int daysDifference = (int) (timeDifferenceMillis / (1000 * 60 * 60 * 24));
						
						if(impsub.getNop() > 0)	{	
							
						
							
							if(impsub.getImposePenaltyAmount() > 0)
							{
								importSubPenaltyLocal += impsub.getImposePenaltyAmount(); 
								Penalty[0] += impsub.getImposePenaltyAmount();
								InvoicePackageNo[12] += impsub.getNop();
							}
							
							String importserviceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
									"import pckgs");

							InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
									InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
									importserviceId, PARTYID, impsub.getNop(), userId, date);
							double importTaxAmount = addInvoiceDetail.getTaxAmount();
							double importBillAmount = addInvoiceDetail.getBillAmount();
							double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();

							importSubRate += importBillAmount;
							amounts[0] += importTaxAmount;
							amounts[1] += importBillAmount;
							amounts[2] += importTotalInvoiceAmount;	
							
							
							DemuragePackagesHistory demorage =new DemuragePackagesHistory();
							demorage.setCompanyId(companyid);
							demorage.setBranchId(branchId);
							demorage.setPartyId(PartyId);
							demorage.setInDate(date);
							demorage.setInviceNo(InvoiceNumber);
							demorage.setBillNo(BillNumber);
							
							demorage.setInviceDate(new Date());
							demorage.setMasterNo(impsub.getRequestId());
							demorage.setSubMasterNo(impsub.getSirNo());		
							demorage.setOutDate(toBeSend);
							demorage.setPackages(impsub.getNop());
							demorage.setDemurageRate(importBillAmount *  impsub.getNop());
							demorage.setPackageType("ImpSub Nop");
							InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);
							
							
						
						}
//						Demurages Charges For Import Sub Packages
						
						

						
						
						if(daysDifference > 0)
						{
						String demurageId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"export DM");

						
						
						
						InvoiceDetail addInvoiceDetail2 = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
								InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
								demurageId, PARTYID, daysDifference, userId, date);
						double importTaxAmount2 = addInvoiceDetail2.getTaxAmount();
						double importBillAmount2 = addInvoiceDetail2.getBillAmount();
						double importTotalInvoiceAmount2 = addInvoiceDetail2.getTotalInvoiceAmount();
					
						if(importTotalInvoiceAmount2 > 0)
						{					
							
						InvoicePackageNo[13] +=  impsub.getNop();

						demuragesNop +=  impsub.getNop() ;
						demuragesRate += importBillAmount2 *  impsub.getNop() ;

						amounts[0] += importTaxAmount2 * impsub.getNop() ;
						amounts[1] += importBillAmount2 * impsub.getNop() ;
						amounts[2] += importTotalInvoiceAmount2	*  impsub.getNop() ;
					
//						Save in Demurage Table 
						DemuragePackagesHistory demorage =new DemuragePackagesHistory();
						demorage.setCompanyId(companyid);
						demorage.setBranchId(branchId);
						demorage.setPartyId(PartyId);
						demorage.setInDate(date);
						demorage.setMasterNo(impsub.getRequestId());
						demorage.setSubMasterNo(impsub.getSirNo());					
						demorage.setInviceDate(new Date());
						demorage.setInviceNo(InvoiceNumber);
						demorage.setBillNo(BillNumber);
						demorage.setOutDate(toBeSend);
						demorage.setPackages(impsub.getNop());
						demorage.setDemurageRate(importBillAmount2 *  impsub.getNop());
						demorage.setPackageType("ImpSub DM");
						InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);
												
				}
			}			
		}
	}
			
				
				if (exportSubNoOfPackages > 0) {
					
					List<ExportSub> findBySerDate = expsubRepo.findByCompanyIdAndBranchIdAndExporterAndSerDate(companyid, branchId, PartyId, date);
					
					for(ExportSub expsub:findBySerDate)
					{
						
						Date toBeSend = (expsub.getOutDate() != null) ? expsub.getOutDate() : new Date();
						long timeDifferenceMillis = toBeSend.getTime() - date.getTime();
						int daysDifference = (int) (timeDifferenceMillis / (1000 * 60 * 60 * 24));
						
						if(expsub.getNop() > 0)
						{
							
							if(expsub.getImposePenaltyAmount() > 0) 
							{								
								exportSubPenaltyLocal += expsub.getImposePenaltyAmount();
								Penalty[1] += expsub.getImposePenaltyAmount();
								InvoicePackageNo[8] += expsub.getNop();								
	
							}
							
							
							String exportserviceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
									"export pckgs");
							InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
									InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
									exportserviceId, PARTYID, expsub.getNop(), userId, date);
							double importTaxAmount = addInvoiceDetail.getTaxAmount();
							double importBillAmount = addInvoiceDetail.getBillAmount();
							double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();

							exportSubRate += importBillAmount;

							amounts[0] += importTaxAmount;
							amounts[1] += importBillAmount;
							amounts[2] += importTotalInvoiceAmount;	
							
							
							DemuragePackagesHistory demorage =new DemuragePackagesHistory();
							demorage.setCompanyId(companyid);
							demorage.setBranchId(branchId);
							demorage.setPartyId(PartyId);
							demorage.setInDate(date);
							demorage.setInviceNo(InvoiceNumber);
							demorage.setBillNo(BillNumber);
							demorage.setInviceDate(new Date());
							demorage.setMasterNo(expsub.getRequestId());
							demorage.setSubMasterNo(expsub.getSerNo());
							
							demorage.setOutDate(toBeSend);
							demorage.setPackages(expsub.getNop());
							demorage.setDemurageRate(importBillAmount *  expsub.getNop());
							demorage.setPackageType("ExpSub NOP");
							InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);								
							
						}
						
//						Demurages Charges For Export Sub Packages						
						
						if(daysDifference > 0)
						{
						String demurageId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
								"export DM");

						InvoiceDetail addInvoiceDetail2 = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
								InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
								demurageId, PARTYID, daysDifference, userId, date);
						double importTaxAmount2 = addInvoiceDetail2.getTaxAmount();
						double importBillAmount2 = addInvoiceDetail2.getBillAmount();
						double importTotalInvoiceAmount2 = addInvoiceDetail2.getTotalInvoiceAmount();

						if(importTotalInvoiceAmount2 > 0)
						{
						InvoicePackageNo[13] +=  expsub.getNop();
						demuragesNop +=  expsub.getNop() ;
						demuragesRate += importBillAmount2 *  expsub.getNop() ;

						amounts[0] += importTaxAmount2 * expsub.getNop() ;
						amounts[1] += importBillAmount2 * expsub.getNop() ;
						amounts[2] += importTotalInvoiceAmount2	*  expsub.getNop() ;
					
//						Save in Demurage Table 
						DemuragePackagesHistory demorage =new DemuragePackagesHistory();
						demorage.setCompanyId(companyid);
						demorage.setBranchId(branchId);
						demorage.setPartyId(PartyId);
						demorage.setInDate(date);
						demorage.setInviceNo(InvoiceNumber);
						demorage.setBillNo(BillNumber);
						demorage.setInviceDate(new Date());
						demorage.setMasterNo(expsub.getRequestId());
						demorage.setSubMasterNo(expsub.getSerNo());
						
						demorage.setOutDate(toBeSend);
						demorage.setPackages(expsub.getNop());
						demorage.setDemurageRate(importBillAmount2 *  expsub.getNop());
						demorage.setPackageType("ExpSub DM");
						InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);			
						}				
					}					
				}					
			}

				
				
				
				
				
				

				

				

				if (holidayStatus != null && holidayStatus.equals("Y")) {
					String importHolidayServiceId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
							"Holiday");
					InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
							InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
							importHolidayServiceId, PARTYID,
							totalPackages + importSubNoOfPackages + exportSubNoOfPackages, userId, date);
					double importTaxAmount = addInvoiceDetail.getTaxAmount();
					double importBillAmount = addInvoiceDetail.getBillAmount();
					double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();

					HolidayRate = importBillAmount;

					InvoicePackageNo[4] += totalPackages + importSubNoOfPackages + exportSubNoOfPackages;

					amounts[0] += importTaxAmount;
					amounts[1] += importBillAmount;
					amounts[2] += importTotalInvoiceAmount;			

				}

				
				InvoicePackageNo[0] += importNoOfPackages;
				InvoicePackageNo[1] += importSubNoOfPackages;
				InvoicePackageNo[2] += exportNoOfPackages;
				InvoicePackageNo[3] += exportSubNoOfPackages;

				InvoicePackagesRates[0] += importRate;	
				InvoicePackagesRates[1] += importSubRate;
				InvoicePackagesRates[2] += exportRate;
				InvoicePackagesRates[3] += exportSubRate;
				InvoicePackagesRates[4] += HolidayRate;
				InvoicePackagesRates[5] += exportScRate;
				InvoicePackagesRates[6] += exportPcRate;
				InvoicePackagesRates[7] += exportHeavyRate;
				InvoicePackagesRates[8] += exportPenaltyLocal + exportSubPenaltyLocal;
				InvoicePackagesRates[9] += importScRate;
				InvoicePackagesRates[10] += importPcRate;
				InvoicePackagesRates[11] += importHeavyRate;
				InvoicePackagesRates[12] += importPenaltyLocal + importSubPenaltyLocal;
				InvoicePackagesRates[13] += demuragesRate;
				totalPackages += importSubNoOfPackages + exportSubNoOfPackages;

				InvoiceTaxDetails detail = new InvoiceTaxDetails(companyid, branchId, InvoiceNumber, partyId, date,
						importNoOfPackages, exportNoOfPackages, totalPackages, Math.round(HolidayRate),
						Math.round(exportScRate), Math.round(exportPcRate), Math.round(exportHeavyRate), exportHpWeight,
						Math.round(exportPenaltyLocal + exportSubPenaltyLocal), Math.round(importScRate),
						Math.round(importPcRate), Math.round(importHeavyRate), importHpWeight,
						Math.round(importPenaltyLocal + importSubPenaltyLocal), amounts[0], amounts[1], amounts[2], "A",
						userId, new Date(), importSubNoOfPackages, exportSubNoOfPackages, Math.round(importSubRate),
						Math.round(exportSubRate), demuragesNop, demuragesRate, InnerniptPackages);

				InvoiceTaxDetailsServiceIMPL.saveInvoiceTaxDetails(detail);

				// Create a CombinedImportExport object with the updated holidayStatus
				return new CombinedImportExport(partyId, findPartyNameById, HolidayRate, date, importNoOfPackages,
						totalPackages, importScRate, importPcRate, importHeavyRate, importHpWeight, importPenaltyLocal,
						exportNoOfPackages, exportScRate, exportPcRate, exportHeavyRate, exportHpWeight,
						exportPenaltyLocal, importRate, exportRate, importSubNoOfPackages, exportSubNoOfPackages,
						importSubRate, exportSubRate, demuragesNop, demuragesRate, InnerniptPackages);
			}).collect(Collectors.toList());

//			System.out.println("Export After Nop " + InvoicePackagesRates[6]);

			List<Date> dateList = combinedDataList.stream().map(item -> item.getDate()) // Replace with the actual
																						// method to get the date from
																						// your object
					.collect(Collectors.toList());

			// Find the lowest and highest dates
			Date minDate = dateList.stream().min(Date::compareTo).orElse(null);

			Date maxDate = dateList.stream().max(Date::compareTo).orElse(null);

			Branch findByCompany_Id = branchRepo.findByCompanyIdAndBranchId(companyid, branchId);
			String CompanyGstNo = findByCompany_Id.getGST_No();
			String PartyGstNo = PartyByID.getGstNo();
			String companyStateCode = CompanyGstNo.substring(0, 2);
			String partyStateCode = PartyGstNo.substring(0, 2);

			System.out.println("Import Penalty Amount "+Penalty[0]);
			System.out.println("Export Penalty Amount "+Penalty[1]);
			
			System.out.println("Total Penalty Amount "+Penalty[0] + Penalty[1]);
			System.out.println("Before Total Amount "+amounts[2]);
			
			amounts[1] += Penalty[0] + Penalty[1];
			amounts[2] += Penalty[0] + Penalty[1];
			
			System.out.println("After Total Amount "+amounts[2]);
			
			InvoiceMain Invoice = new InvoiceMain();
			Invoice.setCompanyId(companyid);
			Invoice.setBranchId(branchId);
			;
			Invoice.setInvoiceNO(InvoiceNumber);
			Invoice.setInvoiceDate(new Date());
			Invoice.setInvoiceDueDate(futureDate);
			Invoice.setPartyId(PartyId);
			Invoice.setTariffNo(finalCfsTarrif.getCfsTariffNo());
			Invoice.setTariffAmndNo(finalCfsTarrif.getCfsAmndNo());

			Invoice.setBillAmount(amounts[1]);
			Invoice.setBillNO(BillNumber);
			Invoice.setPaymentStatus("P");
			double taxamount = 0.0;

			if ("Y".equals(PartyByID.getTaxApplicable())) {
				taxamount = amounts[1] * (18 / 100.0);

				Invoice.setTaxAmount(taxamount);
				if (companyStateCode.equals(partyStateCode)) {
					Invoice.setCgst("Y");
					Invoice.setSgst("Y");
					Invoice.setIgst("N");
				} else {
					Invoice.setCgst("N");
					Invoice.setSgst("N");
					Invoice.setIgst("Y");
				}

			} else {
				Invoice.setTaxAmount(amounts[0]);
				Invoice.setIgst("N");
				Invoice.setCgst("N");
				Invoice.setSgst("N");
			}

			Invoice.setTotalInvoiceAmount(amounts[2] + taxamount);
			Invoice.setPeriodFrom(minDate);
			Invoice.setPeriodTo(maxDate);
			Invoice.setCreatedBy(userId);
			Invoice.setCreatedDate(new Date());
			Invoice.setEditedBy(userId);
			Invoice.setEditedDate(new Date());
			Invoice.setApprovedBy(userId);
			Invoice.setApprovedDate(new Date());
			Invoice.setComments("Invoice Comment");
			Invoice.setStatus("A");
			Invoice.setMailFlag("N");
			Invoice.setReceiptTransactionId("REC101");
			Invoice.setReceiptTransactionDate(new Date());
//			InvoiceMain Invoice = new InvoiceMain(companyid, branchId, InvoiceNumber, PartyId, new Date(),
//					finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), new Date(), amounts[0], amounts[1],
//					OtherTaxes, "REC101", startDate, endDate, futureDate, "Invoice Comments", "N", "N", "N", "N", "A",
//					userId, new Date(), userId, new Date(), userId, new Date());

			PartyByID.setLastInVoiceDate(maxDate);
			PartyByID.setLastInVoiceNo(InvoiceNumber);
			PartyService.saveParty(PartyByID);

//			InvoicePackages Numbers And Rates
			InvoicePackages packages = new InvoicePackages(companyid, branchId, InvoiceNumber, BillNumber, PartyId,
					new Date(), InvoicePackageNo[0], InvoicePackageNo[1], InvoicePackageNo[2], InvoicePackageNo[3],
					InvoicePackageNo[4], InvoicePackageNo[5], InvoicePackageNo[6], InvoicePackageNo[7],
					InvoicePackageNo[8], InvoicePackageNo[9], InvoicePackageNo[10], InvoicePackageNo[11],
					InvoicePackageNo[12], InvoicePackagesRates[0], InvoicePackagesRates[1], InvoicePackagesRates[2],
					InvoicePackagesRates[3], InvoicePackagesRates[4], InvoicePackagesRates[5], InvoicePackagesRates[6],
					InvoicePackagesRates[7], InvoicePackagesRates[8], InvoicePackagesRates[9], InvoicePackagesRates[10],
					InvoicePackagesRates[11], InvoicePackagesRates[12], InvoicePackageNo[13], InvoicePackagesRates[13],
					niptPackages[0]);

//			System.out.println("Packages");
//			System.out.println(  packages );

			InvoicePackagesRepositary.save(packages);

			InvoiceMain addInvoice = invoiceServiceIMPL.addInvoice(Invoice);
//		System.out.println(addInvoice);
//			InvoicePlusBill invoiceResponse =new InvoicePlusBill(addInvoice,combinedDataList);
			return ResponseEntity.ok(addInvoice);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
		}

	}

//	Pdf Controller   For Invoice

	@PostMapping("/{compId}/{branchId}/{invoiceNo}/generatepdf")
	public ResponseEntity<String> generatePdf(@PathVariable("compId") String companyId,
			@PathVariable("branchId") String branchId, @PathVariable("invoiceNo") String invoiceNo,
			@RequestBody List<InvoiceTaxDetails> byInvoiceNo) throws Exception {
		try {
			// Create a Thymeleaf context

			String base64Pdf = FunctionForBillGeneration(companyId, branchId, invoiceNo, byInvoiceNo);

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(base64Pdf);
		} catch (Exception e) {
			// Handle exceptions appropriately
			return ResponseEntity.badRequest().body("Error generating PDF");
		}
	}

//	PDF Bill DownLoad

	@PostMapping("/{compId}/{branchId}/{invoiceNo}/generatebillpdf")
	public ResponseEntity<String> generateBillPdf(@PathVariable("compId") String companyId,
			@PathVariable("branchId") String branchId, @PathVariable("invoiceNo") String invoiceNo,
			@RequestBody List<InvoiceTaxDetails> byInvoiceNo) throws Exception {
		try {

			String base64Pdf = FunctionSingleInvice(companyId, branchId, invoiceNo, byInvoiceNo);
			
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(base64Pdf);
		} catch (Exception e) {
			// Handle exceptions appropriately
			return ResponseEntity.badRequest().body("Error generating PDF");
		}
	}

	private double calculateSum(List<InvoiceTaxDetails> invoiceTaxDetails,
			ToDoubleFunction<InvoiceTaxDetails> columnExtractor) {
		return invoiceTaxDetails.stream().mapToDouble(columnExtractor).sum();
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

	
	
//Payment Transaction

	@GetMapping("/{compId}/{branchId}/{PartyId}/getInviceByPartyId")
	public List<InvoiceMain> getInviceByPartyId(@PathVariable("compId") String compId,
			@PathVariable("branchId") String branchId, @PathVariable("PartyId") String partyId) {
		return invoiceServiceIMPL.getByPartyId(compId, branchId, partyId);
	}

//	Single Bills for Particular party
	
	
	@GetMapping("/{compId}/{branchId}/{partyId}/{invoiceNo}/generateSingleBill")
	public ResponseEntity<String> generatePdf(@PathVariable("compId") String companyId,@PathVariable("partyId") String partyId,
			@PathVariable("branchId") String branchId, @PathVariable("invoiceNo") String invoiceNo
//			@RequestBody List<InvoiceTaxDetails> byInvoiceNo
			) throws Exception {
		try {

			List<InvoiceTaxDetails> findByInvoiceNo = InvoiceTaxDetailsServiceIMPL.findByInvoiceNo(companyId, branchId, partyId, invoiceNo);
					
			String base64Pdf = FunctionForBillGeneration(companyId, branchId, invoiceNo, findByInvoiceNo);

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(base64Pdf);
		} catch (Exception e) {
			// Handle exceptions appropriately
			return ResponseEntity.badRequest().body("Error generating PDF");
		}
	}
	
	
//	Single Invice for Particular party
	
	
	@GetMapping("/{compId}/{branchId}/{partyId}/{invoiceNo}/generateSingleinvice")
	public ResponseEntity<String> generateBillPdf(@PathVariable("compId") String companyId ,@PathVariable("partyId") String partyId,
			@PathVariable("branchId") String branchId, @PathVariable("invoiceNo") String invoiceNo) throws Exception {
		try {

			List<InvoiceTaxDetails> findByInvoiceNo = InvoiceTaxDetailsServiceIMPL.findByInvoiceNo(companyId, branchId, partyId, invoiceNo);
			
			
			String base64Pdf = FunctionSingleInvice(companyId, branchId, invoiceNo, findByInvoiceNo);
			
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(base64Pdf);
		} catch (Exception e) {
			// Handle exceptions appropriately
			return ResponseEntity.badRequest().body("Error generating PDF");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	Single Invice Report

	public String FunctionSingleInvice(String companyId, String branchId, String invoiceNo,
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

			InvoiceMain byInvoiceNo2 = invoiceServiceIMPL.getByInvoiceNo(companyId, branchId, partyId, invoiceNo);

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
			String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);

			return base64Pdf;
		} catch (Exception e) {
			// Handle exceptions appropriately
			return null;
		}
	}

//	Single BIll Pdf Download by Invice Number

	public String FunctionForBillGeneration(String companyId, String branchId, String invoiceNo,
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

			InvoiceMain byInvoiceNo2 = invoiceServiceIMPL.getByInvoiceNo(companyId, branchId, partyId, invoiceNo);

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

			return base64Pdf;
		} catch (Exception e) {
			// Handle exceptions appropriately
			return null;
		}

	}
	
	
//	Single Demurage Charges
	
	@GetMapping("/{compId}/{branchId}/{partyId}/{invoiceNo}/generateSingleDemurage")
	public ResponseEntity<String> generateDemuragePdf(@PathVariable("compId") String companyId ,@PathVariable("partyId") String partyId,
			@PathVariable("branchId") String branchId, @PathVariable("invoiceNo") String invoiceNo) throws Exception {
		try {

			List<DemuragePackagesHistory> findByInvoiceNo = InvoiceTaxDetailsServiceIMPL.getbyDemuragesByPartyId(companyId, branchId, partyId, invoiceNo);
			
			
			String base64Pdf = FunctionSingleDemurages(companyId, branchId, invoiceNo, findByInvoiceNo,partyId);
			
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(base64Pdf);
		} catch (Exception e) {
			// Handle exceptions appropriately
			return ResponseEntity.badRequest().body("Error generating PDF");
		}
	}
	
	
	public String FunctionSingleDemurages(String companyId, String branchId, String invoiceNo,
			List<DemuragePackagesHistory> Demurage,String partyId) throws Exception {
		try 
		{
			Context context = new Context();
			
			Party byParty = PartyService.findPartyById(companyId, branchId, partyId);
			context.setVariable("party",byParty);
//			context.setVariable("gstNo", byParty.getGstNo());

			Branch findByCompany_Id = branchRepo.findByCompanyIdAndBranchId(companyId, branchId);
			InvoiceMain byInvoiceNo2 = invoiceServiceIMPL.getByInvoiceNo(companyId, branchId, partyId, invoiceNo);
			context.setVariable("Branch", findByCompany_Id);
			context.setVariable("invoiceNO", byInvoiceNo2.getInvoiceNO());
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//			String currentDateTime = dateFormat.format(new Date());
			context.setVariable("InvoiceDate", dateFormat.format(byInvoiceNo2.getInvoiceDate()));
//			List<DemuragePackagesHistory> formattedDemurageList = Demurage.stream()
//				    .map(demurage -> {
//				        demurage.setDemurageRate(Math.round(demurage.getDemurageRate()));
//				        return demurage;
//				    })
//				    .collect(Collectors.toList());

			context.setVariable("Demurage", Demurage);
			
			
			String htmlContent = templateEngine.process("Demurage_History", context);

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

			return base64Pdf;	
			
		}
		catch(Exception e)
		{
			return null;
		}
		
		}
	
//	Party Bill Summary Report 
	
	
	@GetMapping(value = "/list/{cid}/{bid}/{sdate}/{edate}/{pid}")
	public List<InvoicePackages> getlistInvoicePackages(@PathVariable("cid") String cid,
			@PathVariable("bid") String bid, @PathVariable("sdate") String sdate, @PathVariable("edate") String edate,
			@PathVariable(required = false, name = "pid") String pid) throws ParseException {

		List<InvoicePackages> invoicePackagesList = new ArrayList<>();

		invoicePackagesList = getData(cid, bid, sdate, edate, pid);

		return invoicePackagesList;
	}

	@GetMapping("/generatePrint/{cid}/{bid}/{sdate}/{edate}/{pid}")
	public ResponseEntity<String> generatePrint(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
			@PathVariable("sdate") String sdate, @PathVariable("edate") String edate,
			@PathVariable(required = false, name = "pid") String pid) throws Exception {
		try {

			List<InvoicePackages> invoicePackagesList = new ArrayList<>();

			invoicePackagesList = getData(cid, bid, sdate, edate, pid);
//			InvoicePackages Total = gettotal(invoicePackagesList);
			List<Party> list = FilterParty1(invoicePackagesList, cid, bid);
			String base64Pdf = FunctionPrintPdf(invoicePackagesList, sdate, edate, cid, bid);

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(base64Pdf);
		} catch (Exception e) {
			// Handle exceptions appropriately
			return ResponseEntity.badRequest().body("Error generating PDF");
		}

	}

	public String FunctionPrintPdf(List<InvoicePackages> list, String sdate, String edate, String cid, String bid)
			throws Exception {
		InvoicePackages invoicePackages = null;
		int totalExportNop = 0;
		int totalExportRate = 0;
		int totalExportSubNop = 0;
		int totalExportSubRate = 0;
		int totalImportNop = 0;
		int totalImportRate = 0;
		int totalImportSubNop = 0;
		int totalImportSubRate = 0;
		int totalHolidayRate = 0;
		int totalDemuragesRate = 0;
		int totalExportSplCartRate = 0;
		int totalExportHpRate = 0;
		int totalExportPcRate = 0;
		int totalExportOcRate = 0;
		int totalImportSplCartRate = 0;
		int totalImportHpRate = 0;
		int totalImportPcRate = 0;
		int totalImportOcRate = 0;
		int totalHolidaySubNop = 0;
		int totalIgst = 0;
		int totalCgst = 0;
		int totalSgst = 0;

		for (InvoicePackages invoice : list) {
			totalExportNop += invoice.getExportNop();
			totalExportRate += (int) invoice.getExportRate();
			totalExportSubNop += invoice.getExportSubNop();
			totalExportSubRate += (int) invoice.getExportSubRate();
			totalImportNop += invoice.getImportNop();
			totalImportRate += (int) invoice.getImportRate();
			totalImportSubNop += invoice.getImportSubNop();
			totalImportSubRate += (int) invoice.getImportSubRate();
			totalHolidayRate += (int) invoice.getHolidayRate();
			totalDemuragesRate += (int) invoice.getDemuragesRate();
			totalExportSplCartRate += (int) invoice.getExportSplCartRate();
			totalExportHpRate += (int) invoice.getExportHpRate();
			totalExportPcRate += (int) invoice.getExportPcRate();
			totalExportOcRate += (int) invoice.getExportOcRate();
			totalImportSplCartRate += (int) invoice.getImportSplCartRate();
			totalImportHpRate += (int) invoice.getImportHpRate();
			totalImportPcRate += (int) invoice.getImportPcRate();
			totalImportOcRate += (int) invoice.getImportOcRate();
			totalHolidaySubNop += invoice.getHolidaySubNop();
			if (invoice.getImportNop() == 1) {
				totalIgst += invoice.getHolidaySubNop();
			} else {
				totalCgst += invoice.getHolidaySubNop() / 2;
				totalSgst += invoice.getHolidaySubNop() / 2;
			}

		}
		List<Party> parties = FilterParty1(list, cid, bid);
		try {

			Context context = new Context();
			context.setVariable("name", "mahesh");
			context.setVariable("startDate", returnformatedate(sdate));
			context.setVariable("endDate", returnformatedate(edate));
			context.setVariable("invoiceDetails", list);
			context.setVariable("totalExportNop", totalExportNop);
			context.setVariable("totalExportRate", totalExportRate);
			context.setVariable("totalExportSubNop", totalExportSubNop);
			context.setVariable("totalExportSubRate", totalExportSubRate);
			context.setVariable("totalImportNop", totalImportNop);
			context.setVariable("totalImportRate", totalImportRate);
			context.setVariable("totalImportSubNop", totalImportSubNop);
			context.setVariable("totalImportSubRate", totalImportSubRate);
			context.setVariable("totalHolidayRate", totalHolidayRate);
			context.setVariable("totalDemuragesRate", totalDemuragesRate);
			context.setVariable("totalExportSplCartRate", totalExportSplCartRate);
			context.setVariable("totalExportHpRate", totalExportHpRate);
			context.setVariable("totalExportPcRate", totalExportPcRate);
			context.setVariable("totalExportOcRate", totalExportOcRate);
			context.setVariable("totalImportSplCartRate", totalImportSplCartRate);
			context.setVariable("totalImportHpRate", totalImportHpRate);
			context.setVariable("totalImportPcRate", totalImportPcRate);
			context.setVariable("totalImportOcRate", totalImportOcRate);
			context.setVariable("totalHolidaySubNop", totalHolidaySubNop);
			context.setVariable("totalIgst", totalIgst);
			context.setVariable("totalCgst", totalCgst);
			context.setVariable("totalSgst", totalSgst);

			if (parties != null && !parties.isEmpty()) {
				// Render the Thymeleaf template with the parties list
				context.setVariable("parties", parties);
			} else {
			}

			Thread.sleep(1000);

			// Process the HTML template with dynamic values
			String htmlContent = templateEngine.process("PartyBillSummaryReport", context);

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

			return base64Pdf;
		} catch (Exception e) {
			// Handle exceptions appropriately
			return null;
		}
	}

	public InvoicePackages gettotal(List<InvoicePackages> list) {

		InvoicePackages invoicePackages = null;
		int totalExportNop = 0;
		int totalExportRate = 0;
		int totalExportSubNop = 0;
		int totalExportSubRate = 0;
		int totalImportNop = 0;
		int totalImportRate = 0;
		int totalImportSubNop = 0;
		int totalImportSubRate = 0;
		int totalHolidayRate = 0;
		int totalDemuragesRate = 0;
		int totalExportSplCartRate = 0;
		int totalExportHpRate = 0;
		int totalExportPcRate = 0;
		int totalExportOcRate = 0;
		int totalImportSplCartRate = 0;
		int totalImportHpRate = 0;
		int totalImportPcRate = 0;
		int totalImportOcRate = 0;
		int totalHolidaySubNop = 0;
		int totalIgst = 0;
		int totalCgst = 0;
		int totalSgst = 0;

		for (InvoicePackages invoice : list) {

			totalExportNop = invoice.getExportNop();
			totalExportRate = (int) invoice.getExportRate();
			totalExportSubNop = invoice.getExportSubNop();
			totalExportSubRate = (int) invoice.getExportSubRate();
			totalImportNop = invoice.getImportNop();
			totalImportRate = (int) invoice.getImportRate();
			totalImportSubNop = invoice.getImportSubNop();
			totalImportSubRate = (int) invoice.getImportSubRate();
			totalHolidayRate = (int) invoice.getHolidayRate();
			totalDemuragesRate = (int) invoice.getDemuragesRate();
			totalExportSplCartRate = (int) invoice.getExportSplCartRate();
			totalExportHpRate = (int) invoice.getExportHpRate();
			totalExportPcRate = (int) invoice.getExportPcRate();
			totalExportOcRate = (int) invoice.getExportOcRate();
			totalImportSplCartRate = (int) invoice.getImportSplCartRate();
			totalImportHpRate = (int) invoice.getImportHpRate();
			totalImportPcRate = (int) invoice.getImportPcRate();
			totalImportOcRate = (int) invoice.getImportOcRate();
			totalHolidaySubNop = invoice.getHolidaySubNop();
			if (invoice.getImportNop() == 1) {
				totalIgst += invoice.getHolidaySubNop();
			} else {
				totalCgst = invoice.getHolidaySubNop() / 2;
				totalSgst = invoice.getHolidaySubNop() / 2;
			}
		}
		invoicePackages.setExportNop(totalExportNop);
		invoicePackages.setExportRate(totalExportRate);
		invoicePackages.setExportSubNop(totalExportSubNop);
		invoicePackages.setExportSubRate(totalExportSubRate);
		invoicePackages.setImportNop(totalImportNop);
		invoicePackages.setImportRate(totalImportRate);
		invoicePackages.setImportSubNop(totalImportSubNop);
		invoicePackages.setImportSubRate(totalImportSubRate);
		invoicePackages.setHolidayRate(totalHolidayRate);
		invoicePackages.setDemuragesRate(totalDemuragesRate);
		invoicePackages.setExportSplCartRate(totalExportSplCartRate);
		invoicePackages.setExportHpRate(totalExportHpRate);
		invoicePackages.setExportPcRate(totalExportPcRate);
		invoicePackages.setExportOcRate(totalExportOcRate);
		invoicePackages.setImportSplCartRate(totalImportSplCartRate);
		invoicePackages.setImportHpRate(totalImportHpRate);
		invoicePackages.setImportPcRate(totalImportPcRate);
		invoicePackages.setImportOcRate(totalImportOcRate);
		invoicePackages.setHolidaySubNop(totalHolidaySubNop);

		invoicePackages.setDemuragesNop(totalIgst);

		invoicePackages.setCompanyId(String.valueOf(totalCgst));
		invoicePackages.setBranchId(String.valueOf(totalSgst));

		return invoicePackages;
	}

	public String returnformatedate(String originalDateString) {

		SimpleDateFormat originalDateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)");

		// Create a SimpleDateFormat to format the date in the new format
		SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String newDateString = null;
		try {
			// Parse the original date string
			Date date = originalDateFormat.parse(originalDateString);

			// Format the date as a new string in the desired format
			newDateString = newDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDateString;
	}

	public List<Party> FilterParty1(List<InvoicePackages> list, String cid, String bid) {


		List<Party> filterParty = new ArrayList<>();
		List<Party> parties = partyRepository.getalldata(cid, bid);

		for (InvoicePackages packages : list) {
			String partyIdToMatch = packages.getPartyId();
			for (Party party : parties) {
				if (partyIdToMatch.equals(party.getPartyId())) {
					filterParty.add(party);
					break; // Break the inner loop once a match is found
				}
			}
		}

		for (Party party : filterParty) {
//			System.out.println(party);
		}

		return filterParty;
	}
	
	
	
		public List<InvoicePackages> getData(String cid, String bid, String sdate, String edate, String pid)
			throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)");

		Date startDate = null;
		Date endDate = null;
		startDate = dateFormat.parse(sdate);
		endDate = dateFormat.parse(edate);
		System.out.println(cid + "" + bid + "" + startDate + "" + endDate + "" + pid);
		List<InvoicePackages> invoicePackages = new ArrayList<>();
		List<InvoicePackages> total = new ArrayList<>();
		if (pid == null || pid == "Null" || pid.equals("null") || pid.equals("Select Party")) {
			invoicePackages = InvoicePackagesRepositary.findInvoicesBetweenDatesAndConditionsAll(startDate, endDate,
					cid, bid);
//			System.out.println("test");
		} else {
			invoicePackages = InvoicePackagesRepositary.findInvoicesBetweenDatesAndConditions(startDate, endDate, cid,
					bid, pid);
		}

		// System.out.println(invoicePackages);
		Map<String, List<InvoicePackages>> groupedByPartyId = invoicePackages.stream()
				.collect(Collectors.groupingBy(InvoicePackages::getPartyId));

		for (Map.Entry<String, List<InvoicePackages>> entry : groupedByPartyId.entrySet()) {
			String partyId = entry.getKey();
			List<InvoicePackages> packagesForParty = entry.getValue();

			InvoicePackages addPackages = new InvoicePackages();
			for (InvoicePackages packages : packagesForParty) {
				addPackages.setPartyId(packages.getPartyId());
				addPackages.setCompanyId(packages.getCompanyId());
				addPackages.setBranchId(packages.getBranchId());
				addPackages.setSrNo(packages.getSrNo());
				addPackages.setInvoiceNO(packages.getInvoiceNO());
				addPackages.setBillNO(packages.getBillNO());
				addPackages.setInvoiceDate(packages.getInvoiceDate());

				addPackages.setExportNop(addPackages.getExportNop() + packages.getExportNop());
				addPackages.setExportRate(addPackages.getExportRate() + packages.getExportRate());
				addPackages.setExportSubNop(addPackages.getExportSubNop() + packages.getExportSubNop());
				addPackages.setExportSubRate(addPackages.getExportSubRate() + packages.getExportSubRate());

				addPackages.setImportNop(addPackages.getImportNop() + packages.getImportNop());
				addPackages.setImportRate(addPackages.getImportRate() + packages.getImportRate());
				addPackages.setImportSubNop(addPackages.getImportSubNop() + packages.getImportSubNop());
				addPackages.setImportSubRate(addPackages.getImportSubRate() + packages.getImportSubRate());

				addPackages.setHolidayRate(addPackages.getHolidayRate() + packages.getHolidayRate());
				addPackages.setHolidaySubNop(addPackages.getHolidaySubNop() + packages.getHolidaySubNop());

				addPackages.setExportSplCartNop(addPackages.getExportSplCartNop() + packages.getExportSplCartNop());
				addPackages.setExportSplCartRate(addPackages.getExportSplCartRate() + packages.getExportSplCartRate());
				addPackages.setExportHpNop(addPackages.getExportHpNop() + packages.getExportHpNop());
				addPackages.setExportHpRate(addPackages.getExportHpRate() + packages.getExportHpRate());
				addPackages.setExportPcNop(addPackages.getExportPcNop() + packages.getExportPcNop());
				addPackages.setExportPcRate(addPackages.getExportPcRate() + packages.getExportPcRate());
				addPackages.setExportOcNop(addPackages.getExportOcNop() + packages.getExportOcNop());
				addPackages.setExportOcRate(addPackages.getExportOcRate() + packages.getExportOcRate());

				addPackages.setImportSplCartNop(addPackages.getImportSplCartNop() + packages.getImportSplCartNop());
				addPackages.setImportSplCartRate(addPackages.getImportSplCartRate() + packages.getImportSplCartRate());
				addPackages.setImportHpNop(addPackages.getImportHpNop() + packages.getImportHpNop());
				addPackages.setImportHpRate(addPackages.getImportHpRate() + packages.getImportHpRate());
				addPackages.setImportPcNop(addPackages.getImportPcNop() + packages.getImportPcNop());
				addPackages.setImportPcRate(addPackages.getImportPcRate() + packages.getImportPcRate());
				addPackages.setImportOcNop(addPackages.getImportOcNop() + packages.getImportOcNop());
				addPackages.setImportOcRate(addPackages.getImportOcRate() + packages.getImportOcRate());
				addPackages.setDemuragesNop(addPackages.getDemuragesNop() + packages.getDemuragesNop());
				addPackages.setDemuragesRate(addPackages.getDemuragesRate() + packages.getDemuragesRate());
				// System.out.println(packages.getExportRate()); // You can customize how you
				// want to print the
				// InvoicePackages
			}
			total.add(addPackages);
		}

		Map<String, Double> statusMap = new HashMap<>();
		int temp = 1;
		for (InvoicePackages invoicePackages2 : total) {
//			System.out.println("Party ID: " + invoicePackages2.getPartyId());

			List<InvoiceMain> main = invoiceRepositary.findInvoicesMainBetweenDatesAndConditions(startDate, endDate,
					cid, bid, invoicePackages2.getPartyId());
			String status = null;
			double tax = 0;
			for (InvoiceMain inMain : main) {

				status = inMain.getIgst();
				tax = inMain.getTaxAmount();
			}

			if (status == "Y" || status.equals("Y")) {
				invoicePackages2.setImportNop(1);
//				System.out.println(temp++);
//				System.out.println(invoicePackages2.getImportNop());
//				System.out.println(invoicePackages2.getPartyId());

//				statusMap.put(status, tax);
			} else {
				invoicePackages2.setImportNop(0);
//				System.out.println(temp++);
//				System.out.println(invoicePackages2.getImportNop());
//				System.out.println(invoicePackages2.getPartyId());
			}
			invoicePackages2.setHolidaySubNop((int) tax);
		}
		return total;

	}	
		
		

	

		@GetMapping("/invoiceAllDataOfParty")
		public List<InvoiceMain> getAllInvoiceData(@RequestParam("companyId") String companyId,
				@RequestParam("branchId") String branchId,
				@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
				@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate) {

//			 System.out.println("Received companyId: " + companyId);
			return invoiceRepository.findAllInvoiceData(companyId, branchId, startDate, endDate);
		}

		
		 @GetMapping("/invoiceDataOfPartyType")
		    public List<InvoiceMain> getInvoiceDataOfPartyType(
		        @RequestParam ("companyId")String companyId,
		        @RequestParam("branchId") String branchId,
		        @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
				@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate,
		        @RequestParam("unitType") String unitType
		    ) {
		        return invoiceRepository.findAllInvoiceDataOfPartyType(companyId, branchId, startDate, endDate, unitType);
		    }
		 
		 
		public Set<Party> FilterParty(List<InvoiceMain> list, String cid, String bid) {

			Set<Party> filterParty = new LinkedHashSet<>();
			List<Party> parties = partyRepository.getalldata(cid, bid);

			for (InvoiceMain packages : list) {
				String partyIdToMatch = packages.getPartyId();
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

		
		
		@PostMapping("/invoicePrintOfPartyType")
		public ResponseEntity<String> generateGateAllInvoiceDataByPartyType(
				@RequestParam ("companyId")String companyId,
		        @RequestParam("branchId") String branchId,
		        @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
				@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate,
		        @RequestParam("unitType") String unitType) {

			Context context = new Context();
			List<InvoiceMain> invoiceAll = invoiceRepository.findAllInvoiceDataOfPartyType(companyId, branchId, startDate, endDate,unitType);
			
			
			
		
			Set<Party> filterParty = FilterParty(invoiceAll, companyId, branchId);

			System.out.println(filterParty);

			try {
				

				// Grouping invoices by partyId
				Map<String, List<InvoiceMain>> invoicesByParty = invoiceAll.stream()
				    .collect(Collectors.groupingBy(InvoiceMain::getPartyId));

				// Initialize the total values
				Map<String, Double> totalPartytotalInvoiceAmount = new HashMap<>();
				Map<String, Double> totalPartyTxableAmount = new HashMap<>();
				Map<String, Double> totalPartyGstAmount = new HashMap<>();
				Map<String, Double> totalPartyIgst = new HashMap<>();
				Map<String, Double> totalPartyCgst = new HashMap<>();
				Map<String, Double> totalPartySgst = new HashMap<>();

				// Iterate over the grouped invoices and calculate the totals for each party
				for (Entry<String, List<InvoiceMain>> entry : invoicesByParty.entrySet()) {
				    String partyId = entry.getKey();
				    List<InvoiceMain> partyInvoices = entry.getValue();

				    double partyTotalInvoiceAmount = 0.0;
				    double partyTotalTxableAmount = 0.0;
				    double partyTotalGstAmount = 0.0;
				    double partyTotalIgst = 0.0;
				    double partyTotalCgst = 0.0;
				    double partyTotalSgst = 0.0;

				    for (InvoiceMain invoice : partyInvoices) {
				        partyTotalInvoiceAmount += invoice.getTotalInvoiceAmount();
				        partyTotalTxableAmount += invoice.getBillAmount();
				        partyTotalGstAmount += invoice.getTaxAmount();

				        if ("Y".equals(invoice.getIgst())) {
				            partyTotalIgst += invoice.getTaxAmount();
				        } else {
				            partyTotalCgst += invoice.getTaxAmount() / 2;
				            partyTotalSgst += invoice.getTaxAmount() / 2;
				        }
				    }

				    // Store the totals for this party using partyId as the key
				    totalPartytotalInvoiceAmount.put(partyId, partyTotalInvoiceAmount);
				    totalPartyTxableAmount.put(partyId, partyTotalTxableAmount);
				    totalPartyGstAmount.put(partyId, partyTotalGstAmount);
				    totalPartyIgst.put(partyId, partyTotalIgst);
				    totalPartyCgst.put(partyId, partyTotalCgst);
				    totalPartySgst.put(partyId, partyTotalSgst);
				}

				// Set these totals as variables in your Thymeleaf context
				context.setVariable("TotalPartytotalInvoiceAmount", totalPartytotalInvoiceAmount);
				context.setVariable("TotalPartyTxableAmount", totalPartyTxableAmount);
				context.setVariable("TotalPartyIgst", totalPartyIgst);
				context.setVariable("TotalPartyCgst", totalPartyCgst);
				context.setVariable("TotalPartySgst", totalPartySgst);
				context.setVariable("TotalPartyGstAmount", totalPartyGstAmount);
				
				

				if (filterParty != null && !filterParty.isEmpty()) {
					context.setVariable("filterParty", filterParty);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}

				if (invoiceAll != null) {

					context.setVariable("invoiceAll", invoiceAll);
//					context.setVariable("partyData", partyData);
					context.setVariable("startDate", startDate);
					context.setVariable("endDate", endDate);

					double TotaltotalInvoiceAmount = 0.0;
					double TotalTxableAmount = 0.0;
					double TotalGstAmount = 0.0;
					double totalIgst = 0.0;
					double totalCgst = 0.0;
					double totalSgst = 0.0;
			

					for (InvoiceMain im : invoiceAll) {
						TotaltotalInvoiceAmount = TotaltotalInvoiceAmount + im.getTotalInvoiceAmount();
						TotalTxableAmount = TotalTxableAmount + im.getBillAmount();
						TotalGstAmount = TotalGstAmount + im.getTaxAmount();

						if (im.getIgst().equals("Y")) {
							context.setVariable("igst", im.getTaxAmount());
							context.setVariable("cgst", 0); // Set to null to clear the value
							context.setVariable("sgst", 0); // Set to null to clear the value
							totalIgst += im.getTaxAmount();
						} else {
							context.setVariable("igst", 0); // Set to null to clear the value
							context.setVariable("cgst", im.getTaxAmount() / 2);
							context.setVariable("sgst", im.getTaxAmount() / 2);
							totalCgst += im.getTaxAmount() / 2;
							totalSgst += im.getTaxAmount() / 2;
						}

					}

					context.setVariable("TotaltotalInvoiceAmount", TotaltotalInvoiceAmount);
					context.setVariable("TotalTxableAmount", TotalTxableAmount);
					context.setVariable("TotalGstAmount", TotalGstAmount);
					context.setVariable("totalIgst", totalIgst);
					context.setVariable("totalCgst", totalCgst);
					context.setVariable("totalSgst", totalSgst);

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
					String htmlContent = templateEngine.process("gst_summary_party_Type", context);

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

		@PostMapping("/invoicPrintOfAllParty")
		public ResponseEntity<String> generateGateAllInvoiceData(@RequestParam("companyId") String companyId,
				@RequestParam("branchId") String branchId,
				@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
				@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate) {

			Context context = new Context();
			List<InvoiceMain> invoiceAll = invoiceRepository.findAllInvoiceData(companyId, branchId, startDate, endDate);
			
			
			
		
			Set<Party> filterParty = FilterParty(invoiceAll, companyId, branchId);

			System.out.println(filterParty);

			try {
				

				// Grouping invoices by partyId
				Map<String, List<InvoiceMain>> invoicesByParty = invoiceAll.stream()
				    .collect(Collectors.groupingBy(InvoiceMain::getPartyId));

				// Initialize the total values
				Map<String, Double> totalPartytotalInvoiceAmount = new HashMap<>();
				Map<String, Double> totalPartyTxableAmount = new HashMap<>();
				Map<String, Double> totalPartyGstAmount = new HashMap<>();
				Map<String, Double> totalPartyIgst = new HashMap<>();
				Map<String, Double> totalPartyCgst = new HashMap<>();
				Map<String, Double> totalPartySgst = new HashMap<>();

				// Iterate over the grouped invoices and calculate the totals for each party
				for (Entry<String, List<InvoiceMain>> entry : invoicesByParty.entrySet()) {
				    String partyId = entry.getKey();
				    List<InvoiceMain> partyInvoices = entry.getValue();

				    double partyTotalInvoiceAmount = 0.0;
				    double partyTotalTxableAmount = 0.0;
				    double partyTotalGstAmount = 0.0;
				    double partyTotalIgst = 0.0;
				    double partyTotalCgst = 0.0;
				    double partyTotalSgst = 0.0;

				    for (InvoiceMain invoice : partyInvoices) {
				        partyTotalInvoiceAmount += invoice.getTotalInvoiceAmount();
				        partyTotalTxableAmount += invoice.getBillAmount();
				        partyTotalGstAmount += invoice.getTaxAmount();

				        if ("Y".equals(invoice.getIgst())) {
				            partyTotalIgst += invoice.getTaxAmount();
				        } else {
				            partyTotalCgst += invoice.getTaxAmount() / 2;
				            partyTotalSgst += invoice.getTaxAmount() / 2;
				        }
				    }

				    // Store the totals for this party using partyId as the key
				    totalPartytotalInvoiceAmount.put(partyId, partyTotalInvoiceAmount);
				    totalPartyTxableAmount.put(partyId, partyTotalTxableAmount);
				    totalPartyGstAmount.put(partyId, partyTotalGstAmount);
				    totalPartyIgst.put(partyId, partyTotalIgst);
				    totalPartyCgst.put(partyId, partyTotalCgst);
				    totalPartySgst.put(partyId, partyTotalSgst);
				}

				// Set these totals as variables in your Thymeleaf context
				context.setVariable("TotalPartytotalInvoiceAmount", totalPartytotalInvoiceAmount);
				context.setVariable("TotalPartyTxableAmount", totalPartyTxableAmount);
				context.setVariable("TotalPartyIgst", totalPartyIgst);
				context.setVariable("TotalPartyCgst", totalPartyCgst);
				context.setVariable("TotalPartySgst", totalPartySgst);
				context.setVariable("TotalPartyGstAmount", totalPartyGstAmount);
				
				

				if (filterParty != null && !filterParty.isEmpty()) {
					context.setVariable("filterParty", filterParty);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}

				if (invoiceAll != null) {

					context.setVariable("invoiceAll", invoiceAll);
//					context.setVariable("partyData", partyData);
					context.setVariable("startDate", startDate);
					context.setVariable("endDate", endDate);

					double TotaltotalInvoiceAmount = 0.0;
					double TotalTxableAmount = 0.0;
					double TotalGstAmount = 0.0;
					double totalIgst = 0.0;
					double totalCgst = 0.0;
					double totalSgst = 0.0;
			

					for (InvoiceMain im : invoiceAll) {
						TotaltotalInvoiceAmount = TotaltotalInvoiceAmount + im.getTotalInvoiceAmount();
						TotalTxableAmount = TotalTxableAmount + im.getBillAmount();
						TotalGstAmount = TotalGstAmount + im.getTaxAmount();

						if (im.getIgst().equals("Y")) {
							context.setVariable("igst", im.getTaxAmount());
							context.setVariable("cgst", 0); // Set to null to clear the value
							context.setVariable("sgst", 0); // Set to null to clear the value
							totalIgst += im.getTaxAmount();
						} else {
							context.setVariable("igst", 0); // Set to null to clear the value
							context.setVariable("cgst", im.getTaxAmount() / 2);
							context.setVariable("sgst", im.getTaxAmount() / 2);
							totalCgst += im.getTaxAmount() / 2;
							totalSgst += im.getTaxAmount() / 2;
						}

					}

					context.setVariable("TotaltotalInvoiceAmount", TotaltotalInvoiceAmount);
					context.setVariable("TotalTxableAmount", TotalTxableAmount);
					context.setVariable("TotalGstAmount", TotalGstAmount);
					context.setVariable("totalIgst", totalIgst);
					context.setVariable("totalCgst", totalCgst);
					context.setVariable("totalSgst", totalSgst);

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
					String htmlContent = templateEngine.process("gst_all_summary", context);

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

		@PostMapping("/invoicPrintOfParty")
		public ResponseEntity<String> generateGatePassPdf(@RequestParam("companyId") String companyId,
				@RequestParam("branchId") String branchId,
				@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
				@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate,
				@RequestParam("partyId") String partyId) {

			try {

				List<InvoiceMain> invoice = invoiceRepository.findInvoiceData(companyId, branchId, startDate, endDate,
						partyId);

				Party partyData = partyRepository.getdatabyid(companyId, branchId, partyId);

				if (invoice != null) {
					Context context = new Context();

					context.setVariable("invoice", invoice);
					context.setVariable("partyData", partyData);
					context.setVariable("startDate", startDate);
					context.setVariable("endDate", endDate);

					double TotaltotalInvoiceAmount = 0.0;
					double TotalTxableAmount = 0.0;
					double TotalGstAmount = 0.0;
					double totalIgst = 0.0;
					double totalCgst = 0.0;
					double totalSgst = 0.0;

					for (InvoiceMain im : invoice) {
						TotaltotalInvoiceAmount = TotaltotalInvoiceAmount + im.getTotalInvoiceAmount();
						TotalTxableAmount = TotalTxableAmount + im.getBillAmount();
						TotalGstAmount = TotalGstAmount + im.getTaxAmount();

						if (im.getIgst().equals("Y")) {
							context.setVariable("igst", im.getTaxAmount());
							context.setVariable("cgst", 0); // Set to null to clear the value
							context.setVariable("sgst", 0); // Set to null to clear the value
							totalIgst += im.getTaxAmount();
						} else {
							context.setVariable("igst", 0); // Set to null to clear the value
							context.setVariable("cgst", im.getTaxAmount() / 2);
							context.setVariable("sgst", im.getTaxAmount() / 2);
							totalCgst += im.getTaxAmount() / 2;
							totalSgst += im.getTaxAmount() / 2;
						}

					}

					context.setVariable("TotaltotalInvoiceAmount", TotaltotalInvoiceAmount);
					context.setVariable("TotalTxableAmount", TotalTxableAmount);
					context.setVariable("TotalGstAmount", TotalGstAmount);
					context.setVariable("totalIgst", totalIgst);
					context.setVariable("totalCgst", totalCgst);
					context.setVariable("totalSgst", totalSgst);

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
					String htmlContent = templateEngine.process("gst_summary", context);

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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		@PostMapping("/invoicPrintOfAllNewGstParty")
		public ResponseEntity<String> generateGateAllInvoiceDataNewGst(@RequestParam("companyId") String companyId,
				@RequestParam("branchId") String branchId,
				@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
				@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate) {

			Context context = new Context();
			List<InvoiceMain> invoiceAll = invoiceRepository.findAllInvoiceData(companyId, branchId, startDate, endDate);
			
		
			List<Party> partyData =  partyRepository.getalldata(companyId, branchId);
			
			Set<Party> filterParty = FilterParty(invoiceAll, companyId, branchId);

			System.out.println(filterParty);

			try {
				

				// Grouping invoices by partyId
				Map<String, List<InvoiceMain>> invoicesByParty = invoiceAll.stream()
				    .collect(Collectors.groupingBy(InvoiceMain::getPartyId));

				// Initialize the total values
				Map<String, Double> totalPartytotalInvoiceAmount = new HashMap<>();
				Map<String, Double> totalPartyTxableAmount = new HashMap<>();
				Map<String, Double> totalPartyGstAmount = new HashMap<>();
				Map<String, Double> totalPartyIgst = new HashMap<>();
				Map<String, Double> totalPartyCgst = new HashMap<>();
				Map<String, Double> totalPartySgst = new HashMap<>();

				// Iterate over the grouped invoices and calculate the totals for each party
				for (Entry<String, List<InvoiceMain>> entry : invoicesByParty.entrySet()) {
				    String partyId = entry.getKey();
				    List<InvoiceMain> partyInvoices = entry.getValue();

				    double partyTotalInvoiceAmount = 0.0;
				    double partyTotalTxableAmount = 0.0;
				    double partyTotalGstAmount = 0.0;
				    double partyTotalIgst = 0.0;
				    double partyTotalCgst = 0.0;
				    double partyTotalSgst = 0.0;

				    for (InvoiceMain invoice : partyInvoices) {
				        partyTotalInvoiceAmount += invoice.getTotalInvoiceAmount();
				        partyTotalTxableAmount += invoice.getBillAmount();
				        partyTotalGstAmount += invoice.getTaxAmount();

				        if ("Y".equals(invoice.getIgst())) {
				            partyTotalIgst += invoice.getTaxAmount();
				        } else {
				            partyTotalCgst += invoice.getTaxAmount() / 2;
				            partyTotalSgst += invoice.getTaxAmount() / 2;
				        }
				    }

				    // Store the totals for this party using partyId as the key
				    totalPartytotalInvoiceAmount.put(partyId, partyTotalInvoiceAmount);
				    totalPartyTxableAmount.put(partyId, partyTotalTxableAmount);
				    totalPartyGstAmount.put(partyId, partyTotalGstAmount);
				    totalPartyIgst.put(partyId, partyTotalIgst);
				    totalPartyCgst.put(partyId, partyTotalCgst);
				    totalPartySgst.put(partyId, partyTotalSgst);
				}

				// Set these totals as variables in your Thymeleaf context
				context.setVariable("TotalPartytotalInvoiceAmount", totalPartytotalInvoiceAmount);
				context.setVariable("TotalPartyTxableAmount", totalPartyTxableAmount);
				context.setVariable("TotalPartyIgst", totalPartyIgst);
				context.setVariable("TotalPartyCgst", totalPartyCgst);
				context.setVariable("TotalPartySgst", totalPartySgst);
				context.setVariable("TotalPartyGstAmount", totalPartyGstAmount);
				
				

				if (filterParty != null && !filterParty.isEmpty()) {
					context.setVariable("filterParty", filterParty);
				
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}

				if (invoiceAll != null) {

					context.setVariable("invoiceAll", invoiceAll);
					context.setVariable("partyData", partyData);
					context.setVariable("startDate", startDate);
					context.setVariable("endDate", endDate);

					double TotaltotalInvoiceAmount = 0.0;
					double TotalTxableAmount = 0.0;
					double TotalGstAmount = 0.0;
					double totalIgst = 0.0;
					double totalCgst = 0.0;
					double totalSgst = 0.0;
			

					for (InvoiceMain im : invoiceAll) {
						TotaltotalInvoiceAmount = TotaltotalInvoiceAmount + im.getTotalInvoiceAmount();
						TotalTxableAmount = TotalTxableAmount + im.getBillAmount();
						TotalGstAmount = TotalGstAmount + im.getTaxAmount();

						if (im.getIgst().equals("Y")) {
							context.setVariable("igst", im.getTaxAmount());
							context.setVariable("cgst", 0); // Set to null to clear the value
							context.setVariable("sgst", 0); // Set to null to clear the value
							totalIgst += im.getTaxAmount();
						} else {
							context.setVariable("igst", 0); // Set to null to clear the value
							context.setVariable("cgst", im.getTaxAmount() / 2);
							context.setVariable("sgst", im.getTaxAmount() / 2);
							totalCgst += im.getTaxAmount() / 2;
							totalSgst += im.getTaxAmount() / 2;
						}

					}

					context.setVariable("TotaltotalInvoiceAmount", TotaltotalInvoiceAmount);
					context.setVariable("TotalTxableAmount", TotalTxableAmount);
					context.setVariable("TotalGstAmount", TotalGstAmount);
					context.setVariable("totalIgst", totalIgst);
					context.setVariable("totalCgst", totalCgst);
					context.setVariable("totalSgst", totalSgst);

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
					String htmlContent = templateEngine.process("new_gst_Report", context);

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

		
		@PostMapping("/invoicPrintOfNewGstParty")
		public ResponseEntity<String> generateNewGstPartReport(@RequestParam("companyId") String companyId,
				@RequestParam("branchId") String branchId,
				@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
				@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate,
				@RequestParam("partyId") String partyId) {

			try {

				List<InvoiceMain> invoice = invoiceRepository.findInvoiceData(companyId, branchId, startDate, endDate,
						partyId);

				Party partyData = partyRepository.getdatabyid(companyId, branchId, partyId);
				
				

				if (invoice != null) {
					Context context = new Context();

					context.setVariable("invoice", invoice);
					context.setVariable("partyData", partyData);
					context.setVariable("startDate", startDate);
					context.setVariable("endDate", endDate);

					double TotaltotalInvoiceAmount = 0.0;
					double TotalTxableAmount = 0.0;
					double TotalGstAmount = 0.0;
					double totalIgst = 0.0;
					double totalCgst = 0.0;
					double totalSgst = 0.0;

					for (InvoiceMain im : invoice) {
						TotaltotalInvoiceAmount = TotaltotalInvoiceAmount + im.getTotalInvoiceAmount();
						TotalTxableAmount = TotalTxableAmount + im.getBillAmount();
						TotalGstAmount = TotalGstAmount + im.getTaxAmount();

						if (im.getIgst().equals("Y")) {
							context.setVariable("igst", im.getTaxAmount());
							context.setVariable("cgst", 0); // Set to null to clear the value
							context.setVariable("sgst", 0); // Set to null to clear the value
							totalIgst += im.getTaxAmount();
						} else {
							context.setVariable("igst", 0); // Set to null to clear the value
							context.setVariable("cgst", im.getTaxAmount() / 2);
							context.setVariable("sgst", im.getTaxAmount() / 2);
							totalCgst += im.getTaxAmount() / 2;
							totalSgst += im.getTaxAmount() / 2;
						}

					}

					context.setVariable("TotaltotalInvoiceAmount", TotaltotalInvoiceAmount);
					context.setVariable("TotalTxableAmount", TotalTxableAmount);
					context.setVariable("TotalGstAmount", TotalGstAmount);
					context.setVariable("totalIgst", totalIgst);
					context.setVariable("totalCgst", totalCgst);
					context.setVariable("totalSgst", totalSgst);

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
					String htmlContent = templateEngine.process("new_gst_party_report", context);

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
		
		
		
		
		
		
		
		
		
		@PostMapping("/invoicePrintOfNewGstReportPartyType")
		public ResponseEntity<String> generateGateAllInvoiceDataNewGstReportByPartyType(
				@RequestParam ("companyId")String companyId,
		        @RequestParam("branchId") String branchId,
		        @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
				@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate,
		        @RequestParam("unitType") String unitType) {

			Context context = new Context();
			List<InvoiceMain> invoiceAll = invoiceRepository.findAllInvoiceDataOfPartyType(companyId, branchId, startDate, endDate,unitType);
			
			
			
		
			Set<Party> filterParty = FilterParty(invoiceAll, companyId, branchId);

			System.out.println(filterParty);

			try {
			

				if (filterParty != null && !filterParty.isEmpty()) {
					context.setVariable("filterParty", filterParty);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}

				if (invoiceAll != null) {

					context.setVariable("invoiceAll", invoiceAll);
//					context.setVariable("partyData", partyData);
					context.setVariable("startDate", startDate);
					context.setVariable("endDate", endDate);

					double TotaltotalInvoiceAmount = 0.0;
					double TotalTxableAmount = 0.0;
					double TotalGstAmount = 0.0;
					
			

					for (InvoiceMain im : invoiceAll) {
						TotaltotalInvoiceAmount = TotaltotalInvoiceAmount + im.getTotalInvoiceAmount();
						TotalTxableAmount = TotalTxableAmount + im.getBillAmount();
						TotalGstAmount = TotalGstAmount + im.getTaxAmount();

						if (im.getIgst().equals("Y")) {
							context.setVariable("igst", im.getTaxAmount());
							context.setVariable("cgst", 0); // Set to null to clear the value
							context.setVariable("sgst", 0); // Set to null to clear the value
							
						} else {
							context.setVariable("igst", 0); // Set to null to clear the value
							context.setVariable("cgst", im.getTaxAmount() / 2);
							context.setVariable("sgst", im.getTaxAmount() / 2);
							
						}

					}

					context.setVariable("TotaltotalInvoiceAmount", TotaltotalInvoiceAmount);
					context.setVariable("TotalTxableAmount", TotalTxableAmount);
					context.setVariable("TotalGstAmount", TotalGstAmount);
				

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
					String htmlContent = templateEngine.process("new_gst_report_partyType", context);

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
	
		
		
		//invoiceData with Party
		
		@GetMapping("/invoiceDataOfParty")
		public List<InvoiceMain> getAllInvoiceDataOfParty(@RequestParam("companyId") String companyId,
				@RequestParam("branchId") String branchId,
				@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
				@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate,

				@RequestParam("partyId") String partyId) {

//			 System.out.println("Received companyId: " + companyId);
			return invoiceRepository.findInvoiceData(companyId, branchId, startDate, endDate, partyId);
		}

		
		//invoiceData without Party
		
	
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
//						image.setAlignment(Element.ALIGN_CENTER);
//						document.add(image);
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
