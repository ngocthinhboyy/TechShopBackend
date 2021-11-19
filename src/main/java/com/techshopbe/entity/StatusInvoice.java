package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATUS_INVOICE")
public class StatusInvoice {
	@Id
	private String id;
	
	private String status;
	private String detail;
	private String note;
	private int step;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public StatusInvoice(String id, String status, String detail, String note, int step) {
		super();
		this.id = id;
		this.status = status;
		this.detail = detail;
		this.note = note;
		this.step = step;
	}
	public StatusInvoice() {}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	};
	
	

}
