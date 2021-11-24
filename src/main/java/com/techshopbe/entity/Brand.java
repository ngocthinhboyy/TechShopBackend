package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BRAND")
public class Brand {
	@Id
	private String id;
	private String name;
	private String img;
	public Brand() {
	}

	public Brand(String id, String name, String img) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
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
