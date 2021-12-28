package com.techshopbe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.YearRevenueDTO;
import com.techshopbe.entity.Invoice;
import com.techshopbe.repository.InvoiceRepository;
import com.techshopbe.repository.ProductRepository;
import com.techshopbe.repository.UserRepository;
import com.techshopbe.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	InvoiceRepository invoiceRepository;

	@Override
	public YearRevenueDTO getYearRevenue(int year) {
		YearRevenueDTO yearRevenue = new YearRevenueDTO();
		yearRevenue.setTotalProducts(productRepository.countByIsDeleted(false));
		
		String startDate = Integer.toString(year) + "-01-01";
		String endDate = Integer.toString(year) + "-12-31";
		
		yearRevenue.setTotalCustomers(userRepository.countByJoiningDateBetween(startDate, endDate));
		
		List<Invoice> invoices = invoiceRepository.findByStepGreaterThan(4);
		List<Invoice> invoicesInYear = new ArrayList<Invoice>();
		int totalRevenues = 0;
		List<Integer> revenue = new ArrayList<Integer>();
		for (int i = 0; i < 12; i++) {
			revenue.add(0);
		}
		
		for(Invoice invoice : invoices) {
			String[] arrProcessDate =  invoice.getProcessDate().split(", ");
			int monthLastConfirm = Integer.parseInt(arrProcessDate[arrProcessDate.length - 1].split("-")[1]);
			int yearLastConfirm = Integer.parseInt(arrProcessDate[arrProcessDate.length - 1].split("-")[0]);
			if(yearLastConfirm == year) {
				invoicesInYear.add(invoice);
				totalRevenues += invoice.getTotalCost();
				revenue.set(monthLastConfirm - 1, invoice.getTotalCost());
			}
		}
		yearRevenue.setTotalInvoices(invoicesInYear.size());
		yearRevenue.setTotalRevenues(totalRevenues);
		yearRevenue.setRevenue(revenue);
		int maxRevenue = revenue.get(0);
		int monthMaxRevenue = 0;
		for(int i = 1; i < 12; i++) {
			if(revenue.get(i) > maxRevenue) {
				maxRevenue = revenue.get(i);
				monthMaxRevenue = i;
			}
		}
		yearRevenue.setHighestRevenue(maxRevenue);
		yearRevenue.setHighlightMonth(monthMaxRevenue);
		return yearRevenue;
	}

}
