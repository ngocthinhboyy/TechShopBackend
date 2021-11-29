package com.techshopbe.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;
import com.techshopbe.dto.ProductSpecificationDTO;
import com.techshopbe.dto.RatingInfoDTO;
import com.techshopbe.dto.SpecificationAttributeDTO;
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

	@Override
	public DetailedProductDTO getDetailedProduct(int productID) {
		DetailedProductDTO detailedProduct = productRepository.findDetailedProductByid(productID);
		List<Object[]> specs = productRepository.findSpecificationsByid(productID);
		List<ProductSpecificationDTO> productSpecsList = new ArrayList<ProductSpecificationDTO>();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdminRole = auth.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

		for (Object[] spec : specs) {
			ProductSpecificationDTO specsDto = new ProductSpecificationDTO();
			
			if (isAdminRole) {
				specsDto.setId(spec[0].toString());
				specsDto.setDataType(spec[3].toString());
			}
			specsDto.setName(spec[1].toString());
			specsDto.setValue(spec[2].toString());

			productSpecsList.add(specsDto);
		}
		detailedProduct.setSpecifications(productSpecsList);
		if (detailedProduct.getStock() > 0)
			detailedProduct.setStockStatus("in-stock");
		else if (detailedProduct.getStock() == 0)
			detailedProduct.setStockStatus("out-of-stock");
		return detailedProduct;
	}

	@Override
	public List<ProductDTO> getRelatedCategoryProducts(int productID) {
		List<ProductDTO> productsByCategory = productRepository.findRelatedProductsByCategory(productID);
		List<ProductDTO> relatedProducts = new ArrayList<ProductDTO>();
		for (int i = 0; i < productsByCategory.size() && i < 4; i++) {
			relatedProducts.add(productsByCategory.get(i));
		}
		return relatedProducts;
	}

	@Override
	public List<ProductDTO> getRelatedBrandProducts(int productID) {
		List<ProductDTO> productsByBrand = productRepository.findRelatedProductsByBrand(productID);
		List<ProductDTO> relatedProducts = new ArrayList<ProductDTO>();
		// System.out.println(productsByBrand.size());
		for (int i = 0; i < productsByBrand.size() && i < 4; i++) {
			relatedProducts.add(productsByBrand.get(i));
		}
		return relatedProducts;
	}

	@Override
	public void updateRating(int productID, float rate) {
		RatingInfoDTO ratingInfoDTO = productRepository.findRatingInfoByid(productID);
		int newTotalReviews = ratingInfoDTO.getTotalReviews() + 1;
		float newRating = (ratingInfoDTO.getProductRate() * ratingInfoDTO.getTotalReviews() + rate) / newTotalReviews;
		productRepository.updateRatingInfoByid(newRating, newTotalReviews, productID);
	}

	@Override
	public List<SpecificationAttributeDTO> getProductSpecificationAttribute(String categoryID, String brandID) {
		List<Object[]> objects = productRepository.getSpecificationAttributeBycategoryIDAndbrandID(categoryID, brandID);
		List<SpecificationAttributeDTO> specifcationAttributes = new ArrayList<SpecificationAttributeDTO>();
		for (Object[] object : objects) {
			specifcationAttributes.add(new SpecificationAttributeDTO(Integer.parseInt(object[0].toString()),
					object[1].toString(), object[2].toString()));
		}
		return specifcationAttributes;
	}

}
