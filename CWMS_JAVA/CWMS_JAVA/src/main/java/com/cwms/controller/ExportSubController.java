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
import com.cwms.entities.ExportSub_History;
import com.cwms.helper.FileUploadProperties;
import com.cwms.repository.BranchRepo;
import com.cwms.repository.CompanyRepo;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.ExportSub_Historyrepo;
import com.cwms.repository.PartyRepository;
import com.cwms.service.ExportSubService;
import com.cwms.service.ImageService;
import com.cwms.service.ProcessNextIdService;

import jakarta.transaction.Transactional;

@CrossOrigin("*")
@RestController
@RequestMapping("/exportsub")
public class ExportSubController {

	@Autowired
	private ExportSubRepository expsubrepo;
	
	@Autowired
	private CompanyRepo companyRepo;
	
	@Autowired
	private ExportSub_Historyrepo exportsubhistory;
	
	@Autowired
	public FileUploadProperties FileUploadProperties;
	
	@Autowired
	private BranchRepo branchRepo;
	
	@Autowired
	private ExportSubService exportSubService;
	
	
	@Autowired
	private ProcessNextIdService nextservice;
	
	@Autowired
	private PartyRepository partyRepository;
	
	@Autowired
	private ImageService imageService;
	
	@GetMapping("/all/{cid}/{bid}")
	public List<ExportSub> getAlldata(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		return expsubrepo.getall(cid, bid);
	}
	
	@GetMapping("/checkdata/{cid}/{bid}")
	public List<ExportSub> getAlldataforcheck(@PathVariable("cid") String cid, @PathVariable("bid") String bid) {
		return expsubrepo.getallFORCHECK(cid, bid);
	}

	@PostMapping("/insertdata/{id}/{cid}/{bid}")
	public ExportSub singlesavedata( @RequestBody ExportSub expsub,@PathVariable("id") String id,@PathVariable("cid") String cid,@PathVariable("bid") String bid) throws Exception {
//		ExportSub sub = expsubrepo.findRequestId(cid, bid, expsub.getRequestId());
//		System.out.println("Sub "+sub);
//		if(sub != null) {
//			throw new Exception("RequestId already present");
//		}
		
		String nextid = nextservice.autoIncrementSubExpId();
		String nexttransid = nextservice.autoIncrementSubExpTransId();
		expsub.setCompanyId(cid);
		expsub.setBranchId(bid);
		expsub.setSerNo(nextid);
		expsub.setExpSubId(nexttransid);
		expsub.setCreatedBy(id);
		expsub.setCreatedDate(new Date());
		expsub.setStatus('N');
		BigDecimal big = new BigDecimal("0.0");
		expsub.setReceived_wt(big);
		expsub.setImposePenaltyAmount(0);
		expsub.setDgdcStatus("Handed over to DGDC SEEPZ");
		expsub.setNsdlStatus("Pending");
	    expsub.setNoc(0);
	    expsub.setForwardedStatus("N");
	    expsub.setDgdc_cargo_in_scan(0);
	    expsub.setDgdc_cargo_out_scan(0);
	    expsub.setDgdc_seepz_in_scan(0);
	    expsub.setDgdc_seepz_out_scan(0);
		expsub.setSerDate(new Date());
		
		ExportSub_History exporthistory = new ExportSub_History();
		exporthistory.setCompanyId(cid);
		exporthistory.setBranchId(bid);
		exporthistory.setNewStatus("Handed over to DGDC SEEPZ");
		exporthistory.setOldStatus("Pending");
		exporthistory.setRequestId(expsub.getRequestId());
		exporthistory.setTransport_Date(new Date());
		exporthistory.setSerNo(nextid);
		exporthistory.setUpdatedBy(id);
		
		exportsubhistory.save(exporthistory);
		
		return expsubrepo.save(expsub);
	}
	
	@GetMapping("/byid/{cid}/{bid}/{expsubid}/{reqid}")
	public ExportSub getdatabyid(@PathVariable("cid") String companyId,@PathVariable("bid") String branchId,@PathVariable("expsubid") String expsubid,@PathVariable("reqid") String reqId) {
		return expsubrepo.findExportSub(companyId, branchId, expsubid, reqId);
	}

	@Transactional
	@PostMapping("/updateData/{id}")
    public ExportSub updateImportSub(@PathVariable("id") String id, @RequestBody ExportSub updatedImportSub) {
		    updatedImportSub.setEditedBy(id);
		    updatedImportSub.setEditedDate(new Date());
            return expsubrepo.save(updatedImportSub);
        
    }
	
	@GetMapping("/getdata")
	public List<ExportSub> getdata() {
		return expsubrepo.findAll();
		
	}
	
	
	
