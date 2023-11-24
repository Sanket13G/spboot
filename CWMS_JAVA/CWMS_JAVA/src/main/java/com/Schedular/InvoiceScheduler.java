package com.Schedular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import com.cwms.entities.Branch;
import com.cwms.entities.CfsTarrif;
import com.cwms.entities.DemuragePackagesHistory;
import com.cwms.entities.EmailScedulerEntity;
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
import com.cwms.repository.EmailScedulerRepositary;
import com.cwms.repository.ExportHeavyPackageRepo;
import com.cwms.repository.ExportRepository;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.ImportRepo;
import com.cwms.repository.ImportSubRepository;
import com.cwms.repository.InvoicePackagesRepositary;
import com.cwms.repository.InvoiceRepositary;
import com.cwms.repository.PartyRepository;
import com.cwms.service.*;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceScheduler {
	
	@Autowired
	private PartyService PartyService;
	
//	@Autowired
//	private InvoiceRepositary invoiceRepositary;
	@Autowired
	public	EmailService EmailService;
	
	@Autowired
	public PartyRepository partyRepository; 
	
	@Autowired
	public EmailScedulerRepositary emailScedulerRepo;
	
//	@Autowired
//	private PartyRepository partyrepo;
	
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
	
    private volatile boolean isFirstSchedulerCompleted = false;
	
	
	

//    @Scheduled(cron = "0 0 0 1 * *")  // Run at midnight on the first day of every month
//    @Scheduled(cron = "0 26 22 17 11 ? 2023") // Run every 2 seconds
//    @Scheduled(fixedRate = 60000) // Run every minute
    public void calculateInvoice() 
	{
    	
//    	System.out.println("Checking ....... in calculateInvoice");
    	LocalDateTime desiredDateTime = LocalDateTime.of(2023, 11, 21, 7, 55);
    	LocalDateTime currentDateTime = LocalDateTime.now();
    		
    		if (currentDateTime.isEqual(desiredDateTime) || currentDateTime.isAfter(desiredDateTime)) {
			
		List<Object[]> findDistinctCompanyIdAndBranchId = branchRepo.findDistinctCompanyIdAndBranchId();
		
		for (Object[] pair : findDistinctCompanyIdAndBranchId) {
		    String companyid = (String) pair[0];
		    String branchId = (String) pair[1];
//		    System.out.println("Company ID: " + companyid + ", Branch ID: " + branchId);
		
		    List<Party> parties = PartyService.getPartiesByInviceType(companyid,branchId,"Periodic", "D");	
//		    System.out.println("Parties");
//		    System.out.println(parties);
		    
		for(Party PartyByID : parties)
		{		
			
				
//				System.out.println("Party Id Ongoing "+PartyByID.getPartyId());
				
				String PartyId = PartyByID.getPartyId();

//				Party PartyByID = PartyService.findPartyById(companyid, branchId, party.getPartyId());
				String findPartyNameById = PartyByID.getPartyName();
				
				double[] amounts = { 0.0, 0.0, 0.0 };

				double[] Penalty = { 0.0, 0.0 };

				double[] InvoicePackagesRates = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
				int[] InvoicePackageNo = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

				int[] niptPackages = { 0 };

				// Get the current date
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());

				// Set the current date to the first day of the month
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				Date firstDayOfMonth = calendar.getTime();

				// Set the current date to the last day of the month
				calendar.add(Calendar.MONTH, 1);
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				Date lastDayOfMonth = calendar.getTime();

				Date startDate;
				Date invoiceDate = PartyByID.getLastInVoiceDate();
//				System.out.println("Invice date "+invoiceDate);
				if (invoiceDate != null) {
					Calendar calendar2 = Calendar.getInstance();
					calendar2.setTime(invoiceDate);
					calendar2.add(Calendar.DAY_OF_MONTH, 1);
					startDate = calendar2.getTime();
				}
				else
				{
					startDate = firstDayOfMonth;
				}
				
				// Use these calculated dates as the startDate and endDate
				
				Date endDate = lastDayOfMonth;
				
				
//				System.out.println("Start Date "+startDate);
//				System.out.println("End Date "+endDate);				
//				
				
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

				
				
				if(ExtractingInvoiceData != null  && !ExtractingInvoiceData.isEmpty())
				{
				
					String InvoiceNumber = proccessNextIdService.autoIncrementInvoiceNumber();
					String BillNumber = proccessNextIdService.autoIncrementBillNumber();
				
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

//				Rates for specific conditions 
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


					boolean isHoliday = holidayService.findByDate(companyid, branchId, date);
					boolean isSecondSaturday = isSecondSaturday(date);

					// Set holidayStatus based on whether it's a holiday
					String holidayStatus = (isHoliday || isSecondSaturday) ? "Y" : "N";
		
					
					
//					Import
					
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
									importserviceId, PARTYID, imp.getNop(), "SYSTEM", date);
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
							demorage.setMasterNo(imp.getHawb());
							demorage.setInviceNo(InvoiceNumber);
							demorage.setBillNo(BillNumber);
							demorage.setSubMasterNo(imp.getSirNo());
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
										importPcServiceId, PARTYID, imp.getNop(), "SYSTEM", date);
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
								demorage.setMasterNo(imp.getHawb());
								demorage.setInviceNo(InvoiceNumber);
								demorage.setBillNo(BillNumber);
								demorage.setSubMasterNo(imp.getSirNo());
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
										importScServiceId, PARTYID, imp.getNop(), "SYSTEM", date);
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
								demorage.setMasterNo(imp.getHawb());
								demorage.setInviceNo(InvoiceNumber);
								demorage.setBillNo(BillNumber);
								demorage.setSubMasterNo(imp.getSirNo());
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

//								Import InvoiceImport = importService.findForInvoice(companyid, branchId, date, PartyId,
//										importNoOfPackages, importPcStatus, importScStatus, importHpStatus);

								List<ImportHeavyPackage> byMAWBImport = ImportHeavyService.getByMAWB(companyid, branchId,
										imp.getImpTransId(), imp.getMawb(), imp.getHawb(),
										imp.getSirNo());

								List<BigDecimal> weights = byMAWBImport.stream().map(ImportHeavyPackage::getHpWeight)
										.collect(Collectors.toList());

								InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetailHeavyWeight(companyid,
										branchId, InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
										importHPServiceId, PARTYID, importHpWeight.intValue(), "SYSTEM", date, weights);
								double importTaxAmount = addInvoiceDetail.getTaxAmount();
								double importBillAmount = addInvoiceDetail.getBillAmount();
								double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();

								importHeavyRate += importBillAmount;

//								System.out.println("Import Heavy Rate "+importHeavyRate );

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
								demorage.setMasterNo(imp.getHawb());
								demorage.setInviceNo(InvoiceNumber);
								demorage.setBillNo(BillNumber);
								demorage.setSubMasterNo(imp.getSirNo());
								demorage.setOutDate(toBeSend);
								demorage.setPackages(imp.getNop());
								demorage.setDemurageRate(importBillAmount *  imp.getNop());
								demorage.setPackageType("Import HP");
								InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);	
								
								
								
							}
							
