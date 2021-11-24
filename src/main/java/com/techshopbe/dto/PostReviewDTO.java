package com.techshopbe.dto;

public class PostReviewDTO {
	private String orderID;
	private int productID;
	private String reviewContent;
	private float rate;
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
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
	public PostReviewDTO(String orderID, int productID, String reviewContent, float rate) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.reviewContent = reviewContent;
		this.rate = rate;
	}
	
}
