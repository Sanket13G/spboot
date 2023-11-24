package com.cwms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.repository.UserRepository;
import com.cwms.service.UserServiceImpl;


@CrossOrigin("*")
@RestController
@RequestMapping("/api2")
public class UserController2 {

	@Autowired
	public UserServiceImpl uservice;
	
	@Autowired
	public UserRepository urepo;

	
    public BCryptPasswordEncoder bcrypt;
	
	
	
	private Logger logger = LoggerFactory.getLogger(UserController2.class);
	
//	@PostMapping("/signup")
//	public User createUser(@RequestBody User user) {
//		Usercompany ucompany = new Usercompany();
//		ucompany.setCompany_Id("C00001");
//		ucompany.setUser_Id("U00018");
//		
//		
//		user.setId(ucompany);
//		user.setBranch_Id(user.getBranch_Id());
//		user.setUsername(user.getUsername());
//		user.setPassword(this.bcrypt.encode(user.getPassword()));
//		user.setMapped_User(user.getMapped_User());
//		user.setUser_Email(user.getMapped_User());
//		user.setStop_Trans(user.getStop_Trans());
//		user.setComments(user.getComments());
//		user.setCreated_By(user.getCreated_By());
//		user.setCreated_Date(user.getCreated_Date());
//		user.setEdited_By(user.getEdited_By());
//		user.setEdited_Date(user.getEdited_Date());
//		user.setApproved_By(user.getApproved_By());
//		user.setApproved_Date(user.getApproved_Date());
//		user.setStatus(user.getStatus());
//		user.setRole(user.getRole());
//		
//		
//		return this.urepo.save(user);
//	}
	
	
}
