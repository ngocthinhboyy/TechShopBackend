package com.techshopbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techshopbe.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
