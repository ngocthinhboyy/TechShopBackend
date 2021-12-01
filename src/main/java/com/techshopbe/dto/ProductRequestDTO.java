package com.techshopbe.dto;

import java.util.List;

public class ProductRequestDTO {
	List<AttributeDTO> existedAttributes;
	List<ProductSpecificationDTO> newSpecifications;
	String id;
	int price;
	String shortDescription;
	int stock;
	int warranty;
	String brand;
	String category;
	String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AttributeDTO> getExistedAttributes() {
		return existedAttributes;
	}

	public void setExistedAttributes(List<AttributeDTO> existedAttributes) {
		this.existedAttributes = existedAttributes;
	}

	public List<ProductSpecificationDTO> getNewSpecifications() {
		return newSpecifications;
	}

	public void setNewSpecifications(List<ProductSpecificationDTO> newSpecifications) {
		this.newSpecifications = newSpecifications;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
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

	public ProductRequestDTO(List<AttributeDTO> existedAttributes,
			List<ProductSpecificationDTO> newSpecifications, int price, String shortDescription,
			int stock, int warranty) {
		super();
		this.existedAttributes = existedAttributes;
		this.newSpecifications = newSpecifications;
		this.price = price;
		this.shortDescription = shortDescription;
		this.stock = stock;
		this.warranty = warranty;
	}

	public ProductRequestDTO() {
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
