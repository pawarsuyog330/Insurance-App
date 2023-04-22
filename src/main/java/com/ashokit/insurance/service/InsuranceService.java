package com.ashokit.insurance.service;

import java.util.List;

import com.ashokit.insurance.request.SearchRequest;
import com.ashokit.insurance.response.SearchResponse;

public interface InsuranceService {

	public List<SearchResponse> searchPlans(SearchRequest request);

	public List<String> getPlanNames();

	public List<String> getPlanStatuses();

}
