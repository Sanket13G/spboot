package com.cwms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.Airline;
import com.cwms.repository.AirlineRepository;

@Service
public class AirlineServiceImpliment implements AirlineService {

	@Autowired
	AirlineRepository airlineRepository;

	@Override
	public Airline createAirline(Airline airline) {
		// TODO Auto-generated method stub
		return airlineRepository.saveAndFlush(airline);
	}


	@Override
	public List<Airline> getListAirlines(String companyId, String branchid ) {
		// TODO Auto-generated method stub
		return airlineRepository.findByBranchIdOrCompanyId(branchid, companyId);
	}


	@Override
	public Optional<Airline> findAirline(String Fid, String bid, String cid) {
		// TODO Auto-generated method stub
		return airlineRepository.findByCompanyIdOrBranchIdOrFlightId(Fid, bid, cid);
	}


}