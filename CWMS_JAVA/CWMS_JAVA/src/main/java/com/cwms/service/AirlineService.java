package com.cwms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cwms.entities.Airline;
import com.cwms.repository.AirlineRepository;

@Service
public interface AirlineService {

	Airline createAirline(Airline airline);

	List<Airline> getListAirlines(String companyId, String BranchId);

	Optional<Airline> findAirline(String Fid, String bid, String cid);
//	Airline updateAirline(String id);

}