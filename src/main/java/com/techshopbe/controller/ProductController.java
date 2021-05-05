package com.techshopbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.dto.ProductDTO;
import com.techshopbe.service.ProductService;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "")
	public Object index() {
		try {
			List<ProductDTO> productList = productService.getAll();

			return new ResponseEntity<List<ProductDTO>>(productList, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

}
