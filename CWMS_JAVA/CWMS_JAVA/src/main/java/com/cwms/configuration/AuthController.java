package com.cwms.configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.Branch;
import com.cwms.entities.Company;
import com.cwms.entities.JwtRequest;
import com.cwms.entities.JwtResponse;
import com.cwms.entities.User;
import com.cwms.repository.BranchRepo;
import com.cwms.repository.CompanyRepo;
import com.cwms.repository.UserRepository;
import com.cwms.security.JwtHelper;
import com.cwms.service.UserServiceImpl;

@CrossOrigin("*")
@RequestMapping("/auth")
@RestController
public class AuthController {
	

	@Autowired
	public CompanyRepo companyRepo;
	
	
	@Autowired
	private BranchRepo brepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServiceImpl uservice;
	
	@Autowired
	public UserRepository repo;

	@Autowired
	private JwtHelper jwtHelper;

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("/number/{bid}/{uid}")
	public String sms(@PathVariable("bid") String bid, @PathVariable("uid") String uid) {

	    String otp = generateOTP(); // Make sure this matches the OTP you want to send

	    String num = repo.getmobileno(bid, uid);
	    User user = repo.findByUser_Idandbranch(uid, bid);
	    user.setOTP(otp);
	    this.repo.save(user);

	    try {
	        String apiKey = "apikey=" + URLEncoder.encode("N2E2ZjU4NmU1OTY5Njg2YjczNjI3OTMxNjg3MjQ4NjM=", "UTF-8");
	        String message = "Dear Sir, Please find your OTP " + otp + " for DGDC E-Custodian login.";
	        String sender = "sender=" + URLEncoder.encode("DGDCSZ", "UTF-8");
	        String numbers = "numbers=" + URLEncoder.encode("91" + num, "UTF-8");

	        // Send data
	        String data = "https://api.textlocal.in/send/?" + apiKey + "&" + numbers + "&message=" + URLEncoder.encode(message, "UTF-8") + "&" + sender;
	        URL url = new URL(data);
	        URLConnection conn = url.openConnection();
	        conn.setDoOutput(true);

	        // Get the response
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        StringBuilder sResult = new StringBuilder();
	        while ((line = rd.readLine()) != null) {
	            sResult.append(line).append(" ");
	        }
	        rd.close();

	        return sResult.toString();
	    } catch (Exception e) {
	        System.out.println("Error SMS " + e);
	        return "Error " + e;
	    }
	}
	
	private String generateOTP() {
	    Random random = new Random();
	    int otp = random.nextInt(9000) + 1000; // Generates a random number between 1000 and 9999
	    return String.valueOf(otp);
	}
	
	@PostMapping("/login/{otp}")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest, @RequestHeader("React-Page-Name") String reactPageName, @PathVariable("otp") String otp) throws Exception {
	    MDC.put("reactPageName", reactPageName);
	    try {
	        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

	        User user = (User) this.uservice.loadUserByUsername(jwtRequest.getUsername());

	        if (user.getStop_Trans() == 'Y') {
	            throw new Exception("User is not active");
	        }

	        if (user.getStatus() == "D") {
	            throw new Exception("User not exist");
	        }

	        if (!user.getStatus().equals("A")) {
	            throw new Exception("User not exist");
	        }

	        if (!user.getBranch_Id().equals(jwtRequest.getBranchid())) {
	            throw new Exception("Invalid branch for the user");
	        }

	        if (user.getOTP().equals(otp) || user.getDefaultotp().equals(otp)) {
	            // OTP matches either user's OTP or default OTP, allow login
	            // Retrieve the company ID and branch ID from the user
	            String userId = user.getUser_Name();
	            String companyId = user.getCompany_Id();
	            String branchId = user.getBranch_Id();
	            String role = user.getRole();

	            Company company = companyRepo.findByCompany_Id(companyId);
	            Branch branch = brepo.findByBranchId(branchId);

	            String companyname = company.getCompany_name();
	            String branchname = branch.getBranchName();
	            

	            User userDetails = (User) this.uservice.loadUserByUsername(jwtRequest.getUsername());

	            String token = this.jwtHelper.generateToken(userDetails);

	            JwtResponse jwtResponse = new JwtResponse(token, userDetails.getUsername(), userId, branchId, companyId, role, companyname, branchname,userDetails.getLogintype(),userDetails.getLogintypeid(),user.getUser_Type());

	            return ResponseEntity.ok(jwtResponse);
	        } else {
	            return ResponseEntity.status(400).body("Please enter correct otp..");
	        }
	    } catch (UsernameNotFoundException e) {
	        e.printStackTrace();
	        throw new Exception("User Not Found");
	    }
	}
	
	
	
	
	

	
	
//	
//	@PostMapping("/login")
//	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//	    try {
//	        authenticate(jwtRequest.getUser_Id(), jwtRequest.getPassword());
//
//	        User user = (User) this.uservice.loadUserByUserId(jwtRequest.getUser_Id());
//
//	        if (!user.getBranch_Id().equals(jwtRequest.getBranchid())) {
//	            throw new Exception("Invalid branch for the user");
//	        }
//
//	        // Retrieve the company ID and branch ID from the user
//	        String username = user.getUsername();
//	        String companyId = user.getId().getCompany_Id();
//	        String branchId = user.getBranch_Id();
//
//	        // Generate the token
//	        User userDetails = (User) this.uservice.loadUserByUserId(jwtRequest.getUser_Id());
//	        String token = this.jwtHelper.generateToken(userDetails);
//
//	        JwtResponse jwtResponse = new JwtResponse(token,username,userDetails.getId().getUser_Id(), companyId, branchId);
//
//	        return ResponseEntity.ok(jwtResponse);
//	    } catch (UsernameNotFoundException e) {
//	        e.printStackTrace();
//	        throw new Exception("User Not Found");
//	    }
//	}

	private void authenticate(String username, String password) throws Exception {

		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {

			throw new Exception("User Disable" + e.getMessage());

		} catch (BadCredentialsException e) {

			throw new Exception("Invalide credential" + e.getMessage());
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	}
	
	
	
}
