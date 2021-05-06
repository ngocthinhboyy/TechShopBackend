package com.techshopbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.dto.DetailedProductDTO;
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
	
	@GetMapping(value = "/{productID}")
	public Object getDetailedProduct(@PathVariable int productID) {
		try {
			DetailedProductDTO detailedProduct = productService.getDetailedProduct(productID);
			return new ResponseEntity<DetailedProductDTO>(detailedProduct, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/category/{categorySlug}")
	public Object getProductsByCategory(@PathVariable String categorySlug) {
		try {
			List<ProductDTO> productsByCategory= productService.getProductsByCategory(categorySlug);
			return new ResponseEntity<List<ProductDTO>>(productsByCategory, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/toppurchased/{categoryID}")
	public Object getTopPurchasedProducts(@PathVariable int categoryID) {
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
