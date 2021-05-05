package com.techshopbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techshopbe.dto.ProductDTO;
import com.techshopbe.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.productID, c.categoryName, b.brandName, p.productRate, p.productName, p.productPrice, p.shortDescrip, p.longDescrip,p.stock, p.warranty, p.purchased, p.specs) FROM Product p, Category c, Brand b WHERE p.categoryID = c.categoryID AND p.brandID = b.brandID")
	List<ProductDTO> getAll();
}
