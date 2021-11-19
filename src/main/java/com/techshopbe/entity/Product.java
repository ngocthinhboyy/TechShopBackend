package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int categoryID;
	private int brandID;
	private float rate;
	private String name;
	private int price;
	private String shortDescrip;
	private int stock;
	private int warranty;
	private int purchased;
	private String specs;
	private String shortTech;
	private int totalReviews;
	private String images;

	public Product() {
	}

	public Product(int id, int categoryID, int brandID, float rate, String name, int price, int stock, int warranty, int purchased, String specs,
			String shortTech, int totalReviews, String images, 
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
		this.specs = specs;
		this.shortTech = shortTech;
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

	public String getShortTech() {
		return shortTech;
	}

	public void setShortTech(String shortTech) {
		this.shortTech = shortTech;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getBrandID() {
		return brandID;
	}

	public void setBrandID(int brandID) {
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

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

}