//							Demurages Charges For Import Sub Packages
							
							
							
							if(daysDifference > 0)
							{
							String demurageId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
									"export DM");

							InvoiceDetail addInvoiceDetail2 = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
									InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
									demurageId, PARTYID, daysDifference, "SYSTEM", date);
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
							
//							Save in Demurage Table 
							DemuragePackagesHistory demorage =new DemuragePackagesHistory();
							demorage.setCompanyId(companyid);
							demorage.setBranchId(branchId);
							demorage.setPartyId(PartyId);
							demorage.setInDate(date);
							demorage.setInviceDate(new Date());
							demorage.setMasterNo(imp.getHawb());
							demorage.setInviceNo(InvoiceNumber);
							demorage.setBillNo(BillNumber);
							demorage.setSubMasterNo(imp.getSirNo());
							demorage.setOutDate(toBeSend);
							demorage.setPackages(imp.getNop());
							demorage.setDemurageRate(importBillAmount2 *  imp.getNop());
							demorage.setPackageType("Import DM");
							InvoiceTaxDetailsServiceIMPL.saveDemuragePackage(demorage);			
						
							}
						}			
					}
				}				
					
//				EXPORT

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
										exportserviceId, PARTYID, exp.getNoOfPackages(), "SYSTEM", date);
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
								demorage.setMasterNo(exp.getSbRequestId());
								demorage.setSubMasterNo(exp.getSerNo());		
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
										exportPcServiceId, PARTYID, exp.getNoOfPackages(), "SYSTEM", date);
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
								demorage.setMasterNo(exp.getSbRequestId());
								demorage.setSubMasterNo(exp.getSerNo());		
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
										exportScServiceId, PARTYID, exp.getNoOfPackages(), "SYSTEM", date);
								double importTaxAmount = addInvoiceDetail.getTaxAmount();
								double importBillAmount = addInvoiceDetail.getBillAmount();
								double importTotalInvoiceAmount = addInvoiceDetail.getTotalInvoiceAmount();

								exportScRate += importBillAmount;

								InvoicePackageNo[5] += exp.getNoOfPackages();

