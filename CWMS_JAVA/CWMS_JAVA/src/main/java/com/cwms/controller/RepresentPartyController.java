//package com.cwms.controller;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.cwms.entities.Export;
//import com.cwms.entities.ExportSub;
//import com.cwms.entities.ExportSub_History;
//import com.cwms.entities.Export_History;
//import com.cwms.entities.ImportSub;
//import com.cwms.entities.ImportSub_History;
//import com.cwms.entities.RepresentParty;
//import com.cwms.helper.FileUploadProperties;
//import com.cwms.repository.ExportRepository;
//import com.cwms.repository.ExportSubRepository;
//import com.cwms.repository.ExportSub_Historyrepo;
//import com.cwms.repository.Export_HistoryRepository;
//import com.cwms.repository.ImportSubHistoryRepo;
//import com.cwms.repository.ImportSubRepository;
//import com.cwms.repository.RepresentPartyRepository;
//import com.cwms.service.EmailService;
//import com.cwms.service.ImageService;
//import com.cwms.service.ProcessNextIdService;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/represent")
//public class RepresentPartyController {
//	@Autowired
//	public RepresentPartyRepository representPartyrepo;
//
//	
//	@Autowired
//	public EmailService emailService;
//	
//	@Autowired
//	public ExportSubRepository exportsubrepo;
//	
//	@Autowired
//	private ImportSubHistoryRepo importsubhisrepo;
//
//	@Autowired
//	public ImportSubRepository importsubrepo;
//
//	@Autowired
//	public ExportRepository exportrepo;
//
//	@Autowired
//	private Export_HistoryRepository export_HistoryRepository;
//	
//	@Autowired
//	private ExportSub_Historyrepo exportsubhistory;
//	
//	@Autowired
//	private ProcessNextIdService processnextId;
//	
//	@Autowired
//	private ImageService imageService;
//	
//	@Autowired
//	public FileUploadProperties FileUploadProperties;
//	
//	@GetMapping("/byuiddata/{cid}/{bid}/{uid}")
//	public List<RepresentParty> getdatabyuid(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("uid") String uid){
//		return representPartyrepo.getbyuserId(cid, bid, uid);
//	}
//	
//	@GetMapping("/byrepresentid/{cid}/{bid}/{rid}")
//	public RepresentParty getrepresentbyuid(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("rid") String rid){
//		return representPartyrepo.getrepresentbyid(rid, cid, bid);
//	}
//	
//	
//	 @GetMapping("/checkotp/{cid}/{bid}/{rid}/{mid}/{otp}/{expid}/{reqID}/{partyid}/{id}")
//	    public ResponseEntity<String> checkOtp(
//	            @PathVariable("cid") String cid,
//	            @PathVariable("bid") String bid,
//	            @PathVariable("rid") String rid,
//	            @PathVariable("mid") String mid,
//	            @PathVariable("otp") String providedOtp,
//	            @PathVariable("expid") String expid,
//	            @PathVariable("reqID") String reqID,
//	            @PathVariable("partyid") String partyId,
//	            @PathVariable("id") String id
//	    		) {
//
//	        // Execute your custom query
//	        RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
//
//	        if (representParty != null) {
//	             
//	            if (representParty.getOtp().equals(providedOtp)) {
//	            	this.exportsubrepo.updateStatus(cid, bid, expid, reqID,partyId,rid);	            	
//	            	representParty.setOtp("");
//	                this.representPartyrepo.save(representParty);
//	                ExportSub exportsub = exportsubrepo.findRequestId(cid, bid, reqID);
//	                ExportSub_History exphistory = new ExportSub_History();
//	                exphistory.setCompanyId(exportsub.getCompanyId());
//	                exphistory.setBranchId(exportsub.getBranchId());
//	                exphistory.setRequestId(exportsub.getRequestId());
//	                exphistory.setSerNo(exportsub.getSerNo());
//	                exphistory.setNewStatus("Handed over to Party/CHA");
//	                exphistory.setOldStatus("Handed over to DGDC SEEPZ");
//	                exphistory.setTransport_Date(new Date());
//	                exphistory.setUpdatedBy(id);
//	                exportsubhistory.save(exphistory);
//	                return ResponseEntity.ok("OTP verification successful!");
//	            } else {
//	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
//	            }
//	        } else {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
//	        }
//	    }
//	 
//	 
//	 @GetMapping("/checkCHAotp/{cid}/{bid}/{rid}/{mid}/{otp}/{expid}/{reqID}/{partyid}/{id}")
//	    public ResponseEntity<String> checkCHAOtp(
//	            @PathVariable("cid") String cid,
//	            @PathVariable("bid") String bid,
//	            @PathVariable("rid") String rid,
//	            @PathVariable("mid") String mid,
//	            @PathVariable("otp") String providedOtp,
//	            @PathVariable("expid") String expid,
//	            @PathVariable("reqID") String reqID,
//	            @PathVariable("partyid") String partyId,
//	            @PathVariable("id") String id
//	    		) {
//
//	        // Execute your custom query
//	        RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
//
//	        if (representParty != null) {
//	             
//	            if (representParty.getOtp().equals(providedOtp)) {
//	            	this.exportsubrepo.updateCHAStatus(cid, bid, expid, reqID,partyId,rid);
//	            	representParty.setOtp("");
//	                this.representPartyrepo.save(representParty);
//	                ExportSub exportsub = exportsubrepo.findRequestId(cid, bid, reqID);
//	                ExportSub_History exphistory = new ExportSub_History();
//	                exphistory.setCompanyId(exportsub.getCompanyId());
//	                exphistory.setBranchId(exportsub.getBranchId());
//	                exphistory.setRequestId(exportsub.getRequestId());
//	                exphistory.setSerNo(exportsub.getSerNo());
//	                exphistory.setNewStatus("Handed over to Party/CHA");
//	                exphistory.setOldStatus("Handed over to DGDC SEEPZ");
//	                exphistory.setTransport_Date(new Date());
//	                exphistory.setUpdatedBy(id);
//	                exportsubhistory.save(exphistory);
//	                return ResponseEntity.ok("OTP verification successful!");
//	            } else {
//	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
//	            }
//	        } else {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
//	        }
//	    }
//	 
//	 
//	 @GetMapping("/checkimpotp/{cid}/{bid}/{rid}/{mid}/{otp}/{expid}/{reqID}/{partyid}/{id}")
//	    public ResponseEntity<String> checkIMPOtp(
//	            @PathVariable("cid") String cid,
//	            @PathVariable("bid") String bid,
//	            @PathVariable("rid") String rid,
//	            @PathVariable("mid") String mid,
//	            @PathVariable("otp") String providedOtp,
//	            @PathVariable("expid") String expid,
//	            @PathVariable("reqID") String reqID,
//	            @PathVariable("id")String id,
//	            @PathVariable("partyid") String partyId
//	    		) {
//
//	        // Execute your custom query
//	        RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
//
//	        if (representParty != null) {
//	             
//	            if (representParty.getOtp().equals(providedOtp)) {
//	            	this.importsubrepo.updateStatus(cid, bid, expid, reqID,partyId,rid);
//	            	representParty.setOtp("");
//	                this.representPartyrepo.save(representParty);
//	                ImportSub impsub = importsubrepo.findImportSub(cid, bid, expid,reqID);
//	                ImportSub_History importsubhistory = new ImportSub_History();
//	       		 importsubhistory.setCompanyId(impsub.getCompanyId());
//	       		 importsubhistory.setBranchId(impsub.getBranchId());
//	       		 importsubhistory.setNewStatus("Handed over to Party/CHA");
//	       		 importsubhistory.setOldStatus("Handed over to DGDC SEEPZ");
//	       		 importsubhistory.setRequestId(impsub.getRequestId());
//	       		 importsubhistory.setSirNo(impsub.getSirNo());
//	       		 importsubhistory.setTransport_Date(new Date());
//	       		 importsubhistory.setUpdatedBy(id);
//	       		 importsubhisrepo.save(importsubhistory);
//	                return ResponseEntity.ok("OTP verification successful!");
//	            } else {
//	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
//	            }
//	        } else {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
//	        }
//	    }
//	 
//	 
//	 @GetMapping("/checkimpCHAotp/{cid}/{bid}/{rid}/{mid}/{otp}/{expid}/{reqID}/{partyid}/{id}")
//	    public ResponseEntity<String> checkIMPCHAOtp(
//	            @PathVariable("cid") String cid,
//	            @PathVariable("bid") String bid,
//	            @PathVariable("rid") String rid,
//	            @PathVariable("mid") String mid,
//	            @PathVariable("otp") String providedOtp,
//	            @PathVariable("expid") String expid,
//	            @PathVariable("reqID") String reqID,
//	            @PluginAttribute("id") String id,
//	            @PathVariable("partyid") String partyId
//	    		) {
//
//	        // Execute your custom query
//	        RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
//
//	        if (representParty != null) {
//	             
//	            if (representParty.getOtp().equals(providedOtp)) {
//	            	this.importsubrepo.updateCHAStatus(cid, bid, expid, reqID,partyId,rid);
//	            	representParty.setOtp("");
//	                this.representPartyrepo.save(representParty);
//	                ImportSub impsub = importsubrepo.findImportSub(cid, bid, expid,reqID);
//	                ImportSub_History importsubhistory = new ImportSub_History();
//	       		 importsubhistory.setCompanyId(impsub.getCompanyId());
//	       		 importsubhistory.setBranchId(impsub.getBranchId());
//	       		 importsubhistory.setNewStatus("Handed over to Party/CHA");
//	       		 importsubhistory.setOldStatus("Handed over to DGDC SEEPZ");
//	       		 importsubhistory.setRequestId(impsub.getRequestId());
//	       		 importsubhistory.setSirNo(impsub.getSirNo());
//	       		 importsubhistory.setTransport_Date(new Date());
//	       		 importsubhistory.setUpdatedBy(id);
//	       		 importsubhisrepo.save(importsubhistory);
//	                return ResponseEntity.ok("OTP verification successful!");
//	            } else {
//	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
//	            }
//	        } else {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
//	        }
//	    }
//
//	 @GetMapping("/checkexpcartotp/{cid}/{bid}/{rid}/{mid}/{otp}/{expid}/{reqID}/{partyid}/{id}")
//		public ResponseEntity<String> checkEXPCartingOtp(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
//				@PathVariable("rid") String rid, @PathVariable("mid") String mid, @PathVariable("otp") String providedOtp,
//				@PathVariable("expid") String expid, @PathVariable("reqID") String reqID,
//				@PathVariable("id") String id,
//				@PathVariable("partyid") String partyId) {
//
//			// Execute your custom query
//			RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
//
//			if (representParty != null) {
//
//				if (representParty.getOtp().equals(providedOtp)) {
//				//	this.exportrepo.updateCartingRecord(partyId, rid, cid, bid, reqID, expid);
//					Export export = exportrepo.findBySBNoandSbreqid(cid, bid, reqID, expid);
//					export.setDgdcStatus("Handed over to Carting Agent");
//					export.setCartingAgent(partyId);
//					export.setPartyRepresentativeId(rid);
//					export.setTpNo(processnextId.getNextTPNo());
//					export.setTpDate(new Date());
//					export.setPctmNo(processnextId.getNextPctmNo());
//					export.setTpDate(new Date());
//					exportrepo.save(export);
//				
//					Export_History export_History = new Export_History();
//					export_History.setCompanyId(export.getCompanyId());
//					export_History.setBranchId(export.getBranchId());
//					export_History.setNewStatus("Handed over to Carting Agent");
//					export_History.setOldStatus("Handed over to DGDC SEEPZ");
//					export_History.setSbNo(export.getSbNo());
//					export_History.setSbRequestId(export.getSbRequestId());
//					export_History.setserNo(export.getSerNo());
//					export_History.setTransport_Date(new Date());
//					export_History.setUpdatedBy(id);
//					export_History.SetHistoryDate();
//					export_HistoryRepository.save(export_History);
//					representParty.setOtp("");
//	                this.representPartyrepo.save(representParty);
//					return ResponseEntity.ok("OTP verification successful!");
//				} else {
//					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
//				}
//			} else {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
//			}
//
//		}
//
//		@PostMapping("/checkhandovercartotp/{cid}/{bid}/{rid}/{mid}/{otp}/{partyid}/{id}")
//		public ResponseEntity<String> handoverCartingOtp(@PathVariable("cid") String cid, 
//		                                                @PathVariable("bid") String bid,
//		                                                @PathVariable("rid") String rid, 
//		                                                @PathVariable("mid") String mid, 
//		                                                @PathVariable("partyid") String partyid, 
//		                                                @PathVariable("otp") String providedOtp,
//		                                                @PathVariable("id") String id,
//		                                                @RequestBody List<Export> exportmain) {
//
//		    // Execute your custom query
//		    RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
//
//		    if (representParty != null) {
//		        if (representParty.getOtp().equals(providedOtp)) {
//		            List<Export_History> exportHistoryList = new ArrayList<>();
//		            Map<String, String> airlineToPCTMMap = new HashMap<>();
//		            String tpno = processnextId.getNextTPNo();
//	                Date date = new Date();
//		            
//		            for (Export exp : exportmain) {
//		            	String airlineName = exp.getAirlineCode();
//		            	  // Check if airlineName is not null or empty
//		                if (airlineName != null && !airlineName.isEmpty()) {
//		                    String pctmno = airlineToPCTMMap.get(airlineName);
//		                    
//		                    if (pctmno == null) {
//		                        // If no PCTM number is associated with this airline, generate a new one
//		                    	pctmno = processnextId.getNextPctmNo();
//		                        airlineToPCTMMap.put(airlineName, pctmno);
//		                    }
//		                exp.setCartingAgent(partyid);
//		                exp.setPartyRepresentativeId(rid);
//		                exp.setPctmNo(pctmno);
//		                exp.setPctmDate(new Date());
//		                exp.setDgdcStatus("Handed over to Carting Agent");
//		                exp.setTpNo(tpno);
//		                exp.setTpDate(date);
//		                
//		                exportrepo.save(exp);
//		                representParty.setOtp("");
//		                this.representPartyrepo.save(representParty);
//
//		            
//		                Export_History export_History = new Export_History();
//						export_History.setCompanyId(exp.getCompanyId());
//						export_History.setBranchId(exp.getBranchId());
//						export_History.setNewStatus("Handed over to Carting Agent");
//						export_History.setOldStatus("Handed over to DGDC SEEPZ");
//						export_History.setSbNo(exp.getSbNo());
//						export_History.setSbRequestId(exp.getSbRequestId());
//						export_History.setserNo(exp.getSerNo());
//						export_History.setTransport_Date(new Date());
//						export_History.setUpdatedBy(id);
//						export_History.SetHistoryDate();
//		                exportHistoryList.add(export_History);
//		                }
//		            }
//
//		            this.export_HistoryRepository.saveAll(exportHistoryList); // Save all history records
//
//		            return ResponseEntity.ok("OTP verification successful!");
//		        } else {
//		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
//		        }
//		    } else {
//		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
//		    }
//		}
//		
//		
////		@PostMapping("/checkhandovercartotp/{cid}/{bid}/{rid}/{mid}/{otp}/{partyid}/{id}")
////		public ResponseEntity<String> handoverCartingOtp(@PathVariable("cid") String cid, 
////		                                                @PathVariable("bid") String bid,
////		                                                @PathVariable("rid") String rid, 
////		                                                @PathVariable("mid") String mid, 
////		                                                @PathVariable("partyid") String partyid, 
////		                                                @PathVariable("otp") String providedOtp,
////		                                                @PathVariable("id") String id,
////		                                                @RequestBody List<Export> exportmain) {
////
////		    // Execute your custom query
////		    RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
////
////		    if (representParty != null) {
////		        if (representParty.getOtp().equals(providedOtp)) {
////		            List<Export_History> exportHistoryList = new ArrayList<>();
////		            Map<String, String> airlineToPCTMMap = new HashMap<>();
////		            String tpno = processnextId.getNextTPNo();
////	                Date date = new Date();
////		            
////		            for (Export exp : exportmain) {
////		            	String airlineName = exp.getAirlineCode();
////		            	  // Check if airlineName is not null or empty
////		                if (airlineName != null && !airlineName.isEmpty()) {
////		                    String pctmno = airlineToPCTMMap.get(airlineName);
////		                    
////		                    if (pctmno == null) {
////		                        // If no PCTM number is associated with this airline, generate a new one
////		                    	pctmno = processnextId.getNextPctmNo();
////		                        airlineToPCTMMap.put(airlineName, pctmno);
////		                    }
////		                exp.setCartingAgent(partyid);
////		                exp.setPartyRepresentativeId(rid);
////		                exp.setPctmNo(pctmno);
////		                exp.setPctmDate(new Date());
////		                exp.setDgdcStatus("Handed over to Carting Agent");
////		                exp.setTpNo(tpno);
////		                exp.setTpDate(date);
////		                
////		                exportrepo.save(exp);
////		                representParty.setOtp("");
////		                this.representPartyrepo.save(representParty);
////
////		            
////		                Export_History export_History = new Export_History();
////						export_History.setCompanyId(exp.getCompanyId());
////						export_History.setBranchId(exp.getBranchId());
////						export_History.setNewStatus("Handed over to Carting Agent");
////						export_History.setOldStatus("Handed over to DGDC SEEPZ");
////						export_History.setSbNo(exp.getSbNo());
////						export_History.setSbRequestId(exp.getSbRequestId());
////						export_History.setserNo(exp.getSerNo());
////						export_History.setTransport_Date(new Date());
////						export_History.setUpdatedBy(id);
////						export_History.SetHistoryDate();
////		                exportHistoryList.add(export_History);
////		                }
////		            }
////
////		            this.export_HistoryRepository.saveAll(exportHistoryList); // Save all history records
////
////		            return ResponseEntity.ok("OTP verification successful!");
////		        } else {
////		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
////		        }
////		    } else {
////		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
////		    }
////		}
//		
//		
//		@PostMapping("/checkreceivecartotp/{cid}/{bid}/{rid}/{mid}/{otp}/{id}")
//		public ResponseEntity<String> receiveCartingOtp(@PathVariable("cid") String cid, 
//		                                                @PathVariable("bid") String bid,
//		                                                @PathVariable("rid") String rid, 
//		                                                @PathVariable("mid") String mid, 
//		                                                
//		                                                @PathVariable("otp") String providedOtp,
//		                                                @PathVariable("id") String id,
//		                                                @RequestBody List<Export> exportmain) {
//
//		    // Execute your custom query
//		    RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
//
//		    if (representParty != null) {
//		        if (representParty.getOtp().equals(providedOtp)) {
//		            List<Export_History> exportHistoryList = new ArrayList<>();
//		            
//
//		            for (Export exp : exportmain) {
//		        
//		                exp.setDgdcStatus("Handed over to DGDC Cargo");
//		      
//		                
//		                exportrepo.save(exp);
//
//		              
//		                Export_History export_History = new Export_History();
//						export_History.setCompanyId(exp.getCompanyId());
//						export_History.setBranchId(exp.getBranchId());
//						export_History.setNewStatus("Handed over to DGDC Cargo");
//						export_History.setOldStatus("Entry at DGDC Cargo GATE");
//						export_History.setSbNo(exp.getSbNo());
//						export_History.setSbRequestId(exp.getSbRequestId());
//						export_History.setserNo(exp.getSerNo());
//						export_History.setTransport_Date(new Date());
//						export_History.setUpdatedBy(id);
//						export_History.SetHistoryDate();
//		                exportHistoryList.add(export_History);
//		            }
//
//		            this.export_HistoryRepository.saveAll(exportHistoryList); // Save all history records
//		            representParty.setOtp("");
//	                this.representPartyrepo.save(representParty);
//		            return ResponseEntity.ok("OTP verification successful!");
//		        } else {
//		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
//		        }
//		    } else {
//		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
//		    }
//		}
//
//		
//		@GetMapping("/generateotp/{cid}/{bid}/{rid}/{mobile}/{nop}")
//		public String generateCartingOTP(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("rid") String rid,@PathVariable("mobile") String mobile,@PathVariable("nop") String nop) {
//			String otp = generateOTP();
//			RepresentParty represent = representPartyrepo.checkOTP(cid, bid, rid, mobile);
//			represent.setOtp(otp);
//			this.representPartyrepo.save(represent);
//			
//			 try {
//			        String apiKey = "apikey=" + URLEncoder.encode("N2E2ZjU4NmU1OTY5Njg2YjczNjI3OTMxNjg3MjQ4NjM=", "UTF-8");
//			        String message = "Dear Sir/Madam, Please validate your identity in DGDC E-Custodian with OTP " + otp + " .";
//			        String sender = "sender=" + URLEncoder.encode("DGDCSZ", "UTF-8");
//			        String numbers = "numbers=" + URLEncoder.encode("91" + mobile, "UTF-8");
//
//			        // Send data
//			        String data = "https://api.textlocal.in/send/?" + apiKey + "&" + numbers + "&message=" + URLEncoder.encode(message, "UTF-8") + "&" + sender;
//			        URL url = new URL(data);
//			        URLConnection conn = url.openConnection();
//			        conn.setDoOutput(true);
//			        
//			        if (represent.getEmail() != null && !represent.getEmail().isEmpty())
//			        {
//			        	 emailService.SendOtpOnEmail(represent.getEmail(),otp,nop);
//			        }
//			       
//			        
//			        // Get the response
//			        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			        String line;
//			        StringBuilder sResult = new StringBuilder();
//			        while ((line = rd.readLine()) != null) {
//			            sResult.append(line).append(" ");
//			        }
//			        rd.close();
//
//			        return sResult.toString();
//			    } catch (Exception e) {
//			        System.out.println("Error SMS " + e);
//			        return "Error " + e;
//			    }
//		}
//		
//		private String generateOTP() {
//		    Random random = new Random();
//		    int otp = random.nextInt(900000) + 100000; // Generates a random number between 1000 and 9999
//		    return String.valueOf(otp);
//		}
//		
//		@GetMapping("/getImage/{cid}/{bid}/{rid}")
//	    public ResponseEntity<byte[]> downloadImage(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("rid") String rid) throws IOException {
//	        // Retrieve the image path from the database based on imageId
//	    	RepresentParty represent = representPartyrepo.getrepresentbyid(rid,cid, bid);
//	        String imagePath = represent.getImagePath();
//
//	        if (imagePath != null) {
//	        	
//	        	 MediaType mediaType = MediaType.IMAGE_JPEG; // Default to JPEG
//
//	             if (imagePath.endsWith(".pdf")) {
//	                 mediaType = MediaType.APPLICATION_PDF;
//	             } else if (imagePath.endsWith(".png")) {
//	                 mediaType = MediaType.IMAGE_PNG;
//	             } else if (imagePath.endsWith(".jpg") || imagePath.endsWith(".jpeg")) {
//	                 mediaType = MediaType.IMAGE_JPEG;
//	             }
//	            // Load the image file as a byte array
//	            byte[] imageBytes = imageService.loadImage(imagePath);
//
//	            // Determine the content type based on the image file type (e.g., image/jpeg)
//	            HttpHeaders headers = new HttpHeaders();
//	       // Adjust as needed
//	            headers.setContentType(mediaType);
//
//	            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
//	        } else {
//	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	        }
//	    }
//	    
//	 // Add Representative of Party
//		
//// 		@PostMapping("/addRepresentative/{companyid}/{branchId}/{partyid}")
//// 		public ResponseEntity<Object> addPartyRepresentative(
//// 				
//// 				@PathVariable("companyid") String cid, 
//// 		        @PathVariable("branchId") String bid,		                                               		                                               
//// 		        @PathVariable("partyid") String partyid  ,
//// 		        
//// 		        @RequestParam("file") MultipartFile file,
//// 				@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
//// 				@RequestParam("lastName") String lastName, @RequestParam("mobile") String mobile,
//// 				@RequestParam("companyid") String companyid, @RequestParam("companyid") String companyid1,
//// 				@RequestParam("branchId") String branchId, @RequestParam("status") String status
//// 		        ) throws java.io.IOException {
//// 			
//// 			try {
//// 		                                               
//// 			      String nextId = processnextId.autoIncrementRepresentativePartyId();   
//// 			
//// 			      RepresentParty newparty = new RepresentParty();
//// 			      
//// 			      newparty.setRepresentativeId(nextId);
//// 			      newparty.setCompanyId(cid);
//// 			      newparty.setBranchId(bid);
//// 			      newparty.setUserId(partyid);
//// 			      newparty.setUserType("Party");
//// 			      newparty.setFirstName(firstName);
//// 			      newparty.setMiddleName(middleName);
//// 			      newparty.setLastName(lastName);
//// 			      newparty.setMobile(mobile);
//// 			      newparty.setStatus("A");
//// 			      newparty.setUserStatus(status);
//// 			      System.out.print(status);
//// 			      newparty.setOtp("1000");
//// 			      newparty.setCreatedDate(new Date());
//// 			      
//// 			   // Get the original file name
//// 					String originalFileName = file.getOriginalFilename();
////
//// 					// Generate a unique file name to avoid duplicates
//// 					String uniqueFileName = generateUniqueFileName(originalFileName);
////
//// 		      newparty.setImagePath(FileUploadProperties.getPath() + uniqueFileName);
////   
//// 		  	// Save the file to your local system with the unique name
//// 				Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName));
//// 				
////// 			      representPartyrepo.save(newparty);		  
//// 			      RepresentParty savedParty = representPartyrepo.save(newparty);
//// 			      return ResponseEntity.status(HttpStatus.OK).body(savedParty);
//// 			}
//// 			catch (IOException e) {
//// 				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
//// 			}
//// 			      // Return the saved object with a success status
//// 			      
//// 		}
//	    
//	    
// // Add Representative of Party
//		
// 		@PostMapping("/addRepresentative/{companyid}/{branchId}/{partyid}")
// 		public ResponseEntity<Object> addPartyRepresentative(
// 				
// 				@PathVariable("companyid") String cid, 
// 		        @PathVariable("branchId") String bid,		                                               		                                               
// 		        @PathVariable("partyid") String partyid  ,
// 		        
// 		        @RequestParam("file") MultipartFile file,
// 		       @RequestParam("file1") MultipartFile file1,
// 				@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
// 				@RequestParam("lastName") String lastName, @RequestParam("mobile") String mobile,
// 				@RequestParam("companyid") String companyid, @RequestParam("companyid") String companyid1,
// 				@RequestParam("branchId") String branchId, @RequestParam("status") String status
// 		        ) throws java.io.IOException {
// 			
// 			try {
// 		                                               
// 			      String nextId = processnextId.autoIncrementRepresentativePartyId();   
// 			
// 			      RepresentParty newparty = new RepresentParty();
// 			      
// 			      newparty.setRepresentativeId(nextId);
// 			      newparty.setCompanyId(cid);
// 			      newparty.setBranchId(bid);
// 			      newparty.setUserId(partyid);
// 			      newparty.setUserType("Party");
// 			      newparty.setFirstName(firstName);
// 			      newparty.setMiddleName(middleName);
// 			      newparty.setLastName(lastName);
// 			      newparty.setMobile(mobile);
// 			      newparty.setStatus("A");
// 			      newparty.setUserStatus(status);
// 			      System.out.print(status);
// 			      newparty.setOtp("1000");
// 			      newparty.setCreatedDate(new Date());
// 			      
// 			     newparty.setEmail("demoemail@gmail.com");
// 			      
// 			   // Get the original file name
// 					String originalFileName = file.getOriginalFilename();
// 					// Generate a unique file name to avoid duplicates
// 					String uniqueFileName = generateUniqueFileName(originalFileName);
// 		      newparty.setImagePath(FileUploadProperties.getPath() + uniqueFileName);
//
// 		  	// Save the file to your local system with the unique name
// 				Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName));
// 				
// 				
// 				
// 				String originalFileName1 = file1.getOriginalFilename();
// 				String uniqueFileName1= generateUniqueFileName(originalFileName1);
// 				 newparty.setSignImagePath(FileUploadProperties.getPath() + uniqueFileName1);
// 				 
// 				Files.copy(file1.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName1));
//// 			      representPartyrepo.save(newparty);		  
// 			      RepresentParty savedParty = representPartyrepo.save(newparty);
// 			      return ResponseEntity.status(HttpStatus.OK).body(savedParty);
// 			}
// 			catch (IOException e) {
// 				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
// 			}
// 			      // Return the saved object with a success status
// 			      
// 		}
//	    
//	    
//	    
// 		
// 		
// 		
//// 		// update with file 
//// 		@PostMapping("/updateRepresentative/{companyid}/{branchId}/{partyid}/{representativeID}")
//// 		public ResponseEntity<Object> updatePartyRepresentative(
//// 				
//// 				@PathVariable("companyid") String cid, 
//// 		        @PathVariable("branchId") String bid,		                                               		                                               
//// 		        @PathVariable("partyid") String partyid  ,
//// 		        @PathVariable("representativeID") String rpid  ,	
//// 		        @RequestParam("file") MultipartFile file,
//// 				@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
//// 				@RequestParam("lastName") String lastName, @RequestParam("mobile") String mobile,@RequestParam("status") String status
//// 		        ) throws java.io.IOException {
//// 			
//// 			try {
//// 		                                               
//// 				   RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(cid, bid, partyid, rpid);
////
//// 				   representPartyObject.setFirstName(firstName);
//// 				   representPartyObject.setMiddleName(middleName);
//// 				   representPartyObject.setLastName(lastName);
//// 				   representPartyObject.setMobile(mobile);
//// 				   representPartyObject.setStatus("A");
//// 				   representPartyObject.setUserStatus(status);
//// 				   representPartyObject.setOtp("1000");
//// 				   representPartyObject.setEditedDate(new Date());
////
//// 				   if(file!= null)
//// 				   {      // Get the original file name
//// 						String originalFileName = file.getOriginalFilename();
////
//// 						// Generate a unique file name to avoid duplicates
//// 						String uniqueFileName = generateUniqueFileName(originalFileName);
////
//// 						representPartyObject.setImagePath(FileUploadProperties.getPath() + uniqueFileName);
//// 	  
//// 			  	// Save the file to your local system with the unique name
//// 					Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName));                  }
//// 			  
//// 				
//// 	  
//// 			      RepresentParty savedParty = representPartyrepo.save(representPartyObject);
//// 			      return ResponseEntity.status(HttpStatus.OK).body(savedParty);
//// 			}
//// 			catch (IOException e) {
//// 				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
//// 			}
//// 			      // Return the saved object with a success status
//// 			      
//// 		}	
// 		
// 		
// 	// update with file 
// 	// update with file 
// 			@PostMapping("/updateRepresentative/{companyid}/{branchId}/{partyid}/{representativeID}")
// 			public ResponseEntity<Object> updatePartyRepresentative(
// 					
// 					@PathVariable("companyid") String cid, 
// 			        @PathVariable("branchId") String bid,		                                               		                                               
// 			        @PathVariable("partyid") String partyid  ,
// 			        @PathVariable("representativeID") String rpid  ,	
// 			        @RequestParam("file") MultipartFile file,
// 			        @RequestParam(value = "file1", required = false) MultipartFile file1,
// 					@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
// 					@RequestParam("lastName") String lastName, @RequestParam("mobile") String mobile,@RequestParam("status") String status
// 			        ) throws java.io.IOException {
// 				
// 				try {
// 			                                               
// 					   RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(cid, bid, partyid, rpid);
//
// 					   if (representPartyObject != null) {
// 						    String nsdlStatusDocsPath = representPartyObject.getImagePath();
// 						    Path filePath = Paths.get(nsdlStatusDocsPath);
//
// 						    // Check if the file exists
// 						    if (Files.exists(filePath)) {
// 						        try {
// 						            // Delete the file
// 						            Files.delete(filePath);
// 						            System.out.println("File deleted successfully.");
//
// 						            // Additional code if needed after deleting the file
//
// 						        } catch (IOException e) {
// 						            // Handle the exception appropriately
// 						            System.err.println("Failed to delete the file: " + e.getMessage());
// 						        }
// 						    } else {
// 						        System.out.println("File does not exist.");
// 						    }
// 						}
//
// 					   				   
// 					   representPartyObject.setFirstName(firstName);
// 					   representPartyObject.setMiddleName(middleName);
// 					   representPartyObject.setLastName(lastName);
// 					   representPartyObject.setMobile(mobile);
// 					   representPartyObject.setStatus("A");
// 					   representPartyObject.setUserStatus(status);
// 					   representPartyObject.setOtp("1000");
// 					   representPartyObject.setEditedDate(new Date());
//
// 					   if(file!= null)
// 					   {      // Get the original file name
// 							String originalFileName = file.getOriginalFilename();
// 							// Generate a unique file name to avoid duplicates
// 							String uniqueFileName = generateUniqueFileName(originalFileName);
// 							representPartyObject.setImagePath(FileUploadProperties.getPath() + uniqueFileName);
// 				  	// Save the file to your local system with the unique name
// 						Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName)); }
// 					   
// 					   
// 					   
// 					   if(file1!= null)
// 					   {      
// 							String originalFileName1 = file1.getOriginalFilename();					
// 							String uniqueFileName1 = generateUniqueFileName(originalFileName1);
// 							representPartyObject.setImagePath(FileUploadProperties.getPath() + uniqueFileName1);
// 						Files.copy(file1.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName1)); }
// 					   
// 	  
// 				      RepresentParty savedParty = representPartyrepo.save(representPartyObject);
// 				      return ResponseEntity.status(HttpStatus.OK).body(savedParty);
// 				}
// 				catch (IOException e) {
// 					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
// 				}
// 				      // Return the saved object with a success status
// 				      
// 			}	
// 		
// 		
// 		
// 		
// 			@PostMapping("/updateRepresentativewithoutFile/{companyid}/{branchId}/{partyid}/{representativeID}")
// 	 		public ResponseEntity<Object> updateRepresentativewithoutFile(
// 	 				
// 	 				@PathVariable("companyid") String cid, 
// 	 		        @PathVariable("branchId") String bid,		                                               		                                               
// 	 		        @PathVariable("partyid") String partyid  ,
// 	 		        @PathVariable("representativeID") String rpid  ,	
// 	 		       @RequestParam(value = "file1", required = false) MultipartFile file1,
// 	 				@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
// 	 				@RequestParam("lastName") String lastName, @RequestParam("mobile") String mobile,@RequestParam("status") String status
// 	 		        ) throws java.io.IOException {
// 	 			
// 	 			RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(cid, bid, partyid, rpid);
//
// 				   representPartyObject.setFirstName(firstName);
// 				   representPartyObject.setMiddleName(middleName);
// 				   representPartyObject.setLastName(lastName);
// 				   representPartyObject.setMobile(mobile);
// 				   representPartyObject.setStatus("A");
// 				   representPartyObject.setUserStatus(status);
// 				   representPartyObject.setOtp("1000");
// 				   representPartyObject.setEditedDate(new Date());
// 				   
// 				   if(file1!= null)
// 				   {      
// 						String originalFileName1 = file1.getOriginalFilename();					
// 						String uniqueFileName1 = generateUniqueFileName(originalFileName1);
//
// 						representPartyObject.setSignImagePath(FileUploadProperties.getPath() + uniqueFileName1);
// 					Files.copy(file1.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName1)); }
// 				   
//
// 				   
// 				  RepresentParty savedParty = representPartyrepo.save(representPartyObject);
// 				  return ResponseEntity.status(HttpStatus.OK).body(savedParty);
// 	 			      
// 	 		}	
// 	 		
//
// 		
//
//
// 		// Helper method to generate a unique file name
// 		private String generateUniqueFileName(String originalFileName) {
// 			String uniqueFileName = originalFileName;
// 			int suffix = 1;
//
// 			// Check if the file with the same name already exists
// 			while (Files.exists(Paths.get(FileUploadProperties.getPath() + uniqueFileName))) {
// 				int dotIndex = originalFileName.lastIndexOf('.');
// 				String nameWithoutExtension = dotIndex != -1 ? originalFileName.substring(0, dotIndex) : originalFileName;
// 				String fileExtension = dotIndex != -1 ? originalFileName.substring(dotIndex) : "";
// 				uniqueFileName = nameWithoutExtension + "_" + suffix + fileExtension;
// 				suffix++;
// 			}
//
// 			return uniqueFileName;
// 		}
// 		
// 		
// 		// get All Representative of Party		
// 		
// 		@GetMapping("/getAllRepresentParty/{cid}/{bid}/{pid}")
// 		public List<RepresentParty> getAllRepresentParty(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("pid") String rid){
//
// 			return representPartyrepo.findByCompanyIdAndBranchIdAndUserIdAndStatusNot(cid,bid,rid ,"D");
// 		}
// 		
//// 		@GetMapping("/getAllRepresentParty/{cid}/{bid}/{pid}")
//// 		public ResponseEntity<?> getAllRepresentParty(@PathVariable("cid") String cid,
//// 		                                              @PathVariable("bid") String bid,
//// 		                                              @PathVariable("pid") String Pid) throws java.io.IOException {
//// 		    List<RepresentParty> representPartyList = representPartyrepo.getByCompanyIdAndBranchIdAndUserId(cid, bid, Pid);
//// 		    if (representPartyList != null && !representPartyList.isEmpty()) {
//// 		        // Assuming you want to get the image data for the first RepresentParty in the list
//// 		        RepresentParty representParty = representPartyList.get(0);
//// 		        String nsdlStatusDocsPath = representParty.getImagePath();
//// 		        try {
//// 		            String fileExtension = getFileExtension(nsdlStatusDocsPath);
// //
//// 		            if (isImageFile(fileExtension)) {
//// 		                byte[] imageBytes = Files.readAllBytes(Paths.get(nsdlStatusDocsPath));
//// 		                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//// 		                String dataURL = "data:image/jpeg;base64," + base64Image;
// //
//// 		                // Create a DTO to hold both the list and image data
//// 		                Map<String, Object> responseData = new HashMap<>();
//// 		                responseData.put("representPartyList", representPartyList);
//// 		                responseData.put("imageData", dataURL);
// //
//// 		                HttpHeaders headers = new HttpHeaders();
//// 		                headers.setContentType(MediaType.APPLICATION_JSON); // Set the content type to application/json
// //
//// 		                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
//// 		            }
//// 		        } catch (IOException e) {
//// 		            e.printStackTrace();
//// 		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//// 		        }
//// 		    }
// //
//// 		    return ResponseEntity.notFound().build();
//// 		}
//
//
// 		
// 		
//// 		 Supplementary code for getting image
// 		private String getFileExtension(String filePath) {
// 			int dotIndex = filePath.lastIndexOf('.');
// 			if (dotIndex >= 0 && dotIndex < filePath.length() - 1) {
// 				return filePath.substring(dotIndex + 1).toLowerCase();
// 			}
// 			return "";
// 		}
// 		
// 		private boolean isImageFile(String fileExtension) {
// 			return fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png")
// 					|| fileExtension.equals("gif");
// 		}
// 		
// 		
//// 		code to get All images by using url		
// 		@GetMapping("/getAllRepresentPartyImg/{rpid}")
// 		public ResponseEntity<?> getImageOrPdf(@PathVariable("rpid") String rpid) throws IOException {
//
// 			RepresentParty RepresentPartyObject = representPartyrepo.getByRepresentativeId(rpid);
//
// 			if (RepresentPartyObject != null) {
// 				String nsdlStatusDocsPath = RepresentPartyObject.getImagePath();
// 				Path filePath = Paths.get(nsdlStatusDocsPath);
//
// 				// Check if the file exists
// 				if (Files.exists(filePath)) {
// 					try {
// 						String fileExtension = getFileExtension(nsdlStatusDocsPath);
//
// 						if (isImageFile(fileExtension)) {
// 							// If it's an image file, return a data URL
// 							byte[] imageBytes = Files.readAllBytes(filePath);
// 							String base64Image = Base64.getEncoder().encodeToString(imageBytes);
// 							String dataURL = "data:image/jpeg;base64," + base64Image;
//
// 							HttpHeaders headers = new HttpHeaders();
// 							headers.setContentType(MediaType.TEXT_PLAIN); // Set the content type to text/plain
//
// 							return new ResponseEntity<>(dataURL, headers, HttpStatus.OK);
// 						} 
// 					} catch (java.io.IOException e) {
// 						// Handle the IOException appropriately (e.g., log it)
// 						e.printStackTrace();
// 						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
// 					}
// 				}
// 			}
//
// 			return ResponseEntity.notFound().build();
// 		}
// 		
//
//// 		code for delete Representative Data set status D
// 		
// 		@DeleteMapping("/deleteRepresentative/{cid}/{bid}/{uid}/{rpid}")
// 		public ResponseEntity<RepresentParty> deleteSingleRepresentParty(
// 		        @PathVariable("cid") String cid,
// 		        @PathVariable("bid") String bid,
// 		        @PathVariable("uid") String uid,
// 		        @PathVariable("rpid") String rpid) {
//
// 		    RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(cid, bid, uid, rpid);
//
// 		    if (representPartyObject != null) {
// 		        representPartyObject.setStatus("D");
//
// 		        RepresentParty savedParty = representPartyrepo.save(representPartyObject);
//
// 		        return ResponseEntity.status(HttpStatus.OK).body(savedParty);
// 		    } else {
// 		        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
// 		    }
// 		}
//
// 		
//// 		get single image from database
// 		@GetMapping("/getImage1/{compid}/{branchId}/{pid}/{rpid}")
// 		public ResponseEntity<?> getImageOrPdf(@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
// 				@PathVariable("pid") String pid, @PathVariable("rpid") String rpid
// 				) throws IOException {
//
//
// 			  RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(compid, branchId, pid, rpid);
// 			
// 			  if (representPartyObject != null) {
// 				String nsdlStatusDocsPath = representPartyObject.getImagePath();
// 				Path filePath = Paths.get(nsdlStatusDocsPath);
//
// 				// Check if the file exists
// 				if (Files.exists(filePath)) {
// 					try {
// 						String fileExtension = getFileExtension(nsdlStatusDocsPath);
//
// 						if (isImageFile(fileExtension)) {
// 							// If it's an image file, return a data URL
// 							byte[] imageBytes = Files.readAllBytes(filePath);
// 							String base64Image = Base64.getEncoder().encodeToString(imageBytes);
// 							String dataURL = "data:image/jpeg;base64," + base64Image;
//
// 							HttpHeaders headers = new HttpHeaders();
// 							headers.setContentType(MediaType.TEXT_PLAIN); 
//
// 							return new ResponseEntity<>(dataURL, headers, HttpStatus.OK);
// 						}
// 					} catch (java.io.IOException e) {
// 						// Handle the IOException appropriately (e.g., log it)
// 						e.printStackTrace();
// 						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
// 					}
// 				}
// 			}
//
// 			return ResponseEntity.notFound().build();
// 		}	
// 		
// 		
// 		
// 		
// 		
//// 		get single image from database
// 		@GetMapping("/getImage2/{compid}/{branchId}/{pid}/{rpid}")
// 		public ResponseEntity<?> getImageOrPdf2(@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
// 				@PathVariable("pid") String pid, @PathVariable("rpid") String rpid
// 				) throws IOException {
//
//
// 			  RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(compid, branchId, pid, rpid);
// 			
// 			  if (representPartyObject != null) {
// 				String nsdlStatusDocsPath = representPartyObject.getSignImagePath();
// 				Path filePath = Paths.get(nsdlStatusDocsPath);
//
// 				// Check if the file exists
// 				if (Files.exists(filePath)) {
// 					try {
// 						String fileExtension = getFileExtension(nsdlStatusDocsPath);
//
// 						if (isImageFile(fileExtension)) {
// 							// If it's an image file, return a data URL
// 							byte[] imageBytes = Files.readAllBytes(filePath);
// 							String base64Image = Base64.getEncoder().encodeToString(imageBytes);
// 							String dataURL = "data:image/jpeg;base64," + base64Image;
//
// 							HttpHeaders headers = new HttpHeaders();
// 							headers.setContentType(MediaType.TEXT_PLAIN); 
//
// 							return new ResponseEntity<>(dataURL, headers, HttpStatus.OK);
// 						}
// 					} catch (java.io.IOException e) {
// 						// Handle the IOException appropriately (e.g., log it)
// 						e.printStackTrace();
// 						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
// 					}
// 				}
// 			}
//
// 			return ResponseEntity.notFound().build();
// 		}	
// 		
// 		
// 		
// 		
// 		
// 		
// 		
// 		
// 		
// 		
// 		
//// 		get single image from database
// 		@GetMapping("/getImage/{compid}/{branchId}/{pid}/{rpid}")
// 		public ResponseEntity<?> getImage(@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
// 				@PathVariable("pid") String pid, @PathVariable("rpid") String rpid
// 				) throws IOException, java.io.IOException {
//
//
// 			  RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(compid, branchId, pid, rpid);
//
// 			  String  imagePath = representPartyObject.getImagePath();
// 	        if (imagePath != null) {
// 	        	
// 	        	 MediaType mediaType = MediaType.IMAGE_JPEG; // Default to JPEG
//
// 	             if (imagePath.endsWith(".pdf")) {
// 	                 mediaType = MediaType.APPLICATION_PDF;
// 	             } else if (imagePath.endsWith(".png")) {
// 	                 mediaType = MediaType.IMAGE_PNG;
// 	             } else if (imagePath.endsWith(".jpg") || imagePath.endsWith(".jpeg")) {
// 	                 mediaType = MediaType.IMAGE_JPEG;
// 	             }
// 	            // Load the image file as a byte array
// 	            byte[] imageBytes = imageService.loadImage(imagePath);
//
// 	            // Determine the content type based on the image file type (e.g., image/jpeg)
// 	            HttpHeaders headers = new HttpHeaders();
// 	       // Adjust as needed
// 	            headers.setContentType(mediaType);
//
// 	            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
// 	        } else {
// 	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
// 	        }
// 	    }
// 		
// 		@GetMapping("/representative1/{cid}/{bid}/{pid}")
//		public List<RepresentParty> getAllRepresent1(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("pid") String pid){
//			
//			return representPartyrepo.getAllRepresent1(cid, bid,pid);
//		}
//}


