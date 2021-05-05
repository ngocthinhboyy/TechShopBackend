package com.techshopbe.controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.dto.ProductDTO;
import com.techshopbe.service.ProductService;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "", params = {"sortOrder"})
	public Object index(@RequestParam String sortOrder) {
		try {
			List<ProductDTO> productList = productService.getAll(sortOrder);
			return new ResponseEntity<List<ProductDTO>>(productList, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "", params = {"categorySlug","sortOrder"})
	public Object getProductsByCategory(@RequestParam String categorySlug, @RequestParam String sortOrder) {
		try {
			List<ProductDTO> productsByCategory= productService.getProductsByCategory(categorySlug, sortOrder);
			return new ResponseEntity<List<ProductDTO>>(productsByCategory, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/toppurchased")
	public Object getTopPurchasedProducts(@RequestParam int categoryID) {
		try {
			List<ProductDTO> topPurchasedProducts = productService.getTopPurchasedProducts(categoryID);

			return new ResponseEntity<List<ProductDTO>>(topPurchasedProducts, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/trending")
	public Object getTrendingProducts() {
		try {
			List<ProductDTO> trendingProducts = productService.getTrendingProducts();
			

			return new ResponseEntity<List<ProductDTO>>(trendingProducts, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
