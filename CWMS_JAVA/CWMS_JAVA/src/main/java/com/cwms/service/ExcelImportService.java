package com.cwms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cwms.entities.Party;
import com.cwms.helper.ExcelImportHelper;
//import com.cwms.helper.ExcelImportHelper.PartyError;
//import com.cwms.helper.ExcelImportHelper.ResultObject;
import com.cwms.repository.PartyRepository;


@Service
public class ExcelImportService {

	
	@Autowired
    private ExcelImportHelper excelImportHelper;

    
    
    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    public ExcelImportService(ExcelImportHelper excelImportHelper) {
        this.excelImportHelper = excelImportHelper;
    }
	
    
    //working code 
	
//    public List<Party> uploadCsvParty(MultipartFile file, String companyid, String branchId, String user_Id) {
//        try {
//            List<Party> parties = excelImportHelper.convertFileToListOfParty(file.getInputStream(), companyid, branchId, user_Id);
//            
//            List<Party> savedParties = this.partyRepository.saveAll(parties);
//            
//            
//            return savedParties;
//        } catch (Exception e) {
//            e.printStackTrace();
//            // You might want to handle the exception differently, like returning an error response.
//            throw new RuntimeException("Failed to upload and save party data.");
//        }
//    }
	 
    
    
//    public Map<String, List<Party>> uploadCsvParty(MultipartFile file, String companyid, String branchId, String user_Id) {
//        try {
//            List<Party> parties = excelImportHelper.convertFileToListOfParty(file.getInputStream(), companyid, branchId, user_Id);
//            
//            List<Party> savedParties = new ArrayList<>();
//            List<Party> errorParties = new ArrayList<>();
//            
//            for (Party party : parties) {
//            	
//            	
//                try {
//                    Party savedParty = this.partyRepository.save(party);
//                    
//                    if(savedParty) {
//                    savedParties.add(savedParty);
//                    }
//                    else {
//                    	 errorParties.add(party);
//                    }
//                    
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.err.println("Error saving party: " + e.getMessage()); // Log the error message
//                    //errorParties.add(party);
//                }
//            }
//            
//            Map<String, List<Party>> result = new HashMap<>();
//            result.put("savedParties", savedParties);
//            result.put("errorParties", errorParties);
//            
//            
//            System.out.println(errorParties + "here ");
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//            // You might want to handle the exception differently, like returning an error response.
//            throw new RuntimeException("Failed to upload and save party data.");
//        }
//    }

    public Map<String, List<Party>> uploadCsvParty(MultipartFile file, String companyid, String branchId, String user_Id) {
        try {
            List<Party> parties = excelImportHelper.convertFileToListOfParty(file.getInputStream(), companyid, branchId, user_Id);
            
            List<Party> savedParties = new ArrayList<>();
            List<Party> errorParties = new ArrayList<>();
            
            for (Party party : parties) {
                try {
                    Party savedParty = this.partyRepository.save(party);
                    
                    if (savedParty != null) { // Check if the savedParty is not null
                        savedParties.add(savedParty);
                    } else {
                        errorParties.add(party);
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Error saving party=============================================================: " + e.getMessage()); // Log the error message
                    errorParties.add(party);
                }
            }
            
            Map<String, List<Party>> result = new HashMap<>();
            result.put("savedParties", savedParties);
            result.put("errorParties", errorParties);
            
            System.out.println(errorParties + "here ");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // You might want to handle the exception differently, like returning an error response.
            throw new RuntimeException("Failed to upload and save party data.");
        }
    }

    
    
    
    
    
    
    
    
    
    
    
//    public Map<String, Object> uploadCsvParty(MultipartFile file, String companyid, String branchId, String user_Id) {
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//            ResultObject partiesResult = excelImportHelper.convertFileToListOfParty(file.getInputStream(), companyid, branchId, user_Id);
//
//            List<Party> savedParties = this.partyRepository.saveAll(partiesResult.getParties());
//            List<PartyError> partyErrors = partiesResult.getErrors();
//
//            response.put("message", "File is uploaded and data is saved to db");
//            response.put("savedParties", savedParties);
//            response.put("partyErrors", partyErrors);
//
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//            // You might want to handle the exception differently, like returning an error response.
//            throw new RuntimeException("Failed to upload and save party data.");
//        }
//    }


	 
	 
	    public List<Party> getAllParties() {
	        return this.partyRepository.findAll();
	    }



	
	
	
	
	
	
	
	

}
