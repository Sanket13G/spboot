package com.cwms.controller;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.core.io.ClassPathResource;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import com.cwms.entities.PartyRepresentative;
import com.cwms.repository.PartyRepresentativeRepo;
import com.cwms.service.PartyRepresentativeService;
import com.cwms.service.ProcessNextIdService;

import jakarta.transaction.Transactional;

@CrossOrigin("*")
@RestController
@RequestMapping("/representive")

@EnableWebMvc
public class PartyRepresentativeController {

	@Autowired
	private PartyRepresentativeService partyRepresentativeService;

	@Autowired
	public ProcessNextIdService proccessNextIdService;

	@Autowired
	public PartyRepresentativeRepo reprsenetRepo;

//	  @PostMapping("/addRepresentive")
//	    public ResponseEntity<PartyRepresentative> createPartyRepresentative(
//	            @RequestBody PartyRepresentative partyRepresentative
//	    ) 
//	  {
//		  String nextPRd = proccessNextIdService.autoIncrementReprsentiveiD();
//		  partyRepresentative.setPartyRepresentativeId(nextPRd);
//	        PartyRepresentative savedPartyRepresentative = partyRepresentativeService.createPartyRepresentative(partyRepresentative);
//	       
//	        savedPartyRepresentative.setStatus("New");
//	        savedPartyRepresentative.setContactStatus("Active");
//	        savedPartyRepresentative.setCreatedDate(new Date());
//	        savedPartyRepresentative.setApprovedDate(new Date());
//	        reprsenetRepo.save(savedPartyRepresentative);
//	        return new ResponseEntity<>(savedPartyRepresentative, HttpStatus.CREATED);
//	    }
//	  

//	@PostMapping("/upload")
//	public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
//		try {
//			String imagePath = partyRepresentativeService.uploadImage(image);
//			return ResponseEntity.ok(imagePath);
//		} catch (Exception e) {
//			return ResponseEntity.status(500).body("Error uploading image");
//		}
//	}

//	  @PostMapping("/addRepresentive")
//	  public ResponseEntity<PartyRepresentative> createPartyRepresentative(
//	      @RequestParam("image") MultipartFile image,
//	      @RequestBody PartyRepresentative partyRepresentative
//	  ) {
//	      try {
//	          String nextPRd = proccessNextIdService.autoIncrementReprsentiveiD();
//	          partyRepresentative.setPartyRepresentativeId(nextPRd);
//
//	          // Save the image data in the partyRepresentative entity
//	          partyRepresentative.setImagePath(image.getBytes());
//
//	          PartyRepresentative savedPartyRepresentative = partyRepresentativeService.createPartyRepresentative(partyRepresentative);
//
//	          savedPartyRepresentative.setStatus("New");
//	          savedPartyRepresentative.setContactStatus("Active");
//	          savedPartyRepresentative.setCreatedDate(new Date());
//	          savedPartyRepresentative.setApprovedDate(new Date());
//	          reprsenetRepo.save(savedPartyRepresentative);
//	          
//	          return new ResponseEntity<>(savedPartyRepresentative, HttpStatus.CREATED);
//	      } catch (Exception e) {
//	          return ResponseEntity.status(500).body("Error creating party representative");
//	      }
//	  }

//	  @Bean
//	  public CommonsMultipartResolver multipartResolver() {
//	      CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//	      resolver.setDefaultEncoding("utf-8");
//	      return resolver;
//	  }

	@PostMapping("/addRepresentive")
	public ResponseEntity<PartyRepresentative> createPartyRepresentative(
			@RequestBody PartyRepresentative partyRepresentative)
			throws IOException {

//		// Setting up PartyRepresentative data
		String nextPRd = proccessNextIdService.autoIncrementReprsentiveiD();
//		// Upload image and set the image path
//		String imagePath = partyRepresentativeService.uploadImage(image);
		partyRepresentative.setImagePath("D:/Log");
		partyRepresentative.setPartyRepresentativeId(nextPRd);

		partyRepresentative.setStatus("N");
		partyRepresentative.setContactStatus("Active");
		partyRepresentative.setCreatedDate(new Date());
		partyRepresentative.setApprovedDate(new Date());

		reprsenetRepo.save(partyRepresentative);

		PartyRepresentative savedPartyRepresentative = partyRepresentativeService
				.createPartyRepresentative(partyRepresentative);

		return new ResponseEntity<>(savedPartyRepresentative, HttpStatus.CREATED);
	}
	@GetMapping("/{CompId}/{BranchId}/{cartingId}/getAllRepresentive")
	public ResponseEntity<List<PartyRepresentative>> getAllPartyRepresentativesOfCarting(
			@PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId,@PathVariable("cartingId") String cartingId
			
			) {
		List<PartyRepresentative> partyRepresentatives = partyRepresentativeService.findByCompanyIdAndBranchIdAndCartingAgentId(CompId,BranchId,cartingId);
		return ResponseEntity.ok(partyRepresentatives);
	}
	