//								System.out.println("Export Nop " + exportNoOfPackages);

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
								demorage.setMasterNo(exp.getSbRequestId());
								demorage.setSubMasterNo(exp.getSerNo());		
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

//								Export InvoiceExport = ExportService.findForInvoiceExport(companyid, branchId, date, PartyId,
//										exportNoOfPackages, exportPcStatus, exportScStatus, exportHpStatus);

								List<ExportHeavyPackage> byMAWB = ExportHeavyPackageRepo.findalldata(companyid, branchId,
										exp.getSbRequestId(), exp.getSbNo());

								List<BigDecimal> weights = byMAWB.stream().map(ExportHeavyPackage::getWeight)
										.collect(Collectors.toList());

								InvoiceDetail addInvoiceDetail = InvoiceDetailServiceIMPL.addInvoiceDetailHeavyWeight(companyid,
										branchId, InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
										exporthpServiceId, PARTYID, exportHpWeight.intValue(), "SYSTEM", date, weights);
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
								demorage.setMasterNo(exp.getSbRequestId());
								demorage.setSubMasterNo(exp.getSerNo());		
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
									demurageId, PARTYID, daysDifference, "SYSTEM", date);
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
						
//							Save in Demurage Table 
							DemuragePackagesHistory demorage =new DemuragePackagesHistory();
							demorage.setCompanyId(companyid);
							demorage.setBranchId(branchId);
							demorage.setPartyId(PartyId);
							demorage.setInDate(date);
							demorage.setInviceNo(InvoiceNumber);
							demorage.setBillNo(BillNumber);
							
							demorage.setInviceDate(new Date());
							demorage.setMasterNo(exp.getSbRequestId());
							demorage.setSubMasterNo(exp.getSerNo());		
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
										importserviceId, PARTYID, impsub.getNop(), "SYSTEM", date);
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
//							Demurages Charges For Import Sub Packages
							
							

							
							
							if(daysDifference > 0)
							{
							String demurageId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
									"export DM");

							
							
							
							InvoiceDetail addInvoiceDetail2 = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
									InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
									demurageId, PARTYID, daysDifference, "SYSTEM", date);
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
						
//							Save in Demurage Table 
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
										exportserviceId, PARTYID, expsub.getNop(), "SYSTEM", date);
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
							
//							Demurages Charges For Export Sub Packages						
							
							if(daysDifference > 0)
							{
							String demurageId = ServiceIdMappingRepositary.findServieIdByKeys(companyid, branchId,
									"export DM");

							InvoiceDetail addInvoiceDetail2 = InvoiceDetailServiceIMPL.addInvoiceDetail(companyid, branchId,
									InvoiceNumber, finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(),
									demurageId, PARTYID, daysDifference, "SYSTEM", date);
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
						
//							Save in Demurage Table 
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
								totalPackages + importSubNoOfPackages + exportSubNoOfPackages, "SYSTEM", date);
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
							"SYSTEM", new Date(), importSubNoOfPackages, exportSubNoOfPackages, Math.round(importSubRate),
							Math.round(exportSubRate), demuragesNop, demuragesRate, InnerniptPackages);

					InvoiceTaxDetailsServiceIMPL.saveInvoiceTaxDetails(detail);

					// Create a CombinedImportExport object with the updated holidayStatus
					return new CombinedImportExport(partyId, findPartyNameById, HolidayRate, date, importNoOfPackages,
							totalPackages, importScRate, importPcRate, importHeavyRate, importHpWeight, importPenaltyLocal,
							exportNoOfPackages, exportScRate, exportPcRate, exportHeavyRate, exportHpWeight,
							exportPenaltyLocal, importRate, exportRate, importSubNoOfPackages, exportSubNoOfPackages,
							importSubRate, exportSubRate, demuragesNop, demuragesRate, InnerniptPackages);
				}).collect(Collectors.toList());

//				System.out.println("Export After Nop " + InvoicePackagesRates[6]);

				List<Date> dateList = combinedDataList.stream().map(item -> item.getDate()) // Replace with the actual
																							// method to get the date from
																							// your object
						.collect(Collectors.toList());

				// Find the lowest and highest dates