package com.cwms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.cwms.entities.Export;
import com.cwms.entities.ExportSub;
import com.cwms.entities.ExportSub_History;
import com.cwms.entities.Export_History;
import com.cwms.entities.ImportSub;
import com.cwms.entities.ImportSub_History;
import com.cwms.entities.RepresentParty;
import com.cwms.helper.FileUploadProperties;
import com.cwms.repository.ExportRepository;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.ExportSub_Historyrepo;
import com.cwms.repository.Export_HistoryRepository;
import com.cwms.repository.ImportSubHistoryRepo;
import com.cwms.repository.ImportSubRepository;
import com.cwms.repository.RepresentPartyRepository;
import com.cwms.service.EmailService;
import com.cwms.service.ImageService;
import com.cwms.service.ProcessNextIdService;

@RestController
@CrossOrigin("*")
@RequestMapping("/represent")
public class RepresentPartyController {
	@Autowired
	public RepresentPartyRepository representPartyrepo;

	
	@Autowired
	public EmailService emailService;
	
	@Autowired
	public ExportSubRepository exportsubrepo;
	
	@Autowired
	private ImportSubHistoryRepo importsubhisrepo;

	@Autowired
	public ImportSubRepository importsubrepo;

	@Autowired
	public ExportRepository exportrepo;

