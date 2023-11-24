package com.cwms.service;

import org.springframework.stereotype.Service;

import com.cwms.entities.ImportPC;

@Service
public interface ImportPCService 
{
    public ImportPC getByIDS(String companyId, String branchId,String MAWb, String HAWB , String sirNo);
    public ImportPC AddImportPC(ImportPC AddImportPC);
    public void deleteImportPc(String companyId, String branchId,String MAWb, String HAWB , String sirNo);
    
}