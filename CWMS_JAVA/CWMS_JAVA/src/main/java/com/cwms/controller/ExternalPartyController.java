package com.cwms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.ExternalParty;
import com.cwms.repository.ExternalPartyRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/externalparty")
public class ExternalPartyController {

	@Autowired
	private ExternalPartyRepository externalpartyrepo;
	
	@GetMapping("/alldata/{cid}/{bid}")
	public List<ExternalParty> getalldata(@PathVariable("cid") String cid,@PathVariable("bid") String bid){
		return externalpartyrepo.getalldata(cid, bid);
	}
	
	@GetMapping("/singledata/{cid}/{bid}/{eid}")
	public ExternalParty getdatabyexternalid(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("eid") String eid) {
		return this.externalpartyrepo.getalldatabyid(cid, bid, eid);
	}
	
	@GetMapping("/consoledata/{cid}/{bid}")
	public List<ExternalParty> getConsole(@PathVariable("cid") String cid,@PathVariable("bid") String bid){
		return externalpartyrepo.getallConsoledata(cid, bid);
	}
	
	@GetMapping("/cartingdata/{cid}/{bid}")
	public List<ExternalParty> getCartingAgent(@PathVariable("cid") String cid,@PathVariable("bid") String bid){
		return externalpartyrepo.getallCartingdata(cid, bid);
	}
	
	@GetMapping("/external-party/{cid}/{bid}")
    public List<ExternalParty> getExternalPartyData(
        @PathVariable("cid") String companyId,
        @PathVariable("bid") String branchId
    ) {
        // Call the repository method with the provided parameters
        return externalpartyrepo.getalldataExternalParties(companyId, branchId);
    }
	
	@GetMapping("/chadata/{cid}/{bid}")
	public List<ExternalParty> getCHA(@PathVariable("cid") String cid,@PathVariable("bid") String bid){
		return externalpartyrepo.getalldata(cid, bid);
	}
}
