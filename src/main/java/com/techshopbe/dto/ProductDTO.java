package com.techshopbe.dto;

public class ProductDTO {

	private String id;
	private String categoryName;
	private String brandName;
	private float rate;
	private String name;
	private int price;
	private int stock;
	private int warranty;
	private int purchased;
	private String categorySlug;
	private String images;
	private String shortDescrip;
	private boolean isDeleted;

	


	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getShortDescrip() {
		return shortDescrip;
	}

	public void setShortDescrip(String shortDescrip) {
		this.shortDescrip = shortDescrip;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getcategorySlug() {
		return categorySlug;
	}

	public void setcategorySlug(String categorySlug) {
		this.categorySlug = categorySlug;
	}

	public ProductDTO() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public int getprice() {
		return price;
	}

	public void setprice(int price) {
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

	public ProductDTO(String id, String categoryName, String brandName, float rate, String name,
			int price, int stock, int warranty, int purchased, String categorySlug, String images, String shortDescrip) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.brandName = brandName;
		this.rate = rate;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.warranty = warranty;
		this.purchased = purchased;
		this.categorySlug = categorySlug;
		this.images = images;
		this.shortDescrip = shortDescrip;
	}
	
	
	
}
