package com.cwms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.stereotype.Service;

import com.cwms.entities.Detention;
import com.cwms.entities.Party;
import com.cwms.repository.DetantionRepository;

import jakarta.transaction.Transactional;
import jakarta.websocket.server.ServerEndpoint;

@Service
public class DetantionService {

	@Autowired
	public DetantionRepository detentionRepository;

	public List<Detention> getAllDataByCompanyAndBranch(String companyId, String branchId) {
		return detentionRepository.getalldata(companyId, branchId);
	}

	public Detention adddetention(Detention deten) {
		return detentionRepository.save(deten);
	}

	public Detention withdrawetention(Detention deten) {
		return detentionRepository.save(deten);
	}

	public Detention getDetentionById(String detentionId) {
		return detentionRepository.findDetentionByDetentionId(detentionId);

	}

	public Detention update(String detentionId, Detention updatedDeten) {
		Detention existingDetention = detentionRepository.findDetentionByDetentionId(detentionId);
		if (existingDetention != null) {
			// Update the fields that can be modified
			existingDetention.setDgdcOfficerDesignation(updatedDeten.getDgdcOfficerDesignation());
			existingDetention.setDgdcOfficerName(updatedDeten.getDgdcOfficerName());
			existingDetention.setApprovedBy(updatedDeten.getApprovedBy());
			existingDetention.setApprovedDate(updatedDeten.getApprovedDate());
			existingDetention.setCreatedBy(updatedDeten.getCreatedBy());
			existingDetention.setCreatedDate(updatedDeten.getCreatedDate());
			existingDetention.setDepositDate(updatedDeten.getDepositDate());
			existingDetention.setEditedBy(updatedDeten.getEditedBy());
			existingDetention.setEditedDate(updatedDeten.getEditedDate());
			existingDetention.setFileNo(updatedDeten.getFileNo());
			existingDetention.setNop(updatedDeten.getNop());
			existingDetention.setOfficerDesignation(updatedDeten.getOfficerDesignation());
			existingDetention.setOfficerName(updatedDeten.getOfficerName());
			existingDetention.setOtherParty(updatedDeten.getOtherParty());
			existingDetention.setParcelDetainedBy(updatedDeten.getParcelDetainedBy());
			existingDetention.setParcelType(updatedDeten.getParcelType());
			existingDetention.setPartyId(updatedDeten.getPartyId());
			existingDetention.setRemarks(updatedDeten.getRemarks());
			existingDetention.setStatus(updatedDeten.getStatus());

			return detentionRepository.save(existingDetention);
		} else {
			// Handle case when the party with the given ID doesn't exist
			throw new IllegalArgumentException("Party with ID " + detentionId + " not found");
		}
	}

	public Detention updateWithraw(String detentionId, Detention updatedDeten) {
		Detention existingDetention = detentionRepository.findDetentionByDetentionId(detentionId);
		if (existingDetention != null) {
			// Update the fields that can be modified
			existingDetention.setWithdrawDate(updatedDeten.getWithdrawDate());
			existingDetention.setWithdrawDgdcOfficerDesignation(updatedDeten.getWithdrawDgdcOfficerName());
			existingDetention.setWithdrawDgdcOfficerDesignation(updatedDeten.getWithdrawDgdcOfficerDesignation());
			existingDetention.setWithdrawOfficerName(updatedDeten.getWithdrawOfficerName());
			existingDetention.setWithdrawOfficerDesignation(updatedDeten.getWithdrawOfficerDesignation());
			existingDetention.setWithdrawNop(updatedDeten.getWithdrawNop());
			existingDetention.setWithdrawPartyId(updatedDeten.getWithdrawPartyId());
			existingDetention.setWithdrawRemarks(updatedDeten.getWithdrawRemarks());

			return detentionRepository.save(existingDetention);
		} else {
			// Handle case when the party with the given ID doesn't exist
			throw new IllegalArgumentException("Party with ID " + detentionId + " not found");
		}
	}


	public Detention updateIssue(String detentionId, Detention updatedIssue) {
		Detention existingDetentionIssue = detentionRepository.findDetentionByDetentionId(detentionId);
		if (existingDetentionIssue != null) {
			// Update the fields that can be modified
			existingDetentionIssue.setIssueDate(updatedIssue.getIssueDate());
			existingDetentionIssue.setIssueOfficerName(updatedIssue.getIssueOfficerName());

			existingDetentionIssue.setIssueOfficerDesignation(updatedIssue.getIssueOfficerDesignation());

			existingDetentionIssue.setIssueDgdcOfficerDesignation(updatedIssue.getIssueDgdcOfficerDesignation());

			existingDetentionIssue.setIssueDgdcOfficerName(updatedIssue.getIssueDgdcOfficerName());

			existingDetentionIssue.setIssueNop(updatedIssue.getIssueNop());

			existingDetentionIssue.setIssueType(updatedIssue.getIssueType());

			existingDetentionIssue.setIssueReason(updatedIssue.getIssueReason());

			existingDetentionIssue.setIssueRemarks(updatedIssue.getIssueRemarks());

			return detentionRepository.save(existingDetentionIssue);
		} else {
			// Handle case when the party with the given ID doesn't exist
			throw new IllegalArgumentException("Party with ID " + detentionId + " not found");
		}
	}

	public Detention updateRedeposite(String detentionId, Detention updatedDetenRe) {
		Detention existingDetentionRedeposite = detentionRepository.findDetentionByDetentionId(detentionId);
		if (existingDetentionRedeposite != null) {
			// Update the fields that can be modified
			existingDetentionRedeposite.setRedepositeDate(updatedDetenRe.getRedepositeDate());

			existingDetentionRedeposite.setRedepositeOfficerName(updatedDetenRe.getRedepositeOfficerName());

			existingDetentionRedeposite
					.setRedepositeOfficerDesignation(updatedDetenRe.getRedepositeOfficerDesignation());

			existingDetentionRedeposite.setRedepositeDgdcOfficerName(updatedDetenRe.getRedepositeDgdcOfficerName());

			existingDetentionRedeposite
					.setRedepositeDgdcOfficerDesignation(updatedDetenRe.getRedepositeDgdcOfficerDesignation());

			existingDetentionRedeposite.setRedepositeType(updatedDetenRe.getRedepositeType());

			existingDetentionRedeposite.setRedepositeNop(updatedDetenRe.getRedepositeNop());

			existingDetentionRedeposite.setRedepositeRemarks(updatedDetenRe.getRedepositeRemarks());

			return detentionRepository.save(existingDetentionRedeposite);
		} else {
			// Handle case when the party with the given ID doesn't exist
			throw new IllegalArgumentException("Party with ID " + detentionId + " not found");
		}
	}

}
