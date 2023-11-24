package com.cwms.service;

import java.util.List;
import com.cwms.entities.*;
import org.springframework.stereotype.Service;

@Service
public interface RepsentativeService 
{
	public	RepresentParty addrepresentative(RepresentParty RepresentParty);
	public List<RepresentParty> findAlRepositary(String companyId,String branchId,String userType);
	public List<RepresentParty> findAlRepositaryByUserID(String companyId,String branchId,String userId);
	public RepresentParty findByRepresentativeId(String companyId,String branchId,String userID,String representativeId);
	public List<RepresentParty> getall(String companyId,String branchId);
}
