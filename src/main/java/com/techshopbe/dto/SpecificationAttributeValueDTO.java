package com.techshopbe.dto;

public class SpecificationAttributeValueDTO {
	private String id;
	private String name;
	private String dataType;
	private boolean isDisabled;

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

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public SpecificationAttributeValueDTO(String id, String name, String dataType, boolean isDisabled) {
		super();
		this.id = id;
		this.name = name;
		this.dataType = dataType;
		this.isDisabled = isDisabled;
	}

	public SpecificationAttributeValueDTO() {
	}
}
