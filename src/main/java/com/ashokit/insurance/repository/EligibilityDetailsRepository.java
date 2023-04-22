package com.ashokit.insurance.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.insurance.entity.EligibilityDetails;

public interface EligibilityDetailsRepository extends JpaRepository<EligibilityDetails, Integer> {

//	// select * from eligibility_details where planName=?
//	public List<EligibilityDetails> findByPlanName(String planName);
//
//	// select * from eligibility_details where planStatus=?
//	public List<EligibilityDetails> findByPlanStatus(String planStatus);
//
//	// select * from eligibility_details where planName=? and planStatus=?
//	public List<EligibilityDetails> findByPlanNameAndPlanStatus(String planName, String planStatus);
//	
//	//select * from eligibility_details where startDate=? and endDate=? 
//	public List<EligibilityDetails> findByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
//
//	// select * from eligibility_details where planName=? and startDate=? and endDate=?
//	public List<EligibilityDetails> findByPlanNameAndStartDateAndEndDate(String planName, LocalDate startDate, LocalDate endDate);
//	
//	// select * from eligibility_details where planStatus=?  and startDate=? and endDate=? 
//	public List<EligibilityDetails> findByPlanStatusAndStartDateAndEndDate(String planStatus, LocalDate startDate, LocalDate endDate);
//	
//	// select * from eligibility_details where planName=? and startDate=? and endDate=?
//	public List<EligibilityDetails> findByPlanNameAndPlanStatusAndStartDateAndEndDate(String planName, String planStatus, LocalDate startDate, LocalDate endDate);
	
	@Query("select distinct(planName) from EligibilityDetails")
	public List<String> getUniquePlanNames();

	@Query("select distinct(planStatus) from EligibilityDetails")
	public List<String> getUniquePlanStatuses();
}