	@Autowired
	private Export_HistoryRepository export_HistoryRepository;
	
	@Autowired
	private ExportSub_Historyrepo exportsubhistory;
	
	@Autowired
	private ProcessNextIdService processnextId;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	public FileUploadProperties FileUploadProperties;
	
	private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
	
	@GetMapping("/byuiddata/{cid}/{bid}/{uid}")
	public List<RepresentParty> getdatabyuid(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("uid") String uid){
		return representPartyrepo.getbyuserId(cid, bid, uid);
	}
	
	@GetMapping("/byrepresentid/{cid}/{bid}/{rid}")
	public RepresentParty getrepresentbyuid(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("rid") String rid){
		return representPartyrepo.getrepresentbyid(rid, cid, bid);
	}
	
	
	 @GetMapping("/checkotp/{cid}/{bid}/{rid}/{mid}/{otp}/{expid}/{reqID}/{partyid}/{id}")
	    public ResponseEntity<String> checkOtp(
	            @PathVariable("cid") String cid,
	            @PathVariable("bid") String bid,
	            @PathVariable("rid") String rid,
	            @PathVariable("mid") String mid,
	            @PathVariable("otp") String providedOtp,
	            @PathVariable("expid") String expid,
	            @PathVariable("reqID") String reqID,
	            @PathVariable("partyid") String partyId,
	            @PathVariable("id") String id
	    		) {

	        // Execute your custom query
	        RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);

