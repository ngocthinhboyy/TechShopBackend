package com.techshopbe.dto;

public class SpecificationAttributeDTO {
	private int id;
	private String name;
	private String dataType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public SpecificationAttributeDTO(int id, String name, String dataType) {
		super();
		this.id = id;
		this.name = name;
		this.dataType = dataType;
	}
	
	public SpecificationAttributeDTO() {};
	
}
