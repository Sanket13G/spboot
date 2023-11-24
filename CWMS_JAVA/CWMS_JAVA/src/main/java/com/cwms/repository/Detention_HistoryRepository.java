package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwms.entities.Detention_History;

public interface Detention_HistoryRepository extends JpaRepository<Detention_History, Long>{

	List<Detention_History> findByCompanyIdAndBranchIdAndSiNoAndFileNo(String companyId,String branchId,Long siNo,String fileNo);
}
