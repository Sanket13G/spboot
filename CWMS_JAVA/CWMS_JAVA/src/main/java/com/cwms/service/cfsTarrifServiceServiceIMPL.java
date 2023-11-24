package com.cwms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cwms.entities.CFSTariffService;
import com.cwms.repository.cfsServicesRepo;
@Service
public class cfsTarrifServiceServiceIMPL implements cfsTarrifServiceService
{
	@Autowired
	public cfsServicesRepo cfsServicesRepo;

	@Override
	public CFSTariffService addcfsTarrifService(CFSTariffService CFSTariffService) {
		// TODO Auto-generated method stub
		return cfsServicesRepo.save(CFSTariffService);
	}

	@Override
	public CFSTariffService updatecfsTarrifService(CFSTariffService CFSTariffService) {
		// TODO Auto-generated method stub
		return cfsServicesRepo.save(CFSTariffService);
	}

	@Override
	public List<CFSTariffService> getcfsTarrifService(String CompId,String branchId) {
		// TODO Auto-generated method stub
		return cfsServicesRepo.findByCompanyIdAndBranchId(CompId,branchId);
	}

	@Override
	public List<CFSTariffService> getcfsTarrifServiceById(String cfstid,String CompId,String branchId) {
		// TODO Auto-generated method stub
		return cfsServicesRepo.findByCfsTariffNoAndCompanyIdAndBranchId(cfstid,CompId,branchId);
	}

		

	@Override
	public CFSTariffService findByTarrifNoandServiceId(String CompId,String branchId,String tarrifNo,String amndno,String ServiceId) 
	{
		
		return cfsServicesRepo.findByCompanyIdAndBranchIdAndCfsTariffNoAndCfsAmndNoAndServiceId(CompId,branchId,tarrifNo,amndno ,ServiceId);
	}

	@Override
	public Double findRateService(String CompId, String branchId, String TarrifNo, String amndno, String ServiceId,
			String partyId) 
	{
		
		return cfsServicesRepo.findRateService(CompId, branchId, TarrifNo, amndno, ServiceId, partyId);
	}
	
	@Override
	public CFSTariffService findByTarrifNoandServiceIdPartyId(String CompId,String branchId,String tarrifNo,String amndno,String ServiceId ,String partyId) 
	{
		
		return cfsServicesRepo.findByCompanyIdAndBranchIdAndCfsTariffNoAndCfsAmndNoAndServiceIdAndPartyId(CompId,branchId,tarrifNo,amndno ,ServiceId,partyId);
	}

	@Override
	public Double findRateServiceByTarrifNo(String CompId, String branchId, String TarrifNo, String amndno,
			String ServiceId) {
		// TODO Auto-generated method stub
		return cfsServicesRepo.findRateServiceByTarrifNo(CompId, branchId, TarrifNo, amndno, ServiceId);
	}

}