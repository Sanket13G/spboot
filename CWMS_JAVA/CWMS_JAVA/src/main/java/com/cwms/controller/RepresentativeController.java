package com.cwms.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import com.cwms.entities.RepresentParty;
import com.cwms.repository.RepresentPartyRepository;
import com.cwms.service.RepsentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
@RestController
@RequestMapping("NewReprentative")
@CrossOrigin("*")
public class RepresentativeController {
	@Autowired
	public RepsentativeService RepsentativeService;
	@Autowired
	public RepresentPartyRepository representPartyRepository;
	
	@GetMapping("/{CompId}/{BranchId}/{UserType}/Bytype")
	public List<RepresentParty> getAll(@PathVariable("CompId") String CompId, @PathVariable("BranchId") String BranchId
			,@PathVariable("UserType") String UserType)
	{	
		return RepsentativeService.findAlRepositary(CompId, BranchId, UserType);
		
	}
	
	@GetMapping("/{CompId}/{BranchId}/{userId}/{reprentativeId}/Byid")
	public RepresentParty getByrepresentative(@PathVariable("CompId") String CompId, @PathVariable("BranchId") String BranchId
			,@PathVariable("reprentativeId") String reprentativeId,@PathVariable("userId") String userId)
	{
		
		
		return RepsentativeService.findByRepresentativeId(CompId, BranchId, userId,reprentativeId);
		
		
	}
	
	@GetMapping("/{CompId}/{BranchId}/{UserId}/ByUserID")
	public List<RepresentParty> getAllByUserId(@PathVariable("CompId") String CompId, @PathVariable("BranchId") String BranchId
			,@PathVariable("UserId") String UserId)
	{	
		return RepsentativeService.findAlRepositaryByUserID(CompId, BranchId, UserId);
		
	}
	
	@GetMapping("/{CompId}/{BranchId}/{userId}/{reprentativeId}/getImage")
	public ResponseEntity<String> getImage(@PathVariable("CompId") String CompId, @PathVariable("BranchId") String BranchId
			,@PathVariable("reprentativeId") String reprentativeId,@PathVariable("userId") String userId)
	 throws IOException {
	    
	    RepresentParty findByRepresentativeId = RepsentativeService.findByRepresentativeId(CompId, BranchId, userId,reprentativeId);

	    if (findByRepresentativeId != null) {
	        String nsdlStatusDocsPath = findByRepresentativeId.getImagePath();
	        Path filePath = Paths.get(nsdlStatusDocsPath);

	        // Check if the file exists
	        if (Files.exists(filePath)) {
	            try {
	                byte[] imageBytes = Files.readAllBytes(filePath);

	                // Encode the image bytes to base64
	                String base64Image = Base64.getEncoder().encodeToString(imageBytes);

	                // Construct a data URL for the image
	                String dataURL = "data:image/jpeg;base64," + base64Image;
	                
	                System.out.println("In Image");

	                HttpHeaders headers = new HttpHeaders();
	                headers.setContentType(MediaType.TEXT_PLAIN); // Set the content type to text/plain

	                return new ResponseEntity<>(dataURL, headers, HttpStatus.OK);
	            } catch (java.io.IOException e) {
	                // Handle the IOException appropriately (e.g., log it)
	                e.printStackTrace();
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	            }
	        }
	    }

	    return ResponseEntity.notFound().build();
	}


	@GetMapping(value = "/getPartyRepresentative/{CompId}/{BranchId}")
	public List<RepresentParty> getPartyRepresentative(@PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId) {
		return RepsentativeService.getall(CompId, BranchId);
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

	@GetMapping("/getAllRepresentPartyImg/{rpid}")
	public ResponseEntity<?> getImageOrPdf(@PathVariable("rpid") String rpid) throws IOException {

		RepresentParty RepresentPartyObject = representPartyRepository.getByRepresentativeId(rpid);

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
				} catch (IOException e) {
					// Handle the IOException appropriately (e.g., log it)
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
			}
		}

		return ResponseEntity.notFound().build();
	}

//	get single image from database
	@GetMapping("/getImage1/{compid}/{branchId}/{pid}/{rpid}")
	public ResponseEntity<?> getImageOrPdf(@PathVariable("compid") String compid,
			@PathVariable("branchId") String branchId, @PathVariable("pid") String pid,
			@PathVariable("rpid") String rpid) throws IOException {

		RepresentParty representPartyObject = representPartyRepository
				.getByCompanyIdAndBranchIdAndUserIdAndRepresentativeId(compid, branchId, pid, rpid);
//		System.out.println("test" + representPartyObject);
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
				} catch (IOException e) {
					// Handle the IOException appropriately (e.g., log it)
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
			}
		}

		return ResponseEntity.notFound().build();
	}
	
	
	
	
}
