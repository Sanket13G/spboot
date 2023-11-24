package com.cwms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cwms.entities.Party;
import com.cwms.helper.ExcelImportHelper;
//import com.cwms.helper.ExcelImportHelper.PartyError;
import com.cwms.repository.PartyRepository;
import com.cwms.service.ExcelImportService;

@CrossOrigin("*")
@RestController
@RequestMapping("/excelupload")
public class ExcelImportController {
	
	
	
	    @Autowired
	    private ExcelImportService excelImportService;

	    @Autowired
	    private ExcelImportHelper excelImportHelper;

	    @Autowired
	    private PartyRepository partyRepository;
	


	  
//	  @PostMapping("/party/{companyid}/{branchId}/{user_Id}")
//	  public ResponseEntity<?> uploadCsvParty(@RequestParam("file") MultipartFile file, @PathVariable String companyid, @PathVariable String branchId, @PathVariable String user_Id) {
//	      if (excelImportHelper.checkFileFormat(file)) {
//	          try {
//	              List<Party> savedParties = this.excelImportService.uploadCsvParty(file, companyid, branchId, user_Id);
//	              return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db", "savedParties", savedParties));
//	          } catch (Exception e) {
//	              e.printStackTrace();
//	              // You might want to return an error response if an exception occurs during processing.
//	              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the CSV file.");
//	          }
//	      }
//	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an Excel file.");
//	  }
//	    
	  
	    @PostMapping("/party/{companyid}/{branchId}/{user_Id}")
	    public ResponseEntity<?> uploadCsvParty(@RequestParam("file") MultipartFile file, @PathVariable String companyid, @PathVariable String branchId, @PathVariable String user_Id) {
	        if (excelImportHelper.checkFileFormat(file)) {
	            try {
	                Map<String, List<Party>> result = this.excelImportService.uploadCsvParty(file, companyid, branchId, user_Id);
	                List<Party> savedParties = result.get("savedParties");
	                List<Party> errorParties = result.get("errorParties");

	                String message = "File is uploaded and data is saved to db";
	                if (!errorParties.isEmpty()) {
	                    message += ". Some parties could not be saved.";
	                }

	                Map<String, Object> response = new HashMap<>();
	                response.put("message", message);
	                response.put("savedParties", savedParties);
	                response.put("errorParties", errorParties);

	                return ResponseEntity.ok(response);
	            } catch (Exception e) {
	                e.printStackTrace();
	                // You might want to return an error response if an exception occurs during processing.
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the CSV file.");
	            }
	        }
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an Excel file.");
	    }

	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
//	    @PostMapping("/party/{companyid}/{branchId}/{user_Id}")
//	    public ResponseEntity<?> uploadCsvParty(@RequestParam("file") MultipartFile file, @PathVariable String companyid, @PathVariable String branchId, @PathVariable String user_Id) {
//	        if (excelImportHelper.checkFileFormat(file)) {
//	            try {
//	                Map<String, Object> response = this.excelImportService.uploadCsvParty(file, companyid, branchId, user_Id);
//	                List<Party> savedParties = (List<Party>) response.get("savedParties");
//	                List<PartyError> partyErrors = (List<PartyError>) response.get("partyErrors");
//
//	                if (!partyErrors.isEmpty()) {
//	                	 return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db", "savedParties", savedParties));
//	                }
//
//	                return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db", "savedParties", savedParties));
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	                // You might want to return an error response if an exception occurs during processing.
//	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the CSV file.");
//	            }
//	        }
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an Excel file.");
//	    }
	    
	  
	  
	  
//	    @PostMapping("/party/{companyid}/{branchId}/{user_Id}")
//	    public ResponseEntity<?> uploadCsvParty(@RequestParam("file") MultipartFile file, @PathVariable String companyid, @PathVariable String branchId, @PathVariable String user_Id) {
//	        if (!excelImportHelper.checkFileFormat(file)) {
//	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an Excel file.");
//	        }
//
//	        try {
//	            Map<String, Object> uploadResult = excelImportService.uploadCsvParty(file, companyid, branchId, user_Id);
//	            
//	            List<PartyError> partyErrors = (List<PartyError>) uploadResult.get("partyErrors");
//	            List<Party> savedParties = (List<Party>) uploadResult.get("savedParties");
//	         
//	            // Create a common response object
//	            Map<String, Object> response = new HashMap<>();
//	            response.put("message", "File is uploaded and data is saved to db");
//	            response.put("savedParties", savedParties);
//	            response.put("partyErrors", partyErrors);
//	            
//	            return ResponseEntity.ok(response);
//
//	        } catch (Exception e) {
//	            // Log the error and return an informative error response
//	            log.error("Error processing the CSV file.", e);
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the CSV file.");
//	        }
//	    }
  
	    
	    

}
