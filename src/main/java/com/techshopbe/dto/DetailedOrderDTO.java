package com.techshopbe.dto;

public class DetailedOrderDTO {
	private int productID;
	private int productPrice;
	private int quantity;
	private int totalPrice;

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public DetailedOrderDTO(int productID, int productPrice, int quantity, int totalPrice) {
		super();
		this.productID = productID;
		this.productPrice = productPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

}
