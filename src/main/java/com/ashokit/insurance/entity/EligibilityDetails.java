package com.ashokit.insurance.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="ELIGIBILITY_DTLS")
@Entity
public class EligibilityDetails {

	@Id
	@GeneratedValue
	private Integer planId;
	
	private Long caseNumber;
	
	private String planName;
	
	private String planStatus;
	
	private String holderName;
	
	private Long holderSsn;
	
	private Double benefitAmount;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private String denialReason;
}