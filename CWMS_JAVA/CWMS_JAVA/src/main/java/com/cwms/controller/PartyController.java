package com.cwms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.emitter.Emitable;

import com.cwms.repository.*;
import com.cwms.service.*;
import com.cwms.entities.*;

import jakarta.annotation.PreDestroy;
import jakarta.transaction.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@CrossOrigin("*")
@RestController
@RequestMapping("/parties")

public class PartyController {

	@Autowired
	public EmailService emailService;

	@Autowired
	public PartyRepository partyRepository;

	
	@Autowired
	public ExternalParty_Repositary externalPartyRepository;
	
	@Autowired
	public UserCreationRepository userCreationRepository;
	
	
	@Autowired
	private UserRepository userrepo;
		
	
	@Autowired
	UserCreationServiceImpliment creationServiceImpliment;
	
	
	@Autowired
	public ExternalParty_Service externalpartyService;

	@Autowired
	public PartyService partyService;

	@Autowired
	public ProcessNextIdService proccessNextIdService;

	@Autowired
	private EmialInfoRepository emailInfoRepository;
	
	
	@Value("${spring.from.mail}")
	private String fromEmail;

	private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
	
	
	@GetMapping("/getAllInviceType/{cid}/{bid}/{invoiceType}")
	public List<Party> getAllPartiesByInvoiceType(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("invoiceType") String invoiceType) {
		return partyRepository.findByCompanyIdAndBranchIdAndInvoiceTypeAndStatusNot(cid, bid,invoiceType,"D");
	}
	
	
	

