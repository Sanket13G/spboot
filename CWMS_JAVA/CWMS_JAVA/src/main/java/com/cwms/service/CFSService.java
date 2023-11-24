package com.cwms.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cwms.entities.CfsTarrif;

@Service
public interface CFSService
{
	public CfsTarrif addTarrifService(CfsTarrif CfsTarrif);
	public CfsTarrif updateTarrif(CfsTarrif CfsTarrif);
	public List<CfsTarrif> getTarrif(String compId,String branchId);
	public CfsTarrif getTarrifById(String cfstid,String compId,String branchId);
	public CfsTarrif getBypartyId(String compId,String branchId,String partyId);

}
