package com.techshopbe.dto;

public class PostReviewDTO {
	private String orderID;
	private String productID;
	private String reviewContent;
	private String status;
	private float rate;

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
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

	public PostReviewDTO(String orderID,  String productID, String reviewContent, float rate) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.reviewContent = reviewContent;
		this.rate = rate;
	}
	public PostReviewDTO(String orderID,  String productID, String reviewContent, float rate, String status) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.reviewContent = reviewContent;
		this.rate = rate;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
