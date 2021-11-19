package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DETAILEDINVOICE")
public class DetailedInvoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int invoiceID;
	private int productID;
	private int quantity;
	private int price;
	private int totalPrice;
	private boolean isReviewed;


	public DetailedInvoice() {
	}


	public DetailedInvoice(int id, int invoiceID, int productID, int quantity, int price, int totalPrice, boolean isReviewed) {
		super();
		this.id = id;
		this.invoiceID = invoiceID;
		this.productID = productID;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.isReviewed = isReviewed;
	}


	public boolean getIsReviewed() {
		return isReviewed;
	}


	public void setIsReviewed(boolean isReviewed) {
		this.isReviewed = isReviewed;
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getInvoiceID() {
		return invoiceID;
	}


	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}


	public int getProductID() {
		return productID;
	}


	public void setProductID(int productID) {
		this.productID = productID;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
}
