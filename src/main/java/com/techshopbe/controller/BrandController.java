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

import com.techshopbe.entity.Brand;
import com.techshopbe.service.BrandService;

@RestController
@RequestMapping("api/v1/brand")
public class BrandController {
	@Autowired
	BrandService brandService;

	@GetMapping
	public Object get() {
		try {
			List<Brand> brandList = brandService.getAll();

			return new ResponseEntity<List<Brand>>(brandList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping
	public Object add(@RequestBody Brand brand) {
		try {
			brandService.add(brand);
			return new ResponseEntity<String>("Add Successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public Object update(@RequestBody Brand brand) {
		try {
			brandService.update(brand);
			return new ResponseEntity<String>("Update Successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value="/{id}")
	public Object delete(@PathVariable String id) {
		try {
			brandService.delete(id);
			return new ResponseEntity<String>("Delete Successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
}
