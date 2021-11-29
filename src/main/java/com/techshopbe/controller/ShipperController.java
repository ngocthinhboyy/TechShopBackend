package com.techshopbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.entity.Shipper;
import com.techshopbe.service.ShipperService;

@RestController
@RequestMapping("api/v1/shipper")
public class ShipperController {

	@Autowired
	private ShipperService shipperService;
	
	@GetMapping
	public Object get() {
		try {
			List<Shipper> shippers = shipperService.getAll();

			return new ResponseEntity<List<Shipper>>(shippers, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping
	public Object add(@RequestBody Shipper shipper) {
		try {
			shipperService.add(shipper);
			return new ResponseEntity<String>("Add Successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public Object update(@RequestBody Shipper shipper) {
		try {
			shipperService.update(shipper);
			return new ResponseEntity<String>("Update Successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value="/{id}")
	public Object delete(@PathVariable String id) {
		try {
			shipperService.delete(id);
			return new ResponseEntity<String>("Delete Successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
}
