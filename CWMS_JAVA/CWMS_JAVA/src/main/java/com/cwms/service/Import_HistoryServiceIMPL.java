package com.cwms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.Import_History;
import com.cwms.repository.Import_HistoryRepo;

@Service
public class Import_HistoryServiceIMPL implements Import_HistoryService
{
	@Autowired
	public Import_HistoryRepo historyRepo;
	
	@Override
	public Import_History addHistory(Import_History history) {
		// TODO Auto-generated method stub
		return historyRepo.save(history);
	}

	@Override
	public Import_History updateHistory(Import_History history) {
		// TODO Auto-generated method stub
		return historyRepo.save(history);
	}

	@Override
	public List<Import_History> findbySIRNO(String cid,String bid,String MAWb, String HAWB , String SIRNO) {
		// TODO Auto-generated method stub
		return historyRepo.findByCompanyIdAndBranchIdAndMawbAndHawbAndSirNo(cid,bid,MAWb,HAWB,SIRNO);
	}

	@Override
	public List<Import_History> SaveAllImportsHistory(List<Import_History> ImportListHistory) {
		// TODO Auto-generated method stub
		return null;
	}

}