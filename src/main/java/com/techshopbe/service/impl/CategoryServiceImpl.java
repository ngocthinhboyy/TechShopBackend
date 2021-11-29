package com.techshopbe.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.entity.Category;
import com.techshopbe.repository.CategoryRepository;
import com.techshopbe.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public List<Category> getAll() {
		return categoryRepository.findAllByIsDeleted(false);
	}
	@Override
	public void add(Category category) {
		String categoryID = UUID.randomUUID().toString();
		LocalDateTime createdDate = LocalDateTime.now();
		category.setId(categoryID);
		category.setCreatedDate(createdDate.toLocalDate().toString());
		categoryRepository.save(category);
	}
	@Override
	public void update(Category category) {
		LocalDateTime lastModified = LocalDateTime.now();
		category.setLastModified(lastModified.toLocalDate().toString());
		categoryRepository.save(category);
	}
	@Override
	public void delete(String id) {
		Category category = categoryRepository.findById(id);
		LocalDateTime lastModified = LocalDateTime.now();
		category.setLastModified(lastModified.toLocalDate().toString());
		category.setIsDeleted(true);
		categoryRepository.save(category);
		
	}

}
