package com.techshopbe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.ReviewDTO;
import com.techshopbe.repository.ReviewRepository;
import com.techshopbe.service.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public List<ReviewDTO> getAllReviewsByProductID(int productID, Pageable page) {
		return reviewRepository.getAllByProductID(productID, page);
		
	}

}
