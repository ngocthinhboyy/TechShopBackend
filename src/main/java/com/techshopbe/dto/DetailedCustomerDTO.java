package com.techshopbe.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DetailedCustomerDTO {
	private String fullname;
	private String phone;
	private String address;
	private String email;
	private String gender;
	private String dob;
	private String reward;
	private int accumulativeOrder;
	private int accumulativeSpending;
	public List<InvoiceForUserDTO> orders;
	
	public DetailedCustomerDTO() {
		orders = new ArrayList<InvoiceForUserDTO>();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public int getAccumulativeOrder() {
		return accumulativeOrder;
	}

	public void setAccumulativeOrder(int accumulativeOrder) {
		this.accumulativeOrder = accumulativeOrder;
	}

	public int getAccumulativeSpending() {
		return accumulativeSpending;
	}

	public void setAccumulativeSpending(int accumulativeSpending) {
		this.accumulativeSpending = accumulativeSpending;
	}
	
	
}
