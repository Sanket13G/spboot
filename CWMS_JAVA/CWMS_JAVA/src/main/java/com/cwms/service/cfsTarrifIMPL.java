package com.cwms.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cwms.entities.CfsTarrif;
import com.cwms.repository.CFSRepositary;
@Service
public class cfsTarrifIMPL  implements CFSService
{
	@Autowired
    private CFSRepositary cfsRepositary;
	
	@Override
	public CfsTarrif addTarrifService(CfsTarrif CfsTarrif) 
	{
		
		return cfsRepositary.save(CfsTarrif);
	}

	@Override
	public CfsTarrif updateTarrif(CfsTarrif CfsTarrif) {
		
		return cfsRepositary.save(CfsTarrif);
	}

	@Override
	public List<CfsTarrif> getTarrif(String compId,String branchId) {
		
		return cfsRepositary.findByCompanyIdAndBranchId(compId,branchId);
	}

	
	@Override
	public CfsTarrif getTarrifById(String cfstid,String compId,String branchId) {
		return cfsRepositary.findByCfsTariffNoAndCompanyIdAndBranchId(cfstid,compId,branchId);
	}

	
	@Override
	public CfsTarrif getBypartyId(String compId, String branchId, String partyId) {
		// TODO Auto-generated method stub
		return cfsRepositary.findByCompanyIdAndBranchIdAndPartyId(compId, branchId, partyId);
	}

}
