package com.techshopbe.service.impl;

import java.util.List;

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

		return brandRepository.findAll();
	}

}