//				Date minDate = dateList.stream().min(Date::compareTo).orElse(null);

//				Date maxDate = dateList.stream().max(Date::compareTo).orElse(null);

				Branch findByCompany_Id = branchRepo.findByCompanyIdAndBranchId(companyid, branchId);
				String CompanyGstNo = findByCompany_Id.getGST_No();
				String PartyGstNo = PartyByID.getGstNo();
				String companyStateCode = CompanyGstNo.substring(0, 2);
				String partyStateCode = PartyGstNo.substring(0, 2);

//				System.out.println("Import Penalty Amount "+Penalty[0]);
//				System.out.println("Export Penalty Amount "+Penalty[1]);
//				
//				System.out.println("Total Penalty Amount "+Penalty[0] + Penalty[1]);
//				System.out.println("Before Total Amount "+amounts[2]);
				
				amounts[1] += Penalty[0] + Penalty[1];
				amounts[2] += Penalty[0] + Penalty[1];
				
//				System.out.println("After Total Amount "+amounts[2]);
				
				InvoiceMain Invoice = new InvoiceMain();
				Invoice.setCompanyId(companyid);
				Invoice.setBranchId(branchId);
				;
				Invoice.setInvoiceNO(InvoiceNumber);
				Invoice.setInvoiceDate(new Date());
				Invoice.setInvoiceDueDate(endDate);
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
				Invoice.setPeriodFrom(startDate);
				Invoice.setPeriodTo(endDate);
				Invoice.setCreatedBy("SYSTEM");
				Invoice.setCreatedDate(new Date());
				Invoice.setEditedBy("SYSTEM");
				Invoice.setEditedDate(new Date());
				Invoice.setApprovedBy("SYSTEM");
				Invoice.setApprovedDate(new Date());
				Invoice.setComments("Invoice Comment");
				Invoice.setStatus("A");
				Invoice.setMailFlag("N");
				Invoice.setReceiptTransactionId("REC101");
				Invoice.setReceiptTransactionDate(new Date());
//				InvoiceMain Invoice = new InvoiceMain(companyid, branchId, InvoiceNumber, PartyId, new Date(),
//						finalCfsTarrif.getCfsTariffNo(), finalCfsTarrif.getCfsAmndNo(), new Date(), amounts[0], amounts[1],
//						OtherTaxes, "REC101", startDate, endDate, futureDate, "Invoice Comments", "N", "N", "N", "N", "A",
//						"SYSTEM", new Date(), "SYSTEM", new Date(), "SYSTEM", new Date());

				
				System.out.println("End Date "+endDate);
				PartyByID.setLastInVoiceDate(endDate);
				PartyByID.setLastInVoiceNo(InvoiceNumber);
				PartyService.saveParty(PartyByID);

//				InvoicePackages Numbers And Rates
				InvoicePackages packages = new InvoicePackages(companyid, branchId, InvoiceNumber, BillNumber, PartyId,
						new Date(), InvoicePackageNo[0], InvoicePackageNo[1], InvoicePackageNo[2], InvoicePackageNo[3],
						InvoicePackageNo[4], InvoicePackageNo[5], InvoicePackageNo[6], InvoicePackageNo[7],
						InvoicePackageNo[8], InvoicePackageNo[9], InvoicePackageNo[10], InvoicePackageNo[11],
						InvoicePackageNo[12], InvoicePackagesRates[0], InvoicePackagesRates[1], InvoicePackagesRates[2],
						InvoicePackagesRates[3], InvoicePackagesRates[4], InvoicePackagesRates[5], InvoicePackagesRates[6],
						InvoicePackagesRates[7], InvoicePackagesRates[8], InvoicePackagesRates[9], InvoicePackagesRates[10],
						InvoicePackagesRates[11], InvoicePackagesRates[12], InvoicePackageNo[13], InvoicePackagesRates[13],
						niptPackages[0]);

//				System.out.println("Packages");
//				System.out.println(  packages );

				InvoicePackagesRepositary.save(packages);

				InvoiceMain addInvoice = invoiceServiceIMPL.addInvoice(Invoice);
