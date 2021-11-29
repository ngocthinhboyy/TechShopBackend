package com.techshopbe.service;

import java.util.List;

import com.techshopbe.entity.Category;

public interface CategoryService {
	public List<Category> getAll();
	public void add(Category category);
	public void update(Category category);
	public void delete(String id);
}