	        if (representParty != null) {
	             
	            if (representParty.getOtp().equals(providedOtp)) {
	            	this.exportsubrepo.updateStatus(cid, bid, expid, reqID,partyId,rid);	            	
	            	representParty.setOtp("");
	                this.representPartyrepo.save(representParty);
	                ExportSub exportsub = exportsubrepo.findRequestId(cid, bid, reqID);
	                ExportSub_History exphistory = new ExportSub_History();
	                exphistory.setCompanyId(exportsub.getCompanyId());
	                exphistory.setBranchId(exportsub.getBranchId());
	                exphistory.setRequestId(exportsub.getRequestId());
	                exphistory.setSerNo(exportsub.getSerNo());
	                exphistory.setNewStatus("Handed over to Party/CHA");
	                exphistory.setOldStatus("Handed over to DGDC SEEPZ");
	                exphistory.setTransport_Date(new Date());
	                exphistory.setUpdatedBy(id);
	                exportsubhistory.save(exphistory);
	                return ResponseEntity.ok("OTP verification successful!");
	            } else {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
	        }
	    }
	 
	 
	 @GetMapping("/checkCHAotp/{cid}/{bid}/{rid}/{mid}/{otp}/{expid}/{reqID}/{partyid}/{id}")
	    public ResponseEntity<String> checkCHAOtp(
	            @PathVariable("cid") String cid,
	            @PathVariable("bid") String bid,
	            @PathVariable("rid") String rid,
	            @PathVariable("mid") String mid,
	            @PathVariable("otp") String providedOtp,
	            @PathVariable("expid") String expid,
	            @PathVariable("reqID") String reqID,
	            @PathVariable("partyid") String partyId,
	            @PathVariable("id") String id
	    		) {

	        // Execute your custom query
	        RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);

