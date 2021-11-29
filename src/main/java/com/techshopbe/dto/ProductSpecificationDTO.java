package com.techshopbe.dto;

public class ProductSpecificationDTO {
	private String id;
	private String name;
	private String value;
	private String dataType;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public ProductSpecificationDTO(String id, String name, String value, String dataType) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.dataType = dataType;
	}
	
	public ProductSpecificationDTO() {}

}
