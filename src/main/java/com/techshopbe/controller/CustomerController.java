package com.techshopbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.dto.CustomerDTO;
import com.techshopbe.dto.DetailedCustomerDTO;
import com.techshopbe.service.CustomerService;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public Object get() {
		try {
			List<CustomerDTO> customers = customerService.getAllCustomer();
			return new ResponseEntity<List<CustomerDTO>>(customers, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/{id}")
	public Object getDetailedCustomer(@PathVariable String id) {
		try {
			DetailedCustomerDTO detailedCustomer = customerService.getDetailedCustomer(id);
			return new ResponseEntity<DetailedCustomerDTO>(detailedCustomer, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
}
