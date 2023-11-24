package com.cwms.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.Branch;
import com.cwms.entities.Company;
import com.cwms.entities.ExternalUserRights;
import com.cwms.entities.UpdateExternalUserRights;
import com.cwms.entities.UpdateUserRights;
import com.cwms.entities.User;
import com.cwms.entities.UserRights;
import com.cwms.repository.BranchRepo;
import com.cwms.repository.CompanyRepo;
import com.cwms.repository.ExternalUserRightsrepo;
import com.cwms.repository.UserRepository;
import com.cwms.repository.UserRightsrepo;
import com.cwms.service.ExternalUserRightsService;
import com.cwms.service.MenuServiceImpl;
import com.cwms.service.UserRightsService;

import jakarta.transaction.Transactional;


@CrossOrigin("*")
@RestController
@RequestMapping("/externaluserrights")
@ComponentScan("com.repo.all")
public class ExternalUserRightsController {



 private static final Logger logger = LoggerFactory.getLogger(UserController.class);

@Autowired
private CompanyRepo crepo;

@Autowired
private BranchRepo brepo;

@Autowired
private UserRepository urepo;

@Autowired
private ExternalUserRightsrepo urights;

@Autowired
private ExternalUserRightsService uservice;

@Autowired
private MenuServiceImpl menuservice;



@GetMapping("/company")
public List<Company> getCompanies(@RequestHeader("React-Page-Name") String reactPageName) {
	MDC.put("reactPageName", reactPageName);
	

	System.out.println(reactPageName);
    List<Company> companies = this.crepo.findAll();
   
    return companies;
}

@GetMapping("/branch")
public List<Branch> getBranchesByCompanyId(@RequestHeader("React-Page-Name") String reactPageName) {
	MDC.put("reactPageName", reactPageName);
	System.out.println(reactPageName);
	 try{
    List<Branch> branches = brepo.findAll();
  
    return branches;
	 }
	 finally {
		MDC.remove("controller");
	}
}

@GetMapping("/get-User/{id}/{cid}/{bid}")
public List<ExternalUserRights> getUserrights(@PathVariable("id") String id,@PathVariable("cid") String cid,@PathVariable("bid") String bid){
	return urights.findByExternalUserId(id,cid,bid);
	
}

@GetMapping("/getuser/{id}/{cid}/{bid}")
public List<ExternalUserRights> getExternalUserRightsbyId(@PathVariable("id") String id,@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
	
	  List<ExternalUserRights> getuserrights = urights.findByExternalUserId(id,cid,bid);
      
      // Format and log the SQL query with the parameter value
      String sqlQuery = "SELECT DISTINCT u.* FROM userinfo u LEFT JOIN userrights ur ON u.branch_id = ur.branch_id LEFT JOIN pmenu p ON ur.process_id = p.process_id LEFT JOIN cmenu c ON p.process_id = c.pprocess_id WHERE u.branch_id = '%s' AND role = 'ROLE_USER'";
      String formattedQuery = String.format(sqlQuery, id);
   
      System.out.println(getuserrights);
      return getuserrights;
}

@GetMapping("/mybranch/{id}")
public List<ExternalUserRights> getExternalUserByBranchId(@PathVariable("id") String id,@RequestHeader("React-Page-Name") String reactPageName) {
	 MDC.put("reactPageName", reactPageName);
    List<ExternalUserRights> userrights = this.urights.findBybid(id);
  
    return userrights;
}

//@GetMapping("ubranch/{id}/{cid}/{bid}")
//public ResponseEntity<List<User>> getUserByBranchId2(@PathVariable("id") String id,@RequestHeader("React-Page-Name") String reactPageName,@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
//	 MDC.put("reactPageName", reactPageName);
//    List<User> users = (List<User>) uservice.getUser(id,cid,bid);
//
//    if (users != null && !users.isEmpty()) {
//      
//        return ResponseEntity.ok(users);
//    } else {
//        return ResponseEntity.notFound().build();
//    }
//}

//@GetMapping("/u/{id}/{cid}/{bid}")
//public List<User> getUsersWithUserRightsByBranchId(@PathVariable("id") String id,@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
//	
//    List<User> user = this.urepo.findbyuid(id,cid,bid);
//    
//    return user;
//}

@PutMapping("/update-rights")
@Transactional
public void updateexternaluserrights(@RequestBody UpdateExternalUserRights request,@RequestHeader("React-Page-Name") String reactPageName) {
	 MDC.put("reactPageName", reactPageName);
    uservice.updateExternalUserrights(
        request.getApprove(), 
        request.getCreate(), 
        request.getDeleteRight(),
        request.getRead(), 
        request.getUpdate(), 
        request.getUserId(), 
        request.getProcessId()
    );
    
}

@PutMapping("/update-rights2")
@Transactional
public void updateexternaluserrights2(@RequestBody List<UpdateExternalUserRights> requests,@RequestHeader("React-Page-Name") String reactPageName) {
	 MDC.put("reactPageName", reactPageName);
    for (UpdateExternalUserRights request : requests) {
        uservice.updateExternalUserrights(
            request.getApprove(), 
            request.getCreate(), 
            request.getDeleteRight(),
            request.getRead(), 
            request.getUpdate(), 
            request.getUserId(), 
            request.getProcessId()
        );
     
    }
}

@GetMapping("/get-user/{id}/{cid}/{bid}")
public User getUserByUserId(@PathVariable("id") String id,@PathVariable("cid") String cid,@PathVariable("bid") String bid) {
	 
    User user = urepo.findByUserId(id,cid,bid);

    return user;
}

@PostMapping("/addRights")
public List<ExternalUserRights> insertByUid(@RequestBody List<ExternalUserRights> userRightsList,@RequestHeader("React-Page-Name") String reactPageName) {
	 MDC.put("reactPageName", reactPageName);
    List<ExternalUserRights> userrights = urights.saveAll(userRightsList);

    return userrights;
}

@PutMapping("/update/{userId}")
public ResponseEntity<String> updateExternalUserRights(@RequestBody List<ExternalUserRights> userRights, @PathVariable("userId") String userId) {
    try {
        uservice.updateExternalUserRights(userRights, userId);
        return ResponseEntity.ok("External User rights updated successfully!");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error updating External user rights!");
    }
}

//@PostMapping("/insert/{userId}")
//public ResponseEntity<String> insertExternalUserRights(@RequestBody List<ExternalUserRights> userRights, @PathVariable("userId") String userId) {
//    try {
//        uservice.updateExternalUserRights(userRights, userId);
//        return ResponseEntity.ok("External User rights inserted successfully!");
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("Error inserting External user rights!");
//    }
//}



