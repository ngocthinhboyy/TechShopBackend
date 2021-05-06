package com.techshopbe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.ProductDTO;
import com.techshopbe.repository.ProductRepository;
import com.techshopbe.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDTO> getAll() {
		return productRepository.getAll();
		
	}

	@Override
	public List<ProductDTO> getTrendingProducts() {
		List<ProductDTO> products = productRepository.findTrendingProducts();
		List<ProductDTO> trendingProducts = new ArrayList<ProductDTO>();
		for (int i = 0; i < 8; i++) {
			trendingProducts.add(products.get(i));
		}
		return trendingProducts;
	}


	@Override
	public List<ProductDTO> getTopPurchasedProducts(int categoryID) {
		
		List<ProductDTO> productsByCategory = productRepository.findTopPurchasedByCategoryId(categoryID);
		List<ProductDTO> topPurchasedProducts = new ArrayList<ProductDTO>();
		for (int i = 0; i < 5; i++) {
			topPurchasedProducts.add(productsByCategory.get(i));
		}
		return topPurchasedProducts;
		
	}

	@Override
	public List<ProductDTO> getProductsByCategory(String categorySlug) {
		
		return productRepository.findByCategorySlug(categorySlug);
		
	}

	
}
