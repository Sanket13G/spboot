package com.cwms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.User;
import com.cwms.repository.UserCreationRepository;

@Service
public class UserCreationServiceImpliment implements UserCreationService{

	@Autowired
	UserCreationRepository userCreationRepository;
	
	@Override
	public User saveUser(User userCreation) {
	        return userCreationRepository.save(userCreation);
	    }

	@Override
	public List<User> getlist(String cid,String bid) {
		// TODO Auto-generated method stub
		return userCreationRepository.findAlldata(cid,bid);
	}

	@Override
	
	public boolean deleteUser(String Userid) {
		
		User userCreation= userCreationRepository.findByUser_Id(Userid);
		if (userCreation!=null) {
			userCreationRepository.delete(userCreation);
			return true;
		}
		return false;
	}
	
}
