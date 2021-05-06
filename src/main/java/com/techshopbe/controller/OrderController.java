package com.techshopbe.controller;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.entity.User;
import com.techshopbe.service.OrderService;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@PostMapping(value = "")
	public Object add(@RequestBody String order) {
		orderService.addOrder(order);
		try {
			return new ResponseEntity<String>("Order Successfully!", HttpStatus.CREATED);
		} catch(Exception e) {
			
			return new ResponseEntity<String>("Order Failed", HttpStatus.BAD_REQUEST);
		}
	}

}
