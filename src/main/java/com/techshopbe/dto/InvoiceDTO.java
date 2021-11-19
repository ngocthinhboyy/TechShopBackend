package com.techshopbe.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class InvoiceDTO {
	List<DetailedInvoiceDTO> detailedInvoices;
	ShippingInfoDTO shippingInfo;

	private String email;

	private int totalPrice;

	private String note;
	private String status;
	
	private String statusNote;
	private String statusDetail;
	private boolean isCancelled;
	private String[] processDate;
	private String cancelledDate;
	private String reason;
	private int totalItems;
	

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

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public String[] getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String[] processDate) {
		this.processDate = processDate;
	}

	public String getCancelledDate() {
		return cancelledDate;
	}

	public void setCancelledDate(String cancelledDate) {
		this.cancelledDate = cancelledDate;
	}

	public InvoiceDTO() {

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<DetailedInvoiceDTO> getDetailedInvoices() {
		return detailedInvoices;
	}

	public void setDetailedInvoices(List<DetailedInvoiceDTO> detailedInvoices) {
		this.detailedInvoices = detailedInvoices;
	}

	public InvoiceDTO(List<DetailedInvoiceDTO> detailedInvoices, ShippingInfoDTO shippingInfo, String email,
			int totalPrice, String note, String status) {
		super();
		this.detailedInvoices = detailedInvoices;
		this.shippingInfo = shippingInfo;
		this.email = email;
		this.totalPrice = totalPrice;
		this.note = note;
		this.status = status;
	}

	public InvoiceDTO(List<DetailedInvoiceDTO> detailedInvoices, ShippingInfoDTO shippingInfo, String email,
			int totalPrice, String note, String status,
			String statusNote, String statusDetail, boolean isCancelled, String[] processDate, String cancelledDate) {
		super();
		this.detailedInvoices = detailedInvoices;
		this.shippingInfo = shippingInfo;
		this.email = email;
		this.totalPrice = totalPrice;
		this.note = note;
		this.status = status;
		this.statusNote = statusNote;
		this.statusDetail = statusDetail;
		this.isCancelled = isCancelled;
		this.processDate = processDate;
		this.cancelledDate = cancelledDate;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	

	
	

}
