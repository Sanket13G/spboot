package com.cwms.controller;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cwms.entities.Branch;
import com.cwms.entities.Company;
import com.cwms.entities.ExportSub;
import com.cwms.entities.ImportSub;
import com.cwms.entities.ImportSub_History;
import com.cwms.helper.FileUploadProperties;
import com.cwms.repository.BranchRepo;
import com.cwms.repository.CompanyRepo;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.ImportSubHistoryRepo;
import com.cwms.repository.ImportSubRepository;
import com.cwms.repository.PartyRepository;
import com.cwms.service.ImageService;
import com.cwms.service.ImportSubService;
import com.cwms.service.ProcessNextIdService;

import jakarta.transaction.Transactional;

@CrossOrigin("*")
@RestController
@RequestMapping("/importsub")
public class ImportSubController {

	@Autowired
	private ImportSubRepository impsubrepo;
	
	@Autowired
	private ProcessNextIdService nextservice;
	
	@Autowired
	private ExportSubRepository exportrepo;
	
	@Autowired
	public FileUploadProperties FileUploadProperties;
	
	@Autowired
	private ImportSubHistoryRepo importsubhisrepo;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private PartyRepository partyRepository;
	
	@Autowired
	private CompanyRepo companyRepo;
	
	@Autowired
	private BranchRepo branchRepo;
	
	@Autowired
	public ImportSubService importSubService;

	@GetMapping("/all/{cid}/{bid}")
	public List<ImportSub> getAlldata(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		return impsubrepo.getall(cid, bid);
	}
	
	
	@GetMapping("/allwtlgd/{cid}/{bid}")
	public List<ImportSub> getAlldata1(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		return impsubrepo.getalltocheckLGD1(cid, bid);
	}
	
	
	@GetMapping("/alllgd/{cid}/{bid}")
	public List<ImportSub> getAlldatatochecklgd(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		return impsubrepo.getalltocheckLGD(cid, bid);
	}
	
	
	@GetMapping("/single/{cid}/{bid}/{sir}")
	public ImportSub getSingledata(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("sir") String sir) {
		return this.impsubrepo.Singledata(cid, bid, sir);
	}

	@PostMapping("/insertdata/{id}/{cid}/{bid}")
	public ImportSub singlesavedata( @RequestBody ImportSub impsub,@PathVariable("id") String id,@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
		String nextid = nextservice.autoIncrementSubImpId();
		String nexttransid = nextservice.autoIncrementSubImpTransId();
		impsub.setSirNo(nextid);
		impsub.setCompanyId(cid);
		impsub.setBranchId(bid);
		impsub.setLgdStatus("N");
		impsub.setImpSubId(nexttransid);
		impsub.setCreatedBy(id);
		impsub.setCreatedDate(new Date());
		impsub.setStatus("N");
		impsub.setDgdcStatus("Handed over to DGDC SEEPZ");
		impsub.setNsdlStatus("Pending");
		impsub.setReentryDate(new Date());
		impsub.setForwardedStatus("N");
		impsub.setNoc(0);
		impsub.setDgdc_cargo_in_scan(0);
		impsub.setDgdc_cargo_out_scan(0);
		impsub.setDgdc_seepz_in_scan(0);
		impsub.setDgdc_seepz_out_scan(0);
		impsub.setSirDate(new Date());
		BigDecimal big = new BigDecimal("0.0");
        impsub.setImposePenaltyAmount(0);
		
		 ImportSub_History importsubhistory = new ImportSub_History();
		 importsubhistory.setCompanyId(cid);
		 importsubhistory.setBranchId(bid);
		 importsubhistory.setNewStatus("Handed over to DGDC SEEPZ");
		 importsubhistory.setOldStatus("Pending");
		 importsubhistory.setRequestId(impsub.getRequestId());
		 importsubhistory.setSirNo(nextid);
		 importsubhistory.setTransport_Date(new Date());
		 importsubhistory.setUpdatedBy(id);
		 importsubhisrepo.save(importsubhistory);
        
		return impsubrepo.save(impsub);
	}
	
	
	@PostMapping("/insertexportdata/{id}/{cid}/{bid}/{eid}")
	public ImportSub exportsinglesavedata( @RequestBody ImportSub impsub,@PathVariable("id") String id,@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("eid") String eid) {
		String nextid = nextservice.autoIncrementSubImpId();
		String nexttransid = nextservice.autoIncrementSubImpTransId();
		impsub.setSirNo(nextid);
		impsub.setCompanyId(cid);
		impsub.setBranchId(bid);
		impsub.setImpSubId(nexttransid);
		impsub.setCreatedBy(id);
		impsub.setCreatedDate(new Date());
		impsub.setStatus("N");
		BigDecimal big = new BigDecimal("0.0");
        impsub.setImposePenaltyAmount(0);
        impsub.setNoc(0);
		impsub.setDgdc_cargo_in_scan(0);
		impsub.setDgdc_cargo_out_scan(0);
		impsub.setDgdc_seepz_in_scan(0);
		impsub.setForwardedStatus("N");
		impsub.setDgdc_seepz_out_scan(0);
		impsub.setDgdcStatus("Handed over to DGDC SEEPZ");
		impsub.setNsdlStatus("Pending");
		
		impsub.setSirDate(new Date());
		this.exportrepo.updateDELETEStatus(cid, bid, eid, impsub.getRequestId());
		ImportSub_History importsubhistory = new ImportSub_History();
		 importsubhistory.setCompanyId(cid);
		 importsubhistory.setBranchId(bid);
		 importsubhistory.setNewStatus("Handed over to DGDC SEEPZ");
		 importsubhistory.setOldStatus("Pending");
		 importsubhistory.setRequestId(impsub.getRequestId());
		 importsubhistory.setSirNo(nextid);
		 importsubhistory.setTransport_Date(new Date());
		 importsubhistory.setUpdatedBy(id);
		 importsubhisrepo.save(importsubhistory);
		return impsubrepo.save(impsub);
	}
	
