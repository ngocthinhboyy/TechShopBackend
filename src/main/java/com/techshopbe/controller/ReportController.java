package com.techshopbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.dto.YearRevenueDTO;
import com.techshopbe.service.ReportService;

@RestController
@RequestMapping("api/v1/report")
public class ReportController {
	
	@Autowired
	ReportService reportService;

	@GetMapping("/revenue/year/{year}")
	public Object getYearRevenue(@PathVariable int year) {
		try {
			YearRevenueDTO yearRevenue = reportService.getYearRevenue(year);
			return new ResponseEntity<YearRevenueDTO>(yearRevenue, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
}
