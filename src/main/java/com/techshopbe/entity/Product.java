package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.techshopbe.dto.ProductRequestDTO;

@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	private String id;
	private String categoryID;
	private String brandID;
	private float rate;
	private String name;
	private int price;
	private String longDescrip;
	private String shortDescrip;
	private int stock;
	private int warranty;
	private int purchased;
	private int totalReviews;
	private String images;
	private boolean isDeleted;
	private String attributeSetID;

	public Product() {
	}

	public Product(String id, String categoryID, String brandID, float rate, String name, int price, int stock, int warranty, int purchased,
			 int totalReviews, String images, 
			String shortDescrip) {
		super();
		this.id = id;
		this.categoryID = categoryID;
		this.brandID = brandID;
		this.rate = rate;
		this.name = name;
		this.price = price;
		this.shortDescrip = shortDescrip;
		this.stock = stock;
		this.warranty = warranty;
		this.purchased = purchased;
		this.totalReviews = totalReviews;
		this.images = images;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(int totalReviews) {
		this.totalReviews = totalReviews;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getShortDescrip() {
		return shortDescrip;
	}

	public void setShortDescrip(String shortDescrip) {
		this.shortDescrip = shortDescrip;
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
	
	public Product(ProductRequestDTO newProduct) {
		super();
		this.id = newProduct.getId();
		this.categoryID = newProduct.getCategory();
		this.brandID = newProduct.getBrand();
		this.rate = 0;
		this.name = newProduct.getName();
		this.price = newProduct.getPrice();
		this.shortDescrip = newProduct.getShortDescription();
		this.stock = newProduct.getStock();
		this.warranty = newProduct.getWarranty();
		this.purchased = 0;
		this.totalReviews = 0;
		this.images = "";
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getAttributeSetID() {
		return attributeSetID;
	}

	public void setAttributeSetID(String attributeSetID) {
		this.attributeSetID = attributeSetID;
	}

	public String getLongDescrip() {
		return longDescrip;
	}

	public void setLongDescrip(String longDescrip) {
		this.longDescrip = longDescrip;
	}

	
}
