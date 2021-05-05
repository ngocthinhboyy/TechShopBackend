package com.techshopbe.service;

import java.util.List;

import javax.swing.SortOrder;

import com.techshopbe.dto.ProductDTO;

public interface ProductService {
	public List<ProductDTO> getAll(String sortOrder);
	public List<ProductDTO> getTrendingProducts();
	public List<ProductDTO> getProductsByCategory(String categorySlug, String sortOrder);
	public List<ProductDTO> getTopPurchasedProducts(int categoryID);
}
