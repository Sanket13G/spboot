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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.Export;
import com.cwms.entities.ExportSub;
import com.cwms.entities.ExportSub_History;
import com.cwms.entities.Export_History;
import com.cwms.entities.Gate_In_Out;
import com.cwms.entities.Import;
import com.cwms.entities.ImportSub;
import com.cwms.entities.ImportSub_History;
import com.cwms.entities.Import_History;
import com.cwms.entities.ScannedParcels;
import com.cwms.repository.ExportRepository;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.ExportSub_Historyrepo;
import com.cwms.repository.Export_HistoryRepository;
import com.cwms.repository.Gate_In_out_Repo;
import com.cwms.repository.ImportRepo;
import com.cwms.repository.ImportSubHistoryRepo;
import com.cwms.repository.ImportSubRepository;
import com.cwms.repository.Import_HistoryRepo;
import com.cwms.repository.ScannedParcelsRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/scan")
public class ScanningController {

	@Autowired
	public ExportSubRepository exportsubrepo;
	
	@Autowired
	private ImportSubHistoryRepo importsubhisrepo;

	@Autowired
	public ImportSubRepository importsubrepo;

	@Autowired
	public ExportRepository exportrepo;
	
	@Autowired
	public Export_HistoryRepository exporthistory;

	@Autowired
	private Export_HistoryRepository export_HistoryRepository;
	
	@Autowired
	private ImportRepo importRepo;
	
	@Autowired
	private Import_HistoryRepo importhistoryrepo;
	
	@Autowired
	private ScannedParcelsRepo scannedparcelsrepo;
	
	  @Autowired
		private Gate_In_out_Repo gateinoutrepo;
	
		@Autowired
		private ExportSub_Historyrepo exportsubhistory;
	
