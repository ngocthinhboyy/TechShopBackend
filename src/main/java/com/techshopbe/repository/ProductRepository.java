package com.techshopbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techshopbe.dto.ProductDTO;
import com.techshopbe.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specs) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID ORDER BY p.productPrice ASC")
	List<ProductDTO> findAllByOrderByPriceAsc();
	
	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specs) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID ORDER BY p.productPrice DESC")
	List<ProductDTO> findAllByOrderByPriceDesc();

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specs) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID AND c.categoryID = ?1 ORDER BY p.purchased DESC")
	List<ProductDTO> findTopPurchasedByCategoryId(int categoryID);

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specs) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID ORDER BY p.purchased DESC")
	List<ProductDTO> findTrendingProducts();

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specs) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID AND c.categorySlug = ?1 ORDER BY p.productPrice ASC")
	List<ProductDTO> findByCategorySlugByOrderByPriceAsc(String categorySlug);
	
	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specs) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID AND c.categorySlug = ?1 ORDER BY p.productPrice DESC")
	List<ProductDTO> findByCategorySlugByOrderByPriceDesc(String categorySlug);
	
	
}
