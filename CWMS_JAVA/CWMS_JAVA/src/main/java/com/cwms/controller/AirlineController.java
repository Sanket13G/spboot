package com.cwms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.Airline;
import com.cwms.repository.AirlineRepository;
import com.cwms.service.AirlineServiceImpliment;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin("*")
@RequestMapping("/Airline")
public class AirlineController {

	@Autowired
	AirlineServiceImpliment airlineServiceImpliment;

	@Autowired
	private AirlineRepository airlinerepo;

	@PostMapping(value = "/add")
	public Airline saveairline(@RequestBody Airline entity) {

		entity.setCurrentSystemDate();
		System.out.println(entity);

		return airlineServiceImpliment.createAirline(entity);

	}

	@GetMapping("/list/{companyId}/{branchId}")
	public List<Airline> getListAirline(@PathVariable("companyId") String companyId, @PathVariable("branchId") String branchId) {
		List<Airline> airlines = airlineServiceImpliment.getListAirlines(companyId, branchId);
		return airlines;
	}

//	@DeleteMapping(value = "/delete/{companyId}/{branchId}/{flightId}")
//	public void deleteMethodName(@PathVariable String companyId, @PathVariable String branchId,
//			@PathVariable String flightId) {
//
//		ResponseEntity<Airline> airlineResponse = getAirline(companyId, branchId, flightId);
//
//		if (airlineResponse.getStatusCode() == HttpStatus.OK) {
//			Airline airline = airlineResponse.getBody();
//			if (airline != null) {
//				airline.setStatus("D");
//				airlineServiceImpliment.createAirline(airline);
//
//			} else {
//				// return "Airline not found"; // Handle the case where the airline is not
//				// found.
//			}
//		} else {
//			// return "Error getting airline"; // Handle the case where there was an error
//			// getting the airline.
//		}
//	}

//	@DeleteMapping("/delete/{companyId}/{branchId}/{flightNo}")
//    public ResponseEntity<String> deleteAirline(
//            @PathVariable String companyId,
//            @PathVariable String branchId,
//            @PathVariable String flightNo) {
//
//        try {
//    		ResponseEntity<Airline> airlineResponse = getAirline(companyId, branchId, flightNo);
//
//    		if (airlineResponse.getStatusCode() == HttpStatus.OK)
//    		{
//    			Airline airline = airlineResponse.getBody();
//    			if (airline != null)
//    			{
//    				airline.setStatus("D");
//    				airlineServiceImpliment.createAirline(airline);
//    				  return ResponseEntity.ok("Airline deleted successfully");
//    				   
//    			}
//    		}
//    		
//    			else {
//    		        return ResponseEntity.status(500).body("airline is empty deleting airline: ");
//    		        	
//    			}
//    		} 
//    		catch (Exception e)
//    		{
//            return ResponseEntity.status(500).body("Error deleting airline: " + e.getMessage());
//        }
//		return null;
//    }

	 @PostMapping("/delete")
	    public ResponseEntity<String> deleteAirline(@RequestBody Airline selectedAirline) {
	        try {
	            Airline airline = airlinerepo.findbyID(
	                selectedAirline.getCompanyId(),
	                selectedAirline.getBranchId(),
	                selectedAirline.getflightNo()
	            );

	            if (airline != null) {
	                airline.setStatus("D");
	                airlineServiceImpliment.createAirline(airline);
	                return ResponseEntity.ok("Airline deleted successfully");
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Error deleting airline: " + e.getMessage());
	        }
	    }
	
//	@GetMapping("/find/{companyId}/{branchId}/{flightId}")
//	@Transactional
//	public ResponseEntity<Airline> getAirline(@PathVariable String companyId, @PathVariable String branchId,
//			@PathVariable String flightId) {
//		Optional<Airline> airline = airlineServiceImpliment.findAirline(flightId, branchId, companyId);
//
//		if (airline.isPresent()) {
//			return ResponseEntity.ok(airline.get());
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
	
	@GetMapping("/find/{companyId}/{branchId}/{flightId}")
	public Airline getAirline(@PathVariable String companyId, @PathVariable String branchId,
			@PathVariable String flightId) {
	 return  airlinerepo.findbyID(companyId, branchId, flightId);
	
	}

	@GetMapping("/find1/{companyId}/{branchId}/{flightId}")
	public Airline getAirline1(@PathVariable String companyId, @PathVariable String branchId,
			@PathVariable String flightId) {
	 return  airlinerepo.findbyAirlineID(companyId, branchId, flightId);
	
	}
	
	@GetMapping("/findByName/{companyId}/{branchId}/{airname}")
	public Airline getAirlineByName(@PathVariable("companyId") String companyId, @PathVariable("branchId") String branchId,
			@PathVariable("airname") String airname) {
	 return  airlinerepo.findByAirlineName(companyId, branchId, airname);
	
	}
	
	@GetMapping("/findByCode/{companyId}/{branchId}/{aircode}")
	public Airline getAirlineByCode(@PathVariable("companyId") String companyId, @PathVariable("branchId") String branchId,
			@PathVariable("aircode") String aircode) {
	 return  airlinerepo.findByAirlineCode(companyId, branchId, aircode);
	
	}
	@GetMapping("/findAirlineNameByCode/{companyId}/{branchId}/{aircode}")
	public String getAirlineNameByCode(@PathVariable("companyId") String companyId, @PathVariable("branchId") String branchId,
			@PathVariable("aircode") String aircode) {		
	 return  airlinerepo.findByNameAirlineCode(companyId, branchId, aircode);
	
	}
	
	@GetMapping("/findByShortName/{companyId}/{branchId}/{shortname}")
	public Airline getAirlineByShortName(@PathVariable("companyId") String companyId, @PathVariable("branchId") String branchId,
			@PathVariable("shortname") String shortname) {
	 return  airlinerepo.findByCompanyIdAndBranchIdAndAirlineShortNameAndStatusNot(companyId, branchId, shortname,"D");
	
	}
	
}