	@GetMapping("/{CompId}/{BranchId}/getAllDataRepresentive")
	public ResponseEntity<List<PartyRepresentative>> getAllPartyRepresentatives(
			@PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId
			
			) {
		List<PartyRepresentative> partyRepresentatives = reprsenetRepo.findByCompanyIdAndBranchId(CompId,BranchId);
		return ResponseEntity.ok(partyRepresentatives);
	}
	
	@GetMapping("/{CompId}/{BranchId}/getAllCarting")
	public ResponseEntity<List<String>> findByCompanyIdAndBranchId(
			@PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId
			
			) {
		List<String> partyRepresentatives = partyRepresentativeService.findByCompanyIdAndBranchId(CompId,BranchId);
		return ResponseEntity.ok(partyRepresentatives);
	}
	
	@GetMapping("/{CompId}/{BranchId}/{Agent}/{reprentativeId}/get")
	public ResponseEntity<PartyRepresentative> findByCompanyIdAndBranchIdReprentative(
			@PathVariable("CompId") String CompId,
			@PathVariable("BranchId") String BranchId,
			@PathVariable("Agent") String Agent,
			@PathVariable("reprentativeId") String reprentativeId			
			) {
		PartyRepresentative partyRepresentatives = partyRepresentativeService.findPartyRepresentativeByCompanyIdAndBranchId(CompId, BranchId, Agent, reprentativeId);
		return ResponseEntity.ok(partyRepresentatives);
	}
	

	@GetMapping("/{partyRepresentativeId}")
	public PartyRepresentative getReprsenetativeById(@PathVariable String partyRepresentativeId) {
		return partyRepresentativeService.getPartyRepresentativeById(partyRepresentativeId);
	}

	@PutMapping("/update/{partyRepresentativeId}")
	public ResponseEntity<PartyRepresentative> updatePartyRepresentative(@PathVariable String partyRepresentativeId,
			@RequestBody PartyRepresentative updateRepresentative) {
		PartyRepresentative updateResult = partyRepresentativeService
				.updatePartyRepresentativeById(partyRepresentativeId, updateRepresentative);
		updateResult.setEditedDate(new Date());
		updateResult.setStatus("Edited");
		reprsenetRepo.save(updateResult);
		return new ResponseEntity<>(updateResult, HttpStatus.OK);

	}
//	  
//	  @DeleteMapping("/delete/{partyRepresentativeId}")
//		@Transactional
//		public ResponseEntity<Void> deletePartyById(@PathVariable String partyRepresentativeId) {
//			partyRepresentativeService.deletePartyRepresentiveById(partyRepresentativeId);
//			
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//		}

	@DeleteMapping("/delete/{partyRepresentativeId}")
	@Transactional
	public ResponseEntity<Void> deletePartyById(@PathVariable String partyRepresentativeId) {
		PartyRepresentative partyRepresentative = partyRepresentativeService
				.getPartyRepresentativeById(partyRepresentativeId);

		if (partyRepresentative != null) {
			partyRepresentative.setStatus("Deleted"); // Set the status to "inactive" or "deleted"
			partyRepresentativeService.updatePartyRepresentative(partyRepresentative);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//
//	    @GetMapping("/get/{imageName}")
//	    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
//	        byte[] imageData = partyRepresentativeService.getImageData(imageName);
//	        if (imageData != null) {
//	            return ResponseEntity.ok().body(imageData);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }

//	@GetMapping("/get/{imageName}")
//	public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
//		byte[] imageData = partyRepresentativeService.getImageData(imageName);
//		if (imageData != null) {
//			return ResponseEntity.ok().contentType(MediaType.ALL).body(imageData);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//		
		
		
		
		
		@PostMapping("/upload")
		public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
		    try {
		        String imagePath = partyRepresentativeService.uploadImage(image);
		        return ResponseEntity.ok(imagePath);
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
		    }
		}

		@GetMapping("/get/{imageName}")
		public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
		    byte[] imageData = partyRepresentativeService.getImageData(imageName);
		    if (imageData != null) {
		        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}
		
		@PostMapping("/addRepresentative")
		public ResponseEntity<PartyRepresentative> createPartyRepresentative(
		    @RequestParam("imagePath") MultipartFile image,
		    @ModelAttribute PartyRepresentative partyRepresentative
		) throws IOException {

		    String nextPRd = proccessNextIdService.autoIncrementReprsentiveiD();
		    partyRepresentative.setPartyRepresentativeId(nextPRd);

		    // Specify the absolute path to save the image
		    String savePath = "/path/to/your/upload/folder"; // Replace with the actual path
		    String fileName = image.getOriginalFilename();
		    Path path = Paths.get(savePath, fileName);
		    Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

		    // Save the image path in the database
		    partyRepresentative.setImagePath(path.toString()); // Store the full path

		    // Set other properties as needed
		    partyRepresentative.setStatus("New");
		    partyRepresentative.setContactStatus("Active");
		    partyRepresentative.setCreatedDate(new Date());
		    partyRepresentative.setApprovedDate(new Date());

		    // Save the party representative
		    PartyRepresentative savedPartyRepresentative = partyRepresentativeService.createPartyRepresentative(partyRepresentative);

		    return new ResponseEntity<>(savedPartyRepresentative, HttpStatus.CREATED);
		}




}