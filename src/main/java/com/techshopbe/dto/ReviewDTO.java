package com.techshopbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ReviewDTO {
	private String id;
	private String productID;
	private String fullname;
	private String reviewDate;
	private String reviewContent;
	private String status;
	private float rate;

	public ReviewDTO() {
	}

	public ReviewDTO(String id, String productID, String fullname, String reviewDate, String reviewContent,
			float rate) {
		super();
		this.id = id;
		this.productID = productID;
		this.fullname = fullname;
		this.reviewDate = reviewDate;
		this.reviewContent = reviewContent;
		this.rate = rate;
	}
	public ReviewDTO(String id, String productID, String fullname, String reviewDate, String reviewContent,
			float rate, String status) {
		super();
		this.id = id;
		this.productID = productID;
		this.fullname = fullname;
		this.reviewDate = reviewDate;
		this.reviewContent = reviewContent;
		this.rate = rate;
		this.setStatus(status);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
