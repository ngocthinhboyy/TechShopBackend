package com.techshopbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.dto.InvoiceDTO;
import com.techshopbe.dto.InvoiceForUserDTO;
import com.techshopbe.entity.Category;
import com.techshopbe.service.InvoiceService;
import com.techshopbe.service.UserService;

@RestController
@RequestMapping("api/v1/invoice")

public class InvoiceController {
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	UserService userService;
	
	@PostMapping
	public Object add(@RequestBody String invoice) {
		try {
			invoiceService.add(invoice);
			return new ResponseEntity<String>("Order Successfully!", HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<String>("Order Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/{month}/{year}")
	public Object getAllInvoicesByMonthAndYear(@PathVariable int month, @PathVariable int year) {
		
		try {
			List<InvoiceForUserDTO> userInvoices = invoiceService.getAllInvoicesByMonthAndYear(month, year);
			return new ResponseEntity<List<InvoiceForUserDTO>>(userInvoices, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/user")
	public Object getAllUserInvoices() {
		
		try {
			List<InvoiceForUserDTO> userInvoices = invoiceService.getAllUserInvoices();
			return new ResponseEntity<List<InvoiceForUserDTO>>(userInvoices, HttpStatus.OK);
		} catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "/{invoiceID}")
	public Object getByInvoiceID(@PathVariable String invoiceID) {
		
		try {
			InvoiceDTO invoice = invoiceService.getByInvoiceID(invoiceID);
			return new ResponseEntity<InvoiceDTO>(invoice, HttpStatus.OK);
		} catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping(value = "/status/{invoiceID}")
	public Object updateStatus(@PathVariable String invoiceID) {
		try {
			int newStep = invoiceService.updateStatusInvoice(invoiceID);
			userService.updateUserReward(invoiceID, newStep);
			
			return new ResponseEntity<String>("Update Successfully!", HttpStatus.OK);
		} catch(Exception e) {
			if(e.getMessage().isEmpty())
				return new ResponseEntity<String>("Update Failed", HttpStatus.BAD_REQUEST);
			else
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping(value = "/cancel/{invoiceID}")
	public Object cancel(@PathVariable String invoiceID, @RequestBody String reason) {
		try {
			invoiceService.cancelInvoice(invoiceID, reason);
			return new ResponseEntity<String>("Cancel Successfully!", HttpStatus.OK);
		} catch(Exception e) {
			if(e.getMessage().isEmpty())
				return new ResponseEntity<String>("Cancel Failed", HttpStatus.BAD_REQUEST);
			else
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping(value="shipper/{invoiceID}/{shipperID}")
	public Object updateShipperInfo(@PathVariable String invoiceID, @PathVariable String shipperID) {
		try {
			invoiceService.updateShipperInformation(invoiceID, shipperID);
			return new ResponseEntity<String>("Update Successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
}
