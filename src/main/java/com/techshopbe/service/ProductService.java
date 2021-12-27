package com.techshopbe.service;

import java.util.List;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;
import com.techshopbe.dto.ProductRequestDTO;
import com.techshopbe.dto.SpecificationAttributeValueDTO;

public interface ProductService {
	public List<ProductDTO> getAll();

	public List<ProductDTO> getTrendingProducts();

	public List<ProductDTO> getProductsByCategory(String categorySlug);

	public List<ProductDTO> getTopPurchasedProducts(int categoryID);

	public DetailedProductDTO getDetailedProduct(String productID);

	public List<ProductDTO> getRelatedCategoryProducts(String productID);

	public List<ProductDTO> getRelatedBrandProducts(String productID);

	public void updateRating(String productID, float rate);

	public List<SpecificationAttributeValueDTO> getProductSpecificationAttribute(String categoryID, String brandID);

	public ProductDTO add(ProductRequestDTO product);

	public void update(ProductRequestDTO product) throws Exception;
	
	public void delete(String id) throws Exception;
	
	public void updateAttributeStatus(String id);
	
	public void deleteSpecification(String id);
	
	public int countProductAffectedWhenDeleteAttibute(String id);
}
