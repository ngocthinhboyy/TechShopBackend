package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHIPPINGINFO")
public class ShippingInfo {
	@Id
	private String id;
	private String invoiceID;
	private String fullname;
	private String phone;
	private String address;

	public ShippingInfo() {
	}
	

	public ShippingInfo(String id, String invoiceID, String fullname, String phone, String address) {
		super();
		this.id = id;
		this.invoiceID = invoiceID;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
