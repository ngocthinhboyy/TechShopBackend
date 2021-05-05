package com.techshopbe.service.impl;

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

}
