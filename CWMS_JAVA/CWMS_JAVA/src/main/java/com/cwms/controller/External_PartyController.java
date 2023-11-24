package com.cwms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.EmailRequest;
import com.cwms.entities.ExternalParty;
import com.cwms.entities.User;
import com.cwms.repository.EmialInfoRepository;
import com.cwms.repository.ExternalPartyRepository;
import com.cwms.repository.UserCreationRepository;
import com.cwms.repository.UserRepository;
import com.cwms.service.EmailService;
import com.cwms.service.ExternalParty_Service;
import com.cwms.service.ProcessNextIdService;

@RequestMapping("/externalParty")
@CrossOrigin("*")
@RestController
public class External_PartyController {
	@Autowired
	private ExternalParty_Service ExternalParty_Service;
	
	@Autowired
	private EmialInfoRepository emailInfoRepository;

	@Autowired
	private ProcessNextIdService ProcessNextIdService;

	private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	public ProcessNextIdService proccessNextIdService;

	@Autowired
	public UserCreationRepository userCreationRepository;
	
	@Autowired
	public EmialInfoRepository emialInfoRepository;
	
	
	@Autowired
	public ExternalPartyRepository externalPartyRepository;
	
	
	@Value("${spring.from.mail}")
	private String fromEmail;

	@Autowired
	private BCryptPasswordEncoder passwordencode;
	
	@Autowired
	public EmailService emailService;

//	@PostMapping("/{compid}/{branchId}/{user}/add")
//	public  ResponseEntity<?> addExternalParty(@RequestBody ExternalParty ExternalParty,
//			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
//			@PathVariable("user") String User)
//	{
//		
//		boolean checkMobileExist = ExternalParty_Service.checkMobileExist(ExternalParty.getMobile());
//		boolean checkEmailExist = ExternalParty_Service.checkEmailExist(ExternalParty.getEmail());
//		boolean checkUsernameExist = ExternalParty_Service.checkLoginusenameExist(ExternalParty.getLoginUserName());
//		if(checkMobileExist)
//		{
//			return ResponseEntity.status(HttpStatus.CONFLICT).body("Mobile number already exists");	
//		}
//		else if (checkEmailExist)
//		{
//			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");	
//		}
//		else if (checkUsernameExist)
//		{
//			return ResponseEntity.status(HttpStatus.CONFLICT).body("Login Username already exists");	
//		}
//		else
//		{
//			if (ExternalParty.getExternaluserId() == null || ExternalParty.getExternaluserId().trim().isEmpty()) {
//				ExternalParty.setExternaluserId(ProcessNextIdService.autoIncrementExternalUserId());
//				ExternalParty.setStatus("N");
//				ExternalParty.setUserstatus("A");
//				ExternalParty.setCreatedBy(User);
//				ExternalParty.setCreatedDate(new Date());
//				ExternalParty.setApprovedBy(User);
//				ExternalParty.setApprovedDate(new Date());
//				
//			}
//			else
//			{
////				ExternalParty singleRecord = ExternalParty_Service.getSingleRecord(compid, branchId, ExternalParty.getExternaluserId());
//				ExternalParty.setStatus("A");
//				ExternalParty.setApprovedBy(User);
//				ExternalParty.setApprovedDate(new Date());
//			}
//			
//			
//			
//			
//		
//		ExternalParty addExternalParty = ExternalParty_Service.addExternalParty(ExternalParty);
//		return ResponseEntity.ok(addExternalParty);
//		
//		
//		
//		
//		
//		}
//		
//	}
//	
	
	@GetMapping("/console/{cid}/{bid}")
    public List<ExternalParty> getExternalParty(
        @PathVariable
        ("cid") String companyId,
        @PathVariable("bid") String branchId
    ) {
        // Call the repository method with the provided parameters
        return externalPartyRepository.getAllExternalParties(companyId, branchId);
    }

