package com.techshopbe.service;

import com.techshopbe.dto.YearRevenueDTO;

public interface ReportService {

	YearRevenueDTO getYearRevenue(int year);
}
