package com.cwms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cwms.repository.RepresentPartyRepository;
import com.cwms.entities.RepresentParty;
@Service
public class RepsentativeServiceIMPL implements RepsentativeService
{	
	@Autowired
	private RepresentPartyRepository RepresentativeRepositary;
	@Override
	public List<RepresentParty> findAlRepositary(String companyId, String branchId, String userType) {
		// TODO Auto-generated method stub
		return RepresentativeRepositary.findByCompanyIdAndBranchIdAndUserTypeAndUserStatusAndStatusNot(companyId, branchId, userType, "A", "D");
	}

	
	
	
	@Override
	public RepresentParty findByRepresentativeId(String companyId, String branchId,String Userid ,String representativeId) {
		// TODO Auto-generated method stub
		return RepresentativeRepositary.findByCompanyIdAndBranchIdAndAndUserIdAndRepresentativeId(companyId, branchId,Userid ,representativeId);
	}




	@Override
	public List<RepresentParty> findAlRepositaryByUserID(String companyId, String branchId, String userId) {
		// TODO Auto-generated method stub
		return RepresentativeRepositary.findByCompanyIdAndBranchIdAndUserIdAndUserStatusAndStatusNot(companyId, branchId, userId, "A", "D");
	}




	@Override
	public RepresentParty addrepresentative(RepresentParty RepresentParty) {
		// TODO Auto-generated method stub
		return RepresentativeRepositary.save(RepresentParty);
	}
	
	@Override
	public List<RepresentParty> getall(String companyId, String branchId) {
		// TODO Auto-generated method stub
		return RepresentativeRepositary.getAllrepresent(companyId, branchId);
	}

}
