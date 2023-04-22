package com.ashokit.insurance.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.insurance.entity.EligibilityDetails;
import com.ashokit.insurance.reportgenerator.ExcelGenerator;
import com.ashokit.insurance.reportgenerator.PdfGenerator;
import com.ashokit.insurance.request.SearchRequest;
import com.ashokit.insurance.response.SearchResponse;
import com.ashokit.insurance.service.InsuranceService;

@RestController
public class ReportGeneratorController {
	
	@Autowired
	private InsuranceService service;

	@GetMapping("/plan-names")
	public List<String> getPlanNames()
	{
		return service.getPlanNames();
	}
	
	@GetMapping("/plan-status")
	public List<String> getPlanStatuses()
	{
		return service.getPlanStatuses();
	}
	
	@GetMapping("/search-plans")
	public List<SearchResponse> searchPlans(@RequestBody SearchRequest request)
	{
		return service.searchPlans(request);
	}
	
	@GetMapping("/excel")
	  public void generateExcel(HttpServletResponse response) throws Exception {

	    response.setContentType("application/octet-stream");
	    String headerKey = "Content-Disposition";
	    String headerValue = "attachment; filename=Plans.xls";
	    response.setHeader(headerKey, headerValue);

	    List<SearchResponse> records = service.searchPlans(null);
	    ExcelGenerator excel = new ExcelGenerator();
	    excel.generateExcel(records, response);
	  }
	
	@GetMapping("/pdf")
	  public void generatePdf(HttpServletResponse response) throws Exception {

	    response.setContentType("application/pdf");
	    String headerKey = "Content-Disposition";
	    String headerValue = "attachment; filename=Plans.pdf";
	    response.setHeader(headerKey, headerValue);

	    List<SearchResponse> records = service.searchPlans(null);
	    PdfGenerator pdf = new PdfGenerator();
	    pdf.generatePdf(records, response);
	  }
}