	@PostMapping(value="/updatedeliverydata",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ExportSub updateDeliveryUpdate(
	    @RequestParam("file") MultipartFile file,
	    @RequestBody ExportSub updatedImportSub
	) {
	    if (!file.isEmpty()) {
	        try {
	            // Save the file to a folder (you need to specify the folder path)
	            String folderPath = "D:/Log"; // Update with your folder path
	            String fileName = file.getOriginalFilename();
	            String filePath = folderPath + "/" + fileName;
	            file.transferTo(new File(filePath));
	            updatedImportSub.setStatus_document(filePath);

	            // Process requestParams and update ExportSub fields as needed
	            // Example: updatedImportSub.setCompanyId(requestParams.get("companyId"));

	            // Save the updatedExportSub object to your database
	            ExportSub savedExportSub = expsubrepo.save(updatedImportSub);
	            return savedExportSub;
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Error saving file and data");
	        }
	    } else {
	        throw new IllegalArgumentException("File is empty");
	    }
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

	    this.expsubrepo.updateData(nsdl, uniqueFilePath, cid, bid, expid, reqid);
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

	
	 @GetMapping("/download/{cid}/{bid}/{expid}/{reqid}")
	    public ResponseEntity<byte[]> downloadImage(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("expid") String expid,@PathVariable("reqid") String reqid) throws IOException {
	        // Retrieve the image path from the database based on imageId
	    	ExportSub expsub = expsubrepo.findExportSub(cid, bid, expid, reqid);
	        String imagePath = expsub.getStatus_document();

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
	    
	    
		@GetMapping("/search")
		public List<ExportSub> searchImports(
		    @RequestParam(name = "companyid", required = false) String companyid,
		    @RequestParam(name = "branchId", required = false) String branchId,
		    @RequestParam(name = "dgdcStatus", required = false) String dgdcStatus,
		    @RequestParam(name = "startDate", required = false)
		    @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
		    @RequestParam(name = "endDate", required = false)
		    @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
		) {
			

		    return expsubrepo.findByAttributes(
		    		companyid,branchId,dgdcStatus, startDate, endDate);
		}
		
		
	 @GetMapping("/exportSubTransaction")
		    public ResponseEntity<List<ExportSub>> findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(
					@RequestParam("serDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date serDate,
		            @RequestParam("companyId") String companyId,
		            @RequestParam("branchId") String branchId,
		            @RequestParam("dgdcStatus") String dgdcStatus) {

			 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String formattedDate = sdf.format(serDate);
				
			 System.out.println(formattedDate);
			 
		        List<ExportSub> exportsSub = exportSubService.findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(formattedDate, companyId, branchId, dgdcStatus);
		        
		        System.out.println(exportsSub);
		        if (exportsSub.isEmpty()) {
		            return ResponseEntity.notFound().build();
		            
		        }

		        return ResponseEntity.ok(exportsSub);
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
        
    
			@GetMapping("/findSubContractData")
		    public List<ExportSub> findExportSubData(
		            @RequestParam("companyId") String companyId,
		            @RequestParam("branchId") String branchId,
		            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
					@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate,
		           
		            @RequestParam("exporter") String exporter
		    ) {
		        return expsubrepo.findImportAllData(companyId, branchId, startDate, endDate, exporter);
		    }

		  @GetMapping("/findByRequestId")
		    public List<ExportSub> findByRequestId(
		            @RequestParam("companyId") String companyId,
		            @RequestParam("branchId") String branchId,
		            @RequestParam("requestId") String requestId
		    ) {
		        return expsubrepo.findRequestIdData(companyId, branchId, requestId);
		    }
		  
		  @GetMapping("/findExportSubAllData")
			public List<ExportSub> findExportSubData(@RequestParam("companyId") String companyId,
					@RequestParam("branchId") String branchId,
					@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startDate,
					@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endDate

			) {
				return expsubrepo.findExportSubAllData(companyId, branchId, startDate, endDate);
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
		  
		  @GetMapping("/byser/{cid}/{bid}/{ser}")
		  public ExportSub getdatabyser(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("ser") String ser) {
			   return expsubrepo.findExportSubByseronly(cid, bid, ser);
		  }
		  
		  @GetMapping("/history/{cid}/{bid}/{rid}/{ser}")
		  public List<ExportSub_History> getalldata(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("rid") String rid,@PathVariable("ser") String ser){
			  return exportsubhistory.getalldata(cid, bid, rid, ser);
		  }
		  
		  
		  @GetMapping("/history1/{cid}/{bid}/{rid}")
		  public List<ExportSub_History> getalldata1(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("rid") String rid){
			  return exportsubhistory.getSingledata1(cid, bid, rid);
		  }
		  
		  @GetMapping("/allhistory/{cid}/{bid}/{reqid}")
		  public List<ExportSub> getalldatabyreqid(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("reqid") String reqid){
			  return expsubrepo.findRequestId1(cid,bid,reqid);
		  }
		  
		  
		  @GetMapping("/checkpartydata/{cid}/{bid}/{exporter}")
		  public List<ExportSub> checkpartydata(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("exporter") String exporter){
			  return expsubrepo.getalldatabyparty(cid,bid,exporter);
		  }
		  
		  
		  @GetMapping("/alldatabyparty/{cid}/{bid}/{exporter}")
			public List<ExportSub> getAlldata3(@PathVariable("cid") String cid, @PathVariable("bid") String bid,@PathVariable("exporter") String exporter) {
				return expsubrepo.getall1(cid, bid,exporter);
			}
			
			@GetMapping("/alldatabyCHA/{cid}/{bid}/{exporter}")
			public List<ExportSub> getAlldata4(@PathVariable("cid") String cid, @PathVariable("bid") String bid,@PathVariable("exporter") String exporter) {
				return expsubrepo.getall2(cid, bid,exporter);
			}
}
