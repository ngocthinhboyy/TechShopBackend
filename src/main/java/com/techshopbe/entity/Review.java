package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REVIEW")
public class Review {
	@Id
	private String id;
	private String productID;
	private String userID;
	private String reviewDate;
	private String reviewContent;
	private String status;
	private float rate;

	public Review() {
	}

	public Review(String id, String productID, String userID, String reviewDate, String reviewContent, float rate) {
		super();
		this.id = id;
		this.productID = productID;
		this.userID = userID;
		this.reviewDate = reviewDate;
		this.reviewContent = reviewContent;
		this.rate = rate;
	}
	public Review(String id, String productID, String userID, String reviewDate, String reviewContent, float rate, String status) {
		super();
		this.id = id;
		this.productID = productID;
		this.userID = userID;
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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
