package com.techshopbe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "REWARD")
public class Reward {
	@Id
	private String id;
	private String name;
	private int minOrder;
	private int minSpending;
	private int level;
	@JsonIgnore
	private boolean isDeleted;

	public Reward() {
	}

	public Reward(String id, String name, int minOrder, int minSpending, int level, boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.minOrder = minOrder;
		this.minSpending = minSpending;
		this.level = level;
		this.isDeleted = isDeleted;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
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

	public int getMinOrder() {
		return minOrder;
	}

	public void setMinOrder(int minOrder) {
		this.minOrder = minOrder;
	}

	public int getMinSpending() {
		return minSpending;
	}

	public void setMinSpending(int minSpending) {
		this.minSpending = minSpending;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
