package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Brand {
	@Id
	private int brandID;
	private String brandName;
	

	public Brand() {
	}

	public int getBrandID() {
		return brandID;
	}

	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}



	public Brand(int brandID, String brandName) {
		super();
		this.brandID = brandID;
		this.brandName = brandName;
	}

}
