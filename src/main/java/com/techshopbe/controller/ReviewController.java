package com.techshopbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.dto.ReviewDTO;
import com.techshopbe.service.ReviewService;

@RestController
@RequestMapping("api/v1/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;

	@GetMapping(value = "/{productID}")
	public Object getAllReviewsByProductID(@PathVariable int productID, @RequestParam int limit,
			@RequestParam int page) {
		try {
			//System.out.println(page);
			List<ReviewDTO> reviewsByProductID = reviewService.getAllReviewsByProductID(productID, PageRequest.of(page,  limit));
			return new ResponseEntity<List<ReviewDTO>>(reviewsByProductID, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

}
