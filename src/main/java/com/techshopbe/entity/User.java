package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String fullname;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String pswd;
	private String DOB;
	private String phone;
	private String address;
	private int roleID;
	private String gender;
	private int totalInvoices = 0;
	private String rewardID;
	private int accumulativeOrder = 0;
	private int accumulativeSpending = 0;
	private boolean isDeleted;

	public User() {
	};

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public User(int id, String email, String fullname, String pswd, String dOB, String phone, String address,
			int roleID, String gender, int totalInvoices, String rewardID, int accumulativeOrder,
			int accumulativeSpending, boolean isDeleted) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.pswd = pswd;
		DOB = dOB;
		this.phone = phone;
		this.address = address;
		this.roleID = roleID;
		this.gender = gender;
		this.totalInvoices = totalInvoices;
		this.rewardID = rewardID;
		this.accumulativeOrder = accumulativeOrder;
		this.accumulativeSpending = accumulativeSpending;
		this.isDeleted = isDeleted;
	}

	public int getTotalInvoices() {
		return totalInvoices;
	}

	public void setTotalInvoices(int totalInvoices) {
		this.totalInvoices = totalInvoices;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
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

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRewardID() {
		return rewardID;
	}

	public void setRewardID(String rewardID) {
		this.rewardID = rewardID;
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
