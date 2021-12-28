package com.techshopbe.dto;

import java.util.List;

public class YearRevenueDTO {

	private List<Integer> revenue;
	private int totalProducts;
	private int totalInvoices;
	private int totalRevenues;
	private int highlightMonth;
	private int highestRevenue;
	private int totalCustomers;
	public YearRevenueDTO(List<Integer> revenue, int totalProducts, int totalInvoices, int totalRevenues,
			int highlightMonth, int highestRevenue, int totalCustomers) {
		super();
		this.revenue = revenue;
		this.totalProducts = totalProducts;
		this.totalInvoices = totalInvoices;
		this.totalRevenues = totalRevenues;
		this.highlightMonth = highlightMonth;
		this.highestRevenue = highestRevenue;
		this.totalCustomers = totalCustomers;
	}
	public YearRevenueDTO() {};
	public List<Integer> getRevenue() {
		return revenue;
	}
	public void setRevenue(List<Integer> revenue) {
		this.revenue = revenue;
	}
	public int getTotalProducts() {
		return totalProducts;
	}
	public void setTotalProducts(int totalProducts) {
		this.totalProducts = totalProducts;
	}
	public int getTotalInvoices() {
		return totalInvoices;
	}
	public void setTotalInvoices(int totalInvoices) {
		this.totalInvoices = totalInvoices;
	}
	public int getTotalRevenues() {
		return totalRevenues;
	}
	public void setTotalRevenues(int totalRevenues) {
		this.totalRevenues = totalRevenues;
	}
	public int getHighlightMonth() {
		return highlightMonth;
	}
	public void setHighlightMonth(int highlightMonth) {
		this.highlightMonth = highlightMonth;
	}
	public int getHighestRevenue() {
		return highestRevenue;
	}
	public void setHighestRevenue(int highestRevenue) {
		this.highestRevenue = highestRevenue;
	}
	public int getTotalCustomers() {
		return totalCustomers;
	}
	public void setTotalCustomers(int totalCustomers) {
		this.totalCustomers = totalCustomers;
	}
	
	
}
