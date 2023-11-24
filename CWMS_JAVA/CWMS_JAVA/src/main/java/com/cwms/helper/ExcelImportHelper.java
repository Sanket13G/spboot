package com.cwms.helper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cwms.entities.ExternalParty;
import com.cwms.entities.Party;
import com.cwms.entities.User;
import com.cwms.repository.UserCreationRepository;
import com.cwms.repository.UserRepository;
import com.cwms.service.ExternalParty_Service;
import com.cwms.service.ProcessNextIdService;

import jakarta.mail.internet.ParseException;

@Component
public class ExcelImportHelper {

	@Autowired
	public ProcessNextIdService proccessNextIdService;
	
	@Autowired
	public UserCreationRepository userCreationRepository;
	
	@Autowired
	public ExternalParty_Service externalpartyService;
	
	
	@Autowired
	private UserRepository userrepo;
	
	
	 public static boolean checkFileFormat(MultipartFile file) {
	        String contentType = file.getContentType();
	        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") || contentType.equals("text/csv")) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	 
	

	public List<Party> convertFileToListOfParty(InputStream inputStream, String companyid, String branchId, String user_Id) throws Exception, UncheckedIOException {
	    InputStreamReader reader = new InputStreamReader(inputStream);
	    List<Party> list = new ArrayList<>();
	   
	    try {
	        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
	        
	        System.out.println("CSV Headers: " + csvParser.getHeaderNames());

	        for (CSVRecord record : csvParser) {
	        	Party party = new Party();
	        	
	        	String nextId = proccessNextIdService.autoIncrementNextIdNext();
	        	party.setPartyId(nextId);
	        	party.setCompanyId(companyid);
                party.setBranchId(branchId);
	        	
	        	
	        	String PartyName = record.get("PartyName");
	            String Email = record.get("Email");
	            String MobileNo = record.get("MobileNo");
	            String IECNo = record.get("IECNo");
	            String EntityId = record.get("EntityId");
	            String PanNo = record.get("PanNo");
	            String GstNo = record.get("GstNo");
	            String LoaNumber = record.get("LoaNumber");	
	        	Date currentDate = Calendar.getInstance().getTime();	        	
	        	String dateStr = record.get("LoaExpiryDate"); // Replace with your actual date string
	        	
	        	

	        	
//	        	if (dateStr != null && !dateStr.isEmpty()) {
//	        	    SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEEE, dd MMMM, yyyy", Locale.ENGLISH);
//					Date loaExpiryDate = inputDateFormat.parse(dateStr);
//					party.setLoaExpiryDate(loaExpiryDate); // Set the parsed date as a Date
//	        	} 
	        	
	        	
	        	ExternalParty  externalParty = new ExternalParty(); 		
	    		externalParty.setExternaluserId(nextId);
	    		externalParty.setCompanyId(companyid);
	    		externalParty.setBranchId(branchId);
	    		externalParty.setLoginPassword("Sanket@13");
	    		externalParty.setUserType("Party");
	    		externalParty.setStatus("N");
	    		externalParty.setCreatedBy(user_Id);
	    		externalParty.setApprovedBy(user_Id);
	    		externalParty.setCreatedDate(new Date());
	    		externalParty.setApprovedDate(new Date());		
	    		
	    		
	    		
	    		User externaluser = new User(); 
	    		
	    		externaluser.setCompany_Id(companyid);
	    		externaluser.setBranch_Id(branchId);
	    		externaluser.setUser_Type("Party");
	    		externaluser.setUser_Password("Sanket@13");	    		
	    		externaluser.setLogintype("Party");
	    		externaluser.setLogintypeid(nextId);
	    		externaluser.setCreated_Date(new Date());
	    		externaluser.setStatus("A");
	    		externaluser.setStop_Trans('N');
	    		externaluser.setOTP("1000");
	    				
	    		
	        	
	        	
	        	if (dateStr != null && !dateStr.isEmpty()) {
	        	    SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
	        	    Date loaExpiryDate = inputDateFormat.parse(dateStr);
	        	    party.setLoaExpiryDate(loaExpiryDate); // Set the parsed date as a Date
	        	}

	            String CreditLimit = record.get("CreditLimit");
	            
	            if (CreditLimit != null && !CreditLimit.isEmpty()) {
	            	  double creditLimit1 = Double.parseDouble(CreditLimit);	
	            	  party.setCreditLimit(creditLimit1);
	            }
	          
	            
	            String Status = record.get("Status");
	           
	           
	            if (Status != null && !Status.isEmpty()) {
	            	 String status1 = String.valueOf(Status.charAt(0));
	            	party.setStatus(status1);	
	            }
	            
	            
	            if (PartyName != null && !PartyName.isEmpty()) {
//	            	party.setPartyName(PartyName);
//	            	
//	            	externalParty.setUserName(PartyName);
//	            	externaluser.setUser_Name(PartyName);
	            	
	            	  party.setPartyName(PartyName.toUpperCase());
	            	    
	            	    externalParty.setUserName(PartyName.toUpperCase());
	            	    externaluser.setUser_Name(PartyName.toUpperCase());
	            }
	            
	            if (Email != null && !Email.isEmpty()) {
	            	   party.setEmail(Email);
	            	   externalParty.setEmail(Email);
	            	   externaluser.setUser_Id(Email);
	            	  externalParty.setLoginUserName(Email);
	            	  
	            	System.out.println(Email+"Email123");
	            	  
	            }
	            	            
	            if (MobileNo != null && !MobileNo.isEmpty()) {
	            	 party.setMobileNo(MobileNo);	 
	            	 externalParty.setMobile(MobileNo);
	            	 
	            }
	            if (IECNo != null && !IECNo.isEmpty()) {
	            	 party.setIecNo(IECNo);
	            }
	            if (EntityId != null && !EntityId.isEmpty()) {
	            	 party.setEntityId(EntityId);
	            }
	            if (PanNo != null && !PanNo.isEmpty()) {
	            	party.setPanNo(PanNo);
	            }
	           
	            if (GstNo != null || !GstNo.isEmpty()) {
	            	 party.setGstNo(GstNo);
	            }
	            if (LoaNumber != null || !LoaNumber.isEmpty()) {
	            	 party.setLoaNumber(LoaNumber);
	            }
	           
	           
                                  
                party.setCreatedBy(user_Id);               
                party.setCreatedDate(currentDate);
                party.setAddress1("Pune");
                party.setAddress2("Pune");
                party.setAddress3("Pune");
                party.setUnitAdminName(user_Id);
                party.setUnitType(user_Id);
                
	            list.add(party);
	            
	            externalpartyService.addExternalParty(externalParty);
	            userCreationRepository.save(externaluser);
	        }
	    } catch (UncheckedIOException uioe) {
	        uioe.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        reader.close();
	    }
	    return list;
	}
	
	
	
//	 public static class PartyError {
//	        private String partyName;
//	        private String parameterWithError;
//
//	        public PartyError(int rowNumber, String partyName, String parameterWithError) {
//	            this.partyName = partyName;
//	            this.parameterWithError = parameterWithError;
//	        }
//
//	        public String getPartyName() {
//	            return partyName;
//	        }
//
//	        public String getParameterWithError() {
//	            return parameterWithError;
//	        }
//	    }
//	 
//	 public static class ResultObject {
//	        private List<Party> parties;
//	        private List<PartyError> errors;
//
//	        public List<Party> getParties() {
//	            return parties;
//	        }
//
//	        public void setParties(List<Party> parties) {
//	            this.parties = parties;
//	        }
//
//	        public List<PartyError> getErrors() {
//	            return errors;
//	        }
//
//	        public void setErrors(List<PartyError> errors) {
//	            this.errors = errors;
//	        }
//	    }
//	
//
//	 public ExcelImportHelper.ResultObject convertFileToListOfParty(InputStream inputStream, String companyId, String branchId, String userId)
//	            throws Exception {
//	    InputStreamReader reader = new InputStreamReader(inputStream);
//	    List<Party> list = new ArrayList<>();
//	    List<PartyError> errorList = new ArrayList<>(); // To store party errors
//	    int rowNumber = 0; // Initialize row number counter
//
//	    try {
//	        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
//
//	        for (CSVRecord record : csvParser) {
//	            rowNumber++; // Increment row number for each record
//	            Party party = new Party();
//	          
//	            String nextId = proccessNextIdService.autoIncrementNextIdNext();
//	           
//	            party.setPartyId(nextId);
//	            party.setCompanyId(companyId);
//	            party.setBranchId(branchId);
//
//	            String partyName = record.get("PartyName");
//	            String email = record.get("Email");
//	            String mobileNo = record.get("MobileNo");
//	            String iecNo = record.get("IECNo");
//	            String entityId = record.get("EntityId");
//	            String panNo = record.get("PanNo");
//	            String gstNo = record.get("GstNo");
//	            String loaNumber = record.get("LoaNumber");
//	            Date currentDate = Calendar.getInstance().getTime();
//	            String dateStr = record.get("LoaExpiryDate");
//	            String creditLimit = record.get("CreditLimit");
//	            
//	            
//	            
//	            
//	            party.setPanNo(panNo);
//                party.setGstNo(gstNo);
//                party.setMobileNo(mobileNo);
//                
//                
//	            // Date format
//	            if (dateStr != null && !dateStr.isEmpty()) {
//	                SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEEE, dd MMMM, yyyy", Locale.ENGLISH);
//	                Date loaExpiryDate = inputDateFormat.parse(dateStr);
//	                party.setLoaExpiryDate(loaExpiryDate);
//	            } else {
//	            	Date loaExpiryDate = parse(dateStr);
//	            	 party.setLoaExpiryDate(loaExpiryDate);
//	            }
//
//	            // Credit limit exception check
//	            if (creditLimit != null && !creditLimit.isEmpty()) {
//	              
//	                    double creditLimit1 = Double.parseDouble(creditLimit);
//	                    party.setCreditLimit(creditLimit1);	               
//	            }                
//	            
//	                          
//	            // Party name  
//	            try {
//	                partyName = record.get("PartyName");
//	                if (partyName != null && !partyName.isEmpty()) {
//	                    party.setPartyName(partyName);
//	                } else {
//	                    errorList.add(new PartyError(rowNumber, "PartyName", partyName)); // Add error with row number and column name
//	                }
//	            } catch (Exception e) {
//	                errorList.add(new PartyError(rowNumber, "PartyName", null)); // Add error with row number and column name, with a null value for partyName
//	            }
//
//     
//	            
//	         // Email
//	            try {
//	                email = record.get("Email");
//	                if (email != null && !email.isEmpty()) {
//	                    party.setEmail(email);
//	                } else {
//	                    errorList.add(new PartyError(rowNumber, "Email", email)); // Add error with row number and column name
//	                }
//	            } catch (Exception e) {
//	                errorList.add(new PartyError(rowNumber, "Email", null)); // Add error with row number and column name, with a null value for email
//	            }
//
//	
//
//	         // IEC Number
//	            
//	            try {
//	                iecNo = record.get("IECNo");
//	                if (iecNo != null && !iecNo.isEmpty()) {
//	                    party.setIecNo(iecNo);
//	                } else {
//	                    errorList.add(new PartyError(rowNumber, "IECNo", iecNo)); // Add error with row number and column name
//	                }
//	            } catch (Exception e) {
//	                errorList.add(new PartyError(rowNumber, "IECNo", null)); // Add error with row number and column name, with a null value for iecNo
//	            }
//
//	            // Entity ID
//	            try {
//	                entityId = record.get("EntityId");
//	                if (entityId != null && !entityId.isEmpty()) {
//	                    party.setEntityId(entityId);
//	                } else {
//	                    errorList.add(new PartyError(rowNumber, "EntityId", entityId)); // Add error with row number and column name
//	                }
//	            } catch (Exception e) {
//	                errorList.add(new PartyError(rowNumber, "EntityId", null)); // Add error with row number and column name, with a null value for entityId
//	            }
//
// 
//	           
//	         // LOA Number
//	            
//	            try {
//	                loaNumber = record.get("LoaNumber");
//	                if (loaNumber != null && !loaNumber.isEmpty()) {
//	                    party.setLoaNumber(loaNumber);
//	                } else {
//	                    errorList.add(new PartyError(rowNumber, "LoaNumber", loaNumber)); // Add error with row number and column name
//	                }
//	            } catch (Exception e) {
//	                errorList.add(new PartyError(rowNumber, "LoaNumber", null)); // Add error with row number and column name, with a null value for loaNumber
//	            }
//
//
//	            
//	            // Status
//	            String status = record.get("Status");
//	            String status1 = status != null && !status.isEmpty() ? String.valueOf(status.charAt(0)) : null;                   
//	            if (status1 != null && !status1.isEmpty()) {
//	                party.setStatus(status1);
//	            }
//	            if (status1 == null || status1.isEmpty()) {
//	                party.setStatus("N");
//	            }
//	            
//	            
//	            // Default set values from backend
//	            
//	            party.setCreatedBy(userId);
//	            party.setCreatedDate(currentDate);
//	            party.setAddress1("Pune");
//	            party.setAddress2("Pune");
//	            party.setAddress3("Pune");
//	            party.setUnitAdminName(userId);
//	            party.setUnitType(userId);
//
//	            
//	                list.add(party);
//	            
//	        }
//	    } catch (UncheckedIOException uioe) {
//	        uioe.printStackTrace();
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    } finally {
//	        reader.close();
//	    }
//
//	    // Create and return a result object containing both the list of parties and the error list
//	    ExcelImportHelper.ResultObject result = new ExcelImportHelper.ResultObject();
//	    result.setParties(list);
//	    result.setErrors(errorList);
//	    return result;
//	}
//
//




	    
	
	



}