	@GetMapping("/byid/{cid}/{bid}/{impsubid}/{reqid}")
	public ImportSub getdatabyid(@PathVariable("cid") String companyId,@PathVariable("bid") String branchId,@PathVariable("impsubid") String impSubId,@PathVariable("reqid") String reqId) {
		return impsubrepo.findImportSub(companyId, branchId, impSubId, reqId);
	}

	@Transactional
	@PostMapping("/updateData/{id}")
    public ImportSub updateImportSub(@PathVariable("id") String id, @RequestBody ImportSub updatedImportSub) {
		    updatedImportSub.setEditedBy(id);
		    updatedImportSub.setEditedDate(new Date());
            return impsubrepo.save(updatedImportSub);
        
    }
	
	@GetMapping("/getdata")
	public List<ImportSub> getdata() {
		return impsubrepo.findAll();
		
	}
	
	@PostMapping("/changedata/{nsdl}/{cid}/{bid}/{expid}/{reqid}")
	public void changeDeliveryUpdate(@PathVariable("nsdl") String nsdl,
	                                 @PathVariable("cid") String cid,
	                                 @PathVariable("bid") String bid,
	                                 @PathVariable("expid") String expid,
	                                 @PathVariable("reqid") String reqid,
	                                 @RequestPart("file") MultipartFile file) throws IllegalStateException, IOException {

	    // Save the file to a folder (you need to specify the folder path)
	    String folderPath = FileUploadProperties.getPath(); // Update with your folder path
	    String fileName = file.getOriginalFilename();
	    String filePath = folderPath + "/" + fileName;

	    // Use the provided logic to generate a unique file name
	    String uniqueFileName = generateUniqueFileName(folderPath, fileName);

	    // Construct the full path for the unique file
	    String uniqueFilePath = folderPath + "/" + uniqueFileName;
	    file.transferTo(new File(uniqueFilePath));

	    this.impsubrepo.updateData(nsdl, uniqueFilePath, cid, bid, expid, reqid);
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

	
	@GetMapping("/getexpdata/{cid}/{bid}/{reqid}")
	public ExportSub getReqId(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("reqid") String reqid) {
		return  exportrepo.findRequestId(cid, bid, reqid);
	}
	
	@GetMapping("/getimpdata/{cid}/{bid}/{reqid}")
	public List<ImportSub> getReqIdforimp(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("reqid") String reqid) {
		return  impsubrepo.findRequestAllId(cid, bid, reqid);
	}
	
	@PostMapping("/penalty")
	public ImportSub imposepenalty(@RequestBody ImportSub impsub) {
	    return this.impsubrepo.save(impsub);
	}
	
	
	@GetMapping("/importSubTransaction")
    public ResponseEntity<List<ImportSub>> findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(
			@RequestParam("sirDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date sirDate,
            @RequestParam("companyId") String companyId,
            @RequestParam("branchId") String branchId,
            @RequestParam("dgdcStatus") String dgdcStatus) {

	 
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(sirDate);
		
	 System.out.println(formattedDate);
	 
        List<ImportSub> imports = importSubService.findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(formattedDate, companyId, branchId, dgdcStatus);
        
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
	
	@GetMapping("/findImportSubContractData")
    public List<ImportSub> findSubContractData(
            @RequestParam("companyId") String companyId,
            @RequestParam("branchId") String branchId,
          
            @RequestParam("exporter") String exporter) {
        // Call the repository method to fetch data based on the parameters
        List<ImportSub> importSubList = impsubrepo.findImportAllData(companyId, branchId,exporter);

        // You can add any additional processing here if needed

        return importSubList;
    }
	
	@GetMapping("/download/{cid}/{bid}/{impid}/{reqid}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("impid") String impid,@PathVariable("reqid") String reqid) throws IOException {
        // Retrieve the image path from the database based on imageId
    	ImportSub importsub = impsubrepo.findImportSub(cid, bid, impid, reqid);
        String imagePath = importsub.getStatus_document();

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

	 
    @GetMapping("/findImportSubAllData/{companyId}/{branchId}/{startDate}/{endDate}")
	public List<ImportSub> findExportSubData(@PathVariable("companyId") String companyId,
			@PathVariable("branchId") String branchId,
			@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate

	) {
		return impsubrepo.findImportSubAllData(companyId, branchId, startDate, endDate);
	}
	  
	  
	 @GetMapping("/bysir/{cid}/{bid}/{sir}")
	  public ImportSub getdatabyser(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("sir") String sir) {
		   return impsubrepo.findImportSubBysironly(cid, bid, sir);
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
	  
	  @GetMapping("/history/{cid}/{bid}/{rid}/{ser}")
	  public List<ImportSub_History> getalldata(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("rid") String rid,@PathVariable("ser") String ser){
		  return importsubhisrepo.getalldata(cid, bid, rid, ser);
	  }
	  
		@GetMapping("/getexpdata1/{cid}/{bid}/{reqid}")
		public List<ExportSub> getReqId1(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("reqid") String reqid) {
			return  exportrepo.findRequestId1(cid, bid, reqid);
		}
		
		
		  @GetMapping("/checkimportpartydata/{cid}/{bid}/{exporter}")
		  public List<ImportSub> checkpartydata(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("exporter") String exporter){
			  return impsubrepo.getalldatabyparty(cid,bid,exporter);
		  }
		 
		  
		  @GetMapping("/checkimportpartydata1/{cid}/{bid}/{exporter}")
		  public List<ImportSub> checkpartydata1(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("exporter") String exporter){
			  return impsubrepo.getalldatabyparty1(cid,bid,exporter);
		  }
		  
		  
		  @GetMapping("/alldatabyparty/{cid}/{bid}/{exporter}")
			public List<ImportSub> getAlldata3(@PathVariable("cid") String cid, @PathVariable("bid") String bid,@PathVariable("exporter") String exporter) {
				return impsubrepo.getalltocheckLGD3(cid, bid,exporter);
			}
			
			
			@GetMapping("/alldatabycha/{cid}/{bid}/{exporter}")
			public List<ImportSub> getAlldata4(@PathVariable("cid") String cid, @PathVariable("bid") String bid,@PathVariable("exporter") String exporter) {
				return impsubrepo.getalltocheckLGD5(cid, bid,exporter);
			}
			
			@GetMapping("/alllgddatabyparty/{cid}/{bid}/{exporter}")
			public List<ImportSub> getAlldatar(@PathVariable("cid") String cid, @PathVariable("bid") String bid,@PathVariable("exporter") String exporter) {
				return impsubrepo.getalltocheckLGD4(cid, bid,exporter);
			}
			
			
			@GetMapping("/alllgddatabycha/{cid}/{bid}/{exporter}")
			public List<ImportSub> getAlldatar1(@PathVariable("cid") String cid, @PathVariable("bid") String bid,@PathVariable("exporter") String exporter) {
				return impsubrepo.getalltocheckLGD6(cid, bid,exporter);
	  
}
}