	        if (representParty != null) {
	             
	            if (representParty.getOtp().equals(providedOtp)) {
	            	this.exportsubrepo.updateCHAStatus(cid, bid, expid, reqID,partyId,rid);
	            	representParty.setOtp("");
	                this.representPartyrepo.save(representParty);
	                ExportSub exportsub = exportsubrepo.findRequestId(cid, bid, reqID);
	                ExportSub_History exphistory = new ExportSub_History();
	                exphistory.setCompanyId(exportsub.getCompanyId());
	                exphistory.setBranchId(exportsub.getBranchId());
	                exphistory.setRequestId(exportsub.getRequestId());
	                exphistory.setSerNo(exportsub.getSerNo());
	                exphistory.setNewStatus("Handed over to Party/CHA");
	                exphistory.setOldStatus("Handed over to DGDC SEEPZ");
	                exphistory.setTransport_Date(new Date());
	                exphistory.setUpdatedBy(id);
	                exportsubhistory.save(exphistory);
	                return ResponseEntity.ok("OTP verification successful!");
	            } else {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
	        }
	    }
	 
	 
	 @GetMapping("/checkimpotp/{cid}/{bid}/{rid}/{mid}/{otp}/{expid}/{reqID}/{partyid}/{id}")
	    public ResponseEntity<String> checkIMPOtp(
	            @PathVariable("cid") String cid,
	            @PathVariable("bid") String bid,
	            @PathVariable("rid") String rid,
	            @PathVariable("mid") String mid,
	            @PathVariable("otp") String providedOtp,
	            @PathVariable("expid") String expid,
	            @PathVariable("reqID") String reqID,
	            @PathVariable("id")String id,
	            @PathVariable("partyid") String partyId
	    		) {

	        // Execute your custom query
	        RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);

	        if (representParty != null) {
	             
	            if (representParty.getOtp().equals(providedOtp)) {
	            	this.importsubrepo.updateStatus(cid, bid, expid, reqID,partyId,rid);
	            	representParty.setOtp("");
	                this.representPartyrepo.save(representParty);
	                ImportSub impsub = importsubrepo.findImportSub(cid, bid, expid,reqID);
	                ImportSub_History importsubhistory = new ImportSub_History();
	       		 importsubhistory.setCompanyId(impsub.getCompanyId());
	       		 importsubhistory.setBranchId(impsub.getBranchId());
	       		 importsubhistory.setNewStatus("Handed over to Party/CHA");
	       		 importsubhistory.setOldStatus("Handed over to DGDC SEEPZ");
	       		 importsubhistory.setRequestId(impsub.getRequestId());
	       		 importsubhistory.setSirNo(impsub.getSirNo());
	       		 importsubhistory.setTransport_Date(new Date());
	       		 importsubhistory.setUpdatedBy(id);
	       		 importsubhisrepo.save(importsubhistory);
	                return ResponseEntity.ok("OTP verification successful!");
	            } else {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
	        }
	    }
	 
	 
	 @GetMapping("/checkimpCHAotp/{cid}/{bid}/{rid}/{mid}/{otp}/{expid}/{reqID}/{partyid}/{id}")
	    public ResponseEntity<String> checkIMPCHAOtp(
	            @PathVariable("cid") String cid,
	            @PathVariable("bid") String bid,
	            @PathVariable("rid") String rid,
	            @PathVariable("mid") String mid,
	            @PathVariable("otp") String providedOtp,
	            @PathVariable("expid") String expid,
	            @PathVariable("reqID") String reqID,
	            @PluginAttribute("id") String id,
	            @PathVariable("partyid") String partyId
	    		) {

	        // Execute your custom query
	        RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);

	        if (representParty != null) {
	             
	            if (representParty.getOtp().equals(providedOtp)) {
	            	this.importsubrepo.updateCHAStatus(cid, bid, expid, reqID,partyId,rid);
	            	representParty.setOtp("");
	                this.representPartyrepo.save(representParty);
	                ImportSub impsub = importsubrepo.findImportSub(cid, bid, expid,reqID);
	                ImportSub_History importsubhistory = new ImportSub_History();
	       		 importsubhistory.setCompanyId(impsub.getCompanyId());
	       		 importsubhistory.setBranchId(impsub.getBranchId());
	       		 importsubhistory.setNewStatus("Handed over to Party/CHA");
	       		 importsubhistory.setOldStatus("Handed over to DGDC SEEPZ");
	       		 importsubhistory.setRequestId(impsub.getRequestId());
	       		 importsubhistory.setSirNo(impsub.getSirNo());
	       		 importsubhistory.setTransport_Date(new Date());
	       		 importsubhistory.setUpdatedBy(id);
	       		 importsubhisrepo.save(importsubhistory);
	                return ResponseEntity.ok("OTP verification successful!");
	            } else {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
	        }
	    }

	 @GetMapping("/checkexpcartotp/{cid}/{bid}/{rid}/{mid}/{otp}/{expid}/{reqID}/{partyid}/{id}/{tp}")
		public ResponseEntity<String> checkEXPCartingOtp(@PathVariable("cid") String cid, @PathVariable("bid") String bid,
				@PathVariable("rid") String rid, @PathVariable("mid") String mid, @PathVariable("otp") String providedOtp,
				@PathVariable("expid") String expid, @PathVariable("reqID") String reqID,
				@PathVariable("id") String id,
				@PathVariable("partyid") String partyId,@PathVariable("tp") String tpdata) {

			// Execute your custom query
		 String replacedString = tpdata.replace("@", "/");
			RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);

			if (representParty != null) {

				if (representParty.getOtp().equals(providedOtp)) {
				//	this.exportrepo.updateCartingRecord(partyId, rid, cid, bid, reqID, expid);
					Export export = exportrepo.findBySBNoandSbreqid(cid, bid, reqID, expid);
					export.setDgdcStatus("Handed over to Carting Agent");
					export.setCartingAgent(partyId);
					export.setPartyRepresentativeId(rid);
					if ("N".equals(replacedString)) {
					    export.setTpNo(processnextId.getNextTPNo());
					}

					else {
						export.setTpNo(replacedString);
					}
					export.setTpDate(new Date());
					export.setPctmNo(processnextId.getNextPctmNo());
					export.setTpDate(new Date());
					exportrepo.save(export);
				
					Export_History export_History = new Export_History();
					export_History.setCompanyId(export.getCompanyId());
					export_History.setBranchId(export.getBranchId());
					export_History.setNewStatus("Handed over to Carting Agent");
					export_History.setOldStatus("Handed over to DGDC SEEPZ");
					export_History.setSbNo(export.getSbNo());
					export_History.setSbRequestId(export.getSbRequestId());
					export_History.setserNo(export.getSerNo());
					export_History.setTransport_Date(new Date());
					export_History.setUpdatedBy(id);
					export_History.SetHistoryDate();
					export_HistoryRepository.save(export_History);
					representParty.setOtp("");
	                this.representPartyrepo.save(representParty);
					return ResponseEntity.ok("OTP verification successful!");
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
				}
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
			}

		}

	 @PostMapping("/checkhandovercartotp/{cid}/{bid}/{rid}/{mid}/{otp}/{partyid}/{id}/{tp}")
		public ResponseEntity<String> handoverCartingOtp(@PathVariable("cid") String cid, 
		                                                @PathVariable("bid") String bid,
		                                                @PathVariable("rid") String rid, 
		                                                @PathVariable("mid") String mid, 
		                                                @PathVariable("partyid") String partyid,
		                                                @PathVariable("otp") String providedOtp, 
		                                                @PathVariable("id") String id,
		                                                @PathVariable("tp") String tpdata,
		                                                @RequestBody List<Export> exportmain) {

		    // Execute your custom query
		    String replacedString = tpdata.replace("@", "/");
		    RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);

		    if (representParty != null) {
		        if (representParty.getOtp().equals(providedOtp)) {
		            List<Export_History> exportHistoryList = new ArrayList<>();
		            Map<String, String> airlineToPCTMMap = new HashMap<>();
		            String tpno = null;

		            if ("N".equals(replacedString)) {
		                tpno = processnextId.getNextTPNo();
		            } else {
		                tpno = replacedString;
		            }
		            
		            Date date = new Date();

		            for (Export exp : exportmain) {
		                String airlineName = exp.getAirlineCode();
		                
		                // Check if airlineName is not null or empty
		                if (airlineName != null && !airlineName.isEmpty()) {
		                    String pctmno = airlineToPCTMMap.get(airlineName);

		                    if (pctmno == null) {
		                        // If no PCTM number is associated with this airline, generate a new one
		                        pctmno = processnextId.getNextPctmNo();
		                        airlineToPCTMMap.put(airlineName, pctmno);
		                    }
		                    
		                    exp.setCartingAgent(partyid);
		                    exp.setPartyRepresentativeId(rid);
		                    exp.setPctmNo(pctmno);
		                    exp.setPctmDate(new Date());
		                    exp.setDgdcStatus("Handed over to Carting Agent");
		                    exp.setTpNo(tpno);
		                    exp.setTpDate(date);

		                    exportrepo.save(exp);
		                    representParty.setOtp("");
		                    this.representPartyrepo.save(representParty);

		                    Export_History export_History = new Export_History(exp.getCompanyId(), exp.getBranchId(),
		                            exp.getSbNo(), exp.getSbRequestId(), exp.getSerNo(), id, "Handed over to Carting Agent",
		                            exp.getDgdcStatus(), null);
		                    export_History.SetHistoryDate();
		                    exportHistoryList.add(export_History);
		                }
		            }

		            this.export_HistoryRepository.saveAll(exportHistoryList); // Save all history records

		            return ResponseEntity.ok("OTP verification successful!");
		        } else {
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
		        }
		    } else {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
		    }
		}
		
		
