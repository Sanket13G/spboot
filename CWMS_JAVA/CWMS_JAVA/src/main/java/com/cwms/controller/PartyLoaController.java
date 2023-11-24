package com.cwms.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cwms.entities.PartyLOA;
import com.cwms.entities.RepresentParty;
import com.cwms.helper.FileUploadProperties;
import com.cwms.repository.PartyLoaRepository;
import com.cwms.repository.PartyRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/partyLoa")
public class PartyLoaController {

	@Autowired
	private PartyRepository partyRepository;

	@Autowired
	private PartyLoaRepository partyLoaRepository;

	@Autowired
	public FileUploadProperties FileUploadProperties;

	@GetMapping(value = "/gethello")
	public String getMethodName() {
		return "Test To Check";
	}

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

	@PostMapping(value = "/savepartyLoa/{formData}")
	public ResponseEntity<Object> addPartyLOA(@PathVariable("formData") String formData) throws ParseException {

		// Create a new PartyLOA object
		PartyLOA newPartyLOA = new PartyLOA();
		newPartyLOA.setCurrentDate();

		// Split the formData string based on "&" to get individual parameters
		String[] params = formData.split("&");

		// Iterate over each parameter and set the values for the PartyLOA object
		for (String param : params) {
			String[] keyValue = param.split("=");
			if (keyValue.length == 2) {
				String key = keyValue[0];
				String value = keyValue[1];

				// Set values for the PartyLOA object based on parameter names

				switch (key) {
				case "companyid":
					newPartyLOA.setCompanyId(value);
					break;
				case "branchId":
					newPartyLOA.setBranchId(value);
					break;
				case "loaNumber":
					newPartyLOA.setLoaNumber(value);
					break;
				case "loaIssueDate":
					// Parse the date string and set it in the PartyLOA
					SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)");
					Date loaIssueDate = dateFormat.parse(value);
					newPartyLOA.setLoaIssueDate(loaIssueDate);
					break;
				case "oldLoaExpiryDate":
					// Parse the date string and set it in the PartyLOA
					Date oldLoaExpiryDate = new Date(Long.parseLong(value));
					newPartyLOA.setOldLoaExpiryDate(oldLoaExpiryDate);
					break;
				case "createdBy":
					newPartyLOA.setCreatedBy(value);
					break;
				case "newLoaExpiryDate":
					// Parse the date string and set it in the PartyLOA
					SimpleDateFormat dateFormat1 = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)");
					Date newLoaExpiryDate = dateFormat1.parse(value);
					newPartyLOA.setNewLoaExpiryDate(newLoaExpiryDate);
					break;
				case "partyid":
					newPartyLOA.setPartyId(value);
					break;
				case "status":
					newPartyLOA.setStatus(value);
					break;
				case "imagePath":
					// Handle imagePath accordingly, e.g., set it as a String
					newPartyLOA.setImagePath(value);
					break;
				default:
					// Handle other parameters if needed
					break;
				}
			}
		}
		// Print the newPartyLOA object to the console
		System.out.println(newPartyLOA);

		// Now, you have set the values for the PartyLOA object based on the parameters
		// in the formData string

		// Save the PartyLOA object to the repository (assuming partyLoaRepository
		// exists)
		PartyLOA savedPartyLOA = partyLoaRepository.saveAndFlush(newPartyLOA);

		return ResponseEntity.status(HttpStatus.OK).body(savedPartyLOA);
	}

	@PostMapping("/add")
	public String addPartyLoa(@RequestParam("loaNumber") String loaNumber,
			@RequestParam("loaIssueDate") String loaIssueDate,
			@RequestParam("oldLoaExpiryDate") String oldLoaExpiryDate,
			@RequestParam("newLoaExpiryDate") String newLoaExpiryDate, @RequestParam("createdBy") String createdBy,
			@RequestParam("status") String status, @RequestParam("partyId") Long partyId,
			@RequestParam("imagePath") MultipartFile imagePath) {
		// Handle the file and other parameters as needed
		// Save the file to a directory, and process other parameters
		return "LOA added successfully!";
	}

	@PostMapping("/partyLoa/add")
	public String uploadFileWithFormData(@RequestPart("loaNumber") String loaNumber,
			@RequestPart("loaIssueDate") String loaIssueDate, @RequestPart("oldLoaExpiryDate") String oldLoaExpiryDate,
			@RequestPart("newLoaExpiryDate") String newLoaExpiryDate, @RequestPart("createdBy") String createdBy,
			@RequestPart("status") String status, @RequestPart("partyId") Long partyId,
			@RequestPart("imagePath") MultipartFile file) {

		return "File uploaded successfully";
	}

	@PostMapping("/add1")
	public String uploadFileWithFormData1(@RequestBody PartyLOA body) {
		body.setCurrentDate();
		System.out.println(body);

		return "File uploaded successfully";
	}

	@PostMapping("/addRepresentative1/{companyid}/{branchId}/{partyid}")
	public ResponseEntity<Object> addPartyRepresentative1(

			@PathVariable("companyid") String cid, @PathVariable("branchId") String bid,
			@PathVariable("partyid") String partyid,

			@RequestParam("file") MultipartFile file, @RequestParam("firstName") String firstName,
			@RequestParam("middleName") String middleName, @RequestParam("lastName") String lastName,
			@RequestParam("mobile") String mobile, @RequestParam("companyid") String companyid,
			@RequestParam("companyid") String companyid1, @RequestParam("branchId") String branchId,
			@RequestParam("status") String status) throws java.io.IOException {

		try {

			RepresentParty newparty = new RepresentParty();

			newparty.setRepresentativeId("10");
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
			newparty.setOtp("1000");
			newparty.setCreatedDate(new Date());

			// Get the original file name
			String originalFileName = file.getOriginalFilename();

			// Generate a unique file name to avoid duplicates
			String uniqueFileName = generateUniqueFileName(originalFileName);

			newparty.setImagePath(FileUploadProperties.getPath() + uniqueFileName);

			// Save the file to your local system with the unique name
			Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName));

