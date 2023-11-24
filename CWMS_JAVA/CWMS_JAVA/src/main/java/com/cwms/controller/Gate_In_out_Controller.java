package com.cwms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.Export;
import com.cwms.entities.ExportSub;
import com.cwms.entities.Gate_In_Out;
import com.cwms.entities.Import;
import com.cwms.entities.ImportSub;
import com.cwms.repository.ExportRepository;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.Gate_In_out_Repo;
import com.cwms.repository.ImportRepo;
import com.cwms.repository.ImportSubRepository;
@CrossOrigin("*")
@RestController
@RequestMapping("/gateinout")
public class Gate_In_out_Controller {
     @Autowired
	private Gate_In_out_Repo gateinoutrepo;
     
     @Autowired
     private ExportSubRepository exportsubrepo;
     
     @Autowired
 	private ImportSubRepository impsubrepo;
     
 	@Autowired
 	private ExportRepository exportRepository;
 	
	@Autowired
	private ImportRepo importRepo;
     
     @PostMapping("/saveexpsub/{cid}/{bid}/{req}/{ser}")
     public String savemultidata(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("req") String req,@PathVariable("ser") String ser) {
        ExportSub exportsub = exportsubrepo.findExportSubByser(cid, bid, ser, req);
        if(exportsub.getNoc() == 0) {
        	for(int i=1;i<=exportsub.getNop();i++) {
        		String srNo = String.format("%04d", i);
        		Gate_In_Out gateinout = new Gate_In_Out();
        		gateinout.setCompanyId(cid);
        		gateinout.setBranchId(bid);
        		gateinout.setNop(exportsub.getNop());
        		gateinout.setErp_doc_ref_no(exportsub.getRequestId());
        		gateinout.setDoc_ref_no(exportsub.getSerNo());
        		gateinout.setSr_No(exportsub.getSerNo()+srNo);
        		gateinout.setDgdc_cargo_in_scan("N");
        		gateinout.setDgdc_cargo_out_scan("N");
        		gateinout.setDgdc_seepz_in_scan("N");
        		gateinout.setDgdc_seepz_out_scan("N");
        		
        		gateinoutrepo.save(gateinout);
        	}
        	
        	exportsub.setNoc(exportsub.getNoc()+1);
        	exportsubrepo.save(exportsub);
        }
        else {
        	exportsub.setNoc(exportsub.getNoc()+1);
        	exportsubrepo.save(exportsub);
        }
    	 
      return "success";
     }
     
     @PostMapping("/saveimpsub/{cid}/{bid}/{req}/{ser}")
     public String savemultidata1(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("req") String req,@PathVariable("ser") String ser) {
        ImportSub exportsub = impsubrepo.findImportSubBysir(cid, bid, ser, req);
        if(exportsub.getNoc() == 0) {
        	for(int i=1;i<=exportsub.getNop();i++) {
        		String srNo = String.format("%04d", i);
        		Gate_In_Out gateinout = new Gate_In_Out();
        		gateinout.setCompanyId(cid);
        		gateinout.setBranchId(bid);
        		gateinout.setNop(exportsub.getNop());
        		gateinout.setErp_doc_ref_no(exportsub.getRequestId());
        		gateinout.setDoc_ref_no(exportsub.getSirNo());
        		gateinout.setSr_No(exportsub.getSirNo()+srNo);
        		gateinout.setDgdc_cargo_in_scan("N");
        		gateinout.setDgdc_cargo_out_scan("N");
        		gateinout.setDgdc_seepz_in_scan("N");
        		gateinout.setDgdc_seepz_out_scan("N");
        		
        		gateinoutrepo.save(gateinout);
        	}
        	
        	exportsub.setNoc(exportsub.getNoc()+1);
        	impsubrepo.save(exportsub);
        }
        else {
        	exportsub.setNoc(exportsub.getNoc()+1);
        	impsubrepo.save(exportsub);
        }
    	 
      return "success";
     }
     
     @PostMapping("/saveexp/{cid}/{bid}/{req}/{sb}/{ser}")
     public String savemultidata2(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("sb") String sb,@PathVariable("req") String req,@PathVariable("ser") String ser) {
        Export exportsub = exportRepository.findBySBNoandSbreqid(cid, bid,req,sb);
        if(exportsub.getNoc() == 0) {
        	for(int i=1;i<=exportsub.getNoOfPackages();i++) {
        		String srNo = String.format("%04d", i);
        		Gate_In_Out gateinout = new Gate_In_Out();
        		gateinout.setCompanyId(cid);
        		gateinout.setBranchId(bid);
        		gateinout.setNop(exportsub.getNoOfPackages());
        		gateinout.setErp_doc_ref_no(exportsub.getSbRequestId());
        		gateinout.setDoc_ref_no(exportsub.getSbNo());
        		gateinout.setSr_No(exportsub.getSerNo()+srNo);
        		gateinout.setDgdc_cargo_in_scan("N");
        		gateinout.setDgdc_cargo_out_scan("N");
        		gateinout.setDgdc_seepz_in_scan("N");
        		gateinout.setDgdc_seepz_out_scan("N");
        		
        		gateinoutrepo.save(gateinout);
        	}
        	
        	exportsub.setNoc(exportsub.getNoc()+1);
        	exportRepository.save(exportsub);
        }
        else {
        	exportsub.setNoc(exportsub.getNoc()+1);
        	exportRepository.save(exportsub);
        }
    	 
      return "success";
     }
     
     
     @PostMapping("/saveimp/{cid}/{bid}/{mawb}/{hawb}/{sir}")
     public String savemultidata3(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("hawb") String hawb,@PathVariable("mawb") String mawb,@PathVariable("sir") String ser) {
        Import exportsub = importRepo.SingleImportdata(cid, bid,mawb,hawb);
        System.out.println("Import "+exportsub);
        if(exportsub.getNoc() == 0) {
        	for(int i=1;i<=exportsub.getNop();i++) {
        		String srNo = String.format("%04d", i);
        		Gate_In_Out gateinout = new Gate_In_Out();
        		gateinout.setCompanyId(cid);
        		gateinout.setBranchId(bid);
        		gateinout.setNop(exportsub.getNop());
        		gateinout.setErp_doc_ref_no(exportsub.getMawb());
        		gateinout.setDoc_ref_no(exportsub.getHawb());
        		gateinout.setSr_No(exportsub.getSirNo()+srNo);
        		gateinout.setDgdc_cargo_in_scan("N");
        		gateinout.setDgdc_cargo_out_scan("N");
        		gateinout.setDgdc_seepz_in_scan("N");
        		gateinout.setDgdc_seepz_out_scan("N");
        		
        		gateinoutrepo.save(gateinout);
        		System.out.println("gateinout "+gateinout);
        	}
        	
        	exportsub.setNoc(exportsub.getNoc()+1);
        	importRepo.save(exportsub);
        }
        else {
        	exportsub.setNoc(exportsub.getNoc()+1);
        	importRepo.save(exportsub);
        }
    	 
      return "success";
     }
     
}
