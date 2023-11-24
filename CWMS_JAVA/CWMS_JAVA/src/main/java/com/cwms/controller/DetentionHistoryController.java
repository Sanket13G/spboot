package com.cwms.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cwms.entities.Detention_History;
import com.cwms.repository.Detention_HistoryRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/detention-history")
public class DetentionHistoryController {

    
    @Autowired
    private  Detention_HistoryRepository detentionHistoryRepository;

    @GetMapping("/history/{companyId}/{branchId}/{siNo}/{fileNo}")
    public ResponseEntity<List<Detention_History>> findByCompanyIdAndBranchIdAndSiNoAndFileNo(
            @PathVariable("companyId") String companyId,
            @PathVariable("branchId") String branchId,
            @PathVariable("siNo") Long siNo,
            @PathVariable("fileNo") String fileNo
    ) {
        List<Detention_History> history = detentionHistoryRepository.findByCompanyIdAndBranchIdAndSiNoAndFileNo(
                companyId,
                branchId,
                siNo,
                fileNo
        );

        if (history != null) {
            return ResponseEntity.ok(history);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
