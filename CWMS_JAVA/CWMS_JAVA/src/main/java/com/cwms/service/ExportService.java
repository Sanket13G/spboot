package com.cwms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.Export;
import com.cwms.repository.ExportRepository;

@Service
public class ExportService {
    

    @Autowired
	private ExportRepository sbTransactionRepository;

    
    
    public Export getSBTransaction(String sbTransId) {
        Optional<Export> optionalSBTransaction = sbTransactionRepository.findById(sbTransId);
        return optionalSBTransaction.orElse(null);
    }

    public Export createSBTransaction(Export sbTransaction) {
        return sbTransactionRepository.save(sbTransaction);
    }

    
    public void deleteSBTransaction(String sbTransId) {
        sbTransactionRepository.deleteById(sbTransId);
    }

    public List<Export> getAllSBTransactions() {
        return sbTransactionRepository.findAllNotDeleted();
    }
    
    public List<Export> getExportsByCompanyAndBranch(String companyId, String branchId) {
        return sbTransactionRepository.findExportsByCompanyAndBranch(companyId, branchId);
    }
    
    public List<Export> findRecordsByFormattedSbDate(String sbdate ,String Cid,String Bid,String status)
    {
        return (List<Export>) sbTransactionRepository.findByCompanyIdAndBranchIdAndSbDateAndDgdcStatus(sbdate,Cid,Bid,status);
    }
    
    public List<Export> findAllExportData(String companyId, String branchId, Date startDate, Date endDate) {
        return sbTransactionRepository.findAllExportData(companyId, branchId, startDate, endDate);
    }
    
    
    public String updatePCTMAndTPNo(String pctmNo, String tpNo, String companyId, String branchId) {
        return sbTransactionRepository.updatePCTMAndTPNo(pctmNo, tpNo, companyId, branchId);
    }
    
    public Export findForInvoiceExport(String companyId, String branchId, Date sirDate, String importerId, int ImpoNop,
			String pcStatus, String scStatus, String hpStatus)
    {    	
    	return sbTransactionRepository.findByCompanyIdAndBranchIdAndSerDateAndNameOfExporterAndNoOfPackagesAndPcStatusAndScStatusAndHpStatus(companyId, branchId, sirDate, importerId, ImpoNop, pcStatus, scStatus, hpStatus);
    }
}