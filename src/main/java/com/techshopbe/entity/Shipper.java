package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "SHIPPER")
@JsonInclude(Include.NON_NULL)
public class Shipper {
	@Id
	private String id;
	private String name;
	private int age;
	private String phone;
	@JsonIgnore
	private boolean isDeleted;
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Shipper(String id, String name, int age, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.phone = phone;
	}
	
	public Shipper() {};
}
