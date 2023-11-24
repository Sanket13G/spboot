package com.cwms.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.ProcessNextId;
import com.cwms.repository.ProcessNextIdRepository;

import jakarta.transaction.Transactional;

@Service

public class ProcessNextIdService {
	
	@Autowired(required = true)
	public ProcessNextIdRepository processNextIdRepository;

	public List<ProcessNextId> getAllByProcessId(String processId) {
		return processNextIdRepository.findByProcessId(processId);
	}

	public List<ProcessNextId> getAllByProcessId() {
		return processNextIdRepository.findAll();
	}

	public ProcessNextId saveProcessNextId(ProcessNextId processNextId) {
		return processNextIdRepository.save(processNextId);
	}

//	public String autoIncrementProcessId() {
//
//		String maxProcessID = processNextIdRepository.findLastProcessId();
//
//		int lastNumericId = Integer.parseInt(maxProcessID.substring(1));
//
//		int nextNumericId = lastNumericId + 1;
//
//		String nextProcessId = String.format("P%05d", nextNumericId);
//
//		return nextProcessId;
//
//		// String processID="POOOO1";
//
//	}
//
//	public String autoIncrementNextId() {
//		String maxNextId = processNextIdRepository.findLastNextId();
//
//		int lastNextNumericId = Integer.parseInt(maxNextId.substring(4));
//
//		int nextNumericNextID = lastNextNumericId + 1;
//
//		String nextNextId = String.format("BKTQ%06d", nextNumericNextID);
//		return nextNextId;
//
//	}
	@Transactional
	public String autoIncrementNextIdNext() {
//		String NextId = processNextIdRepository.findNextId();
//
//		int lastNextNumericId = Integer.parseInt(NextId.substring(4));
//
//		int nextNumericNextID = lastNextNumericId + 1;
//
//		String NextIdD = String.format("H%05d", nextNumericNextID);
//		processNextIdRepository.updateNextId(NextIdD);
//		return NextIdD;
		
		
		String nextId = processNextIdRepository.findNextId();

        int lastNextNumericId = Integer.parseInt(nextId.substring(1));

        int nextNumericNextID = lastNextNumericId + 1;

        String nextIdD = String.format("M%05d", nextNumericNextID);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNextId(nextIdD);

        return nextIdD;

	}
	
	@Transactional
	public String autoIncrementNextIdHoliday() {
//		String NextId = processNextIdRepository.findNextId();
//
//		int lastNextNumericId = Integer.parseInt(NextId.substring(4));
//
//		int nextNumericNextID = lastNextNumericId + 1;
//
//		String NextIdD = String.format("H%05d", nextNumericNextID);
//		processNextIdRepository.updateNextId(NextIdD);
//		return NextIdD;
		
		
		String nextholi = processNextIdRepository.findNextIdforHoliday();

        int lastNextNumericIdh = Integer.parseInt(nextholi.substring(1));

        int nextNumericNextIDh = lastNextNumericIdh + 1;

        String nextIdDholi = String.format("H%05d", nextNumericNextIDh);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNextIdforHoliday(nextIdDholi);

        return nextIdDholi;

	}

	@Transactional
	 public synchronized String autoIncrementMailId() {
	  
	  String nextMailId = processNextIdRepository.findNextMailId();

	        int lastNextNumericId = Integer.parseInt(nextMailId.substring(4));

	        int nextNumericNextID = lastNextNumericId + 1;

	        String MailId = String.format("MAIL%06d", nextNumericNextID);
	        // Update the Next_Id directly in the database using the repository
	        processNextIdRepository.updateNextMailId(MailId);

	        return MailId;

	 }

	@Transactional
	public String autoIncrementNextJarIdNext() {

		
		String nextJarId = processNextIdRepository.findNextJarId();

        int lastNextNumericId = Integer.parseInt(nextJarId.substring(1));

        int nextNumericNextID = lastNextNumericId + 1;

        String nextJD = String.format("J%05d", nextNumericNextID);

        processNextIdRepository.updateNextJarId(nextJD);

        return nextJD;

	}


	
	@Transactional
	public synchronized String autoIncrementReprsentiveiD() {
		
		String nextReId = processNextIdRepository.findNextReId();

        int lastNextNumericId = Integer.parseInt(nextReId.substring(1));

        int nextNumericNextID = lastNextNumericId + 1;

        String ReId = String.format("R%05d", nextNumericNextID);
        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNextReId(ReId);

        return ReId;

	}
	
	
	
	@Transactional
	public String autoIncrementServiceNextId() {
		
		String serviceId = processNextIdRepository.findNextServiceId();

        int lastNextNumericId = Integer.parseInt(serviceId.substring(1));

        int nextNumericNextID = lastNextNumericId + 1;

        String serviceNextIdD = String.format("S%05d", nextNumericNextID);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNextServiceId(serviceNextIdD);

        return serviceNextIdD;

	}
	
