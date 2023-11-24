package com.cwms.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwms.entities.Party;
import com.cwms.entities.PartyId;
import com.cwms.repository.PartyRepository;

import jakarta.transaction.Transactional;

@Service
public class PartyService {

	private final PartyRepository partyRepository;

	@Autowired
	public PartyService(PartyRepository partyRepository) {
		this.partyRepository = partyRepository;
	}

	public Party saveParty(Party party) {
//		party.setApprovedDate(null);
//		party.setEditedDate(null);
		LocalDate d =LocalDate.now();//to get date manually use of method
		System.out.println(d);
		return partyRepository.save(party);
	}

	public List<Party> getPartiesNotInIds(String cid,String bid,List<String> excludedPartyIds) {
        return partyRepository.findByCompanyIdAndBranchIdAndPartyIdNotIn(cid,bid,excludedPartyIds);
    }
	
	public Party getPartyById(PartyId id) {
		return partyRepository.findById(id).orElse(null);
	}

	public Party getParty(String id) {
		Party p = partyRepository.findByPartyId(id);
		return p;
	}
	public Party findPartyById(String companyId,String branchId,String partyId)
	{
		
		Party findParty = partyRepository.findByCompanyIdAndBranchIdAndPartyId(companyId, branchId, partyId);
		
		return findParty;
		
			
	}

	public List<Party> getAllParties() {
		return partyRepository.findAll();
	}

//    public void deleteParty(PartyId id) {
//        partyRepository.deleteById(id);
//    }

//	public String autoIncrementPartyId() {
//
//		String maxProcessID = partyRepository.maxPartyId();
//		// String maxProcessID ="M00001";
//
//		if(maxProcessID==null)
//		{
//		   maxProcessID ="M00000";
//		}
//		
//		int lastNumericId = Integer.parseInt(maxProcessID.substring(1));
//
//		int nextNumericId = lastNumericId + 1;
//
//		String nextPartyId = String.format("M%05d", nextNumericId);
//
//		return nextPartyId;
//
//		// String processID="MOOOO1";
//
//	}

	@Transactional
	public void deletePartyById(String partyId) {
		partyRepository.deleteByPartyId(partyId);
	}

	public Party updatePartyById(String partyId, Party updatedParty) {
		Party existingParty = partyRepository.findByPartyId(partyId);
		if (existingParty != null) {
			// Update the fields that can be modified
			existingParty.setPartyName(updatedParty.getPartyName());
			existingParty.setAddress1(updatedParty.getAddress1());
			existingParty.setAddress2(updatedParty.getAddress2());
			existingParty.setAddress3(updatedParty.getAddress3());
			existingParty.setCity(updatedParty.getCity());
			existingParty.setPin(updatedParty.getPin());
			existingParty.setState(updatedParty.getState());
			existingParty.setCountry(updatedParty.getCountry());
			existingParty.setUnitAdminName(updatedParty.getUnitAdminName());
			existingParty.setUnitType(updatedParty.getUnitType());
			existingParty.setEmail(updatedParty.getEmail());
			existingParty.setPhoneNo(updatedParty.getPhoneNo());
			existingParty.setMobileNo(updatedParty.getMobileNo());
			existingParty.setPartyCode(updatedParty.getPartyCode());
			existingParty.setErpCode(updatedParty.getErpCode());
			existingParty.setCreditLimit(updatedParty.getCreditLimit());
			existingParty.setIecNo(updatedParty.getIecNo());
			existingParty.setEntityId(updatedParty.getEntityId());
			existingParty.setPanNo(updatedParty.getPanNo());
			existingParty.setGstNo(updatedParty.getGstNo());
			existingParty.setLoaNumber(updatedParty.getLoaNumber());
			existingParty.setLoaIssueDate(updatedParty.getLoaIssueDate());
			existingParty.setLoaExpiryDate(updatedParty.getLoaExpiryDate());
			existingParty.setEditedBy(updatedParty.getEditedBy());
			existingParty.setEditedDate(updatedParty.getEditedDate());
			existingParty.setStatus(updatedParty.getStatus());

			return partyRepository.save(existingParty);
		} else {
			// Handle case when the party with the given ID doesn't exist
			throw new IllegalArgumentException("Party with ID " + partyId + " not found");
		}
	}

	public Party findByPartyName(String comp,String branch,String party)
	{
		return partyRepository.findByCompanyIdAndBranchIdAndPartyName(comp, branch, party);
	}
	
	
	public Party findByEntityId(String comp,String branch,String entityId)
	{
		return partyRepository.findbyentityid(comp, branch, entityId);
	}
	public String findByEntityIdPartyId(String comp,String branch,String entityId)
	{
		return partyRepository.findPartyNameEntityId(comp, branch, entityId);
	}
	public List<Party> getPartiesByInviceType(String comp,String branch,String inviceType,String status) {
        return partyRepository.findByCompanyIdAndBranchIdAndInvoiceTypeAndStatusNot(comp,branch,inviceType,status);
    }
	
}
