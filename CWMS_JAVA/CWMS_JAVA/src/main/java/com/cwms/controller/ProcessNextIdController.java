package com.cwms.controller;

import java.util.List;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwms.entities.ProcessNextId;
import com.cwms.service.ProcessNextIdService;

@RestController
@RequestMapping("/api/processnextids")
@ComponentScan("com.party.repository")
public class ProcessNextIdController {
    private final ProcessNextIdService processNextIdService;

    @Autowired
    public ProcessNextIdController(ProcessNextIdService processNextIdService) {
    	
    	this.processNextIdService = processNextIdService;
    }

    @GetMapping("/{processId}")
    public List<ProcessNextId> getAllByProcessId(@PathVariable String processId,@RequestHeader("React-Page-Name") String reactPageName) {
    	MDC.put("reactPageName", reactPageName);
    	return processNextIdService.getAllByProcessId(processId);
    }
    @GetMapping
    public List<ProcessNextId> getAllByProcessId(@RequestHeader("React-Page-Name") String reactPageName) {
    	MDC.put("reactPageName", reactPageName);
    	return processNextIdService.getAllByProcessId();
    }


    @PostMapping
    public ProcessNextId saveProcessNextId(@RequestBody ProcessNextId processNextId,@RequestHeader("React-Page-Name") String reactPageName)
    {
    	MDC.put("reactPageName", reactPageName);
//    	 String nextProcessId = processNextIdService.autoIncrementProcessId();
//    	 String nextNextId=processNextIdService.autoIncrementNextId();
    	 String nextId = processNextIdService.autoIncrementNextIdNext();
    	 processNextId.setNextId(nextId);
//         processNextId.setProcessId(nextProcessId); // Set the auto-incremented process ID in the object
         return processNextIdService.saveProcessNextId(processNextId);
    }
    @GetMapping("/incrementNextId")
    public String incrementNextId(@RequestHeader("React-Page-Name") String reactPageName) {
    	MDC.put("reactPageName", reactPageName);
        String nextId = processNextIdService.autoIncrementNextIdNext();
        return "Incremented Next_Id: " + nextId;
    }
    
    @PostMapping("/holiday")
    public ProcessNextId saveProcessNextIdforHoliday(@RequestBody ProcessNextId processNextId,@RequestHeader("React-Page-Name") String reactPageName)
    {
    	MDC.put("reactPageName", reactPageName);
//    	 String nextProcessId = processNextIdService.autoIncrementProcessId();
//    	 String nextNextId=processNextIdService.autoIncrementNextId();
    	 String nextholi = processNextIdService.autoIncrementNextIdHoliday();
    	 processNextId.setNextId(nextholi);
//         processNextId.setProcessId(nextProcessId); // Set the auto-incremented process ID in the object
         return processNextIdService.saveProcessNextId(processNextId);
    }
    
    @PostMapping("/mail")
    public ProcessNextId saveProcessNextMailId(@RequestBody ProcessNextId processNextId)
    {
//      String nextProcessId = processNextIdService.autoIncrementProcessId();
//      String nextNextId=processNextIdService.autoIncrementNextId();
      String nextMailId=processNextIdService.autoIncrementMailId();
      processNextId.setNextId(nextMailId);
//         processNextId.setProcessId(nextProcessId); // Set the auto-incremented process ID in the object
         return processNextIdService.saveProcessNextId(processNextId);
    }
    
    
    @PostMapping("/pctm")
    public ProcessNextId saveProcessNextPctmNo(@RequestBody ProcessNextId processNextId)
    {
      String PctmNo=processNextIdService.generateAndIncrementPCTMNumber();
      processNextId.setNextId(PctmNo);
         return processNextIdService.saveProcessNextId(processNextId);
    }
    @PostMapping("/tp")
    public ProcessNextId saveProcessNextTpNo(@RequestBody ProcessNextId processNextId)
    {
      String tpNo=processNextIdService.generateAndIncrementTPumber();
      processNextId.setNextId(tpNo);
         return processNextIdService.saveProcessNextId(processNextId);
    }
    
}