		@PostMapping("/alldataforseepzgateout/{cid}/{bid}/{id}/{sr}/{packnum}")
		private String scandata(@PathVariable("cid") String cid, @PathVariable("bid") String bid, @PathVariable("id") String id, @PathVariable("sr") String sr, @PathVariable("packnum") String packnum) {
		    if (sr.startsWith("D-EX")) {
		        ExportSub exportsub = exportsubrepo.findExportSubByseronly(cid, bid, sr);
		        Gate_In_Out gateinout = gateinoutrepo.findbysr(cid, bid, sr + packnum);
		        
		        if ("N".equals(gateinout.getDgdc_seepz_out_scan())) {
		            gateinout.setDgdc_seepz_out_scan("Y");
		            gateinoutrepo.save(gateinout);

		            exportsub.setDgdc_seepz_out_scan(exportsub.getDgdc_seepz_out_scan() + 1);
		            exportsubrepo.save(exportsub);
		            ScannedParcels scanparcels = new ScannedParcels();
		            scanparcels.setBranchId(bid);
		            scanparcels.setCompanyId(cid);
		            scanparcels.setDoc_Ref_No(exportsub.getRequestId());
		            scanparcels.setNop(exportsub.getNop());
		            scanparcels.setPacknum(packnum);
		            scanparcels.setGateiout(new Date());
		            scanparcels.setParcel_type("");
		            scanparcels.setParty(exportsub.getExporter());
		            scanparcels.setSrNo(exportsub.getSerNo());
		            scanparcels.setTypeOfTransaction("DTA-Export-Out");
		            scanparcels.setScan_parcel_type("seepz");
		            scanparcels.setStatus("N");
		            scannedparcelsrepo.save(scanparcels);

		            if (exportsub.getNop()== exportsub.getDgdc_seepz_out_scan()) {
		                exportsub.setDgdcStatus("Exit from DGDC SEEPZ Gate");
		                exportsubrepo.save(exportsub);
		                

		                ExportSub_History exporthistory = new ExportSub_History();
		                exporthistory.setCompanyId(cid);
		                exporthistory.setBranchId(bid);
		                exporthistory.setNewStatus("Exit from DGDC SEEPZ Gate");
		                exporthistory.setOldStatus("Handed over to Party/CHA");
		                exporthistory.setRequestId(exportsub.getRequestId());
		                
		                exporthistory.setTransport_Date(new Date());
		                exporthistory.setSerNo(exportsub.getSerNo());
		                exporthistory.setUpdatedBy(id);

		                exportsubhistory.save(exporthistory);
		            } else {
		                return "NOP is not matched";
		            }
		        } else {
		            return "Dgdc_seepz_out_scan is already 'Y'";
		        }
		    }
		    else if(sr.startsWith("D-IM")) {
		    	 ImportSub exportsub = importsubrepo.findImportSubBysironly(cid, bid, sr);
			        Gate_In_Out gateinout = gateinoutrepo.findbysr(cid, bid, sr + packnum);
			        
			        if ("N".equals(gateinout.getDgdc_seepz_out_scan())) {
			            gateinout.setDgdc_seepz_out_scan("Y");
			            gateinoutrepo.save(gateinout);

			            exportsub.setDgdc_seepz_out_scan(exportsub.getDgdc_seepz_out_scan() + 1);
			            importsubrepo.save(exportsub);
			            ScannedParcels scanparcels = new ScannedParcels();
			            scanparcels.setBranchId(bid);
			            scanparcels.setCompanyId(cid);
			            scanparcels.setDoc_Ref_No(exportsub.getRequestId());
			            scanparcels.setNop(exportsub.getNop());
			            scanparcels.setPacknum(packnum);
			            scanparcels.setGateiout(new Date());
			            scanparcels.setParcel_type("");
			            scanparcels.setStatus("N");
			            scanparcels.setParty(exportsub.getExporter());
			            scanparcels.setSrNo(exportsub.getSirNo());
			            scanparcels.setTypeOfTransaction("DTA-Import-out");
			            scanparcels.setScan_parcel_type("seepz");
			            scannedparcelsrepo.save(scanparcels);

			            if (exportsub.getNop()== exportsub.getDgdc_seepz_out_scan()) {
			                exportsub.setDgdcStatus("Exit from DGDC SEEPZ Gate");
			                importsubrepo.save(exportsub);

			                ImportSub_History exporthistory = new ImportSub_History();
			                exporthistory.setCompanyId(cid);
			                exporthistory.setBranchId(bid);
			                exporthistory.setNewStatus("Exit from DGDC SEEPZ Gate");
			                exporthistory.setOldStatus("Handed over to Party/CHA");
			                exporthistory.setRequestId(exportsub.getRequestId());
			                exporthistory.setTransport_Date(new Date());
			                exporthistory.setSirNo(exportsub.getSirNo());
			                exporthistory.setUpdatedBy(id);

			                importsubhisrepo.save(exporthistory);
			            } else {
			                return "NOP is not matched";
			            }
			        } else {
			            return "Dgdc_seepz_out_scan is already 'Y'";
			        }
		    }
		    else if(sr.startsWith("EX")) {
		    	 Export exportsub = exportrepo.findBySer(cid, bid, sr);
			        Gate_In_Out gateinout = gateinoutrepo.findbysr(cid, bid, sr + packnum);
			        
			        if ("N".equals(gateinout.getDgdc_seepz_out_scan())) {
			            gateinout.setDgdc_seepz_out_scan("Y");
			            gateinoutrepo.save(gateinout);

			            exportsub.setDgdc_seepz_out_scan(exportsub.getDgdc_seepz_out_scan() + 1);
			            exportrepo.save(exportsub);
			            ScannedParcels scanparcels = new ScannedParcels();
			            scanparcels.setBranchId(bid);
			            scanparcels.setCompanyId(cid);
			            scanparcels.setDoc_Ref_No(exportsub.getSbNo());
			            scanparcels.setNop(exportsub.getNoOfPackages());
			            scanparcels.setPacknum(packnum);
			            scanparcels.setGateiout(new Date());
			            scanparcels.setStatus("N");
			            scanparcels.setParcel_type("");
			            scanparcels.setParty(exportsub.getNameOfExporter());
			            scanparcels.setSrNo(exportsub.getSerNo());
			            scanparcels.setTypeOfTransaction("Export-out");
			            scanparcels.setScan_parcel_type("seepz");
			            scannedparcelsrepo.save(scanparcels);

			            if (exportsub.getNoOfPackages()== exportsub.getDgdc_seepz_out_scan()) {
			                exportsub.setDgdcStatus("Exit from DGDC SEEPZ Gate");
			                exportrepo.save(exportsub);

			                Export_History exporthistory1 = new Export_History();
			                exporthistory1.setCompanyId(cid);
			                exporthistory1.setBranchId(bid);
			                exporthistory1.setNewStatus("Exit from DGDC SEEPZ Gate");
			                exporthistory1.setOldStatus("Handed over to Carting Agent");
			                exporthistory1.setSbNo(exportsub.getSbNo());
			                exporthistory1.setSbRequestId(exportsub.getSbRequestId());
			                exporthistory1.setTransport_Date(new Date());
			                exporthistory1.setserNo(exportsub.getSerNo());
			                exporthistory1.setUpdatedBy(id);

			                exporthistory.save(exporthistory1);
			            } else {
			                return "NOP is not matched";
			            }
			        } else {
			            return "Dgdc_seepz_out_scan is already 'Y'";
			        }
		    }
		    
		    else if(sr.startsWith("IM")) {
		    	 Import exportsub = importRepo.Singledata(cid, bid, sr);
			        Gate_In_Out gateinout = gateinoutrepo.findbysr(cid, bid, sr + packnum);
			        
			        if ("N".equals(gateinout.getDgdc_seepz_out_scan())) {
			            gateinout.setDgdc_seepz_out_scan("Y");
			            gateinoutrepo.save(gateinout);

			            exportsub.setDgdc_seepz_out_scan(exportsub.getDgdc_seepz_out_scan() + 1);
			            importRepo.save(exportsub);
			            ScannedParcels scanparcels = new ScannedParcels();
			            scanparcels.setBranchId(bid);
			            scanparcels.setCompanyId(cid);
			            scanparcels.setDoc_Ref_No(exportsub.getBeNo());
			            scanparcels.setNop(exportsub.getNop());
			            scanparcels.setPacknum(packnum);
			            scanparcels.setGateiout(new Date());
			            scanparcels.setParcel_type("");
			            scanparcels.setParty(exportsub.getImporterId());
			            scanparcels.setSrNo(exportsub.getSirNo());
			            scanparcels.setStatus("N");
			            scanparcels.setTypeOfTransaction("Import-out");
			            scanparcels.setScan_parcel_type("seepz");
			            scannedparcelsrepo.save(scanparcels);

			            if (exportsub.getNop()== exportsub.getDgdc_seepz_out_scan()) {
			                exportsub.setDgdcStatus("Exit from DGDC SEEPZ Gate");
			                importRepo.save(exportsub);

			                Import_History exporthistory1 = new Import_History();
			                exporthistory1.setCompanyId(cid);
			                exporthistory1.setBranchId(bid);
			                exporthistory1.setNewStatus("Exit from DGDC SEEPZ Gate");
			                exporthistory1.setOldStatus("Handed over to Carting Agent");
			                exporthistory1.setMawb(exportsub.getMawb());
			                exporthistory1.setHawb(exportsub.getHawb());
			                exporthistory1.setTransport_Date(new Date());
			                exporthistory1.setSirNo(exportsub.getSirNo());
			                exporthistory1.setUpdatedBy(id);

			                importhistoryrepo.save(exporthistory1);
			            } else {
			                return "NOP is not matched";
			            }
			        } else {
			            return "Dgdc_seepz_out_scan is already 'Y'";
			        }
		    }
		    
		    return "success";
		}
		
		
		@PostMapping("/alldataforseepzgatein/{cid}/{bid}/{id}/{sr}/{packnum}")
		private String scandata1(@PathVariable("cid") String cid, @PathVariable("bid") String bid, @PathVariable("id") String id, @PathVariable("sr") String sr, @PathVariable("packnum") String packnum) {
		    		    
		    if(sr.startsWith("IM")) {
		    	 Import exportsub = importRepo.Singledata(cid, bid, sr);
			        Gate_In_Out gateinout = gateinoutrepo.findbysr(cid, bid, sr + packnum);
			        
			        if ("N".equals(gateinout.getDgdc_seepz_in_scan())) {
			            gateinout.setDgdc_seepz_in_scan("Y");
			            gateinoutrepo.save(gateinout);

			            exportsub.setDgdc_seepz_in_scan(exportsub.getDgdc_seepz_in_scan() + 1);
			            importRepo.save(exportsub);
			            ScannedParcels scanparcels = new ScannedParcels();
			            scanparcels.setBranchId(bid);
			            scanparcels.setCompanyId(cid);
			            scanparcels.setDoc_Ref_No(exportsub.getBeNo());
			            scanparcels.setNop(exportsub.getNop());
			            scanparcels.setPacknum(packnum);
			            scanparcels.setGateiout(new Date());
			            scanparcels.setParcel_type("");
			            scanparcels.setStatus("N");
			            scanparcels.setParty(exportsub.getImporterId());
			            scanparcels.setSrNo(exportsub.getSirNo());
			            scanparcels.setTypeOfTransaction("Import-in");
			            scanparcels.setScan_parcel_type("seepz");
			            scannedparcelsrepo.save(scanparcels);

			            if (exportsub.getNop()== exportsub.getDgdc_seepz_in_scan()) {
			                exportsub.setDgdcStatus("Entry at DGDC SEEPZ Gate");
			                importRepo.save(exportsub);

			                Import_History exporthistory1 = new Import_History();
			                exporthistory1.setCompanyId(cid);
			                exporthistory1.setBranchId(bid);
			                exporthistory1.setNewStatus("Entry at DGDC SEEPZ Gate");
			                exporthistory1.setOldStatus("Exit from DGDC Cargo Gate");
			                exporthistory1.setMawb(exportsub.getMawb());
			                exporthistory1.setHawb(exportsub.getHawb());
			                exporthistory1.setTransport_Date(new Date());
			                exporthistory1.setSirNo(exportsub.getSirNo());
			                exporthistory1.setUpdatedBy(id);

			                importhistoryrepo.save(exporthistory1);
			               

			            } else {
			                return "NOP is not matched";
			            }
			        } else {
			            return "Dgdc_seepz_out_scan is already 'Y'";
			        }
		    }
		    
		    return "success";
		}