    @PostMapping("/insert/{userId}")
    public ResponseEntity<String> insertExternalUserRights(
            @RequestBody List<ExternalUserRights> userRights,
            @PathVariable("userId") String userId) {
        try {
        	uservice.insertExternalUserRights(userRights, userId);
            return ResponseEntity.ok("External User rights inserted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inserting External user rights!");
        }
    }



@GetMapping("/getmenu/{id}")
public List<ExternalUserRights> getmenudatafromuserrights(@PathVariable("id") String id,@RequestHeader("React-Page-Name") String reactPageName) {
	 MDC.put("reactPageName", reactPageName);
    List<ExternalUserRights> user = this.urights.getMenuDataUsingExternalUserRihts(id);

    return user;
}

@GetMapping("/getallmenu/{userId}/{cid}/{bid}")
public List<ExternalUserRights> getUserRights(@PathVariable String userId,@PathVariable("cid") String cid,@PathVariable("bid") String bid) {

    List<ExternalUserRights> user = this.menuservice.getExternalUserRightsByUserId(userId,cid,bid);

    return user;
}

@GetMapping("/combined-data/{id}")
public ResponseEntity<List<Map<String, Object>>> getAllCombinedData(@PathVariable("id") String id) {
	
    List<Map<String, Object>> combinedData = menuservice.getAllParentAndChildMenus(id);

    return ResponseEntity.ok(combinedData);
}

@GetMapping("/status/{id}")
public ExternalUserRights getStatus(@PathVariable("id") String id) {
	return urights.getStatus(id);
}
}
