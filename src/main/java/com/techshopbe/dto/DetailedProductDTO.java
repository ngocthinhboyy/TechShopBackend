package com.techshopbe.dto;

import java.util.List;

public class DetailedProductDTO {
	private int id;
	private String categoryID;
	private String brandID;
	private String categoryName;
	private String brandName;
	private float rate;
	private String name;
	private int price;
	private int stock;
	private int warranty;
	private int purchased;
	private String stockStatus;
	private int totalReviews;
	private String images;
	private String shortDescrip;
	private List<ProductSpecificationDTO> specifications;
	
	
	public int getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(int totalReviews) {
		this.totalReviews = totalReviews;
	}

	public DetailedProductDTO() {}

	public DetailedProductDTO(int id, String categoryID, String brandID, String categoryName, String brandName, float rate,
			String name, int price, int stock, int warranty,
			int purchased,int totalReviews, String images, String shortDescrip) {
		super();
		this.id = id;
		this.categoryID = categoryID;
		this.brandID = brandID;
		this.categoryName = categoryName;
		this.brandName = brandName;
		this.rate = rate;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.warranty = warranty;
		this.purchased = purchased;
		this.totalReviews = totalReviews;
		this.images = images;
		this.shortDescrip = shortDescrip;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getBrandID() {
		return brandID;
	}

	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}

	public int getPurchased() {
		return purchased;
	}

	public void setPurchased(int purchased) {
		this.purchased = purchased;
	}


	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getShortDescrip() {
		return shortDescrip;
	}

	public void setShortDescrip(String shortDescrip) {
		this.shortDescrip = shortDescrip;
	}

	public List<ProductSpecificationDTO> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<ProductSpecificationDTO> specifications) {
		this.specifications = specifications;
	}

	
	
}
