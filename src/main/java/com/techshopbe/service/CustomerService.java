package com.techshopbe.service;

import java.util.List;

import com.techshopbe.dto.CustomerDTO;
import com.techshopbe.dto.DetailedCustomerDTO;

public interface CustomerService {
	List<CustomerDTO> getAllCustomer();
	DetailedCustomerDTO getDetailedCustomer(String id);
}
