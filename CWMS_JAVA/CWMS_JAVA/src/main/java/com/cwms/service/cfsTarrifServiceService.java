package com.cwms.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cwms.entities.CFSTariffService;

@Service
public interface cfsTarrifServiceService 
{
	
	public CFSTariffService addcfsTarrifService(CFSTariffService CFSTariffService);
	public CFSTariffService updatecfsTarrifService(CFSTariffService CFSTariffService);
	public List<CFSTariffService> getcfsTarrifService(String CompId,String branchId);
	public List<CFSTariffService> getcfsTarrifServiceById(String CompId,String branchId,String cfstid);
	public CFSTariffService findByTarrifNoandServiceId(String CompId,String branchId,String TarrifNo,String amndno,String ServiceId);
	public Double findRateService(String CompId,String branchId,String TarrifNo,String amndno,String ServiceId,String partyId);
	CFSTariffService findByTarrifNoandServiceIdPartyId(String CompId, String branchId, String tarrifNo, String amndno,
			String ServiceId, String partyId);
	
	public Double findRateServiceByTarrifNo(String CompId,String branchId,String TarrifNo,String amndno,String ServiceId);
	
}