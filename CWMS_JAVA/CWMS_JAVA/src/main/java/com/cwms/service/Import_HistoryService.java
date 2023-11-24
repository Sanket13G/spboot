package com.cwms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cwms.entities.Import_History;

@Service
public interface Import_HistoryService {
	Import_History addHistory(Import_History history);

	Import_History updateHistory(Import_History history);

	List<Import_History> findbySIRNO(String cid, String bid,String MAWb, String HAWB ,String SIRNO);
	List<Import_History> SaveAllImportsHistory(List<Import_History> ImportListHistory);
}