	@Transactional
	public String autoIncrementCFSTarrifNextId() {
		
		String CFSTarrifNo = processNextIdRepository.findNextCFSTarrifNo();

        int lastNextNumericId = Integer.parseInt(CFSTarrifNo.substring(4));

        int nextNumericNextID = lastNextNumericId + 1;

        String CFSTTArrifNextIdD = String.format("CFST%06d", nextNumericNextID);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNextCFSTarrifNo(CFSTTArrifNextIdD);

        return CFSTTArrifNextIdD;

	}
	
	@Transactional
	public String autoIncrementSIRId() {
		
		String SIRNo = processNextIdRepository.findNextSIRNo();

        int lastNextNumericId = Integer.parseInt(SIRNo.substring(2));

        int nextNumericNextID = lastNextNumericId + 1;

        String NextSIRNo = String.format("IM%06d", nextNumericNextID);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNextSIRNo(NextSIRNo);

        return NextSIRNo;

	}
	
	
	@Transactional
	public String autoIncrementIMPTransId() {
		
		String IMPTransId = processNextIdRepository.findNextimpTransId();

        int lastNextNumericId = Integer.parseInt(IMPTransId.substring(4));

        int nextNumericNextID = lastNextNumericId + 1;

        String NextimpTransId = String.format("IMPT%04d", nextNumericNextID);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNextimpTransId(NextimpTransId);

        return NextimpTransId;

	}
	
	
	@Transactional
	public String autoIncrementSubImpId( ) {
		
		String IMPTransId = processNextIdRepository.findNextsubimpid();

        int lastNextNumericId = Integer.parseInt(IMPTransId.substring(4));

        int nextNumericNextID = lastNextNumericId + 1;

        String NextimpTransId = String.format("D-IM%06d", nextNumericNextID);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNexsubimpid(NextimpTransId);

        return NextimpTransId;

	}
	
	@Transactional
	public String autoIncrementSIRExportId() {
		
		String SIRNo = processNextIdRepository.findNextSIRExportNo();

        int lastNextNumericId = Integer.parseInt(SIRNo.substring(2));

        int nextNumericNextID = lastNextNumericId + 1;

        String NextSIRNo = String.format("EX%06d", nextNumericNextID);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNextSIRExportNo(NextSIRNo);

        return NextSIRNo;

	}
	
	
	@Transactional
	public String autoIncrementSubImpTransId( ) {
		
		String IMPTransId = processNextIdRepository.findNextsubimptransid();

        int lastNextNumericId = Integer.parseInt(IMPTransId.substring(3));

        int nextNumericNextID = lastNextNumericId + 1;

        String NextimpTransId = String.format("SIM%05d", nextNumericNextID);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNexsubimptransid(NextimpTransId);

        return NextimpTransId;

	}
	
	@Transactional
	public String autoIncrementSubExpId( ) {
		
		String IMPTransId = processNextIdRepository.findNextsubexptransid();

        int lastNextNumericId = Integer.parseInt(IMPTransId.substring(4));

        int nextNumericNextID = lastNextNumericId + 1;

        String NextimpTransId = String.format("D-EX%06d", nextNumericNextID);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNexsubexpid(NextimpTransId);

        return NextimpTransId;

	}
	
	@Transactional
	public String autoIncrementSubExpTransId( ) {
		
		String IMPTransId = processNextIdRepository.findNextsubexptransid();

        int lastNextNumericId = Integer.parseInt(IMPTransId.substring(3));

        int nextNumericNextID = lastNextNumericId + 1;

        String NextimpTransId = String.format("SER%05d", nextNumericNextID);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNexsubexptransid(NextimpTransId);

        return NextimpTransId;

	}
	
	
	
	
	
	
	private static final String COMMON_PREFIX = "000";
	private static final String FIXED_PART = "/23-24";
    private static final String SEPARATOR = "/";
    private static final String PREFIX = COMMON_PREFIX + SEPARATOR;
                                                          
	@Transactional
	public synchronized String generateAndIncrementPCTMNumber() {
	    String nextPCTMNumber = processNextIdRepository.findNextPctmNo();

	    // Extract the fixed part from the constant
	    String fixedPart = FIXED_PART;

	    // Extract and increment the numeric part
	    int lastNumericPart = extractNumericPart(nextPCTMNumber);
	    int nextNumericPart = lastNumericPart + 1;

	    // Format the numeric part with zero-padding
	    String formattedNumericPart = formatNumericPart(nextNumericPart);

	    // Combine the formatted numeric part, slash, and the rest of the string to create the new PCTM number
	    String newPCTMNumber = formattedNumericPart + "/" + nextPCTMNumber.substring(9);

	    // Update the Next_PCTM_Number directly in the database using the repository
	    processNextIdRepository.updateNextPctmNo(newPCTMNumber);

	    return newPCTMNumber;
	}