//		@PostMapping("/checkhandovercartotp/{cid}/{bid}/{rid}/{mid}/{otp}/{partyid}/{id}")
//		public ResponseEntity<String> handoverCartingOtp(@PathVariable("cid") String cid, 
//		                                                @PathVariable("bid") String bid,
//		                                                @PathVariable("rid") String rid, 
//		                                                @PathVariable("mid") String mid, 
//		                                                @PathVariable("partyid") String partyid, 
//		                                                @PathVariable("otp") String providedOtp,
//		                                                @PathVariable("id") String id,
//		                                                @RequestBody List<Export> exportmain) {
//
//		    // Execute your custom query
//		    RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
//
//		    if (representParty != null) {
//		        if (representParty.getOtp().equals(providedOtp)) {
//		            List<Export_History> exportHistoryList = new ArrayList<>();
//		            Map<String, String> airlineToPCTMMap = new HashMap<>();
//		            String tpno = processnextId.getNextTPNo();
//	                Date date = new Date();
//		            
//		            for (Export exp : exportmain) {
//		            	String airlineName = exp.getAirlineCode();
//		            	  // Check if airlineName is not null or empty
//		                if (airlineName != null && !airlineName.isEmpty()) {
//		                    String pctmno = airlineToPCTMMap.get(airlineName);
//		                    
//		                    if (pctmno == null) {
//		                        // If no PCTM number is associated with this airline, generate a new one
//		                    	pctmno = processnextId.getNextPctmNo();
//		                        airlineToPCTMMap.put(airlineName, pctmno);
//		                    }
//		                exp.setCartingAgent(partyid);
//		                exp.setPartyRepresentativeId(rid);
//		                exp.setPctmNo(pctmno);
//		                exp.setPctmDate(new Date());
//		                exp.setDgdcStatus("Handed over to Carting Agent");
//		                exp.setTpNo(tpno);
//		                exp.setTpDate(date);
//		                
//		                exportrepo.save(exp);
//		                representParty.setOtp("");
//		                this.representPartyrepo.save(representParty);
//
//		            
//		                Export_History export_History = new Export_History();
//						export_History.setCompanyId(exp.getCompanyId());
//						export_History.setBranchId(exp.getBranchId());
//						export_History.setNewStatus("Handed over to Carting Agent");
//						export_History.setOldStatus("Handed over to DGDC SEEPZ");
//						export_History.setSbNo(exp.getSbNo());
//						export_History.setSbRequestId(exp.getSbRequestId());
//						export_History.setserNo(exp.getSerNo());
//						export_History.setTransport_Date(new Date());
//						export_History.setUpdatedBy(id);
//						export_History.SetHistoryDate();
//		                exportHistoryList.add(export_History);
//		                }
//		            }
//
//		            this.export_HistoryRepository.saveAll(exportHistoryList); // Save all history records
//
//		            return ResponseEntity.ok("OTP verification successful!");
//		        } else {
//		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
//		        }
//		    } else {
//		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
//		    }
//		}
		
		
		@PostMapping("/checkreceivecartotp/{cid}/{bid}/{rid}/{mid}/{otp}/{id}")
		public ResponseEntity<String> receiveCartingOtp(@PathVariable("cid") String cid, 
		                                                @PathVariable("bid") String bid,
		                                                @PathVariable("rid") String rid, 
		                                                @PathVariable("mid") String mid, 
		                                                
		                                                @PathVariable("otp") String providedOtp,
		                                                @PathVariable("id") String id,
		                                                @RequestBody List<Export> exportmain) {

		    // Execute your custom query
		    RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);

		    if (representParty != null) {
		        if (representParty.getOtp().equals(providedOtp)) {
		            List<Export_History> exportHistoryList = new ArrayList<>();
		            

		            for (Export exp : exportmain) {
		        
		                exp.setDgdcStatus("Handed over to DGDC Cargo");
		      
		                
		                exportrepo.save(exp);

		              
		                Export_History export_History = new Export_History();
						export_History.setCompanyId(exp.getCompanyId());
						export_History.setBranchId(exp.getBranchId());
						export_History.setNewStatus("Handed over to DGDC Cargo");
						export_History.setOldStatus("Entry at DGDC Cargo GATE");
						export_History.setSbNo(exp.getSbNo());
						export_History.setSbRequestId(exp.getSbRequestId());
						export_History.setserNo(exp.getSerNo());
						export_History.setTransport_Date(new Date());
						export_History.setUpdatedBy(id);
						export_History.SetHistoryDate();
		                exportHistoryList.add(export_History);
		            }

		            this.export_HistoryRepository.saveAll(exportHistoryList); // Save all history records
		            representParty.setOtp("");
	                this.representPartyrepo.save(representParty);
		            return ResponseEntity.ok("OTP verification successful!");
		        } else {
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
		        }
		    } else {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
		    }
		}

		
		@GetMapping("/generateotp/{cid}/{bid}/{rid}/{mobile}/{nop}")
		public String generateCartingOTP(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("rid") String rid,@PathVariable("mobile") String mobile,@PathVariable("nop") String nop) {
			String otp = generateOTP();
			RepresentParty represent = representPartyrepo.checkOTP(cid, bid, rid, mobile);
			represent.setOtp(otp);
			this.representPartyrepo.save(represent);
			
			 try {
			        String apiKey = "apikey=" + URLEncoder.encode("N2E2ZjU4NmU1OTY5Njg2YjczNjI3OTMxNjg3MjQ4NjM=", "UTF-8");
			        String message = "Dear Sir/Madam, Please validate your identity in DGDC E-Custodian with OTP " + otp + " .";
			        String sender = "sender=" + URLEncoder.encode("DGDCSZ", "UTF-8");
			        String numbers = "numbers=" + URLEncoder.encode("91" + mobile, "UTF-8");

			        // Send data
			        String data = "https://api.textlocal.in/send/?" + apiKey + "&" + numbers + "&message=" + URLEncoder.encode(message, "UTF-8") + "&" + sender;
			        URL url = new URL(data);
			        URLConnection conn = url.openConnection();
			        conn.setDoOutput(true);
			        
			        if (represent.getEmail() != null && !represent.getEmail().isEmpty())
			        {
			        	executorService.schedule(() -> {
				        	 emailService.SendOtpOnEmail("custodian.seepz@gmail.com",otp,nop);
				        	}, 2, TimeUnit.SECONDS);
			        }
			       
			        
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
		
		@GetMapping("/getImage/{cid}/{bid}/{rid}")
	    public ResponseEntity<byte[]> downloadImage(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("rid") String rid) throws IOException {
	        // Retrieve the image path from the database based on imageId
	    	RepresentParty represent = representPartyrepo.getrepresentbyid(rid,cid, bid);
	        String imagePath = represent.getImagePath();

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
	    
	 // Add Representative of Party
		
// 		@PostMapping("/addRepresentative/{companyid}/{branchId}/{partyid}")
// 		public ResponseEntity<Object> addPartyRepresentative(
// 				
// 				@PathVariable("companyid") String cid, 
// 		        @PathVariable("branchId") String bid,		                                               		                                               
// 		        @PathVariable("partyid") String partyid  ,
// 		        
// 		        @RequestParam("file") MultipartFile file,
// 				@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
// 				@RequestParam("lastName") String lastName, @RequestParam("mobile") String mobile,
// 				@RequestParam("companyid") String companyid, @RequestParam("companyid") String companyid1,
// 				@RequestParam("branchId") String branchId, @RequestParam("status") String status
// 		        ) throws java.io.IOException {
// 			
// 			try {
// 		                                               
// 			      String nextId = processnextId.autoIncrementRepresentativePartyId();   
// 			
// 			      RepresentParty newparty = new RepresentParty();
// 			      
// 			      newparty.setRepresentativeId(nextId);
// 			      newparty.setCompanyId(cid);
// 			      newparty.setBranchId(bid);
// 			      newparty.setUserId(partyid);
// 			      newparty.setUserType("Party");
// 			      newparty.setFirstName(firstName);
// 			      newparty.setMiddleName(middleName);
// 			      newparty.setLastName(lastName);
// 			      newparty.setMobile(mobile);
// 			      newparty.setStatus("A");
// 			      newparty.setUserStatus(status);
// 			      System.out.print(status);
// 			      newparty.setOtp("1000");
// 			      newparty.setCreatedDate(new Date());
// 			      
// 			   // Get the original file name
// 					String originalFileName = file.getOriginalFilename();
//
// 					// Generate a unique file name to avoid duplicates
// 					String uniqueFileName = generateUniqueFileName(originalFileName);
//
// 		      newparty.setImagePath(FileUploadProperties.getPath() + uniqueFileName);
//   
// 		  	// Save the file to your local system with the unique name
// 				Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName));
// 				
//// 			      representPartyrepo.save(newparty);		  
// 			      RepresentParty savedParty = representPartyrepo.save(newparty);
// 			      return ResponseEntity.status(HttpStatus.OK).body(savedParty);
// 			}
// 			catch (IOException e) {
// 				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
// 			}
// 			      // Return the saved object with a success status
// 			      
// 		}
	    
	    
 // Add Representative of Party
		
 		@PostMapping("/addRepresentative/{companyid}/{branchId}/{partyid}")
 		public ResponseEntity<Object> addPartyRepresentative(
 				
 				@PathVariable("companyid") String cid, 
 		        @PathVariable("branchId") String bid,		                                               		                                               
 		        @PathVariable("partyid") String partyid  ,
 		        
 		        @RequestParam("file") MultipartFile file,
 		       @RequestParam("file1") MultipartFile file1,
 				@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
 				@RequestParam("lastName") String lastName, @RequestParam("mobile") String mobile,
 				@RequestParam("companyid") String companyid, @RequestParam("companyid") String companyid1,
 				@RequestParam("branchId") String branchId, @RequestParam("status") String status
 		        ) throws java.io.IOException {
 			
 			try {
 		                                               
 			      String nextId = processnextId.autoIncrementRepresentativePartyId();   
 			
 			      RepresentParty newparty = new RepresentParty();
 			      
 			      newparty.setRepresentativeId(nextId);
 			      newparty.setCompanyId(cid);
 			      newparty.setBranchId(bid);
 			      newparty.setUserId(partyid);
 			      newparty.setUserType("Party");
 			      newparty.setFirstName(firstName);
 			      newparty.setMiddleName(middleName);
 			      newparty.setLastName(lastName);
 			      newparty.setMobile(mobile);
 			      newparty.setStatus("A");
 			      newparty.setUserStatus(status);
 			      System.out.print(status);
 			      newparty.setOtp("1000");
 			      newparty.setCreatedDate(new Date());
 			      
 			     newparty.setEmail("demoemail@gmail.com");
 			      
 			   // Get the original file name
 					String originalFileName = file.getOriginalFilename();
 					// Generate a unique file name to avoid duplicates
 					String uniqueFileName = generateUniqueFileName(originalFileName);
 		      newparty.setImagePath(FileUploadProperties.getPath() + uniqueFileName);

 		  	// Save the file to your local system with the unique name
 				Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName));
 				
 				
 				
 				String originalFileName1 = file1.getOriginalFilename();
 				String uniqueFileName1= generateUniqueFileName(originalFileName1);
 				 newparty.setSignImagePath(FileUploadProperties.getPath() + uniqueFileName1);
 				 
 				Files.copy(file1.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName1));
// 			      representPartyrepo.save(newparty);		  
 			      RepresentParty savedParty = representPartyrepo.save(newparty);
 			      return ResponseEntity.status(HttpStatus.OK).body(savedParty);
 			}
 			catch (IOException e) {
 				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
 			}
 			      // Return the saved object with a success status
 			      
 		}
	    
	    
	    
 		
 		
 		
