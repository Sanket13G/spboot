package com.cwms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.ExportSub;
import com.cwms.entities.Forward_out;
import com.cwms.entities.Import;
import com.cwms.entities.ImportSub;
import com.cwms.repository.ExportSubRepository;
import com.cwms.repository.ForwardOutRepo;
import com.cwms.repository.ImportRepo;
import com.cwms.repository.ImportSubRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/forwardout")
public class ForwardOutController {

	@Autowired
	private ForwardOutRepo forwardrepo;
	
	@Autowired
	private ImportRepo imprepo;
	
	@Autowired
	private ImportSubRepository impsubrepo;
	
	@Autowired
	private ExportSubRepository exportsubrepo;
	
	@PostMapping("/save/{cid}/{bid}/{sir}/{packnum}")
	public Forward_out saveData(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("sir") String sir,@PathVariable("packnum") String packnum) {
		 if(sir.startsWith("D-IM")) {
			 ImportSub impsub = impsubrepo.Singledata(cid, bid, sir);
			 Forward_out forwardout = new Forward_out();
			 forwardout.setCompanyId(cid);
			 forwardout.setBranchId(bid);
			 forwardout.setErp_doc_ref_no(impsub.getRequestId());
			 forwardout.setDoc_ref_no(impsub.getSirNo());
			 forwardout.setSirNo(sir);
			 forwardout.setPackageNo(packnum);
			 forwardout.setBeNumber(impsub.getRequestId());
			 forwardout.setTypeOfTransaction("Forwarded-Out");
			 forwardout.setSirDate(impsub.getSirDate());
			 forwardout.setParty(impsub.getExporter());
			 forwardout.setNop(Integer.toString(impsub.getNop()));
			 forwardout.setForwardoutDate(new Date());
			 this.forwardrepo.save(forwardout);
			 int forwardout1 = forwardrepo.findbydoc_ref_no(cid, bid, sir);
			 if(impsub.getNop() == forwardout1) {
				 impsub.setForwardedStatus("FWD_OUT");
				 impsubrepo.save(impsub);
			 }
			 
			 
			 return forwardout;
			 
		 }
		 else if(sir.startsWith("D-EX")) {
			 ExportSub impsub = exportsubrepo.findExportSubByseronly(cid, bid, sir);
			 Forward_out forwardout = new Forward_out();
			 forwardout.setCompanyId(cid);
			 forwardout.setBranchId(bid);
			 forwardout.setErp_doc_ref_no(impsub.getRequestId());
			 forwardout.setDoc_ref_no(impsub.getSerNo());
			 forwardout.setSirNo(sir);
			 forwardout.setPackageNo(packnum);
			 forwardout.setBeNumber(impsub.getRequestId());
			 forwardout.setTypeOfTransaction("Forwarded-Out");
			 forwardout.setSirDate(impsub.getSerDate());
			 forwardout.setParty(impsub.getExporter());
			 forwardout.setNop(Integer.toString(impsub.getNop()));
			 forwardout.setForwardoutDate(new Date());
			 this.forwardrepo.save(forwardout);
			 int forwardout1 = forwardrepo.findbydoc_ref_no(cid, bid, sir);
			 if(impsub.getNop() == forwardout1) {
				 impsub.setForwardedStatus("FWD_OUT");
				 exportsubrepo.save(impsub);
			 }
			 
			 
			 return forwardout;
			 
		 }
		 else {
			 Import impsub = imprepo.Singledata(cid, bid, sir);
			 Forward_out forwardout = new Forward_out();
			 forwardout.setCompanyId(cid);
			 forwardout.setBranchId(bid);
			 forwardout.setErp_doc_ref_no(impsub.getMawb());
			 forwardout.setDoc_ref_no(impsub.getHawb());
			 forwardout.setSirNo(sir);
			 forwardout.setPackageNo(packnum);
			 forwardout.setBeNumber("");
			 forwardout.setTypeOfTransaction("Forwarded-Out");
			 forwardout.setSirDate(impsub.getSirDate());
			 forwardout.setParty(impsub.getImporterId());
			 forwardout.setNop(Integer.toString(impsub.getNop()));
			 forwardout.setForwardoutDate(new Date());
			 this.forwardrepo.save(forwardout);
			 int forwardout1 = forwardrepo.findbydoc_ref_no1(cid, bid, sir);
			 if(impsub.getNop() == forwardout1) {
				 impsub.setForwardedStatus("FWD_OUT");
				 imprepo.save(impsub);
			 }
			 
			 
			 return forwardout;
		 }
	}
	
	@GetMapping("/getdata/{cid}/{bid}")
	public List<Forward_out> getdata(@PathVariable("cid") String cid,@PathVariable("bid") String bid){
		return this.forwardrepo.findByCompanyIdAndBranchId(cid, bid);
	}
	
	@GetMapping("/getsingledata/{cid}/{bid}/{sir}/{packno}")
	public Forward_out getSingledata(@PathVariable("cid") String cid,@PathVariable("bid") String bid,@PathVariable("sir") String sir,@PathVariable("packno") String packno) {
		return this.forwardrepo.findByCompanyIdAndBranchIdAndSirNoAndPackageNo(cid, bid, sir, packno);
	}
}
