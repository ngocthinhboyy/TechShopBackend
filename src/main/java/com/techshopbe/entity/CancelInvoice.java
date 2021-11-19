package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CANCEL_INVOICE")
public class CancelInvoice {
	@Id
	private String id;
	
	private String cancelledDate;
	private String reason;
	private String whoCancel;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCancelledDate() {
		return cancelledDate;
	}
	public void setCancelledDate(String cancelledDate) {
		this.cancelledDate = cancelledDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getWhoCancel() {
		return whoCancel;
	}
	public void setWhoCancel(String whoCancel) {
		this.whoCancel = whoCancel;
	}
	public CancelInvoice(String id, String cancelledDate, String reason, String whoCancel) {
		super();
		this.id = id;
		this.cancelledDate = cancelledDate;
		this.reason = reason;
		this.whoCancel = whoCancel;
	}
	public CancelInvoice() {};
	
	
}
