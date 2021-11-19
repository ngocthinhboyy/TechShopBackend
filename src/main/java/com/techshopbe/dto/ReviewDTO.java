package com.techshopbe.dto;

public class ReviewDTO {
	private int id;
	private int productID;
	private String fullname;
	private String reviewDate;
	private String reviewContent;
	private float rate;

	public ReviewDTO() {
	}

	public ReviewDTO(int id, int productID, String fullname, String reviewDate, String reviewContent,
			float rate) {
		super();
		this.id = id;
		this.productID = productID;
		this.fullname = fullname;
		this.reviewDate = reviewDate;
		this.reviewContent = reviewContent;
		this.rate = rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
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

}
