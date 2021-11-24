package com.techshopbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class InvoiceForUserDTO {
	private String id;
	private String status;
	private String statusNote;
	private String statusDetail;
	private String reason;
	private int totalItems;
	private int total;
	private FirstProduct firstProduct;
	private String lastConfirm;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusNote() {
		return statusNote;
	}
	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}
	public String getStatusDetail() {
		return statusDetail;
	}
	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public InvoiceForUserDTO(String id, String status, String statusNote, String statusDetail, int totalItems, String lastConfirm) {
		super();
		this.id = id;
		this.status = status;
		this.statusNote = statusNote;
		this.statusDetail = statusDetail;
		this.totalItems = totalItems;
		this.lastConfirm = lastConfirm;
		this.firstProduct = new FirstProduct();
	}
	public InvoiceForUserDTO() {};
	public FirstProduct getFirstProduct() {
		return firstProduct;
	}
	public void setFirstProduct(int id, String categorySlug, String name, String image, int quantity,
			int oldPrice, int salePrice, int total) {
		this.firstProduct = new FirstProduct();
		this.firstProduct.id = id;
		this.firstProduct.categorySlug = categorySlug;
		this.firstProduct.name = name;
		this.firstProduct.images = image;
		this.firstProduct.quantity = quantity;
		this.firstProduct.oldPrice = oldPrice;
		this.firstProduct.salePrice = salePrice;
		this.firstProduct.total = total;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getLastConfirm() {
		return lastConfirm;
	}
	public void setLastConfirm(String lastConfirm) {
		this.lastConfirm = lastConfirm;
	}
	
}

class FirstProduct {
	public int id;
	public String categorySlug;
	public String name;
	public String images;
	public int quantity;
	public int oldPrice;
	public int salePrice;
	public int total;
	public FirstProduct() {};
	public FirstProduct(int id, String categorySlug, String name, String image, int quantity,
			int oldPrice, int salePrice, int total) {
		super();
		this.id = id;
		this.categorySlug = categorySlug;
		this.name = name;
		this.images = image;
		this.quantity = quantity;
		this.oldPrice = oldPrice;
		this.salePrice = salePrice;
		this.total = total;
	}
	
}
