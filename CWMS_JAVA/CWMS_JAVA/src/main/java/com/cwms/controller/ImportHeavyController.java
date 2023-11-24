package com.cwms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cwms.service.*;
import com.cwms.entities.Import;
import com.cwms.entities.ImportHeavyPackage;

@RestController
@CrossOrigin("*")
@RequestMapping("/importHeavy")
public class ImportHeavyController 
{
	@Autowired
	public ImportHeavyService ImportHeavyService;
	
	@Autowired
	public ImportService importService;
	
	@GetMapping("/{compid}/{branchId}/{tranId}/{MAWB}/{HAWB}/{sirNo}/getAllHeavy")
	public List<ImportHeavyPackage> getAll(@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("tranId") String transId, @PathVariable("sirNo") String sirNo)
	{
		return ImportHeavyService.getByMAWB(compid, branchId, transId, MAWB, HAWB, sirNo);
	}
	
	@GetMapping("/{compid}/{branchId}/{tranId}/{MAWB}/{HAWB}/{sirNo}/{packageNo}/getByPakageNo")
	public ImportHeavyPackage getByPackageNo(@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("tranId") String transId, @PathVariable("sirNo") String sirNo, @PathVariable("packageNo") String packageNo)
	{
		return ImportHeavyService.getByPackageNo(compid, branchId, transId, MAWB, HAWB, sirNo, packageNo);
	}
	
	@PostMapping("/{compid}/{branchId}/{user}/addHeavy")
	public ImportHeavyPackage getByPackageNo(@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,@RequestBody ImportHeavyPackage ImportHeavyPackage)
	{
		
		Import byMAWBANdHAWB = importService.getByMAWBANdHAWB(compid, branchId, ImportHeavyPackage.getImpTransId(), ImportHeavyPackage.getMawb(), ImportHeavyPackage.getHawb(), ImportHeavyPackage.getSirNo());
		byMAWBANdHAWB.setHpStatus("Y");
		byMAWBANdHAWB.setParcelType("HPP");
		importService.addImport(byMAWBANdHAWB);
		return ImportHeavyService.addImportHeavy(ImportHeavyPackage);
	}
	@DeleteMapping("/{compid}/{branchId}/{tranId}/{MAWB}/{HAWB}/{sirNo}/{packageNo}/delete")
	public ImportHeavyPackage deleteByPackageNo(@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("tranId") String transId, @PathVariable("sirNo") String sirNo, @PathVariable("packageNo") String packageNo)
	{
		ImportHeavyPackage ImportHeavyPackage = ImportHeavyService.getByPackageNo(compid, branchId, transId, MAWB, HAWB, sirNo, packageNo);
		ImportHeavyPackage deleteImportHeavyPackage = ImportHeavyService.deleteImportHeavyPackage(ImportHeavyPackage);
		
		List<ImportHeavyPackage> byMAWB = ImportHeavyService.getByMAWB(compid, branchId, transId, MAWB, HAWB, sirNo);
		
		
//		System.out.println(byMAWB);
		Import byMAWBANdHAWB = importService.getByMAWBANdHAWB(compid, branchId, ImportHeavyPackage.getImpTransId(), ImportHeavyPackage.getMawb(), ImportHeavyPackage.getHawb(), ImportHeavyPackage.getSirNo());
		if(byMAWB != null && !byMAWB.isEmpty())
		{		
		byMAWBANdHAWB.setHpStatus("Y");	
		byMAWBANdHAWB.setParcelType("HPP");
		importService.addImport(byMAWBANdHAWB);
//		System.out.println(byMAWBANdHAWB);
		}
		else
		{
		byMAWBANdHAWB.setHpStatus("N");
		byMAWBANdHAWB.setParcelType("");
		importService.addImport(byMAWBANdHAWB);
		
//		System.out.println(byMAWBANdHAWB);
		}
		
		return deleteImportHeavyPackage;
	}
	@PutMapping("/{compid}/{branchId}/{tranId}/{MAWB}/{HAWB}/{sirNo}/{packageNo}/update")
	public ImportHeavyPackage updateByPackageNo(@PathVariable("MAWB") String MAWB, @PathVariable("HAWB") String HAWB,
			@PathVariable("compid") String compid, @PathVariable("branchId") String branchId,
			@PathVariable("tranId") String transId, @PathVariable("sirNo") String sirNo, @PathVariable("packageNo") String packageNo ,@RequestBody ImportHeavyPackage ImportHeavyPackage)
	{
			
		ImportHeavyPackage ImportHeavyPackage2 = ImportHeavyService.getByPackageNo(compid, branchId, transId, MAWB, HAWB, sirNo, packageNo);
		if(ImportHeavyPackage2 != null)
		{
		ImportHeavyPackage2.setHppackageno(ImportHeavyPackage.getHppackageno());
		ImportHeavyPackage2.setHpWeight(ImportHeavyPackage.getHpWeight());
		
		Import byMAWBANdHAWB = importService.getByMAWBANdHAWB(compid, branchId, ImportHeavyPackage2.getImpTransId(), ImportHeavyPackage2.getMawb(), ImportHeavyPackage2.getHawb(), ImportHeavyPackage2.getSirNo());
		byMAWBANdHAWB.setHpStatus("Y");
		byMAWBANdHAWB.setParcelType("HPP");
		importService.addImport(byMAWBANdHAWB);
		
		return ImportHeavyService.addImportHeavy(ImportHeavyPackage2);
		}
		else
		{
			Import byMAWBANdHAWB = importService.getByMAWBANdHAWB(compid, branchId, ImportHeavyPackage.getImpTransId(), ImportHeavyPackage.getMawb(), ImportHeavyPackage.getHawb(), ImportHeavyPackage.getSirNo());
			byMAWBANdHAWB.setHpStatus("Y");
			byMAWBANdHAWB.setParcelType("HPP");
			importService.addImport(byMAWBANdHAWB);
		return ImportHeavyService.addImportHeavy(ImportHeavyPackage);
		}
	}
	
	
}