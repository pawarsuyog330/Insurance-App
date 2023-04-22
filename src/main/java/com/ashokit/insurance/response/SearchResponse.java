package com.ashokit.insurance.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchResponse {

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
