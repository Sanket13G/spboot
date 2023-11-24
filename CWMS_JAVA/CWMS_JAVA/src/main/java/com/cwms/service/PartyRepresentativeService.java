package com.cwms.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cwms.entities.PartyRepresentative;
import com.cwms.repository.PartyRepresentativeRepo;

import jakarta.transaction.Transactional;

@Service
public class PartyRepresentativeService {

	@Autowired
	public PartyRepresentativeRepo representRepo;
	
	public List<PartyRepresentative> getAllPartyRepresentatives() {
        return representRepo.findAll();
    }

    
    public PartyRepresentative createPartyRepresentative(PartyRepresentative partyRepresentative) {
        return representRepo.save(partyRepresentative);
    }

    public PartyRepresentative updatePartyRepresentative(PartyRepresentative partyRepresentative) {
        return representRepo.save(partyRepresentative);
    }

//    public void deletePartyRepresentative(String id) {
//    	representRepo.deleteById(id);
//    }
    
    @Transactional
    public List<PartyRepresentative> findByCompanyIdAndBranchIdAndCartingAgentId(String compId,String branchId,String CartingId)
    {
    	return representRepo.findByCompanyIdAndBranchIdAndCartingAgent(compId,branchId,CartingId);
    }
    @Transactional
    public List<String> findByCompanyIdAndBranchId(String compId,String branchId)
    {
    	return representRepo.findUniqueCartingAgentByCompanyIdAndBranchId(compId, branchId);
    }
    @Transactional
    public PartyRepresentative findPartyRepresentativeByCompanyIdAndBranchId(String compId,String branchId,String CartingAgent,String reprentativeId)
    {
    	return representRepo.findByCompanyIdAndBranchIdAndCartingAgentAndPartyRepresentativeId(compId, branchId, CartingAgent, reprentativeId);
    }
    
    @Transactional
    public PartyRepresentative getPartyRepresentativeById(String partyRepresentativeId)
    {
    	return representRepo.findRepresentivBypartyRepresentativeId(partyRepresentativeId);
    }
    
    
	@Transactional
	public void deletePartyRepresentiveById(String partyRepresentativeId) {
		
		representRepo.deleteRepresentiveById(partyRepresentativeId);
	}
	
	public PartyRepresentative updatePartyRepresentativeById(String partyRepresentativeId, PartyRepresentative updatedPartyRepresentative) {
	    PartyRepresentative existingPartyRepresentative = representRepo.findRepresentivBypartyRepresentativeId(partyRepresentativeId);
	    
	    if (existingPartyRepresentative != null) {
	        // Update the fields that can be modified
	        existingPartyRepresentative.setPartyName(updatedPartyRepresentative.getPartyName());
	        existingPartyRepresentative.setContactName(updatedPartyRepresentative.getContactName());
	        existingPartyRepresentative.setMobileNo(updatedPartyRepresentative.getMobileNo());
//	        existingPartyRepresentative.setImagePath(updatedPartyRepresentative.getImagePath());
	        existingPartyRepresentative.setContactStatus(updatedPartyRepresentative.getContactStatus());
	        existingPartyRepresentative.setEditedBy(updatedPartyRepresentative.getEditedBy());
	        existingPartyRepresentative.setEditedDate(updatedPartyRepresentative.getEditedDate());
	        existingPartyRepresentative.setStatus(updatedPartyRepresentative.getStatus());
	        
	        
	        existingPartyRepresentative.setEditedDate(new Date());
	        return representRepo.save(existingPartyRepresentative);
	        
	       
	    } else {
	        // Handle case when the party representative with the given ID doesn't exist
	        throw new IllegalArgumentException("Party Representative with ID " + partyRepresentativeId + " not found");
	    }
	}
	
	
	
	
	 
//	    private String uploadDirectory; // Set this in your application.properties
//
//	    public String uploadImage(MultipartFile image) throws IOException {
//	        String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
//	        Path imagePath = Path.of(uploadDirectory, imageName);
//	        Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
//	        return imageName;
//	    }
//
//	    public byte[] getImageData(String imageName) {
//	        try {
//	            Path imagePath = Path.of(uploadDirectory, imageName);
//	            return Files.readAllBytes(imagePath);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	            return null;
//	        }
//	    }

	// In PartyRepresentativeService.java

	// Define the image upload directory in application.properties
	@Value("${spring.servlet.multipart.location}")
	 private String imageUploadDirectory;

	 public String uploadImage(MultipartFile image) throws IOException {
	     String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
	     Path imagePath = Paths.get(imageUploadDirectory, imageName);
	     Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
	     return imageName;
	 }

	 public byte[] getImageData(String imageName) {
	     try {
	         Path imagePath = Paths.get(imageUploadDirectory, imageName);
	         return Files.readAllBytes(imagePath);
	     } catch (IOException e) {
	         e.printStackTrace();
	         return null;
	     }
	 }

}