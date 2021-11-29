package com.techshopbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techshopbe.entity.Brand;

public interface BrandRepository  extends JpaRepository<Brand, Integer> {

	Brand findById(String id);
	List<Brand> findAllByIsDeleted(boolean isDeleted);
}
