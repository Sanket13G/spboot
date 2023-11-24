package com.cwms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.Import_History;
import com.cwms.service.Import_HistoryService;

@RestController
@RequestMapping("history")
@CrossOrigin("*")
public class ImportHistoryController 
{
		@Autowired
		private Import_HistoryService historyService;
		
		@GetMapping("/{cid}/{bid}/{MAWB}/{HAWB}/{SIRNO}")
		public List<Import_History> getBySirno(@PathVariable("SIRNO")String SIRNO,
				@PathVariable("cid")String CompId, @PathVariable("bid")String branchId,
				@PathVariable("MAWB")String MAWB, @PathVariable("HAWB")String HAWB)
		{
			return historyService.findbySIRNO(CompId,branchId,MAWB,HAWB,SIRNO);
		}
		
}