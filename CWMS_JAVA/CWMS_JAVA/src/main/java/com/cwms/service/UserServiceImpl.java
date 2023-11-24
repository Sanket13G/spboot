package com.cwms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cwms.entities.User;
import com.cwms.repository.BranchRepo;
import com.cwms.repository.UserRepository;

@Component
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	public UserRepository urepo;
	
	@Autowired
	public BranchRepo brepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = urepo.findByUser_Id(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with the provided username");
        }
        return  user;
	}
}
