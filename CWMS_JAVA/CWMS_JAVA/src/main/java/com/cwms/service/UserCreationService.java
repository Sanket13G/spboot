package com.cwms.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.cwms.entities.User;

@Service
public interface UserCreationService  {

	User saveUser(User userCreation);

	boolean deleteUser(String Userid);

	 List<User > getlist(String cid,String bid);
	 
	 
}