	@PostMapping("/{compid}/{branchId}/{user}/{encodedCompanyId}/{encodedBranchId}/{ipaddress}/add")
	public ResponseEntity<?> addExternalParty(@RequestBody ExternalParty ExternalParty,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,@PathVariable("encodedCompanyId") String encodedCompanyId,
			@PathVariable("encodedBranchId") String encodedBranchId, @PathVariable("ipaddress") String ipaddress,@PathVariable("user") String User) {

		if (ExternalParty.getExternaluserId() == null || ExternalParty.getExternaluserId().trim().isEmpty()) {

			boolean checkMobileExist = ExternalParty_Service.checkMobileExist(compid, branchId,
					ExternalParty.getMobile());
			boolean checkEmailExist = ExternalParty_Service.checkEmailExist(compid, branchId, ExternalParty.getEmail());
			boolean checkUsernameExist = ExternalParty_Service.checkLoginusenameExist(compid, branchId,
					ExternalParty.getLoginUserName());
			if (checkMobileExist) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Mobile number already exists");
			} else if (checkEmailExist) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
			} else if (checkUsernameExist) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Login Username already exists");
			} else {

				ExternalParty.setExternaluserId(ProcessNextIdService.autoIncrementExternalUserId());
				ExternalParty.setStatus("N");
//				ExternalParty.setUserstatus("A");
				ExternalParty.setCreatedBy(User);
				ExternalParty.setCreatedDate(new Date());
				ExternalParty.setApprovedBy(User);
				ExternalParty.setApprovedDate(new Date());

			}

		} else {
			boolean existsUsername = ExternalParty_Service.existsUsernameForOtherRecords(
					ExternalParty.getLoginUserName(), compid, branchId, ExternalParty.getExternaluserId());
			boolean existsMobile = ExternalParty_Service.existsMobileForOtherRecords(ExternalParty.getMobile(), compid,
					branchId, ExternalParty.getExternaluserId());
			boolean existsEmail = ExternalParty_Service.existsEmailForOtherRecords(ExternalParty.getEmail(), compid,
					branchId, ExternalParty.getExternaluserId());
			if (existsEmail) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
			} else if (existsUsername) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Login Username already exists");
			} else if (existsMobile) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Mobile number already exists");

			}

			ExternalParty.setStatus("A");
			ExternalParty.setEditedBy(User);
			ExternalParty.setEditedDate(new Date());

		}

		ExternalParty addExternalParty = ExternalParty_Service.addExternalParty(ExternalParty);
		
        User externaluser = new User(); 
		
		externaluser.setCompany_Id(compid);
		externaluser.setBranch_Id(branchId);
		externaluser.setUser_Name(addExternalParty.getUserName());
		externaluser.setUser_Type(addExternalParty.getUserType());
		externaluser.setUser_Password("Sanket@13");
		externaluser.setUser_Id(addExternalParty.getLoginUserName());
		externaluser.setLogintype(addExternalParty.getUserType());
		externaluser.setLogintypeid(addExternalParty.getExternaluserId());
		externaluser.setCreated_Date(new Date());
		externaluser.setStatus("A");
		externaluser.setOTP("1000");
		externaluser.setStop_Trans('N');
				
		userCreationRepository.save(externaluser);
		
		
		
		// Create the XLS file
				Workbook workbook = new XSSFWorkbook();
				Sheet sheet = workbook.createSheet("Party Data");

				Row headerRow = sheet.createRow(0);
				headerRow.createCell(0).setCellValue("User Name");
				headerRow.createCell(1).setCellValue("User Type ");
				headerRow.createCell(2).setCellValue("Email");
				headerRow.createCell(3).setCellValue("Login User Name");
				headerRow.createCell(4).setCellValue("Mobile");
				

				Row dataRow = sheet.createRow(1);
				dataRow.createCell(0).setCellValue(addExternalParty.getUserName());
				dataRow.createCell(1).setCellValue(addExternalParty.getUserType());
				dataRow.createCell(2).setCellValue(addExternalParty.getEmail());
				dataRow.createCell(3).setCellValue(addExternalParty.getLoginUserName());
				dataRow.createCell(4).setCellValue(addExternalParty.getMobile());
			
		


		String to = addExternalParty.getEmail();
		String subject = "New " + addExternalParty.getUserType() +"Added";
		String resetLink = "http://" + ipaddress + ":3001/login/" + encodedCompanyId + "/" + encodedBranchId + "/"
				+ addExternalParty.getExternaluserId() + "/reset/externaluser";

		String LoginLink = "http://" + ipaddress + ":3001/";
		String logoUrl = "https://www.dgdcseepz.com/sites/all/themes/mmtcec/img/logo.png";

		String htmlContent = "<html><head><style>"
				+ "@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400&display=swap');"
				+ "</style></head><body style=\"text-align: center; font-family: 'Roboto', sans-serif;\">"
				+ "<div style=\"display: flex; flex-direction: column; justify-content: space-between; height: 100vh;\">"
				+ "<div style=\"flex: 1; padding: 20px;\"></div>" + // 1/4 space at the top
				"<div style=\"flex: 2;\">" + // 1/2 space in the middle
				"<img src=\"" + logoUrl + "\" alt=\"Company Logo\" style=\"max-width: 200px;\"/><br/><br/>"
				+ "<hr style=\"border: 1px solid #000;\">" + "<h3 style=\"color: #000;\">New External User Created, Welcome "
				+ addExternalParty.getUserName() + "</h3>"
				+ "<p>A site administrator at DGDC eCustodian has created an account for you.</p>"
				+ "<p>You may now log in by clicking this link or copying and pasting it to your browser.</p>"

				+ "<div style=\"margin: 0 auto; width: 50%;\">" + "<table border=\"1\" cellpadding=\"10\">"
				+ "<tr><th><strong>User Name </strong></th>" + "<th><strong>User ID </strong></th>"
				+ "<th><strong>User  Type</strong></th>" + "<th><strong>Email ID </strong></th>"
				+ "<th><strong>Contact No </strong></th>" + "</tr>" + "<tr><td> " + addExternalParty.getUserName()
				+ "</td>" + "<td>" + addExternalParty.getExternaluserId()+ "</td>" + "<td>" + addExternalParty.getUserType()+ "</td>"
				+ "<td>" + addExternalParty.getEmail() + "</td>" + "<td>" + addExternalParty.getMobile() + "</td></tr>"
				+ "</table>" + "</div>" + "<p>Please find the document attached </p>" +

				"<p>You may now Reset Password by clicking this link or copying and pasting it to your browser.</p>" +

				"<p><a href=\"" + resetLink + "\">Password Reset Link - " + resetLink + "</a></p>" +

				"<p>This link can be used anytime to Reset Password and will lead you to a page where you can set your password. After setting your password, you will be able to log in by using following link</p>"
				+ "<p><a href=\"" + LoginLink + "\">Login Link- " + LoginLink + "</a></p>" + "Your User Name : "
				+ addExternalParty.getEmail() + "<br/>" + " Password : (Use same password that you reset using above link)</p>" +

				"<hr style=\"border: 1px solid #000;\">" + "<p>Thanks & Regards, DGDC eCustodian Team</p>"
				+ "<p>© DGDC.eCustodian System, All rights Reserved<br/>" + "<br/>"
				+ "To help keep your account secure, please don't forward this email</p>" + "</div>" + // End of 1/2
																										// space in the
																										// middle
				"<div style=\"flex: 1; padding: 20px;\"></div>" + // 1/4 space at the bottom
				"</div>" + // End of flex container
				"</body></html>";
		
		
		
		

		
		
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

		emailInfo.setTo(addExternalParty.getEmail());
		emailInfo.setFromEmail(fromEmail);
		emailInfo.setAttachmentPath(xlsFilePath);
		emailInfo.setSubject("An administrator created an account for you at DGDC eCustodian");
		emailInfo.setFileCreatedDate(new Date());
		emailInfo.setSentDate(new Date());
		emailInfo.setCompanyId(addExternalParty.getCompanyId());
		emailInfo.setBranchId(addExternalParty.getBranchId());
		emailInfo.setCcMail(ccEmail); // Replace with the actual CC email address
		emailInfo.setBodyMail(htmlContent);
		emailInfo.setMailStatus("Send Successfully");
		emailInfo.setStatus("N");
		emialInfoRepository.save(emailInfo);

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
					emialInfoRepository.save(emailInfo);

				}, 2, TimeUnit.SECONDS);

				return new ResponseEntity<>("Party added successfully. Email sent.", HttpStatus.OK);

			}

	@PutMapping("/{compid}/{branchId}/{user}/{UserId}/delete")
	public ExternalParty updateExternalParty(@RequestBody ExternalParty ExternalParty,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("UserId") String UserId,@PathVariable("user") String user) {
		ExternalParty singleRecord = ExternalParty_Service.getSingleRecord(compid, branchId, UserId);

		if (singleRecord != null) {

			singleRecord.setStatus("D");
			singleRecord.setUserstatus("I");
			singleRecord.setEditedBy(user);
			singleRecord.setEditedDate(new Date());
			return ExternalParty_Service.addExternalParty(singleRecord);

		}
		return null;
	}

	@GetMapping("/{compid}/{branchId}/{userId}/get")
	public ExternalParty getSingleRecord(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId, @PathVariable("userId") String UserId) {
		return ExternalParty_Service.getSingleRecord(compid, branchId, UserId);
	}

	@GetMapping("/{compid}/{branchId}/getAll")
	public List<ExternalParty> getAllRecords(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId) {
		return ExternalParty_Service.getAllRecords(compid, branchId);
	}
	
	@GetMapping("/{compid}/{branchId}/{UserType}/getByUsertype")
	public List<ExternalParty> getAllRecordsByType(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId,
			@PathVariable("UserType") String UserType) {
		return ExternalParty_Service.findByCompanyIdAndBranchIdAndUserType(compid, branchId,UserType);
	}
	
	@GetMapping("/{compid}/{branchId}/{UserType}/GetForImport")
	public List<ExternalParty> getAllRecordsByTypeAndUserStatus(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId,
			@PathVariable("UserType") String UserType) {
		return ExternalParty_Service.findByUserType(compid, branchId,UserType);
	}

	
	@GetMapping("/{compid}/{branchId}/{UserId}/getByUsernameByID")
	public String getByUsernameByID(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId,
			@PathVariable("UserId") String externaluserid) {
		return ExternalParty_Service.findUsernameByExternalUserId(compid, branchId,externaluserid);
	}
	

	@PostMapping("/{DecodedCompanyId1}/{DecodedBranchId1}/{encodedPartyId}/{password}/update-password")
	public ResponseEntity<?> updateExternalPartyPassword(@PathVariable("DecodedCompanyId1") String compid,
			@PathVariable("DecodedBranchId1") String branchId, @PathVariable("encodedPartyId") String UserId,
			@PathVariable("password") String password) {

		User externaluser = userrepo.findByLogintypeid(UserId);

		if (externaluser != null) {

			String encodedPassword = passwordencode.encode(password);
			externaluser.setUser_Password(encodedPassword);
			externaluser.setStatus("A");
			externaluser.setStop_Trans('N');
			userCreationRepository.save(externaluser);
		}

		ExternalParty singleRecord = ExternalParty_Service.getSingleRecord(compid, branchId, UserId);
		System.out.print(singleRecord);

		if (singleRecord != null) {
			// Assuming you have a password encoder bean named 'passwordEncoder' configured
			// in your application
			String encodedPassword = passwordencode.encode(password);
			singleRecord.setLoginPassword(encodedPassword);
			System.out.print(encodedPassword);

			ExternalParty updatedExternalParty = ExternalParty_Service.addExternalParty(singleRecord);

			if (updatedExternalParty != null) {
				// Return a successful response with HTTP status 200 OK
				return new ResponseEntity<>("Password updated successfully.", HttpStatus.OK);
			} else {
				// Handle the case where the update failed
				return new ResponseEntity<>("Password update failed.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			// Handle the case where the external party record was not found
			return new ResponseEntity<>("External party not found.", HttpStatus.NOT_FOUND);
		}
	}
	
	

//	code for sending link on email to reset password 	for External User
	
	@PostMapping("/resetpassword/{ipaddress}/{encodedCompanyId}/{encodedBranchId}/{DecodedUserId1}")
	public ResponseEntity<Object> sendPasswordResetLink(
		    @PathVariable String ipaddress,
		    @PathVariable String encodedCompanyId,
		    @PathVariable String encodedBranchId,
		    @PathVariable String DecodedUserId1,
		    @RequestBody ExternalParty party
		) {  
			
//		externalPartyRepository.save(party);		
//		Party createdParty = ExternalParty_Service.saveExternalParty(party);
		
		String subject = "A Request To Set Your New Password";
		

		String resetLink = "http://"+ipaddress+":3001/login/"+encodedCompanyId+"/"+encodedBranchId+"/"+DecodedUserId1+"/reset/externaluser";
				

		
		String logoUrl = "https://www.dgdcseepz.com/sites/all/themes/mmtcec/img/logo.png";



		String htmlContent = "<html><head><style>" +
			    "@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400&display=swap');" +
			    "</style></head><body style=\"text-align: center; font-family: 'Roboto', sans-serif;\">" +
			    "<div style=\"display: flex; flex-direction: column; justify-content: space-between; height: 100vh;\">" +
			    "<div style=\"flex: 1; padding: 20px;\"></div>" + // 1/4 space at the top
			    "<div style=\"flex: 2;\">" + // 1/2 space in the middle
			    "<img src=\"" + logoUrl + "\" alt=\"Company Logo\" style=\"max-width: 200px;\"/><br/><br/>" +
			    "<hr style=\"border: 1px solid #000;\">" +
			    "<h3 style=\"color: #000;\">Password Reset Request for " + DecodedUserId1 + "</h3>" +
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

		emailInfo.setTo(party.getEmail());
		emailInfo.setFromEmail(fromEmail);
		emailInfo.setAttachmentPath("");
		emailInfo.setSubject("Replacement login information for "+DecodedUserId1+" at DGDC eCustodian");
		emailInfo.setFileCreatedDate(new Date());
		emailInfo.setSentDate(new Date());
		emailInfo.setCompanyId(party.getCompanyId());
		emailInfo.setBranchId(party.getBranchId());
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
		

		return new ResponseEntity<>("Email sent.", HttpStatus.OK);

	}
	
	
	
}
