package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INVOICE")
public class Invoice {
	@Id
	private String id;
	private String userID;
	private int totalCost;
	private String note;
	private boolean otherShippingAddress;
	private String status;
	private String userInvoiceIndex;
	private String statusID;
	private String cancelID;
	private boolean isCancelled;
	private String processDate;
	private int step;
	private int totalItems;
	
	public String getStatusID() {
		return statusID;
	}


	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}


	public boolean isCancelled() {
		return isCancelled;
	}


	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}


	public String getProcessDate() {
		return processDate;
	}


	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	

	public Invoice() {
	}
	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getUserInvoiceIndex() {
		return userInvoiceIndex;
	}


	public void setUserInvoiceIndex(String userInvoiceIndex) {
		this.userInvoiceIndex = userInvoiceIndex;
	}


	public Invoice(String id, String userID, int totalCost, String note,
			boolean otherShippingAddress, String status, String userInvoiceIndex) {
		super();
		this.id = id;
		this.userID = userID;
		this.totalCost = totalCost;
		this.note = note;
		this.otherShippingAddress = otherShippingAddress;
		this.status = status;
		this.userInvoiceIndex = userInvoiceIndex;
	}
	


	public Invoice(String id, String userID, int totalCost, String note,
			boolean otherShippingAddress, String status, String userInvoiceIndex, String statusID, String cancelID,
			boolean isCancelled, String processDate, int step) {
		super();
		this.id = id;
		this.userID = userID;
		this.totalCost = totalCost;
		this.note = note;
		this.otherShippingAddress = otherShippingAddress;
		this.status = status;
		this.userInvoiceIndex = userInvoiceIndex;
		this.statusID = statusID;
		this.cancelID = cancelID;
		this.isCancelled = isCancelled;
		this.processDate = processDate;
		this.step = step;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
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


	public String getCancelID() {
		return cancelID;
	}


	public void setCancelID(String cancelID) {
		this.cancelID = cancelID;
	}


	public int getStep() {
		return step;
	}


	public void setStep(int step) {
		this.step = step;
	}


	public int getTotalItems() {
		return totalItems;
	}


	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	
	
}