		@PostMapping("/alldataforcargoout/{cid}/{bid}/{id}/{sr}/{packnum}")
		private String scandata2(@PathVariable("cid") String cid, @PathVariable("bid") String bid, @PathVariable("id") String id, @PathVariable("sr") String sr, @PathVariable("packnum") String packnum) {
		    		    
		    if(sr.startsWith("IM")) {
		    	 Import exportsub = importRepo.Singledata(cid, bid, sr);
			        Gate_In_Out gateinout = gateinoutrepo.findbysr(cid, bid, sr + packnum);
			        
			        if ("N".equals(gateinout.getDgdc_cargo_out_scan())) {
			            gateinout.setDgdc_cargo_out_scan("Y");
			            gateinoutrepo.save(gateinout);

			            exportsub.setDgdc_cargo_out_scan(exportsub.getDgdc_cargo_out_scan() + 1);              
			            importRepo.save(exportsub);
			            ScannedParcels scanparcels = new ScannedParcels();
			            scanparcels.setBranchId(bid);
			            scanparcels.setCompanyId(cid);
			            scanparcels.setDoc_Ref_No(exportsub.getBeNo());
			            scanparcels.setNop(exportsub.getNop());
			            scanparcels.setPacknum(packnum);
			            scanparcels.setGateiout(new Date());
			            scanparcels.setParcel_type("");
			            scanparcels.setStatus("N");
			            scanparcels.setParty(exportsub.getImporterId());
			            scanparcels.setSrNo(exportsub.getSirNo());
			            scanparcels.setTypeOfTransaction("Import-out");
			            scanparcels.setScan_parcel_type("cargo");
			            scannedparcelsrepo.save(scanparcels);


			            if (exportsub.getNop()== exportsub.getDgdc_cargo_out_scan()) {
			                exportsub.setDgdcStatus("Exit from DGDC Cargo Gate");
			                importRepo.save(exportsub);

			                Import_History exporthistory1 = new Import_History();
			                exporthistory1.setCompanyId(cid);
			                exporthistory1.setBranchId(bid);
			                exporthistory1.setNewStatus("Exit from DGDC Cargo Gate");
			                exporthistory1.setOldStatus("Handed over to Carting Agent");
			                exporthistory1.setMawb(exportsub.getMawb());
			                exporthistory1.setHawb(exportsub.getHawb());
			                exporthistory1.setTransport_Date(new Date());
			                exporthistory1.setSirNo(exportsub.getSirNo());
			                exporthistory1.setUpdatedBy(id);

			                importhistoryrepo.save(exporthistory1);
			            } else {
			                return "NOP is not matched";
			            }
			        } else {
			            return "Dgdc_seepz_out_scan is already 'Y'";
			        }
		    }
		    
		    return "success";
		}
		
		
		@PostMapping("/alldataforcargoin/{cid}/{bid}/{id}/{sr}/{packnum}")
		private String scandata3(@PathVariable("cid") String cid, @PathVariable("bid") String bid, @PathVariable("id") String id, @PathVariable("sr") String sr, @PathVariable("packnum") String packnum) {
		    		    
		    if(sr.startsWith("EX")) {
		    	 Export exportsub = exportrepo.findBySer(cid, bid, sr);
			        Gate_In_Out gateinout = gateinoutrepo.findbysr(cid, bid, sr + packnum);
			        
			        if ("N".equals(gateinout.getDgdc_cargo_in_scan())) {
			            gateinout.setDgdc_cargo_in_scan("Y");
			            gateinoutrepo.save(gateinout);

			            exportsub.setDgdc_cargo_in_scan(exportsub.getDgdc_cargo_in_scan() + 1);              
			            exportrepo.save(exportsub);
			            ScannedParcels scanparcels = new ScannedParcels();
			            scanparcels.setBranchId(bid);
			            scanparcels.setCompanyId(cid);
			            scanparcels.setDoc_Ref_No(exportsub.getSbNo());
			            scanparcels.setNop(exportsub.getNoOfPackages());
			            scanparcels.setPacknum(packnum);
			            scanparcels.setGateiout(new Date());
			            scanparcels.setParcel_type("");
			            scanparcels.setStatus("N");
			            scanparcels.setParty(exportsub.getNameOfExporter());
			            scanparcels.setSrNo(exportsub.getSerNo());
			            scanparcels.setTypeOfTransaction("Export-in");
			            scanparcels.setScan_parcel_type("cargo");
			            scannedparcelsrepo.save(scanparcels);


			            if (exportsub.getNoOfPackages()== exportsub.getDgdc_cargo_in_scan()) {
			                exportsub.setDgdcStatus("Entry at DGDC Cargo GATE");
			                exportrepo.save(exportsub);

			                Export_History exporthistory1 = new Export_History();
			                exporthistory1.setCompanyId(cid);
			                exporthistory1.setBranchId(bid);
			                exporthistory1.setNewStatus("Entry at DGDC Cargo GATE");
			                exporthistory1.setOldStatus("Exit from DGDC SEEPZ Gate");
			                exporthistory1.setSbRequestId(exportsub.getSbRequestId());
			                exporthistory1.setSbNo(exportsub.getSbNo());
			                exporthistory1.setTransport_Date(new Date());
			                exporthistory1.setserNo(exportsub.getSerNo());
			                exporthistory1.setUpdatedBy(id);

			                exporthistory.save(exporthistory1);
			            } else {
			                return "NOP is not matched";
			            }
			        } else {
			            return "Dgdc_seepz_out_scan is already 'Y'";
			        }
		    }
		    
		    return "success";
		}
		
//		@GetMapping("/seepzdata/{cid}/{bid}")
//		public List<ScannedParcels> getdata1(@PathVariable("cid") String cid,@PathVariable("bid") String bid){
//			return scannedparcelsrepo.getalldata1(cid, bid);
//		}
		
