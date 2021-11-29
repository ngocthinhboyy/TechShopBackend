package com.techshopbe.service;

import java.util.List;

import com.techshopbe.entity.Brand;



public interface BrandService {
	public List<Brand> getAll();
	public void add(Brand brand);
	public void update(Brand brand);
	public void delete(String id);
}
