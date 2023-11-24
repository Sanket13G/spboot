package com.cwms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.DefaultPartyDetails;
import com.cwms.entities.ExportSub;
import com.cwms.entities.ImportSub;
import com.cwms.repository.DefaultParyDetailsRepository;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.ImportSubRepository;

@CrossOrigin
@RestController
@RequestMapping("/defaultparty")
public class DefaultPartyDetailsController {

	@Autowired
	public DefaultParyDetailsRepository defaultrepo;
	
	
	@Autowired
	private ImportSubRepository impsubrepo;
	
	@Autowired
	private ExportSubRepository expsubrepo;
	
	@GetMapping("/getdata/{cid}/{bid}/{uid}")
	public DefaultPartyDetails getdata(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("uid") String uid) {
		return defaultrepo.getdatabyuser_id(cid, bid, uid);
	}
	
	
	@PostMapping(value = "/SaveRecord")
	public DefaultPartyDetails SaveRecord(@RequestBody DefaultPartyDetails entity) {
		entity.setCurrentDate();
		System.out.println(entity);
		return defaultrepo.save(entity);
//		return entity;
	}
	
	
	
	@GetMapping(value="/findexpcha/{cid}/{bid}/{expcha}")
	public List<ExportSub> findoutexpcha(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("expcha") String expcha){
		List<DefaultPartyDetails> defaultparty = defaultrepo.findoutbyexportcha(cid,bid,expcha);
		List<ExportSub> exportsubdata = new ArrayList<>();
		for(DefaultPartyDetails defaultpartydetails : defaultparty) {
			List<ExportSub> findoutexportsub = expsubrepo.findExportSubByparty(cid,bid,defaultpartydetails.getUseId());
			System.out.println("findoutexportsub "+findoutexportsub);
			exportsubdata.addAll(findoutexportsub);
			
		}
		return exportsubdata;
	}
	
	
	@GetMapping(value="/findimpcha/{cid}/{bid}/{expcha}")
	public List<ImportSub> findoutimpcha(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("expcha") String expcha){
		List<DefaultPartyDetails> defaultparty = defaultrepo.findoutbyimportcha(cid,bid,expcha);
		List<ImportSub> exportsubdata = new ArrayList<>();
		for(DefaultPartyDetails defaultpartydetails : defaultparty) {
			List<ImportSub> findoutexportsub = impsubrepo.getalldatabyparty(cid,bid,defaultpartydetails.getUseId());
			System.out.println("findoutexportsub "+findoutexportsub);
			exportsubdata.addAll(findoutexportsub);
			
		}
		return exportsubdata;
	}
	
	
	
	@GetMapping(value="/findimpcha1/{cid}/{bid}/{expcha}")
	public List<ImportSub> findoutimpcha1(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("expcha") String expcha){
		List<DefaultPartyDetails> defaultparty = defaultrepo.findoutbyimportcha(cid,bid,expcha);
		List<ImportSub> exportsubdata = new ArrayList<>();
		for(DefaultPartyDetails defaultpartydetails : defaultparty) {
			List<ImportSub> findoutexportsub = impsubrepo.getalldatabyparty1(cid,bid,defaultpartydetails.getUseId());
			System.out.println("findoutexportsub "+findoutexportsub);
			exportsubdata.addAll(findoutexportsub);
			
		}
		return exportsubdata;
	}
}
