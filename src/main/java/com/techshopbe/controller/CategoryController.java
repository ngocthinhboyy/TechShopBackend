package com.techshopbe.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.entity.Category;
import com.techshopbe.service.CategoryService;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping()
	public Object index() {
		try {
			List<Category> categoryList = categoryService.getAll();

			return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	public Object add(@RequestBody Category category) {
		try {
			categoryService.add(category);
			return new ResponseEntity<String>("Add Successfully!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
}
