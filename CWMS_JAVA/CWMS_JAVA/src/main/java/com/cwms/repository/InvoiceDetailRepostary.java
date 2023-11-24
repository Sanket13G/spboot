package com.cwms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cwms.entities.InvoiceDetail;

public interface InvoiceDetailRepostary extends JpaRepository<InvoiceDetail, String>
{
 List<InvoiceDetail> findByCompanyIdAndBranchIdAndInvoiceNO(String companyId,String branchId,String onvoiceNo);
}