		@GetMapping("/seepzdata/{cid}/{bid}")
		public List<ScannedParcels> getdata1(@PathVariable("cid") String cid,@PathVariable("bid") String bid) throws ParseException{
	        // Format the start and end of the day as strings
	        LocalDate currentDate = LocalDate.now();
	        LocalDateTime startOfDay = currentDate.atStartOfDay();

	        // Get the end of the day (just before midnight) as LocalDateTime
	        LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);
	        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedStartOfDayStr = startOfDay.format(formatter1);
	        String formattedEndOfDayStr = endOfDay.format(formatter1);

	        // Parse the formatted strings into Date objects
	        Date formattedStartOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedStartOfDayStr);
	        Date formattedEndOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedEndOfDayStr);
			return scannedparcelsrepo.getalldata3(cid, bid,formattedStartOfDay,formattedEndOfDay);
		}
		
//		@GetMapping("/cargodata/{cid}/{bid}")
//		public List<ScannedParcels> getdata2(@PathVariable("cid") String cid,@PathVariable("bid") String bid){
//			return scannedparcelsrepo.getalldata2(cid, bid);
//		}
		
		@GetMapping("/cargodata/{cid}/{bid}")
		public List<ScannedParcels> getdata2(@PathVariable("cid") String cid,@PathVariable("bid") String bid) throws ParseException{
			 LocalDate currentDate = LocalDate.now();
		        LocalDateTime startOfDay = currentDate.atStartOfDay();

		        // Get the end of the day (just before midnight) as LocalDateTime
		        LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);
		        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedStartOfDayStr = startOfDay.format(formatter1);
		        String formattedEndOfDayStr = endOfDay.format(formatter1);

		        // Parse the formatted strings into Date objects
		        Date formattedStartOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedStartOfDayStr);
		        Date formattedEndOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedEndOfDayStr);
			return scannedparcelsrepo.getalldata4(cid, bid,formattedStartOfDay,formattedEndOfDay);
		}
		
		
		@GetMapping("/exportoutdata/{cid}/{bid}")
		public List<Export> getCurrentoutdata1(@PathVariable("cid") String cid,@PathVariable("bid") String bid) throws ParseException{
			 LocalDate currentDate = LocalDate.now();
		        LocalDateTime startOfDay = currentDate.atStartOfDay();

		        // Get the end of the day (just before midnight) as LocalDateTime
		        LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);
		        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedStartOfDayStr = startOfDay.format(formatter1);
		        String formattedEndOfDayStr = endOfDay.format(formatter1);

		        // Parse the formatted strings into Date objects
		        Date formattedStartOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedStartOfDayStr);
		        Date formattedEndOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedEndOfDayStr);
		        
		        
		        
		        List<String> getdata = scannedparcelsrepo.getExportoutdata(cid, bid, formattedStartOfDay, formattedEndOfDay);
		        
		        List<Export> importdata = new ArrayList<Export>();
		        
		        if(!getdata.isEmpty()) {
		        	 for(String data : getdata) {
		        		 Export import1 = exportrepo.finexportdata(cid,bid,data);
		        		 importdata.add(import1);
		        	 }
		        	
		       
		        }
		        return importdata;
		} 
		
		
		@GetMapping("/importsuboutdata/{cid}/{bid}")
		public List<ImportSub> getCurrentoutdata2(@PathVariable("cid") String cid,@PathVariable("bid") String bid) throws ParseException{
			 LocalDate currentDate = LocalDate.now();
		        LocalDateTime startOfDay = currentDate.atStartOfDay();

		        // Get the end of the day (just before midnight) as LocalDateTime
		        LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);
		        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedStartOfDayStr = startOfDay.format(formatter1);
		        String formattedEndOfDayStr = endOfDay.format(formatter1);

		        // Parse the formatted strings into Date objects
		        Date formattedStartOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedStartOfDayStr);
		        Date formattedEndOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedEndOfDayStr);
		        
		        
		        
		        List<String> getdata = scannedparcelsrepo.getImportSuboutdata(cid, bid, formattedStartOfDay, formattedEndOfDay);
		        
		        List<ImportSub> importdata = new ArrayList<ImportSub>();
		        
		        if(!getdata.isEmpty()) {
		        	 for(String data : getdata) {
		        		 ImportSub import1 = importsubrepo.Singledata(cid,bid,data);
		        		 importdata.add(import1);
		        	 }
		        	
		       
		        }
		        return importdata;
		} 
		
		
		@GetMapping("/exportsuboutdata/{cid}/{bid}")
		public List<ExportSub> getCurrentoutdata3(@PathVariable("cid") String cid,@PathVariable("bid") String bid) throws ParseException{
			 LocalDate currentDate = LocalDate.now();
		        LocalDateTime startOfDay = currentDate.atStartOfDay();

		        // Get the end of the day (just before midnight) as LocalDateTime
		        LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);
		        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedStartOfDayStr = startOfDay.format(formatter1);
		        String formattedEndOfDayStr = endOfDay.format(formatter1);

		        // Parse the formatted strings into Date objects
		        Date formattedStartOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedStartOfDayStr);
		        Date formattedEndOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedEndOfDayStr);
		        
		        
		        
		        List<String> getdata = scannedparcelsrepo.getExportSuboutdata(cid, bid, formattedStartOfDay, formattedEndOfDay);
		        
		        List<ExportSub> importdata = new ArrayList<ExportSub>();
		        
		        if(!getdata.isEmpty()) {
		        	 for(String data : getdata) {
		        		 ExportSub import1 = exportsubrepo.findExportSubByseronly(cid,bid,data);
		        		 importdata.add(import1);
		        	 }
		        	
		       
		        }
		        return importdata;
		} 
		
		
		@GetMapping("/importoutdata/{cid}/{bid}")
		public List<Import> getCurrentoutdata(@PathVariable("cid") String cid,@PathVariable("bid") String bid) throws ParseException{
			 LocalDate currentDate = LocalDate.now();
		        LocalDateTime startOfDay = currentDate.atStartOfDay();

		        // Get the end of the day (just before midnight) as LocalDateTime
		        LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);
		        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedStartOfDayStr = startOfDay.format(formatter1);
		        String formattedEndOfDayStr = endOfDay.format(formatter1);

		        // Parse the formatted strings into Date objects
		        Date formattedStartOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedStartOfDayStr);
		        Date formattedEndOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedEndOfDayStr);
		        
		        
		        
		        List<String> getdata = scannedparcelsrepo.getImportoutdata(cid, bid, formattedStartOfDay, formattedEndOfDay);
		        
		        List<Import> importdata = new ArrayList<Import>();
		        
		        if(!getdata.isEmpty()) {
		        	 for(String data : getdata) {
		        		 Import import1 = importRepo.Singledata(cid,bid,data);
		        		 importdata.add(import1);
		        	 }
		        	
		       
		        }
		        return importdata;
		} 
}
