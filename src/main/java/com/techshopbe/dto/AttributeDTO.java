package com.techshopbe.dto;

public class AttributeDTO {
	private String id;
	private String dataType;
	private String value;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	

	public AttributeDTO(String id, String dataType, String value) {
		super();
		this.id = id;
		this.dataType = dataType;
		this.value = value;
	}

	public AttributeDTO() {
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
}