// 		// update with file 
// 		@PostMapping("/updateRepresentative/{companyid}/{branchId}/{partyid}/{representativeID}")
// 		public ResponseEntity<Object> updatePartyRepresentative(
// 				
// 				@PathVariable("companyid") String cid, 
// 		        @PathVariable("branchId") String bid,		                                               		                                               
// 		        @PathVariable("partyid") String partyid  ,
// 		        @PathVariable("representativeID") String rpid  ,	
// 		        @RequestParam("file") MultipartFile file,
// 				@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
// 				@RequestParam("lastName") String lastName, @RequestParam("mobile") String mobile,@RequestParam("status") String status
// 		        ) throws java.io.IOException {
// 			
// 			try {
// 		                                               
// 				   RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(cid, bid, partyid, rpid);
//
// 				   representPartyObject.setFirstName(firstName);
// 				   representPartyObject.setMiddleName(middleName);
// 				   representPartyObject.setLastName(lastName);
// 				   representPartyObject.setMobile(mobile);
// 				   representPartyObject.setStatus("A");
// 				   representPartyObject.setUserStatus(status);
// 				   representPartyObject.setOtp("1000");
// 				   representPartyObject.setEditedDate(new Date());
//
// 				   if(file!= null)
// 				   {      // Get the original file name
// 						String originalFileName = file.getOriginalFilename();
//
// 						// Generate a unique file name to avoid duplicates
// 						String uniqueFileName = generateUniqueFileName(originalFileName);
//
// 						representPartyObject.setImagePath(FileUploadProperties.getPath() + uniqueFileName);
// 	  
// 			  	// Save the file to your local system with the unique name
// 					Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName));                  }
// 			  
// 				
// 	  
// 			      RepresentParty savedParty = representPartyrepo.save(representPartyObject);
// 			      return ResponseEntity.status(HttpStatus.OK).body(savedParty);
// 			}
// 			catch (IOException e) {
// 				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
// 			}
// 			      // Return the saved object with a success status
// 			      
// 		}	
 		
 		
 	// update with file 
 	// update with file 
 			@PostMapping("/updateRepresentative/{companyid}/{branchId}/{partyid}/{representativeID}")
 			public ResponseEntity<Object> updatePartyRepresentative(
 					
 					@PathVariable("companyid") String cid, 
 			        @PathVariable("branchId") String bid,		                                               		                                               
 			        @PathVariable("partyid") String partyid  ,
 			        @PathVariable("representativeID") String rpid  ,	
 			        @RequestParam("file") MultipartFile file,
 			        @RequestParam(value = "file1", required = false) MultipartFile file1,
 					@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
 					@RequestParam("lastName") String lastName, @RequestParam("mobile") String mobile,@RequestParam("status") String status
 			        ) throws java.io.IOException {
 				
 				try {
 			                                               
 					   RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(cid, bid, partyid, rpid);

 					   if (representPartyObject != null) {
 						    String nsdlStatusDocsPath = representPartyObject.getImagePath();
 						    Path filePath = Paths.get(nsdlStatusDocsPath);

 						    // Check if the file exists
 						    if (Files.exists(filePath)) {
 						        try {
 						            // Delete the file
 						            Files.delete(filePath);
 						            System.out.println("File deleted successfully.");

 						            // Additional code if needed after deleting the file

 						        } catch (IOException e) {
 						            // Handle the exception appropriately
 						            System.err.println("Failed to delete the file: " + e.getMessage());
 						        }
 						    } else {
 						        System.out.println("File does not exist.");
 						    }
 						}

 					   				   
 					   representPartyObject.setFirstName(firstName);
 					   representPartyObject.setMiddleName(middleName);
 					   representPartyObject.setLastName(lastName);
 					   representPartyObject.setMobile(mobile);
 					   representPartyObject.setStatus("A");
 					   representPartyObject.setUserStatus(status);
 					   representPartyObject.setOtp("1000");
 					   representPartyObject.setEditedDate(new Date());

 					   if(file!= null)
 					   {      // Get the original file name
 							String originalFileName = file.getOriginalFilename();
 							// Generate a unique file name to avoid duplicates
 							String uniqueFileName = generateUniqueFileName(originalFileName);
 							representPartyObject.setImagePath(FileUploadProperties.getPath() + uniqueFileName);
 				  	// Save the file to your local system with the unique name
 						Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName)); }
 					   
 					   
 					   
 					   if(file1!= null)
 					   {      
 							String originalFileName1 = file1.getOriginalFilename();					
 							String uniqueFileName1 = generateUniqueFileName(originalFileName1);
 							representPartyObject.setImagePath(FileUploadProperties.getPath() + uniqueFileName1);
 						Files.copy(file1.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName1)); }
 					   
 	  
 				      RepresentParty savedParty = representPartyrepo.save(representPartyObject);
 				      return ResponseEntity.status(HttpStatus.OK).body(savedParty);
 				}
 				catch (IOException e) {
 					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
 				}
 				      // Return the saved object with a success status
 				      
 			}	
 		
 		
 		
 		
 			@PostMapping("/updateRepresentativewithoutFile/{companyid}/{branchId}/{partyid}/{representativeID}")
 	 		public ResponseEntity<Object> updateRepresentativewithoutFile(
 	 				
 	 				@PathVariable("companyid") String cid, 
 	 		        @PathVariable("branchId") String bid,		                                               		                                               
 	 		        @PathVariable("partyid") String partyid  ,
 	 		        @PathVariable("representativeID") String rpid  ,	
 	 		       @RequestParam(value = "file1", required = false) MultipartFile file1,
 	 				@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
 	 				@RequestParam("lastName") String lastName, @RequestParam("mobile") String mobile,@RequestParam("status") String status
 	 		        ) throws java.io.IOException {
 	 			
 	 			RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(cid, bid, partyid, rpid);

 				   representPartyObject.setFirstName(firstName);
 				   representPartyObject.setMiddleName(middleName);
 				   representPartyObject.setLastName(lastName);
 				   representPartyObject.setMobile(mobile);
 				   representPartyObject.setStatus("A");
 				   representPartyObject.setUserStatus(status);
 				   representPartyObject.setOtp("1000");
 				   representPartyObject.setEditedDate(new Date());
 				   
 				   if(file1!= null)
 				   {      
 						String originalFileName1 = file1.getOriginalFilename();					
 						String uniqueFileName1 = generateUniqueFileName(originalFileName1);

 						representPartyObject.setSignImagePath(FileUploadProperties.getPath() + uniqueFileName1);
 					Files.copy(file1.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName1)); }
 				   

 				   
 				  RepresentParty savedParty = representPartyrepo.save(representPartyObject);
 				  return ResponseEntity.status(HttpStatus.OK).body(savedParty);
 	 			      
 	 		}	
 	 		

 		


 		// Helper method to generate a unique file name
 		private String generateUniqueFileName(String originalFileName) {
 			String uniqueFileName = originalFileName;
 			int suffix = 1;

 			// Check if the file with the same name already exists
 			while (Files.exists(Paths.get(FileUploadProperties.getPath() + uniqueFileName))) {
 				int dotIndex = originalFileName.lastIndexOf('.');
 				String nameWithoutExtension = dotIndex != -1 ? originalFileName.substring(0, dotIndex) : originalFileName;
 				String fileExtension = dotIndex != -1 ? originalFileName.substring(dotIndex) : "";
 				uniqueFileName = nameWithoutExtension + "_" + suffix + fileExtension;
 				suffix++;
 			}

 			return uniqueFileName;
 		}
 		
 		
 		// get All Representative of Party		
 		
 		@GetMapping("/getAllRepresentParty/{cid}/{bid}/{pid}")
 		public List<RepresentParty> getAllRepresentParty(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("pid") String rid){

 			return representPartyrepo.findByCompanyIdAndBranchIdAndUserIdAndStatusNot(cid,bid,rid ,"D");
 		}
 		
// 		@GetMapping("/getAllRepresentParty/{cid}/{bid}/{pid}")
// 		public ResponseEntity<?> getAllRepresentParty(@PathVariable("cid") String cid,
// 		                                              @PathVariable("bid") String bid,
// 		                                              @PathVariable("pid") String Pid) throws java.io.IOException {
// 		    List<RepresentParty> representPartyList = representPartyrepo.getByCompanyIdAndBranchIdAndUserId(cid, bid, Pid);
// 		    if (representPartyList != null && !representPartyList.isEmpty()) {
// 		        // Assuming you want to get the image data for the first RepresentParty in the list
// 		        RepresentParty representParty = representPartyList.get(0);
// 		        String nsdlStatusDocsPath = representParty.getImagePath();
// 		        try {
// 		            String fileExtension = getFileExtension(nsdlStatusDocsPath);
 //
// 		            if (isImageFile(fileExtension)) {
// 		                byte[] imageBytes = Files.readAllBytes(Paths.get(nsdlStatusDocsPath));
// 		                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
// 		                String dataURL = "data:image/jpeg;base64," + base64Image;
 //
// 		                // Create a DTO to hold both the list and image data
// 		                Map<String, Object> responseData = new HashMap<>();
// 		                responseData.put("representPartyList", representPartyList);
// 		                responseData.put("imageData", dataURL);
 //
// 		                HttpHeaders headers = new HttpHeaders();
// 		                headers.setContentType(MediaType.APPLICATION_JSON); // Set the content type to application/json
 //
// 		                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
// 		            }
// 		        } catch (IOException e) {
// 		            e.printStackTrace();
// 		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
// 		        }
// 		    }
 //
// 		    return ResponseEntity.notFound().build();
// 		}


 		
 		
// 		 Supplementary code for getting image
 		private String getFileExtension(String filePath) {
 			int dotIndex = filePath.lastIndexOf('.');
 			if (dotIndex >= 0 && dotIndex < filePath.length() - 1) {
 				return filePath.substring(dotIndex + 1).toLowerCase();
 			}
 			return "";
 		}
 		
 		private boolean isImageFile(String fileExtension) {
 			return fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png")
 					|| fileExtension.equals("gif");
 		}
 		
 		
// 		code to get All images by using url		
 		@GetMapping("/getAllRepresentPartyImg/{rpid}")
 		public ResponseEntity<?> getImageOrPdf(@PathVariable("rpid") String rpid) throws IOException {

 			RepresentParty RepresentPartyObject = representPartyrepo.getByRepresentativeId(rpid);

 			if (RepresentPartyObject != null) {
 				String nsdlStatusDocsPath = RepresentPartyObject.getImagePath();
 				Path filePath = Paths.get(nsdlStatusDocsPath);

 				// Check if the file exists
 				if (Files.exists(filePath)) {
 					try {
 						String fileExtension = getFileExtension(nsdlStatusDocsPath);

 						if (isImageFile(fileExtension)) {
 							// If it's an image file, return a data URL
 							byte[] imageBytes = Files.readAllBytes(filePath);
 							String base64Image = Base64.getEncoder().encodeToString(imageBytes);
 							String dataURL = "data:image/jpeg;base64," + base64Image;

 							HttpHeaders headers = new HttpHeaders();
 							headers.setContentType(MediaType.TEXT_PLAIN); // Set the content type to text/plain

 							return new ResponseEntity<>(dataURL, headers, HttpStatus.OK);
 						} 
 					} catch (java.io.IOException e) {
 						// Handle the IOException appropriately (e.g., log it)
 						e.printStackTrace();
 						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
 					}
 				}
 			}

 			return ResponseEntity.notFound().build();
 		}
 		

// 		code for delete Representative Data set status D
 		
 		@DeleteMapping("/deleteRepresentative/{cid}/{bid}/{uid}/{rpid}")
 		public ResponseEntity<RepresentParty> deleteSingleRepresentParty(
 		        @PathVariable("cid") String cid,
 		        @PathVariable("bid") String bid,
 		        @PathVariable("uid") String uid,
 		        @PathVariable("rpid") String rpid) {

 		    RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(cid, bid, uid, rpid);

 		    if (representPartyObject != null) {
 		        representPartyObject.setStatus("D");

 		        RepresentParty savedParty = representPartyrepo.save(representPartyObject);

 		        return ResponseEntity.status(HttpStatus.OK).body(savedParty);
 		    } else {
 		        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
 		    }
 		}

 		