	public int extractNumericPart(String pctmNumber) throws NumberFormatException {
	    // Use a regular expression to match the expected format "00000000/23-24"
	    Pattern pattern = Pattern.compile("(\\d{8}/\\d{2}-\\d{2})");
	    Matcher matcher = pattern.matcher(pctmNumber);

	    if (!matcher.matches()) {
	        throw new NumberFormatException("Invalid PCTM Number format: " + pctmNumber);
	    }

	    // Extract the numeric part
	    String numericPart = pctmNumber.substring(0, 8); // Assuming "00000000/23-24", this extracts "00000000"

	    return Integer.parseInt(numericPart);
	}

	private String formatNumericPart(int numericPart) {
	    return String.format("%08d", numericPart); // Assuming you want an 8-digit zero-padded numeric part
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Transactional
	public synchronized String generateAndIncrementTPumber() {
	    String nextTPumber = processNextIdRepository.findNexttpNo();

	    // Extract the fixed part from the constant
	    String fixedPart = FIXED_PART;

	    // Extract and increment the numeric part
	    int lastNumericPartTp = extractNumericPart(nextTPumber);
	    int nextNumericPartTP = lastNumericPartTp + 1;

	    // Format the numeric part with zero-padding
	    String formattedNumericPart = formatNumericPart(nextNumericPartTP);

	    // Combine the formatted numeric part, slash, and the rest of the string to create the new PCTM number
	    String newTPNumber = formattedNumericPart + "/" + nextTPumber.substring(9);

	    // Update the Next_PCTM_Number directly in the database using the repository
	    processNextIdRepository.updateNexttpNo(newTPNumber);

	    return newTPNumber;
	}

	public int extractNumericPartOfTp(String tpNumber) throws NumberFormatException {
	    // Use a regular expression to match the expected format "00000000/23-24"
	    Pattern pattern = Pattern.compile("(\\d{8}/\\d{2}-\\d{2})");
	    Matcher matcher = pattern.matcher(tpNumber);

	    if (!matcher.matches()) {
	        throw new NumberFormatException("Invalid PCTM Number format: " + tpNumber);
	    }

	    // Extract the numeric part
	    String numericPartTP = tpNumber.substring(0, 8); // Assuming "00000000/23-24", this extracts "00000000"

	    return Integer.parseInt(numericPartTP);
	}

	private String formatNumericPartTP(int numericPartTP) {
	    return String.format("%08d", numericPartTP); // Assuming you want an 8-digit zero-padded numeric part
	}
	
	
	
	@Transactional
	public String autoIncrementExternalUserId() {
		
		String SIRNo = processNextIdRepository.findNextexternalUserId();

        int lastNextNumericId = Integer.parseInt(SIRNo.substring(2));

        int nextNumericNextID = lastNextNumericId + 1;

        String NextSIRNo = String.format("EU%04d", nextNumericNextID);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNextexternalUserId(NextSIRNo);

        return NextSIRNo;

	}
	
	@Transactional
	public String autoIncrementDetentionId( ) {
		
		String detentionId = processNextIdRepository.findDetentionId();

        int lastNextNumericId = Integer.parseInt(detentionId.substring(1));

        int nextNumericDeten = lastNextNumericId + 1;

        String NextidetentionId = String.format("D%09d", nextNumericDeten);

        // Update the Next_Id directly in the database using the repository
        processNextIdRepository.updateNextDetentionId(NextidetentionId);

        return NextidetentionId;

	}
	
	@Transactional
	public String autoIncrementDoNumber() {

		String doNumber = processNextIdRepository.findDoNumber();

		int lastNextNumericId = Integer.parseInt(doNumber);
		lastNextNumericId = lastNextNumericId+1;
		String nextDoNumber = String.valueOf(lastNextNumericId);

		// Update the Next_Id directly in the database using the repository
		processNextIdRepository.updateNextDoNumber(nextDoNumber);

		return doNumber;

	}
	
	 public String getNextPctmNo() {
	        // Get the last value from the database
	        String lastValue = processNextIdRepository.findNextEXPPctmNo();

	        // Get the current date
	        LocalDate currentDate = LocalDate.now();

	        // Determine the year part based on the current date
	        String yearPart = determineYearPart(currentDate);

	        // Increment the last value
	        String nextValue = incrementPctmNo(lastValue, yearPart);

	        // Update the database with the new value
	        processNextIdRepository.updateNextEXPPctmNo(nextValue);

	        return nextValue;
	    }

	 private String determineYearPart(LocalDate currentDate) {
		    LocalDate april1 = LocalDate.of(currentDate.getYear(), Month.APRIL, 1);

		    if (currentDate.isAfter(april1) || currentDate.isEqual(april1)) {
		        // If the current date is on or after April 1, use the current year and the next year
		        return (currentDate.getYear() % 100) + "-" + ((currentDate.getYear() % 100) + 1);
		    } else {
		        // If the current date is before April 1, use the previous year and the current year
		        return ((currentDate.getYear() - 1) % 100) + "-" + (currentDate.getYear() % 100);
		    }
		}


	    private String incrementPctmNo(String lastValue, String yearPart) {
	        if (lastValue == null || lastValue.isEmpty()) {
	            // Handle the case where the last value is not found or empty
	            // You can start with the initial value, e.g., "000000/23-24"
	            return "000000/" + yearPart;
	        }

	        // Split the last value into parts
	        String[] parts = lastValue.split("/");

	        if (parts.length != 2) {
	            // Handle the case where the last value is not in the expected format
	            // You can throw an exception or handle it as needed
	            throw new IllegalArgumentException("Invalid last value format: " + lastValue);
	        }

	        int numericPart = Integer.parseInt(parts[0]);

	        // Increment the numeric part
	        numericPart++;

	        // Format the incremented value
	        String incrementedValue = String.format("%d/%s", numericPart, yearPart);

	        return incrementedValue;
	    }
	    
		
		@Transactional
		public String autoIncrementGateInId( ) {
			
			String detentionId = processNextIdRepository.findGateInId();

	        int lastNextNumericId = Integer.parseInt(detentionId.substring(2));

	        int nextNumericDeten = lastNextNumericId + 1;

	        String NextidetentionId = String.format("GI%04d", nextNumericDeten);

	        // Update the Next_Id directly in the database using the repository
	        processNextIdRepository.updateGateInId(NextidetentionId);

	        return NextidetentionId;

		}
	
	    
	    public String getNextTPNo() {
	        // Get the last value from the database
	        String lastValue = processNextIdRepository.findNextEXPtpNo();

	        // Get the current date
	        LocalDate currentDate = LocalDate.now();

	        // Determine the year part based on the current date
	        String yearPart = determineYearPart(currentDate);

	        // Increment the last value
	        String nextValue = incrementTPNo(lastValue, yearPart);

	        // Update the database with the new value
	        processNextIdRepository.updateNextEXPtpNo(nextValue);

	        return nextValue;
	    }



	    private String incrementTPNo(String lastValue, String yearPart) {
	        if (lastValue == null || lastValue.isEmpty()) {
	            // Handle the case where the last value is not found or empty
	            // You can start with the initial value, e.g., "000000/23-24"
	            return "000000/" + yearPart;
	        }

	        // Split the last value into parts
	        String[] parts = lastValue.split("/");

	        if (parts.length != 2) {
	            // Handle the case where the last value is not in the expected format
	            // You can throw an exception or handle it as needed
	            throw new IllegalArgumentException("Invalid last value format: " + lastValue);
	        }

	        int numericPart = Integer.parseInt(parts[0]);

	        // Increment the numeric part
	        numericPart++;

	        // Format the incremented value
	        String incrementedValue = String.format("%d/%s", numericPart, yearPart);

	        return incrementedValue;
	    }
	    
	    @Transactional
		public String autoIncrementInvoiceNumber() {
			
			String SIRNo = processNextIdRepository.findNextInvoiceNumber();

	        int lastNextNumericId = Integer.parseInt(SIRNo.substring(2));

	        int nextNumericNextID = lastNextNumericId + 1;

	        String NextInvoiceNumber = String.format("IN%04d", nextNumericNextID);

	        // Update the Next_Id directly in the database using the repository
	        processNextIdRepository.updateNextextInvoiceNumber(NextInvoiceNumber);

	        return NextInvoiceNumber;

		}
		
//		 Bill Number
		
		@Transactional
		public String autoIncrementBillNumber() {
			
			String SIRNo = processNextIdRepository.findNextBillNumber();

	        int lastNextNumericId = Integer.parseInt(SIRNo.substring(2));

	        int nextNumericNextID = lastNextNumericId + 1;

	        String NextBillNumber = String.format("BL%04d", nextNumericNextID);

	        // Update the Next_Id directly in the database using the repository
	        processNextIdRepository.updateNextextBillNumber(NextBillNumber);

	        return NextBillNumber;

		}
		
		
		@Transactional
		public String autoIncrementRepresentativePartyId() {

			
			String nextRepresentativePartyId = processNextIdRepository.findNextRepresentativePartyId();

	        int lastNextNumericId = Integer.parseInt(nextRepresentativePartyId.substring(2));

	        int nextNumericNextID = lastNextNumericId + 1;

	        String nextID = String.format("RI%04d", nextNumericNextID);

	        processNextIdRepository.updateNextRepresentativePartyId(nextID);

	        return nextID;

		}	
}