	@PostMapping("/add/{id}/{cid}/{bid}/{ipaddress}/{encodedCompanyId}/{encodedBranchId}/{encodedPartyId}")
	public ResponseEntity<Object> createParty(@RequestBody Party party,@PathVariable("id") String id, @PathVariable String ipaddress,
		    @PathVariable String encodedCompanyId,
		    @PathVariable String encodedBranchId,
		    @PathVariable String encodedPartyId,@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
		String nextId = proccessNextIdService.autoIncrementNextIdNext();
		
		party.setCompanyId(cid);
		party.setBranchId(bid);
		party.setPartyId(nextId);
		party.setStatus("A");
		party.setCreatedBy(id);
		party.setApprovedBy(id);
		party.setCreatedDate(new Date());
		party.setApprovedDate(new Date());
		party.setLoaIssueDate(new Date());
		party.setLoaExpiryDate(new Date());
		
		partyRepository.save(party);
		
		Party createdParty = partyService.saveParty(party);
		
			
		ExternalParty  externalParty = new ExternalParty(); 		
		externalParty.setExternaluserId(nextId);
		externalParty.setCompanyId(cid);
		externalParty.setBranchId(bid);
		externalParty.setEmail(party.getEmail());
		externalParty.setLoginPassword("Sanket@13");
		externalParty.setUserType("Party");
		externalParty.setUserName(party.getPartyName());
		externalParty.setLoginUserName(party.getEmail());
		externalParty.setMobile(party.getMobileNo());
		externalParty.setStatus("N");
		externalParty.setCreatedBy(id);
		externalParty.setApprovedBy(id);
		externalParty.setCreatedDate(new Date());
		externalParty.setApprovedDate(new Date());		
		externalpartyService.addExternalParty(externalParty);
		
		
		User externaluser = new User(); 
		
		externaluser.setCompany_Id(cid);
		externaluser.setBranch_Id(bid);
		externaluser.setUser_Name(party.getPartyName());
		externaluser.setUser_Type("Party");
		externaluser.setUser_Password("Sanket@13");
		externaluser.setUser_Id(party.getEmail());
		externaluser.setLogintype("Party");
		externaluser.setLogintypeid(nextId);
		externaluser.setCreated_Date(new Date());
		externaluser.setStatus("A");
		externaluser.setStop_Trans('N');
		externaluser.setOTP("1000");
				
		userCreationRepository.save(externaluser);
					

		// Create the XLS file
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Party Data");

		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Party Name");
		headerRow.createCell(1).setCellValue("Party ID");
		headerRow.createCell(2).setCellValue("Address");
		headerRow.createCell(3).setCellValue("City");
		headerRow.createCell(4).setCellValue("State");
		headerRow.createCell(5).setCellValue("Country");
		headerRow.createCell(6).setCellValue("Unit Admin Name");
		headerRow.createCell(7).setCellValue("Unit Type");
		headerRow.createCell(8).setCellValue("Email");
		headerRow.createCell(9).setCellValue("Phone No");
		headerRow.createCell(10).setCellValue("Mobile No");
		headerRow.createCell(11).setCellValue("Party Code");
		headerRow.createCell(12).setCellValue("ERP Code");
		headerRow.createCell(13).setCellValue("Credit Limit");
		headerRow.createCell(14).setCellValue("IEC No");
		headerRow.createCell(15).setCellValue("Entity ID");
		headerRow.createCell(16).setCellValue("PAN No");
		headerRow.createCell(17).setCellValue("GST No");
		headerRow.createCell(18).setCellValue("LOA Number");
		headerRow.createCell(19).setCellValue("LOA Issue Date");
		headerRow.createCell(20).setCellValue("LOA Expiry Date");
		headerRow.createCell(21).setCellValue("Created By");
		headerRow.createCell(22).setCellValue("Created Date");
		headerRow.createCell(23).setCellValue("Edited By");
		headerRow.createCell(24).setCellValue("Edited Date");
		headerRow.createCell(25).setCellValue("Approved By");
		headerRow.createCell(26).setCellValue("Approved Date");
		headerRow.createCell(27).setCellValue("Status");

		Row dataRow = sheet.createRow(1);
		dataRow.createCell(0).setCellValue(createdParty.getPartyName());
		dataRow.createCell(1).setCellValue(createdParty.getPartyId());
		dataRow.createCell(2).setCellValue(
		createdParty.getAddress1() + ", " + createdParty.getAddress2() + ", " + createdParty.getAddress3());
		dataRow.createCell(3).setCellValue(createdParty.getCity());
		dataRow.createCell(4).setCellValue(createdParty.getState());
		dataRow.createCell(5).setCellValue(createdParty.getCountry());
		dataRow.createCell(6).setCellValue(createdParty.getUnitAdminName());
		dataRow.createCell(7).setCellValue(createdParty.getUnitType());
		dataRow.createCell(8).setCellValue(createdParty.getEmail());
		dataRow.createCell(9).setCellValue(createdParty.getPhoneNo());
		dataRow.createCell(10).setCellValue(createdParty.getMobileNo());
		dataRow.createCell(11).setCellValue(createdParty.getPartyCode());
		dataRow.createCell(12).setCellValue(createdParty.getErpCode());
		dataRow.createCell(13).setCellValue(createdParty.getCreditLimit());
		dataRow.createCell(14).setCellValue(createdParty.getIecNo());
		dataRow.createCell(15).setCellValue(createdParty.getEntityId());
		dataRow.createCell(16).setCellValue(createdParty.getPanNo());
		dataRow.createCell(17).setCellValue(createdParty.getGstNo());
		dataRow.createCell(18).setCellValue(createdParty.getLoaNumber());
		dataRow.createCell(19).setCellValue(createdParty.getLoaIssueDate().toString());
		dataRow.createCell(20).setCellValue(createdParty.getLoaExpiryDate().toString());
		dataRow.createCell(21).setCellValue(createdParty.getCreatedBy());
		dataRow.createCell(22).setCellValue(createdParty.getCreatedDate().toString());
		dataRow.createCell(23).setCellValue(createdParty.getEditedBy());
		dataRow.createCell(24)
		.setCellValue(createdParty.getEditedDate() != null ? createdParty.getEditedDate().toString() : "");
		dataRow.createCell(25).setCellValue(createdParty.getApprovedBy());
		dataRow.createCell(26)
		.setCellValue(createdParty.getApprovedDate() != null ? createdParty.getApprovedDate().toString() : "");
		dataRow.createCell(27).setCellValue(createdParty.getStatus());

		
		
		
		
		
		
		
		String to = createdParty.getEmail();		
		String subject = "New Party Added";		
		String resetLink = "http://"+ipaddress+":3000/login/"+encodedCompanyId+"/"+encodedBranchId+"/"+party.getPartyId()+"/reset";

		String LoginLink = "http://"+ipaddress+":3000/";
		String logoUrl = "https://www.dgdcseepz.com/sites/all/themes/mmtcec/img/logo.png";



		String htmlContent = "<html><head><style>" +
			    "@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400&display=swap');" +
			    "</style></head><body style=\"text-align: center; font-family: 'Roboto', sans-serif;\">" +
			    "<div style=\"display: flex; flex-direction: column; justify-content: space-between; height: 100vh;\">" +
			    "<div style=\"flex: 1; padding: 20px;\"></div>" + // 1/4 space at the top
			    "<div style=\"flex: 2;\">" + // 1/2 space in the middle
			    "<img src=\"" + logoUrl + "\" alt=\"Company Logo\" style=\"max-width: 200px;\"/><br/><br/>" +
			    "<hr style=\"border: 1px solid #000;\">" +
			    "<h3 style=\"color: #000;\">New Party Created, Welcome " + party.getPartyName() + "</h3>" +
			    "<p>A site administrator at DGDC eCustodian has created an account for you.</p>" +
			    "<p>You may now log in by clicking this link or copying and pasting it to your browser.</p>" 
			    


+"<div style=\"margin: 0 auto; width: 50%;\">"
+ "<table border=\"1\" cellpadding=\"10\">"
+ "<tr><th><strong>Party Name </strong></th>" + "<th><strong>Party ID </strong></th>"
+ "<th><strong>Pan No </strong></th>" + "<th><strong>GST No </strong></th>"
+ "<th><strong>Contact No </strong></th>" + "</tr>" +
"<tr><td> " + createdParty.getPartyName() + "</td>" + "<td>" + createdParty.getPartyId() + "</td>"
+ "<td>" + createdParty.getPanNo() + "</td>" + "<td>" + createdParty.getGstNo() + "</td>" + "<td>"
+ createdParty.getMobileNo() + "</td></tr>" + "</table>" + "</div>"
+ "<p>Please find the document attached </p>"+

				
				  
				  "<p>You may now Reset Password by clicking this link or copying and pasting it to your browser.</p>" +
				
			    "<p><a href=\"" + resetLink + "\">Password Reset Link - " + resetLink + "</a></p>" +
			    
			   
			    "<p>This link can only be used anytime to Reset Password and will lead you to a page where you can set your password. After setting your password, you will be able to log in by using following link</p>" +
			    "<p><a href=\"" + LoginLink + "\">Login Link- " + LoginLink + "</a></p>" +
			    "Your User Name : " + party.getEmail() + "<br/>" + " Password : (Use same password that you reset using above link)</p>" +

			    "<hr style=\"border: 1px solid #000;\">" +
			    "<p>Thanks & Regards, DGDC eCustodian Team</p>" +
			    "<p>© DGDC.eCustodian System, All rights Reserved<br/>" +
			   "<br/>" + "To help keep your account secure, please don't forward this email</p>" +			   
			    "</div>" + // End of 1/2 space in the middle
			    "<div style=\"flex: 1; padding: 20px;\"></div>" + // 1/4 space at the bottom
			    "</div>" + // End of flex container
			    "</body></html>";
	

		
		
		
		
//		System.out.println(createdParty.getPanNo());
		
		
		// Save the XLS file to a temporary location
		String xlsFileName = "party_data.xlsx";
		String xlsFilePath = System.getProperty("java.io.tmpdir") + File.separator + xlsFileName;
		
	
		try (FileOutputStream fileOut = new FileOutputStream(xlsFilePath)) {
			workbook.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String ccEmail = "shubhamdeshmukh3826@gmail.com"; // Replace with your sender's email
		// String attachmentPath = xlsFilePath; // Provide the attachment path

		EmailRequest emailInfo = new EmailRequest();
		String nextMailId = proccessNextIdService.autoIncrementMailId();
		emailInfo.setMailTransId(nextMailId);

		emailInfo.setTo(party.getEmail());
		emailInfo.setFromEmail(fromEmail);
		emailInfo.setAttachmentPath(xlsFilePath);
		emailInfo.setSubject("An administrator created an account for you at DGDC eCustodian");
		emailInfo.setFileCreatedDate(new Date());
		emailInfo.setSentDate(new Date());
		emailInfo.setCompanyId(createdParty.getCompanyId());
		emailInfo.setBranchId(createdParty.getBranchId());
		emailInfo.setCcMail(ccEmail); // Replace with the actual CC email address
		emailInfo.setBodyMail(htmlContent);
		emailInfo.setMailStatus("Send Successfully");
		emailInfo.setStatus("N");
		emailInfoRepository.save(emailInfo);

		// Schedule email sending after a delay (1 minute in this example)
		executorService.schedule(() -> {
			boolean emailResult = emailService.sendEmailWithHtmlContentAndAttachment(emailInfo.getSubject(),
					htmlContent, emailInfo.getTo(), xlsFilePath, fromEmail, emailInfo.getCcMail());
			if (emailResult) {
				emailInfo.setStatus("Y");
				emailInfo.setSentDate(new Date());
				// Update status to "Y" if email is sent successfully
			} else {
				emailInfo.setStatus("N"); // Update status to "N" if email sending fails
				System.out.println("Failed to send email...");
			}
			emailInfoRepository.save(emailInfo);
//		}, 1, TimeUnit.MINUTES);
		}, 2, TimeUnit.SECONDS);

		return new ResponseEntity<>("Party added successfully. Email sent.", HttpStatus.OK);

	}



	@PreDestroy
	public void cleanUp() {
		executorService.shutdown();
	}
	
	
	@GetMapping("/getAlldata/{cid}/{bid}")
	public List<Party> getAllParties1(@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
		return partyRepository.getalldata1(cid, bid);
	}
	
	@PostMapping("/editdata")
	public Party editdata(@RequestBody Party party) {
		party.setStatus("A");
		return partyRepository.save(party);
	}

	@GetMapping("/{companyId}/{branchId}/{partyId}")
	public ResponseEntity<Party> getParty(@PathVariable String companyId, @PathVariable String branchId,
			@PathVariable String partyId) {
		PartyId id = new PartyId(companyId, branchId, partyId);
		Party party = partyService.getPartyById(id);
		if (party != null) {
			return new ResponseEntity<>(party, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAll/{cid}/{bid}")
	public List<Party> getAllParties(@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
		return partyRepository.getalldata3(cid, bid);
	}


	@PutMapping("/update/{partyId}")
	public ResponseEntity<Party> updatePartyById(@PathVariable String partyId, 
		    @RequestBody Party updatedParty) {
		Party updatedPartyResult = partyService.updatePartyById(partyId, updatedParty);
		
		
		updatedPartyResult.setStatus("E");
		updatedPartyResult.setCorrection(updatedParty.getCorrection());
		updatedPartyResult.setEditedDate(new Date());
		partyRepository.save(updatedPartyResult);
		
		
		
		ExternalParty existingExternalParty = externalpartyService.getSingleRecord1( partyId);
		
		if (existingExternalParty != null) {
			existingExternalParty.setEmail(updatedParty.getEmail());
			existingExternalParty.setMobile(updatedParty.getMobileNo());
			existingExternalParty.setLoginUserName(updatedParty.getEmail());
			existingExternalParty.setUserName(updatedParty.getPartyName());
			
		externalPartyRepository.save(existingExternalParty);}
		
		
		
		
		
		
		 userrepo.deleteByLogintypeid(partyId);
		
		
		
          User externaluser = new User(); 
		
		externaluser.setCompany_Id(updatedParty.getCompanyId());
		externaluser.setBranch_Id(updatedParty.getBranchId());
		externaluser.setUser_Name(updatedParty.getPartyName());
		externaluser.setUser_Type("Party");				
		externaluser.setUser_Password(existingExternalParty.getLoginPassword());
		externaluser.setUser_Id(updatedParty.getEmail());
		externaluser.setLogintype("Party");
		externaluser.setLogintypeid(updatedParty.getPartyId());
		externaluser.setCreated_Date(new Date());
				
		userCreationRepository.save(externaluser);
	
		return new ResponseEntity<>(updatedPartyResult, HttpStatus.OK);
	}


	@DeleteMapping("/delete/{partyId}")
	@Transactional
	public ResponseEntity<Void> deletePartyById(@PathVariable String partyId) {
		Party p=partyService.getParty(partyId);
		if(partyId!=null)
		{
			p.setStatus("D");
			partyService.updatePartyById(partyId, p);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		

	}
	
	@GetMapping("/{cid}/{bid}/diffparty")
    public List<Party> getPartiesNotInIds(@RequestParam("excludedPartyIds") List<String> excludedPartyIds,@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
		 List<Party> partiesNotInIds = partyService.getPartiesNotInIds(cid,bid,excludedPartyIds);
		
		if(excludedPartyIds == null || excludedPartyIds.isEmpty())
		{
			return partyService.getAllParties();
		}
		return partiesNotInIds;
    }
	
	@GetMapping("/getpartybyid/{cid}/{bid}/{pid}")
	public Party getdatabyid(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("pid") String pid) {
		return this.partyRepository.getdatabyid(cid, bid, pid);
	}
	
	
//	code for sending link on email to reset password 	
	@PostMapping("/resetpassword/{ipaddress}/{encodedCompanyId}/{encodedBranchId}/{encodedPartyId}")
	public ResponseEntity<Object> sendPasswordResetLink(
		    @PathVariable String ipaddress,
		    @PathVariable String encodedCompanyId,
		    @PathVariable String encodedBranchId,
		    @PathVariable String encodedPartyId,
		    @RequestBody Party party
		) {  
			
		partyRepository.save(party);		
		Party createdParty = partyService.saveParty(party);
		
		String subject = "A Request To Set Your New Password";
		

		String resetLink = "http://"+ipaddress+":3000/login/"+encodedCompanyId+"/"+encodedBranchId+"/"+party.getPartyId()+"/reset";
				

		
		String logoUrl = "https://www.dgdcseepz.com/sites/all/themes/mmtcec/img/logo.png";



		String htmlContent = "<html><head><style>" +
			    "@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400&display=swap');" +
			    "</style></head><body style=\"text-align: center; font-family: 'Roboto', sans-serif;\">" +
			    "<div style=\"display: flex; flex-direction: column; justify-content: space-between; height: 100vh;\">" +
			    "<div style=\"flex: 1; padding: 20px;\"></div>" + // 1/4 space at the top
			    "<div style=\"flex: 2;\">" + // 1/2 space in the middle
			    "<img src=\"" + logoUrl + "\" alt=\"Company Logo\" style=\"max-width: 200px;\"/><br/><br/>" +
			    "<hr style=\"border: 1px solid #000;\">" +
			    "<h3 style=\"color: #000;\">Password Reset Request for " + party.getPartyId() + "</h3>" +
			    "<p>A request to reset the password for your account has been made at DGDC eCustodian.</p>" +
			    "<p>You may now log in by clicking this link or copying and pasting it to your browser:</p>" +
			    "<p><a href=\"" + resetLink + "\">Click here - " + resetLink + "</a></p>" +
			    "<p>This link can only be used once to log in and will lead you to a page where you can set your password. It expires after one day and nothing will happen if it's not used.</p>" +
			    "<hr style=\"border: 1px solid #000;\">" +
			    "<p>Thanks & Regards, DGDC eCustodian team</p>" +
			    "<p>DGDC<br/>© DGDC.eCustodian System, All rights Reserved<br/>" +
			    "This message was sent to " + party.getEmail() + "<br/>" + "To help keep your account secure, please don't forward this email</p>" +			   
			    "</div>" + // End of 1/2 space in the middle
			    "<div style=\"flex: 1; padding: 20px;\"></div>" + // 1/4 space at the bottom
			    "</div>" + // End of flex container
			    "</body></html>";
	

										
		String ccEmail = "shubhamdeshmukh3826@gmail.com"; // Replace with your sender's email
		EmailRequest emailInfo = new EmailRequest();
		String nextMailId = proccessNextIdService.autoIncrementMailId();
		emailInfo.setMailTransId(nextMailId);

		emailInfo.setTo(createdParty.getEmail());
		emailInfo.setFromEmail(fromEmail);
		emailInfo.setAttachmentPath("");
		emailInfo.setSubject("Replacement login information for "+party.getPartyId()+" at DGDC eCustodian");
		emailInfo.setFileCreatedDate(new Date());
		emailInfo.setSentDate(new Date());
		emailInfo.setCompanyId(createdParty.getCompanyId());
		emailInfo.setBranchId(createdParty.getBranchId());
		emailInfo.setCcMail(ccEmail); // Replace with the actual CC email address
		emailInfo.setBodyMail(htmlContent);
		emailInfo.setMailStatus("Send Successfully");
		emailInfo.setStatus("N");
		emailInfoRepository.save(emailInfo);

		// Schedule email sending after a delay (1 minute in this example)
		
			boolean emailResult = emailService.sendEmailWithHtmlContent(emailInfo.getSubject(),
					htmlContent, emailInfo.getTo(),  fromEmail, emailInfo.getCcMail());
			if (emailResult) {
				emailInfo.setStatus("Y");
				emailInfo.setSentDate(new Date());
				// Update status to "Y" if email is sent successfully
			} else {
				emailInfo.setStatus("N"); // Update status to "N" if email sending fails
				System.out.println("Failed to send email...");
			}
			emailInfoRepository.save(emailInfo);
		

		return new ResponseEntity<>("Party added successfully. Email sent.", HttpStatus.OK);

	}
	
	@PostMapping(value = "/updatepartyloa")
	public Party updateLoa(@RequestBody Party entity) {
		System.out.println(entity);
	partyRepository.save(entity);
		return entity;
	}
	
}
