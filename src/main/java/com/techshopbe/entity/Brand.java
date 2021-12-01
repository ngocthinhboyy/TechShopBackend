package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BRAND")
public class Brand {
	@Id
	private String id;
	private String name;
	private String img;
	private String createdDate;
	private String lastModified;
	@JsonIgnore
	private boolean isDeleted;
	
	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public Brand() {
	}




	public Brand(String id, String name, String img, String createdDate, String lastModified, boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
		this.createdDate = createdDate;
		this.lastModified = lastModified;
		this.isDeleted = isDeleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String brandImg) {
		this.img = brandImg;
	}

	

}
