package com.cwms.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cwms.entities.*;
import com.cwms.repository.*;

@Service
public class CFSTariffRangeServiceIMPL implements CFSTariffRangeService {
	@Autowired
	public CFSTariffRangeRepositary CFSTariffRangeRepositary;

	@Autowired
	public ServicesInterface ServicesInterface;

	@Override
	public CFSTariffRange addcfsTarrifServiceRange(CFSTariffRange CFSTariffRange) {
		// TODO Auto-generated method stub
		return CFSTariffRangeRepositary.save(CFSTariffRange);
	}

	@Override
	public CFSTariffRange updatecfsTarrifServiceRange(CFSTariffRange CFSTariffRange) {
		// TODO Auto-generated method stub
		return CFSTariffRangeRepositary.save(CFSTariffRange);
	}

	@Override
	public List<CFSTariffRange> getcfsTarrifServiceRange(String compId, String branchId) {
		// TODO Auto-generated method stub
		return CFSTariffRangeRepositary.findByCompanyIdAndBranchId(compId, branchId);
	}

	@Override
	public List<CFSTariffRange> getcfsTarrifServiceRangeById(String compId, String branchId, String cfstid,
			String amndno) {
		// TODO Auto-generated method stub
		return CFSTariffRangeRepositary.findByCompanyIdAndBranchIdAndCfsTariffNoAndCfsAmndNo(compId, branchId, cfstid,
				amndno);
	}

	@Override
	public List<CFSTariffRange> findByCfsTariffNoAndServiceId(String compId, String branchId, String cfsTariffNo,
			String amndno, String srlNo) {
		// TODO Auto-generated method stub
		return CFSTariffRangeRepositary.findByCompanyIdAndBranchIdAndCfsTariffNoAndCfsAmndNoAndServiceIdAndStatusNot(
				compId, branchId, cfsTariffNo, amndno, srlNo, "D");
	}

	@Override
	public List<CFSTariffRange> saveAllTariffRanges(List<CFSTariffRange> tariffRanges) {

		return CFSTariffRangeRepositary.saveAll(tariffRanges);

//		MAKe method
	}

	@Override
	public BigDecimal findRate(String compId, String branchId, String cfstid, String amndno, String serviceId,
			int totalPackage) {
		// TODO Auto-generated method stub
		return CFSTariffRangeRepositary.findRateByCriteria(compId, branchId, cfstid, amndno, serviceId, totalPackage);
	}

	@Override
	public List<CFSTariffRange> findByCfsTariffNoAndCfsAmndNoAndServiceIdAndPartyId(String companyId, String branchId,
			String cfsTariffNo, String cfsAmndNo, String serviceId, String partyId) {
		// TODO Auto-generated method stub
		return CFSTariffRangeRepositary
				.findByCompanyIdAndBranchIdAndCfsTariffNoAndCfsAmndNoAndServiceIdAndPartyIdAndStatusNot(companyId,
						branchId, cfsTariffNo, cfsAmndNo, serviceId, partyId, "D");
	}

	public Double calculateRateForSlab(List<CFSTariffRange> tariffRanges, Double totalPackage) {
		Double rate = 0.0;
		Double remainingPackage = totalPackage;

		System.out.println("Packages " + remainingPackage);
		// Sort the tariffRanges by rangeFrom in ascending order
		tariffRanges.sort(Comparator.comparingDouble(CFSTariffRange::getRangeFrom));

		for (CFSTariffRange tariffRange : tariffRanges) {
			Double rangeFrom = tariffRange.getRangeFrom();
			Double rangeTo = tariffRange.getRangeTo();
			Double rangeRate = tariffRange.getRangeRate();

			if (remainingPackage <= 0) {
				break; // No need to continue if we've accounted for the entire totalPackage
			}

			if (remainingPackage >= (rangeTo - rangeFrom)) {
				// If remainingPackage is greater than or equal to the entire range, calculate
				// rate
				rate += (rangeTo - rangeFrom) * rangeRate;
				remainingPackage -= (int) (rangeTo - rangeFrom);
			} else {
				// If remainingPackage is less than the entire range, calculate partial rate
				rate += remainingPackage * rangeRate;

//	            System.out.println("Partial Rate " +remainingPackage * rangeRate);
				remainingPackage = 0.0;
			}
		}

		return rate;
	}

	@Override
	public Double findRateForDervice(String compId, String branchId, String cfstid, String amndno, String serviceId,
			String partyId, int totalPackage) {
		String currencyId = null;
		String rangeType = null;
		String taxApplicable = null;
		Double TaxPercentage;
		double totalPackageDecimal = (double) totalPackage;
		Double Rate = 0.0;

		List<CFSTariffRange> tariffRanges = findByCfsTariffNoAndCfsAmndNoAndServiceIdAndPartyId(compId, branchId,
				cfstid, amndno, serviceId, partyId);

		if (!tariffRanges.isEmpty()) {
			currencyId = tariffRanges.get(0).getCurrencyId();
			rangeType = tariffRanges.get(0).getRangeType();
			taxApplicable = tariffRanges.get(0).getTaxApplicable();
		}
		if (taxApplicable != null && taxApplicable.equals("Y")) {
			TaxPercentage = Double.parseDouble(ServicesInterface.findTaxPercentage(compId, branchId, serviceId));
		}

		if ("Range".equals(rangeType)) {
			for (CFSTariffRange tariffRange : tariffRanges) {
				Double rangeFrom = tariffRange.getRangeFrom();
				Double rangeTo = tariffRange.getRangeTo();
				Double rangeRate = tariffRange.getRangeRate();

				if (totalPackageDecimal > rangeFrom && totalPackageDecimal <= rangeTo) {
					Rate = rangeRate;
					break;
				}
			}

		} else if ("Slab".equals(rangeType)) {
//			System.out.println("***************************");

			Rate = calculateRateForSlab(tariffRanges, totalPackageDecimal);

//			 System.out.println("Slab Rates "+Rate);
		}

		return Rate;
	}

	@Override
	public Double findRateForHeavy(String compId, String branchId, String cfstid, String amndno, String serviceId,
			String partyId, List<BigDecimal> weights) {
		String currencyId = null;
		String rangeType = null;
		String taxApplicable = null;
		Double TaxPercentage;
//		double totalPackageDecimal = (double) totalPackage;
		Double Rate = 0.0;

		List<CFSTariffRange> tariffRanges = findByCfsTariffNoAndCfsAmndNoAndServiceIdAndPartyId(compId, branchId,
				cfstid, amndno, serviceId, partyId);

		if (!tariffRanges.isEmpty()) {
			currencyId = tariffRanges.get(0).getCurrencyId();
			rangeType = tariffRanges.get(0).getRangeType();
			taxApplicable = tariffRanges.get(0).getTaxApplicable();
		}
		if (taxApplicable != null && taxApplicable.equals("Y")) {
			TaxPercentage = Double.parseDouble(ServicesInterface.findTaxPercentage(compId, branchId, serviceId));
		}

		if ("Range".equals(rangeType)) {

			for (CFSTariffRange tariffRange : tariffRanges) {
				Double rangeFrom = tariffRange.getRangeFrom();
				Double rangeTo = tariffRange.getRangeTo();
				Double rangeRate = tariffRange.getRangeRate();

				for (BigDecimal heavy : weights) {
					double hpWeightValue = heavy.doubleValue();
					if (hpWeightValue > rangeFrom && hpWeightValue <= rangeTo) {
						Rate += rangeRate;
						break;
					}

				}

			}

		}

		return Rate;
	}

}