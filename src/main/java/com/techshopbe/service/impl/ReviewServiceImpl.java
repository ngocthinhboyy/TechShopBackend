package com.techshopbe.service.impl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.PostReviewDTO;
import com.techshopbe.dto.ReviewDTO;
import com.techshopbe.entity.DetailedInvoice;
import com.techshopbe.entity.Review;
import com.techshopbe.repository.DetailedInvoiceRepository;
import com.techshopbe.repository.ProductRepository;
import com.techshopbe.repository.ReviewRepository;
import com.techshopbe.security.CustomUserDetails;
import com.techshopbe.service.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	DetailedInvoiceRepository detailedInvoiceRepository;
	@Autowired
	ProductRepository productRepository;
	

	@Override
	public List<ReviewDTO> getAllReviewsByProductID(String productID, Pageable page) {
		return reviewRepository.getAllByProductID(productID, page);
		
	}

	@Override
	public void addReview(List<PostReviewDTO> postReviewDTO) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal();
		
		for(PostReviewDTO postReview : postReviewDTO) {
			Review review = new Review();
			review.setId(UUID.randomUUID().toString());
			review.setProductID(postReview.getProductID());
			review.setRate(postReview.getRate());
			review.setReviewContent(postReview.getReviewContent());
			review.setReviewDate(LocalDateTime.now().toString());
			review.setUserID(userDetails.getUserID());
			reviewRepository.save(review);
			
			DetailedInvoice detailedInvoice = detailedInvoiceRepository.findByInvoiceIDAndProductID(postReview.getOrderID(), postReview.getProductID());
			detailedInvoice.setIsReviewed(true);
			detailedInvoice.setReviewID(review.getId());
			detailedInvoiceRepository.save(detailedInvoice);
			
			int totalReviews = productRepository.findTotalReviewsById(postReview.getProductID());
			totalReviews += 1;
			productRepository.updateTotalReviewsById(totalReviews, postReview.getProductID());
		}

		
	}

}
