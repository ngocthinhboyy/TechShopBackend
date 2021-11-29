package com.techshopbe.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.entity.Brand;
import com.techshopbe.repository.BrandRepository;
import com.techshopbe.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public List<Brand> getAll() {
		return brandRepository.findAllByIsDeleted(false);
	}

	@Override
	public void add(Brand brand) {
		brand.setId(UUID.randomUUID().toString());
		LocalDateTime createdDate = LocalDateTime.now();
		brand.setCreatedDate(createdDate.toLocalDate().toString());
		brandRepository.save(brand);
		
	}

	@Override
	public void update(Brand brand) {
		LocalDateTime lastModified = LocalDateTime.now();
		brand.setLastModified(lastModified.toLocalDate().toString());
		brandRepository.save(brand);
		
	}

	@Override
	public void delete(String id) {
		Brand brand = brandRepository.findById(id);
		LocalDateTime lastModified = LocalDateTime.now();
		brand.setLastModified(lastModified.toLocalDate().toString());
		brand.setIsDeleted(true);
		brandRepository.save(brand);
		
	}

}
