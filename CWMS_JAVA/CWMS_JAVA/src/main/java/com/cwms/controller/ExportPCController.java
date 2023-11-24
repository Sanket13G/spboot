package com.cwms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.ExportPC;
import com.cwms.repository.ExportPCRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/exportpc")
public class ExportPCController {

	@Autowired
	private ExportPCRepo exportpcrepo;
	
	@GetMapping("/alldata/{cid}/{bid}")
	public List<ExportPC> getalldata(@PathVariable("cid") String cid,@PathVariable("bid") String bid){
		return exportpcrepo.findalldata(cid, bid);
	}
	
	@GetMapping("/byid/{cid}/{bid}/{reqid}/{sbno}/{serno}")
	public ExportPC getdatabyid(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("reqid") String reqid,@PathVariable("sbno") String sbno,@PathVariable("serno") String serno) {
		return exportpcrepo.getdataById(cid, bid, reqid, sbno, serno);
	}
	
	@PostMapping("/savedata/{cid}/{bid}/{id}")
	public ExportPC savedata(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("id") String id ,@RequestBody ExportPC exportpc) {
		 exportpc.setCompanyId(cid);
		 exportpc.setBranchId(bid);
		exportpc.setApprovedBy(id);
		 exportpc.setApprovedDate(new Date());
		 return exportpcrepo.save(exportpc);
	}
}
