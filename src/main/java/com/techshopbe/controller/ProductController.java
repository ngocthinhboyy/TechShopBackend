package com.techshopbe.controller;

import java.util.List;

import javax.transaction.Transactional;

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

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;
import com.techshopbe.dto.ProductRequestDTO;
import com.techshopbe.dto.SpecificationAttributeValueDTO;
import com.techshopbe.service.ProductService;


@RestController
@RequestMapping("api/v1/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping(value = "")
	public Object get() {
		try {
			List<ProductDTO> productList = productService.getAll();
			return new ResponseEntity<List<ProductDTO>>(productList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{productID}")
	public Object getDetailedProduct(@PathVariable String productID) {
		try {
			DetailedProductDTO detailedProduct = productService.getDetailedProduct(productID);
			return new ResponseEntity<DetailedProductDTO>(detailedProduct, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/category/{categorySlug}")
	public Object getProductsByCategory(@PathVariable String categorySlug) {
		try {
			List<ProductDTO> productsByCategory = productService.getProductsByCategory(categorySlug);
			return new ResponseEntity<List<ProductDTO>>(productsByCategory, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/toppurchased/{categoryID}")
	public Object getTopPurchasedProducts(@PathVariable int categoryID) {
		try {
			List<ProductDTO> topPurchasedProducts = productService.getTopPurchasedProducts(categoryID);

			return new ResponseEntity<List<ProductDTO>>(topPurchasedProducts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/trending")
	public Object getTrendingProducts() {
		try {
			List<ProductDTO> trendingProducts = productService.getTrendingProducts();

			return new ResponseEntity<List<ProductDTO>>(trendingProducts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "related-category/{productID}")
	public Object getRelatedCategoryProducts(@PathVariable String productID) {
		
		try {
			List<ProductDTO> relatedCategoryProducts = productService.getRelatedCategoryProducts(productID);
			
			return new ResponseEntity<List<ProductDTO>>(relatedCategoryProducts, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "related-brand/{productID}")
	public Object getRelatedBrandProducts(@PathVariable String productID) {
		
		try {
			List<ProductDTO> relatedBrandProducts = productService.getRelatedBrandProducts(productID);

			return new ResponseEntity<List<ProductDTO>>(relatedBrandProducts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "specification/{categoryID}/{brandID}")
	public Object getProductSpecificationAttribute(@PathVariable String categoryID, @PathVariable String brandID) {
		try {
			List<SpecificationAttributeValueDTO> specificationAttributes = productService.getProductSpecificationAttribute(categoryID, brandID);
			return new ResponseEntity<List<SpecificationAttributeValueDTO>>(specificationAttributes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional
	@PostMapping
	public Object addProduct(@RequestBody ProductRequestDTO product) {
		try {
			ProductDTO newProduct = productService.add(product);
			return new ResponseEntity<ProductDTO>(newProduct, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional
	@PutMapping
	public Object updateProduct(@RequestBody ProductRequestDTO product) {
		try {
			ProductDTO updatedProduct= productService.update(product);
			return new ResponseEntity<ProductDTO>(updatedProduct, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional
	@DeleteMapping(value = "/specification/{id}")
	public Object deleteProductSpecification(@PathVariable String id) {
		try {
			productService.deleteSpecification(id);
			return new ResponseEntity<String>("Delete Specification Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public Object deleteProduct(@PathVariable String id) {
		try {
			productService.delete(id);
			return new ResponseEntity<String>("Delete Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional
	@PutMapping(value = "/specification/{id}")
	public Object updateAttributeStatus(@PathVariable String id) {
		try {
			productService.updateAttributeStatus(id);
			return new ResponseEntity<String>("Update Attribute Status Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/specification/affected-product/{id}")
	public Object countAffectedProduct(@PathVariable String id) {
		try {
			int affectedProduct = productService.countProductAffectedWhenDeleteAttibute(id);
			return new ResponseEntity<Integer>(affectedProduct, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