// 		get single image from database
 		@GetMapping("/getImage1/{compid}/{branchId}/{pid}/{rpid}")
 		public ResponseEntity<?> getImageOrPdf(@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
 				@PathVariable("pid") String pid, @PathVariable("rpid") String rpid
 				) throws IOException {


 			  RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(compid, branchId, pid, rpid);
 			
 			  if (representPartyObject != null) {
 				String nsdlStatusDocsPath = representPartyObject.getImagePath();
 				Path filePath = Paths.get(nsdlStatusDocsPath);

 				// Check if the file exists
 				if (Files.exists(filePath)) {
 					try {
 						String fileExtension = getFileExtension(nsdlStatusDocsPath);

 						if (isImageFile(fileExtension)) {
 							// If it's an image file, return a data URL
 							byte[] imageBytes = Files.readAllBytes(filePath);
 							String base64Image = Base64.getEncoder().encodeToString(imageBytes);
 							String dataURL = "data:image/jpeg;base64," + base64Image;

 							HttpHeaders headers = new HttpHeaders();
 							headers.setContentType(MediaType.TEXT_PLAIN); 

 							return new ResponseEntity<>(dataURL, headers, HttpStatus.OK);
 						}
 					} catch (java.io.IOException e) {
 						// Handle the IOException appropriately (e.g., log it)
 						e.printStackTrace();
 						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
 					}
 				}
 			}

 			return ResponseEntity.notFound().build();
 		}	
 		
 		
 		
 		
 		
// 		get single image from database
 		@GetMapping("/getImage2/{compid}/{branchId}/{pid}/{rpid}")
 		public ResponseEntity<?> getImageOrPdf2(@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
 				@PathVariable("pid") String pid, @PathVariable("rpid") String rpid
 				) throws IOException {


 			  RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(compid, branchId, pid, rpid);
 			
 			  if (representPartyObject != null) {
 				String nsdlStatusDocsPath = representPartyObject.getSignImagePath();
 				Path filePath = Paths.get(nsdlStatusDocsPath);

 				// Check if the file exists
 				if (Files.exists(filePath)) {
 					try {
 						String fileExtension = getFileExtension(nsdlStatusDocsPath);

 						if (isImageFile(fileExtension)) {
 							// If it's an image file, return a data URL
 							byte[] imageBytes = Files.readAllBytes(filePath);
 							String base64Image = Base64.getEncoder().encodeToString(imageBytes);
 							String dataURL = "data:image/jpeg;base64," + base64Image;

 							HttpHeaders headers = new HttpHeaders();
 							headers.setContentType(MediaType.TEXT_PLAIN); 

 							return new ResponseEntity<>(dataURL, headers, HttpStatus.OK);
 						}
 					} catch (java.io.IOException e) {
 						// Handle the IOException appropriately (e.g., log it)
 						e.printStackTrace();
 						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
 					}
 				}
 			}

 			return ResponseEntity.notFound().build();
 		}	
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
// 		get single image from database
 		@GetMapping("/getImage/{compid}/{branchId}/{pid}/{rpid}")
 		public ResponseEntity<?> getImage(@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
 				@PathVariable("pid") String pid, @PathVariable("rpid") String rpid
 				) throws IOException, java.io.IOException {


 			  RepresentParty representPartyObject = representPartyrepo.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(compid, branchId, pid, rpid);

 			  String  imagePath = representPartyObject.getImagePath();
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
 		
 		@GetMapping("/representative1/{cid}/{bid}/{pid}")
		public List<RepresentParty> getAllRepresent1(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("pid") String pid){
			
 			System.out.println("CompanyId "+cid +"Branch  "+ bid + "PartyId " +pid);
 			
 			
			return representPartyrepo.getAllRepresent1(cid, bid,pid);
		}
 		
 		
 		// Subcontract export

 		@PostMapping("/checkhandoverexpsubcartotp/{cid}/{bid}/{rid}/{mid}/{otp}/{partyid}/{id}")
 		public ResponseEntity<String> handoverSubcontractexportCartingOtp(@PathVariable("cid") String cid,
 				@PathVariable("bid") String bid, @PathVariable("rid") String rid, @PathVariable("mid") String mid,
 				@PathVariable("partyid") String partyid, @PathVariable("otp") String providedOtp,
 				@PathVariable("id") String id, @RequestBody List<ExportSub> exportsub) {

 			// Execute your custom query
 			RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
 			if (representParty != null) {
 				if (representParty.getOtp().equals(providedOtp)) {
 					List<ExportSub_History> exportHistoryList = new ArrayList<>();
 					for (ExportSub exp : exportsub) {
 						exp.setDgdcStatus("Handed over to Party/CHA");
 						exp.setHandover_Party_CHA("P");
 						exp.setHandover_Party_Name(partyid);
 						exp.setHandover_Represntative_id(rid);
 						exportsubrepo.save(exp);

 						ExportSub_History exphistory = new ExportSub_History();
 						exphistory.setCompanyId(exp.getCompanyId());
 						exphistory.setBranchId(exp.getBranchId());
 						exphistory.setRequestId(exp.getRequestId());
 						exphistory.setSerNo(exp.getSerNo());
 						exphistory.setNewStatus("Handed over to Party/CHA");
 						exphistory.setOldStatus("Handed over to DGDC SEEPZ");
 						exphistory.setTransport_Date(new Date());
 						exphistory.setUpdatedBy(id);
 						exportHistoryList.add(exphistory);
 					}
 					this.exportsubhistory.saveAll(exportHistoryList); // Save all history records
 					representParty.setOtp("");
 					this.representPartyrepo.save(representParty);
 					return ResponseEntity.ok("OTP verification successful!");
 				}
 				else {
 					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
 				}
 			} else {
 				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
 			}
 			
 		}
 		
 		
 		@PostMapping("/checkhandoverexpsubcartotp1/{cid}/{bid}/{rid}/{mid}/{otp}/{partyid}/{id}")
 		public ResponseEntity<String> handoverSubcontractexportCartingOtp1(@PathVariable("cid") String cid,
 				@PathVariable("bid") String bid, @PathVariable("rid") String rid, @PathVariable("mid") String mid,
 				@PathVariable("partyid") String partyid, @PathVariable("otp") String providedOtp,
 				@PathVariable("id") String id, @RequestBody List<ExportSub> exportsub) {

 			// Execute your custom query
 			RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
 			if (representParty != null) {
 				if (representParty.getOtp().equals(providedOtp)) {
 					List<ExportSub_History> exportHistoryList = new ArrayList<>();
 					for (ExportSub exp : exportsub) {
 						exp.setDgdcStatus("Handed over to Party/CHA");
 						exp.setHandover_Party_CHA("C");
 						exp.setHandover_Party_Name(partyid);
 						exp.setHandover_Represntative_id(rid);
 						exportsubrepo.save(exp);

 						ExportSub_History exphistory = new ExportSub_History();
 						exphistory.setCompanyId(exp.getCompanyId());
 						exphistory.setBranchId(exp.getBranchId());
 						exphistory.setRequestId(exp.getRequestId());
 						exphistory.setSerNo(exp.getSerNo());
 						exphistory.setNewStatus("Handed over to Party/CHA");
 						exphistory.setOldStatus("Handed over to DGDC SEEPZ");
 						exphistory.setTransport_Date(new Date());
 						exphistory.setUpdatedBy(id);
 						exportHistoryList.add(exphistory);
 					}
 					this.exportsubhistory.saveAll(exportHistoryList); // Save all history records
 					representParty.setOtp("");
 					this.representPartyrepo.save(representParty);
 					return ResponseEntity.ok("OTP verification successful!");
 				}
 				else {
 					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
 				}
 			} else {
 				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
 			}
 			
 		}

 		
 		
 		// Subcontract import job work

 			@PostMapping("/checkhandoverimpjobsubcartotp/{cid}/{bid}/{rid}/{mid}/{otp}/{partyid}/{id}")
 			public ResponseEntity<String> handoverSubcontractimportjobCartingOtp(@PathVariable("cid") String cid,
 					@PathVariable("bid") String bid, @PathVariable("rid") String rid, @PathVariable("mid") String mid,
 					@PathVariable("partyid") String partyid, @PathVariable("otp") String providedOtp,
 					@PathVariable("id") String id, @RequestBody List<ImportSub> exportsub) {

 				// Execute your custom query
 				RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
 				if (representParty != null) {
 					if (representParty.getOtp().equals(providedOtp)) {
 						List<ImportSub_History> exportHistoryList = new ArrayList<>();
 						for (ImportSub exp : exportsub) {
 							exp.setDgdcStatus("Handed over to Party/CHA");
 							exp.setHandover_Party_CHA("P");
 							exp.setHandover_Party_Name(partyid);
 							exp.setHandover_Represntative_id(rid);
 							importsubrepo.save(exp);

 							ImportSub_History exphistory = new ImportSub_History();
 							exphistory.setCompanyId(exp.getCompanyId());
 							exphistory.setBranchId(exp.getBranchId());
 							exphistory.setRequestId(exp.getRequestId());
 							exphistory.setSirNo(exp.getSirNo());
 							exphistory.setNewStatus("Handed over to Party/CHA");
 							exphistory.setOldStatus("Handed over to DGDC SEEPZ");
 							exphistory.setTransport_Date(new Date());
 							exphistory.setUpdatedBy(id);
 							exportHistoryList.add(exphistory);
 						}
 						this.importsubhisrepo.saveAll(exportHistoryList); // Save all history records
 						representParty.setOtp("");
 						this.representPartyrepo.save(representParty);
 						return ResponseEntity.ok("OTP verification successful!");
 					}
 					else {
 						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
 					}
 				} else {
 					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
 				}
 				
 			}
 			
 			
 			@PostMapping("/checkhandoverimpjobsubcartotp1/{cid}/{bid}/{rid}/{mid}/{otp}/{partyid}/{id}")
 			public ResponseEntity<String> handoverSubcontractimportjobCartingOtp1(@PathVariable("cid") String cid,
 					@PathVariable("bid") String bid, @PathVariable("rid") String rid, @PathVariable("mid") String mid,
 					@PathVariable("partyid") String partyid, @PathVariable("otp") String providedOtp,
 					@PathVariable("id") String id, @RequestBody List<ImportSub> exportsub) {

 				// Execute your custom query
 				RepresentParty representParty = representPartyrepo.checkOTP(cid, bid, rid, mid);
 				if (representParty != null) {
 					if (representParty.getOtp().equals(providedOtp)) {
 						List<ImportSub_History> exportHistoryList = new ArrayList<>();
 						for (ImportSub exp : exportsub) {
 							exp.setDgdcStatus("Handed over to Party/CHA");
 							exp.setHandover_Party_CHA("C");
 							exp.setHandover_Party_Name(partyid);
 							exp.setHandover_Represntative_id(rid);
 							importsubrepo.save(exp);

 							ImportSub_History exphistory = new ImportSub_History();
 							exphistory.setCompanyId(exp.getCompanyId());
 							exphistory.setBranchId(exp.getBranchId());
 							exphistory.setRequestId(exp.getRequestId());
 							exphistory.setSirNo(exp.getSirNo());
 							exphistory.setNewStatus("Handed over to Party/CHA");
 							exphistory.setOldStatus("Handed over to DGDC SEEPZ");
 							exphistory.setTransport_Date(new Date());
 							exphistory.setUpdatedBy(id);
 							exportHistoryList.add(exphistory);
 						}
 						this.importsubhisrepo.saveAll(exportHistoryList); // Save all history records
 						representParty.setOtp("");
 						this.representPartyrepo.save(representParty);
 						return ResponseEntity.ok("OTP verification successful!");
 					}
 					else {
 						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
 					}
 				} else {
 					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representative not found!");
 				}
 				
 			}
}