//			System.out.println(addInvoice);
//				InvoicePlusBill invoiceResponse =new InvoicePlusBill(addInvoice,combinedDataList);
				
			
			
				EmailScedulerEntity eamil_Schedular = new EmailScedulerEntity();
				eamil_Schedular.setCompanyId(companyid);
				eamil_Schedular.setBranchId(branchId);
				eamil_Schedular.setBillNO(BillNumber);
				eamil_Schedular.setInvoiceNO(InvoiceNumber);
				eamil_Schedular.setPartyId(PartyId);
				eamil_Schedular.setPeriodFrom(startDate);
				eamil_Schedular.setPeriodTo(endDate);
				eamil_Schedular.setMailFlag("N");				
				emailScedulerRepo.save(eamil_Schedular);
			
			
			
			
			
			
			
			
			
				}
			
			
			
		}
			
		}	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
		
		
		
		
		
		
		isFirstSchedulerCompleted = true;
		ScheduledTaskRegistrar taskRegistrar = new ScheduledTaskRegistrar();
        taskRegistrar.destroy();
    }
		
		
//		System.out.println(parties);
        // Your invoice calculation logic goes here
//        System.out.println("Calculating invoice on the first day of the month");
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


// Scheduled Email For All Party 
//@Scheduled(cron = "0 28 22 17 11 ? 2023") // Run every 2 seconds
//@Scheduled(fixedRate = 60000) // Run every minute
public void SendingEmailToParty() throws Exception
{ 
//	System.out.println("Checking ....... in SendingEmailToParty");
	
	LocalDateTime desiredDateTime = LocalDateTime.of(2023, 11, 21, 8, 1);
LocalDateTime currentDateTime = LocalDateTime.now();
	
	if (currentDateTime.isEqual(desiredDateTime) || currentDateTime.isAfter(desiredDateTime)) {
	
	
	
	
	List<Object[]> findDistinctCompanyIdAndBranchId = branchRepo.findDistinctCompanyIdAndBranchId();
	
	
	
	for (Object[] pair : findDistinctCompanyIdAndBranchId) {
	    String companyid = (String) pair[0];
	    String branchId = (String) pair[1];    
	    
	    List<EmailScedulerEntity> EmailScedulerEntity = emailScedulerRepo.findByCompanyIdAndBranchIdAndMailFlagNot(companyid,branchId,"Y");
	    
	    if(EmailScedulerEntity != null  && !EmailScedulerEntity.isEmpty())
		{
	    
	    for(EmailScedulerEntity emails : EmailScedulerEntity)
	    {
	    	
	    	Party PartyId = partyRepository.findByCompanyIdAndBranchIdAndPartyId(companyid, branchId, emails.getPartyId());
	    	
//	    	Bill 
	    	List<InvoiceTaxDetails> findByInvoiceNo = InvoiceTaxDetailsServiceIMPL.findByInvoiceNo(companyid, branchId, emails.getPartyId(), emails.getInvoiceNO());
	    	byte[] functionForBillGeneration = invoiceServiceIMPL.FunctionForBillGeneration(companyid, branchId, emails.getInvoiceNO(), findByInvoiceNo);
	    	
	    	byte[] functionSingleInvice = invoiceServiceIMPL.FunctionSingleInvice(companyid, branchId, emails.getInvoiceNO(), findByInvoiceNo);
	    	
//	    	String body = "<html><body><p>Body of the email</p></body></html>";
	    	
	    	String additionalParagraph = "<p>Dear Sir/Madam,</p>"
	    	        + "<p>Please find below Invoice and Bill Documents for the Date From  <b>"+emails.getPeriodFrom()+" </b> to <b>"+ emails.getPeriodTo() 
	    	        + "</b> for The Party <b>"+PartyId.getPartyName()+"</b>.</p>";
	    	
	    	String body = "<html><body>" + additionalParagraph + "</body></html>";
	    	boolean sendEmail = EmailService.sendEmailWithTwoPdfAttachments("patilakash062@gmail.com", functionSingleInvice,functionForBillGeneration ,body,"shivraj@rapportsoft.co.in","Invoice.pdf","Bill.pdf");
	    	
//	    	EmailService.sendEmailWithPdfAttachment("tukaram8805@gmail.com", functionForBillGeneration, "tukaram1030@gmail.com","Bill.pdf");
	    	if(sendEmail == true)
	    	{
	    		emails.setMailFlag("Y");
	    		emailScedulerRepo.save(emails);
	    	}	    	
	    	
	    }
	    }
	    
	}    
	    
	
	
	 ScheduledTaskRegistrar taskRegistrar = new ScheduledTaskRegistrar();
     taskRegistrar.destroy();
 }
	
	
}



























}