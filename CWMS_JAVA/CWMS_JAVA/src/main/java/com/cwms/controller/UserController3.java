package com.cwms.controller;

import java.util.List;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.UserRights;
import com.cwms.repository.UserRightsrepo;

@CrossOrigin
@RestController
@RequestMapping("/api1")
public class UserController3 {

	@Autowired
	private UserRightsrepo urights;
	

	
	@GetMapping("/rights/{id}/{cid}/{bid}")
	public List<UserRights> getRightsById(@PathVariable("id") String id,@RequestHeader("React-Page-Name") String reactPageName,@PathVariable("cid") String cid,@PathVariable("bid") String bid){
		MDC.put("reactPageName", reactPageName);
		return urights.findByUserId(id,bid,cid);
	}
	
	
	@GetMapping("/rights2/{id}")
	public  UserRights getRightsById2(@PathVariable("id") String id,@RequestHeader("React-Page-Name") String reactPageName) {
		MDC.put("reactPageName", reactPageName);
		return urights.findByUserId2(id);
	}
}
