package com.techshopbe.entity;

import javax.persistence.Id;

public class Invoice {
	@Id
	private int invoiceID;
	private int userID;
	private int totalCost;
	private String invoiceDate;
	private String shippingDate;
	private String note;
	private boolean otherShippingAddress;

	public Invoice() {
	}
	

	public Invoice(int invoiceID, int userID, int totalCost, String invoiceDate, String shippingDate, String note,
			boolean otherShippingAddress) {
		super();
		this.invoiceID = invoiceID;
		this.userID = userID;
		this.totalCost = totalCost;
		this.invoiceDate = invoiceDate;
		this.shippingDate = shippingDate;
		this.note = note;
		this.otherShippingAddress = otherShippingAddress;
	}


	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isOtherShippingAddress() {
		return otherShippingAddress;
	}

	public void setOtherShippingAddress(boolean otherShippingAddress) {
		this.otherShippingAddress = otherShippingAddress;
	}
	
	
}
