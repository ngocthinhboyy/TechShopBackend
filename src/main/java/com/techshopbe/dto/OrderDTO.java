package com.techshopbe.dto;

import java.util.List;

public class OrderDTO {
	List<DetailedOrderDTO> listProductOrder;
	ShippingInfoDTO shippingInfo;
	
	private String email;
	
	private int totalPrice;
	
	private String note;

	public List<DetailedOrderDTO> getListProductOrder() {
		return listProductOrder;
	}

	public void setListProductOrder(List<DetailedOrderDTO> listProductOrder) {
		this.listProductOrder = listProductOrder;
	}

	public ShippingInfoDTO getShippingInfo() {
		return shippingInfo;
	}

	public void setShippingInfo(ShippingInfoDTO shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public OrderDTO(List<DetailedOrderDTO> listProductOrder, ShippingInfoDTO shippingInfo, String email,
			int totalPrice, String note) {
		super();
		this.listProductOrder = listProductOrder;
		this.shippingInfo = shippingInfo;
		this.email = email;
		this.totalPrice = totalPrice;
		this.note = note;
	}

}