//		      representPartyrepo.save(newparty);		  
//		      RepresentParty savedParty = representPartyrepo.save(newparty);
			return ResponseEntity.status(HttpStatus.OK).body(newparty);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
		}
		// Return the saved object with a success status

	}

	@PostMapping("/addRepresentative/{companyid}/{branchId}/{partyid}")
	public ResponseEntity<Object> addPartyRepresentative(

			@PathVariable("companyid") String cid, @PathVariable("branchId") String bid,
			@PathVariable("partyid") String partyid,

			@RequestParam("file") MultipartFile file, @RequestParam("loaNumber") String loaNumber,
			@RequestParam("loaIssueDate") Date loaIssueDate, @RequestParam("oldLoaExpiryDate") Date oldLoaExpiryDate,
			@RequestParam("newLoaExpiryDate") Date newLoaExpiryDate,

			@RequestParam("createdBy") String createdBy, @RequestParam("partyId") String partyId,
			@RequestParam("status") String status) throws java.io.IOException {

		try {

			PartyLOA newparty = new PartyLOA();

			newparty.setCompanyId(cid);
			newparty.setBranchId(bid);
			newparty.setPartyId(partyId);
			newparty.setLoaNumber(loaNumber);
			newparty.setLoaIssueDate(loaIssueDate);
			newparty.setOldLoaExpiryDate(oldLoaExpiryDate);
			newparty.setNewLoaExpiryDate(newLoaExpiryDate);
			newparty.setStatus("A");
			newparty.setCreatedBy(createdBy);
			newparty.setCreatedDate(new Date());

			// Get the original file name
			String originalFileName = file.getOriginalFilename();

			// Generate a unique file name to avoid duplicates
			String uniqueFileName = generateUniqueFileName(originalFileName);

			newparty.setImagePath(FileUploadProperties.getPath() + uniqueFileName);

			// Save the file to your local system with the unique name
			Files.copy(file.getInputStream(), Paths.get(FileUploadProperties.getPath() + uniqueFileName));

			System.out.println(partyLoaRepository.save(newparty));

			return ResponseEntity.status(HttpStatus.OK).body(newparty);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
		}
		// Return the saved object with a success status

	}

	@PostMapping("/addRepresentativewithoutfile/{companyid}/{branchId}/{partyid}")
	public ResponseEntity<Object> addPartyRepresentative1(
			@PathVariable("companyid") String cid, @PathVariable("branchId") String bid,
			@PathVariable("partyid") String partyid,

			@RequestParam("loaNumber") String loaNumber, @RequestParam("loaIssueDate") Date loaIssueDate,
			@RequestParam("oldLoaExpiryDate") Date oldLoaExpiryDate,
			@RequestParam("newLoaExpiryDate") Date newLoaExpiryDate,

			@RequestParam("createdBy") String createdBy, @RequestParam("partyId") String partyId,
			@RequestParam("status") String status) throws java.io.IOException {

		PartyLOA newparty = new PartyLOA();

		newparty.setCompanyId(cid);
		newparty.setBranchId(bid);
		newparty.setPartyId(partyId);
		newparty.setLoaNumber(loaNumber);
		newparty.setLoaIssueDate(loaIssueDate);
		newparty.setOldLoaExpiryDate(oldLoaExpiryDate);
		newparty.setNewLoaExpiryDate(newLoaExpiryDate);
		newparty.setStatus("A");
		newparty.setCreatedBy(createdBy);
		newparty.setCreatedDate(new Date());

		// Get the original file name
		System.out.println(partyLoaRepository.save(newparty));

		return ResponseEntity.status(HttpStatus.OK).body(newparty);

	}

	@GetMapping(value = "/historyLoa/{companyid}/{branchId}/{partyid}")
	public List<PartyLOA> postMethodName(@PathVariable("companyid") String cid, @PathVariable("branchId") String bid,
			@PathVariable("partyid") String partyid) {

		System.out.println(partyLoaRepository.findByCompanyBranchAndPartyId(cid, bid, partyid));

		// TODO: process POST request
		return partyLoaRepository.findByCompanyBranchAndPartyId(cid, bid, partyid);

	}

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

	private boolean isPdfFile(String fileExtension) {
		return fileExtension.equals("pdf");
	}

	@GetMapping(value = "/getfile/{companyid}/{branchId}/{partyid}/{loaserId}")
	public ResponseEntity<?> getfileLoa(@PathVariable("companyid") String cid, @PathVariable("branchId") String bid,
			@PathVariable("partyid") String partyid, @PathVariable("loaserId") String loaserId) {

		PartyLOA partyLOA = partyLoaRepository.findPartyLOAByCompositeKey(cid, bid, partyid, loaserId);

		if (partyLOA != null) {
			String nsdlStatusDocsPath = partyLOA.getImagePath();
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
					} else if (isPdfFile(fileExtension)) {
						// If it's a PDF file, return the PDF data as base64
						byte[] pdfBytes = Files.readAllBytes(filePath);
						String pdfBase64 = Base64.getEncoder().encodeToString(pdfBytes);

						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_PDF); // Set the content type to application/pdf

						return new ResponseEntity<>(pdfBase64, headers, HttpStatus.OK);
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

}