package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DetailedInvoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int detailedInvoiceID;
	private int invoiceID;
	private int productID;
	private int quantity;
	private int price;
	private int totalPrice;


	public DetailedInvoice() {
	}


	public DetailedInvoice(int detailedInvoiceID, int invoiceID, int productID, int quantity, int price, int totalPrice) {
		super();
		this.detailedInvoiceID = detailedInvoiceID;
		this.invoiceID = invoiceID;
		this.productID = productID;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getDetailedInvoiceID() {
		return detailedInvoiceID;
	}


	public void setDetailedInvoiceID(int detailedInvoiceID) {
		this.detailedInvoiceID = detailedInvoiceID;
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
