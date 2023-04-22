package com.ashokit.insurance.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.insurance.entity.EligibilityDetails;
import com.ashokit.insurance.repository.EligibilityDetailsRepository;
import com.ashokit.insurance.request.SearchRequest;
import com.ashokit.insurance.response.SearchResponse;
import com.ashokit.insurance.service.InsuranceService;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	public EligibilityDetailsRepository detailsRepo;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<SearchResponse> searchPlans(SearchRequest request) {
		
		List<EligibilityDetails> allRecords =null;

		if (isSearchEmpty(request)) {
			allRecords = detailsRepo.findAll();
		} else {
			String planName = request.getPlanName();
			String planStatus = request.getPlanStatus();
			LocalDate startDate = request.getStartDate();
			LocalDate endDate = request.getEndDate();
			
			EligibilityDetails entity =new EligibilityDetails();
			

			if (planName != null && !planName.equals("")) {
				// add planName to where clause
				entity.setPlanName(planName);
			}

			if (planStatus != null && !planStatus.equals("")) {
				// add planStatus to where clause
				entity.setPlanStatus(planStatus);
			}

			if (startDate != null & endDate != null) {
				// add startDate and endDate to where clause
				entity.setStartDate(startDate);
				entity.setEndDate(endDate);
			}
			
			Example<EligibilityDetails> example = Example.of(entity);
			allRecords=detailsRepo.findAll(example);
		}
		
		List<SearchResponse> response =new ArrayList<>();
		for(EligibilityDetails records: allRecords)
		{
			SearchResponse sr=new SearchResponse();
			BeanUtils.copyProperties(records, sr);
			response.add(sr);
		}
		return response;
	}

	@Override
	public List<String> getPlanNames() {
		return detailsRepo.getUniquePlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return detailsRepo.getUniquePlanStatuses();
	}
	
	public void m1(SearchRequest request)
	{
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<EligibilityDetails> query=cb.createQuery(EligibilityDetails.class);
		Root<EligibilityDetails> root=query.from(EligibilityDetails.class);
		query.select(root);
		
		Predicate planNamePredicate=cb.equal(root.get("PlanName"), request.getPlanName());
		query.where(planNamePredicate);
		
		Predicate planStatusPredicate=cb.equal(root.get("PlanStatus"), request.getPlanStatus());
		query.where(planStatusPredicate);
		
		// cb.ge(root.get("startDate"), request.getStartDate());
	}
	
	public boolean isSearchEmpty(SearchRequest request)
	{
		if(request.getPlanName()!=null && !request.getPlanName().equals(""))
		{
			return false;
		}
		
		if(request.getPlanStatus()!=null && !request.getPlanStatus().equals(""))
		{
			return false;
		}
		
		if(request.getStartDate()!=null && !request.getStartDate().equals(""))
		{
			return false;
		}
		
		if(request.getEndDate()!=null && !request.getEndDate().equals(""))
		{
			return false;
		}
		
		return true;
	}
}
